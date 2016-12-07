package com.bean.service;

import com.alibaba.fastjson.JSON;
import com.bean.dao.*;
import com.bean.model.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.MyLogger;
import utils.SmsPost;
import utils.Utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by bean on 2016/6/22.
 */
@Service("vendingService")
public class VendingServiceImpl implements VendingService {
    private MyLogger LOGGER = new MyLogger(VendingServiceImpl.class);
    @Autowired
    private CanbinetCellMapper canbinetCellMapper;
    @Autowired
    private CaptchaRecordMapper captchaRecordMapper;
    @Autowired
    private CardRechargeMapper cardRechargeMapper;
    @Autowired
    private CardRecordMapper cardRecordMapper;
    @Autowired
    private DeviceTemperatureRecordMapper deviceTemperatureRecordMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private PackageRecordMapper packageRecordMapper;

    @Autowired
    private VendorMapper vendorMapper;
    @Autowired
    private VendorAisleMapper vendorAisleMapper;

    @Autowired
    private VendingUpMapper vendingUpMapper;
    @Autowired
    private VendingMapper vendingMapper;
    @Autowired
    private VendingTrackMapper vendingTrackMapper;
    @Autowired
    private VendingStockMapper vendingStockMapper;
    @Autowired
    private CanbinetCellLogMapper canbinetCellLogMapper;

    @Autowired
    private OrderGoodsMapper orderGoodsMapper;
    @Autowired
    private CanbinetMapper canbinetMapper;
    @Autowired
    private VendingAdvMapper vendingAdvMapper;
    @Autowired
    private CanbinetManagerRecordMapper canbinetManagerRecordMapper;
    @Autowired
    private CanbinetManagerMapper canbinetManagerMapper;
    @Autowired
    private CardRecordService cardRecordService;


    /**
     * 得到商品管理信息
     *
     * @return 商品管理信息List
     */
    @Override
    public List<Vending> getGoodsManageList(String vendingCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("vendingCode", vendingCode);
        LOGGER.info("得到商品管理信息 vendingCode :" + vendingCode);
        return vendingMapper.getGoodsManageList(map);
    }

    /**
     * 销售订单
     *
     * @param vendingCode 机器号
     * @param orderCode   订单号
     * @param TrackCode   货道号
     * @param goodsInfoId 商品id
     * @param oldPrice    应付价格
     * @param prePrice    实付价格
     * @param rfidCode    芯片号
     * @param isSuccess   是否成功0失败 1成功
     * @param stockNum    库存
     * @param datetime    交易时间
     * @return 成功或失败
     */
    @Override
    public boolean insertOrder(String vendingCode, String orderCode, String TrackCode, Long goodsInfoId, BigDecimal price, BigDecimal oldPrice, BigDecimal prePrice, String rfidCode, Integer isSuccess, Integer stockNum, Date datetime) {
        Order order = new Order();
        order.setOrderCode(orderCode);
        order.setOrderOldPrice(oldPrice);
        order.setOrderPrice(prePrice);
        order.setRfidCode(rfidCode);
        order.setIsSuccess(isSuccess == 1);
        order.setPayTime(datetime);
        order.setCreateTime(new Date());
        order.setOrderStatus("3");
        order.setOrderMode("1");
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            if (!cardRecordService.cardRecord(rfidCode, prePrice, sdf.format(datetime), 1 + "", orderCode)) {
                return false;
            }

            OrderGoods orderGoods = new OrderGoods();

            Map<String, Object> vendingMap = new ConcurrentHashMap<>();//机器
            vendingMap.put("vendorNum", vendingCode);
            Map<String, Object> vendingTrackMap = new ConcurrentHashMap<>();//货道
            Long vendorId = vendorMapper.getByMap(vendingMap).getId();
            vendingTrackMap.put("fkVendor", vendorId);
            vendingTrackMap.put("aisleNum", TrackCode);
            order.setVendingId(vendorId);
            VendorAisle vendorAisle = new VendorAisle();
            vendorAisle.setId(vendorAisleMapper.getByMap(vendingTrackMap).getId());
            vendorAisle.setStock(stockNum);
            if (isSuccess == 1) {
                vendorAisleMapper.update(vendorAisle);
            }

            order.setVendingAisleNum(TrackCode);
            orderMapper.insert(order);
            Map map = orderGoodsMapper.getGoodsInfoIdByNo(goodsInfoId);
            orderGoods.setOrderId(order.getOrderId());
            orderGoods.setGoodsInfoId(new Long(map.get("goods_info_id") + ""));
            orderGoods.setGoodsId(new Long(map.get("goods_id") + ""));
            orderGoods.setGoodsInfoNum(new Long(oldPrice.divide(price).toString()));
            orderGoods.setGoodsInfoOldPrice(oldPrice);
            orderGoods.setGoodsInfoPrice(price);
            orderGoods.setGoodsCouponPrice(oldPrice.subtract(price));
            orderGoods.setGoodsInfoSumPrice(prePrice);
            orderGoods.setBuyTime(new Date());
            orderGoodsMapper.insert(orderGoods);


            LOGGER.info("销售订单 vendingCode " + vendingCode + ", orderCode" + orderCode + ", TrackCode" + TrackCode + ", goodsInfoId" + goodsInfoId + ", oldPrice" + oldPrice + ", prePrice" + prePrice + ", rfidCode+" + rfidCode + ", isSuccess" + rfidCode + ", stockNum" + stockNum + ",  datetime" + datetime);
            return true;
        } catch (SQLException e) {
            LOGGER.info("销售订单 sql fail vendingCode " + vendingCode + ", orderCode" + orderCode + ", TrackCode" + TrackCode + ", goodsInfoId" + goodsInfoId + ", oldPrice" + oldPrice + ", prePrice" + prePrice + ", rfidCode+" + rfidCode + ", isSuccess" + rfidCode + ", stockNum" + stockNum + ",  datetime" + datetime);
//            e.printStackTrace();
            return false;
        }

    }
    @Transactional
    @Override
    public String insertPackageIntoVending(String vendingCode, String packageNo) {
        String mobile = "";
        try {
            LOGGER.info("送货 insertPackageIntoVending [vendingCode:" + vendingCode + ",packageNo:" + packageNo + "]");
            Random random = new Random();
            Map<String, Object> packageRecordMap = new ConcurrentHashMap<>();
            packageRecordMap.put("packageNo", packageNo);
            packageRecordMap.put("packageStatus", 1);
            PackageRecord packageRecord = packageRecordMapper.getByMap(packageRecordMap);

            if (null == packageRecord) {//包裹号不存在
                LOGGER.info("送货 packageNo:{0} is not exist", packageNo);
                return "NOPACKAGE";
            } else {//限制第二次送同一包裹10s以后
                Date indate = packageRecord.getPackageInTime();
                Calendar rightNow = Calendar.getInstance();
                rightNow.setTime(indate);
                rightNow.add(Calendar.SECOND, 10);
                Date dt1 = rightNow.getTime();
                if (new Date().compareTo(dt1) == -1) {
                    return "NOPACKAGE";
                }

            }

            Long canbinetCellId = packageRecord.getCanbinetCellId();//格子号
            /*
             第一步 判断是否已分配cell
             */
            if (null != canbinetCellId) {//已分配时,将之前的cell的status变为4：未知异常
                CanbinetCell canbinetCell = new CanbinetCell();
                canbinetCell.setCanbinetCellId(canbinetCellId);
                canbinetCell.setCellStatus("4");
                canbinetCellMapper.update(canbinetCell);
                //格子状态记录
                CanbinetCellLog canbinetCellLog = new CanbinetCellLog();
                canbinetCellLog.setCellId(canbinetCellId);
                canbinetCellLog.setType(Byte.valueOf("1"));
                canbinetCellLog.setPackageNo(packageNo);
                canbinetCellLogMapper.insert(canbinetCellLog);
            }
            /*
            第二步  分配空闲的cell（0）
             */
            Map<String, Object> vendingMap = new ConcurrentHashMap<>();//机器
            vendingMap.put("vendorNum", vendingCode);
            Map<String, Object> canbinetMap = new ConcurrentHashMap<>();//云柜
            canbinetMap.put("vendingId", vendorMapper.getByMap(vendingMap).getId());
            canbinetMap.put("cellStatus", "0");
            List<CanbinetCell> canbinetCells = canbinetCellMapper.getCellsByMap(canbinetMap);//得到status为0的cell
            if (0 == canbinetCells.size()) {
                LOGGER.info("送货 Here is not avaliable cell ");
                return "NOCELL";
            }
            CanbinetCell canbinetCell = canbinetCells.get(random.nextInt(canbinetCells.size()));//得到分配云柜cell
            /*
            第三步  设置密码
                 1.密码如果已经给了，不在生成；
                 2.如果没有，生成判断：
                       本云柜的其他已占用的cell密码不能重复
             */
            if (null == packageRecord.getPassword() || "".equals(packageRecord.getPassword())) {  //设置密码
                String password = Utils.createRandomVcode(8);
                canbinetMap.put("cellStatus", "1");
                List<CanbinetCell> canbinetCellsParam = canbinetCellMapper.getListWithPassWordByMap(canbinetMap);//得到status为1的cell  占用
                password = checkPackagePass(password, canbinetCellsParam);//验证 密码不重复

                packageRecord.setPassword(password);
            }
            /*
                 使cell的stauts变为1：占用
             */
            Long putCanbinetCellId = canbinetCell.getCanbinetCellId();
            CanbinetCell canbinetCellstatus = new CanbinetCell();
            canbinetCellstatus.setCanbinetCellId(putCanbinetCellId);
            canbinetCellstatus.setCellStatus("1");
            canbinetCellMapper.update(canbinetCellstatus);
            /*
            update  包裹的cellid   密码
             */
            packageRecord.setCanbinetCellId(putCanbinetCellId);
            packageRecord.setPackageInTime(new Date());
            packageRecord.setPackageStatus(1);
            packageRecordMapper.update(packageRecord);

            //格子状态记录
            CanbinetCellLog canbinetCellLog = new CanbinetCellLog();
            canbinetCellLog.setCellId(putCanbinetCellId);
            canbinetCellLog.setPackageNo(packageNo);
            canbinetCellLog.setType(Byte.valueOf("0"));
            canbinetCellLogMapper.insert(canbinetCellLog);
            //根据云柜格子
            Canbinet canbinet = canbinetMapper.getById(canbinetCell.getCanbinetId());
            String canbinetCode = canbinet.getCanbinetCode();
            BigDecimal code = new BigDecimal(canbinetCode.substring(canbinetCode.length() - 1)).subtract(new BigDecimal(1)).multiply(new BigDecimal(10)).add(new BigDecimal(canbinetCell.getCellCode()));
//修改订单状态
            Map<String, Object> orderMap = new ConcurrentHashMap<>();
            orderMap.put("orderCode", packageRecord.getOrderCode());
            Order order = orderMapper.getByMap(orderMap);
            order.setOrderStatus("3");
            orderMapper.update(order);
            /*
              发短信
             */
            mobile = packageRecordMapper.getCustomerMobileByPackageNo(packageRecord.getPackageNo());
            SmsPost.huaxSend(mobile, MessageFormat.format(SmsPost.MSG_SENDPACKAGE, code, packageRecord.getPassword()));
            return code.toString();
        } catch (SQLException e) {
            LOGGER.info("送货 sql mobile :" + mobile + "send fail");
//            e.printStackTrace();
        } catch (IOException ioe) {
            LOGGER.info("送货 e mobile :" + mobile + "send fail");
//            ioe.printStackTrace();
        }
        return null;
    }

    @Override
    public String updateVendingGetPackageOut(String vendingCode, String code) throws SQLException {
            LOGGER.info("云柜取货 vendingCode:" + vendingCode + ",code:" + code);
            Map<String, String> cellCode = packageRecordMapper.getCellCodeWithPassWordOrRfidCode(vendingCode, code);
            if (null == cellCode) {
                LOGGER.info("云柜取货 vendingCode:" + vendingCode + ",code:" + code + "  not exist");
                return null;
            }
            CanbinetCell canbinetCell = new CanbinetCell();
            canbinetCell.setCanbinetCellId(Long.valueOf(String.valueOf(cellCode.get("canbinet_cell_id"))));
            canbinetCell.setCellStatus("0");


            PackageRecord packageRecord = new PackageRecord();
            packageRecord.setId(new Long(String.valueOf(cellCode.get("id"))));
            packageRecord.setPackageStatus(4);
            packageRecord.setPackageOutTime(new Date());

            CanbinetCell canbinetCell1 = canbinetCellMapper.getById(canbinetCell.getCanbinetCellId());

            canbinetCellMapper.update(canbinetCell);
            packageRecordMapper.update(packageRecord);
            packageRecord=packageRecordMapper.getById(packageRecord.getId());
            //格子状态记录
            CanbinetCellLog canbinetCellLog = new CanbinetCellLog();
            canbinetCellLog.setCode(code);
            canbinetCellLog.setPackageNo(String.valueOf(cellCode.get("package_no")));
            canbinetCellLog.setCellId(canbinetCell.getCanbinetCellId());
            canbinetCellLog.setType(Byte.valueOf("2"));
            canbinetCellLog.setCustomeId(Long.valueOf(String.valueOf(cellCode.get("customer_id"))));
            canbinetCellLogMapper.insert(canbinetCellLog);
            //修改订单状态
            Map<String, Object> orderMap = new ConcurrentHashMap<>();
            orderMap.put("orderCode", packageRecord.getOrderCode());
            Order order = orderMapper.getByMap(orderMap);
            order.setOrderStatus("3");
            orderMapper.update(order);
            //根据云柜格子
            Canbinet canbinet = canbinetMapper.getById(canbinetCell1.getCanbinetId());
            String canbinetCode = canbinet.getCanbinetCode();
            BigDecimal codePara = new BigDecimal(canbinetCode.substring(canbinetCode.length() - 1)).subtract(new BigDecimal(1)).multiply(new BigDecimal(10)).add(new BigDecimal(String.valueOf(cellCode.get("cell_code"))));
            return codePara.toString();
//        } catch (SQLException sqle) {
////            sqle.printStackTrace();
//            LOGGER.info("fail sql 云柜取货 vendingCode:" + vendingCode + ",code:" + code);
//        } catch (Exception e) {
////            e.printStackTrace();
//            LOGGER.info("fail e 云柜取货 vendingCode:" + vendingCode + ",code:" + code);
//            LOGGER.error("e:", e);
//        }
    }

    /**
     * 上传温度
     *
     * @param vendingCode 云柜号
     * @param type        1=售卖机,2=云柜
     * @param temperature 设备温度℃
     * @return int 0: 关；1：开 2：不管
     */
    @Override
    public int insertDeviceTemerature(String vendingCode, int type, BigDecimal temperature) {
        int low = 6;
        int up = 10;
        try {
            DeviceTemperatureRecord deviceTemperatureRecord = new DeviceTemperatureRecord();
            Map<String, Object> map = new ConcurrentHashMap<>();
            map.put("vendorNum", vendingCode.substring(0, vendingCode.length() - 1));//售货机号
            Long vendingId = vendorMapper.getByMap(map).getId();
            if (1 == type) {//1=售卖机
                deviceTemperatureRecord.setDeviceId(vendingId);
            } else if (2 == type) {//2=云柜
                map.put("canbinetCode", vendingCode);
                deviceTemperatureRecord.setDeviceId(canbinetMapper.getByMap(map).getCanbinetId());
            }
            deviceTemperatureRecord.setDeviceType(type);
            deviceTemperatureRecord.setTemperature(temperature);
            deviceTemperatureRecord.setUpdateTime(new Date());
//            deviceTemperatureRecordMapper.insert(deviceTemperatureRecord);
            LOGGER.info("上传温度 vendingCode:" + vendingCode + ",  type," + type + "  temerature" + temperature);
        } catch (SQLException sqle) {
            LOGGER.info("fail sqle 上传温度 vendingCode:" + vendingCode + ",  type：" + type + "  temerature" + temperature);
//            sqle.printStackTrace();
        }
        int rs = 2;
        // 判断温度  开压缩机
        if (temperature.compareTo(new BigDecimal(low)) == -1) {//小于2度关压缩机
            rs = 0;
        } else if (temperature.compareTo(new BigDecimal(up)) == 1) {//大于8度  开压缩机
            rs = 1;
        }
        return rs;
    }

    @Override
    public PageInfo getOrderListByMap() {
        try {
            Map<String, Object> map = new ConcurrentHashMap<String, Object>();
            map.put("orderCode", "asd");
            PageHelper.startPage(1, 20); // 核心分页代码
            PageInfo page = new PageInfo(orderMapper.getListByMap(map));
            return page;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 密码判断是否重复
     */
    @Override
    public String checkPackagePass(String password, List<CanbinetCell> canbinetCells) {
        for (CanbinetCell canbinetCell1 : canbinetCells) {
            if (password.equals(canbinetCell1.getPassWord())) {
                password = Utils.createRandomVcode(8);
                return this.checkPackagePass(password, canbinetCells);
            }
        }
        return password;
    }


    @Override
    public VendingAdv getVendingAdvByScreenAndToken(Date tokenTime, String screenType) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        map.put("tokenTime", tokenTime);
        map.put("screenType", screenType);
        try {
            LOGGER.info("大小屏滚动信息 tokenTime:" + tokenTime + ",  screenType:" + screenType);
            return vendingAdvMapper.getByScreenAndToken(map);
        } catch (SQLException sqle) {
            LOGGER.info("fail sqle 大小屏滚动信息 tokenTime:" + tokenTime + ",  screenType:" + screenType);
//            sqle.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateAddStock(String vendingCode, Date operateTime, Long managerId, List<VendingUp> vendingUps) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        map.put("vendingCode", vendingCode);
        map.put("list", vendingUps);
        try {

            int num = vendingUpMapper.insertList(map);
            int num1 = vendingUpMapper.updateAddStockList(map);
            LOGGER.info("补货信息 operateTime:" + operateTime + ",  managerId:" + managerId + ",vendingUps:" + JSON.toJSONString(vendingUps));
            return num > 0 && num1 > 0;
        } catch (SQLException sqle) {
            LOGGER.info("fail sqle 补货信息 operateTime:" + operateTime + ",  managerId:" + managerId + ",vendingUps:" + vendingUps);
            return false;
        }

    }
}

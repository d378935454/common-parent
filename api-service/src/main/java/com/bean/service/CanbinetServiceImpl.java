package com.bean.service;

import com.bean.dao.*;
import com.bean.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.MyLogger;
import utils.SmsPost;
import utils.Utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by bean on 2016/6/22.
 */
@Service("canbinetService")
public class CanbinetServiceImpl implements CanbinetService {
    private MyLogger LOGGER = new MyLogger(CanbinetServiceImpl.class);
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
    @Autowired
    private VendingService vendingService;
    @Autowired
    private CanbinetCellLogMapper canbinetCellLogMapper;
    @Override
    public CanbinetManager getCabinetManagerByRfidAndCanbinetId(String rfidCode, String boxCode)throws Exception {
        Map<String, Object> map = new ConcurrentHashMap<>();
        map.put("rfidCode", rfidCode);
        map.put("canbinetCode", boxCode);
        CanbinetManager canbinetManager = null;
            LOGGER.info(" 得到云柜管理员 rfidCode:" + rfidCode + ", boxId;" + boxCode);
            canbinetManager = canbinetManagerMapper.getCabinetManagerByRfidAndCanbinetId(map);
            if (null != canbinetManager) {//记录管理员登陆
                Map<String, Object> map1 = new ConcurrentHashMap<>();
                map1.put("canbinetCode", boxCode);
                CanbinetManagerRecord canbinetManagerRecord = new CanbinetManagerRecord();
                canbinetManagerRecord.setManagerId(canbinetManager.getId());
                canbinetManagerRecord.setCanbinetId(canbinetMapper.getByMap(map1).getCanbinetId());

                canbinetManagerRecord.setTime(new Date());
                canbinetManagerRecordMapper.insert(canbinetManagerRecord);
            }

        return canbinetManager;
    }

    @Override
    public PackageRecord insertPackageIntoCabinet(String canbinet, String packageNo, String cellCode) throws Exception {
        String mobile = "";
            LOGGER.info("送货 insertPackageIntoCabinet [canbinet:" + canbinet + ",packageNo:" + packageNo + ",cellCode:" + cellCode);
            Map<String, Object> packageRecordMap = new ConcurrentHashMap<>();
            packageRecordMap.put("packageNo", packageNo);
            packageRecordMap.put("packageStatus", 1);
            PackageRecord packageRecord = packageRecordMapper.getByMap(packageRecordMap);
            if (null == packageRecord) {//包裹号不存在
                LOGGER.info("送货 packageNo is not exist");
//                packageRecord = new PackageRecord();
//                packageRecord.setOrderCode(packageRecordMapper.getOrderCodeByPackageNo(packageNo));
                return packageRecord;
            }
//            else {//限制第二次送同一包裹10s以后
//                Date indate = packageRecord.getPackageInTime();
//                Calendar rightNow = Calendar.getInstance();
//                rightNow.setTime(indate);
//                rightNow.add(Calendar.SECOND, 10);
//                Date dt1 = rightNow.getTime();
//                if (new Date().compareTo(dt1) == -1) {
//                    return null;
//                }
//
//            }

            if (!"FF".equals(cellCode)) {//爆柜跳过

                cellCode=String.valueOf(Integer.valueOf(cellCode));

            /*
            第二步  分配空闲的cell（0）
             */
                Map<String, Object> canbinetMap = new ConcurrentHashMap<>();//云柜
                canbinetMap.put("canbinetCodeRoot", canbinet);
                canbinetMap.put("cellCode", cellCode);
                CanbinetCell canbinetCell = canbinetCellMapper.getCellsBycellCodeAndCanbinet(canbinetMap);//得到分配云柜cell

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
                    password = vendingService.checkPackagePass(password, canbinetCellsParam);//验证 密码不重复

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
                Rfid16to10(packageRecord.getRfid());
                //修改订单状态
                Map<String, Object> orderMap = new ConcurrentHashMap<>();
                orderMap.put("orderCode", packageRecord.getOrderCode());
                Order order = orderMapper.getByMap(orderMap);
                order.setOrderStatus("17");
                orderMapper.update(order);
            /*
              发短信
             */
                int l = canbinet.length();
                String yunguiCode = canbinet.substring(l - 1, l);
                cellCode = (Objects.equals(yunguiCode, "1") ? "A" : "B") + cellCode;
                mobile = packageRecordMapper.getCustomerMobileByPackageNo(packageRecord.getPackageNo());
                SmsPost.huaxSend(mobile, MessageFormat.format(SmsPost.MSG_SENDPACKAGE, cellCode, packageRecord.getPassword()));
                return packageRecord;
            }

        return null;
    }


    @Override
    public void updatePackage(String boxId, String packageNo, int type) throws Exception{
        Map<String, Object> packageRecordMap = new ConcurrentHashMap<>();
        packageRecordMap.put("packageNo", packageNo);
            PackageRecord packageRecord = packageRecordMapper.getByMap(packageRecordMap);
            packageRecord.setPackageStatus(4);
            packageRecord.setPackageOutTime(new Date());
            packageRecordMapper.update(packageRecord);

            CanbinetCell canbinetCell = canbinetCellMapper.getById(packageRecord.getCanbinetCellId());
            canbinetCell.setCellStatus("0");
            canbinetCellMapper.update(canbinetCell);
            //格子状态记录
            CanbinetCellLog canbinetCellLog = new CanbinetCellLog();
            canbinetCellLog.setCode(String.valueOf(type));
            canbinetCellLog.setPackageNo(packageNo);
            canbinetCellLog.setCellId(packageRecord.getCanbinetCellId());
            canbinetCellLog.setType(Byte.valueOf("2"));
            canbinetCellLog.setCustomeId(0L);
            canbinetCellLogMapper.insert(canbinetCellLog);
            Map<String, Object> orderMap = new ConcurrentHashMap<>();
            orderMap.put("orderCode", packageRecord.getOrderCode());
            Order order = orderMapper.getByMap(orderMap);
            order.setOrderStatus("3");
            orderMapper.update(order);


    }

    /**
     * 第一次连接时，将boxid与channel的key关联 在数据库中
     *
     * @param key
     * @param boxId
     */
    @Override
    public void updateVendorSocketKey(String key, String boxId) throws Exception{
        Map<String, Object> vendormap = new ConcurrentHashMap<>();
        vendormap.put("canbinetCodeRoot", boxId);
            LOGGER.info("将boxid:{0}与channel的key:{1}关联", boxId, key);
            Canbinet canbinet = canbinetMapper.getByMap(vendormap);
            canbinet.setSocketKey(key);
            canbinetMapper.update(canbinet);

    }

    /**
     * 云柜上传温度
     *
     * @param
     */
    @Override
    public void insertTemperature(String boxId,Byte temperature)throws Exception {
        DeviceTemperatureRecord deviceTemperatureRecord = new DeviceTemperatureRecord();
        Map<String, Object> map = new ConcurrentHashMap<>();


            map.put("canbinetCodeRoot", boxId);//售货机号
            deviceTemperatureRecord.setDeviceId(canbinetMapper.getByMap(map).getCanbinetId());
            deviceTemperatureRecord.setDeviceType(2);
            deviceTemperatureRecord.setTemperature(new BigDecimal(temperature));
            deviceTemperatureRecord.setUpdateTime(new Date());
            deviceTemperatureRecordMapper.insert(deviceTemperatureRecord);

    }



    /**
     * 出來rfid為16进制   把它转成正常
     * 列 C9391C00  C9391C   1C39C9  0001849801
     */
    public  String Rfid16to10(String Rfid)throws Exception{
        int l=Rfid.length();
        String rs=Rfid.substring(4,6)+Rfid.substring(2,4)+Rfid.substring(0,2);
        rs=String.valueOf(Integer.parseInt(rs, 16));
        while (true){
            if(rs.length()<10){
                rs="0"+rs;
            }else {
                break;
            }

        }
        return rs;
    }
}

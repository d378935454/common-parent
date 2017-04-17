package com.bean.controller;

import com.bean.model.CustomerCard;
import com.bean.model.Vending;
import com.bean.model.VendingAdv;
import com.bean.model.VendingUp;
import com.bean.service.CardRechargeService;
import com.bean.service.CustomerCardService;
import com.bean.service.VendingManagerService;
import com.bean.service.VendingService;
import utils.MyLogger;
import utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by bean on 2016/6/27.
 */
@Controller
public class ZYController {

    /**
     * 日志记录
     **/
    private static final MyLogger LOGGER = new MyLogger(TestController.class);
    @Autowired
    private VendingService vendingService;
    @Autowired
    private VendingManagerService vendingManagerService;
    @Autowired
    private CardRechargeService cardRechargeService;
    @Autowired
    private CustomerCardService customerCardService;

    @RequestMapping(value = "/ZY.htmls")
    public void ZYApi(HttpServletRequest request,
                      HttpServletResponse response,
                      @RequestParam(value = "vimid", required = false) String vmid) throws IOException {
        String base64 = "";
        response.setContentType("text/html");
        java.io.BufferedReader reader;
        reader = request.getReader();
        StringBuffer content = new StringBuffer();
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line);
        }
        base64 = content.toString();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        List<String> data = null;
        try {
            Map<String, String> map = Utils.array2Map(base64);
            String[] type = map.get("type").split(",");
            String[] dataArr = map.get("data").split(",");
            LOGGER.debug(dataArr.toString());
            byte[] bytes = new byte[1];
            switch (type[1]) {
                /**
                 * 终端协议 0x01 签到
                 * 数组第2位为机器码
                 */
                case "1":
                    switch (type[2]) {
                        /**
                         *  终端协议 0x01 测试是否联网
                         */
                        case "1":
                            switch (type[3]) {
                                /**
                                 * 终端协议 0x01 测试是否联网
                                 */
                                case "1":
                                    data = new ArrayList<>();
                                    data.add((char) 0x10 + "");
                                    data.add((char) 0x02 + "");
                                    data.add((char) 0x01 + "");
                                    Utils.sos(Utils.Bill(data), response);
                                    LOGGER.info("终端联网测试 ，vmid：" + vmid + ",dataArr[0]:" + dataArr[0]);
                                    break;
                            }
                            break;
                    }
                    break;
                /**
                 * 终端协议 0x02 日常信息
                 */
                case "2":
                    switch (type[2]) {
                        /**
                         * 终端协议 0x01 咨询机器数据更新
                         */
                        case "1":
                            switch (type[3]) {
                                /**
                                 * 终端协议 0x01 商品管理
                                 * data 机器最新更新时间(14位)
                                 */
                                case "1":
                                    break;
                                /**
                                 * 终端协议 0x02 机器管理
                                 * data 账号,密码
                                 */
                                case "2":
                                    int resultManager = vendingManagerService.validateManager(dataArr[0], dataArr[1], vmid);

                                    data = new ArrayList<>();
                                    data.add((char) 0x10 + "");
                                    data.add((char) 0x03 + "");
                                    data.add((char) 0x02 + "");
                                    data.add((char) 0x01 + "");
                                    if (resultManager != 0) {
                                        data.add((char) 0x02 + "");
                                    } else {
                                        data.add((char) 0x01 + "");
                                    }
                                    Utils.sos(Utils.Bill(data), response);
                                    break;
                                /**
                                 * 终端协议 0x03 广告管理
                                 * data 机器最新更新时间(14位)
                                 */
                                case "3":
                                    break;
                                /**
                                 * 终端协议 0x04 游戏管理
                                 * data 机器最新更新时间(14位)
                                 */
                                case "4":
                                    break;
                                /**
                                 * 终端协议 0x05 系统更新
                                 * data     无
                                 */
                                case "5":
                                    break;
                                /**
                                 * 终端协议 0x06 支付宝
                                 * data     无
                                 */
                                case "6":
                                    break;
                                /**
                                 * 终端协议 0x07 银联
                                 * data     无
                                 */
                                case "7":
                                    break;
                                /**
                                 * 终端协议 0x08 大屏信息(停用)
                                 * data     无
                                 */
                                case "8":
                                    break;
                                /**
                                 * 终端协议 0x09 大小屏滚动信息
                                 * data 机器最新更新时间(14位),屏幕类型
                                 */
                                case "9":
                                    data = new ArrayList<>();
                                    VendingAdv vendingAdv = vendingService.getVendingAdvByScreenAndToken(sdf.parse(dataArr[0].substring(0, 14)), dataArr[0].substring(14, 15));
                                    data.add((char) 0x10 + "");
                                    if (null == vendingAdv) {//无更新信息
                                        data.add((char) 0x01 + "");
                                        data.add((char) 0x04 + "");
                                        data.add((char) 0x09 + "");
                                        data.add((char) 0x02 + "");
                                    } else {//提取信息成功
                                        data.add((char) 0x03 + "");
                                        data.add((char) 0x0a + "");
                                        data.add((char) 0x01 + "");
                                        data.add(sdf.format(vendingAdv.getTokenTime()));//最新时间
                                        data.add(vendingAdv.getScreenType().toString().length() + "");//类型
                                        data.add(vendingAdv.getScreenType().toString());
                                        bytes[0] = (byte) vendingAdv.getId().toString().length();//ID
                                        data.add(new String(bytes));
                                        data.add(vendingAdv.getId().toString());
                                        bytes[0] = (byte) vendingAdv.getConment().length();//内容
                                        data.add(new String(bytes));
                                        data.add(vendingAdv.getConment());
                                    }
                                    Utils.sos(Utils.Bill(data), response);
                                    break;
                                /**
                                 * 终端协议 0x0a 支付设置
                                 * data 机器最新更新时间(14位)
                                 */
                                case "10":
                                    data = new ArrayList<>();
                                    String payType = "card";
                                    String payTypeId = "5";
                                    char a0 = (char) 0x10;
                                    char a1 = (char) 0x03;
                                    char a2 = (char) 0x0a;
                                    char a3 = (char) 0x01;
                                    data.add(a0 + "");
                                    data.add(a1 + "");
                                    data.add(a2 + "");
                                    data.add(a3 + "");
                                    //data.add(sdf.format(new Date()));
                                    bytes[0] = (byte) (payType.length());
                                    data.add(new String(bytes));
                                    data.add(payType);
                                    data.add("1");
                                    data.add(payTypeId);
                                    Utils.sos(Utils.Bill(data), response);
                                    break;
                                /**
                                 * 终端协议 0x0b 非现金设置
                                 * data 机器最新更新时间(14位)
                                 */
                                case "11":
                                    break;
                                /**
                                 * 终端协议 0x0c 联系我们图片
                                 * data 机器最新更新时间(14位)
                                 */
                                case "12":
                                    break;
                                /**
                                 * 终端协议 0x0d 自动出货
                                 * data 机器最新更新时间(14位)
                                 */
                                case "13":
                                    break;
                                /**
                                 * 终端协议 0x0e 取货码
                                 * data 取货码
                                 */
                                case "14":
                                    break;
                                /**
                                 * 终端协议 0x0f 商品管理_20150730
                                 * data 机器最新更新时间(14位)
                                 */
                                case "15":
                                    Date datetime = sdf.parse(dataArr[0]);
                                    List<Vending> vendings = vendingService.getGoodsManageList(vmid);
                                    data = new ArrayList<>();
                                    if (vendings != null && vendings.get(0).getUpdateTime().compareTo(datetime) == 1) {
                                        data.add((char) 0x10 + "");
                                        data.add((char) 0x03 + "");
                                        data.add((char) 0x10 + "");
                                        data.add((char) 0x01 + "");
                                        data.add(sdf.format(new Date()));
                                        for (Vending vending : vendings) {
                                            bytes[0] = (byte) (vending.getTrackCode().length());
                                            data.add(new String(bytes));
                                            data.add(vending.getTrackCode());
                                            bytes[0] = (byte) (vending.getProductId().length());
                                            data.add(new String(bytes));
                                            data.add(vending.getProductId());
                                            bytes[0] = (byte) ("0".length());
                                            data.add(new String(bytes));
                                            data.add("0");
                                            bytes[0] = (byte) (vending.getGoodsInfoName().getBytes(Utils.utf8).length);
                                            data.add(new String(bytes));
                                            data.add(vending.getGoodsInfoName());
                                            bytes[0] = (byte) (vending.getSpecValueRemark().getBytes(Utils.utf8).length);
                                            data.add(new String(bytes));
                                            data.add(vending.getSpecValueRemark());
                                            bytes[0] = (byte) ((vending.getGoodsInfoPreferPrice().multiply(new BigDecimal(100)).longValue()+"").length());//乘以100变为分
                                            data.add(new String(bytes));
                                            data.add(vending.getGoodsInfoPreferPrice().multiply(new BigDecimal(100)).longValue()+"");
                                            bytes[0] = (byte) (vending.getGoodsInfoImgId().length());
                                            data.add(new String(bytes));
                                            data.add(vending.getGoodsInfoImgId());
                                            bytes[0] = (byte) ("0".length());
                                            data.add(new String(bytes));
                                            data.add("0");
                                            bytes[0] = (byte) ("0".length());
                                            data.add(new String(bytes));
                                            data.add("0");
                                            bytes[0] = (byte) ("0".length());
                                            data.add(new String(bytes));
                                            data.add("0");
                                            bytes[0] = (byte) (vending.getProductNum().toString().length());
                                            data.add(new String(bytes));
                                            data.add(vending.getProductNum().toString());
                                            bytes[0] = (byte) ("0".length());
                                            data.add(new String(bytes));
                                            data.add("0");
                                            bytes[0] = (byte) ("0".length());
                                            data.add(new String(bytes));
                                            data.add("0");
                                            bytes[0] = (byte) ("0".length());
                                            data.add(new String(bytes));
                                            data.add("0");
                                            bytes[0] = (byte) ("0".length());
                                            data.add(new String(bytes));
                                            data.add("0");
                                        }
                                    } else {
                                        data.add((char) 0x10 + "");
                                        data.add((char) 0x01 + "");
                                        data.add((char) 0x04 + "");
                                        data.add((char) 0x01 + "");
                                        data.add((char) 0x02 + "");
                                    }
                                    Utils.sos(Utils.Bill(data), response);
                                    break;
                                /**
                                 * 终端协议 0x10 继电控制
                                 */
                                case "16":
                                    String paras=dataArr[0].substring(1);
                                    Long parl= Long.valueOf(paras);
                                    BigDecimal para=BigDecimal.valueOf(parl);
                                    int rsTemper = vendingService.insertDeviceTemerature(vmid, 2,para );
                                    data = new ArrayList<>();
                                    data.add((char) 0x10 + "");
                                    data.add((char) 0x03 + "");
                                    data.add((char) 0x12 + "");
                                    data.add((char) 0x01 + "");
                                    data.add(vmid.substring(vmid.length() - 1));
                                    data.add(rsTemper+"");
                                    Utils.sos(Utils.Bill(data), response);
                                    break;
                            }
                            break;
                        /**
                         * 终端协议 0x02 更新平台信息1
                         */
                        case "2":
                            switch (type[3]) {
                                /**
                                 * 终端协议 0x01 商品营收
                                 * data 暂时不用
                                 */
                                case "1":
                                    break;
                                /**
                                 * 终端协议 0x02 游戏营收
                                 * data 暂时不用
                                 */
                                case "2":
                                    break;
                                /**
                                 * 终端协议 0x03 广告营收
                                 * data 暂时不用
                                 */
                                case "3":
                                    break;
                                /**
                                 * 终端协议 0x04 钱币管理
                                 * data 硬币种类1(字符串),硬币种类2(字符串),…硬币种类16(字符串),时间
                                 */
                                case "4":
                                    data = new ArrayList<>();
                                    data.add((char) 0x10 + "");
                                    data.add((char) 0x02 + "");
                                    data.add((char) 0x04 + "");
                                    Utils.sos(Utils.Bill(data), response);
                                    break;
                                /**
                                 * 终端协议 0x05 商品管理
                                 * data 货道,商品ID,商品头名,商品脚名,单价,库存,时间,页码ID,页码
                                 */
                                case "5":
                                    Date datetime = sdf.parse(dataArr[0].substring(1));
                                    data = new ArrayList<>();
                                    List<Vending> vendings = vendingService.getGoodsManageList(vmid);
                                    if (vendings != null && vendings.get(0).getUpdateTime().compareTo(datetime) == 1) {
                                        data.add((char) 0x10 + "");
                                        data.add((char) 0x02 + "");
                                        data.add((char) 0x05 + "");
                                        for (Vending vending : vendings) {
                                            data.add(sdf.format(new Date()));
                                            bytes[0] = (byte) (vending.getTrackCode().length());
                                            data.add(new String(bytes));
                                            data.add(vending.getTrackCode());
                                            bytes[0] = (byte) (vending.getProductId().toString().length());
                                            data.add(new String(bytes));
                                            data.add(vending.getProductId().toString());
                                            bytes[0] = (byte) ("0".length());
                                            data.add(new String(bytes));
                                            data.add("0");
                                            bytes[0] = (byte) (vending.getGoodsInfoName().length());
                                            data.add(new String(bytes));
                                            data.add(vending.getGoodsInfoName());
                                            bytes[0] = (byte) (vending.getSpecValueRemark().length());
                                            data.add(new String(bytes));
                                            data.add(vending.getSpecValueRemark());
                                            bytes[0] = (byte) (vending.getGoodsInfoPreferPrice().multiply(new BigDecimal(100)).toString().length());//乘以100变为分
                                            data.add(new String(bytes));
                                            data.add(vending.getGoodsInfoPreferPrice().multiply(new BigDecimal(100)).toString());
                                            bytes[0] = (byte) (vending.getGoodsInfoImgId().length());
                                            data.add(new String(bytes));
                                            data.add(vending.getGoodsInfoImgId());
                                            data.add("0".length() + "");
                                            data.add("0");
                                            data.add("0".length() + "");
                                            data.add("0");
                                            data.add("0".length() + "");
                                            data.add("0");
                                            bytes[0] = (byte) (vending.getProductNum().toString().length());
                                            data.add(new String(bytes));
                                            data.add(vending.getProductNum().toString());
                                            data.add("0".length() + "");
                                            data.add("0");
                                            data.add("0".length() + "");
                                            data.add("0");
                                            data.add("0".length() + "");
                                            data.add("0");
                                            data.add("0".length() + "");
                                            data.add("0");
                                        }
                                    } else {
                                        data.add((char) 0x10 + "");
                                        data.add((char) 0x01 + "");
                                        data.add((char) 0x04 + "");
                                        data.add((char) 0x01 + "");
                                        data.add((char) 0x02 + "");
                                    }
                                    Utils.sos(Utils.Bill(data), response);
                                    break;
                                /**
                                 * 终端协议 0x06 机器状态
                                 * data 暂时不用
                                 */
                                case "6":
                                    break;
                                /**
                                 * 终端协议 0x07 销售账单
                                 * data 已不用
                                 */
                                case "7":
                                    break;
                                /**
                                 * 终端协议 0x08 补货记录
                                 * data 序号,开柜时间,关柜时间,操作时间,补货员编号,货道及数量(货道+库存)
                                 */
                                case "8":
                                    data = new ArrayList<>();
                                    data.add((char) 0x10 + "");
                                    LOGGER.info("补货记录");
                                    String vendingup = dataArr[5];
                                    Date date = sdf.parse(dataArr[2]);
                                    List<VendingUp> vendingUps = new ArrayList<>();
                                    int l = vendingup.length();
                                    for (int i = 0; i < l; i += 4) {
                                        VendingUp vendingUp = new VendingUp();
                                        vendingUp.setOperatorDate(date);
                                        vendingUp.setTrackCode(vendingup.substring(i, i + 2));
                                        vendingUp.setProductNum(new Long(vendingup.substring(i + 2, i + 4)));
                                        vendingUps.add(vendingUp);
                                    }
                                    boolean AddStockrs = vendingService.updateAddStock(vmid, date, 1L, vendingUps);
                                    if (AddStockrs) {//上传补货记录成功
                                        data.add((char) 0x02 + "");
                                        data.add((char) 0x09 + "");
                                    } else {//上传补货记录失败
                                        data.add((char) 0x01 + "");
                                        data.add((char) 0x03 + "");
                                        data.add((char) 0x0a + "");
                                    }
                                    Utils.sos(Utils.Bill(data), response);
                                    break;
                                /**
                                 * 终端协议 0x09 货道更新
                                 * data 货道|容量,货道|容量,货道|容量...货道|容量(全部货道)
                                 */
                                case "9":
                                    break;
                                /**
                                 * 终端协议 0x0a 销售账单_20140403
                                 * data 已不用
                                 */
                                case "10":
                                    break;
                                /**
                                 * 终端协议 0x0b 销售账单_20140408
                                 * data 自定义订单序号(3个字幕+3个数字),自定义订单编号(14位时间+4个随机数),货道,商品编号,
                                 * 单价,应收,支付方式 ,实收,会员卡号,出货状态,剩余库存,交易时间
                                 */
                                case "11":
                                    boolean rs = false;
                                    try {
                                        rs = vendingService.insertOrder(vmid,
                                                dataArr[1],
                                                dataArr[2],
                                                Long.valueOf(dataArr[3]),
                                                new BigDecimal(dataArr[4]).divide(new BigDecimal(100)),
                                                new BigDecimal(dataArr[5]).divide(new BigDecimal(100)),
                                                new BigDecimal(dataArr[7]).divide(new BigDecimal(100)),
                                                dataArr[8],
                                                Integer.valueOf(dataArr[9]),
                                                Integer.valueOf(dataArr[10]),
                                                sdf.parse(dataArr[11]));

                                    } catch (ParseException e) {
                                        LOGGER.info("销售账单_20140408  fail datetime :" + dataArr[10]);
                                        e.printStackTrace();
                                    }
                                    data = new ArrayList<>();
                                    data.add((char) 0x10 + "");
                                    if (rs) {//上传销售订单成功
                                        data.add((char) 0x02 + "");
                                        data.add((char) 0x0c + "");
                                        List<CustomerCard> customerCard = customerCardService.getCustomerCard(dataArr[8]);
                                        if (customerCard.size() > 0) {
                                            for (CustomerCard cc : customerCard) {//返回余额分
                                                bytes[0] = (byte) (Long.toString(cc.getCardBalance().multiply(new BigDecimal(100)).longValue()).length());
                                                data.add(new String(bytes));
                                                data.add(Long.toString(cc.getCardBalance().multiply(new BigDecimal(100)).longValue()));
                                            }
                                        }
                                    } else {//上传订单失败
                                        data.add((char) 0x01 + "");
                                        data.add((char) 0x03 + "");
                                        data.add((char) 0x0d + "");
                                    }
                                    Utils.sos(Utils.Bill(data), response);
                                    break;
                                /**
                                 * 终端协议 0x0c 机器故障
                                 * data     无
                                 */
                                case "12":
                                    break;
                                /**
                                 * 终端协议 0x0d 云柜格子状态
                                 * data 0空闲 1占用
                                 */
                                case "13":
                                    break;
                                /**
                                 * 终端协议 0x0c 云柜温度
                                 * data
                                 */
                                case "14":

                                    break;
                            }
                            break;
                    }
                    break;
                /**
                 * 终端协议 0x03 下载文件
                 */
                case "3":
                    break;
                /**
                 * 终端协议 0x04 读卡器
                 */
                case "4":
                    switch (type[2]) {
                        /**
                         * 终端协议 0x01 充值
                         */
                        case "1":
                            switch (type[3]) {
                                /**
                                 * 终端协议 0x01 充值
                                 * data 充值金额、卡号
                                 */
                                case "1":
                                    List<CustomerCard> customerCard = customerCardService.getCustomerCard(dataArr[1]);
                                    data = new ArrayList<>();
                                    data.add((char) 0x10 + "");
                                    data.add((char) 0x04 + "");
                                    data.add((char) 0x01 + "");
                                    if (customerCard.size() > 0) {
                                        int rechargeRes = cardRechargeService.cardRecharge(new BigDecimal(dataArr[0]).divide(new BigDecimal(100)), dataArr[1], "3", sdf.format(new Date()), "0", dataArr[2], vmid);

                                        if (rechargeRes > 0) {
                                            data.add((char) 0x01 + "");
                                        } else {
                                            data.add((char) 0x02 + "");
                                        }

                                        for (CustomerCard cc : customerCard) {
                                            bytes[0] = (byte) cc.getRfidCode().length();
                                            data.add(new String(bytes));
                                            data.add(cc.getRfidCode());
                                            bytes[0] = (byte) (Long.toString(cc.getCardBalance().multiply(new BigDecimal(100)).longValue()).length());
                                            data.add(new String(bytes));
                                            data.add(Long.toString(cc.getCardBalance().multiply(new BigDecimal(100)).longValue()));
                                        }
                                    } else {
                                        data.add((char) 0x03 + "");
                                    }


                                    Utils.sos(Utils.Bill(data), response);
                                    break;
                            }
                            break;
                        /**
                         * 终端协议 0x02 购买扣款
                         */
                        case "2":
                            switch (type[3]) {
                                /**
                                 * 终端协议 0x02 购买扣款
                                 * data 商品金额、卡号
                                 */
                                case "2":
                                    List<CustomerCard> customerCard = customerCardService.getCustomerCard(dataArr[0]);
                                    data = new ArrayList<>();
                                    data.add((char) 0x10 + "");
                                    data.add((char) 0x04 + "");
                                    data.add((char) 0x02 + "");
                                    if (customerCard.size() > 0) {//卡号存在
                                        if (Integer.parseInt(customerCard.get(0).getDelFlag()) == 0 && Integer.parseInt(customerCard.get(0).getCardStatus()) == 0) {//正常状态
                                            if (customerCard.get(0).getCardBalance().compareTo(new BigDecimal(dataArr[2]).divide(new BigDecimal(100))) >= 0) {//余额充足
                                                data.add((char) 0x01 + "");
                                            } else {//余额不足
                                                data.add((char) 0x02 + "");
                                            }
//                                            //返回卡号和余额
//                                            for (CustomerCard cc : customerCard) {
//                                                bytes[0]=(byte)cc.getRfidCode().length();
//                                                data.add(new String(bytes));
//                                                data.add(cc.getRfidCode());
//                                                bytes[0]=(byte)(Long.toString(cc.getCardBalance().multiply(new BigDecimal(100)).longValue()).length());
//                                                data.add(new String(bytes));
//                                                data.add(Long.toString(cc.getCardBalance().multiply(new BigDecimal(100)).longValue()));
//                                            }
                                        } else {//不正常状态
                                            data.add((char) 0x03 + "");
                                        }
                                    } else {//卡号不存在
                                        data.add((char) 0x04 + "");
                                    }
                                    Utils.sos(Utils.Bill(data), response);
                                    break;
                            }
                            /**
                             * 终端协议 0x03 会员开卡
                             *
                             */
                        case "3":
                            switch (type[3]) {
                                /**
                                 * 终端协议 0x03 会员开卡
                                 * data 卡号、手机号码、验证码
                                 */
                                case "3":
                                    //TODO
                                    String rs = customerCardService.getCheckCustomerCard(dataArr[0], dataArr[1],vmid);
                                    data = new ArrayList<>();
                                    data.add((char) 0x10 + "");
                                    data.add((char) 0x06 + "");
                                    data.add((char) 0x01 + "");
                                    switch (rs) {
                                        case "e"://短信失败
                                            data.add((char) 0x05 + "");
                                            break;
                                        case "mobile"://电话号码已使用
                                            data.add((char) 0x02 + "");
                                            break;
                                        case "rfidCode"://物理卡号已使用
                                            data.add((char) 0x03 + "");
                                            break;
                                        case "mobileError"://电话号码格式不正确
                                            data.add((char) 0x04 + "");
                                            break;
                                        case "norfidCode"://没有该卡
                                            data.add((char) 0x05 + "");
                                            break;
                                        default://发送成功，返回验证码
                                            data.add((char) 0x01 + "");
                                            bytes[0] = (byte) (rs.length());
                                            data.add(new String(bytes));
                                            data.add(rs);
                                    }
                                    break;
                            }
                            break;
                        /**
                         * 终端协议 0x04 会员卡余额查询
                         *
                         */
                        case "4":
                            switch (type[3]) {
                                /**
                                 * 终端协议 0x04 会员卡余额查询
                                 * data 卡号
                                 */
                                case "4":
                                    List<CustomerCard> customerCard = customerCardService.getCustomerCard(dataArr[0]);
                                    data = new ArrayList<>();
                                    data.add((char) 0x10 + "");
                                    data.add((char) 0x04 + "");
                                    data.add((char) 0x04 + "");
                                    if (customerCard.size() > 0) {//卡号存在
                                        if (Integer.parseInt(customerCard.get(0).getDelFlag()) == 0 && Integer.parseInt(customerCard.get(0).getCardStatus()) == 0) {//正常状态
                                            data.add((char) 0x01 + "");
//                                            //返回卡号和余额
                                            for (CustomerCard cc : customerCard) {
                                                bytes[0] = (byte) cc.getRfidCode().length();
                                                data.add(new String(bytes));
                                                data.add(cc.getRfidCode());
                                                bytes[0] = (byte) (Long.toString(cc.getCardBalance().multiply(new BigDecimal(100)).longValue()).length());
                                                data.add(new String(bytes));
                                                data.add(Long.toString(cc.getCardBalance().multiply(new BigDecimal(100)).longValue()));
                                            }
                                        } else {//不正常状态
                                            data.add((char) 0x02 + "");
                                        }
                                    } else {//卡号不存在
                                        data.add((char) 0x03 + "");
                                    }
                                    Utils.sos(Utils.Bill(data), response);
                                    break;
                            }
                            break;
                    }

                    break;
                /**
                 * 终端协议 0x05
                 */
                case "5":
                    switch (type[2]) {
                        /**
                         * 终端协议 0x01 云柜取货
                         */
                        case "1":
                            switch (type[3]) {
                                /**
                                 * 终端协议 0x01 云柜取货
                                 * data 卡号或取货密码
                                 */
                                case "1":
                                    data = new ArrayList<>();
                                    data.add((char) 0x10 + "");
                                    data.add((char) 0x05 + "");
                                    data.add((char) 0x01 + "");
                                    String rs = vendingService.updateVendingGetPackageOut(vmid.substring(0,4), dataArr[0]);
                                    if (null != rs) {//成功取货返回柜号
                                        data.add((char) 0x01 + "");
                                        bytes[0] = (byte) (rs.length());
                                        data.add(new String(bytes));
                                        data.add(rs);
                                    } else {//无货物
                                        data.add((char) 0x02 + "");
                                    }
                                    Utils.sos(Utils.Bill(data), response);
                                    break;
                            }
                            break;
                        /**
                         * 终端协议 0x02 云柜送货
                         */
                        case "2":
                            switch (type[3]) {
                                /**
                                 * 终端协议 0x01 云柜送货
                                 * data 包裹号
                                 */
                                case "1":
                                    data = new ArrayList<>();
                                    data.add((char) 0x10 + "");
                                    data.add((char) 0x05 + "");
                                    data.add((char) 0x02 + "");
                                    String rs = vendingService.insertPackageIntoVending(vmid, dataArr[0]);
                                    switch (rs) {
                                        case "NOPACKAGE":
                                            data.add((char) 0x02 + "");
                                            break;
                                        case "NOCELL":
                                            data.add((char) 0x03 + "");
                                            break;
                                        default://成功送货 返回柜号
                                            data.add((char) 0x01 + "");
                                            bytes[0] = (byte) (rs.length());
                                            data.add(new String(bytes));
                                            data.add(rs);
                                    }
                                    Utils.sos(Utils.Bill(data), response);
                                    break;
                            }
                            break;
                    }
                    break;
                /**
                 * 终端协议 0x06 验证码，卡号验证
                 */
                case "6":
                    switch (type[2]) {
                        /**
                         * 终端协议 0x01 验证码，卡号验证
                         */
                        case "1":
                            switch (type[3]) {
                                /**
                                 * 终端协议 0x01 验证码，卡号验证
                                 */
                                case "1":
                                    String rs = customerCardService.getCheckCustomerCard(dataArr[0], dataArr[1],vmid);
                                    data = new ArrayList<>();
                                    data.add((char) 0x10 + "");
                                    data.add((char) 0x06 + "");
                                    data.add((char) 0x01 + "");
                                    switch (rs) {
                                        case "e"://短信失败
                                            data.add((char) 0x05 + "");
                                            break;
                                        case "mobile"://电话号码已使用
                                            data.add((char) 0x02 + "");
                                            break;
                                        case "rfidCode"://物理卡号已使用
                                            data.add((char) 0x03 + "");
                                            break;
                                        case "mobileError"://电话号码格式不正确
                                            data.add((char) 0x04 + "");
                                            break;
                                        default://发送成功，返回验证码
                                            data.add((char) 0x01 + "");
                                            bytes[0] = (byte) (rs.length());
                                            data.add(new String(bytes));
                                            data.add(rs);
                                    }
                                    Utils.sos(Utils.Bill(data), response);
                                    break;
                            }
                            break;
                    }
                    break;
            }
        } catch (IOException ioe) {
            LOGGER.info("ioe fail vmid:" + vmid + "，base64：" + base64);
            ioe.printStackTrace();
        } catch (Exception e) {
            //返回参数错误
            data = new ArrayList<>();
            data.add((char) 0x10 + "");
            data.add((char) 0x00 + "");
            Utils.sos(Utils.Bill(data), response);
            LOGGER.info("e fail vmid:" + vmid + "，base64：" + base64);
            LOGGER.info("e:" + e);
            e.printStackTrace();
        }
    }
}

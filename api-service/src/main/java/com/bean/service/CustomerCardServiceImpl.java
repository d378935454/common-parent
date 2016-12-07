package com.bean.service;

import com.bean.dao.*;
import com.bean.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.MyLogger;
import utils.RanddomMath;
import utils.SmsPost;
import utils.Validation;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhao on 2016/6/30.
 */
@Service
public class CustomerCardServiceImpl implements CustomerCardService {
    private MyLogger LOGGER = new MyLogger(VendingManagerService.class);
    @Autowired
    private VendorMapper vendorMapper;
    @Autowired
    private CustomerCardMapper customerCardMapper;
    @Autowired
    private CustomerInfoMapper customerInfoMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CaptchaRecordMapper captchaRecordMapper;

    public List<CustomerCard> getCustomerCard(String rfidCode) {
        LOGGER.info("是否有该卡 getCustomerCard :" + rfidCode);
        return customerCardMapper.getCustomerCard(rfidCode);
    }


    @Transactional
    public String getCheckCustomerCard(String rfidCode, String mobile, String vmid) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        map.put("rfidCode", rfidCode);
        map.put("infoMobile", mobile);
        map.put("delFlag", "0");
        if (!Validation.isMobile(mobile)) {
            LOGGER.info("售货柜开卡电话不正确 getCheckCustomerCard mobile:" + mobile);
            return "mobileError";
        }
        try {
            CustomerInfo customerInfo = customerInfoMapper.getByMap(map);//电话
            CustomerCard customerCard = customerCardMapper.getByMap(map);//物理卡号
            if (customerInfo != null) {
                LOGGER.info("售货柜开卡电话已被使用 getCheckCustomerCard mobile:" + mobile);
                return "mobile";
            } else if (customerCard == null) {
                LOGGER.info("没有该物理卡号 getCheckCustomerCard rfidCode:" + rfidCode);
                return "norfidCode";
            } else if (customerCard.getCustomerId() != null) {
                LOGGER.info("售货柜开卡物理卡号已被使用 getCheckCustomerCard rfidCode:" + rfidCode);
                return "rfidCode";
            }
            LOGGER.info("售货柜开卡判断物理卡号，电话可以使用 getCheckCustomerCard rfidCode:" + rfidCode + "mobile:" + mobile);

            ConcurrentHashMap<String, Object> vendorMap = new ConcurrentHashMap<>();
            vendorMap.put("vendorNum", vmid);
            Vendor vendor = vendorMapper.getByMap(vendorMap);
            CustomerAllInfo customerAllInfo = new CustomerAllInfo();//用户信息
            customerAllInfo.setInfoMobile(mobile);
            customerAllInfo.setInfoAddress(vendor.getVendorName());
            customerAllInfo.setInfoRealname(mobile + "_p");
            customerAllInfo.setInfoStreet(String.valueOf(vendor.getFkVendorAddr2()));
            CustomerAddress customerAddress = new CustomerAddress();//地址
            customerAddress.setInfoStreet(String.valueOf(vendor.getFkVendorAddr2()));
            customerAddress.setInfoVillage(String.valueOf(vendor.getFkVendorAddr3()));
            //返回密码
            return addCustomerCard(customerAllInfo, rfidCode, customerAddress, vmid);
        } catch (SQLException e) {
            LOGGER.info("fail e 售货柜开卡判断物理卡号，电话是否已使用 getCheckCustomerCard :" + rfidCode);
            e.printStackTrace();
            return "e";
        }
    }

    public String addCustomerCard(CustomerAllInfo allInfo,
                                  String rfidCode,
                                  CustomerAddress cusAddr,
                                  String vmid) {
        ConcurrentHashMap<String, Object> cardCodeMap = new ConcurrentHashMap<>();
        cardCodeMap.put("cardCode", rfidCode);
        try {
            //通过显示卡号获取卡
            CustomerCard bean = customerCardMapper.getByMap(cardCodeMap);


            allInfo.setInfoAddress(allInfo.getInfoAddress());
            bean.setIssueOperator(allInfo.getInfoAddress());
            bean.setIssueMode("1");
            // 先保存用户信息
            allInfo.setIsMobile("1");
            //设置登录key
            UUID uuid = UUID.randomUUID();
            allInfo.setLoginKey(uuid.toString());
            allInfo.setCustomerNickname(allInfo.getInfoMobile() + "_p");
            if (allInfo.getPointLevelId() == null) {
                allInfo.setPointLevelId(customerCardMapper.selectDefaultCustomerLevel().getPointLelvelId());
            }
            allInfo.setCustomerUsername(allInfo.getInfoMobile());
            //生成6位随机数密码
            StringBuffer passWord = new StringBuffer();
            Random random = new Random();
            int a;
            for (int i = 0; i < 6; i++) {
                passWord.append(random.nextInt(10));
            }
            allInfo.setCustomerPassword(passWord.toString());
            customerMapper.insertSelective(allInfo);
            int c = customerInfoMapper.insertSelective(allInfo);
            String id = allInfo.getCustomerId().toString();


            //设置默认地址
            cusAddr.setInfoProvince("1");
            cusAddr.setInfoCity("1");
            cusAddr.setInfoCounty("11");
            cusAddr.setCustomerId(Long.valueOf(id));
            cusAddr.setAddressMoblie(allInfo.getInfoMobile());
            cusAddr.setAddressName(allInfo.getInfoRealname());
            cusAddr.setAddressDetail(allInfo.getInfoAddress());
            cusAddr.setIsDefault("1");
            customerMapper.insertAddress(cusAddr);
            //设置默认地址结束
            int length = id.length();
            int zero = 12 - length;
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < zero; i++) {
                if (i == 0) {
                    sb.append('X');
                } else {
                    sb.append('0');
                }
            }
            String cardCode = id.concat(sb.toString()).concat(RanddomMath.randomString(6));
//    	bean.setCardBalance(new BigDecimal(0));//20160719
            if (bean.getCardCode() == "" || bean.getCardCode() == null) {
                bean.setCardCode(cardCode);
            }

            bean.setCustomerId(allInfo.getCustomerId());
            //bean.setDelFlag("0");//20160719
            //bean.setCardStatus("0");//20160719
            bean.setIssueDate(Calendar.getInstance().getTime());
            //customerCardMapper.insertSelective(bean); //20160719
            customerCardMapper.updateByCardCodeSelective(bean);
            if (c > 0) {//发送短信
                String msgContent = SmsPost.MSG_PASSWORD.replace("@1", passWord);
                SmsPost.huaxSend(allInfo.getInfoMobile(), msgContent);
            }
            return passWord.toString();

        } catch (SQLException e) {
            LOGGER.info("售卖机（{0}） e mobile :{1} 开卡 fail", vmid, allInfo.getInfoMobile());
            e.printStackTrace();
            return "e";
        } catch (IOException ioe) {
            LOGGER.info("售卖机（{0}） e mobile :{1} 开卡发短信 fail", vmid, allInfo.getInfoMobile());
//            ioe.printStackTrace();
            return "e";
        }

    }


    //HttpServletRequest request,
    public String sendMsgCodeToMobile(String mobile) {
        String code=null;
        try {
            if (customerMapper.checkMobileExist(mobile) > 0) {
                return "1";
            }
            String receiveMobile = mobile;
            int num = (int) ((Math.random() * 9 + 1) * 100000);
             code = ((Integer) num).toString();
            String msgContent = "验证码为" + code;
            SmsPost.huaxSend(mobile, msgContent);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean sendPost = true;
        return code;
//        if(null == request.getSession().getAttribute(MOBILE_MSG_CODE)){
//            //没有发送过，将该短信信息存入session中
//            sendPost = smsService.sendPost(receiveMobile, msgContent);
//            if(sendPost){
//                request.getSession().setAttribute(MOBILE_MSG_CODE, code);
//                request.getSession().setAttribute(MOBILE_MSG_PHONE, mobile);
//                request.getSession().setAttribute(MOBILE_MSG_DATETIME, System.currentTimeMillis());
//                return 0;
//            }else{
//                return -1;
//            }
//        }else{
//            Long date = (Long)request.getSession().getAttribute(MOBILE_MSG_DATETIME);
//            // 已发送过，两分钟后才能再次发送
//            if(null == date || System.currentTimeMillis() - date.longValue() > 120000){
//                sendPost = smsService.sendPost(receiveMobile, msgContent);
//                if(sendPost){
//                    request.getSession().setAttribute(MOBILE_MSG_CODE, code);
//                    request.getSession().setAttribute(MOBILE_MSG_PHONE, mobile);
//                    request.getSession().setAttribute(MOBILE_MSG_DATETIME, System.currentTimeMillis());
//                    return 0;
//                }else{
//                    return -1;
//                }
//            }else{
//                return 2;
//            }
//        }
    }

    public int updateCustomerAmount(String rfidCode, BigDecimal amount, String recordType) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        map.put("amount", amount);
        map.put("rfidCode", rfidCode);
        map.put("recodeType", recordType);
        return customerCardMapper.updateCustomerAmount(map);
    }

}

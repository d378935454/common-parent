package com.bean.dao;

import com.bean.model.PackageRecord;
import mybatis.basemapper.BaseMapper;

import java.sql.SQLException;
import java.util.Map;

public interface PackageRecordMapper extends BaseMapper<PackageRecord> {
    /**
     * 通过包裹号得到电话
     * @param packageNo
     * @return
     */
    String getCustomerMobileByPackageNo(String packageNo) throws SQLException;

    /**
     * 通过密码或物理卡号取货
     * @param vendingCode
     * @param code
     * @return
     */
    Map<String,String> getCellCodeWithPassWordOrRfidCode(String vendingCode, String code) throws SQLException;

    /**
     * 通过包裹号得到订单号
     * @param packageNo
     * @return
     */
    String getOrderCodeByPackageNo(String packageNo);
    /**
     * 通过包裹号得到用户rid
     * @param packageNo
     * @return
     */
    String getRfidByPackageNo(String packageNo);

}
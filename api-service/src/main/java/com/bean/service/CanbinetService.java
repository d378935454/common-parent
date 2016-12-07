package com.bean.service;

import com.bean.model.*;
import com.github.pagehelper.PageInfo;



import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by bean on 2016/6/22.
 */
public interface CanbinetService {


    /**
     * 通过rfid得到云柜管理员
     * @param manegerRfid 云柜管理员id
     * @param boxcode
     * @return
     */
    CanbinetManager getCabinetManagerByRfidAndCanbinetId(String manegerRfid, String boxcode)throws Exception;

    /**
     * 云柜送货
     *
     * @param canbinet 云柜号
     * @param packageNo   包裹号
     * @param cellCode   cell码
     * @return
     */
    PackageRecord insertPackageIntoCabinet(String canbinet, String packageNo, String cellCode) throws Exception;

    /**
     * 配送员取货
     * @param boxId
     * @param packageNo
     * @param type
     */
    void updatePackage(String boxId, String packageNo, int type)throws Exception;

    /**
     * 第一次连接时，将boxid与channel的key关联 在数据库中
     * @param key
     * @param boxId
     */
    void updateVendorSocketKey(String key, String boxId)throws Exception;

    /**
     * 云柜上传温度
     * @param
     */
    void insertTemperature(String boxId,Byte temperature)throws Exception;

    /**
     * 出來rfid為16进制   把它转成正常
     * 列 C9391C00  C9391C   1C39C9  0001849801
     */
    String Rfid16to10(String Rfid) throws Exception;

}

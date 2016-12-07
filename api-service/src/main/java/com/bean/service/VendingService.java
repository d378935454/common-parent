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
public interface VendingService {

    /**
     * 得到商品管理信息
     *
     * @return
     */
    List<Vending> getGoodsManageList(String vendingCode);

    /**
     * 销售订单
     *
     * @param vendingCode 机器号
     * @param orderCode   订单号
     * @param TrackCode   货道号
     * @param goodsInfoId 商品id
     * @param price       单价
     * @param oldPrice    应付价格
     * @param prePrice    实付价格
     * @param rfidCode    芯片号
     * @param isSuccess   是否成功0失败 1成功
     * @param stockNum    库存
     * @param datetime    交易时间
     * @return
     */
    boolean insertOrder(String vendingCode, String orderCode, String TrackCode, Long goodsInfoId, BigDecimal price, BigDecimal oldPrice, BigDecimal prePrice, String rfidCode, Integer isSuccess, Integer stockNum, Date datetime);

    /**
     * 云柜送货
     *
     * @param vendingCode 机器号
     * @param packageNo   包裹号
     * @return
     */
    String insertPackageIntoVending(String vendingCode, String packageNo);

    /**
     * 云柜取货
     *
     * @param vendingCode
     * @param code
     * @return
     */
    String updateVendingGetPackageOut(String vendingCode, String code) throws Exception;

    /**
     * 上传温度
     *
     * @param vendingCode 机器号
     * @param type        1=售卖机,2=云柜
     * @param temperature 设备温度℃
     * @return
     */
    int insertDeviceTemerature(String vendingCode, int type, BigDecimal temperature);

    PageInfo getOrderListByMap();

    /**
     * 大小屏滚动信息
     *
     * @param tokenTime  机器最新更新时间(14位)
     * @param screenType 屏幕类型 1小屏 2大屏
     * @return
     */
    VendingAdv getVendingAdvByScreenAndToken(Date tokenTime, String screenType);

    /**
     * @param vendingCode
     * @param operateTime 操作时间
     * @param managerId   补货员编号
     * @param vendingUps  货道及数量
     * @return
     */
    boolean updateAddStock(String vendingCode, Date operateTime, Long managerId, List<VendingUp> vendingUps);

//    /**
//     * 通过rfid得到云柜管理员
//     * @param manegerRfid 云柜管理员id
//     * @param boxcode
//     * @return
//     */
//    CanbinetManager getCabinetManagerByRfidAndCanbinetId(String manegerRfid,String boxcode);
//
//    /**
//     * 云柜送货
//     *
//     * @param canbinet 云柜号
//     * @param packageNo   包裹号
//     * @param cellCode   cell码
//     * @return
//     */
//    PackageRecord insertPackageIntoCabinet(String canbinet ,String packageNo,String cellCode);

    String checkPackagePass(String password, List<CanbinetCell> canbinetCellsParam);
}

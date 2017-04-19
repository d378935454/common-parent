package com.bean.springboot.dto.order;

import com.bean.springboot.dto.goods.GoodsInfo;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by bean on 2017/4/16.
 */
@Entity
@Table(name = "order_info", schema = "xiashi")
public class OrderInfo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "goods_info_id")
    private GoodsInfo goodsInfo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "express_id")
    private Express express;
    @Basic
    @Column(name = "old_price", nullable = false, precision = 0)
    private int oldPrice;
    @Basic
    @Column(name = "price", nullable = false, precision = 0)
    private int price;
    @Basic
    @Column(name = "all_num", nullable = false)
    private int allNum;
    @Basic
    @Column(name = "check_num", nullable = true)
    private Integer checkNum;
    @Basic
    @Column(name = "send_num", nullable = true)
    private Integer sendNum;
    @Basic
    @Column(name = "inserttime", nullable = false)
    private Timestamp inserttime;
    @Basic
    @Column(name = "updatetime", nullable = false)
    private Timestamp updatetime;
    @Basic
    @Column(name = "is_Delete", nullable = true)
    private Byte isDelete;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public GoodsInfo getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public Express getExpress() {
        return express;
    }

    public void setExpress(Express express) {
        this.express = express;
    }

    public int getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(int oldPrice) {
        this.oldPrice = oldPrice;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAllNum() {
        return allNum;
    }

    public void setAllNum(int allNum) {
        this.allNum = allNum;
    }

    public Integer getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(Integer checkNum) {
        this.checkNum = checkNum;
    }


    public Integer getSendNum() {
        return sendNum;
    }

    public void setSendNum(Integer sendNum) {
        this.sendNum = sendNum;
    }


    public Timestamp getInserttime() {
        return inserttime;
    }

    public void setInserttime(Timestamp inserttime) {
        this.inserttime = inserttime;
    }


    public Timestamp getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Timestamp updatetime) {
        this.updatetime = updatetime;
    }


    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderInfo orderInfo = (OrderInfo) o;

        if (id != orderInfo.id) return false;
        if (oldPrice != orderInfo.oldPrice) return false;
        if (price != orderInfo.price) return false;
        if (allNum != orderInfo.allNum) return false;
        if (checkNum != null ? !checkNum.equals(orderInfo.checkNum) : orderInfo.checkNum != null) return false;
        if (sendNum != null ? !sendNum.equals(orderInfo.sendNum) : orderInfo.sendNum != null) return false;
        if (inserttime != null ? !inserttime.equals(orderInfo.inserttime) : orderInfo.inserttime != null) return false;
        if (updatetime != null ? !updatetime.equals(orderInfo.updatetime) : orderInfo.updatetime != null) return false;
        if (isDelete != null ? !isDelete.equals(orderInfo.isDelete) : orderInfo.isDelete != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + oldPrice;
        result = 31 * result + price;
        result = 31 * result + allNum;
        result = 31 * result + (checkNum != null ? checkNum.hashCode() : 0);
        result = 31 * result + (sendNum != null ? sendNum.hashCode() : 0);
        result = 31 * result + (inserttime != null ? inserttime.hashCode() : 0);
        result = 31 * result + (updatetime != null ? updatetime.hashCode() : 0);
        result = 31 * result + (isDelete != null ? isDelete.hashCode() : 0);
        return result;
    }
}

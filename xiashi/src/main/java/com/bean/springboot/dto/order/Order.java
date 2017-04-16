package com.bean.springboot.dto.order;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bean on 2017/4/16.
 */
@Entity
@Table(name = "xs_order")
public class Order {
    private long id;
    private Byte state;
    private Integer oldPrice;
    private Integer price;
    private String sendAddress;
    private Timestamp sendDate;
    private Timestamp relSendDate;
    private String getAddress;
    private Timestamp getDate;
    private String picUrl;
    private Timestamp inserttime;
    private Timestamp updatetime;
    private byte isDelete;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "state", nullable = true)
    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    @Basic
    @Column(name = "old_price", nullable = true, precision = 0)
    public Integer getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Integer oldPrice) {
        this.oldPrice = oldPrice;
    }

    @Basic
    @Column(name = "price", nullable = true, precision = 0)
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Basic
    @Column(name = "send_address", nullable = true, length = 50)
    public String getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }

    @Basic
    @Column(name = "send_date", nullable = true)
    public Timestamp getSendDate() {
        return sendDate;
    }

    public void setSendDate(Timestamp sendDate) {
        this.sendDate = sendDate;
    }

    @Basic
    @Column(name = "rel_send_date", nullable = true)
    public Timestamp getRelSendDate() {
        return relSendDate;
    }

    public void setRelSendDate(Timestamp relSendDate) {
        this.relSendDate = relSendDate;
    }

    @Basic
    @Column(name = "get_address", nullable = true, length = 50)
    public String getGetAddress() {
        return getAddress;
    }

    public void setGetAddress(String getAddress) {
        this.getAddress = getAddress;
    }

    @Basic
    @Column(name = "get_date", nullable = true)
    public Timestamp getGetDate() {
        return getDate;
    }

    public void setGetDate(Timestamp getDate) {
        this.getDate = getDate;
    }

    @Basic
    @Column(name = "pic_url", nullable = true, length = 255)
    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Basic
    @Column(name = "inserttime", nullable = true)
    public Timestamp getInserttime() {
        return inserttime;
    }

    public void setInserttime(Timestamp inserttime) {
        this.inserttime = inserttime;
    }

    @Basic
    @Column(name = "updatetime", nullable = true)
    public Timestamp getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Timestamp updatetime) {
        this.updatetime = updatetime;
    }

    @Basic
    @Column(name = "is_delete", nullable = false)
    public byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(byte isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (isDelete != order.isDelete) return false;
        if (state != null ? !state.equals(order.state) : order.state != null) return false;
        if (oldPrice != null ? !oldPrice.equals(order.oldPrice) : order.oldPrice != null) return false;
        if (price != null ? !price.equals(order.price) : order.price != null) return false;
        if (sendAddress != null ? !sendAddress.equals(order.sendAddress) : order.sendAddress != null) return false;
        if (sendDate != null ? !sendDate.equals(order.sendDate) : order.sendDate != null) return false;
        if (relSendDate != null ? !relSendDate.equals(order.relSendDate) : order.relSendDate != null) return false;
        if (getAddress != null ? !getAddress.equals(order.getAddress) : order.getAddress != null) return false;
        if (getDate != null ? !getDate.equals(order.getDate) : order.getDate != null) return false;
        if (picUrl != null ? !picUrl.equals(order.picUrl) : order.picUrl != null) return false;
        if (inserttime != null ? !inserttime.equals(order.inserttime) : order.inserttime != null) return false;
        if (updatetime != null ? !updatetime.equals(order.updatetime) : order.updatetime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (oldPrice != null ? oldPrice.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (sendAddress != null ? sendAddress.hashCode() : 0);
        result = 31 * result + (sendDate != null ? sendDate.hashCode() : 0);
        result = 31 * result + (relSendDate != null ? relSendDate.hashCode() : 0);
        result = 31 * result + (getAddress != null ? getAddress.hashCode() : 0);
        result = 31 * result + (getDate != null ? getDate.hashCode() : 0);
        result = 31 * result + (picUrl != null ? picUrl.hashCode() : 0);
        result = 31 * result + (inserttime != null ? inserttime.hashCode() : 0);
        result = 31 * result + (updatetime != null ? updatetime.hashCode() : 0);
        result = 31 * result + (int) isDelete;
        return result;
    }
}

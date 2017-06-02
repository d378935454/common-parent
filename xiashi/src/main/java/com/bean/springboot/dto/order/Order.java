package com.bean.springboot.dto.order;

import com.bean.springboot.dto.usermanagement.User;
import com.bean.springboot.type.StateType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by bean on 2017/4/16.
 */
@Entity
@Table(name = "xs_order")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")//防止json转换无限循环
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Basic
    @Column(name = "state", nullable = true)
    @Enumerated(EnumType.ORDINAL)
    private StateType state;
    @Basic
    @Column(name = "order_no")
    private String orderNo;
    @Basic
    @Column(name = "old_price", nullable = true, precision = 0)
    private BigDecimal oldPrice;
    @Basic
    @Column(name = "price", nullable = true, precision = 0)
    private BigDecimal price;
    @Basic
    @Column(name = "send_address", nullable = true, length = 50)
    private String sendAddress;
    @Basic
    @Column(name = "send_date", nullable = true)
    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")//json的返回格式
    private Timestamp sendDate;
    @Basic
    @Column(name = "rel_send_date", nullable = true)
    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")//json的返回格式
    private Timestamp relSendDate;
    @Basic
    @Column(name = "get_address", nullable = true, length = 50)
    private String getAddress;

    @Basic
    @Column(name = "old_get_date", nullable = true)
    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")//json的返回格式
    private Timestamp oldGetDate;
    @Basic
    @Column(name = "get_date", nullable = true)
    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")//json的返回格式
    private Timestamp getDate;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "express_id")
    private Express express;
    @ManyToOne
    @JoinColumn(name = "get_user_id" )
    private User user;
    @Basic
    @Column(name = "pic_url", nullable = true, length = 255)
    private String picUrl;
    @OneToMany(mappedBy = "order",cascade = CascadeType.PERSIST)
    private List<OrderInfo> orderInfos;
    @Basic
    @Column(name = "inserttime", nullable = true)
    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")//json的返回格式
    private Timestamp inserttime;
    @Basic
    @Column(name = "updatetime", nullable = true)
    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")//json的返回格式
    private Timestamp updatetime;
    @Basic
    @Column(name = "is_delete", nullable = false)
    private byte isDelete;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public StateType getState() {
        return state;
    }

    public void setState(StateType state) {
        this.state = state;
    }

    public Express getExpress() {
        return express;
    }

    public void setExpress(Express express) {
        this.express = express;
    }
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }


    public List<OrderInfo> getOrderInfos() {
        return orderInfos;
    }

    public void setOrderInfos(List<OrderInfo> orderInfos) {
        this.orderInfos = orderInfos;
    }


    public BigDecimal getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(BigDecimal oldPrice) {
        this.oldPrice = oldPrice;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public Timestamp getOldGetDate() {
        return oldGetDate;
    }

    public void setOldGetDate(Timestamp oldGetDate) {
        this.oldGetDate = oldGetDate;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }


    public Timestamp getSendDate() {
        return sendDate;
    }

    public void setSendDate(Timestamp sendDate) {
        this.sendDate = sendDate;
    }


    public Timestamp getRelSendDate() {
        return relSendDate;
    }

    public void setRelSendDate(Timestamp relSendDate) {
        this.relSendDate = relSendDate;
    }


    public String getGetAddress() {
        return getAddress;
    }

    public void setGetAddress(String getAddress) {
        this.getAddress = getAddress;
    }


    public Timestamp getGetDate() {
        return getDate;
    }

    public void setGetDate(Timestamp getDate) {
        this.getDate = getDate;
    }


    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
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

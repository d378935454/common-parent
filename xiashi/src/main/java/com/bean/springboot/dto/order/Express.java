package com.bean.springboot.dto.order;

import com.bean.springboot.dto.usermanagement.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by bean on 2017/4/16.
 */
@Entity
@Table(name = "express")
public class Express {
    private long id;
    private String expressNo;
    private User user;
    private BigDecimal price;
    private Timestamp startdate;
    private Timestamp oldSenddate;
    private Timestamp relSenddate;
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
    @Column(name = "express_no", nullable = true, length = 50)
    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    @Basic
    @Column(name = "price", nullable = true, length = 20)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "startdate", nullable = true)
    public Timestamp getStartdate() {
        return startdate;
    }

    public void setStartdate(Timestamp startdate) {
        this.startdate = startdate;
    }

    @Basic
    @Column(name = "old_senddate", nullable = true)
    public Timestamp getOldSenddate() {
        return oldSenddate;
    }

    public void setOldSenddate(Timestamp oldSenddate) {
        this.oldSenddate = oldSenddate;
    }

    @Basic
    @Column(name = "rel_senddate", nullable = true)
    public Timestamp getRelSenddate() {
        return relSenddate;
    }

    public void setRelSenddate(Timestamp relSenddate) {
        this.relSenddate = relSenddate;
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

        Express express = (Express) o;

        if (id != express.id) return false;
        if (isDelete != express.isDelete) return false;
        if (expressNo != null ? !expressNo.equals(express.expressNo) : express.expressNo != null) return false;
        if (startdate != null ? !startdate.equals(express.startdate) : express.startdate != null) return false;
        if (oldSenddate != null ? !oldSenddate.equals(express.oldSenddate) : express.oldSenddate != null) return false;
        if (relSenddate != null ? !relSenddate.equals(express.relSenddate) : express.relSenddate != null) return false;
        if (inserttime != null ? !inserttime.equals(express.inserttime) : express.inserttime != null) return false;
        if (updatetime != null ? !updatetime.equals(express.updatetime) : express.updatetime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (expressNo != null ? expressNo.hashCode() : 0);
        result = 31 * result + (startdate != null ? startdate.hashCode() : 0);
        result = 31 * result + (oldSenddate != null ? oldSenddate.hashCode() : 0);
        result = 31 * result + (relSenddate != null ? relSenddate.hashCode() : 0);
        result = 31 * result + (inserttime != null ? inserttime.hashCode() : 0);
        result = 31 * result + (updatetime != null ? updatetime.hashCode() : 0);
        result = 31 * result + (int) isDelete;
        return result;
    }
}

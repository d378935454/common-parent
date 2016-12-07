package com.bean.model;

import java.util.Date;

public class CanbinetManagerRecord {
    /**
     * 
     */
    private Long id;

    /**
     * 云柜管理员id
     */
    private Long managerId;

    /**
     * 云柜id
     */
    private Long canbinetId;

    /**
     * 
     */
    private Date time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getCanbinetId() {
        return canbinetId;
    }

    public void setCanbinetId(Long canbinetId) {
        this.canbinetId = canbinetId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
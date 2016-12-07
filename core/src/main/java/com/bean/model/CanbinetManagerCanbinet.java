package com.bean.model;

public class CanbinetManagerCanbinet {
    /**
     * 
     */
    private Long id;

    /**
     * 云柜管理员id
     */
    private Long managerId;

    /**
     * 云柜id
     */
    private Long canbinetId;

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
}
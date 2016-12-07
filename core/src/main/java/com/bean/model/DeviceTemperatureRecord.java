package com.bean.model;

import java.math.BigDecimal;
import java.util.Date;

public class DeviceTemperatureRecord {
    /**
     *
     */
    private Long id;

    /**
     * 设备id
     */
    private Long deviceId;

    /**
     * 1=售卖机,2=云柜
     */
    private Integer deviceType;

    /**
     * 设备温度℃
     */
    private BigDecimal temperature;

    /**
     * 温度记录时间
     */
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
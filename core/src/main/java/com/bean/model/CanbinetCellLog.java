package com.bean.model;

import java.util.Date;

public class CanbinetCellLog {
    /**
     * 主键
     */
    private Long id;

    /**
     * 包裹号
     */
    private String packageNo;

    /**
     * 0:送货1:格子异常2:取货
     */
    private Byte type;

    /**
     * 云柜格子主键
     */
    private Long cellId;

    /**
     * 用户id
     */
    private Long customeId;

    /**
     * 产生时间
     */
    private Date datetime;

    /**
     * 取货时的密码或物理卡号
     */
    private String code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPackageNo() {
        return packageNo;
    }

    public void setPackageNo(String packageNo) {
        this.packageNo = packageNo == null ? null : packageNo.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Long getCellId() {
        return cellId;
    }

    public void setCellId(Long cellId) {
        this.cellId = cellId;
    }

    public Long getCustomeId() {
        return customeId;
    }

    public void setCustomeId(Long customeId) {
        this.customeId = customeId;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }
}
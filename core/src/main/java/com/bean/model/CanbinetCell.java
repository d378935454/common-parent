package com.bean.model;

public class CanbinetCell {
    /**
     *
     */
    private Long canbinetCellId;

    /**
     * 云柜id
     */
    private Long canbinetId;

    /**
     * 云柜格编号
     */
    private String cellCode;

    /**
     * 云柜格状态 0 空闲 1占用 2 停用 3 未关门 4 未知异常
     */
    private String cellStatus;

    public Long getCanbinetCellId() {
        return canbinetCellId;
    }

    public void setCanbinetCellId(Long canbinetCellId) {
        this.canbinetCellId = canbinetCellId;
    }

    public Long getCanbinetId() {
        return canbinetId;
    }

    public void setCanbinetId(Long canbinetId) {
        this.canbinetId = canbinetId;
    }

    public String getCellCode() {
        return cellCode;
    }

    public void setCellCode(String cellCode) {
        this.cellCode = cellCode == null ? null : cellCode.trim();
    }

    public String getCellStatus() {
        return cellStatus;
    }

    public void setCellStatus(String cellStatus) {
        this.cellStatus = cellStatus == null ? null : cellStatus.trim();
    }

    private String passWord;//密码

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
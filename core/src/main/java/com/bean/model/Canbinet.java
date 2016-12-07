package com.bean.model;

public class Canbinet {
    /**
     * canbinet_id
     */
    private Long canbinetId;

    /**
     * 售卖机id
     */
    private Long vendingId;

    /**
     * 云柜格数量
     */
    private Long cellNum;

    /**
     * 云柜状态 0 正常 1 停用 2爆柜
     */
    private String canbinetStatus;

    /**
     * 售卖柜云柜
     */
    private String canbinetCode;

    /**
     * 单独云柜码
     */
    private String canbinetCodeRoot;

    /**
     * socket链接的key
     */
    private String socketKey;

    public Long getCanbinetId() {
        return canbinetId;
    }

    public void setCanbinetId(Long canbinetId) {
        this.canbinetId = canbinetId;
    }

    public Long getVendingId() {
        return vendingId;
    }

    public void setVendingId(Long vendingId) {
        this.vendingId = vendingId;
    }

    public Long getCellNum() {
        return cellNum;
    }

    public void setCellNum(Long cellNum) {
        this.cellNum = cellNum;
    }

    public String getCanbinetStatus() {
        return canbinetStatus;
    }

    public void setCanbinetStatus(String canbinetStatus) {
        this.canbinetStatus = canbinetStatus == null ? null : canbinetStatus.trim();
    }

    public String getCanbinetCode() {
        return canbinetCode;
    }

    public void setCanbinetCode(String canbinetCode) {
        this.canbinetCode = canbinetCode == null ? null : canbinetCode.trim();
    }

    public String getCanbinetCodeRoot() {
        return canbinetCodeRoot;
    }

    public void setCanbinetCodeRoot(String canbinetCodeRoot) {
        this.canbinetCodeRoot = canbinetCodeRoot == null ? null : canbinetCodeRoot.trim();
    }

    public String getSocketKey() {
        return socketKey;
    }

    public void setSocketKey(String socketKey) {
        this.socketKey = socketKey == null ? null : socketKey.trim();
    }
}
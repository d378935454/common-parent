package com.bean.model;

public class VendorAisle {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private Integer stock;

    /**
     * 
     */
    private String aisleNum;

    /**
     * 
     */
    private Integer vendorAllocationAmount;

    /**
     * 
     */
    private Long fkNpGoods;

    /**
     * 
     */
    private Long fkVendor;

    /**
     * 
     */
    private Long fkCdVendorTypeAisle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getAisleNum() {
        return aisleNum;
    }

    public void setAisleNum(String aisleNum) {
        this.aisleNum = aisleNum;
    }

    public Integer getVendorAllocationAmount() {
        return vendorAllocationAmount;
    }

    public void setVendorAllocationAmount(Integer vendorAllocationAmount) {
        this.vendorAllocationAmount = vendorAllocationAmount;
    }

    public Long getFkNpGoods() {
        return fkNpGoods;
    }

    public void setFkNpGoods(Long fkNpGoods) {
        this.fkNpGoods = fkNpGoods;
    }

    public Long getFkVendor() {
        return fkVendor;
    }

    public void setFkVendor(Long fkVendor) {
        this.fkVendor = fkVendor;
    }

    public Long getFkCdVendorTypeAisle() {
        return fkCdVendorTypeAisle;
    }

    public void setFkCdVendorTypeAisle(Long fkCdVendorTypeAisle) {
        this.fkCdVendorTypeAisle = fkCdVendorTypeAisle;
    }
}
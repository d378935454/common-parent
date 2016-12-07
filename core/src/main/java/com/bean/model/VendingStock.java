package com.bean.model;

public class VendingStock {
    /**
     * 
     */
    private Long vendingStockId;

    /**
     * 
     */
    private Long trackId;

    /**
     * 
     */
    private Long productId;

    /**
     * 
     */
    private Long productNum;

    public Long getVendingStockId() {
        return vendingStockId;
    }

    public void setVendingStockId(Long vendingStockId) {
        this.vendingStockId = vendingStockId;
    }

    public Long getTrackId() {
        return trackId;
    }

    public void setTrackId(Long trackId) {
        this.trackId = trackId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductNum() {
        return productNum;
    }

    public void setProductNum(Long productNum) {
        this.productNum = productNum;
    }
}
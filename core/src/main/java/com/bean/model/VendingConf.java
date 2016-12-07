package com.bean.model;

import java.util.Date;

public class VendingConf {
    /**
     * vending_conf_id
     */
    private Long vendingConfId;

    /**
     * 货道id
     */
    private Long trackId;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 菜品数量
     */
    private Long productNum;

    /**
     * 配置日期
     */
    private Date confDate;

    public Long getVendingConfId() {
        return vendingConfId;
    }

    public void setVendingConfId(Long vendingConfId) {
        this.vendingConfId = vendingConfId;
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

    public Date getConfDate() {
        return confDate;
    }

    public void setConfDate(Date confDate) {
        this.confDate = confDate;
    }
}
package com.bean.model;

public class VendingTrack {
    /**
     * 货道id
     */
    private Long vendingTrackId;

    /**
     * 售卖机id
     */
    private Long vendingId;

    /**
     * 货道规格
     */
    private String trackSpec;

    /**
     * 货道码
     */
    private String trackCode;

    public Long getVendingTrackId() {
        return vendingTrackId;
    }

    public void setVendingTrackId(Long vendingTrackId) {
        this.vendingTrackId = vendingTrackId;
    }

    public Long getVendingId() {
        return vendingId;
    }

    public void setVendingId(Long vendingId) {
        this.vendingId = vendingId;
    }

    public String getTrackSpec() {
        return trackSpec;
    }

    public void setTrackSpec(String trackSpec) {
        this.trackSpec = trackSpec == null ? null : trackSpec.trim();
    }

    public String getTrackCode() {
        return trackCode;
    }

    public void setTrackCode(String trackCode) {
        this.trackCode = trackCode == null ? null : trackCode.trim();
    }
}
package com.bean.springboot.dto.goods;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by bean on 2017/4/16.
 */
@Entity
@Table(name = "goods_info", schema = "xiashi")
public class GoodsInfo {
    private long id;

    private Spec spec;
    private Goods goods;
    private String goodsInfoNo;
    private BigDecimal oldPrice;
    private BigDecimal price;
    private String name;
    private Timestamp inserttime;
    private Timestamp updatetime;
    private Byte isDelete;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "goods_id",referencedColumnName = "id")
    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Basic
    @Column(name = "goods_info_no", nullable = true, length = 30)
    public String getGoodsInfoNo() {
        return goodsInfoNo;
    }

    public void setGoodsInfoNo(String goodsInfoNo) {
        this.goodsInfoNo = goodsInfoNo;
    }

    @ManyToOne
    @JoinColumn(name = "spec_id",referencedColumnName = "id",nullable = false)
    public Spec getSpec() {
        return spec;
    }

    public void setSpec(Spec spec) {
        this.spec = spec;
    }

    @Basic
    @Column(name = "old_price", nullable = true, precision = 0)
    public BigDecimal getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(BigDecimal oldPrice) {
        this.oldPrice = oldPrice;
    }

    @Basic
    @Column(name = "price", nullable = true, precision = 0)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "inserttime", nullable = true)
    public Timestamp getInserttime() {
        return inserttime;
    }

    public void setInserttime(Timestamp inserttime) {
        this.inserttime = inserttime;
    }

    @Basic
    @Column(name = "updatetime", nullable = true)
    public Timestamp getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Timestamp updatetime) {
        this.updatetime = updatetime;
    }

    @Basic
    @Column(name = "is_delete", nullable = true)
    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodsInfo goodsInfo = (GoodsInfo) o;

        if (id != goodsInfo.id) return false;
        if (goodsInfoNo != null ? !goodsInfoNo.equals(goodsInfo.goodsInfoNo) : goodsInfo.goodsInfoNo != null)
            return false;
        if (oldPrice != null ? !oldPrice.equals(goodsInfo.oldPrice) : goodsInfo.oldPrice != null) return false;
        if (price != null ? !price.equals(goodsInfo.price) : goodsInfo.price != null) return false;
        if (name != null ? !name.equals(goodsInfo.name) : goodsInfo.name != null) return false;
        if (inserttime != null ? !inserttime.equals(goodsInfo.inserttime) : goodsInfo.inserttime != null) return false;
        if (updatetime != null ? !updatetime.equals(goodsInfo.updatetime) : goodsInfo.updatetime != null) return false;
        if (isDelete != null ? !isDelete.equals(goodsInfo.isDelete) : goodsInfo.isDelete != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (goodsInfoNo != null ? goodsInfoNo.hashCode() : 0);
        result = 31 * result + (oldPrice != null ? oldPrice.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (inserttime != null ? inserttime.hashCode() : 0);
        result = 31 * result + (updatetime != null ? updatetime.hashCode() : 0);
        result = 31 * result + (isDelete != null ? isDelete.hashCode() : 0);
        return result;
    }
}

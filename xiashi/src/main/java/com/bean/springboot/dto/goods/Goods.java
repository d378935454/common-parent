package com.bean.springboot.dto.goods;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by bean on 2017/4/16.
 */
@Entity
public class Goods {
    private long id;
    private String goodsNo;
    private String name;
    private Type type;
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

    @Basic
    @Column(name = "goods_no", nullable = true, length = 30)
    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "type_id",referencedColumnName = "id")
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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

        Goods goods = (Goods) o;

        if (id != goods.id) return false;
        if (goodsNo != null ? !goodsNo.equals(goods.goodsNo) : goods.goodsNo != null) return false;
        if (name != null ? !name.equals(goods.name) : goods.name != null) return false;
        if (inserttime != null ? !inserttime.equals(goods.inserttime) : goods.inserttime != null) return false;
        if (updatetime != null ? !updatetime.equals(goods.updatetime) : goods.updatetime != null) return false;
        if (isDelete != null ? !isDelete.equals(goods.isDelete) : goods.isDelete != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (goodsNo != null ? goodsNo.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (inserttime != null ? inserttime.hashCode() : 0);
        result = 31 * result + (updatetime != null ? updatetime.hashCode() : 0);
        result = 31 * result + (isDelete != null ? isDelete.hashCode() : 0);
        return result;
    }
}

package com.bean.springboot.dto.goods;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by bean on 2017/4/16.
 */
@Entity
@Table(name = "spec")
public class Spec {
    private long id;
    private String spec;
    private Timestamp inserttime;
    private Timestamp updatetime;
    private byte isDelete;

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
    @Column(name = "spec", nullable = true, length = 255)
    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
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
    @Column(name = "is_delete", nullable = false)
    public byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(byte isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Spec spec1 = (Spec) o;

        if (id != spec1.id) return false;
        if (isDelete != spec1.isDelete) return false;
        if (spec != null ? !spec.equals(spec1.spec) : spec1.spec != null) return false;
        if (inserttime != null ? !inserttime.equals(spec1.inserttime) : spec1.inserttime != null) return false;
        if (updatetime != null ? !updatetime.equals(spec1.updatetime) : spec1.updatetime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (spec != null ? spec.hashCode() : 0);
        result = 31 * result + (inserttime != null ? inserttime.hashCode() : 0);
        result = 31 * result + (updatetime != null ? updatetime.hashCode() : 0);
        result = 31 * result + (int) isDelete;
        return result;
    }
}

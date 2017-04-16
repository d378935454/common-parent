package com.bean.springboot.dto.goods;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by bean on 2017/4/16.
 */
@Entity
public class Type {
    private long id;
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

    @Basic
    @Column(name = "name", nullable = true, length = 20)
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

        Type type = (Type) o;

        if (id != type.id) return false;
        if (name != null ? !name.equals(type.name) : type.name != null) return false;
        if (inserttime != null ? !inserttime.equals(type.inserttime) : type.inserttime != null) return false;
        if (updatetime != null ? !updatetime.equals(type.updatetime) : type.updatetime != null) return false;
        if (isDelete != null ? !isDelete.equals(type.isDelete) : type.isDelete != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (inserttime != null ? inserttime.hashCode() : 0);
        result = 31 * result + (updatetime != null ? updatetime.hashCode() : 0);
        result = 31 * result + (isDelete != null ? isDelete.hashCode() : 0);
        return result;
    }
}

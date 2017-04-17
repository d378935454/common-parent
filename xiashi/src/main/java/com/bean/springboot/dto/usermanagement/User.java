package com.bean.springboot.dto.usermanagement;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "account_name")
    @JsonIgnore
    private String accountName;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Column(name = "id_card")
    private String idCard;

    @Column(name = "real_name")
    private String name;

    @Column(name = "mobile")
    private String mobile;


    @Column(name = "email")
    private String email;

    @Column(name = "is_delete")
    @JsonIgnore
    private Integer isDelete;


    @ManyToOne
    @JoinColumn(name = "admin_id")
    @JsonIgnore
    private User admin;


    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "permission_id")
    @JsonIgnore
    private Permission permission ;

    @Transient
    private Set<Menu> menus=new HashSet<>();

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public User() {
    }

    public User(int id) {
        this.id = id;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getIsDelete() {
        return isDelete;
    }


}


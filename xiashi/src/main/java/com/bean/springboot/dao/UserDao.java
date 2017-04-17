package com.bean.springboot.dao;

import com.bean.springboot.dto.usermanagement.Menu;
import com.bean.springboot.dto.usermanagement.User;

import java.util.List;

/**
 * Created by bean on 2017/4/2.
 */
public interface UserDao {
    User getAllUser();

    /**
     * 通过用户名密码
     * @param account
     * @return
     */
    List<User> getUserByAcount(String account);
}

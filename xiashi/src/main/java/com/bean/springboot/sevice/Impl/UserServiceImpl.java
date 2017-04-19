package com.bean.springboot.sevice.Impl;

import com.bean.springboot.dao.UserDao;
import com.bean.springboot.dto.usermanagement.User;
import com.bean.springboot.sevice.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bean on 2017/4/2.
 */
@Service
public class UserServiceImpl implements UserSevice {

    @Autowired
    private UserDao userDao;
    @Override
    public User getAllUser() {
        return null;
    }

    /**
     * 通过用户名密码
     *
     * @param account
     * @return
     */
    @Override
    public List<User> getUserByAcount(String account) {
        return userDao.getUserByAcount(account);
    }
}

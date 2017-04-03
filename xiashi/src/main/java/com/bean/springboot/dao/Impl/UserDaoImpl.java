package com.bean.springboot.dao.Impl;

import com.bean.springboot.dao.UserDao;
import com.bean.springboot.dto.usermanagement.Menu;
import com.bean.springboot.dto.usermanagement.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by bean on 2017/4/2.
 */
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public User getAllUser() {
        return em.find(User.class,1);
    }

    /**
     * 通过用户名密码
     *
     * @param account
     * @return
     */
    @Override
    public List<User> getUserByAcount(String account) {
        return em.createQuery("select u from User u where u.accountName=:account",User.class)
                .setParameter("account",account).getResultList();
    }
}

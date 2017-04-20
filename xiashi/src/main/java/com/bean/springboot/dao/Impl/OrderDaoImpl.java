package com.bean.springboot.dao.Impl;

import com.bean.springboot.dao.OrderDao;
import com.bean.springboot.dto.order.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by bean on 2017/4/2.
 */
@Repository
public class OrderDaoImpl implements OrderDao {

    private ReentrantLock reentrantLock=new ReentrantLock();
    @PersistenceContext
    private EntityManager em;


    /**
     * 新建订单
     *
     * @param order
     * @return
     */
    @Override
    public void insertOrder(Order order) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date= sdf.format(new Date());
        reentrantLock.lock();
        StringBuilder num= new StringBuilder(em.createQuery("select count(o) from Order o where o.orderNo like CONCAT(:date,'%') ", Long.class)
                .setParameter("date", date)
                .getSingleResult().toString())
                ;
        int len = num.length();
        for(int i=len;i<4;i++){
            num.insert(0, "0");
        }
        order.setOrderNo(date+num);
        em.persist(order);
        reentrantLock.unlock();
    }
}

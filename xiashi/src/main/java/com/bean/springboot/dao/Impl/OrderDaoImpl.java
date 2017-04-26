package com.bean.springboot.dao.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bean.springboot.dao.OrderDao;
import com.bean.springboot.dto.order.Order;
import com.bean.springboot.type.StateType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by bean on 2017/4/2.
 */
@Repository
public class OrderDaoImpl implements OrderDao {

    private ReentrantLock reentrantLock = new ReentrantLock();
    private ReentrantLock reentrantLock1 = new ReentrantLock();
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
        String date = sdf.format(new Date());
        reentrantLock.lock();
        StringBuilder num = new StringBuilder(em.createQuery("select count(o) from Order o where o.orderNo like CONCAT(:date,'%') ", Long.class)
                .setParameter("date", date)
                .getSingleResult().toString());
        int len = num.length();
        for (int i = len; i < 4; i++) {
            num.insert(0, "0");
        }
        order.setOrderNo(date + num);
        em.persist(order);
        reentrantLock.unlock();
    }

    /**
     * 根据类型得到订单
     *
     * @param type
     * @return
     */
    @Override
    public List<Order> getOrderListByType(StateType type) {
        return em.createQuery("select o from Order o where o.state=:types and o.isDelete=0", Order.class)
                .setParameter("types", type)
                .getResultList();
    }

    /**
     * 根据id得到订单
     *
     * @param id
     * @return
     */
    @Override
    public Order getOrderById(Long id) {
        return em.find(Order.class, id);
    }

    /**
     * 根据id修改订单state
     *
     * @param id
     * @param stateType
     */
    @Override
    public void updateStateById(Long id, StateType stateType, StateType $state) {
        em.createQuery("update Order o set o.state=:stateType where o.id=:id and o.state=:state")
                .setParameter("id", id)
                .setParameter("stateType", stateType)
                .setParameter("state", $state)
                .executeUpdate();
    }

    /**
     * 质检订单
     *
     * @param order
     */
    @Override
    public void check(JSONObject order) {
        Long orderId = order.getLong("id");

        JSONArray orderInfos = order.getJSONArray("orderInfos");
        reentrantLock1.lock();
        Order o = em.find(Order.class, orderId);
        if(o.getState().equals(StateType.WAITCHECK)) {
            for (Object obj : orderInfos) {
                JSONObject oi = (JSONObject) obj;
                em.createQuery("update OrderInfo oi set oi.checkNum=:checkNum where oi.id=:id")
                        .setParameter("checkNum", oi.getInteger("checkNum"))
                        .setParameter("id", (long) oi.getLong("id"))
                        .executeUpdate();
                orderId = oi.getLong("order");
            }
            o.setState(StateType.CHECKED);
            em.flush();
        }
        reentrantLock1.unlock();
    }
    /**
     * 上传收货凭证图片
     *
     * @param id
     */
    @Override
    public void upPic(Long id, String picUrl) {
        em.createQuery("update Order o set o.picUrl=:picUrl ,o.state=:state where o.id=:id and o.state=:nstate")
                .setParameter("picUrl", picUrl)
                .setParameter("id", id)
                .setParameter("state",StateType.UPPIC)
                .setParameter("nstate",StateType.CHECKED)
                .executeUpdate();
    }
}

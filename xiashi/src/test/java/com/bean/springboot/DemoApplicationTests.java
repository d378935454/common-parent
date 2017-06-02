package com.bean.springboot;

import com.bean.springboot.dao.UserDao;
import com.bean.springboot.dto.order.Order;
import com.bean.springboot.dto.order.OrderInfo;
import com.bean.springboot.dto.usermanagement.Menu;
import com.bean.springboot.dto.usermanagement.User;
import com.bean.springboot.sevice.UserSevice;
import com.bean.springboot.utils.TreeUtil;
import com.bean.springboot.utils.redis.BaseRedisSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Inject
	UserSevice userSevice;
	@Inject
	BaseRedisSupport redis;

	@Inject
	EntityManager em;

	@Test
	public void contextLoads() {
		redis.set("a","asdasda",10);
		redis.get("a");
		userSevice.getAllUser();

	}
	@Test
	@Transactional
	public void aa(){
		OrderInfo order=em.find(OrderInfo.class,1L);
		System.out.println();
	}

}

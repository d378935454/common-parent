package com.bean.springboot;

import com.bean.springboot.dao.UserDao;
import com.bean.springboot.dto.usermanagement.Menu;
import com.bean.springboot.dto.usermanagement.User;
import com.bean.springboot.utils.TreeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	UserDao userDao;

	@Test
	public void contextLoads() {
		User user = userDao.getAllUser();
		Set<Menu> menus = TreeUtil.initMenus(user.getPermission().getMenus());
		String aa = "";
	}

}

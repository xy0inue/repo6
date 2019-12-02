package cn.xy.test;

import org.junit.Test;

import cn.xy.dao.UserDao;
import cn.xy.domain.User;

public class UserDaoTest {

	@Test
	public void testLogin() {
		User user = new User();
		user.setUsername("ss");
		user.setPassword("12131");

		UserDao userDao = new UserDao();
		User userQuery = userDao.login(user);
		System.out.println(userQuery);

	}
}

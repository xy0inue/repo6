package cn.xy.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import cn.xy.domain.User;
import cn.xy.util.JdbcUtils;

public class UserDao {
	private JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtils.getDataSource());

	String sql = "select * from user where username=? and password =?;";

	// 接收的User只有用户名和密码
	public User login(User loginUser) {
		try {
			User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),
					loginUser.getUsername(), loginUser.getPassword());
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}

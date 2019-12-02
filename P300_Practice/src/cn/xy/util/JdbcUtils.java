package cn.xy.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class JdbcUtils {
	private static DataSource ds;
	static {
		Properties properties = new Properties();
		InputStream resourceAsStream = JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");
		try {
			properties.load(resourceAsStream);

			ds = DruidDataSourceFactory.createDataSource(properties);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获得数据库连接池
	public static DataSource getDataSource() {
		return ds;
	}

	// 获得数据库连接对象
	public static Connection getConnection() throws Exception {
		return ds.getConnection();
	}
}

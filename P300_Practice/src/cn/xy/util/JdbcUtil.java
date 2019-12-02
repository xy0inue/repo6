package cn.xy.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JdbcUtil {
	private static String dbUrl;
	private static String dbUser;
	private static String dbPassWord;
	private static String dbDriver;

	// ==================1.静态代码块获取连接参数=================================
	// 读取资源文件，获取url,user,passWord,driver值;只需要读取一次即可拿到这些值。使用静态代码块
	static {
		// 1.创建Properties集合类
		Properties pro = new Properties();
		try {
			FileReader fileReader = new FileReader(
					JdbcUtil.class.getClassLoader().getResource("jdbc.properties").getPath());
			// -----------------上面读取配置文件实现----------------------------
			// == .class调用：getClassLoader（）；获取类加载器ClassLoader
			// ClassLoader classLoader = JdbcUtil.class.getClassLoader();

			// == ClassLoader 类加载器调用getResource()方法读取资源的[ URL 对象]；
			// 如果找不到该资源，或者调用者没有足够的权限获取该资源，则返回 null。
			// URL resource = classLoader.getResource("jdbc.properties");

			// == URL对象调用： public String getPath() :获取此 URL 的路径部分
			// String path
			// =JdbcUtil.class.getClassLoader().getResource("jdbc.properties").getPath();
			// -----------------------------------------------------

			// 2.加载文件
			pro.load(fileReader);

			// 3.获取数值，复制
			dbUrl = pro.getProperty("url");
			dbUser = pro.getProperty("user");
			dbPassWord = pro.getProperty("passWord");
			dbDriver = pro.getProperty("driver");
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ==================2.获取连接对象=========================================
	public static Connection getConnection() {
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(dbUrl, dbUser, dbPassWord);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	// ==================3.数据库增删改方法======================================
	// 方法参数:sql语句,以及sql中的占位符
	public static boolean dbDML(String sql, Object... objects) {
		Connection connection = getConnection();

		PreparedStatement ps = null;

		try {
			ps = connection.prepareStatement(sql);
			for (int i = 0; i < objects.length; i++) {
				ps.setObject(i + 1, objects[i]);
			}
			ps.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			close(connection, ps);
		}

	}
	// ==================4.数据库查询方法========================================

	public static <E> List<E> dbDQL(String sql, Class<E> class1, Object... objects) {
		System.out.println(sql);
		Connection connection = getConnection();

		PreparedStatement ps = null;
		ResultSet resultSet = null;
		List<E> list = new ArrayList<E>();

		try {
			ps = connection.prepareStatement(sql);
			// 传入参数,完善sql语句
			for (int i = 0; i < objects.length; i++) {
				ps.setObject(i + 1, objects[i]);
			}
			// 获得数据库中所有的列名称
			ResultSetMetaData data = ps.getMetaData();
			// 声明一个字符串数组,长度为数据库表中列的数量
			String[] names = new String[data.getColumnCount()];
			for (int i = 0; i < names.length; i++) {
				// 获得每一列的名称,然后把值赋值给字符串数组names,data的下标从1开始
				String name = data.getColumnLabel(i + 1);
				names[i] = name;
			}
			resultSet = ps.executeQuery();
			while (resultSet.next()) { // 循环行
				// 根据传入的class获取一个实例对象
				E o = class1.newInstance();
				// 循环数据库列名称 (数据库列名称与model属性名称保持一致)
				for (String string : names) {
					// 通过属性名获得属性值value
					Object value = resultSet.getObject(string);
					// 获得属性对象
					Field field = class1.getDeclaredField(string);
					// 公开属性权限
					field.setAccessible(true);
					// 给创建的实体类对象O赋值.
					field.set(o, value);
				}
				// 向结果结合中添加封装好的对象.
				list.add(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, ps, resultSet);
		}

		return list;
	}

	// ==================5.关流================================================

	// DQL调用的关流
	public static void close(Connection connection, PreparedStatement ps, ResultSet resultSet) {
		close(resultSet);
		close(ps);
		close(connection);
	}

	// DML时调用的关流
	public static void close(Connection connection, PreparedStatement ps) {
		close(ps);
		close(connection);
	}

	// ---------关流细节-----------------
	// 关闭数据库连接
	private static void close(Connection connection) {
		if (connection != null) {

			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 关闭执行语句对象流
	private static void close(PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	// 关闭ResultSet
	private static void close(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

}

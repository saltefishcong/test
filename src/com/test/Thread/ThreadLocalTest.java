package com.test.Thread;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class ThreadLocalTest {
	static pools pool = new pools();
	static ConnectionUtils conn = new ConnectionUtils(pool);
	static volatile ThreadLocal<Connection> connLocal = new ThreadLocal<Connection>() {
		@Override
		protected Connection initialValue() {
			// TODO Auto-generated method stub
			Connection connection = conn.getConnection();
			connLocal.set(connection);
			return connection;
		}
	};

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runnable runnable = () -> {
			Connection connection = connLocal.get();
			System.out.println(Thread.currentThread().getName() + " 获取了连接对象  " + connection);
		};
		for (int i = 0; i < 7; i++) {
			Thread thread = new Thread(runnable);
			thread.start();
		}
	}

}

class pools {
	static String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=true&useUnicode=true&characterEncoding=UTF-8";
	static String user = "root";
	static String password = "123456";
	static List<Connection> list = new ArrayList<Connection>();
	static volatile boolean[] falg;

	public pools() {
		creatConnection();
		falg = new boolean[list.size()];
	}

	public void creatConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			for (int i = 0; i < 5; i++) {
				Connection connection = DriverManager.getConnection(url, user, password);
				list.add(connection);
				System.out.print(connection + "   ");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

class ConnectionUtils {
	static pools pool;

	public ConnectionUtils(pools pool) {
		this.pool = pool;
	}

	public Connection getConnection() {
		for (int i = 0; i < 5; i++) {
			if (pool.falg[i] == false) {
				pool.falg[i] = true;
				System.out.println(Thread.currentThread().getName() + " 拿走了  " + pool.list.get(i));
				return pool.list.get(i);
			}
		}
		return null;
	}

}

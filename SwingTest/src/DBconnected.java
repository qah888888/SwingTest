import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBconnected {
	
	private static final String driver;
	private static final String url;
	private static final String username;
	private static final String password;
 
	static {
		driver = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://localhost:3307/zizuwang?serverTimezone=GMT%2B8";
		username = "root";
		password = "root";
	}

	
	/*
	 * 加载数据库的方法
	 */
 
	public static void locadClass() throws ClassNotFoundException {
 
		Class.forName(driver);
	}
	
	
	/*
	 * 获取数据库连接的方法
	 */
	public static Connection getConnection() throws Exception {
 
		Connection conn = DriverManager.getConnection(url, username, password);
 
		return conn;
	}

	
	/*
	 * 关闭连接并释放资源的方法
	 */
	public static void result(Connection conn, Statement stam) {
 
		if (conn != null) {
 
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = null;
		}
 
		if (stam != null) {
 
			try {
				stam.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stam = null;
		}
	}
	
	
	/*
	 * 关闭连接并释放资源的方法
	 */
	public static void result(Connection conn, Statement stam, ResultSet rs) {
 
		if (conn != null) {
 
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = null;
		}
 
		if (stam != null) {
 
			try {
				stam.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stam = null;
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rs = null;
		}
 
	}

}

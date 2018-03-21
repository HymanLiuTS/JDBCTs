
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCMain {

	static String JDBCDriver = "org.postgresql.Driver";
	static String DB_URL = "jdbc:postgresql://192.168.5.201:5433/yiibai";

	static String UserName = "postgres";
	static String PassWord = "123456";

	public static void main(String args[]) {
		Connection conn = null;
		Statement sttmt = null;
		try {

			// 步骤1，注册JDBC Driver
			// Class.forName(JDBCDriver);方法一
			// Class.forName(JDBCDriver).newInstance();// 方法二，使用getInstance()方法来解决不合规的JVM
			// 方法三，使用DriverManager.registerDriver进行注册
			Driver myDriver = new org.postgresql.Driver();
			DriverManager.registerDriver(myDriver);

			// 步骤2，打开一个连接
			System.out.println("DataBase begin connect...");
			// conn = DriverManager.getConnection(DB_URL, UserName,
			// PassWord);//方法一，使用带三个参数的getConnection创建数据库连接

			// 方法二，url中增加数据库和密码
			// DB_URL =
			// "jdbc:postgresql://192.168.5.201:5433/yiibai?user=postgres&password=123456";
			// conn = DriverManager.getConnection(DB_URL);

			//方法三，使用带两个参数的DriverManager.getConnection创建数据库连接
			Properties info = new Properties();
			info.put("user", "postgres");
			info.put("password", "123456");
			conn = DriverManager.getConnection(DB_URL, info);

			// 步骤3，执行一次查询
			System.out.println("Creating statement...");
			sttmt = conn.createStatement();
			String sql;
			sql = "select * from myuser";
			ResultSet rs = sttmt.executeQuery(sql);

			// 步骤四，获取查询到的数据
			while (rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				int mobile = rs.getInt("mobile");
				String createdate = rs.getString("created");

				System.out.println("ID: " + id);
				System.out.println("username: " + username);
				System.out.println("mobile: " + mobile);
				System.out.println("username: " + createdate);
			}

			rs.close();
			sttmt.close();
			sttmt = null;
			conn.close();
			conn = null;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (sttmt != null) {
					sttmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}

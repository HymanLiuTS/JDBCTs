
import java.sql.*;

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
			Class.forName(JDBCDriver);

			// 步骤2，打开一个连接
			System.out.println("DataBase begin connect...");
			conn = DriverManager.getConnection(DB_URL, UserName, PassWord);

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

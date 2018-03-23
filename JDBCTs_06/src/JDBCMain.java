
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
			conn.setAutoCommit(false);//默认情况下自动提交打开，这里我们关闭自动提交
			// 步骤3，执行一次查询
			System.out.println("Creating statement...");
			sttmt = conn.createStatement();
			String sql;
			sql = "insert into myuser (username,mobile,created) values ('jerry',1234536,'2018-03-22 15:45:02')";
			sttmt.executeUpdate(sql);
			
			Savepoint  savepoint1=conn.setSavepoint("Savepoint1");
			
			sql = "insert into myuser (username,mobile,created) values ('tom',123456,'2018-03-22 15:45:02')";
			sttmt.executeUpdate(sql);
			
			conn.rollback(savepoint1);
			conn.commit();
			sttmt.close();
			sttmt = null;
			conn.close();
			conn = null;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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

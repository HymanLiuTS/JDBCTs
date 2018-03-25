
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
			//Escape character:
			//d-datetime
			//t-time
			//ts-timestamp
			//sql="insert into myuser values(16,'jerry',123321,{ts '2018-03-25 14:43:18'})";
			//sttmt.execute(sql);
			//The word 'escape' declares the '\' is an escape character
			sql="select * from myuser where username like '\\%rry'{escape '\'}";
			
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
			
			//The keyword 'fn' declares it will call the system procedure in database;
			sql="select {fn length('Hello World')} from myuser where id=1;";
			
			rs = sttmt.executeQuery(sql);
			while (rs.next()) {
				int length = rs.getInt("length");
				System.out.println("length: " + length);
			}
			rs.close();
			
			//The keyword 'oj' declares it is a out-join SQL statement;
			sql="select * from {oj post left join  myuser on user_id=id where id=1}";
			rs=sttmt.executeQuery(sql);
			while (rs.next()) {
				String username = rs.getString("username");
				System.out.println("username: " + username);
				
				String title = rs.getString("title");
				System.out.println("title: " + title);
				
				String content = rs.getString("content");
				System.out.println("content: " + content);
			}
			
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

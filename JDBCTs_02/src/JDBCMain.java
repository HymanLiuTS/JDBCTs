
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

			// ����1��ע��JDBC Driver
			// Class.forName(JDBCDriver);����һ
			// Class.forName(JDBCDriver).newInstance();// ��������ʹ��getInstance()������������Ϲ��JVM
			// ��������ʹ��DriverManager.registerDriver����ע��
			Driver myDriver = new org.postgresql.Driver();
			DriverManager.registerDriver(myDriver);

			// ����2����һ������
			System.out.println("DataBase begin connect...");
			// conn = DriverManager.getConnection(DB_URL, UserName,
			// PassWord);//����һ��ʹ�ô�����������getConnection�������ݿ�����

			// ��������url���������ݿ������
			// DB_URL =
			// "jdbc:postgresql://192.168.5.201:5433/yiibai?user=postgres&password=123456";
			// conn = DriverManager.getConnection(DB_URL);

			//��������ʹ�ô�����������DriverManager.getConnection�������ݿ�����
			Properties info = new Properties();
			info.put("user", "postgres");
			info.put("password", "123456");
			conn = DriverManager.getConnection(DB_URL, info);

			// ����3��ִ��һ�β�ѯ
			System.out.println("Creating statement...");
			sttmt = conn.createStatement();
			String sql;
			sql = "select * from myuser";
			ResultSet rs = sttmt.executeQuery(sql);

			// �����ģ���ȡ��ѯ��������
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

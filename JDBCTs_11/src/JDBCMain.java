
import java.sql.*;

public class JDBCMain {

	static String JDBCDriver = "org.postgresql.Driver";
	static String DB_URL = "jdbc:postgresql://192.168.5.201:5433/";

	static String UserName = "postgres";
	static String PassWord = "123456";

	public static void main(String args[]) {
		Connection conn = null;
		Statement sttmt = null;
		try {

			Class.forName(JDBCDriver);

			System.out.println("DataBase begin connect...");
			conn = DriverManager.getConnection(DB_URL, UserName, PassWord);

			System.out.println("Creating statement...");
			sttmt = conn.createStatement();
			String sql;
			sql = "create database test";
			System.out.println("Creating database test...");
			sttmt.execute(sql);
			sql="create table employee(id serial,name character varying(64),deptno integer)";
			System.out.println("Creating table employee...");
			sttmt.execute(sql);
			sttmt.close();
			sttmt = null;
			conn.close();
			conn = null;

		} catch (Exception e) {
			e.printStackTrace();
			String sql = "drop database test";
			try {
				sttmt.execute(sql);
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

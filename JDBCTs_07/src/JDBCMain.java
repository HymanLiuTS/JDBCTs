
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCMain {

	static String JDBCDriver = "org.postgresql.Driver";
	static String DB_URL = "jdbc:postgresql://192.168.5.201:5433/yiibai";

	static String UserName = "postgres";
	static String PassWord = "123456";

	public static void main(String args[]) {
		Connection conn = null;
		Statement sttmt = null;
		try {
			// 1、Register the  Driver of JDBC
			Class.forName(JDBCDriver);

			System.out.println("DataBase begin connect...");
			//2、Get a Connection Object
			conn = DriverManager.getConnection(DB_URL, UserName, PassWord);
			conn.setAutoCommit(false);//Set the AutoCommiting closed;
			
			System.out.println("Creating statement...");
			//3、Create a Statement Object
			sttmt = conn.createStatement();
			
			String sql;
			sql = "insert into myuser (username,mobile,created) values ('jerry',1234536,'2018-03-22 15:45:02')";
			//4、Insert one SQL statement into the batch;
			sttmt.addBatch(sql);
			
			sql = "insert into myuser (username,mobile,created) values ('tom',123456,'2018-03-22 15:45:02')";
			//5、Insert another SQL statement into the batch;
			sttmt.addBatch(sql);
			
			//When you call the claerBatch,the batch will be clear.but it doesn't has any method to delete a member assigned
			//sttmt.clearBatch();
			
			//5、Create an int[] to hold the return value;
			//The element of the int[] is the number of database rows updated by the SQL statement in the batch;
			int[] count = sttmt.executeBatch();
			
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

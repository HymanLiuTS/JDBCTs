
import java.sql.*;

public class JDBCMain {

	static String JDBCDriver = "org.postgresql.Driver";
	static String DB_URL = "jdbc:postgresql://192.168.5.201:5433/yiibai";

	static String UserName = "postgres";
	static String PassWord = "123456";

	public static void main(String args[]) {
		Connection conn = null;
		CallableStatement callsttmt = null;
		try {

			Class.forName(JDBCDriver);

			System.out.println("DataBase begin connect...");
			conn = DriverManager.getConnection(DB_URL, UserName, PassWord);

			System.out.println("Creating statement...");
			// Create a CallableStatement Object to call the stored procedure.
			// The '?' is a placehoder that will be replaced by a parameter
			callsttmt = conn.prepareCall("{call myquery(?)}");
			// The first parameter of setInt is start with 1 not 0.
			callsttmt.setInt(1, 1);
			//The registerOutParameter sets the value of parameter which signed "out"
			//It's also start witch 1.
			callsttmt.registerOutParameter(1, Types.DOUBLE);
			callsttmt.execute();
			double count = callsttmt.getDouble(1);
			System.out.println(String.format("count=%f", count));
			callsttmt.close();
			callsttmt = null;
			conn.close();
			conn = null;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (callsttmt != null) {
					callsttmt.close();
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

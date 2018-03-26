
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCMain {

	static String JDBCDriver = "org.postgresql.Driver";
	static String DB_URL = "jdbc:postgresql://192.168.5.201:5433/yiibai";

	static String UserName = "postgres";
	static String PassWord = "123456";

	public static void main(String args[]) {
		Connection conn = null;
		PreparedStatement psttmt = null;
		Statement sttmt = null;
		try {
			Class.forName(JDBCDriver);

			System.out.println("DataBase begin connect...");
			conn = DriverManager.getConnection(DB_URL, UserName, PassWord);

			System.out.println("Creating statement...");
	
			sttmt = conn.createStatement();
			//Create the XML_DATA table;
			createXMLData(sttmt);
			//Load the xml file
			File f=new File("bin/xml_data.xml");
			long fileLength=f.length();
			FileInputStream fis=new FileInputStream (f);
			
			//Insert the xml file
			String sql="insert into XML_DATA values(?,?)";
			psttmt=conn.prepareStatement(sql);
			psttmt.setInt(1,118);
			//Provide the ASCII characters
			psttmt.setAsciiStream(2,fis,fileLength);
			//Privide the UNICODE characters
			//psttmt.setCharacterStream(..);
			//Provide the binary streams
			//psttmt.setBinaryStream(2,fis,fileLength);
			psttmt.execute();
			
			sql="select data from xml_data where id=118";
			ResultSet rs=psttmt.executeQuery(sql);
			while(rs.next()) {
				InputStream xmlInputStream = rs.getAsciiStream (1);
		         int c;
		         ByteArrayOutputStream bos = new ByteArrayOutputStream();
		         while (( c = xmlInputStream.read ()) != -1)
		            bos.write(c);
		         //Print results
		         System.out.println(bos.toString());
			}
			
			psttmt.close();
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

	private static void createXMLData(Statement stmt) {
		try {
			String sql = "create table XML_DATA(id serial,data bytea)";
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				stmt.executeUpdate("drop table XML_DATA");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}
}

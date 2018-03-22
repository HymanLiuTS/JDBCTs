
public class SqlDateTime {

	public static void main(String[] args) {

		java.util.Date javaDate = new java.util.Date();
		long javaTime = javaDate.getTime();
		System.out.println("The javaDate is:" + javaDate.toString());

		java.sql.Date sqlDate = new java.sql.Date(javaTime);
		System.out.println("The sqlDate is:" + sqlDate.toString());

		java.sql.Time sqlTime = new java.sql.Time(javaTime);
		System.out.println("The sqlTime is:" + sqlTime.toString());

		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(javaTime);
		System.out.println("The sqlTimestamp is:" + sqlTimestamp);
	}

}

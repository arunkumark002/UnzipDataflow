import java.sql.*;

public class JDBCJavaConnection {

	static final String DB_URL = "jdbc:sqlserver://ito18199.hosts.cloud.ford.com:1433;database=VDDSQA";
	   static final String USER = "prx_cvdpqa_vdds";
	   static final String PASS = "FtRi6wuv3oKn";
	   static final String QUERY = "select VDD064_TARGET_TYPE_C,VDD064_DATA_TYPE_C from NVDD064_ATTRIBUTE_DEFINITION limit 1";

	   public static void main(String[] args) {
	      // Open a connection
	      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(QUERY);) {
	         // Extract data from result set
	         while (rs.next()) {
	            // Retrieve by column name
//	            System.out.print("ID: " + rs.getInt("id"));
//	            System.out.print(", Age: " + rs.getInt("age"));
//	            System.out.print(", First: " + rs.getString("first"));
//	            System.out.println(", Last: " + rs.getString("last"));
	        	 System.out.println(rs);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } 
	   }
}

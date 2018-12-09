package mail;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnHelper {
	public static Connection GetDBConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/individual?useSSL=false", "user", "test");
	//		System.out.println("Connected");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
				return conn;

		
}
public static void CloseDBConnection (Connection conn) throws SQLException {
	conn.close();
}
}


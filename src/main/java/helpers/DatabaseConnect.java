package helpers;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnect {
	private static DatabaseConnect dc = null;
	Connection conn = null;
	String url, dbname, username, password, driver;
	private DatabaseConnect() {
		
	}
	public static DatabaseConnect createInstance() {
		if(dc == null)
			dc = new DatabaseConnect();
		return dc;
	}
	public Connection mySqlConnection(String url, String dbname, String username, String password, String driver) {
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+dbname,username,password);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return conn;
	}
}

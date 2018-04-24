package helpers;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnect {
	private static DatabaseConnect dc = null;
	Connection conn = null;
	String url="jdbc:mysql://localhost:3306/", dbname="project", username="root", password="1234", driver="com.mysql.jdbc.Driver";
	private DatabaseConnect() {
		
	}
	public static DatabaseConnect createInstance() {
		if(dc == null)
			dc = new DatabaseConnect();
		return dc;
	}
	public Connection mySqlConnection() {
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

package entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import helpers.DatabaseConnect;

public class Request {
	String rid,isbn,buid;

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getBuid() {
		return buid;
	}

	public void setBuid(String buid) {
		this.buid = buid;
	}
	
	public static boolean requestBook(Request r) {
		boolean b = false;
		try {			 	
				Connection conn = DatabaseConnect.createInstance().mySqlConnection();
			 	String query = "insert into request values(?,?,?)";
			 	PreparedStatement ps = conn.prepareStatement(query);
			 	ps.setString(1, "R1");
			 	ps.setString(2, r.getIsbn());
			 	ps.setString(3, r.getBuid());
			 	b = ps.execute();
			}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
}

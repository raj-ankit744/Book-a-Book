package entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import helpers.DatabaseConnect;

public class Order {
	String oid,buid;
	Post p;
	int status;
	Date date; 
	public Order(String oid, String buid, Post p, int status, Date date) {
		this.oid = oid;
		this.buid = buid;
		this.p = p;
		this.status = status;
		this.date = date;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getBuid() {
		return buid;
	}
	public Post getPost() {
		return p;
	}
	public Date getDate() {
		return date;
	}
	public void placeOrder() {
		try {
			Connection conn = DatabaseConnect.createInstance().mySqlConnection();
			String query = "insert into orders (oid, buid, pid, date, status)" 
					+ " values (?,?,?,?,?)";
			String sql  = "update postad set status = 0 where id = ?";
			if(conn == null)	
				return ;
			PreparedStatement ps = conn.prepareStatement(query);
			PreparedStatement ps1=conn.prepareStatement(sql);
			String id = "O"+this.p.getId().substring(1);
			this.setOid(id);
			ps1.setString(1,this.p.getId());
			ps.setString(1, this.oid);
			ps.setString(2, this.buid);
			ps.setString(3, this.p.getId());
			ps.setObject(4, this.date);
			ps.setInt(5, this.status);
			ps.execute();
			ps1.executeUpdate();
			conn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public static void requestOrder(String isbn, String uid) {
		String id = "R1";
		try {			 	
				Connection conn = DatabaseConnect.createInstance().mySqlConnection();
			 	String query = "insert into request values(?,?,?)";
			 	String sql = "select * from request order by rid desc";
			 	PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					long n = Long.parseLong(rs.getString("rid").substring(1,rs.getString("rid").length()))+1;
					id = "R"+n;
				}
			 	PreparedStatement ps1 = conn.prepareStatement(query);
			 	ps1.setString(1, id);
			 	ps1.setString(2, isbn);
			 	ps1.setString(3, uid);
			 	ps1.execute();
			 	conn.close();
			}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static ArrayList<String> getRequestId(String isbn) {
		ArrayList<String> ruid = new ArrayList<String>();
		try {			 	
			Connection conn = DatabaseConnect.createInstance().mySqlConnection();
		 	String query = "select * from request where isbn = ?";
		 	if(conn ==null)
		 		return null;
		 	PreparedStatement ps = conn.prepareStatement(query);
		 	ps.setString(1, isbn);
		 	ResultSet rs = ps.executeQuery();
		 	while(rs.next()) {
		 		String sql = "delete from request where rid = ?";
		 		PreparedStatement ps1 = conn.prepareStatement(sql);
		 		ps1.setString(1, rs.getString(1));
		 		ruid.add(rs.getString(3));
		 		ps1.executeUpdate();
		 	}
		 	conn.close();
		 	return ruid;
		}
	catch(SQLException e) {
		e.printStackTrace();
	}
		return null;
	}
	
	
}

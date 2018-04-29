package entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
		try {			 	
				Connection conn = DatabaseConnect.createInstance().mySqlConnection();
			 	String query = "insert into request values(?,?,?)";
			 	PreparedStatement ps = conn.prepareStatement(query);
			 	ps.setString(1, "R1");
			 	ps.setString(2, isbn);
			 	ps.setString(3, uid);
			 	ps.execute();
			}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}

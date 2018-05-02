package entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import entity.Post;
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
	
	public static void cancelOrder(String oid, String pid) {
		try {
			Connection conn = DatabaseConnect.createInstance().mySqlConnection();
			String query = "update postad set status = 1 where id = ?";
			String query1 = "update orders set status = 1 where id = ?";
			if(conn == null)
				return;
			PreparedStatement ps = conn.prepareStatement(query);
			PreparedStatement ps1 = conn.prepareStatement(query1);
			ps.setString(1, pid);
			ps1.setString(1, oid);
			ps.executeUpdate();
			ps1.executeUpdate();
			conn.close();
		}
		catch(Exception e) {
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
	 public static ArrayList<Order> getOrder(String buid) {
		 ArrayList<Order> ord = new ArrayList<Order>();
		 Order o;
		 Book b;
		 Post pt;
		 try {			 	
			 	Connection conn = DatabaseConnect.createInstance().mySqlConnection();
			 	String sql1 = "select * from orders where buid = ?";
			 	PreparedStatement st = conn.prepareStatement(sql1);
			 	st.setString(1,buid);
			 	String title, author, isbnTemp = "", uid, description, pidTemp, oid;
			 	int status2;
			 	Date date;
			 	boolean status;
			 	double price;
			 	ResultSet rs1 = st.executeQuery();
			 	while(rs1.next()) {		
			 		pidTemp = rs1.getString("pid");
			 		String sql2 = "select * from postad where id = ? and status = 0";
			 		PreparedStatement st1 = conn.prepareStatement(sql2);
			 		st1.setString(1, pidTemp);
			 		ResultSet rs2 = st1.executeQuery();
			 		if(rs2.next()) {
			 			isbnTemp = rs2.getString("isbn");
			 			String sql3 = "select * from book where isbn = ?";
			 			PreparedStatement st2 = conn.prepareStatement(sql3);
			 			st2.setString(1, isbnTemp);
			 			ResultSet rs3 = st2.executeQuery();
			 			if(rs3.next()) {
			 				author = rs3.getString("author");
			 				title = rs3.getString("title");
			 				b = new Book(isbnTemp, title, author);
			 			
			 			uid = rs2.getString("uid");
			 			description = rs2.getString("description");
			 			price = rs2.getDouble("price");
			 			status = rs2.getBoolean("status");
			 			pt = new Post(pidTemp, b, uid, description, price, status);
			 			oid = rs1.getString("oid");			 			
			 			status2 = rs1.getInt("status");
			 			date = rs1.getDate("date");
			 			o = new Order(oid, buid, pt, status2, date);
			 			ord.add(o);
			 			}
			 		}			 					 		
			 	}
		 }
		 catch (Exception e) {
			  e.printStackTrace();
		 }		 
		 return ord;
	 }
}

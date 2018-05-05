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
	public boolean placeOrder() {
		boolean b = false;
		try {
			Connection conn = DatabaseConnect.createInstance().mySqlConnection();
			String query = "insert into orders (oid, buid, pid, date, status)" 
					+ " values (?,?,?,?,?)";
			String sql  = "update postad set status = 0 where id = ?";
			if(conn == null)	
				return b;
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
			b = ps.execute();
			ps1.executeUpdate();
			conn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public static boolean cancelOrder(String oid, String pid) {
		boolean b = false;
		try {
			Connection conn = DatabaseConnect.createInstance().mySqlConnection();
			String query = "update postad set status = 1 where id = ?";
			String query1 = "update orders set status = 2 where oid = ?";
			if(conn == null)
				return b;
			PreparedStatement ps = conn.prepareStatement(query);
			PreparedStatement ps1 = conn.prepareStatement(query1);
			ps.setString(1, pid);
			ps1.setString(1, oid);
			ps.execute();
			b = ps1.execute();
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	public static boolean confirmOrder(String oid) {
		boolean b = false;
		try {
			Connection conn = DatabaseConnect.createInstance().mySqlConnection();
			String query1 = "update orders set status = 1 where oid = ?";
			if(conn == null)
				return b;
			PreparedStatement ps1 = conn.prepareStatement(query1);
			ps1.setString(1, oid);
			b = ps1.execute();
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public static boolean requestOrder(String isbn, String uid) {
		boolean b = false;
		String id = "R1";
		try {			 	
				Connection conn = DatabaseConnect.createInstance().mySqlConnection();
				if(conn == null) {
					return b;
				}
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
			 	b = ps1.execute();
			 	conn.close();
			}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public static ArrayList<Order> getOrderForSeller(String uid){
		ArrayList<Order> ord = new ArrayList<Order>();
		Order o;
		Book b;
		Post pt;
		try {
			Connection conn = DatabaseConnect.createInstance().mySqlConnection();
			String sql1 = "select * from postad where uid = ? and status = 0";
			PreparedStatement st = conn.prepareStatement(sql1);
			st.setString(1, uid);
			String title, author, isbn, buid, description, pid, oid, id ;
			Date date;
			int status2;
			boolean status;
			double price;
			ResultSet rs1 = st.executeQuery();
			while(rs1.next()) {
				id = rs1.getString("id");
				isbn = rs1.getString("isbn");
				String sql2 = "select * from orders where pid = ?";
				PreparedStatement st1 = conn.prepareStatement(sql2);
				st1.setString(1, id);
				ResultSet rs2 = st1.executeQuery();
				if(rs2.next()) {
					String sql3 = "select * from book where isbn = ?";
					PreparedStatement st2 = conn.prepareStatement(sql3);
					st2.setString(1, isbn);
					ResultSet rs3 = st2.executeQuery();
					if(rs3.next()) {
						title = rs3.getString("title");
						author = rs3.getString("author");
						b = new Book(isbn, title, author);
					
						buid = rs2.getString("buid");
						date = rs2.getDate("date");
						status2 = rs2.getInt("status");
						oid = rs2.getString("oid");
						
						description = rs1.getString("description");
						price = rs1.getDouble("price");
						status = rs1.getBoolean("status");
						pt = new Post(id, b, uid, description, price, status);
						o = new Order(oid, buid, pt,status2, date);
						ord.add(o);
					}
				}
			}			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return ord;
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
			 		String sql2 = "select * from postad where id = ?";
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

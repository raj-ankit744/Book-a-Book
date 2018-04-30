package entity;

import java.sql.*;
import java.util.*;

import helpers.DatabaseConnect;

public class Post {
	String id;
	Book b;
	String uid;
	String description;
	double price;
	boolean status;
	
	public Post(String id,Book b,String uid, String description, double price,boolean status){
		this.id = id;
		this.b = b;
		this.uid = uid;
		this.description = description;
		this.price = price;
		this.status = status;
	}

	public Post() {
		
	}
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Book getB() {
		return b;
	}

	public void setB(Book b) {
		this.b = b;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	 public void createPost() {
		 Connection conn = DatabaseConnect.createInstance().mySqlConnection();
		 if(conn == null)	
				return ;
		 String id="P1";
		 try {
			 String query = "select * from postad order by id desc";
			 PreparedStatement ps = conn.prepareStatement(query);
			 ResultSet rs = ps.executeQuery();
			 if(rs.next()) {
				 long n = Long.parseLong(rs.getString("id").substring(1,rs.getString("id").length()))+1;
				 id = "P"+n;
				 this.setId(id);
			 }
		 }catch(SQLException e) {
			 e.printStackTrace();
		 }
		 try {
				
				String query = "insert into postad (id, isbn, uid, description, price, status)" 
						+ " values (?,?,?,?,?,?)";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, this.getId());
				ps.setString(2, this.getB().getIsbn());
				ps.setString(3, this.getUid());
				ps.setString(4, this.getDescription());
				ps.setDouble(5, this.getPrice());
				ps.setBoolean(6, this.status);
				ps.execute();
				conn.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
	 }
	 public ArrayList<Post> getPost() {
		 ArrayList<Post> p = new ArrayList<Post>();	
		 Book b;
		 try {			 	
			 	Connection conn = DatabaseConnect.createInstance().mySqlConnection();
			 	String sql1 = "select * from postad where uid = ? and status = 1";
			 	PreparedStatement st = conn.prepareStatement(sql1);
			 	st.setString(1,this.uid);
			 	String title, author, isbnTemp = "";
			 	ResultSet rs1 = st.executeQuery();
			 	while(rs1.next()) {		
			 		isbnTemp = rs1.getString("isbn");
			 		String sql2 = "select * from book where isbn = ?";
			 		PreparedStatement st1 = conn.prepareStatement(sql2);
			 		st1.setString(1, isbnTemp);
			 		ResultSet rs2 = st1.executeQuery();
			 		if(rs2.next()) {
			 		author = rs2.getString("author");
			 		title = rs2.getString("title");
			 		b = new Book(isbnTemp, title, author);
			 		Post temp = new Post(rs1.getString("id"), b, this.uid, rs1.getString("description")
			 				, rs1.getDouble("price"), rs1.getBoolean("status"));
			 		p.add(temp);
			 		}
			 	}
		 }
		 catch (Exception e) {
			  e.printStackTrace();
		 }		 
		 return p;
	 }
	public static Post getPost(String id) {
		try {
			Connection conn = DatabaseConnect.createInstance().mySqlConnection();
			if(conn==null)
				return null;
		 	PreparedStatement ps;
		 	ResultSet rs;
		 	String sql = "select * from postad where id = ?";
		 	ps = conn.prepareStatement(sql);
		 	ps.setString(1, id);
		 	rs = ps.executeQuery();
		 	
		 	if(rs.next()) {
		 		
		 		String sql1 = "select * from book where isbn = ?";
		 		PreparedStatement ps1 = conn.prepareStatement(sql1);
		 		ps1.setString(1,rs.getString("isbn"));
		 		ResultSet rs1 = ps1.executeQuery();
		 		if(rs1.next()) {
		 			Book b = new Book(rs1.getString(1),rs1.getString(2),rs1.getString(3));
		 			
		 			return new Post(rs.getString(1),b,rs.getString(3),rs.getString(4),rs.getDouble(5),rs.getBoolean(6));
		 		}
		 	}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static ArrayList<Post> searchBook(String radio, String isbn, String title, String author) {
		// TODO Auto-generated method stub
		ArrayList<Post> res = new ArrayList<Post>();	
		 try {			 	
			 	Connection conn = DatabaseConnect.createInstance().mySqlConnection();
			 	PreparedStatement ps;
			 	ResultSet rs;
			 	String query = "select * from postad,book where postad.status = 1 and ";
			 	if(radio.equals("ISBN")) {
			 		query += "postad.isbn=? and postad.isbn=book.isbn";
			 		ps = conn.prepareStatement(query);
			 		ps.setString(1, isbn);
			 	}else if(radio.equals("Title")) {
			 		query += "book.title=? and postad.isbn=book.isbn";
			 		ps = conn.prepareStatement(query);
			 		ps.setString(1, title);
			 	}else {
			 		query += "book.author=? and postad.isbn=book.isbn";
			 		ps = conn.prepareStatement(query);
			 		ps.setString(1,author);
			 	}
			 	rs = ps.executeQuery();
			 	while(rs.next()) {
			 		Book b = new Book(rs.getString(7), rs.getString(8), rs.getString(9));
					Post p = new Post(rs.getString(1),b,rs.getString(3),rs.getString(4),rs.getDouble(5),rs.getBoolean(6));
					res.add(p);
			 	}
			 	
			 	
		 }catch(SQLException e) {
			 e.printStackTrace();
		 }
		return res;
	}

	public void modifyPost() {
		// TODO Auto-generated method stub
		 try {
				Connection conn = DatabaseConnect.createInstance().mySqlConnection();
				String query = "update postad set isbn=?, description=?, price=?, status=? where id=? and uid=?";
				if(conn == null)	
					return ;
				PreparedStatement ps = conn.prepareStatement(query);
				
				ps.setString(1, this.getB().getIsbn());
				ps.setString(2, this.getDescription());
				ps.setDouble(3, this.getPrice());
				ps.setBoolean(4, this.status);
				ps.setString(5, this.getId());
				ps.setString(6, this.getUid());
			
				ps.execute();
				
				conn.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
	}
}
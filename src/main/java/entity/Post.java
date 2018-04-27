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
	
	public Post(String id,Book b,String uid, String description, double price){
		this.id = id;
		this.b = b;
		this.uid = uid;
		this.description = description;
		this.price = price;
	}

	public Post() {
		
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
		 try {
				Connection conn = DatabaseConnect.createInstance().mySqlConnection();
				String query = "insert into postad (id, isbn, uid, description, price)" 
						+ " values (?,?,?,?,?)";
				
				if(conn == null)	
					return ;
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, this.getId());
				ps.setString(2, this.getB().getIsbn());
				ps.setString(3, this.getUid());
				ps.setString(4, this.getDescription());
				ps.setDouble(5, this.getPrice());
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
			 	Statement st = conn.createStatement();
			 	String sql1 = ("select * from postad where uid = " + this.uid);
			 	String title, author, isbnTemp = "";
			 	ResultSet rs1 = st.executeQuery(sql1);
			 	if(rs1.next()) {			 		
			 		isbnTemp = rs1.getString("isbn");
			 		String sql2 = ("select * from book where isbn = " + isbnTemp);
			 		ResultSet rs2 = st.executeQuery(sql2);
			 		author = rs2.getString("author");
			 		title = rs2.getString("title");
			 		b = new Book(isbnTemp, title, author);
			 		Post temp = new Post(rs1.getString("id"), b, this.uid, rs1.getString("description")
			 				, rs1.getDouble("price"));
			 		p.add(temp);
			 	}
		 }
		 catch (Exception e) {
			  e.printStackTrace();
		 }		 
		 return p;
	 }
}

package entity;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
	 public void save() {
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
}

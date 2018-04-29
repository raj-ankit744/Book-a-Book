package entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import helpers.DatabaseConnect;
import java.util.ArrayList;

public class User {
	String uid, password, name, type, address, contact;
	ArrayList<Order> order = new ArrayList<>();
	public User(String uid, String password, String name, String type, String address, String contact,ArrayList<Order> order) {
		this.uid = uid;
		this.password =  password;
		this.name = name;
		this.type = type;
		this.address = address;
		this.contact = contact;
		this.order = order;
	}

	public void register() { 
		try {
			Connection conn = DatabaseConnect.createInstance().mySqlConnection();
			String query = "insert into user (uid, password, name, usertype, contact, address)" 
					+ " values (?,?,?,?,?,?)";
			
			if(conn == null)	
				return ;
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, this.uid);
			ps.setString(2, this.password);
			ps.setString(3, this.name);
			ps.setString(4, this.type);
			ps.setString(5, this.contact);
			ps.setString(6, this.address);
			ps.execute();
			conn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static User getUser(String id) {
		try {
			Connection conn = DatabaseConnect.createInstance().mySqlConnection();
			String query = "Select * from user where uid = ?"; 
			
			if(conn == null)	
				return null;
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1,id);
			ResultSet rs = ps.executeQuery();
			if(!rs.next())	
				return null;
			User u = new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),new ArrayList<Order>());
			rs.close();
			conn.close();
			return u;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean verifyuser() {
		try {
			Connection conn = DatabaseConnect.createInstance().mySqlConnection();
			String query = "Select * from user where uid = ?"; 
			
			if(conn == null)	
				return false;
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1,this.uid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
			if(rs.getString("password").equals(this.password) && rs.getString("usertype").equals(this.type))
				return true;
			}
			rs.close();
			conn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public String getUid() {
		return uid;
	}
	public String getPassword() {
		return password;
	}
	public String getType() {
		return type;
	}
	public String getAddress() {
		return address;
	}
	public String getContact() {
		return contact;
	}

}

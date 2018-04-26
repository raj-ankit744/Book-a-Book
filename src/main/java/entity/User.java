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
	public String verifyuser() {
		try {
			Connection conn = DatabaseConnect.createInstance().mySqlConnection();
			String query = "Select * from user where uid = ?"; 
			
			if(conn == null)	
				return "Invalid";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1,this.uid);
			ResultSet rs = ps.executeQuery();
			if(!rs.next())	
				return "Invalid";
			if(rs.getString(2).equals(this.password))
				return rs.getString(4);
			rs.close();
			conn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "Invalid";
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

package entity;

import java.util.ArrayList;

public class User {
	String uid, password, type, address;
	long contact;
	ArrayList<Order> order = new ArrayList<>();
	public User(String uid, String password, String type, String address, long contact,ArrayList<Order> order) {
		this.uid = uid;
		this.password =  password;
		this.type = type;
		this.address = address;
		this.contact = contact;
		this.order = order;
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
	public long getContact() {
		return contact;
	}
}

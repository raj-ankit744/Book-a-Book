package model;
import entity.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entity.User;
import helpers.DatabaseConnect;
public class UserManager {

	public void createUser(String uid, String password,String name, String usertype, String address, String contact) {
		User u = new User(uid,password,name,usertype,address,contact,new ArrayList<Order>());
		u.register();
	}
	public boolean verifyuser(String uid, String password,String usertype) {
		User u = new User(uid,password,"",usertype,"","", new ArrayList<Order>());
		return u.verifyuser();
	}
}

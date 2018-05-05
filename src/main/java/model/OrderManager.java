package model;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import entity.Order;
import entity.Post;

public class OrderManager {
	NotificationManager nm = new NotificationManager();
	public boolean placeOrder(Post p, String buid) {
		String oid = "O" + p.getId().substring(1);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		Order o = new Order(oid,buid,p,0,date);
		boolean b = o.placeOrder();
		return b;
	}
	public boolean requestOrder(String isbn, String uid) {
		// TODO Auto-generated method stub
		boolean b = Order.requestOrder(isbn, uid);
		return b;
	}	
	public ArrayList<Order> getOrder(String buid){
		return Order.getOrder(buid);
	}
	public boolean cancelOrder(String oid, String pid) {
		boolean b = Order.cancelOrder(oid, pid);
		return b;
	}
	public boolean confirmOrder(String oid) {
		boolean b = Order.confirmOrder(oid);
		return b;
	}
	public ArrayList<Order> getOrderForSeller(String uid) {
		return Order.getOrderForSeller(uid);
	}
}

package model;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import entity.Order;
import entity.Post;

public class OrderManager {
	NotificationManager nm = new NotificationManager();
	public void placeOrder(Post p, String buid) {
		String oid = "O" + p.getId().substring(1);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		Order o = new Order(oid,buid,p,0,date);
		o.placeOrder();
	}
	public void requestOrder(String isbn, String uid) {
		// TODO Auto-generated method stub
		Order.requestOrder(isbn, uid);
	}	
	public ArrayList<Order> getOrder(String buid){
		return Order.getOrder(buid);
	}
	public void cancelOrder(String oid, String pid) {
		Order.cancelOrder(oid, pid);
	}
	public ArrayList<Order> getOrderForSeller(String uid) {
		return Order.getOrderForSeller(uid);
	}
}

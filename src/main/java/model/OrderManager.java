package model;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import entity.Order;
import entity.Post;

public class OrderManager {
<<<<<<< HEAD

=======
	public void placeOrder(Post p, String buid) {
		String oid = "O" + p.getId().substring(1);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		Order o = new Order(oid,buid,p,0,date);
		o.placeOrder();
	}
>>>>>>> 8ba340999f8667e109a0b07250bdfc923a36cff7
}

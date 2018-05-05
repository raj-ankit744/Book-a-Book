package model;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import entity.Book;
import entity.Order;
import entity.Post;

public class OrderManagerTest {
	OrderManager om = new OrderManager();
	@Test
	public void testPlaceOrder() {
		Book b= new Book("123456", "abc", "xyz");
		Post p = new Post("P4", b, "test@gmail.com", "Book is 2 years old", 180, true);
		boolean actual = om.placeOrder(p, "testbuyer@gmail.com");
		assertEquals(false, actual);
	}

	@Test
	public void testRequestOrder() {
		boolean b = om.requestOrder("123", "testbuyer@gmail.com");
		assertEquals(false, b);
	}

	@Test
	public void testGetOrder() {
		ArrayList<Order> order = om.getOrder("testbuyer@gmail.com");
		assertEquals("O3", order.get(1).getOid());
		assertEquals("testbuyer@gmail.com", order.get(1).getBuid());
		assertEquals(2, order.get(1).getStatus());
		assertEquals("123456", order.get(1).getPost().getB().getIsbn());
		assertEquals("abc", order.get(1).getPost().getB().getTitle());
		assertEquals("xyz", order.get(1).getPost().getB().getAuthor());
		assertEquals("P3", order.get(1).getPost().getId());
		assertEquals("test@gmail.com", order.get(1).getPost().getUid());
		assertEquals("Book is 2 years old", order.get(1).getPost().getDescription());
		assertEquals(180, order.get(1).getPost().getPrice(),1);
		assertEquals(true, order.get(1).getPost().isStatus());
		Date myDate = parseDate("2018-05-06");
		assertEquals(myDate, order.get(0).getDate());
	}
	
	public static Date parseDate(String date) {
	     try {
	         return new SimpleDateFormat("yyyy-MM-dd").parse(date);
	     } catch (ParseException e) {
	         return null;
	     }
	  }

	@Test
	public void testCancelOrder() {
		boolean b = om.cancelOrder("O3", "P3");
		assertEquals(false, b);
	}

	@Test
	public void testConfirmOrder() {
		boolean b = om.confirmOrder("O3");
		assertEquals(false,  b);
	}

	@Test
	public void testGetOrderForSeller() {
		ArrayList<Order> order = om.getOrderForSeller("test@gmail.com");
		assertEquals("O4", order.get(0).getOid());
		assertEquals("testbuyer@gmail.com", order.get(0).getBuid());
		assertEquals(0, order.get(0).getStatus());
		assertEquals("123456", order.get(0).getPost().getB().getIsbn());
		assertEquals("abc", order.get(0).getPost().getB().getTitle());
		assertEquals("xyz", order.get(0).getPost().getB().getAuthor());
		assertEquals("P4", order.get(0).getPost().getId());
		assertEquals("test@gmail.com", order.get(0).getPost().getUid());
		assertEquals("Book is 2 years old", order.get(0).getPost().getDescription());
		assertEquals(180, order.get(0).getPost().getPrice(),1);
		assertEquals(false, order.get(0).getPost().isStatus());
		Date myDate = parseDate("2018-05-06");
		assertEquals(myDate, order.get(0).getDate());
	}

}

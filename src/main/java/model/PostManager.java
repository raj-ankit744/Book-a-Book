package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import entity.Book;
import entity.Post;
import java.util.*;
import helpers.DatabaseConnect;

public class PostManager {

	public boolean createPost(String isbn, String title, String author, String uid, String description, double price,
			boolean status) {
		// TODO Auto-generated method stub
		Book b = new Book(isbn,title,author);
		boolean c = b.createBook();
		Post p = new Post("P1",b,uid,description,price,status);
		boolean d = p.createPost();
		if(d == false || c == false)
			return d;
		else
			return true;
	}
	
	public ArrayList<Post> getPost(String uid) {		
		Post p = new Post();
		p.setUid(uid);
		return p.getPost();
	}

	public boolean modifyPost(String id, String isbn, String title, String author, String uid, String description,  double price,
			boolean b) {
		// TODO Auto-generated method stub
		Book book = new Book(isbn,title,author);
		boolean c = book.modifyBook();
		Post p = new Post(id,book,uid,description,price,b);
		boolean d = p.modifyPost();
		if(d == false || c == false)
			return d;
		else
			return true;
	}	
}
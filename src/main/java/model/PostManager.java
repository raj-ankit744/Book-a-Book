package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import entity.Book;
import entity.Post;
import java.util.*;
import helpers.DatabaseConnect;

public class PostManager {

	public void createPost(String pid,String isbn,String title,String author,
			String uid,String description,double price, Boolean status) {
		Book b = new Book(isbn,title,author);
		b.createBook();
		Post p = new Post(pid,b,uid,description,price,status);
		p.createPost();
	}
	
	public ArrayList<Post> getPost(String uid) {		
		Post p = new Post();
		p.setUid(uid);
		return p.getPost();
	}

	public void modifyPost(String pid, String misbn, String mtitle, String mauthor, String uid, String mdescription,
			double mprice, boolean b) {
		// TODO Auto-generated method stub
		Book book = new Book(misbn,mtitle,mauthor);
		book.createBook();
		Post p = new Post(pid,book,uid,mdescription,mprice,b);
		p.modifyPost();
	}
	
	
}
package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import entity.Book;
import entity.Post;
import java.util.*;
import helpers.DatabaseConnect;

public class PostManager {

	public void createPost(String pid,String isbn,String title,String author,
			String uid,String description,double price) {
		Book b = new Book(isbn,title,author);
		Post p = new Post(pid,b,"12",description,price);
		p.createPost();
	}
	
	public ArrayList<Post> getPost(String uid) {		
		Post p = new Post();
		p.setUid(uid);
		return p.getPost();
	}
}
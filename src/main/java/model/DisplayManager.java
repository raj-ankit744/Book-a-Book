package model;

import java.sql.ResultSet;
import java.util.ArrayList;

import entity.Post;
import entity.Request;

public class DisplayManager {

	public ArrayList<Post> searchBook(String radio, String isbn, String title, String author) {
		// TODO Auto-generated method stub
		ArrayList<Post> rs = Post.searchBook(radio, isbn, title, author);
		return rs;
	}

	public boolean requestBook(String isbn, String uid) {
		// TODO Auto-generated method stub
		Request r = new Request();
		r.setIsbn(isbn);
		r.setBuid(uid);
		boolean b = (boolean) Request.requestBook(r);
		return b;
	}	
}

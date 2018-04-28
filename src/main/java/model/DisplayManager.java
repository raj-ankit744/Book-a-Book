package model;

import java.sql.ResultSet;
import java.util.ArrayList;

import entity.Post;

public class DisplayManager {

	public ArrayList<Post> searchBook(String radio, String isbn, String title, String author) {
		// TODO Auto-generated method stub
		ArrayList<Post> rs = Post.searchBook(radio, isbn, title, author);
		return rs;
	}	
}

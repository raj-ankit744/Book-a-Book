package model;

import java.sql.ResultSet;
import entity.Post;

public class DisplayManager {

	public ResultSet searchBook(String radio, String isbn, String title, String author) {
		// TODO Auto-generated method stub
		ResultSet rs = Post.searchBook(radio, isbn, title, author);
		return rs;
	}	
}

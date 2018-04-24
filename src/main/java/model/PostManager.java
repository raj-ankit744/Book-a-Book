package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import entity.Book;
import entity.Post;
import helpers.DatabaseConnect;

public class PostManager {

	public void savePost(Post p) {
		
		// create post object
		// post.save();
		
		try {
			Connection conn = DatabaseConnect.createInstance().mySqlConnection();
			String query = "insert into postad (id, isbn, uid, description, price)" 
					+ " values (?,?,?,?,?)";
			
			if(conn == null)	
				return ;
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, p.getId());
			ps.setString(2, p.getB().getIsbn());
			ps.setString(3, p.getUid());
			ps.setString(4, p.getDescription());
			ps.setDouble(5, p.getPrice());
			ps.execute();
			conn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
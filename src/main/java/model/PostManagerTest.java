package model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import entity.Book;
import entity.Post;

public class PostManagerTest {
	
	PostManager pm = new PostManager();
	@Test
	public void testCreatePost() {
		
		boolean b = pm.createPost("123456","abc","xyz","test@gmail.com","Book is 2 years old",180,true);
		assertEquals(false, b);
		//fail("Not yet implemented");
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGetPost() {
		ArrayList<Post> post = pm.getPost("test@gmail.com");
		assertEquals("123456", post.get(0).getB().getIsbn());
		assertEquals("abc", post.get(0).getB().getTitle());
		assertEquals("xyz", post.get(0).getB().getAuthor());
		assertEquals("P1", post.get(0).getId());
		assertEquals("test@gmail.com", post.get(0).getUid());
		assertEquals("Book is 2 years old", post.get(0).getDescription());
		assertEquals(180, post.get(0).getPrice(),1);
		assertEquals(true, post.get(0).isStatus());
		//fail("Not yet implemented");
	}

	@Test
	public void testModifyPost() {
		boolean b = pm.modifyPost("P1", "123456", "abc", "xyz", "test@gmail.com", "Book is 2 years old", 180, true);
		assertEquals(false, b);
		//fail("Not yet implemented");
	}

}

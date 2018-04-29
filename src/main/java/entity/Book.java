package entity;

import java.sql.Connection;
import java.sql.PreparedStatement;

import helpers.DatabaseConnect;

public class Book {
	String isbn, title , author;
	public Book(String isbn, String title, String author) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void createBook() {
		try {
			Connection conn = DatabaseConnect.createInstance().mySqlConnection();
			if(conn == null)
				return;
			String query = "insert into book values(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, this.isbn);
			ps.setString(2, this.title);
			ps.setString(3, this.author);
			ps.execute();
			conn.close();
			
		}
		catch(Exception e ) {
			e.printStackTrace();
		}
		
	}
}

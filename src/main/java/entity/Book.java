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
	public boolean createBook() {
		boolean b = false;
		try {
			Connection conn = DatabaseConnect.createInstance().mySqlConnection();
			if(conn == null)
				return b;
			String query = "insert into book values(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, this.isbn);
			ps.setString(2, this.title);
			ps.setString(3, this.author);
			b = ps.execute();
			conn.close();
			
		}
		catch(Exception e ) {
			e.printStackTrace();
		}
		return b;
	}
	public boolean modifyBook() {
		boolean b = false;
		try {
			Connection conn = DatabaseConnect.createInstance().mySqlConnection();
			if(conn == null)
				return b;
			String query = "update book set title=?,author=? where isbn=?";
			PreparedStatement ps1 = conn.prepareStatement(query);
			ps1.setString(1, this.getTitle());
			ps1.setString(2, this.getAuthor());
			ps1.setString(3, this.getIsbn());
			b = ps1.execute();
			conn.close();
		}catch(Exception e ) {
			e.printStackTrace();
		}
		return b;
	}
}

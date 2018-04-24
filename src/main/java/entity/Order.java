package entity;

import java.util.Date;

public class Order {
	String id,suid,buid,isbn;
	Boolean status;
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public String getSuid() {
		return suid;
	}
	public String getBuid() {
		return buid;
	}
	public String getIsbn() {
		return isbn;
	}
	public Date getDate() {
		return date;
	}
	Date date; 
	
}

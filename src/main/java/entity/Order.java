package entity;

import java.util.Date;

public class Order {
	String oid,suid,buid,isbn;
	Boolean status;
	Date date; 
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getOid() {
		return oid;
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
	
	
}

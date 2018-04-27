package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import helpers.DatabaseConnect;

public class SomeDailyJob implements Runnable {
	final long year = 31556952000L;
	@Override
	public void run() {
			try {
				Connection conn = DatabaseConnect.createInstance().mySqlConnection();
				if(conn == null)	
					return ;
				String query = "select * from orders";
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs =  ps.executeQuery();
				while(rs.next()) {
					java.sql.Date t = (java.sql.Date) rs.getObject(5);
					
					Date t1 = new Date(); 
					Date t2 = new Date(t.getTime());
					Long no_of_days =  t1.getTime() - t2.getTime();
					if(no_of_days>=31556952000L) {
						String qry = "insert into archives (oid,suid,buid,isbn,date) values(?,?,?,?,?)";
						String qr1 = "Delete from orders where oid=?";
						PreparedStatement ps1 = conn.prepareStatement(qry);
						PreparedStatement ps2 = conn.prepareStatement(qr1);
						ps2.setString(1,rs.getString(1));
						ps1.setString(1, rs.getString(1));
						ps1.setString(2, rs.getString(2));
						ps1.setString(3, rs.getString(3));
						ps1.setString(4, rs.getString(4));
						ps1.setObject(5, rs.getObject(5));
						ps1.execute();			
						ps2.executeUpdate();
					}
				}
				rs.close();
				conn.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
	}

}

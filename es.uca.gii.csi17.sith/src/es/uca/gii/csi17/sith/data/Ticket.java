package es.uca.gii.csi17.sith.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Ticket 
{
	private int _iId;
	private int _iPrecio;
	
	
	public Ticket(int iId) throws Exception
	{
		Connection con = null;
	    ResultSet rs = null;
	    try
	    {
	    	System.out.println("id: " + iId);
	    	con = Data.Connection();
	 		rs = con.createStatement().executeQuery("SELECT * FROM ticket where id "
	 				+ "="+iId+";");
	 		rs.next();
	 		try {
		 		_iId = rs.getInt("id");
		 		_iPrecio = rs.getInt("precio");
	 		}
	 		catch(Exception e) {
	 			_iId = -1;
	 			_iPrecio = -1;
	 		}
	    }
	    catch (SQLException ee) { throw ee; }
		finally
		{
			if (rs != null) rs.close();
	 	    if (con != null) con.close();
		}
	}
	

	public Ticket create(int iPrecio) throws Exception
	{
		Connection con = null;
	    ResultSet rs = null;
	    int iId = -1;
	    try
	    {
	 		con = Data.Connection();
	 		con.createStatement().executeUpdate("INSERT INTO ticket(precio) VALUES ("
	 		+iPrecio+");");
	 		iId = Data.LastId(con);
	    }
	    catch (SQLException ee) { throw ee; }
		finally
		{
			if (rs != null) rs.close();
	 	    if (con != null) con.close();
		}
	    
	    Ticket ticket = new Ticket(iId);
	    return ticket;
	}
	
	public int getId(){return _iId;}
	
	public int getPrecio(){return _iPrecio;}
	
	public void setPrecio(int iPrecio){_iPrecio = iPrecio;}
	
	public String toString()
	{
		return super.toString()+":"+Integer.toString(_iId)+":"+Integer.toString(_iPrecio);
	}
}


package es.uca.gii.csi17.sith.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Cliente 
{
	private int _iId;
	private String _sNombre;
	
	public Cliente() {}
	
	public Cliente(int iId) throws Exception
	{
		Connection con = null;
	    ResultSet rs = null;
	    try
	    {
	 		con = Data.Connection();
	 		rs = con.createStatement().executeQuery("SELECT * FROM cliente where id "
	 				+ "="+iId+";");
	 		rs.next();
	 		_iId = rs.getInt("id");
	 		_sNombre = rs.getString("nombre");
	    }
	    catch (SQLException ee) { throw ee; }
		finally
		{
			if (rs != null) rs.close();
	 	    if (con != null) con.close();
		}
	}
	
	public static Cliente create(String sNombre) throws Exception
	{
		Connection con = null;
	    ResultSet rs = null;
	    int iId = -1;
	    try
	    {
	 		con = Data.Connection();

	 		System.out.println("INSERT INTO cliente(nombre) VALUES ("
	 		 		+sNombre+");");
	 		con.createStatement().executeUpdate("INSERT INTO cliente(nombre) VALUES ("
	 		+Data.String2Sql(sNombre, true, false)+");");
	 		iId = Data.LastId(con);
	    }
	    catch (SQLException ee) { throw ee; }
		finally
		{
			if (rs != null) rs.close();
	 	    if (con != null) con.close();
		}
	    
	    Cliente cliente = new Cliente(iId);
	    return cliente;
	}
	
	public int getId(){return _iId;}
	
	public String getNombre(){return _sNombre;}
	
	public void setNombre(String sNombre){_sNombre = sNombre;}
	
	public String toString()
	{
		return super.toString()+":"+Integer.toString(_iId)+":"+_sNombre;
	}
}

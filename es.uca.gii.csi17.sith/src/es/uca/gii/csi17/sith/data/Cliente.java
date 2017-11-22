package es.uca.gii.csi17.sith.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Cliente 
{
	private int _iId;
	private String _sNombre;
	private boolean _bIsDeleted;
	
	public int getId() { return _iId; }
	
	public String getNombre() { return _sNombre; }
	
	public boolean getIsDeleted() { return _bIsDeleted; }
	
	public void setNombre(String sNombre) { _sNombre = sNombre; }
	
	/**
	 * Función que devuelve una instancia de la base de datos en tipo String.
	 * @return Instancia de la base de datos en tipo String.
	 */
	public String toString()
	{
		return super.toString() + ":" + _iId + ":" + _sNombre;
	}
	
	/**
	 * @author Javier Barroso Canto, Jorge Gutiérrez Vila
	 * @param iId Identificador del cliente
	 * @return Entidad de la clase Cliente
	 */
	public Cliente(int iId) throws Exception
	{
		Connection con = null;
	    ResultSet rs = null;
	    try
	    {
	 		con = Data.Connection();
	 		rs = con.createStatement().executeQuery("SELECT id, nombre FROM cliente where "
	 				+ "id "	+ "=" + iId + ";");
	 		rs.next();
	 		_iId = iId;
	 		_sNombre = rs.getString("nombre");
	    }
	    catch (SQLException ee) { throw ee; }
		finally
		{
			if (rs != null) rs.close();
	 	    if (con != null) con.close();
		}
	}
	
	/**
	 * Función que crea una entidad de la clase Cliente a partir de un nombre de una
	 * instancia en la base de datos.
	 * @param sNombre Nombre del cliente en la base de datos.
	 * @return Cliente creado a partir de una instancia de la base de datos.
	 */
	public static Cliente create(String sNombre) throws Exception
	{
		Connection con = null;
	    ResultSet rs = null;
	    try
	    {
	 		con = Data.Connection();
	 		con.createStatement().executeUpdate("INSERT INTO cliente(nombre) VALUES ("
	 				+ Data.String2Sql(sNombre, true, false) + ");");
	 		
	 		return new Cliente(Data.LastId(con));
	    }
	    catch (SQLException ee) { throw ee; }
		finally
		{
			if (rs != null) rs.close();
	 	    if (con != null) con.close();
		}
	}
	
	private static String where(Integer iId, String sNombre) {
		String sWhere = "";
		
		if(iId != null)
			sWhere = sWhere + " id = " + iId + " and ";
		
		if(sNombre != null)
			sWhere = sWhere + " nombre = " + Data.String2Sql(sNombre, true, false)
				+ " and ";
		
		if(sWhere!="")
			return "where " + sWhere.substring(0, sWhere.length()-4); 
		else
			return "";
	}
	
	public void Delete() throws Exception {
		
		if (_bIsDeleted)
			throw new Exception("Entidad ya eliminada");
			
		Connection con = null;
		ResultSet rs = null;
	    try
	    {
	 		con = Data.Connection();
	 		con.createStatement().executeUpdate("DELETE FROM cliente " + 
	 				where(_iId, null));
	 		System.out.println("DELETE FROM cliente " + 
	 				where(_iId, null));
	 		_bIsDeleted = true;
	    }
	    catch (SQLException ee) { throw ee; }
		finally
		{
			if (rs != null) rs.close();
	 	    if (con != null) con.close();
		}
	}
	
	public void Update() throws Exception {
		
		if (_bIsDeleted)
			throw new Exception("Entidad ya eliminada");
			
		Connection con = null;
		ResultSet rs = null;
	    try
	    {
	 		con = Data.Connection();
	 		con.createStatement().executeUpdate("UPDATE cliente SET nombre = " 
	 				+ Data.String2Sql(_sNombre, true, false) + " " + where(_iId, null) + ";");
	 	}
	    catch (SQLException ee) { throw ee; }
		finally
		{
			if (rs != null) rs.close();
	 	    if (con != null) con.close();
		}
	}
	
	public List<Cliente> Select(int iId, String sNombre) throws Exception {
		
		if (_bIsDeleted)
			throw new Exception("Entidad ya eliminada");
		
		List<Cliente> list = new LinkedList<Cliente>();
		Connection con = null;
		ResultSet rs = null;
	    try
	    {
	 		con = Data.Connection();
	 		rs = con.createStatement().executeQuery("SELECT id FROM cliente " 
	 				+ where(iId, sNombre));

	 		while(rs.next()) 
	 			list.add(new Cliente(rs.getInt("id")));
	 		
	 		return list;
	    }
	    catch (SQLException ee) { throw ee; }
		finally
		{
			if (rs != null) rs.close();
	 	    if (con != null) con.close();
		}
	}
}

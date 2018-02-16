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
	private Raza _raza;
	
	public int getId() { return _iId; }
	
	public String getNombre() { return _sNombre; }
	
	public boolean getIsDeleted() { return _bIsDeleted; }
	
	public void setNombre(String sNombre) { _sNombre = sNombre; }
	
	public void setRaza(Raza raza) {_raza = raza;}
	
	public Raza getRaza() { return _raza;}
	
	/**
	 * Función que devuelve una instancia de la base de datos en tipo String.
	 * @return Instancia de la base de datos en tipo String.
	 */
	public String toString()
	{
		return super.toString() + ":" + _iId + ":" + _sNombre + ":" + _raza.getId();
	}
	
	/**
	 * @author Javier Barroso Canto, Jorge Gutiérrez Vila
	 * @param iId Identificador del cliente
	 * @return Entidad de la clase Cliente
	 */
	public Cliente(Integer iId) throws Exception
	{
		Connection con = null;
	    ResultSet rs = null;
	    try
	    {
	 		con = Data.Connection();
	 		rs = con.createStatement().executeQuery("SELECT id, nombre, id_Raza FROM cliente "
	 				+ "where id " + "=" + iId + ";");
	 		rs.next();
	 		_iId = iId;
	 		_sNombre = rs.getString("nombre");
	 		_raza = new Raza(rs.getInt("id_Raza"));
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
	public static Cliente create(String sNombre, Raza raza) throws Exception
	{
		Connection con = null;
	    try
	    {
	 		con = Data.Connection();
	 		con.createStatement().executeUpdate("INSERT INTO cliente(nombre, id_Raza) VALUES "
	 				+ "(" + Data.String2Sql(sNombre, true, false) + "," + raza.getId() + ");");
	 		return new Cliente(Data.LastId(con));
	    }
	    catch (SQLException ee) { throw ee; }
		finally
		{
	 	    if (con != null) con.close();
		}
	}
	
	/**
	 * Método privado que recibe dos parámetros y devuelve en forma de cadena
	 * la clausula WHERE [condiciones].
	 * @param Integer iId, String sNombre.
	 * @return String que contiene la clausula where + condiciones transformadas en 
	 * cadena.
	 */	
	private static String where(Integer iId, String sNombre, String sRaza) {
		String sWhere = "";
		
		if(sRaza != null)
			sWhere = sWhere + "raza.nombre = " + Data.String2Sql(sRaza, true, false) 
							+ " and ";
		
		if(iId != null)
			sWhere = sWhere + " cliente.id = " + iId + " and ";
		
		if(sNombre != null)
			sWhere = sWhere + " cliente.nombre = " + Data.String2Sql(sNombre, true, false)
				+ " and ";
		
		if(sWhere!="")
			return "where " + sWhere.substring(0, sWhere.length()-4); 
		else
			return "";
	}
	
	/**
	 * Método que elimina de la base de datos una instancia. Si la instancia 
	 * ya ha sido eliminada lanza una excepción.
	 */
	public void Delete() throws Exception {
		
		if (_bIsDeleted)
			throw new Exception("Entidad ya eliminada");
			
		Connection con = null;
	    try
	    {
	 		con = Data.Connection();
	 		con.createStatement().executeUpdate("DELETE FROM cliente " + 
	 				where(_iId, null, null));
	 		_bIsDeleted = true;
	    }
	    catch (SQLException ee) { throw ee; }
		finally
		{
	 	    if (con != null) con.close();
		}
	}
	
	/**
	 * Método que actualiza una instancia en la base de datos. Si la instancia ha
	 * sido eliminada, lanza una excepción.
	 */
	public void Update() throws Exception {
		
		if (_bIsDeleted)
			throw new Exception("Entidad ya eliminada");
			
		Connection con = null;
	    try
	    {
	 		con = Data.Connection();
	 		con.createStatement().executeUpdate("UPDATE cliente SET nombre = " 
	 				+ Data.String2Sql(_sNombre, true, false) + ", id_Raza = " + _raza.getId() + " " + 
	 				where(_iId, null, null) + ";");
	 	}
	    catch (SQLException ee) { throw ee; }
		finally
		{
	 	    if (con != null) con.close();
		}
	}
	
	/**
	 * Método que recibe tantos parámetros como atributos tiene la tabla sobre la que
	 * realiza el select. Si la entidad sobre la que está intentando hacer el
	 * select ha sido eliminada, lanza una excepción.
	 * @param int iId, String sNombre. Estos parámetros pueden ser nulos, la funcion
	 * where() se encargará de devolver una cosa u otra en función.
	 * @return List<Cliente> list. Devuelve una lista con los clientes cuyos atributos
	 * coincidan con los pasados como parámetros.
	 * 
	 * Anotacion: Realmente no debería pasarse como parámetro el ID ya que es 
	 * clave primaria y es única (y además no puede ser nula) pero para no tener 
	 * que modificar las tablas lo hicimos así, pero realmente está mal. 
	 */
	public static List<Cliente> Select(Integer iId, String sNombre, String sRaza) throws Exception {
		
		List<Cliente> list = new LinkedList<Cliente>();
		Connection con = null;
		ResultSet rs = null;
	    try
	    {
	 		con = Data.Connection();
	 		
	 		if(sRaza == null) {
		 		rs = con.createStatement().executeQuery("SELECT id FROM cliente " 
		 				+ where(iId, sNombre, sRaza));
		
		 		while(rs.next()) 
		 			list.add(new Cliente(rs.getInt("id")));
	 		}
	 		
	 		else {
		 		rs = con.createStatement().executeQuery("SELECT cliente.id FROM cliente "
		 				+ "INNER JOIN raza on cliente.id_Raza = raza.id " 
		 				+ where(iId, sNombre, sRaza));
		
		 		while(rs.next()) 
		 			list.add(new Cliente(rs.getInt("id")));
	 		}
	 		
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

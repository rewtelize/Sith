package es.uca.gii.csi17.sith.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Raza 
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
		return _sNombre;
	}
	
	/**
	 * @author Javier Barroso Canto, Jorge Gutiérrez Vila
	 * @param iId Identificador del cliente
	 * @return Entidad de la clase Cliente
	 */
	public Raza(Integer iId) throws Exception
	{
		Connection con = null;
	    ResultSet rs = null;
	    try
	    {
	 		con = Data.Connection();
	 		rs = con.createStatement().executeQuery("SELECT id, nombre FROM raza "
	 				+ "where id " + "=" + iId + ";");
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
	public static Raza create(String sNombre) throws Exception
	{
		Connection con = null;
	    try
	    {
	 		con = Data.Connection();
	 		con.createStatement().executeUpdate("INSERT INTO cliente(nombre) VALUES "
	 				+ "(" + Data.String2Sql(sNombre, true, false) + ");");
	 		
	 		return new Raza(Data.LastId(con));
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
	 		con.createStatement().executeUpdate("DELETE FROM raza " + 
	 				where(_iId, null));
	 		System.out.println("DELETE FROM raza " + 
	 				where(_iId, null));
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
	 		con.createStatement().executeUpdate("UPDATE raza SET nombre = " 
	 				+ Data.String2Sql(_sNombre, true, false) + " " + 
	 				where(_iId, null) + ";");
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
	public static List<Raza> Select() throws Exception {
		
		List<Raza> list = new LinkedList<Raza>();
		Connection con = null;
		ResultSet rs = null;
	    try
	    {
	 		con = Data.Connection();
	 		rs = con.createStatement().executeQuery("SELECT * FROM raza order by nombre");
	
	 		while(rs.next()) 
	 			list.add(new Raza(rs.getInt("id")));
	 		
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


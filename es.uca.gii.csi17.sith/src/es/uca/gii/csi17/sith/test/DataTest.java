package es.uca.gii.csi17.sith.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.uca.gii.csi17.sith.data.Data;

class DataTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Data.LoadDriver();
	}

	@Test
	@Disabled("Ya probado")
	public void testDbAccess() throws Exception {
		Connection con = null;
	    ResultSet rs = null;
	 	try {
	        	
	 		con = Data.Connection();
	 		rs = con.createStatement().executeQuery("SELECT * FROM cliente;");
	            
	 		int i = 0;
	 		System.out.println("Numero de registros por tabla: " + rs.getMetaData().getColumnCount());
	 		while (rs.next()) {
	 			System.out.println(rs.getString("id") + " " + rs.getString("nombre"));
	 			i++;
	 		}
	 		
	 		assertEquals(i, 2);
	    }
	 	catch (SQLException ee) { throw ee; }
		finally {
			if (rs != null) rs.close();
	 	    if (con != null) con.close();
		}
	}
	
	@Test
	public void testData() throws Exception {
	    assertEquals("'hola'", Data.String2Sql("hola", true, false));
	    assertEquals("'h''ola'", Data.String2Sql("h'ola", true, false));
	    assertEquals("%''hola''%", Data.String2Sql("'hola'", false, true));
	    assertEquals("'%''hola''%'", Data.String2Sql("'hola'", true, true));
	    
	}
	
	@Test
	public void testBoolean() throws Exception {
	    assertEquals(0, Data.Boolean2Sql(false));
	    assertEquals(1, Data.Boolean2Sql(true));
	}
}
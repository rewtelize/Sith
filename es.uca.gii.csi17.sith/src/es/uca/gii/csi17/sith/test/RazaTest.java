package es.uca.gii.csi17.sith.test;

import es.uca.gii.csi17.sith.data.Raza;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import es.uca.gii.csi17.sith.data.Data;

class RazaTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception 
	{
		Data.LoadDriver();
	}

	@Test
	public void testConstructor() throws Exception
	{
		Raza raza = new Raza(1);
		assertEquals(1, raza.getId());
		assertEquals("wookiee", raza.getNombre());
	}
	
	@Test
	public void testSelect() throws Exception
	{
		Raza raza = new Raza(1);
		List<Raza> list = Raza.Select();
		assertEquals(list.get(1).getNombre(), raza.getNombre());		
	}
}

package es.uca.gii.csi17.sith.test;

import  es.uca.gii.csi17.sith.data.Cliente;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import es.uca.gii.csi17.sith.data.Data;

public class ClienteTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception
	{
		Data.LoadDriver();
	}

	@Test
	public void testConstructor() throws Exception
	{
		Cliente cliente = new Cliente(2);
		assertEquals(2, cliente.getId());
		assertEquals("george", cliente.getNombre());
	}
	
	@Test
	public void testCreate() throws Exception
	{
		Cliente cliente = Cliente.create("pepe");
		assertEquals("pepe", cliente.getNombre());
		cliente.Delete();
	}
	
	@Test
	public void testSelect() throws Exception
	{
		Cliente cliente = new Cliente(2);
		List<Cliente> list = Cliente.Select(2, null);
		assertEquals(list.get(0).getNombre(), cliente.getNombre());		
	}
	
	@Test
	public void testUpdate() throws Exception
	{
		Cliente cliente = new Cliente(2);
		cliente.setNombre("jorge");
		cliente.Update();
		cliente = new Cliente(2);
		assertEquals(cliente.getNombre(), "jorge");	
		cliente.setNombre("george");
		cliente.Update();
	}
	
	@Test
	public void testDelete() throws Exception
	{
		Cliente cliente = Cliente.create("javi");
		cliente.Delete();
		assertEquals(cliente.getIsDeleted(), true);
		try {
			cliente = new Cliente(cliente.getId());
		}
		catch(Exception e) {
			assertEquals(1,1);
		}
	}
}

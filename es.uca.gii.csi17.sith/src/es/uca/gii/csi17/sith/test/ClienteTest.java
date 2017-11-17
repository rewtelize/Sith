package es.uca.gii.csi17.sith.test;

import  es.uca.gii.csi17.sith.data.Cliente;

import static org.junit.jupiter.api.Assertions.*;


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
		Cliente cliente = new Cliente(1);
		assertEquals(1, cliente.getId());
		assertEquals("javi", cliente.getNombre());
	}
	
	@Test
	public void testCreate() throws Exception
	{
		Cliente cliente = new Cliente();
		cliente = Cliente.create("Pepe");
		assertEquals("Pepe", cliente.getNombre());		
	}

}

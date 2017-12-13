package es.uca.gii.csi17.sith.test;

import es.uca.gii.csi17.sith.data.Ticket;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import es.uca.gii.csi17.sith.data.Data;

class TicketTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception
	{
		Data.LoadDriver();
	}

	@Test
	public void testConstructor() throws Exception
	{
		Ticket ticket = new Ticket(1);
	}
	
	@Test
	public void testCreate() throws Exception
	{
		Ticket ticket = new Ticket(10);	
	}

}

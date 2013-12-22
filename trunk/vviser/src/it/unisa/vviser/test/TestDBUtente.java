package it.unisa.vviser.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import it.unisa.vviser.entity.Utente;
import it.unisa.vviser.storage.DBConnectionPool;
import it.unisa.vviser.storage.DBNames;
import it.unisa.vviser.storage.DBUtente;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestDBUtente {
private DBUtente dbUtente ;
private Utente utente;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		utente = new Utente();
		dbUtente =new DBUtente();
		utente = dbUtente.authenticate("eugenio.gigante@gmail.com", "genio");
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testDBUtente() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testAddUtente() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testRemoveUtente() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testVisualizzaUtenti() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testModificaNome() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testModificaCognome() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testModificaDataDiNascita() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testModificaComuneDiNascita() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testModificaProvinciaDiNascita() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testModificaCodiceFiscale() throws SQLException {
		assertEquals(true,dbUtente.modificaCodiceFiscale(utente, "ggngen"));
		//fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testModificaPassword() throws SQLException {
		assertEquals(true,dbUtente.modificaPassword(utente, "geniotest"));
		//fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testModificaEmail() throws SQLException {
		assertEquals(true,dbUtente.modificaEmail(utente, "eugenio.gigante@test.it"));
		//fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testModificaDipartimento() throws SQLException {
		assertEquals(true,dbUtente.modificaDipartimento(utente, "informatica"));
		//fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testModificaTipologia() throws SQLException {
		assertEquals(true,dbUtente.modificaTipologia(utente, "tipotest"));
		//fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testAuthenticate() {
		
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetUtente() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testObject() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetClass() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testHashCode() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testEquals() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testClone() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testToString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testNotify() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testNotifyAll() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testWaitLong() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testWaitLongInt() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testWait() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testFinalize() {
		fail("Not yet implemented"); // TODO
	}

}

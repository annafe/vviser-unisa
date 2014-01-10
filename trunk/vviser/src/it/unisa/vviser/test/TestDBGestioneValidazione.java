package it.unisa.vviser.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.vviser.entity.Prodotto;
import it.unisa.vviser.storage.DBGestioneValidazione;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestDBGestioneValidazione { 
	private DBGestioneValidazione dbGestioneValidazione;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testDBGestioneValidazione() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testVisualizzaProdotti() throws SQLException {
		ArrayList<Prodotto> listProdotto=new ArrayList<Prodotto>();
		//dbGestioneValidazione..addEvento(eventoValutazione);
		listProdotto =dbGestioneValidazione.visualizzaProdotti();
		if (listProdotto==null)	fail("Not yet implemented");
		assertFalse(listProdotto==null);
		assertTrue("ok", !(listProdotto==null));
		//dbEventiValutazione.deleteEventoByID(eventoValutazione);
		
	}

	@Test
	public final void testVisualizzaProdottivalidatiDipartimento() throws SQLException {
		ArrayList<Prodotto> listProdotto=new ArrayList<Prodotto>();
		//dbGestioneValidazione..addEvento(eventoValutazione);
		listProdotto =dbGestioneValidazione.visualizzaProdottivalidatiDipartimento();
		if (listProdotto==null)	fail("Not yet implemented");
		assertFalse(listProdotto==null);
		assertTrue("ok", !(listProdotto==null));
		//dbEventiValutazione.deleteEventoByID(eventoValutazione);
	}

	@Test
	public final void testInvionotifica() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testVALIDATODIPARTIMENTO() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testValidatoAreaScientifica() {
		fail("Not yet implemented"); // TODO
	}


}

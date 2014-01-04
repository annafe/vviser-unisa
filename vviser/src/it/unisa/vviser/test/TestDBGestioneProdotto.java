package it.unisa.vviser.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import it.unisa.vviser.entity.Prodotto;
import it.unisa.vviser.storage.DBGestioneProdotto;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestDBGestioneProdotto {
	private DBGestioneProdotto dbGestioneProdotto;
	private Prodotto prodotto;

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
	public final void testDBGestioneProdotto() throws SQLException {
		
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetInstance() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testInsertProdotto() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testEliminaProdotto() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testModificaProdotto() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSottomettiAlMIUR() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testVisualizzaDettagliProdotto() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testVisualizzaProdottiPersonali() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testVisualizzaProdottiProprietarioCoautore() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testVisualizzaProdottiCoautoreNonConvalidati() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testVisualizzaProdottiCoautoreConvalidato() {
		fail("Not yet implemented"); // TODO
	}
	
	@Test
	public final void ricercaProdottoPerTitoloProprietarioAnnoTipologia()throws SQLException
	{
		dbGestioneProdotto = new DBGestioneProdotto();
		prodotto = dbGestioneProdotto.ricercaProdottoPerTitoloProprietarioAnnoTipologia("matematica23", "mr@gmail.com", "2013,12,28", "Rivista");
		if (prodotto==null)	fail("Not yet implemented");
		assertFalse(prodotto==null);
		assertTrue("ok", !(prodotto==null));
		
	}
	@Test
	public final void testRicercaProdottoPerTitoloProdotto() {
		
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testRicercaProdottoPerAnni() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testRicercaProdottoPerTitoloRivista() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testRicercaProdottoPerIssnRivista() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testRicercaProdottoPerTipologia() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testConvalidaProdotto() {
		fail("Not yet implemented"); // TODO
	}

	

}

package it.unisa.vviser.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.vviser.entity.Prodotto;
import it.unisa.vviser.entity.Utente;
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
	public final void testRicercaProdottoPerTitoloProdotto() throws SQLException {
		dbGestioneProdotto = new DBGestioneProdotto();
		ArrayList<Prodotto> listaProdotto = dbGestioneProdotto.ricercaProdottoPerTipologia("tipotest");
		if (listaProdotto==null)	fail("Not yet implemented");
		assertFalse(listaProdotto==null);
		assertTrue("ok", !(listaProdotto==null));
	}

	@Test
	public final void testRicercaProdottoPerAnni() throws SQLException {
		Utente utente=new Utente();
		ArrayList<Prodotto> listaProdotto = dbGestioneProdotto.ricercaPrivataProdottoPerAnni(2000, 2013, utente);
		if (listaProdotto==null)	fail("Not yet implemented");
		assertFalse(listaProdotto==null);
		assertTrue("ok", !(listaProdotto==null));
	}

	@Test
	public final void testRicercaProdottoPerTitoloRivista() throws SQLException {
		ArrayList<Prodotto> listaProdotto = dbGestioneProdotto.ricercaProdottoPerTitoloRivista("titolorivista");
		if (listaProdotto==null)	fail("Not yet implemented");
		assertFalse(listaProdotto==null);
		assertTrue("ok", !(listaProdotto==null));
		
	}

	@Test
	public final void testRicercaProdottoPerIssnRivista() throws SQLException {
		Prodotto prodotto = dbGestioneProdotto.ricercaProdottoISBN("isbn_prodotto");
		if (prodotto==null)	fail("Not yet implemented");
		assertFalse(prodotto==null);
		assertTrue("ok", !(prodotto==null));
	}

	@Test
	public final void testRicercaProdottoPerTipologia() throws SQLException {
		ArrayList<Prodotto> listaProdotto = dbGestioneProdotto.ricercaProdottoPerTipologia("tipologia");
		if (listaProdotto==null)	fail("Not yet implemented");
		assertFalse(listaProdotto==null);
		assertTrue("ok", !(listaProdotto==null));
		
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testConvalidaProdotto() {
		fail("Not yet implemented"); // TODO
	}

	

}

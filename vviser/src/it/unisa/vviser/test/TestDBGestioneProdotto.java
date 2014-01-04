package it.unisa.vviser.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import it.unisa.vviser.entity.Prodotto;
import it.unisa.vviser.entity.Utente;
import it.unisa.vviser.storage.DBGestioneProdotto;
import it.unisa.vviser.storage.DBUtente;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestDBGestioneProdotto {
	private DBGestioneProdotto dbGestioneProdotto;
	private Prodotto prodotto;
	private Utente utente = new Utente();
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		utente.setNome("nome");
		utente.setCognome("cognome");
		utente.setEmail("email@email.com");
		utente.setPassword("password");
		utente.setCodiceFiscale("TTTTTTTTTTTTTTTT");
		utente.setComuneDiNascita("comuneDiNascita");
		utente.setProvinciaDiNascita("PN");
		utente.setDataDiNascita(new GregorianCalendar(2013,12,23));
		utente.setDipartimento("Informatica");
		utente.setTipologia("Ricercatore");
		
		prodotto.setAnnoPubblicazione(new GregorianCalendar(2013,01,01));
		prodotto.setApagina(1);
		prodotto.setBozza(false);
		prodotto.setCodiceDOI("codice DOI");
		prodotto.setDaPagina(1);
		prodotto.setDescrizioneContenuti("descrizione");
		prodotto.setDiffusione("diffusione");
		prodotto.setEditore("editor");
		prodotto.setFormatoPubblicazione("formato");
		prodotto.setIndirizzoWeb("indirizzo web");
		prodotto.setIsbn("1234567890qwerty");
		prodotto.setListaCollaboratori("collaboratori");
		prodotto.setProprietario("proprietario");
		prodotto.setStato("stato");
		prodotto.setParoleChiavi("parole chiavi");
		prodotto.setNote("note");
		prodotto.setNumVolume(10);
		prodotto.setTipologia("tipologia");
		prodotto.setTitolo("titolo");
		prodotto.setTotalePagine(1);
		
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
	public final void testInsertProdotto() throws SQLException {
		dbGestioneProdotto.insertProdotto(prodotto);
		prodotto=dbGestioneProdotto.ricercaProdottoISBN("1234567890qwerty");
		assertEquals(prodotto.getIsbn(),"1234567890qwerty");
		dbGestioneProdotto.eliminaProdotto("1234567890qwerty");
	}

	@Test
	public final void testEliminaProdotto() throws SQLException {
		dbGestioneProdotto.insertProdotto(prodotto);
		dbGestioneProdotto.eliminaProdotto("1234567890qwerty");
		prodotto=dbGestioneProdotto.ricercaProdottoISBN("1234567890qwerty");
		assertTrue("ok", prodotto==null);
	}

	@Test
	public final void testModificaProdotto() throws SQLException {
		dbGestioneProdotto.insertProdotto(prodotto);
		prodotto.setIndirizzoWeb("nuova indirizzo web");
		dbGestioneProdotto.modificaProdotto(prodotto);
		prodotto=dbGestioneProdotto.ricercaProdottoISBN("1234567890qwerty");
		assertEquals(prodotto.getIndirizzoWeb(),"nuova indirizzo web");
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
	public final void testVisualizzaProdottiCoautoreConvalidato() throws SQLException {
		
		ArrayList<Prodotto> listaProdotto=dbGestioneProdotto.visualizzaProdottiCoautoreConvalidato("utente");
		if (listaProdotto==null)	fail("Not yet implemented");
		assertFalse(listaProdotto==null);
		assertTrue("ok", !(listaProdotto==null));
		
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
	public final void testConvalidaProdotto() throws SQLException {
		dbGestioneProdotto.convalidaProdotto("collaboratore", "isb_prodotto");
		Prodotto prodotto = dbGestioneProdotto.ricercaProdottoISBN("isbn_prodotto");
		assertEquals(prodotto.getStato(), "prodotto validato");//non credo sia il metodo giusto per capire se un prodotto è convalidato.
		
	}

	

}

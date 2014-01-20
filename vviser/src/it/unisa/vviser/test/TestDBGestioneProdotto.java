package it.unisa.vviser.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import it.unisa.vviser.entity.Prodotto;
import it.unisa.vviser.entity.Utente;
import it.unisa.vviser.storage.DBGestioneProdotto;
import it.unisa.vviser.storage.DBUtente;
import it.vviser.common.CommonMethod;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestDBGestioneProdotto {
	private DBGestioneProdotto dbGestioneProdotto=new DBGestioneProdotto();
	private Prodotto prodotto=new Prodotto("1234567","nuovotitolo",CommonMethod.stringToDate("2014-01-01"),
			"PDF",null,null,null,"NonValidato",false,"altroMinesteriale","robdep@unisa.it",null,null,
			null,null,null,0,1,1,1);
	private Utente utente = new Utente();
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		/*utente.setNome("nome");
		utente.setCognome("cognome");
		utente.setEmail("email@email.com");
		utente.setPassword("password");
		utente.setCodiceFiscale("TTTTTTTTTTTTTTTT");
		utente.setComuneDiNascita("comuneDiNascita");
		utente.setProvinciaDiNascita("PN");
		utente.setDataDiNascita(new GregorianCalendar(2013,12,23));
		utente.setDipartimento("Informatica");
		utente.setTipologia("Ricercatore");
		
		prodotto.setAnnoPubblicazione(CommonMethod.stringToDate("2014-01-01"));//new GregorianCalendar(2014,01,01));
		prodotto.setApagina(0);
		prodotto.setBozza(false);
		prodotto.setCodiceDOI(null);
		prodotto.setDaPagina(1);
		prodotto.setDescrizioneContenuti(null);
		prodotto.setDiffusione(null);
		prodotto.setEditore("editor");
		prodotto.setFormatoPubblicazione("PDF");
		prodotto.setIndirizzoWeb("indirizzo web");
		prodotto.setIsbn("1234567890999");
		prodotto.setListaCollaboratori(null);
		prodotto.setProprietario("robdep@unisa.it");
		prodotto.setStato("NonValidato");
		prodotto.setParoleChiavi(null);
		prodotto.setNote("note");
		prodotto.setNumVolume(10);
		prodotto.setTipologia("altroMinesteriale");
		prodotto.setTitolo("titolorivista");
		prodotto.setTotalePagine(1);
		*/
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testInsertProdotto() throws SQLException {
		dbGestioneProdotto.insertProdotto(prodotto);
		prodotto=dbGestioneProdotto.ricercaProdottoISBN("1234567");
		assertEquals(dbGestioneProdotto.visualizzaDettagliProdotto("1234567").getIsbn(),"1234567");
		dbGestioneProdotto.eliminaProdotto("1234567");
	}
	
	@Test
	public final void testModificaProdotto() throws SQLException {
		dbGestioneProdotto.insertProdotto(prodotto);
		prodotto.setIndirizzoWeb("nuova indirizzo web");
		dbGestioneProdotto.modificaProdotto(prodotto);
		prodotto=dbGestioneProdotto.ricercaProdottoISBN("1234567");
		assertEquals(prodotto.getIndirizzoWeb(),"nuova indirizzo web");
		dbGestioneProdotto.eliminaProdotto("1234567");
	}
	

	@Test
	public final void ricercaProdottoPerTitoloProprietarioAnnoTipologia()throws SQLException
	{
		dbGestioneProdotto.insertProdotto(prodotto);
		prodotto = dbGestioneProdotto.ricercaProdottoPerTitoloProprietarioAnnoTipologia("nuovotitolo", "robdep@unisa.it", "2014-01-01", "altroMinesteriale");
		if (prodotto==null)	fail("Not yet implemented");
		assertFalse(prodotto==null);
		assertTrue("ok", !(prodotto==null));
		dbGestioneProdotto.eliminaProdotto("1234567");
	}
	
	
	@Test
	public final void testRicercaProdottoPerTitoloProdotto() throws SQLException {
		dbGestioneProdotto.insertProdotto(prodotto);
		Utente utente=new Utente();
		utente.setEmail("robdep@unisa.it");
		ArrayList<Prodotto> listaProdotto = dbGestioneProdotto.ricercaPrivataProdottoPerTitoloProdotto("nuovotitolo", utente);
		if (listaProdotto==null)	fail("Not yet implemented");
		assertFalse(listaProdotto==null);
		assertTrue("ok", !(listaProdotto==null));
		dbGestioneProdotto.eliminaProdotto("1234567");
	}
	
	@Test
	public final void testRicercaProdottoPerAnni() throws SQLException {
		Utente utente=new Utente();
		utente.setEmail("robdep@unisa.it");
		dbGestioneProdotto.insertProdotto(prodotto);
		ArrayList<Prodotto> listaProdotto = dbGestioneProdotto.ricercaPrivataProdottoPerAnni(2000, 2014, utente);
		if (listaProdotto==null)	fail("Not yet implemented");
		assertFalse(listaProdotto==null);
		assertTrue("ok", !(listaProdotto==null));
		dbGestioneProdotto.eliminaProdotto("1234567");
	}
	
	@Test
	public final void testRicercaProdottoPerTitoloRivista() throws SQLException {
		dbGestioneProdotto.insertProdotto(prodotto);
		ArrayList<Prodotto> listaProdotto = dbGestioneProdotto.ricercaProdottoPerTitoloRivista("titolorivista");
		if (listaProdotto==null)	fail("Not yet implemented");
		assertFalse(listaProdotto==null);
		assertTrue("ok", !(listaProdotto==null));
		dbGestioneProdotto.eliminaProdotto("1234567");
		
	}
	
	@Test
	public final void testRicercaProdottoPerIssnRivista() throws SQLException {
		dbGestioneProdotto.insertProdotto(prodotto);
		ArrayList<Prodotto> prodottoRicerca = dbGestioneProdotto.ricercaProdottoPerIssnRivista("1234567");
		if (prodottoRicerca==null)	fail("Not yet implemented");
		assertFalse(prodottoRicerca==null);
		assertTrue("ok", !(prodottoRicerca==null));
		dbGestioneProdotto.eliminaProdotto("1234567");
	}
	
	@Test
	public final void testRicercaProdottoPerTipologia() throws SQLException {
		dbGestioneProdotto.insertProdotto(prodotto);
		ArrayList<Prodotto> listaProdotto = dbGestioneProdotto.ricercaProdottoPerTipologia("altroMinisteriale");
		if (listaProdotto==null)	fail("Not yet implemented");
		assertFalse(listaProdotto==null);
		assertTrue("ok", !(listaProdotto==null));
		dbGestioneProdotto.eliminaProdotto("1234567");

	}
	
	@Test
	public final void testConvalidaProdotto() throws SQLException {
		dbGestioneProdotto.insertProdotto(prodotto);
		dbGestioneProdotto.convalidaProdotto("robdep@unisa.it", "1234567");
		Prodotto prodottoDettagli = dbGestioneProdotto.visualizzaDettagliProdotto("1234567");
		dbGestioneProdotto.eliminaProdotto("1234567");
		assertEquals(prodottoDettagli.getStato(), "Non Validato");
		
	}
	/*
	@Test
	public final void testEliminaProdotto() throws SQLException {
		dbGestioneProdotto.insertProdotto(prodotto);
		dbGestioneProdotto.eliminaProdotto("1234567");
		prodotto=dbGestioneProdotto.ricercaProdottoISBN("1234567");
		assertTrue("ok", prodotto==null);
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
*/
	

}

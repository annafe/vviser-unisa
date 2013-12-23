package it.unisa.vviser.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;
import java.util.List;

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
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testDBUtente() {
	}

	@Test
	public final void testAddUtente() throws SQLException {
		assertTrue(dbUtente.addUtente(utente));
		dbUtente.removeUtente(utente);
	}

	@Test
	public final void testRemoveUtente() throws SQLException {
		dbUtente.addUtente(utente);
		assertTrue(dbUtente.removeUtente(utente));
	}

	@Test
	public final void testVisualizzaUtenti() throws SQLException {
		List<Utente> list = dbUtente.visualizzaUtenti();
		dbUtente.addUtente(utente);
		assertTrue(list.size() > 0);
	}

	@Test
	public final void testModificaNome() throws SQLException {
		dbUtente.addUtente(utente);
		dbUtente.modificaNome(utente, "nuovoNome");
		assertEquals(utente.getNome(), "nuovoNome");
		assertEquals(dbUtente.getUtente(utente.getEmail()).getNome(), "nuovoNome");
		dbUtente.removeUtente(utente);
	}

	@Test
	public final void testModificaCognome() throws SQLException {
		dbUtente.addUtente(utente);
		dbUtente.modificaCognome(utente, "nuovoCognome");
		assertEquals(utente.getCognome(), "nuovoCognome");
		assertEquals(dbUtente.getUtente(utente.getEmail()).getCognome(), "nuovoCognome");
		dbUtente.removeUtente(utente);
	}

	@Test
	public final void testModificaDataDiNascita() throws SQLException {
		dbUtente.addUtente(utente);
		GregorianCalendar gc = new GregorianCalendar(2013,12,24);
		dbUtente.modificaDataDiNascita(utente, gc);
		assertEquals(utente.getDataDiNascita(), gc);
		assertEquals(dbUtente.getUtente(utente.getEmail()).getDataDiNascita(), gc);
		dbUtente.removeUtente(utente);
	}

	@Test
	public final void testModificaComuneDiNascita() throws SQLException {
		dbUtente.addUtente(utente);
		dbUtente.modificaComuneDiNascita(utente, "nuovoCN");
		assertEquals(utente.getComuneDiNascita(), "nuovoCN");
		assertEquals(dbUtente.getUtente(utente.getEmail()).getComuneDiNascita(), "nuovoCN");
		dbUtente.removeUtente(utente);
	}

	@Test
	public final void testModificaProvinciaDiNascita() throws SQLException {
		dbUtente.addUtente(utente);
		dbUtente.modificaProvinciaDiNascita(utente, "NP");
		assertEquals(utente.getProvinciaDiNascita(), "NP");
		assertEquals(dbUtente.getUtente(utente.getEmail()).getProvinciaDiNascita(), "NP");
		dbUtente.removeUtente(utente);
	}

	@Test
	public final void testModificaCodiceFiscale() throws SQLException {
		dbUtente.addUtente(utente);
		dbUtente.modificaCodiceFiscale(utente, "NNNNNNNNNNNNNNNN");
		assertEquals(utente.getCodiceFiscale(), "NNNNNNNNNNNNNNNN");
		assertEquals(dbUtente.getUtente(utente.getEmail()).getCodiceFiscale(), "NNNNNNNNNNNNNNNN");
		dbUtente.removeUtente(utente);
	}

	@Test
	public final void testModificaPassword() throws SQLException {
		dbUtente.addUtente(utente);
		dbUtente.modificaPassword(utente, "nuovaPassword");
		assertEquals(utente.getPassword(), "nuovaPassword");
		assertEquals(dbUtente.getUtente(utente.getEmail()).getPassword(), "nuovaPassword");
		dbUtente.removeUtente(utente);
	}

	@Test
	public final void testModificaEmail() throws SQLException {
		dbUtente.addUtente(utente);
		dbUtente.modificaEmail(utente, "nuovaemail@email.com");
		assertEquals(utente.getEmail(), "nuovaemail@email.com");
		assertEquals(dbUtente.getUtente(utente.getEmail()).getEmail(), "nuovaemail@email.com");
		dbUtente.removeUtente(utente);
	}

	@Test
	public final void testModificaDipartimento() throws SQLException {
		dbUtente.addUtente(utente);
		dbUtente.modificaDipartimento(utente, "Filosofia");
		assertEquals(utente.getDipartimento(), "Filosofia");
		assertEquals(dbUtente.getUtente(utente.getEmail()).getDipartimento(), "Filosofia");
		dbUtente.removeUtente(utente);
	}

	@Test
	public final void testModificaTipologia() throws SQLException {
		dbUtente.addUtente(utente);
		dbUtente.modificaTipologia(utente, "amministratore");
		assertEquals(utente.getTipologia(), "amministratore");
		assertEquals(dbUtente.getUtente(utente.getEmail()).getTipologia(), "amministratore");
		dbUtente.removeUtente(utente);
	}

	@Test
	public final void testAuthenticate() throws SQLException {
		dbUtente.addUtente(utente);
		assertTrue(dbUtente.authenticate("email@email.com", "password") != null);
		dbUtente.removeUtente(utente);
	}

	@Test
	public final void testGetUtente() throws SQLException {
		dbUtente.addUtente(utente);
		assertTrue(dbUtente.getUtente("email@email.com")!=null);
	}
}

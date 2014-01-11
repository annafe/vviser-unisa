package it.unisa.vviser.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.vviser.entity.EventoValutazione;
import it.unisa.vviser.entity.ListaProdottiValutazione;
import it.unisa.vviser.entity.Prodotto;
import it.unisa.vviser.entity.ProdottoValutazione;
import it.unisa.vviser.entity.Utente;
import it.unisa.vviser.exception.InsertProdottiValutazioneException;
import it.unisa.vviser.exception.NotAvailableProdottiPerValutazioneException;
import it.unisa.vviser.exception.NotFoundListeValutazioneException;
import it.unisa.vviser.storage.DBProdottiValutazione;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestDBProdottiValutazione {
	private DBProdottiValutazione dbProdottiValutazione;
	private ListaProdottiValutazione listaProdottiValutazione;
	private String emailUtente="eugenio.gigante@gmail.com";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		listaProdottiValutazione=new ListaProdottiValutazione();
		ProdottoValutazione prodottoValutazione=new ProdottoValutazione();
		prodottoValutazione.setIsbn("qwerty12345");
		prodottoValutazione.setPriority(1);
		prodottoValutazione.setTitle("test1");
		listaProdottiValutazione.addProdottoValutazione(prodottoValutazione);
		
		
	}



	@Test
	public void testSottomettiListaProdottiValutazione() {
		fail("Not yet implemented");
	}

	@Test
	public void testShowProdottiVal() {
		ListaProdottiValutazione showProdottiVal=new ListaProdottiValutazione();
		showProdottiVal =dbProdottiValutazione.showProdottiVal(emailUtente, idEvento);
		if (showProdottiVal==null)	fail("Not yet implemented");
		assertFalse(showProdottiVal==null);
		assertTrue("ok", !(showProdottiVal==null));
		
	}

	@Test
	public void testShowListaUtenti() throws SQLException {
		ArrayList<Utente> listaUtente=new ArrayList<Utente>();
		listaUtente =dbProdottiValutazione.showListaUtenti(emailUtente);
		if (listaUtente==null)	fail("Not yet implemented");
		assertFalse(listaUtente==null);
		assertTrue("ok", !(listaUtente==null));
		
	}

	@Test
	public void testModifyProdottiValutazione() {
		
		fail("Not yet implemented");
	}

	@Test
	public void testGetListeProdottiValutazione() throws NotFoundListeValutazioneException, SQLException {
		ArrayList<ListaProdottiValutazione> listeProdottiValutazione=new ArrayList<ListaProdottiValutazione>();
		listeProdottiValutazione =dbProdottiValutazione.getListeProdottiValutazione(emailUtente);
		if (listeProdottiValutazione==null)	fail("Not yet implemented");
		assertFalse(listeProdottiValutazione==null);
		assertTrue("ok", !(listeProdottiValutazione==null));
	}

	@Test
	public void testGetEventoValutazione() throws SQLException, InsertProdottiValutazioneException {
		EventoValutazione eventoValutazione=new EventoValutazione();
		eventoValutazione=dbProdottiValutazione.getEventoValutazione(emailUtente);
		assertEquals(eventoValutazione.getNumeroPubblicazioni(), 123);
		
	}

	@Test
	public void testProdottiFiltrati() throws NotAvailableProdottiPerValutazioneException {
		ArrayList<Prodotto> prodotti=new ArrayList<Prodotto>();
		EventoValutazione evento=new EventoValutazione();
		ArrayList<Prodotto> listeProdotto=new ArrayList<Prodotto>();
		listeProdotto =dbProdottiValutazione.prodottiFiltrati(evento, prodotti);
		if (listeProdotto==null)	fail("Not yet implemented");
		assertFalse(listeProdotto==null);
		assertTrue("ok", !(listeProdotto==null));
	}

	@Test
	public void testOttieniEventoValutazione() throws SQLException {
		EventoValutazione eventoValutazione=new EventoValutazione();
		eventoValutazione=dbProdottiValutazione.ottieniEventoValutazione(123);
		assertEquals(eventoValutazione.getNumeroPubblicazioni(), 123);
	}

	@Test
	public void testConvalidaORifiutaListaProdottiValutazione() {
		fail("Not yet implemented");
	}

	@Test
	public void testSettaSuggerimentoListaValutazione() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetProdottiValutazioneInConflitto() {
		fail("Not yet implemented");
	}

}

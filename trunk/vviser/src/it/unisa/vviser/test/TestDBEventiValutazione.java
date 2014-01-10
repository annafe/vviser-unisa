/**
 * 
 */
package it.unisa.vviser.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import it.unisa.vviser.entity.EventoValutazione;
import it.unisa.vviser.storage.DBEventiValutazione;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Genio
 *
 */
public class TestDBEventiValutazione {
	private DBEventiValutazione dbEventiValutazione;
	private EventoValutazione eventoValutazione;
	private List<EventoValutazione> toReturn = new ArrayList<EventoValutazione>();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dbEventiValutazione= new DBEventiValutazione();
		eventoValutazione =new EventoValutazione();
		eventoValutazione.setID(99);
		eventoValutazione.setNomeEvento("test");
		eventoValutazione.setNumeroPubblicazioni(99);
		eventoValutazione.setScadenza(new GregorianCalendar(2014,12,23));
		eventoValutazione.setDataInizio(new GregorianCalendar(2014,01,01));
		eventoValutazione.setDataFine(new GregorianCalendar(2014,12,23));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link it.unisa.vviser.storage.DBEventiValutazione#addEvento(it.unisa.vviser.entity.EventoValutazione)}.
	 * @throws SQLException 
	 */
	@Test
	public final void testAddEvento() throws SQLException {
		assertTrue(dbEventiValutazione.addEvento(eventoValutazione));
		dbEventiValutazione.deleteEventoByID(eventoValutazione);
	}

	/**
	 * Test method for {@link it.unisa.vviser.storage.DBEventiValutazione#modifyNomeEventoByID(int, java.lang.String)}.
	 * @throws SQLException 
	 */
	@Test
	public final void testModifyNomeEventoByID() throws SQLException {
		dbEventiValutazione.addEvento(eventoValutazione);
		assertTrue(dbEventiValutazione.modifyNomeEventoByID(Integer.toString(eventoValutazione.getID()), "test2"));
		dbEventiValutazione.deleteEventoByID(eventoValutazione);
	}

	/**
	 * Test method for {@link it.unisa.vviser.storage.DBEventiValutazione#modifyDataFineEventoByNome(java.lang.String, java.lang.String)}.
	 * @throws SQLException 
	 */
	@Test
	public final void testModifyDataFineEventoByNome() throws SQLException {
		dbEventiValutazione.addEvento(eventoValutazione);
		assertTrue(dbEventiValutazione.modifyDataFineEventoByNome(eventoValutazione.getNomeEvento(), "test2"));
		dbEventiValutazione.deleteEventoByID(eventoValutazione);
		
	}

	/**
	 * Test method for {@link it.unisa.vviser.storage.DBEventiValutazione#modifyScadenzaByNome(java.lang.String, java.util.GregorianCalendar)}.
	 * @throws SQLException 
	 */
	@Test
	public final void testModifyScadenzaByNome() throws SQLException {
		dbEventiValutazione.addEvento(eventoValutazione);
		assertTrue(dbEventiValutazione.modifyScadenzaByNome(eventoValutazione.getNomeEvento(), new GregorianCalendar(2014,11,23)));
		dbEventiValutazione.deleteEventoByID(eventoValutazione);
	}

	/**
	 * Test method for {@link it.unisa.vviser.storage.DBEventiValutazione#modifyNumPubblicazioniByNome(int, java.lang.String)}.
	 * @throws SQLException 
	 */
	@Test
	public final void testModifyNumPubblicazioniByNome() throws SQLException {
		dbEventiValutazione.addEvento(eventoValutazione);
		assertTrue(dbEventiValutazione.modifyNumPubblicazioniByNome(88, eventoValutazione.getNomeEvento()));
		dbEventiValutazione.deleteEventoByID(eventoValutazione);
	}

	/**
	 * Test method for {@link it.unisa.vviser.storage.DBEventiValutazione#modifyDataInizioByNome(java.util.GregorianCalendar, java.lang.String)}.
	 * @throws SQLException 
	 */
	@Test
	public final void testModifyDataInizioByNome() throws SQLException {
		dbEventiValutazione.addEvento(eventoValutazione);
		assertTrue(dbEventiValutazione.modifyDataInizioByNome( new GregorianCalendar(2014,01,01), eventoValutazione.getNomeEvento()));
		dbEventiValutazione.deleteEventoByID(eventoValutazione);
	}

	/**
	 * Test method for {@link it.unisa.vviser.storage.DBEventiValutazione#modifyDataFineByNome(java.util.GregorianCalendar, java.lang.String)}.
	 * @throws SQLException 
	 */
	@Test
	public final void testModifyDataFineByNome() throws SQLException {
		dbEventiValutazione.addEvento(eventoValutazione);
		assertTrue(dbEventiValutazione.modifyDataFineByNome( new GregorianCalendar(2014,12,01), eventoValutazione.getNomeEvento()));
		dbEventiValutazione.deleteEventoByID(eventoValutazione);
	}

	/**
	 * Test method for {@link it.unisa.vviser.storage.DBEventiValutazione#visualizzaEventi()}.
	 * @throws SQLException 
	 */
	@Test
	public final void testVisualizzaEventi() throws SQLException {
		dbEventiValutazione.addEvento(eventoValutazione);
		toReturn =dbEventiValutazione.visualizzaEventi();
		if (toReturn==null)	fail("Not yet implemented");
		assertFalse(toReturn==null);
		assertTrue("ok", !(toReturn==null));
		dbEventiValutazione.deleteEventoByID(eventoValutazione);
	}

	/**
	 * Test method for {@link it.unisa.vviser.storage.DBEventiValutazione#visualizzaEventiPerNome(it.unisa.vviser.entity.EventoValutazione)}.
	 */
	@Test
	public final void testVisualizzaEventiPerNome() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link it.unisa.vviser.storage.DBEventiValutazione#visualizzaEventiPerScadenza(it.unisa.vviser.entity.EventoValutazione)}.
	 */
	@Test
	public final void testVisualizzaEventiPerScadenza() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link it.unisa.vviser.storage.DBEventiValutazione#visualizzaEventiPerDataFine(it.unisa.vviser.entity.EventoValutazione)}.
	 */
	@Test
	public final void testVisualizzaEventiPerDataFine() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link it.unisa.vviser.storage.DBEventiValutazione#deleteEventoByID(it.unisa.vviser.entity.EventoValutazione)}.
	 * @throws SQLException 
	 */
	@Test
	public final void testDeleteEventoByID() throws SQLException {
		dbEventiValutazione.addEvento(eventoValutazione);
		assertTrue(dbEventiValutazione.deleteEventoByID(eventoValutazione));
	}

	/**
	 * Test method for {@link it.unisa.vviser.storage.DBEventiValutazione#deleteEventoByNome(java.lang.String)}.
	 * @throws SQLException 
	 */
	@Test
	public final void testDeleteEventoByNome() throws SQLException {
		dbEventiValutazione.addEvento(eventoValutazione);
		assertTrue(dbEventiValutazione.deleteEventoByNome(eventoValutazione.getNomeEvento()));
	}

	/**
	 * Test method for {@link it.unisa.vviser.storage.DBEventiValutazione#invioNotificaConflitto(it.unisa.vviser.entity.Notifica)}.
	 */
	@Test
	public final void testInvioNotificaConflitto() {
		fail("Not yet implemented"); // TODO il metodo non restituisce elementi per il confronto
	}

	/**
	 * Test method for {@link it.unisa.vviser.storage.DBEventiValutazione#prodottiInStatoBozza()}.
	 */
	@Test
	public final void testProdottiInStatoBozza() {
		fail("Not yet implemented"); // TODO metodo vuoto
	}



}

package it.unisa.vviser.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.unisa.vviser.storage.*;

public class TestDBTipologie {
	private DBTipologie dbTipologie;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		dbTipologie = new DBTipologie();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetInstance() {
		assertTrue(dbTipologie.getInstance() != null);
	}

	@Test
	public final void testGetTipologie() throws SQLException {
		assertTrue(dbTipologie.getTipologie() != null);//il la classe DBTipologie non possiede un metodo setTipologie, l'unico modo per controllare il suo funzionamento
		//è quello di controllare se la chiamate restituisce un oggetto null, va però previsto nella classe DBTipologie l'assegnazione del null all'arraylist
		//quando non ci sono tipologie nel db oppure è piu corretto aggiugere il metodo setTipologie
	}




}

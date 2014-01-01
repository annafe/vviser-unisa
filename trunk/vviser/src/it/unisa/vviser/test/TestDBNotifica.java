package it.unisa.vviser.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import it.unisa.vviser.entity.Notifica;
import it.unisa.vviser.storage.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestDBNotifica {
	private DBNotifica dbNotifica;
	private Notifica notifica;
	private String isbn="1234asd56789";
	private String mittente="eugenio.gigante@gmail.com";
	private String destinatario="eugenio.gigante@gmail.com";
	private String messaggio="testNotifica";
	private String tipo="test";
	private int id=1;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		this.notifica =new Notifica(destinatario,tipo, mittente,messaggio);
		this.dbNotifica=new DBNotifica();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	
	public final void testAddNotifica() throws SQLException {
		assertTrue(dbNotifica.addNotifica(notifica));
		
	}

	@Test
	public final void testAddNotificaConflitto() throws SQLException {
		dbNotifica.addNotificaConflitto(isbn);//la chiamata al metodo non ristituisce nulla,
											//e non ci sono chiamate per verificare se il conflitto è stato aggiunt
	}

	@Test
	public final void testAddNotificaConflittoValidazione() throws SQLException {
		dbNotifica.addNotificaConflittoValidazione(mittente, isbn, messaggio);//la chiamata al metodo non ristituisce nulla,
																			//e non ci sono chiamate per verificare se il conflitto è stato aggiunt
	}


}

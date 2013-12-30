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

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		String mittente="eugenio.gigante@gmail.com";
		String destinatario="eugenio.gigante@gmail.com";
		String messaggio="testNotifica";
		String tipo="test";
		int id=1;
		this.notifica =new Notifica(destinatario,tipo, mittente,messaggio);
		this.dbNotifica=new DBNotifica();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testDBNotifica() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testAddNotifica() throws SQLException {
		assertTrue(dbNotifica.addNotifica(notifica));
		
	}

	@Test
	public final void testAddNotificaConflitto() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testAddNotificaConflittoValidazione() {
		fail("Not yet implemented"); // TODO
	}


}

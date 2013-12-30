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
		assertTrue(dbTipologie.getTipologie() != null);
	}




}

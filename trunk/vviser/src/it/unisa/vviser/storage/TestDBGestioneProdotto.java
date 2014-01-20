/**
 * 
 */
package it.unisa.vviser.storage;

import static org.junit.Assert.*;
import it.unisa.vviser.entity.Prodotto;
import it.vviser.common.CommonMethod;

import java.sql.SQLException;
import java.util.GregorianCalendar;

import org.junit.Test;

/**
 * @author Romano Simone 0512101343
 *
 */
public class TestDBGestioneProdotto {
	private DBGestioneProdotto toTest;
	private Prodotto product;
	@Test
	public void test() throws SQLException {
		toTest = new DBGestioneProdotto();
		product = toTest.ricercaProdottoPerTitoloProprietarioAnnoTipologia("test prodotto", "adinolfi@unisa.it", "2014-01-15", "altroMinesteriale");
		if (product==null)	fail("Not yet implemented");
		assertFalse(product==null);
		assertTrue("ok", !(product==null));
	}
	
	
}

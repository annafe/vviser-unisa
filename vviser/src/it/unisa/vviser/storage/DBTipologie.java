package it.unisa.vviser.storage;

import it.unisa.vviser.entity.Tipologia;
import it.vviser.common.CommonMethod;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * 
 * @author Anna
 *
 */

public class DBTipologie {

	private static DBTipologie manager;
	
	/**
	 * Costruttore vuoto
	 */
	public DBTipologie()
	{
		
	}
	
	/**
	 * Metodo che implementa il design pattern singleton
	 * @return manager istanza di DBTipologie
	 */
	public static DBTipologie getInstance()
	{
		if(manager==null)
			manager=new DBTipologie();
		return manager;
		
	}
	
	/**
	 * Metodo per il recupero nel database di tutte le tipologie di prodotto memorizzate
	 * @return
	 * @throws SQLException
	 */
	
	public  ArrayList<Tipologia> getTipologie() throws SQLException
	{
		
		Connection conn=null;
		Statement st=null;
		ResultSet ris=null;
		GregorianCalendar datafine = null;
		String query;
		ArrayList<Tipologia> listaTipologie = new ArrayList<Tipologia>();
		try
		{
			conn=DBConnectionPool.getConnection();
			query="SELECT "+DBNames.ATTR_TIPOLOGIA_NOME+","
					+DBNames.ATTR_TIPOLOGIA_DESCRIZIONE+","
					+DBNames.ATTR_TIPOLOGIA_DA+"," 
					+DBNames.ATTR_TIPOLOGIA_VALIDITA+"," 
					+DBNames.ATTR_TIPOLOGIA_A
					+ " FROM " +DBNames.TABLE_TIPOLOGIA+" ";
			
			st=conn.createStatement();
			ris=st.executeQuery(query);
			while(ris.next())
			{
				String nome=ris.getString(DBNames.ATTR_TIPOLOGIA_NOME);
				String descrizione=ris.getString(DBNames.ATTR_TIPOLOGIA_DESCRIZIONE);
				GregorianCalendar datainizio=CommonMethod.stringToDate(ris.getString(DBNames.ATTR_TIPOLOGIA_DA));
				String date = ris.getString(DBNames.ATTR_TIPOLOGIA_A);
				if (date!= null) {datafine=CommonMethod.stringToDate(date);}
				int val = ris.getInt(DBNames.ATTR_TIPOLOGIA_VALIDITA);
				boolean validita = false;
				if (val>0) validita = true;
				
				Tipologia tip = new Tipologia(nome,descrizione,validita,datainizio,datafine);
				listaTipologie.add(tip);
			}
		}
		finally
		{
			st.close();
			DBConnectionPool.releaseConnection(conn);
		}
		
		return listaTipologie;
	}
	
}

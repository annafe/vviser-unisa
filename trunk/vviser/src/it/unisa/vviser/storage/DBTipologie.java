package it.unisa.vviser.storage;

import it.unisa.vviser.entity.ListaProdottiValutazione;
import it.unisa.vviser.entity.ProdottoValutazione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	private DBTipologie()
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
	
	public ListaTipologie showProdottiVal(String emailUtente,int idEvento) throws SQLException
	{
		Connection conn=null;
		Statement st=null;
		ResultSet ris=null;
		String query;
		ListaProdottiValutazione listaProdottiValutazione=new ListaProdottiValutazione(new ArrayList<ProdottoValutazione>(),emailUtente,idEvento,"");
		try
		{
			conn=DBConnectionPool.getConnection();
			query="SELECT "+DBNames.ATTR_PRODOTTO_ISBN+","
					+DBNames.ATTR_PRODOTTO_TITOLO+","
					+DBNames.ATTR_PRODOTTOLISTA_PRIORITA
					+ " FROM " +DBNames.TABLE_PRODOTTO+" "+DBNames.TABLE_PRODOTTOLISTA
					+ " WHERE "+DBNames.ATTR_PRODOTTO_ISBN+"="+DBNames.ATTR_PRODOTTOLISTA_PRODOTTO_ISBN
					+" and "+DBNames.ATTR_PRODOTTOLISTA_UTENTE_EMAIL+"="+listaProdottiValutazione.getEmailUtente()
					+" and "+DBNames.ATTR_PRODOTTOLISTA_EVENTOVALUTAZIONE_ID+"="+listaProdottiValutazione.getIdEventoValutazione();
			
			st=conn.createStatement();
			ris=st.executeQuery(query);
			while(ris.next())
			{
				String isbn=ris.getString(DBNames.ATTR_PRODOTTOLISTA_PRODOTTO_ISBN);
				String title=ris.getString(DBNames.ATTR_PRODOTTO_TITOLO);
				int priority=ris.getInt(DBNames.ATTR_PRODOTTOLISTA_PRIORITA);
				
				ProdottoValutazione p=new ProdottoValutazione(isbn,title,priority);
				listaProdottiValutazione.addProdottoValutazione(p);
			}
		}
		finally
		{
			st.close();
			DBConnectionPool.releaseConnection(conn);
		}
		
		return listaProdottiValutazione;
	}
	
}

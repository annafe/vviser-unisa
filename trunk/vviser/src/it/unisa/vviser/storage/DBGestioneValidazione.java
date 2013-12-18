package it.unisa.vviser.storage;

import it.unisa.vviser.entity.Prodotto;
import it.unisa.vviser.entity.ProdottoValutazione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 
 * @author Angiuoli Salvatore
 *
 */
public class DBGestioneValidazione {
	/**
	 * Costruttore vuoto
	 */
	public DBGestioneValidazione()
	{
		
	}
	/**
	 *Metodo che mostra i prodotti sottomessi a validazione dal ricercatore 
	  * @param idUt
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Prodotto> visualizzaProdotti(String utente)throws SQLException
	{
		
		//TODO aggiungere prodotti da tabella autoriconoscimento
		Statement st=null;
		ResultSet ris=null;
		String query;
		Connection conn=null;
		
		try
		{
			ArrayList<Prodotto> listProdotto=new ArrayList<Prodotto>();
			conn = DBConnectionPool.getConnection();
			
			query="SELECT *"
					+ " FROM " +DBNames.TABLE_PRODOTTO
					+ " WHERE "+DBNames.ATTR_PRODOTTO_STATO+"=NonValidato";
			
            st=conn.createStatement();
    		ris=st.executeQuery(query);
    		while(ris.next())
			{
    			Prodotto p=new Prodotto(ris.getString(DBNames.ATTR_PRODOTTO_ISBN),ris.getString(DBNames.ATTR_PRODOTTO_TITOLO)
        				,ris.getString(DBNames.ATTR_PRODOTTO_ANNOPUBBLICAZIONE),ris.getString(DBNames.ATTR_PRODOTTO_FORMATOPUBBLICAZIONE)
        				,ris.getString(DBNames.ATTR_PRODOTTO_CODICEDOI),ris.getString(DBNames.ATTR_PRODOTTO_DIFFUSIONE)
        				,ris.getString(DBNames.ATTR_PRODOTTO_NOTE),ris.getString(DBNames.ATTR_PRODOTTO_STATO)
        				,ris.getBoolean(DBNames.ATTR_PRODOTTO_BOZZA),ris.getString(DBNames.ATTR_PRODOTTO_TIPOLOGIA)
        				,ris.getString(DBNames.ATTR_PRODOTTO_EMAILPROPRIETARIO),"try");
        	
				listProdotto.add(p);
			}
    		
    		
           
            st=conn.createStatement();
    		ris=st.executeQuery(query);
			
			
    		return listProdotto;
		}
		finally 
        {
            st.close();
            DBConnectionPool.releaseConnection(conn);
        }
	}
	
	
	
	/**
	 *Metodo che mostra i prodotti sottomessi a validazione validati dal dipartimento 
	  * @param idUt
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Prodotto> visualizzaProdottivalidatiDipartimento(String utente)throws SQLException
	{
		
		//TODO aggiungere prodotti da tabella autoriconoscimento
		Statement st=null;
		ResultSet ris=null;
		String query;
		Connection conn=null;
		
		try
		{
			ArrayList<Prodotto> listProdotto=new ArrayList<Prodotto>();
			conn = DBConnectionPool.getConnection();
			
			query="SELECT *"
					+ " FROM " +DBNames.TABLE_PRODOTTO
					+ " WHERE "+DBNames.ATTR_PRODOTTO_STATO+"=ValidatoDipartimento";
			
            st=conn.createStatement();
    		ris=st.executeQuery(query);
    		while(ris.next())
			{
    			Prodotto p=new Prodotto(ris.getString(DBNames.ATTR_PRODOTTO_ISBN),ris.getString(DBNames.ATTR_PRODOTTO_TITOLO)
        				,ris.getString(DBNames.ATTR_PRODOTTO_ANNOPUBBLICAZIONE),ris.getString(DBNames.ATTR_PRODOTTO_FORMATOPUBBLICAZIONE)
        				,ris.getString(DBNames.ATTR_PRODOTTO_CODICEDOI),ris.getString(DBNames.ATTR_PRODOTTO_DIFFUSIONE)
        				,ris.getString(DBNames.ATTR_PRODOTTO_NOTE),ris.getString(DBNames.ATTR_PRODOTTO_STATO)
        				,ris.getBoolean(DBNames.ATTR_PRODOTTO_BOZZA),ris.getString(DBNames.ATTR_PRODOTTO_TIPOLOGIA)
        				,ris.getString(DBNames.ATTR_PRODOTTO_EMAILPROPRIETARIO),"try");
        	
				listProdotto.add(p);
			}
    		
    		
           
            st=conn.createStatement();
    		ris=st.executeQuery(query);
			
			
    		return listProdotto;
		}
		finally 
        {
            st.close();
            DBConnectionPool.releaseConnection(conn);
        }
	}
	
	
	
	/**
	 *Metodo che permette di inviare nel database una notifica per errore
	 * @throws SQLException
	 */
	public void invionotifica(String messaggio) throws SQLException
	{
		Connection conn=null;
		PreparedStatement st=null;
		String query;
		try
		
		{
			conn=DBConnectionPool.getConnection();
			query="UPDATE "+DBNames.TABLE_NOTIFICA
					+" SET "+DBNames.ATTR_NOTIFICA_MESSAGGIO+"="+messaggio
					+DBNames.ATTR_NOTIFICA_DESTINATARIO+"="+DBNames.ATTR_UTENTE_EMAIL
					+ " WHERE "+DBNames.ATTR_PRODOTTO_EMAILPROPRIETARIO+"="+DBNames.ATTR_UTENTE_EMAIL
					+DBNames.ATTR_NOTIFICA_DESTINATARIO+"="+DBNames.ATTR_UTENTE_EMAIL;
			
			st=conn.prepareStatement(query);
			st.executeUpdate();
			conn.commit();
		}
		finally
		{
			st.close();
			DBConnectionPool.releaseConnection(conn);
		}
	}
		/**
		 *Metodo che permette di VALIDARE dal direttore nel database un PRODOTTO	
		 * @throws SQLException
		 */
		public void VALIDATODIPARTIMENTO() throws SQLException
		{
			Connection conn=null;
			PreparedStatement st=null;
			String query;
			try
			{
				conn=DBConnectionPool.getConnection();
				query="UPDATE "+DBNames.TABLE_PRODOTTO
						+" SET "+DBNames.ATTR_PRODOTTO_STATO+"="+"ValidatoDipartimento";
				
				
				st=conn.prepareStatement(query);
				st.executeUpdate();
				conn.commit();
			}
			finally
			{
				st.close();
				DBConnectionPool.releaseConnection(conn);
			}

}
		/**
		 *Metodo che permette di VALIDARE comitato area nel database un PRODOTTO	
		 * @throws SQLException
		 */
		public void ValidatoAreaScientifica() throws SQLException
		{
			Connection conn=null;
			PreparedStatement st=null;
			String query;
			try
			{
				conn=DBConnectionPool.getConnection();
				query="UPDATE "+DBNames.TABLE_PRODOTTO
						+" SET "+DBNames.ATTR_PRODOTTO_STATO+"="+"ValidatoComitatoArea";
				
				
				st=conn.prepareStatement(query);
				st.executeUpdate();
				conn.commit();
			}
			finally
			{
				st.close();
				DBConnectionPool.releaseConnection(conn);
			}

}
}
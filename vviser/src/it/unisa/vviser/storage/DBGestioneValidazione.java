package it.unisa.vviser.storage;

import it.unisa.vviser.entity.Prodotto;
import it.unisa.vviser.entity.ProdottoValutazione;
import it.vviser.common.CommonMethod;

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
	private static DBGestioneValidazione manager;
	/**
	 * Costruttore vuoto
	 */
	public DBGestioneValidazione()
	{
		
	}
	/**
	 * Metodo che implementa il design pattern singleton
	 * @return manager istanza di DBProdottiValutazione
	 */
	public static DBGestioneValidazione getInstance()
	{
		
		if(manager==null)
			manager=new DBGestioneValidazione();
		return manager;
		
	}
	
	/**
	 *Metodo che mostra i prodotti sottomessi a validazione dal ricercatore 
	  * @param idUt 
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Prodotto> visualizzaProdotti()throws SQLException
	{
		
		
		Statement st=null;
		ResultSet ris=null;
		String query;
		Connection conn=null;
		
		try
		{
			ArrayList<Prodotto> listProdotto=new ArrayList<Prodotto>();
			conn = DBConnectionPool.getConnection();
			
			query="SELECT *"
					+ " FROM " +DBNames.TABLE_PRODOTTO+","+DBNames.TABLE_UTENTE
					+ " WHERE "+DBNames.ATTR_PRODOTTO_STATO+"='NonValidato'"
					+" AND "+DBNames.TABLE_UTENTE+"."+DBNames.ATTR_UTENTE_EMAIL+"="+DBNames.TABLE_PRODOTTO+"."+DBNames.ATTR_PRODOTTO_EMAILPROPRIETARIO
					+" AND "+DBNames.TABLE_PRODOTTO+"."+DBNames.ATTR_PRODOTTO_BOZZA+"=0"
					+" AND "+DBNames.TABLE_UTENTE+"."+DBNames.ATTR_UTENTE_DIPARTIMENTO_NOME+"='INFORMATICA'";
            st=conn.createStatement();
    		ris=st.executeQuery(query);
    		while(ris.next())
			{
    			Prodotto p=new Prodotto(ris.getString(DBNames.ATTR_PRODOTTO_ISBN),ris.getString(DBNames.ATTR_PRODOTTO_TITOLO)
    					,CommonMethod.stringToDate(ris.getString(DBNames.ATTR_PRODOTTO_ANNOPUBBLICAZIONE))
    					,ris.getString(DBNames.ATTR_PRODOTTO_FORMATOPUBBLICAZIONE),
        				ris.getString(DBNames.ATTR_PRODOTTO_CODICEDOI),ris.getString(DBNames.ATTR_PRODOTTO_DIFFUSIONE)
        				,ris.getString(DBNames.ATTR_PRODOTTO_NOTE),ris.getString(DBNames.ATTR_PRODOTTO_STATO)
        				,ris.getBoolean(DBNames.ATTR_PRODOTTO_BOZZA),ris.getString(DBNames.ATTR_PRODOTTO_TIPOLOGIA)
        				,ris.getString(DBNames.ATTR_PRODOTTO_EMAILPROPRIETARIO)
        				,ris.getString(DBNames.ATTR_PRODOTTO_LISTACOLLABORATORI)
        				,ris.getString(DBNames.ATTR_PRODOTTO_DESCRIZIONECONTENUTI),ris.getString(DBNames.ATTR_PRODOTTO_INDIRIZZOWEB)
        				,ris.getString(DBNames.ATTR_PRODOTTO_PAROLECHIAVI),ris.getString(DBNames.ATTR_PRODOTTO_EDITORE)
        				,ris.getInt(DBNames.ATTR_PRODOTTO_NUMVOLUME),ris.getInt(DBNames.ATTR_PRODOTTO_TOTALEPAGINE)
        				,ris.getInt(DBNames.ATTR_PRODOTTO_DAPAGINA),ris.getInt(DBNames.ATTR_PRODOTTO_APAGINA));
    			
    			
    			
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
	 *Metodo che visualizza i prodotti sottomessi a validazione validati dal dipartimento 
	  * @param idUt
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Prodotto> visualizzaProdottivalidatiDipartimento()throws SQLException
	{
		
		
		Statement st=null;
		ResultSet ris=null;
		String query;
		Connection conn=null;
		
		try
		{
			ArrayList<Prodotto> listProdotto=new ArrayList<Prodotto>();
			conn = DBConnectionPool.getConnection();
			
			
			query="SELECT *"
					+ " FROM " +DBNames.TABLE_PRODOTTO+","+DBNames.TABLE_UTENTE
					+ " WHERE "+DBNames.ATTR_PRODOTTO_STATO+"='ValidatoDipartimento'"
					+" AND "+DBNames.TABLE_UTENTE+"."+DBNames.ATTR_UTENTE_EMAIL+"="+DBNames.TABLE_PRODOTTO+"."+DBNames.ATTR_PRODOTTO_EMAILPROPRIETARIO
					+" AND "+DBNames.TABLE_PRODOTTO+"."+DBNames.ATTR_PRODOTTO_BOZZA+"=0"
					+" AND "+DBNames.TABLE_UTENTE+"."+DBNames.ATTR_UTENTE_DIPARTIMENTO_NOME+"='INFORMATICA'";
			
			
            st=conn.createStatement();
    		ris=st.executeQuery(query);
    		while(ris.next())
			{
    			Prodotto p=new Prodotto(ris.getString(DBNames.ATTR_PRODOTTO_ISBN),ris.getString(DBNames.ATTR_PRODOTTO_TITOLO)
    					,CommonMethod.stringToDate(ris.getString(DBNames.ATTR_PRODOTTO_ANNOPUBBLICAZIONE))
    					,ris.getString(DBNames.ATTR_PRODOTTO_FORMATOPUBBLICAZIONE),
        				ris.getString(DBNames.ATTR_PRODOTTO_CODICEDOI),ris.getString(DBNames.ATTR_PRODOTTO_DIFFUSIONE)
        				,ris.getString(DBNames.ATTR_PRODOTTO_NOTE),ris.getString(DBNames.ATTR_PRODOTTO_STATO)
        				,ris.getBoolean(DBNames.ATTR_PRODOTTO_BOZZA),ris.getString(DBNames.ATTR_PRODOTTO_TIPOLOGIA)
        				,ris.getString(DBNames.ATTR_PRODOTTO_EMAILPROPRIETARIO)
        				,ris.getString(DBNames.ATTR_PRODOTTO_LISTACOLLABORATORI)
        				,ris.getString(DBNames.ATTR_PRODOTTO_DESCRIZIONECONTENUTI),ris.getString(DBNames.ATTR_PRODOTTO_INDIRIZZOWEB)
        				,ris.getString(DBNames.ATTR_PRODOTTO_PAROLECHIAVI),ris.getString(DBNames.ATTR_PRODOTTO_EDITORE)
        				,ris.getInt(DBNames.ATTR_PRODOTTO_NUMVOLUME),ris.getInt(DBNames.ATTR_PRODOTTO_TOTALEPAGINE)
        				,ris.getInt(DBNames.ATTR_PRODOTTO_DAPAGINA),ris.getInt(DBNames.ATTR_PRODOTTO_APAGINA));
        	
    			
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
	public void invionotifica(String messaggio,String destinatario,String mittente,String oggetto) throws SQLException
	{
		Connection conn=null;
		PreparedStatement st=null;
		String query;
		try
		
		{
			conn=DBConnectionPool.getConnection();
			query="INSERT INTO "+DBNames.TABLE_NOTIFICA+"("+
			DBNames.ATTR_NOTIFICA_ID+","+DBNames.ATTR_NOTIFICA_TIPO+","+DBNames.ATTR_NOTIFICA_STATO+","+DBNames.ATTR_NOTIFICA_MESSAGGIO+","+
			DBNames.ATTR_NOTIFICA_MITTENTE+","+DBNames.ATTR_NOTIFICA_DESTINATARIO+") value (?,?,?,?,?,?)";
					
					
			
			st=conn.prepareStatement(query);
			st.setString(2,oggetto);
			st.setString(3,"nonletto");
			st.setString(4,messaggio);
			st.setString(5,mittente);
			st.setString(6,destinatario);
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
		public void VALIDATODIPARTIMENTO(String isbn) throws SQLException
		{
			Connection conn=null;
			PreparedStatement st=null;
			String query;
			try
			{
				conn=DBConnectionPool.getConnection();
				query="UPDATE "+DBNames.TABLE_PRODOTTO
						+" SET "+DBNames.ATTR_PRODOTTO_STATO+"='ValidatoDipartimento'"
						+" WHERE "+DBNames.ATTR_PRODOTTO_ISBN+"='"+isbn+"'";
				
				
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
		public void ValidatoAreaScientifica(String isbn) throws SQLException
		{
			Connection conn=null;
			PreparedStatement st=null;
			String query;
			try
			{
				conn=DBConnectionPool.getConnection();
				query="UPDATE "+DBNames.TABLE_PRODOTTO
						+" SET "+DBNames.ATTR_PRODOTTO_STATO+"='ValidatoComitatoArea'"
						+" WHERE "+DBNames.ATTR_PRODOTTO_ISBN+"='"+isbn+"'";
				
				
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
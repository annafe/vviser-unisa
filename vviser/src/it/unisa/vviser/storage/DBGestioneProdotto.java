package it.unisa.vviser.storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;

import it.unisa.vviser.entity.Prodotto;
import it.unisa.vviser.entity.ProdottoValutazione;


/**
 * 
 * @author Angiuoli Salvatore
 * @author Antonio De Piano
 *
 */
public class DBGestioneProdotto
{
	private static DBGestioneProdotto manager;
	
	/**
	 * Costruttore vuoto
	 */
	public DBGestioneProdotto()
	{
		
	}
	
	/**
	 * Metodo che implementa il design pattern singleton
	 * @return manager istanza di DBGestioneProdotto
	 */
	public static DBGestioneProdotto getInstance()
	{
		if(manager==null)
			manager=new DBGestioneProdotto();
		return manager;
		
	}
	
	/**
	 * Metodo che permette di inserire un prodotto della ricerca
	 * @param p prodotto
	 * @throws SQLException
	 */
	public void insertProdotto(Prodotto p)throws SQLException
	{
		Connection conn=null;
		PreparedStatement st=null;
		String query;
		
		try
		{
			conn = DBConnectionPool.getConnection();
            query="INSERT INTO "+DBNames.TABLE_PRODOTTO+"("
            		+DBNames.ATTR_PRODOTTO_ISBN+","
            		+DBNames.ATTR_PRODOTTO_TITOLO+","
            		+DBNames.ATTR_PRODOTTO_ANNOPUBBLICAZIONE+","
            		+DBNames.ATTR_PRODOTTO_FORMATOPUBBLICAZIONE+","
            		+DBNames.ATTR_PRODOTTO_CODICEDOI+","
            		+DBNames.ATTR_PRODOTTO_DIFFUSIONE+","
            		+DBNames.ATTR_PRODOTTO_NOTE+","
            		+DBNames.ATTR_PRODOTTO_STATO+","
            		+DBNames.ATTR_PRODOTTO_BOZZA+","
            		+DBNames.ATTR_PRODOTTO_TIPOLOGIA+","
            		+DBNames.ATTR_PRODOTTO_EMAILPROPRIETARIO+","
         
            		+DBNames.ATTR_PRODOTTO_APAGINA+","
            		+DBNames.ATTR_PRODOTTO_DAPAGINA+","
            		+DBNames.ATTR_PRODOTTO_INDIRIZZOWEB+","
            		+DBNames.ATTR_PRODOTTO_LISTACOLLABORATORI+","
            		+DBNames.ATTR_PRODOTTO_PAROLECHIAVI+","
            		+DBNames.ATTR_PRODOTTO_EDITORE+","
            		+DBNames.ATTR_PRODOTTO_NUMVOLUME+","
            		+DBNames.ATTR_PRODOTTO_TOTALEPAGINE+","
            		+DBNames.ATTR_PRODOTTO_DESCRIZIONECONTENUTI 
            		+") values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            st=conn.prepareStatement(query);
            st.setString(1,p.getIsbn());
            st.setString(2,p.getTitolo());
            st.setString(3,p.getAnnoPubblicazione());
            st.setString(4,p.getFormatoPubblicazione());
            st.setString(5,p.getCodiceDOI());
            st.setString(6,p.getDiffusione());
            st.setString(7,p.getNote());
            st.setString(8,p.getStato());
            st.setBoolean(9,p.getBozza());
            st.setString(10,p.getTipologia());
            st.setString(11,p.getProprietario());
            st.setInt(12,p.getApagina());
            st.setInt(13,p.getDaPagina());
            st.setString(14,p.getIndirizzoWeb());
            st.setString(15,p.getListaCollaboratori());
            st.setString(16,p.getParoleChiavi());
            st.setString(17,p.getEditore());
            st.setInt(18,p.getNumVolume());
            st.setInt(19,p.getTotalePagine());
            st.setString(20,p.getDescrizioneContenuti());
   
            st.executeUpdate();
            conn.commit();
            
            
            //Aggiungo un entry nella tabella collaborazioni per ogni utente indicato come coautore
            StringTokenizer collaboratori = new StringTokenizer(p.getListaCollaboratori(),";");
            
    		while (collaboratori.hasMoreElements())
    		{
    			query="INSERT INTO "+DBNames.TABLE_COLLABORAZIONI+" ("
    					+DBNames.ATTR_COLLABORAZIONI_COLLABORATORE+","
    					+DBNames.ATTR_COLLABORAZIONI_PRODOTTO_ISBN+","
    					+DBNames.ATTR_COLLABORAZIONI_PROPRIETARIO+","
    					+DBNames.ATTR_COLLABORAZIONI_CONVALIDATO+") values (?,?,?,?)";
    			 st=conn.prepareStatement(query);
    	         st.setString(1,(String) collaboratori.nextElement());
    	         st.setString(2,p.getIsbn());
    	         st.setString(3,p.getProprietario());
    	         st.setBoolean(4,false);
    	         st.executeUpdate();
    	         conn.commit();
    		}
		}
		finally 
        {
            st.close();
            DBConnectionPool.releaseConnection(conn);
        }   
	}

	/**
	 * Metodo che permette di eliminare un prodotto
	 * @param isbn
	 * @throws SQLException
	 */
	public void eliminaProdotto(String isbn) throws SQLException
	{
		Connection conn=null;
		PreparedStatement st=null;
		String query;
        try 
        {
            conn = DBConnectionPool.getConnection();
            query= "DELETE FROM "+DBNames.TABLE_PRODOTTO+" WHERE "+DBNames.ATTR_PRODOTTO_ISBN+"='"+isbn+"'";

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
	 *Metodo che permette di modificare di un prodotto
	 * @param p prodotto
	 * 
	 * @throws SQLException
	 */
	public void modificaProdotto(Prodotto p) throws SQLException
	{
		Connection conn=null;
		PreparedStatement st=null;
		String query;
		try
		{
			conn=DBConnectionPool.getConnection();
			query="UPDATE "+DBNames.TABLE_PRODOTTO+" SET "+DBNames.ATTR_PRODOTTO_ANNOPUBBLICAZIONE+"="+p.getAnnoPubblicazione()+","
					+DBNames.ATTR_PRODOTTO_TITOLO+"="+p.getTitolo()+","
            		+DBNames.ATTR_PRODOTTO_FORMATOPUBBLICAZIONE+"="+p.getFormatoPubblicazione()+","
            		+DBNames.ATTR_PRODOTTO_CODICEDOI+"="+p.getCodiceDOI()+","
            		+DBNames.ATTR_PRODOTTO_DIFFUSIONE+"="+p.getDiffusione()+","
            		+DBNames.ATTR_PRODOTTO_NOTE+"="+p.getNote()+","
            		+DBNames.ATTR_PRODOTTO_STATO+"="+p.getStato()+","
            		+DBNames.ATTR_PRODOTTO_BOZZA+"="+p.getBozza()+","
            		+DBNames.ATTR_PRODOTTO_TIPOLOGIA+"="+p.getTipologia()+","
            		+DBNames.ATTR_PRODOTTO_EMAILPROPRIETARIO+"="+p.getProprietario()+","
            		+DBNames.ATTR_PRODOTTO_LISTACOLLABORATORI+"="+p.getListaCollaboratori()+","
            		+DBNames.ATTR_PRODOTTO_DESCRIZIONECONTENUTI+"="+p.getDescrizioneContenuti()+","
            		+DBNames.ATTR_PRODOTTO_INDIRIZZOWEB+"="+p.getIndirizzoWeb()+","
            		+DBNames.ATTR_PRODOTTO_PAROLECHIAVI+"="+p.getParoleChiavi()+","
            		+DBNames.ATTR_PRODOTTO_EDITORE+"="+p.getEditore()+","
            		+DBNames.ATTR_PRODOTTO_NUMVOLUME+"="+p.getNumVolume()+","
            		+DBNames.ATTR_PRODOTTO_TOTALEPAGINE+"="+p.getTotalePagine()+","
            		+DBNames.ATTR_PRODOTTO_DAPAGINA+"="+p.getDaPagina()+","
            		+DBNames.ATTR_PRODOTTO_APAGINA+"="+p.getApagina()+" "
					+"WHERE "+DBNames.ATTR_PRODOTTO_ISBN+"="+"'"+p.getIsbn()+"'";
			
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
	 * Metodo che permette di sottomettere un prodotto al MIUR
	 * @param proprietario proprietario del prodotto
	 * @param isbn del prodotto da sottomettere
	 * @throws SQLException
	 */
	public void sottomettiAlMIUR(String proprietario,String isbn)throws SQLException
	{
		Connection conn=null;
		PreparedStatement st=null;
		String query;
		
		try
		{
			conn = DBConnectionPool.getConnection();
            query="INSERT INTO "+DBNames.TABLE_PRODOTTO+"("
            		+DBNames.ATTR_PRODOTTO_EMAILPROPRIETARIO+","
            		+DBNames.ATTR_PRODOTTO_ISBN
            		+") values (?,?)";
            st=conn.prepareStatement(query);
            st.setString(1, proprietario);
            st.setString(2,isbn);
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
	 * Metodo che permette di visualizzare i dettagli di un dato prodotto
	 * @param isbn del prodotto
	 * @return Il prodotto con i dettagli 
	 * @throws SQLException 
	 */
	public Prodotto visualizzaDettagliProdotto(String isbn) throws SQLException
	{
		
		Statement st=null;
		ResultSet ris=null;
		String query;
		Connection conn=null;
		
		try
		{
			conn = DBConnectionPool.getConnection();
			
            query="SELECT * FROM "+DBNames.TABLE_PRODOTTO+
            		" WHERE "+DBNames.ATTR_PRODOTTO_ISBN+"="+isbn;
            
            st=conn.createStatement();
    		ris=st.executeQuery(query);
    		ris.next();
    		Prodotto p=new Prodotto(ris.getString(DBNames.ATTR_PRODOTTO_ISBN),ris.getString(DBNames.ATTR_PRODOTTO_TITOLO)
    				,ris.getString(DBNames.ATTR_PRODOTTO_ANNOPUBBLICAZIONE),ris.getString(DBNames.ATTR_PRODOTTO_FORMATOPUBBLICAZIONE)
    				,ris.getString(DBNames.ATTR_PRODOTTO_CODICEDOI),ris.getString(DBNames.ATTR_PRODOTTO_DIFFUSIONE)
    				,ris.getString(DBNames.ATTR_PRODOTTO_NOTE),ris.getString(DBNames.ATTR_PRODOTTO_STATO)
    				,ris.getBoolean(DBNames.ATTR_PRODOTTO_BOZZA),ris.getString(DBNames.ATTR_PRODOTTO_TIPOLOGIA)
    				,ris.getString(DBNames.ATTR_PRODOTTO_EMAILPROPRIETARIO),ris.getString(DBNames.ATTR_PRODOTTO_LISTACOLLABORATORI)
    				,ris.getString(DBNames.ATTR_PRODOTTO_DESCRIZIONECONTENUTI),ris.getString(DBNames.ATTR_PRODOTTO_INDIRIZZOWEB)
    				,ris.getString(DBNames.ATTR_PRODOTTO_PAROLECHIAVI),ris.getString(DBNames.ATTR_PRODOTTO_EDITORE)
    				,ris.getInt(DBNames.ATTR_PRODOTTO_NUMVOLUME),ris.getInt(DBNames.ATTR_PRODOTTO_TOTALEPAGINE)
    				,ris.getInt(DBNames.ATTR_PRODOTTO_DAPAGINA),ris.getInt(DBNames.ATTR_PRODOTTO_APAGINA));
    		
    		return p;
		}
		finally 
        {
            st.close();
            DBConnectionPool.releaseConnection(conn);
        }
	}
	
	/**
	 * Metodo che restituisce la lista dei proodotti personali dove l'utente compare come proprietario
	 * @param utente proprietario del prodotto
	 * @return lista dei prodotti
	 * @throws SQLException
	 */
	public ArrayList<Prodotto> visualizzaProdottiPersonali(String utente)throws SQLException
	{
		Statement st=null;
		ResultSet ris=null;
		String query;
		Connection conn=null;
		
		try
		{
			ArrayList<Prodotto> listProdotto=new ArrayList<Prodotto>();
			conn = DBConnectionPool.getConnection();
			
            query="SELECT * FROM "+DBNames.TABLE_PRODOTTO
            		+" WHERE "+DBNames.ATTR_PRODOTTO_EMAILPROPRIETARIO+"='"+utente+"'";
          
            st=conn.createStatement();
    		ris=st.executeQuery(query);
    		while(ris.next())
			{
    			Prodotto p=new Prodotto(ris.getString(DBNames.ATTR_PRODOTTO_ISBN),ris.getString(DBNames.ATTR_PRODOTTO_TITOLO)
        				,ris.getString(DBNames.ATTR_PRODOTTO_ANNOPUBBLICAZIONE),ris.getString(DBNames.ATTR_PRODOTTO_FORMATOPUBBLICAZIONE)
        				,ris.getString(DBNames.ATTR_PRODOTTO_CODICEDOI),ris.getString(DBNames.ATTR_PRODOTTO_DIFFUSIONE)
        				,ris.getString(DBNames.ATTR_PRODOTTO_NOTE),ris.getString(DBNames.ATTR_PRODOTTO_STATO)
        				,ris.getBoolean(DBNames.ATTR_PRODOTTO_BOZZA),ris.getString(DBNames.ATTR_PRODOTTO_TIPOLOGIA)
        				,ris.getString(DBNames.ATTR_PRODOTTO_EMAILPROPRIETARIO),ris.getString(DBNames.ATTR_PRODOTTO_LISTACOLLABORATORI)
        				,ris.getString(DBNames.ATTR_PRODOTTO_DESCRIZIONECONTENUTI),ris.getString(DBNames.ATTR_PRODOTTO_INDIRIZZOWEB)
        				,ris.getString(DBNames.ATTR_PRODOTTO_PAROLECHIAVI),ris.getString(DBNames.ATTR_PRODOTTO_EDITORE)
        				,ris.getInt(DBNames.ATTR_PRODOTTO_NUMVOLUME),ris.getInt(DBNames.ATTR_PRODOTTO_TOTALEPAGINE)
        				,ris.getInt(DBNames.ATTR_PRODOTTO_DAPAGINA),ris.getInt(DBNames.ATTR_PRODOTTO_APAGINA));
        	
				listProdotto.add(p);
			}
    		return listProdotto;
		}
		finally 
        {
            st.close();
            DBConnectionPool.releaseConnection(conn);
        }
    
	}
	
	/**
	 *Metodo che permette di visualizzare tutti i prodotti personali(proprietario)
	 *anche quelli dove l'utente ha confermato di essere un coautore
	 * @param utente del prodotto
	 * @return lista dei prodotti
	 * @throws SQLException
	 */
	public ArrayList<Prodotto> visualizzaProdottiProprietarioCoautore(String utente)throws SQLException
	{
		Statement st=null;
		Statement st1=null;
		ResultSet ris=null;
		String query;
		Connection conn=null;
		
		try
		{
			ArrayList<Prodotto> listProdotto=new ArrayList<Prodotto>();
			conn = DBConnectionPool.getConnection();
			
            query="SELECT * FROM "+DBNames.TABLE_PRODOTTO
            		+" WHERE "+DBNames.ATTR_PRODOTTO_EMAILPROPRIETARIO+"='"+utente+"'";
          
            st=conn.createStatement();
    		ris=st.executeQuery(query);
    		while(ris.next())
			{
    			Prodotto p=new Prodotto(ris.getString(DBNames.ATTR_PRODOTTO_ISBN),ris.getString(DBNames.ATTR_PRODOTTO_TITOLO)
        				,ris.getString(DBNames.ATTR_PRODOTTO_ANNOPUBBLICAZIONE),ris.getString(DBNames.ATTR_PRODOTTO_FORMATOPUBBLICAZIONE)
        				,ris.getString(DBNames.ATTR_PRODOTTO_CODICEDOI),ris.getString(DBNames.ATTR_PRODOTTO_DIFFUSIONE)
        				,ris.getString(DBNames.ATTR_PRODOTTO_NOTE),ris.getString(DBNames.ATTR_PRODOTTO_STATO)
        				,ris.getBoolean(DBNames.ATTR_PRODOTTO_BOZZA),ris.getString(DBNames.ATTR_PRODOTTO_TIPOLOGIA)
        				,ris.getString(DBNames.ATTR_PRODOTTO_EMAILPROPRIETARIO),ris.getString(DBNames.ATTR_PRODOTTO_LISTACOLLABORATORI)
        				,ris.getString(DBNames.ATTR_PRODOTTO_DESCRIZIONECONTENUTI),ris.getString(DBNames.ATTR_PRODOTTO_INDIRIZZOWEB)
        				,ris.getString(DBNames.ATTR_PRODOTTO_PAROLECHIAVI),ris.getString(DBNames.ATTR_PRODOTTO_EDITORE)
        				,ris.getInt(DBNames.ATTR_PRODOTTO_NUMVOLUME),ris.getInt(DBNames.ATTR_PRODOTTO_TOTALEPAGINE)
        				,ris.getInt(DBNames.ATTR_PRODOTTO_DAPAGINA),ris.getInt(DBNames.ATTR_PRODOTTO_APAGINA));
        	
				listProdotto.add(p);
			}
    		
    		//Ora prendo i prodotti dove sono indicato come coautore e ho confermato
    		query="SELECT * FROM "+DBNames.TABLE_PRODOTTO+","+DBNames.TABLE_COLLABORAZIONI
            		+" WHERE "+DBNames.TABLE_PRODOTTO+"."+DBNames.ATTR_PRODOTTO_ISBN+"='"+DBNames.TABLE_COLLABORAZIONI+"."+DBNames.ATTR_COLLABORAZIONI_PRODOTTO_ISBN+"'"
            		+" AND "+DBNames.TABLE_COLLABORAZIONI+"."+DBNames.ATTR_COLLABORAZIONI_COLLABORATORE+"='"+utente+"' AND "
            		+DBNames.TABLE_COLLABORAZIONI+"."+DBNames.ATTR_COLLABORAZIONI_CONVALIDATO+"="+true;
            
            st1=conn.createStatement();
    		ris=st1.executeQuery(query);
    		while(ris.next())
			{
    			Prodotto p=new Prodotto(ris.getString(DBNames.ATTR_PRODOTTO_ISBN),ris.getString(DBNames.ATTR_PRODOTTO_TITOLO)
        				,ris.getString(DBNames.ATTR_PRODOTTO_ANNOPUBBLICAZIONE),ris.getString(DBNames.ATTR_PRODOTTO_FORMATOPUBBLICAZIONE)
        				,ris.getString(DBNames.ATTR_PRODOTTO_CODICEDOI),ris.getString(DBNames.ATTR_PRODOTTO_DIFFUSIONE)
        				,ris.getString(DBNames.ATTR_PRODOTTO_NOTE),ris.getString(DBNames.ATTR_PRODOTTO_STATO)
        				,ris.getBoolean(DBNames.ATTR_PRODOTTO_BOZZA),ris.getString(DBNames.ATTR_PRODOTTO_TIPOLOGIA)
        				,ris.getString(DBNames.ATTR_PRODOTTO_EMAILPROPRIETARIO),ris.getString(DBNames.ATTR_PRODOTTO_LISTACOLLABORATORI)
        				,ris.getString(DBNames.ATTR_PRODOTTO_DESCRIZIONECONTENUTI),ris.getString(DBNames.ATTR_PRODOTTO_INDIRIZZOWEB)
        				,ris.getString(DBNames.ATTR_PRODOTTO_PAROLECHIAVI),ris.getString(DBNames.ATTR_PRODOTTO_EDITORE)
        				,ris.getInt(DBNames.ATTR_PRODOTTO_NUMVOLUME),ris.getInt(DBNames.ATTR_PRODOTTO_TOTALEPAGINE)
        				,ris.getInt(DBNames.ATTR_PRODOTTO_DAPAGINA),ris.getInt(DBNames.ATTR_PRODOTTO_APAGINA));
        	
				listProdotto.add(p);
			}
    		return listProdotto;
		}
		finally 
        {
            st.close();
            st1.close();
            DBConnectionPool.releaseConnection(conn);
        }
	}
	
	/**
	 *Metodo che permette di visualizzare tutti i prodotti
	 *dove l'utente non ha confermato di essere un coautore
	 * @param utente del prodotto
	 * @return lista dei prodotti
	 * @throws SQLException
	 */
	public ArrayList<Prodotto> visualizzaProdottiCoautoreNonConvalidati(String utente)throws SQLException
	{
		Statement st1=null;
		ResultSet ris=null;
		String query;
		Connection conn=null;
		
		try
		{
			ArrayList<Prodotto> listProdotto=new ArrayList<Prodotto>();
			conn = DBConnectionPool.getConnection();
			
            query="SELECT * FROM "+DBNames.TABLE_PRODOTTO+","+DBNames.TABLE_COLLABORAZIONI
            		+" WHERE "+DBNames.TABLE_PRODOTTO+"."+DBNames.ATTR_PRODOTTO_ISBN+"='"+DBNames.TABLE_COLLABORAZIONI+"."+DBNames.ATTR_COLLABORAZIONI_PRODOTTO_ISBN+"'"
            		+" AND "+DBNames.TABLE_COLLABORAZIONI+"."+DBNames.ATTR_COLLABORAZIONI_COLLABORATORE+"='"+utente+"' AND "
            		+DBNames.TABLE_COLLABORAZIONI+"."+DBNames.ATTR_COLLABORAZIONI_CONVALIDATO+"="+false;
            
            st1=conn.createStatement();
    		ris=st1.executeQuery(query);
    		while(ris.next())
			{
    			Prodotto p=new Prodotto(ris.getString(DBNames.ATTR_PRODOTTO_ISBN),ris.getString(DBNames.ATTR_PRODOTTO_TITOLO)
        				,ris.getString(DBNames.ATTR_PRODOTTO_ANNOPUBBLICAZIONE),ris.getString(DBNames.ATTR_PRODOTTO_FORMATOPUBBLICAZIONE)
        				,ris.getString(DBNames.ATTR_PRODOTTO_CODICEDOI),ris.getString(DBNames.ATTR_PRODOTTO_DIFFUSIONE)
        				,ris.getString(DBNames.ATTR_PRODOTTO_NOTE),ris.getString(DBNames.ATTR_PRODOTTO_STATO)
        				,ris.getBoolean(DBNames.ATTR_PRODOTTO_BOZZA),ris.getString(DBNames.ATTR_PRODOTTO_TIPOLOGIA)
        				,ris.getString(DBNames.ATTR_PRODOTTO_EMAILPROPRIETARIO),ris.getString(DBNames.ATTR_PRODOTTO_LISTACOLLABORATORI)
        				,ris.getString(DBNames.ATTR_PRODOTTO_DESCRIZIONECONTENUTI),ris.getString(DBNames.ATTR_PRODOTTO_INDIRIZZOWEB)
        				,ris.getString(DBNames.ATTR_PRODOTTO_PAROLECHIAVI),ris.getString(DBNames.ATTR_PRODOTTO_EDITORE)
        				,ris.getInt(DBNames.ATTR_PRODOTTO_NUMVOLUME),ris.getInt(DBNames.ATTR_PRODOTTO_TOTALEPAGINE)
        				,ris.getInt(DBNames.ATTR_PRODOTTO_DAPAGINA),ris.getInt(DBNames.ATTR_PRODOTTO_APAGINA));
        	
				listProdotto.add(p);
			}
    		return listProdotto;
		}
		finally 
        {
            st1.close();
            DBConnectionPool.releaseConnection(conn);
        }
	}
	
	/**
	 *Metodo che permette di visualizzare tutti i prodotti
	 *dove l'utente ha confermato di essere un coautore
	 * @param utente del prodotto
	 * @return lista dei prodotti
	 * @throws SQLException
	 */
	public ArrayList<Prodotto> visualizzaProdottiCoautoreConvalidato(String utente)throws SQLException
	{
		Statement st1=null;
		ResultSet ris=null;
		String query;
		Connection conn=null;
		
		try
		{
			ArrayList<Prodotto> listProdotto=new ArrayList<Prodotto>();
			conn = DBConnectionPool.getConnection();
			
            query="SELECT * FROM "+DBNames.TABLE_PRODOTTO+","+DBNames.TABLE_COLLABORAZIONI
            		+" WHERE "+DBNames.TABLE_PRODOTTO+"."+DBNames.ATTR_PRODOTTO_ISBN+"='"+DBNames.TABLE_COLLABORAZIONI+"."+DBNames.ATTR_COLLABORAZIONI_PRODOTTO_ISBN+"'"
            		+" AND "+DBNames.TABLE_COLLABORAZIONI+"."+DBNames.ATTR_COLLABORAZIONI_COLLABORATORE+"='"+utente+"' AND "
            		+DBNames.TABLE_COLLABORAZIONI+"."+DBNames.ATTR_COLLABORAZIONI_CONVALIDATO+"="+true;
            
            st1=conn.createStatement();
    		ris=st1.executeQuery(query);
    		while(ris.next())
			{
    			Prodotto p=new Prodotto(ris.getString(DBNames.ATTR_PRODOTTO_ISBN),ris.getString(DBNames.ATTR_PRODOTTO_TITOLO)
        				,ris.getString(DBNames.ATTR_PRODOTTO_ANNOPUBBLICAZIONE),ris.getString(DBNames.ATTR_PRODOTTO_FORMATOPUBBLICAZIONE)
        				,ris.getString(DBNames.ATTR_PRODOTTO_CODICEDOI),ris.getString(DBNames.ATTR_PRODOTTO_DIFFUSIONE)
        				,ris.getString(DBNames.ATTR_PRODOTTO_NOTE),ris.getString(DBNames.ATTR_PRODOTTO_STATO)
        				,ris.getBoolean(DBNames.ATTR_PRODOTTO_BOZZA),ris.getString(DBNames.ATTR_PRODOTTO_TIPOLOGIA)
        				,ris.getString(DBNames.ATTR_PRODOTTO_EMAILPROPRIETARIO),ris.getString(DBNames.ATTR_PRODOTTO_LISTACOLLABORATORI)
        				,ris.getString(DBNames.ATTR_PRODOTTO_DESCRIZIONECONTENUTI),ris.getString(DBNames.ATTR_PRODOTTO_INDIRIZZOWEB)
        				,ris.getString(DBNames.ATTR_PRODOTTO_PAROLECHIAVI),ris.getString(DBNames.ATTR_PRODOTTO_EDITORE)
        				,ris.getInt(DBNames.ATTR_PRODOTTO_NUMVOLUME),ris.getInt(DBNames.ATTR_PRODOTTO_TOTALEPAGINE)
        				,ris.getInt(DBNames.ATTR_PRODOTTO_DAPAGINA),ris.getInt(DBNames.ATTR_PRODOTTO_APAGINA));
        	
				listProdotto.add(p);
			}
    		return listProdotto;
		}
		finally 
        {
            st1.close();
            DBConnectionPool.releaseConnection(conn);
        }
	}
	
	/**
	 * Metodo che permete di ricercare un prodotto in base al suo titolo
	 * @param titolo del prodotto
	 * @return lsita prodotti
	 */
	public ArrayList<Prodotto> ricercaProdottoPerTitoloProdotto(String titolo)throws SQLException
	{
		Statement st=null;
		ResultSet ris=null;
		String query;
		Connection conn=null;
		
		try
		{
			ArrayList<Prodotto> listProdotto=new ArrayList<Prodotto>();
			conn = DBConnectionPool.getConnection();
			
            query="SELECT * FROM "+DBNames.TABLE_PRODOTTO+
            		" WHERE "+DBNames.ATTR_PRODOTTO_TITOLO+"='"+titolo+"'";
            
            st=conn.createStatement();
    		ris=st.executeQuery(query);
    		while(ris.next())
			{
    			Prodotto p=new Prodotto(ris.getString(DBNames.ATTR_PRODOTTO_ISBN),ris.getString(DBNames.ATTR_PRODOTTO_TITOLO)
        				,ris.getString(DBNames.ATTR_PRODOTTO_ANNOPUBBLICAZIONE),ris.getString(DBNames.ATTR_PRODOTTO_FORMATOPUBBLICAZIONE)
        				,ris.getString(DBNames.ATTR_PRODOTTO_CODICEDOI),ris.getString(DBNames.ATTR_PRODOTTO_DIFFUSIONE)
        				,ris.getString(DBNames.ATTR_PRODOTTO_NOTE),ris.getString(DBNames.ATTR_PRODOTTO_STATO)
        				,ris.getBoolean(DBNames.ATTR_PRODOTTO_BOZZA),ris.getString(DBNames.ATTR_PRODOTTO_TIPOLOGIA)
        				,ris.getString(DBNames.ATTR_PRODOTTO_EMAILPROPRIETARIO),ris.getString(DBNames.ATTR_PRODOTTO_LISTACOLLABORATORI)
        				,ris.getString(DBNames.ATTR_PRODOTTO_DESCRIZIONECONTENUTI),ris.getString(DBNames.ATTR_PRODOTTO_INDIRIZZOWEB)
        				,ris.getString(DBNames.ATTR_PRODOTTO_PAROLECHIAVI),ris.getString(DBNames.ATTR_PRODOTTO_EDITORE)
        				,ris.getInt(DBNames.ATTR_PRODOTTO_NUMVOLUME),ris.getInt(DBNames.ATTR_PRODOTTO_TOTALEPAGINE)
        				,ris.getInt(DBNames.ATTR_PRODOTTO_DAPAGINA),ris.getInt(DBNames.ATTR_PRODOTTO_APAGINA));
        	
				listProdotto.add(p);
			}
    		return listProdotto;
		}
		finally 
        {
            st.close();
            DBConnectionPool.releaseConnection(conn);
        }
	}
	
	/**
	 * Metodo che permete di ricercare un prodotto in base al suo titolo
	 * @param titolo del prodotto
	 * @return lsita prodotti
	 */
	public ArrayList<Prodotto> ricercaProdottoPerTitoloProdotto(String titolo,String proprietario)throws SQLException
	{
		Statement st=null;
		ResultSet ris=null;
		String query;
		Connection conn=null;
		
		try
		{
			ArrayList<Prodotto> listProdotto=new ArrayList<Prodotto>();
			conn = DBConnectionPool.getConnection();
			
            query="SELECT * FROM "+DBNames.TABLE_PRODOTTO+
            		" WHERE "+DBNames.ATTR_PRODOTTO_TITOLO+"='"+titolo+"'";
            
            st=conn.createStatement();
    		ris=st.executeQuery(query);
    		while(ris.next())
			{
    			Prodotto p=new Prodotto(ris.getString(DBNames.ATTR_PRODOTTO_ISBN),ris.getString(DBNames.ATTR_PRODOTTO_TITOLO)
        				,ris.getString(DBNames.ATTR_PRODOTTO_ANNOPUBBLICAZIONE),ris.getString(DBNames.ATTR_PRODOTTO_FORMATOPUBBLICAZIONE)
        				,ris.getString(DBNames.ATTR_PRODOTTO_CODICEDOI),ris.getString(DBNames.ATTR_PRODOTTO_DIFFUSIONE)
        				,ris.getString(DBNames.ATTR_PRODOTTO_NOTE),ris.getString(DBNames.ATTR_PRODOTTO_STATO)
        				,ris.getBoolean(DBNames.ATTR_PRODOTTO_BOZZA),ris.getString(DBNames.ATTR_PRODOTTO_TIPOLOGIA)
        				,ris.getString(DBNames.ATTR_PRODOTTO_EMAILPROPRIETARIO),ris.getString(DBNames.ATTR_PRODOTTO_LISTACOLLABORATORI)
        				,ris.getString(DBNames.ATTR_PRODOTTO_DESCRIZIONECONTENUTI),ris.getString(DBNames.ATTR_PRODOTTO_INDIRIZZOWEB)
        				,ris.getString(DBNames.ATTR_PRODOTTO_PAROLECHIAVI),ris.getString(DBNames.ATTR_PRODOTTO_EDITORE)
        				,ris.getInt(DBNames.ATTR_PRODOTTO_NUMVOLUME),ris.getInt(DBNames.ATTR_PRODOTTO_TOTALEPAGINE)
        				,ris.getInt(DBNames.ATTR_PRODOTTO_DAPAGINA),ris.getInt(DBNames.ATTR_PRODOTTO_APAGINA));
        	
				listProdotto.add(p);
			}
    		return listProdotto;
		}
		finally 
        {
            st.close();
            DBConnectionPool.releaseConnection(conn);
        }
	}
	
	/**
	 * Metodo che permette di ricercare i prodotti in un intervallo di anni es. Da 1995 a 2000
	 * @param anno della pubblicazione
	 * @param anno della pubblicazione
	 * @return lista prodotti
	 * @throws SQLException
	 */
	public ArrayList<Prodotto> ricercaProdottoPerAnni(int anno,int anno1)throws SQLException
	{
		Statement st=null;
		ResultSet ris=null;
		String query;
		Connection conn=null;
		
		try
		{
			ArrayList<Prodotto> listProdotto=new ArrayList<Prodotto>();
			conn = DBConnectionPool.getConnection();
			
            query="SELECT * FROM "+DBNames.TABLE_PRODOTTO+
            		" WHERE "+DBNames.ATTR_PRODOTTO_ANNOPUBBLICAZIONE+">="+anno+" AND "+DBNames.ATTR_PRODOTTO_ANNOPUBBLICAZIONE+"<="+anno1;
            
            st=conn.createStatement();
    		ris=st.executeQuery(query);
    		while(ris.next())
			{
    			Prodotto p=new Prodotto(ris.getString(DBNames.ATTR_PRODOTTO_ISBN),ris.getString(DBNames.ATTR_PRODOTTO_TITOLO)
        				,ris.getString(DBNames.ATTR_PRODOTTO_ANNOPUBBLICAZIONE),ris.getString(DBNames.ATTR_PRODOTTO_FORMATOPUBBLICAZIONE)
        				,ris.getString(DBNames.ATTR_PRODOTTO_CODICEDOI),ris.getString(DBNames.ATTR_PRODOTTO_DIFFUSIONE)
        				,ris.getString(DBNames.ATTR_PRODOTTO_NOTE),ris.getString(DBNames.ATTR_PRODOTTO_STATO)
        				,ris.getBoolean(DBNames.ATTR_PRODOTTO_BOZZA),ris.getString(DBNames.ATTR_PRODOTTO_TIPOLOGIA)
        				,ris.getString(DBNames.ATTR_PRODOTTO_EMAILPROPRIETARIO),ris.getString(DBNames.ATTR_PRODOTTO_LISTACOLLABORATORI)
        				,ris.getString(DBNames.ATTR_PRODOTTO_DESCRIZIONECONTENUTI),ris.getString(DBNames.ATTR_PRODOTTO_INDIRIZZOWEB)
        				,ris.getString(DBNames.ATTR_PRODOTTO_PAROLECHIAVI),ris.getString(DBNames.ATTR_PRODOTTO_EDITORE)
        				,ris.getInt(DBNames.ATTR_PRODOTTO_NUMVOLUME),ris.getInt(DBNames.ATTR_PRODOTTO_TOTALEPAGINE)
        				,ris.getInt(DBNames.ATTR_PRODOTTO_DAPAGINA),ris.getInt(DBNames.ATTR_PRODOTTO_APAGINA));
        	
				listProdotto.add(p);
			}
    		return listProdotto;
		}
		finally 
        {
            st.close();
            DBConnectionPool.releaseConnection(conn);
        }
	}
	
	/**
	 * Metodo che permette di ricercare i prodotti in base al titolo della rivista
	 * @param titoloRivista titolo della rivista su cui è pubblicato il prodotto
	 * @return lista prodotti
	 * @throws SQLException
	 */
	public ArrayList<Prodotto> ricercaProdottoPerTitoloRivista(String titoloRivista)throws SQLException
	{
		Statement st=null;
		ResultSet ris=null;
		String query;
		Connection conn=null;
		
		try
		{
			ArrayList<Prodotto> listProdotto=new ArrayList<Prodotto>();
			conn = DBConnectionPool.getConnection();
			
            query="SELECT * FROM "+DBNames.TABLE_PRODOTTO+","+DBNames.TABLE_PUBBLICAZIONESURIVISTA+","+DBNames.TABLE_RIVISTA+
            		" WHERE "+DBNames.TABLE_RIVISTA+"."+DBNames.ATTR_RIVISTA_ISSN+"="+DBNames.TABLE_PUBBLICAZIONESURIVISTA+"."+DBNames.ATTR_PUBBLICAZIONESURIVISTA_RIVISTA_ISSN+" AND "
            		+DBNames.TABLE_PRODOTTO+"."+DBNames.ATTR_PRODOTTO_ISBN+"="+DBNames.TABLE_PUBBLICAZIONESURIVISTA+"."+DBNames.ATTR_PUBBLICAZIONESURIVISTA_PRODOTTO_ISBN
            		+" AND "+DBNames.TABLE_RIVISTA+"."+DBNames.ATTR_RIVISTA_NOME+"='"+titoloRivista+"'";
            		
            st=conn.createStatement();
    		ris=st.executeQuery(query);
    		while(ris.next())
			{
    			Prodotto p=new Prodotto(ris.getString(DBNames.ATTR_PRODOTTO_ISBN),ris.getString(DBNames.ATTR_PRODOTTO_TITOLO)
        				,ris.getString(DBNames.ATTR_PRODOTTO_ANNOPUBBLICAZIONE),ris.getString(DBNames.ATTR_PRODOTTO_FORMATOPUBBLICAZIONE)
        				,ris.getString(DBNames.ATTR_PRODOTTO_CODICEDOI),ris.getString(DBNames.ATTR_PRODOTTO_DIFFUSIONE)
        				,ris.getString(DBNames.ATTR_PRODOTTO_NOTE),ris.getString(DBNames.ATTR_PRODOTTO_STATO)
        				,ris.getBoolean(DBNames.ATTR_PRODOTTO_BOZZA),ris.getString(DBNames.ATTR_PRODOTTO_TIPOLOGIA)
        				,ris.getString(DBNames.ATTR_PRODOTTO_EMAILPROPRIETARIO),ris.getString(DBNames.ATTR_PRODOTTO_LISTACOLLABORATORI)
        				,ris.getString(DBNames.ATTR_PRODOTTO_DESCRIZIONECONTENUTI),ris.getString(DBNames.ATTR_PRODOTTO_INDIRIZZOWEB)
        				,ris.getString(DBNames.ATTR_PRODOTTO_PAROLECHIAVI),ris.getString(DBNames.ATTR_PRODOTTO_EDITORE)
        				,ris.getInt(DBNames.ATTR_PRODOTTO_NUMVOLUME),ris.getInt(DBNames.ATTR_PRODOTTO_TOTALEPAGINE)
        				,ris.getInt(DBNames.ATTR_PRODOTTO_DAPAGINA),ris.getInt(DBNames.ATTR_PRODOTTO_APAGINA));
        	
				listProdotto.add(p);
			}
    		return listProdotto;
		}
		finally 
        {
            st.close();
            DBConnectionPool.releaseConnection(conn);
        }
	}
	
	/**
	 * Metodo che permette di ricercare i prodotti in base all'issn della rivista
	 * @param issn della rivista
	 * @return lista prodotti
	 * @throws SQLException
	 */
	public ArrayList<Prodotto> ricercaProdottoPerIssnRivista(String issn)throws SQLException
	{
		Statement st=null;
		ResultSet ris=null;
		String query;
		Connection conn=null;
		
		try
		{
			ArrayList<Prodotto> listProdotto=new ArrayList<Prodotto>();
			conn = DBConnectionPool.getConnection();
			
            query="SELECT * FROM "+DBNames.TABLE_PRODOTTO+","+DBNames.TABLE_PUBBLICAZIONESURIVISTA+","+DBNames.TABLE_RIVISTA+
            		" WHERE "+DBNames.TABLE_RIVISTA+"."+DBNames.ATTR_RIVISTA_ISSN+"="+DBNames.TABLE_PUBBLICAZIONESURIVISTA+"."+DBNames.ATTR_PUBBLICAZIONESURIVISTA_RIVISTA_ISSN+" AND "
            		+DBNames.TABLE_PRODOTTO+"."+DBNames.ATTR_PRODOTTO_ISBN+"="+DBNames.TABLE_PUBBLICAZIONESURIVISTA+"."+DBNames.ATTR_PUBBLICAZIONESURIVISTA_PRODOTTO_ISBN
            		+" AND "+DBNames.TABLE_RIVISTA+"."+DBNames.ATTR_RIVISTA_ISSN+"='"+issn+"'";
            		
            st=conn.createStatement();
    		ris=st.executeQuery(query);
    		while(ris.next())
			{
    			Prodotto p=new Prodotto(ris.getString(DBNames.ATTR_PRODOTTO_ISBN),ris.getString(DBNames.ATTR_PRODOTTO_TITOLO)
        				,ris.getString(DBNames.ATTR_PRODOTTO_ANNOPUBBLICAZIONE),ris.getString(DBNames.ATTR_PRODOTTO_FORMATOPUBBLICAZIONE)
        				,ris.getString(DBNames.ATTR_PRODOTTO_CODICEDOI),ris.getString(DBNames.ATTR_PRODOTTO_DIFFUSIONE)
        				,ris.getString(DBNames.ATTR_PRODOTTO_NOTE),ris.getString(DBNames.ATTR_PRODOTTO_STATO)
        				,ris.getBoolean(DBNames.ATTR_PRODOTTO_BOZZA),ris.getString(DBNames.ATTR_PRODOTTO_TIPOLOGIA)
        				,ris.getString(DBNames.ATTR_PRODOTTO_EMAILPROPRIETARIO),ris.getString(DBNames.ATTR_PRODOTTO_LISTACOLLABORATORI)
        				,ris.getString(DBNames.ATTR_PRODOTTO_DESCRIZIONECONTENUTI),ris.getString(DBNames.ATTR_PRODOTTO_INDIRIZZOWEB)
        				,ris.getString(DBNames.ATTR_PRODOTTO_PAROLECHIAVI),ris.getString(DBNames.ATTR_PRODOTTO_EDITORE)
        				,ris.getInt(DBNames.ATTR_PRODOTTO_NUMVOLUME),ris.getInt(DBNames.ATTR_PRODOTTO_TOTALEPAGINE)
        				,ris.getInt(DBNames.ATTR_PRODOTTO_DAPAGINA),ris.getInt(DBNames.ATTR_PRODOTTO_APAGINA));
        	
				listProdotto.add(p);
			}
    		return listProdotto;
		}
		finally 
        {
            st.close();
            DBConnectionPool.releaseConnection(conn);
        }
	}

	/**
	 * Metodo che permette di ricercare i prodotti in base alla tipologia
	 * @param tipologia del prodotto
	 * @return lista prodotti
	 * @throws SQLException
	 */
	public ArrayList<Prodotto> ricercaProdottoPerTipologia(String tipologia)throws SQLException
	{
		Statement st=null;
		ResultSet ris=null;
		String query;
		Connection conn=null;
		
		try
		{
			ArrayList<Prodotto> listProdotto=new ArrayList<Prodotto>();
			conn = DBConnectionPool.getConnection();
			
            query="SELECT * FROM "+DBNames.TABLE_PRODOTTO+","+DBNames.TABLE_TIPOLOGIA
            		+DBNames.TABLE_PRODOTTO+"."+DBNames.ATTR_PRODOTTO_TIPOLOGIA+"="+DBNames.TABLE_TIPOLOGIA+"."+DBNames.ATTR_TIPOLOGIA_NOME
            		+" AND "+DBNames.TABLE_TIPOLOGIA+"."+DBNames.ATTR_TIPOLOGIA_NOME+"='"+tipologia+"'";
            		
            st=conn.createStatement();
    		ris=st.executeQuery(query);
    		while(ris.next())
			{
    			Prodotto p=new Prodotto(ris.getString(DBNames.ATTR_PRODOTTO_ISBN),ris.getString(DBNames.ATTR_PRODOTTO_TITOLO)
        				,ris.getString(DBNames.ATTR_PRODOTTO_ANNOPUBBLICAZIONE),ris.getString(DBNames.ATTR_PRODOTTO_FORMATOPUBBLICAZIONE)
        				,ris.getString(DBNames.ATTR_PRODOTTO_CODICEDOI),ris.getString(DBNames.ATTR_PRODOTTO_DIFFUSIONE)
        				,ris.getString(DBNames.ATTR_PRODOTTO_NOTE),ris.getString(DBNames.ATTR_PRODOTTO_STATO)
        				,ris.getBoolean(DBNames.ATTR_PRODOTTO_BOZZA),ris.getString(DBNames.ATTR_PRODOTTO_TIPOLOGIA)
        				,ris.getString(DBNames.ATTR_PRODOTTO_EMAILPROPRIETARIO),ris.getString(DBNames.ATTR_PRODOTTO_LISTACOLLABORATORI)
        				,ris.getString(DBNames.ATTR_PRODOTTO_DESCRIZIONECONTENUTI),ris.getString(DBNames.ATTR_PRODOTTO_INDIRIZZOWEB)
        				,ris.getString(DBNames.ATTR_PRODOTTO_PAROLECHIAVI),ris.getString(DBNames.ATTR_PRODOTTO_EDITORE)
        				,ris.getInt(DBNames.ATTR_PRODOTTO_NUMVOLUME),ris.getInt(DBNames.ATTR_PRODOTTO_TOTALEPAGINE)
        				,ris.getInt(DBNames.ATTR_PRODOTTO_DAPAGINA),ris.getInt(DBNames.ATTR_PRODOTTO_APAGINA));
        	
				listProdotto.add(p);
			}
    		return listProdotto;
		}
		finally 
        {
            st.close();
            DBConnectionPool.releaseConnection(conn);
        }
	}
	
	/**
	 * Metodo che permette di ricercare i prodotti personali dell'utente specificato e della tipologia data
	 * @param tipologia del prodotto
	 * @param utente proprietario o cautore del prodotto
	 * @return lista prodotti
	 * @throws SQLException
	 */
	public ArrayList<Prodotto> ricercaProdottoPerTipologia(String tipologia,String utente)throws SQLException
	{
		Statement st=null;
		ResultSet ris=null;
		String query;
		Connection conn=null;
		
		try
		{
			ArrayList<Prodotto> listProdotto=new ArrayList<Prodotto>();
			conn = DBConnectionPool.getConnection();
			
            query="SELECT * FROM "+DBNames.TABLE_PRODOTTO+","+DBNames.TABLE_COLLABORAZIONI+" WHERE "
            		+DBNames.TABLE_PRODOTTO+"."+DBNames.ATTR_PRODOTTO_ISBN+"="+DBNames.TABLE_COLLABORAZIONI+"."+DBNames.ATTR_COLLABORAZIONI_PRODOTTO_ISBN
            		+" AND "+DBNames.TABLE_PRODOTTO+"."+DBNames.ATTR_PRODOTTO_TIPOLOGIA+"='"+tipologia+"' AND ("
            		+DBNames.TABLE_COLLABORAZIONI+"."+DBNames.ATTR_COLLABORAZIONI_COLLABORATORE+"='"+utente+"' OR "
            		+DBNames.TABLE_PRODOTTO+"."+DBNames.ATTR_PRODOTTO_EMAILPROPRIETARIO+"='"+utente+"')";
            		
            st=conn.createStatement();
    		ris=st.executeQuery(query);
    		while(ris.next())
			{
    			Prodotto p=new Prodotto(ris.getString(DBNames.ATTR_PRODOTTO_ISBN),ris.getString(DBNames.ATTR_PRODOTTO_TITOLO)
        				,ris.getString(DBNames.ATTR_PRODOTTO_ANNOPUBBLICAZIONE),ris.getString(DBNames.ATTR_PRODOTTO_FORMATOPUBBLICAZIONE)
        				,ris.getString(DBNames.ATTR_PRODOTTO_CODICEDOI),ris.getString(DBNames.ATTR_PRODOTTO_DIFFUSIONE)
        				,ris.getString(DBNames.ATTR_PRODOTTO_NOTE),ris.getString(DBNames.ATTR_PRODOTTO_STATO)
        				,ris.getBoolean(DBNames.ATTR_PRODOTTO_BOZZA),ris.getString(DBNames.ATTR_PRODOTTO_TIPOLOGIA)
        				,ris.getString(DBNames.ATTR_PRODOTTO_EMAILPROPRIETARIO),ris.getString(DBNames.ATTR_PRODOTTO_LISTACOLLABORATORI)
        				,ris.getString(DBNames.ATTR_PRODOTTO_DESCRIZIONECONTENUTI),ris.getString(DBNames.ATTR_PRODOTTO_INDIRIZZOWEB)
        				,ris.getString(DBNames.ATTR_PRODOTTO_PAROLECHIAVI),ris.getString(DBNames.ATTR_PRODOTTO_EDITORE)
        				,ris.getInt(DBNames.ATTR_PRODOTTO_NUMVOLUME),ris.getInt(DBNames.ATTR_PRODOTTO_TOTALEPAGINE)
        				,ris.getInt(DBNames.ATTR_PRODOTTO_DAPAGINA),ris.getInt(DBNames.ATTR_PRODOTTO_APAGINA));
    			
    			if(ris.getString(DBNames.ATTR_COLLABORAZIONI_COLLABORATORE)!=null)
    			{
    				  StringTokenizer collaboratori = new StringTokenizer(ris.getString(DBNames.ATTR_COLLABORAZIONI_COLLABORATORE),";");
    	              
    		      		while (collaboratori.hasMoreElements())
    		      		{
    		      			 if(utente.equals(collaboratori.nextElement()))
    		      			{
    		      				 p.setCollaboratore(utente);
    		      			}
    		      		}
    		      		
    			}
				listProdotto.add(p);
			}
    		return listProdotto;
		}
		finally 
        {
            st.close();
            DBConnectionPool.releaseConnection(conn);
        }
	}
	
	/**
	 * Metodo che permette a un ricercatore di convalidare un prodotto quando è indicato come coautore 
	 * @param collaboratore del prodotto
	 * @param isb_prodotto del prodotto
	 * @throws SQLException
	 */
	public void convalidaProdotto(String collaboratore,String isb_prodotto)throws SQLException
	{
		Connection conn=null;
		PreparedStatement st=null;
		String query;
		try
		{
			conn=DBConnectionPool.getConnection();
			query="UPDATE "+DBNames.TABLE_COLLABORAZIONI+" SET "+DBNames.ATTR_COLLABORAZIONI_CONVALIDATO+"=true"
					+"WHERE "+DBNames.ATTR_COLLABORAZIONI_PRODOTTO_ISBN+"='"+isb_prodotto+"' AND "
					+DBNames.ATTR_COLLABORAZIONI_COLLABORATORE+"='"+collaboratore+"'";
			
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

package it.unisa.vviser.storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import it.unisa.vviser.entity.ListaProdottiValutazione;
import it.unisa.vviser.entity.Prodotto;
import it.unisa.vviser.entity.ProdottoValutazione;
import it.unisa.vviser.exception.InvalidModifyListaProdottiValutazione;


/**
 * 
 * @author Giuseppe Sabato
 *
 */
public class DBProdottiValutazione {
	
	private static DBProdottiValutazione manager;
	
	/**
	 * Costruttore vuoto
	 */
	private DBProdottiValutazione()
	{
		
	}
	
	/**
	 * Metodo che implementa il design pattern singleton
	 * @return manager istanza di DBProdottiValutazione
	 */
	public static DBProdottiValutazione getInstance()
	{
		if(manager==null)
			manager=new DBProdottiValutazione();
		return manager;
		
	}
	
	/**
	 * Metodo che permette di inserire nel database una lista di prodotti per valutazione
	 * @param prodottiValutazione lista prodotti da sottomettere a valutazione
	 * @param emailUtente identificativo dell'utente
	 * @throws SQLException
	 */
	public void insertProdottiVal(ArrayList<ProdottoValutazione> prodottiValutazione,String emailUtente) throws SQLException
	{
		Connection conn=null;
		PreparedStatement st=null;
		String query;
		
		
        try 
        {
            conn = DBConnectionPool.getConnection();
            
            
            int idEvento=getIdEventoValutazione();
			
			ListaProdottiValutazione lp=new ListaProdottiValutazione(prodottiValutazione,emailUtente, idEvento,"");
			
			//Stabilisco la partecipazione dell'utente all'evento di valutazione
			query="INSERT INTO "+DBNames.TABLE_PARTECIPAZIONEAVALUTAZIONE+"("
					+DBNames.ATTR_PARTECIPAZIONEAVALUTAZIONE_UTENTE_EMAIL+","
					+DBNames.ATTR_PARTECIPAZIONEAVALUTAZIONE_EVENTOVALUTAZIONE_ID+
					") values (?,?)";
			
			st=conn.prepareStatement(query);
			st.setString(1,lp.getEmailUtente());
			st.setInt(2, lp.getIdEventoValutazione());
			
			st.executeUpdate();
			conn.commit();
			
            
            
            query="INSERT INTO "+DBNames.TABLE_LISTAVALUTAZIONE+"("
            		+DBNames.ATTR_LISTAVALUTAZIONE_UTENTE_EMAIL+","
            		+DBNames.ATTR_LISTAVALUTAZIONE_EVENTOVALUTAZIONE_ID+
            		") values (?,?)";
            
            st=conn.prepareStatement(query);
            st.setString(1, lp.getEmailUtente());
            st.setInt(2, lp.getIdEventoValutazione());
            
            st.executeUpdate();
            conn.commit();
            

            for(int i=0;i<lp.getListaProdottiValutazione().size();i++)
            {
            	
	            query= "INSERT INTO "+DBNames.TABLE_PRODOTTOLISTA+"("
	            		+DBNames.ATTR_PRODOTTOLISTA_PRODOTTO_ISBN+","
	            		+DBNames.ATTR_PRODOTTOLISTA_UTENTE_EMAIL+","
	            		+DBNames.ATTR_PRODOTTOLISTA_EVENTOVALUTAZIONE_ID+","
	            		+DBNames.ATTR_PRODOTTOLISTA_PRIORITA+
	            		") values (?,?,?,?)";
	            
	            st = conn.prepareStatement(query);
	            st.setString(1, lp.getListaProdottiValutazione().get(i).getIsbn());
	            st.setString(2, lp.getEmailUtente());
	            st.setInt(3, lp.getIdEventoValutazione());
	            st.setInt(4, lp.getListaProdottiValutazione().get(i).getPriority());
	
	            st.executeUpdate();
	            conn.commit();
	            
	            //Se in conflitto, inserisco il prodotto nella tabella dei prodotti
	            //in conflitto.
	            if(isInConflitto(lp.getListaProdottiValutazione().get(i),conn))
	            {
	            	query= "INSERT INTO "+DBNames.TABLE_PRODOTTOINCONFLITTO+"("
		            		+DBNames.ATTR_PRODOTTOINCONFLITTO_PRODOTTO_ISBN+","
		            		+DBNames.ATTR_PRODOTTOINCONFLITTO_UTENTE_EMAIL+","
		            		+DBNames.ATTR_PRODOTTOINCONFLITTO_EVENTOVALUTAZIONE_ID+
		            		") values (?,?,?)";
		            
		            st = conn.prepareStatement(query);
		            st.setString(1, lp.getListaProdottiValutazione().get(i).getIsbn());
		            st.setString(2, lp.getEmailUtente());
		            st.setInt(3, lp.getIdEventoValutazione());
		
		            st.executeUpdate();
		            conn.commit();
	            }
            }
        } 
        finally 
        {
            st.close();
            DBConnectionPool.releaseConnection(conn);
        }
	}
	
	/**
	 *Metodo che mostra i prodotti sottomessi a valutazione dell'utente in input
	 * @param emailUtente identificativo dell'utente
	 * @param idEvento identificativo dell'evento di valutazione
	 * @return listaProdottiValutazione lista prodotti sottomessi a valutazione dall'utente
	 * @throws SQLException
	 */
	public ListaProdottiValutazione showProdottiVal(String emailUtente,int idEvento) throws SQLException
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
	
	/**
	 *Metodo che permette di sostituire nel database un prodotto sottomesso a valutazione
	 *in conflitto con un altro prodotto
	 * @param lp lista prodotti sottomessi a valutazione che contiene il prodotto in conflitto
	 * @param prodottiValutazione prodotti sottomessi a valutazione in conflitto
	 * @param prodotti per sostituire quelli in conflitto
	 * @throws SQLException
	 */
	public void modifyProdVal(ListaProdottiValutazione lp, ArrayList<ProdottoValutazione> prodottiValutazione, ArrayList<Prodotto> prodotti) throws SQLException
	{
		Connection conn=null;
		PreparedStatement st=null;
		String query;
		
		if(!lp.getBloccato())
		{
			try
			{
				conn=DBConnectionPool.getConnection();
				
				for (int i=0;i<prodottiValutazione.size();i++)
				{
					query="UPDATE "+DBNames.TABLE_PRODOTTOLISTA
							+" SET "+DBNames.ATTR_PRODOTTOLISTA_PRODOTTO_ISBN+"=?"
							+ " WHERE "+DBNames.ATTR_PRODOTTOLISTA_PRODOTTO_ISBN+"="+prodottiValutazione.get(i).getIsbn()
							+" and "+DBNames.ATTR_PRODOTTOLISTA_UTENTE_EMAIL+"="+lp.getEmailUtente()
							+" and "+DBNames.ATTR_PRODOTTOLISTA_EVENTOVALUTAZIONE_ID+"="+lp.getIdEventoValutazione();
					
					st=conn.prepareStatement(query);
					st.setString(1, prodotti.get(i).getIsbn());
					st.executeUpdate();
					conn.commit();
					
					//Cancello ex prodotto valutazione dalla tabella dei conflitti
					query="DELETE FROM "+DBNames.TABLE_PRODOTTOINCONFLITTO
							+" WHERE "+DBNames.ATTR_PRODOTTOINCONFLITTO_PRODOTTO_ISBN+"="+prodottiValutazione.get(i).getIsbn()
							+" and "+DBNames.ATTR_PRODOTTOLISTA_UTENTE_EMAIL+"="+lp.getEmailUtente()
							+" and "+DBNames.ATTR_PRODOTTOLISTA_EVENTOVALUTAZIONE_ID+"="+lp.getIdEventoValutazione();
					
					st=conn.prepareStatement(query);
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
		else
		{
			throw new InvalidModifyListaProdottiValutazione("Lista prodotti sottomessi a valutazione bloccata!");
		}
	}
	
	/**
	 *Metodo che controlla se il prodotto sottomesso a valutazione e' in conflitto
	 *con qualche altro prodotto, di un alto utente, sottomesso in precedenza
	 * @param p prodotto sottomesso a valutazione
	 * @param conn connessiona al database
	 * @return true se il prodotto e' in conflitto, false altrimenti
	 * @throws SQLException
	 */
	private boolean isInConflitto(ProdottoValutazione p, Connection conn) throws SQLException
	{
		Statement st1=null;
		ResultSet ris1=null;
		String query1;
		//verifico se ci sono due prodotti con lo stesso isbn
		query1="SELECT count(*) as numeroConflitti"
				+" FROM "+DBNames.TABLE_PRODOTTOLISTA
				+" WHERE "+DBNames.ATTR_PRODOTTOLISTA_PRODOTTO_ISBN+"="
				+"'"+p.getIsbn()+"'";
		
		st1=conn.createStatement();
		ris1=st1.executeQuery(query1);
		ris1.next();
		if(ris1.getInt("numeroConflitti")<=1)
		{
			st1.close();
			return false;
		}
		else
		{
			st1.close();
			return true;
		}
	}
	
	/**
	 * Metodo che restituisce l'identificativo dell'evento a cui l'utente vuole partecipare
	 * @return identificativo dell'evento di valutazione
	 * @throws SQLException
	 */
	private int getIdEventoValutazione() throws SQLException
	{
		Connection conn=null;
		Statement st1=null;
		ResultSet ris=null;
		String query1;
		
		try
		{
			conn = DBConnectionPool.getConnection();
			
			//Recupero l'id dell'evento di valutazione a cui l'utente partecipa
            //supponiamo che la query restitisca un unico risultato......
            query1="SELECT "+DBNames.ATTR_EVENTOVALUTAZIONE_ID
            		+" FROM "+DBNames.TABLE_EVENTOVALUTAZIONE
            		+" WHERE NOW() BETWEEN "+DBNames.ATTR_EVENTOVALUTAZIONE_DADATA
            		+" AND "+DBNames.ATTR_EVENTOVALUTAZIONE_ADATA;
        
            
            
            st1=conn.createStatement();
			ris=st1.executeQuery(query1);
			
			int idEvento;
			ris.next();
			idEvento=ris.getInt(DBNames.ATTR_EVENTOVALUTAZIONE_ID);
			
			return idEvento;
		}
		finally
		{
			st1.close();
            DBConnectionPool.releaseConnection(conn);
		}
	}
	/**
	 * Metodo che restituisce il numero massimo di prodotti che l'utente puo' sottomettere a valutazione
	 * @return numero massimo di sottomissioni per quell'evento
	 * @throws SQLException
	 */
	public int getNumeroSottomissioniMax() throws SQLException
	{
		Connection conn=null;
		Statement st1=null;
		ResultSet ris=null;
		String query1;
		
		int idEvento=getIdEventoValutazione();
		System.out.println(idEvento);
		try
		{
			conn = DBConnectionPool.getConnection();
			
			
            query1="SELECT "+DBNames.ATTR_EVENTOVALUTAZIONE_NUMERODIPUBBLICAZIONI
            		+" FROM "+DBNames.TABLE_EVENTOVALUTAZIONE
            		+" WHERE "+DBNames.ATTR_EVENTOVALUTAZIONE_ID+"="+idEvento;
            
            st1=conn.createStatement();
			ris=st1.executeQuery(query1);
			ris.next();
			
			return (ris.getInt(DBNames.ATTR_EVENTOVALUTAZIONE_NUMERODIPUBBLICAZIONI));
		}
		finally
		{
			st1.close();
            DBConnectionPool.releaseConnection(conn);
		}
	}
	
	
	
	

}

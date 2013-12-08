package it.unisa.vviser.storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import it.unisa.vviser.entity.ListaProdottiValutazione;
import it.unisa.vviser.entity.ProdottoValutazione;


/**
 * 
 * @author Giuseppe Sabato
 *
 */
public class DBProdottiValutazione {
	
	/**
	 * Costruttore vuoto
	 */
	public DBProdottiValutazione()
	{
		
	}
	
	/**
	 * Metodo che permette di inserire nel database un prodotto per valutazione
	 * @param p prodotto da sottomettere a valutazione
	 * @param idLista identificativo lista prodotti sottomissione a valutazione
	 * @throws SQLException
	 */
	public void insertProdottiVal(ListaProdottiValutazione lp,int idLista) throws SQLException
	{
		Connection conn=null;
		PreparedStatement st=null;
		String query;
        try 
        {
            conn = DBConnectionPool.getConnection();
            
            for(int i=0;i<lp.getListaProdottiValutazione().size();i++)
            {
            	
	            query= "INSERT INTO "+DBNames.TABLE_PRODOTTOLISTA+"("
	            		+DBNames.ATTR_PRODOTTOLISTA_PRODOTTO_ISBN+","
	            		+DBNames.ATTR_PRODOTTOLISTA_LISTA_ID+","
	            		+DBNames.ATTR_PRODOTTOLISTA_PRIORITA+
	            		") values (?,?,?)";
	            
	            st = conn.prepareStatement(query);
	            st.setString(1, lp.getListaProdottiValutazione().get(i).getIsbn());
	            st.setInt(2, idLista);
	            st.setInt(3, lp.getListaProdottiValutazione().get(i).getPriority());
	
	            st.executeUpdate();
	            conn.commit();
	            
	            //Se in conflitto, inserisco il prodotto nella tabella dei prodotti
	            //in conflitto.
	            if(isInConflitto(lp.getListaProdottiValutazione().get(i),conn))
	            {
	            	query= "INSERT INTO "+DBNames.TABLE_PRODOTTOINCONFLITTO+"("
		            		+DBNames.ATTR_PRODOTTOINCONFLITTO_PRODOTTO_ISBN+","
		            		+DBNames.ATTR_PRODOTTOINCONFLITTO_LISTA_ID+
		            		") values (?,?)";
		            
		            st = conn.prepareStatement(query);
		            st.setString(1, lp.getListaProdottiValutazione().get(i).getIsbn());
		            st.setInt(2, idLista);
		
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
	 *Metodo che mostra i prodotti sottomessi a valutazione dall'utente in input
	 * @param idLista identificativo lista prodotti sottomessi a valutazione
	 * @return listaProdVal lista prodotti sottomessi a valutazione dall'utente
	 * @throws SQLException
	 */
	public ListaProdottiValutazione showProdottiVal(int idLista) throws SQLException
	{
		Connection conn=null;
		Statement st=null;
		ResultSet ris=null;
		String query;
		ListaProdottiValutazione listProdVal=new ListaProdottiValutazione();
		try
		{
			conn=DBConnectionPool.getConnection();
			query="SELECT "+DBNames.ATTR_PRODOTTOLISTA_PRODOTTO_ISBN+","
					+DBNames.ATTR_PRODOTTO_TITOLO+","
					+DBNames.ATTR_PRODOTTOLISTA_PRIORITA
					+ " FROM " +DBNames.TABLE_PRODOTTO+" "+DBNames.TABLE_PRODOTTOLISTA
					+ " WHERE "+DBNames.ATTR_PRODOTTO_ISBN+"="+DBNames.ATTR_PRODOTTOLISTA_PRODOTTO_ISBN
					+" and "+DBNames.ATTR_PRODOTTOLISTA_LISTA_ID+"="+idLista;
			
			st=conn.createStatement();
			ris=st.executeQuery(query);
			while(ris.next())
			{
				String isbn=ris.getString(DBNames.ATTR_PRODOTTOLISTA_PRODOTTO_ISBN);
				String title=ris.getString(DBNames.ATTR_PRODOTTO_TITOLO);
				int priority=ris.getInt(DBNames.ATTR_PRODOTTOLISTA_PRIORITA);
				
				ProdottoValutazione p=new ProdottoValutazione();
				p.setIsbn(isbn);
				p.setTitle(title);
				p.setPriority(priority);
				listProdVal.addProdottoValutazione(p);
			}
		}
		finally
		{
			st.close();
			DBConnectionPool.releaseConnection(conn);
		}
		
		return listProdVal;
	}
	
	/**
	 *Metodo che permette di sostituire nel database un prodotto sottomesso a valutazione
	 *in conflitto con un altro prodotto
	 * @param pv prodotto sottomesso a valutazione in conflitto
	 * @param p prodotto per sostituire quello in conflitto
	 * @param idLista identificativo lista prodotti valutazione
	 * @throws SQLException
	 */
	public void modifyProdVal(ProdottoValutazione pv/*, Prodotto p*/,int idLista) throws SQLException
	{
		Connection conn=null;
		PreparedStatement st=null;
		String query;
		try
		{
			conn=DBConnectionPool.getConnection();
			query="UPDATE "+DBNames.TABLE_PRODOTTOLISTA
					+" SET "+DBNames.ATTR_PRODOTTOLISTA_PRODOTTO_ISBN+"=?"
					+ " WHERE "+DBNames.ATTR_PRODOTTOLISTA_PRODOTTO_ISBN+"="+pv.getIsbn()
					+" and "+DBNames.ATTR_PRODOTTOLISTA_LISTA_ID+"="+idLista;
			
			st=conn.prepareStatement(query);
			//st.setString(1, p.getIsbn()); //non ho ancora il beans Prodotto
			st.executeUpdate();
			conn.commit();
			
			//Cancello ex prodotto valutazione dalla tabella dei conflitti
			query="DELETE FROM "+DBNames.TABLE_PRODOTTOINCONFLITTO
					+" WHERE "+DBNames.ATTR_PRODOTTOINCONFLITTO_PRODOTTO_ISBN+"="+pv.getIsbn()
					+"and "+DBNames.ATTR_PRODOTTOINCONFLITTO_LISTA_ID+"="+idLista;
			
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
		
		query1="SELECT count(*) as numeroConflitti"
				+" FROM "+DBNames.TABLE_PRODOTTOINCONFLITTO
				+" WHERE "+DBNames.ATTR_PRODOTTOINCONFLITTO_PRODOTTO_ISBN+"="
				+p.getIsbn();
		
		st1=conn.createStatement();
		ris1=st1.executeQuery(query1);
		ris1.next();
		if(ris1.getInt("numeroConflitti")==0)
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
	
	

}

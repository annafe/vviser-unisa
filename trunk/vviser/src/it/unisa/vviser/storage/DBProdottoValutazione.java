package it.unisa.vviser.storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import it.unisa.vviser.entity.ProdottoValutazione;


/**
 * 
 * @author Giuseppe Sabato
 *
 */
public class DBProdottoValutazione {
	
	/**
	 * Costruttore vuoto
	 */
	public DBProdottoValutazione()
	{
		
	}
	
	/**
	 * Metodo che permette di inserire nel database un prodotto per valutazione
	 * @param p 
	 * @throws SQLException
	 */
	public void insertProdottiVal(ProdottoValutazione p) throws SQLException
	{
		Connection conn=null;
		PreparedStatement st=null;
		String query;
        try 
        {
            conn = DBConnectionPool.getConnection();
            query= "INSERT INTO prodottoeventodivalutazione(prodotto_isbn,priorita) values (?,?)";

            st = conn.prepareStatement(query);
            st.setString(1, p.getIsbn());//??
            //id evento di valutazione ??
            st.setInt(3, p.getPriority());

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
	 *Metodo che mostra i prodotti sottomessi a valutazione dal ricercatore in input
	 * @param idUt
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<ProdottoValutazione> showProdottiVal(int idUt) throws SQLException
	{
		Connection conn=null;
		Statement st=null;
		ResultSet ris=null;
		String query;
		ArrayList<ProdottoValutazione> listProdVal=new ArrayList<ProdottoValutazione>();
		try
		{
			conn=DBConnectionPool.getConnection();
			query="SELECT titolo,prodotto_isbn,priorita,suggerimento"
					+ "FROM prodotto,prodottoeventodivalutazione,prodottiutente"
					+ "WHERE prodotto.isbn=prodottoeventodivalutazione.isbn"
					+ "and prodotto.isbn=prodottiutente.prodotto_isbn"
					+"and utente.id=prodottiutente.utente_id"
					+ "and utente.id="+idUt;
			st=conn.createStatement();
			ris=st.executeQuery(query);
			while(ris.next())
			{
				String isbn=ris.getString("prodotto_isbn");
				String title=ris.getString("titolo");
				int priority=ris.getInt("priorita");
				String suggestion=ris.getString("suggerimento");
				
				ProdottoValutazione p=new ProdottoValutazione();
				p.setIsbn(isbn);
				p.setTitle(title);
				p.setPriority(priority);
				p.setSuggestion(suggestion);
				listProdVal.add(p);
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
	 *in conflitto
	 * @param pv
	 * @param p
	 * @throws SQLException
	 */
	public void modifyProdVal(ProdottoValutazione pv/*, Prodotto p*/) throws SQLException
	{
		Connection conn=null;
		PreparedStatement st=null;
		String query;
		try
		{
			conn=DBConnectionPool.getConnection();
			query="UPDATE prodottoeventodivalutazione SET prodotto_isbn=?,"
					+ "eventoDiValutazione_id=?"
					+ "WHERE prodotto_isbn="+pv.getIsbn();
			
			st=conn.prepareStatement(query);
			st.setString(1, pv.getIsbn());
			//eventovalutazione??
			st.setInt(3, pv.getPriority());
			st.setString(4, pv.getSuggestion());
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

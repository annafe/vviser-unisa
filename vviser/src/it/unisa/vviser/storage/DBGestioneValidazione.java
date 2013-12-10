package it.unisa.vviser.storage;

import it.unisa.vviser.entity.ProdottoValutazione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBGestioneValidazione {
	/**
	 *Metodo che mostra i prodotti sottomessi a validazione dal ricercatore 
	  * @param idUt
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<ProdottoValidazione> showProdottiVal() throws SQLException
	{
		Connection conn=null;
		Statement st=null;
		ResultSet ris=null;
		String query;
		ArrayList<ProdottoValutazione> listProdVal=new ArrayList<ProdottoValutazione>();
		try
		{
			conn=DBConnectionPool.getConnection();
			query="SELECT *"
					+ "FROM DBNames.TABLE_PRODOTTO";
			st=conn.createStatement();
			ris=st.executeQuery(query);
			while(ris.next())
			{
				String isbn=ris.getString("isbn");
				String title=ris.getString("titolo");
				String anno=ris.getString("AnnoPubblicazione");
				String formato=ris.getString("FormatoPubblicazione");
				String codice=ris.getString("CodiceDOI");
				String categoria=ris.getString("Categoria.nome");
				String note=ris.getString("Note");
				String diffusione=ris.getString("Diffusione");
				
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
	 *Metodo che permette di inviare nel database una notifica
	 * @throws SQLException
	 */
	public void invionotifica() throws SQLException
	{
		Connection conn=null;
		PreparedStatement st=null;
		String query;
		try
		{
			conn=DBConnectionPool.getConnection();
			query="UPDATE notica SET tipon=?,"
					+ "FROM DBNames.TABLE_NOTIFICA,DBNames.TABLE_RICEZIONENOTIFICA,
					DBNames.TABLE_UTENTE,DBNames.TABLE_PRODOTTOUTENTE,DBNames.TABLE_PRODOTTO"
					+ "WHERE ;
			
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
		/**
		 *Metodo che permette di VALIDARE nel database un PRODOTTO	
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
				query="UPDATE prodotto SET STATO=VALIDATODIPARTIMENTO,"
						+ "FROM DBNames.TABLE_PRODOTTO"
				
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
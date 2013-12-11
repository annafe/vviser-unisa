package it.unisa.vviser.storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import it.unisa.vviser.entity.Prodotto;
import it.unisa.vviser.entity.ProdottoValutazione;


/**
 * 
 * @author Angiuoli Salvatore
 *
 */
public class DBGestioneProdotto {
	
	/**
	 * Metodo che permette di inserire nel database un prodotto
	 * @param p 
	 * @throws SQLException
	 */
	
	public void insertProdotti1(Prodotto p) throws SQLException
	{
		Connection conn=null;
		PreparedStatement st=null;
		String query;
        try 
        {
            conn = DBConnectionPool.getConnection();
            query= "INSERT INTO DBNames.TABLE_PRODOTTO(Isbn Titolo, AnnoPubblicazione, FormatoPubblicazione, CodiceDOI, Diffusione, Note, Categoria.nome, Stato) values (?,?)"
            		+"WHERE DBNames.TABLE_PRODOTTO_isbn=DBNames.TABLE_publicazionerivista_prodotto.isbn"
            		+"DBNames.TABLE_PUBLICAZIONERIVISTA_rivista.issn=DBNames.TABLE_rivista_issn";

            st = conn.prepareStatement(query);
            st.setString(1, p.getIsbn());
            

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
	 * Metodo che permette di inserire nel database un prodotto parziale
	 * @param p 
	 * @throws SQLException
	 */
	public void insertProdottiValparz(Prodotto p) throws SQLException
	{
		Connection conn=null;
		PreparedStatement st=null;
		String query;
        try 
        {
            conn = DBConnectionPool.getConnection();
            query= "INSERT INTO DBNames.TABLE_PRODOTTO(Isbn, Titolo, AnnoPubblicazione, Stato) values (?,?)";

            st = conn.prepareStatement(query);
            st.setString(1, p.getIsbn());
            

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
	 * Metodo che permette di eliminare nel database un prodotto
	 * @param pisbn
	 * @return 
	 * @return 
	 * @throws SQLException
	 */
	public void deleteProdotti(String pisbn) throws SQLException
	{
		Connection conn=null;
		PreparedStatement st=null;
		String query;
        try 
        {
            conn = DBConnectionPool.getConnection();
            query= "DELETE INTO DBNames.TABLE_PRODOTTO"+"WHERE DBNames.TABLE_PRODOTTO_isbn=pisbm";

            st = conn.prepareStatement(query);
            st.setString(1, p.getIsbn());
            

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
	 *Metodo che permette di modificaree nel database un prodotto publicato su rivista non validato
	 * @param pisbn
	 * 
	 * @throws SQLException
	 */
	public void modifyProdNonVal(String pisbn/*, Prodotto p*/) throws SQLException
	{
		Connection conn=null;
		PreparedStatement st=null;
		String query;
		try
		{
			conn=DBConnectionPool.getConnection();
			query="UPDATE FROM DBNames.TABLE_PRODOTTO, DBNames.TABLE_Publicazioneivista SET DBNames.TABLE_PRODOTTO_Isbn=?,"+" DBNames.TABLE_PRODOTTO_Titolo=?,"+" DBNames.TABLE_PRODOTTO_AnnoPubblicazione=?," +"DBNames.TABLE_PRODOTTO_FormatoPubblicazione=?,"+ "DBNames.TABLE_PRODOTTO_CodiceDOI=?,"+ "DBNames.TABLE_PRODOTTO_Diffusione=?,"+ "DBNames.TABLE_PRODOTTO_Note=?," +"DBNames.TABLE_PRODOTTO_Categoria.nome=?," 	
					+ "WHERE DBNames.TABLE_PRODOTTO_isbn=DBNames.TABLE_publicazionerivista_isbn"
					+ "and DBNames.TABLE_PRODOTTO_isbn="+pisbn
					+ "and DBNames.TABLE_PRODOTTO_stato= NonValidato";
			
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
		 *Metodo che permette di modificaree nel database un prodotto publicato su rivista validato
		 * @param pisbn
		 * 
		 * @throws SQLException
		 */
		public void modifyProdVal(String pisbn) throws SQLException
		{
			Connection conn=null;
			PreparedStatement st=null;
			String query;
			try
			{
				conn=DBConnectionPool.getConnection();
				query="UPDATE FROM DBNames.TABLE_PRODOTTO, DBNames.TABLE_publicazioneivista SET DBNames.TABLE_PRODOTTO_nota=?"  	
						+ "WHERE DBNames.TABLE_PRODOTTO_isbn=DBNames.TABLE_publicazionerivista_isbn"
						+ "and DBNames.TABLE_PRODOTTO_isbn="+pisbn
						+ "and DBNames.TABLE_PRODOTTO.stato= Validato";
				
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
		 * Metodo che permette di visualizzare nel database i prodotti in stato di bozza
		 * @param p 
		 * @throws SQLException
		 */
		public ArrayList<Prodotto> showProdotti11(int idUt) throws SQLException
		{
			Connection conn=null;
			Statement st=null;
			ResultSet ris=null;
			String query;
			ArrayList<ProdottoValutazione> listProdVal=new ArrayList<ProdottoValutazione>();
			try
			{
				conn=DBConnectionPool.getConnection();
				query="SELECT Isbn, Titolo, AnnoPubblicazione, FormatoPubblicazione, CodiceDOI, Diffusione, Note, Categoria.nome"
						+ "FROM DBNames.TABLE_PRODOTTO"
						+ "WHERE BOZZA=true";
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
					
					
					
					Prodotto p=new Prodotto();
					p.setIsbn(isbn);
					p.setTitle(title);
					p.setAnno(anno);
					p.setFormato(formato);
					p.setCodiceDOI(codice);
					p.setCategoria(categoria);
					p.setNote(note);
					p.setDiffusione(diffusione);
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
		 * Metodo che permette di inserire nel database un prodotto salvato in bozza
		 * @param p 
		 * @throws SQLException
		 */
		public void insertProdotti(Prodotto p) throws SQLException
		{
			Connection conn=null;
			PreparedStatement st=null;
			String query;
	        try 
	        {
	            conn = DBConnectionPool.getConnection();
	            query= "INSERT INTO DBNames.TABLE_PRODOTTO(Isbn, Titolo, AnnoPubblicazione) values (?,?)"
	            		+"WHERE DBNames.TABLE_PRODOTTO_BOZZA=true";

	            st = conn.prepareStatement(query);
	            st.setString(1, p.getIsbn());
	            

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
		 * Metodo che permette di visualizzare nel database i prodotti		
		 *  * @param p 
		 * @throws SQLException
		 */
		public ArrayList<Prodotto> showProdotti(int idUt) throws SQLException
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
					
					
					
					Prodotto p=new Prodotto();
					p.setIsbn(isbn);
					p.setTitle(title);
					p.setAnno(anno);
					p.setFormato(formato);
					p.setCodiceDOI(codice);
					p.setCategoria(categoria);
					p.setNote(note);
					p.setDiffusione(diffusione);
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
		 * Metodo che permette di visualizzare nel database i prodotti per titolo
		 * @param p 
		 * @throws SQLException
		 */
		public ArrayList<Prodotto> showProdotti1(String titolo) throws SQLException
		{
			Connection conn=null;
			Statement st=null;
			ResultSet ris=null;
			String query;
			ArrayList<ProdottoValutazione> listProdVal=new ArrayList<ProdottoValutazione>();
			try
			{
				conn=DBConnectionPool.getConnection();
				query="SELECT DBNames.TABLE_PRODOTTO_Isbn, DBNames.TABLE_PRODOTTO_AnnoPubblicazione, DBNames.TABLE_PRODOTTO_FormatoPubblicazione,DBNames.TABLE_PRODOTTO_CodiceDOI, DBNames.TABLE_PRODOTTO_Diffusione, DBNames.TABLE_PRODOTTO_Note, DBNames.TABLE_PRODOTTO_Categoria.nome"
						+ "FROM DBNames.TABLE_PRODOTTO, DBNames.TABLE_prodottorivista, DBNames.TABLE_rivista"
						+ "WHERE DBNames.TABLE_prodotto_titolo=titolo"
						+" and DBNames.TABLE_PRODOTTO_isbn=DBNames.TABLE_prodottirivista_prodotto.isbn"
						+"and DBNames.TABLE_prodottorivista_rivista.issn=DBNames.TABLE_rivista_issn";
				st=conn.createStatement();
				ris=st.executeQuery(query);
				while(ris.next())
				{
					String isbn=ris.getString("DBNames.TABLE_PRODOTTO_isbn");
					String title=ris.getString("DBNames.TABLE_PRODOTTO_titolo");
					String anno=ris.getString("DBNames.TABLE_PRODOTTO_AnnoPubblicazione");
					String formato=ris.getString("DBNames.TABLE_PRODOTTO_FormatoPubblicazione");
					String codice=ris.getString("DBNames.TABLE_PRODOTTO_CodiceDOI");
					String categoria=ris.getString("DBNames.TABLE_PRODOTTO_Categoria.nome");
					String note=ris.getString("DBNames.TABLE_PRODOTTO_Note");
					String diffusione=ris.getString("DBNames.TABLE_PRODOTTO_Diffusione");
					
					
					
					Prodotto p=new Prodotto();
					p.setIsbn(isbn);
					p.setAnno(anno);
					p.setFormato(formato);
					p.setCodiceDOI(codice);
					p.setCategoria(categoria);
					p.setNote(note);
					p.setDiffusione(diffusione);
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
		 * Metodo che permette di visualizzare nel database i prodotti per isbn
		 * @param p 
		 * @throws SQLException
		 */
		public ArrayList<Prodotto> showProdotti(String pissn) throws SQLException
		{
			Connection conn=null;
			Statement st=null;
			ResultSet ris=null;
			String query;
			ArrayList<ProdottoValutazione> listProdVal=new ArrayList<ProdottoValutazione>();
			try
			{
				conn=DBConnectionPool.getConnection();
				query="SELECT DBNames.TABLE_PRODOTTO_title, DBNames.TABLE_PRODOTTO_AnnoPubblicazione, DBNames.TABLE_PRODOTTO_FormatoPubblicazione, DBNames.TABLE_PRODOTTO_CodiceDOI, DBNames.TABLE_PRODOTTO_Diffusione, DBNames.TABLE_PRODOTTO_Note, DBNames.TABLE_PRODOTTO_Categoria.nome"
						+ "FROM DBNames.TABLE_PRODOTTO, DBNames.TABLE_PRODOTTOrivista, DBNames.TABLE_rivista"
						+ "WHERE DBNames.TABLE_rivista_issn=pissn"
						+" and DBNames.TABLE_PRODOTTO_isbn=DBNames.TABLE_PRODOTTOrivista_prodotto.isbn"
						+"and DBNames.TABLE_PRODOTTOrivista_rivista.issn=DBNames.TABLE_rivista_issn";
				st=conn.createStatement();
				ris=st.executeQuery(query);
				while(ris.next())
				{
					String isbn=ris.getString("DBNames.TABLE_PRODOTTO_title");
					String title=ris.getString("DBNames.TABLE_PRODOTTO_titolo");
					String anno=ris.getString("DBNames.TABLE_PRODOTTO_AnnoPubblicazione");
					String formato=ris.getString("DBNames.TABLE_PRODOTTO_FormatoPubblicazione");
					String codice=ris.getString("DBNames.TABLE_PRODOTTO_CodiceDOI");
					String categoria=ris.getString("DBNames.TABLE_PRODOTTO_Categoria.nome");
					String note=ris.getString("DBNames.TABLE_PRODOTTO_Note");
					String diffusione=ris.getString("DBNames.TABLE_PRODOTTO_Diffusione");
					
					
					
					Prodotto p=new Prodotto();
					p.setIsbn(isbn);
					p.setAnno(anno);
					p.setFormato(formato);
					p.setCodiceDOI(codice);
					p.setCategoria(categoria);
					p.setNote(note);
					p.setDiffusione(diffusione);
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
		 * Metodo che permette di visualizzare nel database i prodotti per data
		 * @param p 
		 * @throws SQLException
		 */
		public ArrayList<Prodotto> showProdotti(String annoa, String annob) throws SQLException
		{
			Connection conn=null;
			Statement st=null;
			ResultSet ris=null;
			String query;
			ArrayList<ProdottoValutazione> listProdVal=new ArrayList<ProdottoValutazione>();
			try
			{
				conn=DBConnectionPool.getConnection();
				query="SELECT Isbn, title, FormatoPubblicazione, CodiceDOI, Diffusione, Note, Categoria.nome"
						+ "FROM DBNames.TABLE_PRODOTTO,DBNames.TABLE_PRODOTTOrivista, DBNames.TABLE_rivista"
						+ "WHERE DBNames.TABLE_PRODOTTO_annopubblicazione>annoa"
						+"and DBNames.TABLE_PRODOTTO_annopubblicazione<annob"
						+" and DBNames.TABLE_PRODOTTO_isbn=DBNames.TABLE_PRODOTTOrivista_prodotto.isbn"
						+"and DBNames.TABLE_PRODOTTOrivista_rivista.issn=DBNames.TABLE_rivista_issn";
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
					
					
					
					Prodotto p=new Prodotto();
					p.setIsbn(isbn);
					p.setAnno(anno);
					p.setFormato(formato);
					p.setCodiceDOI(codice);
					p.setCategoria(categoria);
					p.setNote(note);
					p.setDiffusione(diffusione);
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
		
		
}

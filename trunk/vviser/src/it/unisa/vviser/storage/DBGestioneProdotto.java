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
 * @author Antonio De Piano
 *
 */
public class DBGestioneProdotto
{
	/**
	 * Costruttore vuoto
	 */
	public DBGestioneProdotto()
	{
		
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
            		+DBNames.ATTR_PRODOTTO_CATEGORIA_NOME+","
            		+DBNames.ATTR_PRODOTTO_EMAILPROPRIETARIO
            		+") values (?,?,?,?,?,?,?,?,?,?,?)";
            
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
            st.setString(10,p.getCategoria());
            st.setString(11,p.getProprietario());
            
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
	/*
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
	*/
	
	/**
	 * Metodo che permette di eliminare un prodotto
	 * @param isbn
	 * @throws SQLException
	 */
	public void deleteProdotto(String isbn) throws SQLException
	{
		Connection conn=null;
		PreparedStatement st=null;
		String query;
        try 
        {
            conn = DBConnectionPool.getConnection();
            query= "DELETE FROM "+DBNames.TABLE_PRODOTTO+" WHERE "+DBNames.ATTR_PRODOTTO_ISBN+"="+isbn;

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
            		+DBNames.ATTR_PRODOTTO_CATEGORIA_NOME+"="+p.getCategoria()+","
            		+DBNames.ATTR_PRODOTTO_EMAILPROPRIETARIO+"="+p.getProprietario()+" "
					+"WHERE "+DBNames.ATTR_PRODOTTO_ISBN+"="+p.getIsbn();
			
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
		/*
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
	*/
	
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
    				,ris.getBoolean(DBNames.ATTR_PRODOTTO_BOZZA),ris.getString(DBNames.ATTR_PRODOTTO_CATEGORIA_NOME)
    				,ris.getString(DBNames.ATTR_PRODOTTO_EMAILPROPRIETARIO));
    		return p;
		}
		finally 
        {
            st.close();
            DBConnectionPool.releaseConnection(conn);
        }
	}
	
	/**
	 *Metodo che permette di visualizzare tutti i prodotti personali 
	 * @param proprietario del prodotto
	 * @return lista dei prodotti
	 * @throws SQLException
	 */
	public ArrayList<Prodotto> visualizzaProdotti(String proprietario)throws SQLException
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
            		" WHERE "+DBNames.ATTR_PRODOTTO_EMAILPROPRIETARIO+"="+proprietario;
            
            st=conn.createStatement();
    		ris=st.executeQuery(query);
    		while(ris.next())
			{
    			Prodotto p=new Prodotto(ris.getString(DBNames.ATTR_PRODOTTO_ISBN),ris.getString(DBNames.ATTR_PRODOTTO_TITOLO)
        				,ris.getString(DBNames.ATTR_PRODOTTO_ANNOPUBBLICAZIONE),ris.getString(DBNames.ATTR_PRODOTTO_FORMATOPUBBLICAZIONE)
        				,ris.getString(DBNames.ATTR_PRODOTTO_CODICEDOI),ris.getString(DBNames.ATTR_PRODOTTO_DIFFUSIONE)
        				,ris.getString(DBNames.ATTR_PRODOTTO_NOTE),ris.getString(DBNames.ATTR_PRODOTTO_STATO)
        				,ris.getBoolean(DBNames.ATTR_PRODOTTO_BOZZA),ris.getString(DBNames.ATTR_PRODOTTO_CATEGORIA_NOME)
        				,ris.getString(DBNames.ATTR_PRODOTTO_EMAILPROPRIETARIO));
        	
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
	 * Metodo che permete di ricerca i prodotti in base al titolo
	 * @param titolo del prodotto
	 * @return
	 */
	public ArrayList<Prodotto> ricercaProdotto(String titolo)throws SQLException
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
            		" WHERE "+DBNames.ATTR_PRODOTTO_TITOLO+"="+titolo;
            
            st=conn.createStatement();
    		ris=st.executeQuery(query);
    		while(ris.next())
			{
    			Prodotto p=new Prodotto(ris.getString(DBNames.ATTR_PRODOTTO_ISBN),ris.getString(DBNames.ATTR_PRODOTTO_TITOLO)
        				,ris.getString(DBNames.ATTR_PRODOTTO_ANNOPUBBLICAZIONE),ris.getString(DBNames.ATTR_PRODOTTO_FORMATOPUBBLICAZIONE)
        				,ris.getString(DBNames.ATTR_PRODOTTO_CODICEDOI),ris.getString(DBNames.ATTR_PRODOTTO_DIFFUSIONE)
        				,ris.getString(DBNames.ATTR_PRODOTTO_NOTE),ris.getString(DBNames.ATTR_PRODOTTO_STATO)
        				,ris.getBoolean(DBNames.ATTR_PRODOTTO_BOZZA),ris.getString(DBNames.ATTR_PRODOTTO_CATEGORIA_NOME)
        				,ris.getString(DBNames.ATTR_PRODOTTO_EMAILPROPRIETARIO));
        	
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
	 * Metodo che permette di ricercare i prodotti in un intervallo di anni
	 * @param titolo
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Prodotto> ricercaProdotto(String anno,String anno1)throws SQLException
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
        				,ris.getBoolean(DBNames.ATTR_PRODOTTO_BOZZA),ris.getString(DBNames.ATTR_PRODOTTO_CATEGORIA_NOME)
        				,ris.getString(DBNames.ATTR_PRODOTTO_EMAILPROPRIETARIO));
        	
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
		 * Metodo che permette di visualizzare nel database i prodotti in stato di bozza
		 * @param p 
		 * @throws SQLException
		 */
	/*
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
		*/
		/**
		 * Metodo che permette di inserire nel database un prodotto salvato in bozza
		 * @param p 
		 * @throws SQLException
		 */
		/*public void insertProdotti(Prodotto p) throws SQLException
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
*/
		/**
		 * Metodo che permette di visualizzare nel database i prodotti		
		 *  * @param p 
		 * @throws SQLException
		 */
		/*
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
	*/
		/**
		 * Metodo che permette di visualizzare nel database i prodotti per titolo
		 * @param p 
		 * @throws SQLException
		 */
		/*
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
		}*/
		/**
		 * Metodo che permette di visualizzare nel database i prodotti per isbn
		 * @param p 
		 * @throws SQLException
		 */
		/*public ArrayList<Prodotto> showProdotti(String pissn) throws SQLException
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
		}*/
		/**
		 * Metodo che permette di visualizzare nel database i prodotti per data
		 * @param p 
		 * @throws SQLException
		 */
		/*public ArrayList<Prodotto> showProdotti(String annoa, String annob) throws SQLException
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
		*/
		
}

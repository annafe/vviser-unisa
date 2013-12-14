package it.unisa.vviser.storage;

import it.unisa.vviser.entity.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 
 * @author Michele Roviello
 *
 */
public class DBAccount {
	
	private Connection conn;
	private String query;
	private PreparedStatement st;
	
	/**
	 * Costruttore vuoto della classe DBAccount
	 */
	public DBAccount(){ }
	
	
	
	// METODI DI AGGIUNTA
	/**
	 * Aggiunge un nuovo account al sistema
	 * @param a Account da aggiungere
	 * @return true se l'operazione è stata eseguita con successo, false altrimenti
	 * @throws SQLException
	 */
	public boolean addAccount(Account a) throws SQLException{
		
		try{
			conn = DBConnectionPool.getConnection();
			
			query = "INSERT INTO "+DBNames.TABLE_UTENTE+"( "
					+ DBNames.ATTR_UTENTE_NOME+", "
					+ DBNames.ATTR_UTENTE_COGNOME+", "
					+ DBNames.ATTR_UTENTE_DATADINASCITA+","
					+ DBNames.ATTR_UTENTE_COMUNEDINASCITA+", "
					+ DBNames.ATTR_UTENTE_PROVINCIADINASCITA+", "
					+ DBNames.ATTR_UTENTE_CODICEFISCALE+", "
					+ DBNames.ATTR_UTENTE_PASSWORD+", "
					+ DBNames.ATTR_UTENTE_EMAIL+", "
					+ DBNames.ATTR_UTENTE_DIPARTIMENTO_NOME+", "
					+ DBNames.ATTR_UTENTE_TIPOLOGIA+" "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			st = conn.prepareStatement(query);
			st.setString(1, a.getNome());
			st.setString(2, a.getCognome());
			st.setString(3, a.getDataDiNascita());
			st.setString(4, a.getComuneDiNascita());
			st.setString(5, a.getProvinciaDiNascita());
			st.setString(6, a.getCodiceFiscale());
			st.setString(7, a.getPassword());
			st.setString(8, a.getEmail());
			st.setString(9, a.getDipartimento());
			st.setString(10, a.getTipologia());
			
			if(st.executeUpdate() > 0)
				return true;
			
		} catch(SQLException e){
			e.printStackTrace();
		}finally{
			st.close();
			DBConnectionPool.releaseConnection(conn);
		}
		return false;
	}
	
	
	// METODI DI ELIMINAZIONE
	/**
	 * Elimina un account dal sistema
	 * @param a Account da eliminare
	 * @return true se l'operazione è stata eseguita con successo, false altrimenti
	 * @throws SQLException
	 */
	public boolean removeAccount(Account a) throws SQLException{
		
		try{
			conn = DBConnectionPool.getConnection();
			
			query = "DELETE FROM "+DBNames.TABLE_UTENTE+" WHERE id = ?";
			st = conn.prepareStatement(query);
			st.setInt(1, a.getID());
			
			if(st.executeUpdate() > 0)
				return true;
			
		} catch(SQLException e){
			e.printStackTrace();
		}finally{
			st.close();
			DBConnectionPool.releaseConnection(conn);
		}
		return false;
	}
	
	// METODI DI RICERCA
	
	/**
	 * Ricerca account in base al nome
	 * @param name Nome associato all'account
	 * @return Lista degli account trovati
	 * @throws SQLException 
	 */
	public List<Account> findByName(String name) throws SQLException{
		
		
		try{
			conn = DBConnectionPool.getConnection();
			
			query = "SELECT * FROM "+DBNames.TABLE_UTENTE
					+" WHERE "+DBNames.ATTR_UTENTE_NOME+"="+name;
			st = conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			
			return fillResults(rs);
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			st.close();
			DBConnectionPool.releaseConnection(conn);
		}
		return null;
		
	}
	
	/**
	 * Ricerca account in base al cognome
	 * @param surname Nome associato all'account
	 * @return Lista degli account trovati
	 * @throws SQLException 
	 */
	public List<Account> findBySurname(String surname) throws SQLException{
		
		try{
			conn = DBConnectionPool.getConnection();
			
			query = "SELECT * FROM "+DBNames.TABLE_UTENTE
					+" WHERE "+DBNames.ATTR_UTENTE_COGNOME+"="+surname;
			st = conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			
			return fillResults(rs);
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			st.close();
			DBConnectionPool.releaseConnection(conn);
		}
		return null;
		
	}
	 /**
	  * Ricerca account in base al nome o al cognome
	  * @param name Nome associato all'account
	  * @param surname Cognome associato all'account
	  * @return Lista degli account trovati
	  * @throws SQLException
	  */
	public List<Account> findByNameSurname(String name, String surname) throws SQLException{
		
		try{
			conn = DBConnectionPool.getConnection();
			
			query = "SELECT * FROM "+DBNames.TABLE_UTENTE
					+" WHERE "+DBNames.ATTR_UTENTE_COGNOME+"="+surname
					+" OR "+DBNames.ATTR_UTENTE_NOME+"="+name;
			st = conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			
			return fillResults(rs);
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			st.close();
			DBConnectionPool.releaseConnection(conn);
		}
		return null;
		
	}

	// METODI DI SUPPORTO PER LA CLASSE

	/**
	 * Riempie una lista di Account con i risultati di una ricerca
	 * @param rs ResultSet con i risultati di una query
	 * @return Lista di Account trovati
	 * @throws SQLException
	 */
	private List<Account> fillResults(ResultSet rs) throws SQLException {
		List<Account> results = new ArrayList<Account>();
		
		while(rs.next()){
			int id = rs.getInt("id");
			String n = rs.getString(DBNames.ATTR_UTENTE_NOME);
			String c = rs.getString(DBNames.ATTR_UTENTE_COGNOME);
			GregorianCalendar dn = creaData(rs.getString(DBNames.ATTR_UTENTE_DATADINASCITA));
			String cn = rs.getString(DBNames.ATTR_UTENTE_COMUNEDINASCITA);
			String pn = rs.getString(DBNames.ATTR_UTENTE_PROVINCIADINASCITA);
			String cf = rs.getString(DBNames.ATTR_UTENTE_CODICEFISCALE);
			String p = rs.getString(DBNames.ATTR_UTENTE_PASSWORD);
			String e = rs.getString(DBNames.ATTR_UTENTE_EMAIL);
			String d = rs.getString(DBNames.ATTR_UTENTE_DIPARTIMENTO_NOME);
			String t = rs.getString(DBNames.ATTR_UTENTE_TIPOLOGIA);
			
			results.add(new Account(id, n, c, dn, cn, pn, cf, p, e, d, t)); 
		}
		
		return results;
	}
	
	
	
	/**
	 * crea un GregorianCalendar data una stringa in formato "YYYY/MM/DD"
	 * @param data stringa che contiene la data in formato "YYYY/MM/DD"
	 * @return un GregorianCalendar contenente la data inserita in input
	 */
	private GregorianCalendar creaData(String data){
		GregorianCalendar toReturn;
		String[] tokens = new String[3];
		int[] g=new int[3];
			
		tokens = data.split("/");
			
		g[0]=Integer.parseInt(tokens[0]); 
		g[1]=Integer.parseInt(tokens[1]); 
		g[2]=Integer.parseInt(tokens[2]);
			
		toReturn = new GregorianCalendar(g[0],g[1],g[2]);
		return toReturn;
	}
	
}

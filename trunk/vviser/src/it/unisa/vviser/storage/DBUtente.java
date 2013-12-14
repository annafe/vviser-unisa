package it.unisa.vviser.storage;

import it.unisa.vviser.entity.Utente;

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
public class DBUtente {
	
	private Connection conn;
	private String query;
	private PreparedStatement st;
	
	/**
	 * Costruttore vuoto della classe DBUtente
	 */
	public DBUtente(){ }
	
	
	
	// METODI DI AGGIUNTA
	/**
	 * Aggiunge un nuovo Utente al sistema
	 * @param a Utente da aggiungere
	 * @return true se l'operazione è stata eseguita con successo, false altrimenti
	 * @throws SQLException
	 */
	public boolean addUtente(Utente a) throws SQLException{
		
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
	 * Elimina un Utente dal sistema
	 * @param a Utente da eliminare
	 * @return true se l'operazione è stata eseguita con successo, false altrimenti
	 * @throws SQLException
	 */
	public boolean removeUtente(Utente u) throws SQLException{
		
		try{
			conn = DBConnectionPool.getConnection();
			
			query = "DELETE FROM "+DBNames.TABLE_UTENTE+" WHERE"
					+DBNames.ATTR_UTENTE_EMAIL+" = ?";
			st = conn.prepareStatement(query);
			st.setString(1, u.getEmail());
			
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
	
	
	//METODI DI VISUALIZZAZIONE
	public List<Utente> visualizzaUtente() throws SQLException{
		
		try{
			conn = DBConnectionPool.getConnection();
			
			query = "SELECT * FROM "+DBNames.TABLE_UTENTE;
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
	
	// METODI DI MODIFICA
	
	/**
	 * Modifica il nome associato all'Utente
	 * @param nome Nuovo nome da memorizzare
	 * @return true se l'operazione è stata eseguita con successo, false altrimenti
	 * @throws SQLException 
	 */
	public boolean modificaNome(Utente a, String nome) throws SQLException{
		a.setNome(nome);
		try{
			conn = DBConnectionPool.getConnection();
			
			query = "UPDATE "+DBNames.TABLE_UTENTE+" "
					+" SET "+DBNames.ATTR_UTENTE_NOME+" = ? "
					+" WHERE "+DBNames.ATTR_UTENTE_EMAIL+" = ?";
			st = conn.prepareStatement(query);
			st.setString(1, nome);
			st.setString(2, a.getEmail());
			
			if(st.executeUpdate() > 0)
				return true;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			st.close();
			DBConnectionPool.releaseConnection(conn);
		}
		
		return false;
	}
	
	/**
	 * Modifica il cognome associato all'Utente
	 * @param cognome Nuovo cognome da memorizzare
	 * @return true se l'operazione è stata eseguita con successo, false altrimenti
	 * @throws SQLException 
	 */
	public boolean modificaCognome(Utente a, String cognome) throws SQLException{
		a.setCognome(cognome);
		try{
			conn = DBConnectionPool.getConnection();
			
			query = "UPDATE "+DBNames.TABLE_UTENTE+" "
					+" SET "+DBNames.ATTR_UTENTE_COGNOME+" = ? "
					+" WHERE "+DBNames.ATTR_UTENTE_EMAIL+" = ?";
			st = conn.prepareStatement(query);
			st.setString(1, cognome);
			st.setString(2, a.getEmail());
			
			if(st.executeUpdate() > 0)
				return true;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			st.close();
			DBConnectionPool.releaseConnection(conn);
		}
		
		return false;
	}
	
	
	
	
	// METODI DI RICERCA
	
	/**
	 * Ricerca Utente in base al nome
	 * @param name Nome associato all'Utente
	 * @return Lista degli Utente trovati
	 * @throws SQLException 
	 */
	public List<Utente> findByName(String name) throws SQLException{
		
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
	 * Ricerca Utente in base al cognome
	 * @param surname Nome associato all'Utente
	 * @return Lista degli Utente trovati
	 * @throws SQLException 
	 */
	public List<Utente> findBySurname(String surname) throws SQLException{
		
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
	  * Ricerca Utente in base al nome o al cognome
	  * @param name Nome associato all'Utente
	  * @param surname Cognome associato all'Utente
	  * @return Lista degli Utente trovati
	  * @throws SQLException
	  */
	public List<Utente> findByNameSurname(String name, String surname) throws SQLException{
		
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
	 * Riempie una lista di Utente con i risultati di una query
	 * @param rs ResultSet con i risultati di una query
	 * @return Lista di Utente trovati
	 * @throws SQLException
	 */
	private List<Utente> fillResults(ResultSet rs) throws SQLException {
		List<Utente> results = new ArrayList<Utente>();
		
		while(rs.next()){
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
			
			results.add(new Utente(n, c, dn, cn, pn, cf, p, e, d, t)); 
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

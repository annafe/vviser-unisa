package it.unisa.vviser.storage;

import it.unisa.vviser.entity.Utente;
import it.vviser.common.CommonMethod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
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
					+ ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			st = conn.prepareStatement(query);
			st.setString(1, a.getNome());
			st.setString(2, a.getCognome());
			st.setString(3, CommonMethod.dateToString(a.getDataDiNascita()));
			st.setString(4, a.getComuneDiNascita());
			st.setString(5, a.getProvinciaDiNascita());
			st.setString(6, a.getCodiceFiscale());
			st.setString(7, a.getPassword());
			st.setString(8, a.getEmail());
			st.setString(9, a.getDipartimento());
			st.setString(10, a.getTipologia());
			
			if(st.executeUpdate() > 0){
				conn.commit();
				return true;
			}
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
		query = "DELETE FROM "+DBNames.TABLE_UTENTE+" WHERE "
					+DBNames.ATTR_UTENTE_EMAIL+"='"+u.getEmail()+"'";
		
		return update();
	}
	
	
	//METODI DI VISUALIZZAZIONE
	/**
	 * Restituisce la lista delgli utenti presenti nel sistema
	 * @return Lista degli utenti
	 * @throws SQLException
	 */
	public List<Utente> visualizzaUtenti() throws SQLException{
		
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
	 * Modifica il nome dell'utente
	 * @param nome Nuovo nome da memorizzare
	 * @return true se l'operazione è stata eseguita con successo, false altrimenti
	 * @throws SQLException 
	 */
	public boolean modificaNome(Utente u, String nome) throws SQLException{
		
		u.setNome(nome);
		query = "UPDATE "+DBNames.TABLE_UTENTE
					+" SET "+DBNames.ATTR_UTENTE_NOME+"='"+nome+"'"
					+" WHERE "+DBNames.ATTR_UTENTE_EMAIL+"='"+u.getEmail()+"'";
		
		return update();
	}
	
	/**
	 * Modifica il cognome dell'utente
	 * @param cognome Nuovo cognome da memorizzare
	 * @return true se l'operazione è stata eseguita con successo, false altrimenti
	 * @throws SQLException 
	 */
	public boolean modificaCognome(Utente u, String cognome) throws SQLException{
		
		u.setCognome(cognome);
		query = "UPDATE "+DBNames.TABLE_UTENTE+" "
					+" SET "+DBNames.ATTR_UTENTE_COGNOME+"='"+cognome+"'"
					+" WHERE "+DBNames.ATTR_UTENTE_EMAIL+"='"+u.getEmail()+"'";
		
		return update();
	}
	
	/**
	 * Modifica la data di nascita dell'utente
	 * @param u Utente da modificare
	 * @param dn Nuova data di nascita
	 * @return true se l'operazione è stata eseguita con successo, false altrimenti
	 * @throws SQLException
	 */
	public boolean modificaDataDiNascita(Utente u, GregorianCalendar dn) throws SQLException{
		
		u.setDataDiNascita(dn);
		String nuovaData = dn.get(Calendar.YEAR)+"-"
							+dn.get(Calendar.MONTH)+"-"
							+dn.get(Calendar.DAY_OF_MONTH);
		query = "UPDATE "+DBNames.TABLE_UTENTE+" "
					+" SET "+DBNames.ATTR_UTENTE_DATADINASCITA+"='"+nuovaData+"'"
					+" WHERE "+DBNames.ATTR_UTENTE_EMAIL+"='"+u.getEmail()+"'";
			
		return update();
	}
	
	/**
	 * Modifica il comune di nascita dell'utente
	 * @param u Utente da modificare
	 * @param c Nuovo comune di nascita
	 * @return true se l'operazione è stata eseguita con successo, false altrimenti
	 * @throws SQLException
	 */
	public boolean modificaComuneDiNascita(Utente u, String c) throws SQLException{
		
		u.setComuneDiNascita(c);
		query = "UPDATE "+DBNames.TABLE_UTENTE+" "
					+" SET "+DBNames.ATTR_UTENTE_COMUNEDINASCITA+"='"+c+"'"
					+" WHERE "+DBNames.ATTR_UTENTE_EMAIL+"='"+u.getEmail()+"'";
		
		return update();
	}
	
	/**
	 * Modifica la provincia di nascita dell'utente
	 * @param u Utente da modificare
	 * @param p Nuova provincia di nadcita
	 * @return true se l'operazione è stata eseguita con successo, false altrimenti
	 * @throws SQLException
	 */
	public boolean modificaProvinciaDiNascita(Utente u, String p) throws SQLException{
		
		u.setProvinciaDiNascita(p);
		query = "UPDATE "+DBNames.TABLE_UTENTE+" "
					+" SET "+DBNames.ATTR_UTENTE_PROVINCIADINASCITA+"='"+p+"'"
					+" WHERE "+DBNames.ATTR_UTENTE_EMAIL+"='"+u.getEmail()+"'";
		
		return update();
	}
	
	/**
	 * Modifica il codice fiscale dell'utente
	 * @param u Utente da modificare
	 * @param cf Nuovo codice fiscale
	 * @return true se l'operazione è stata eseguita con successo, false altrimenti
	 * @throws SQLException
	 */
	public boolean modificaCodiceFiscale(Utente u, String cf) throws SQLException{
		
		u.setCodiceFiscale(cf);
		query = "UPDATE "+DBNames.TABLE_UTENTE+" "
					+" SET "+DBNames.ATTR_UTENTE_CODICEFISCALE+"='"+cf+"'"
					+" WHERE "+DBNames.ATTR_UTENTE_EMAIL+"='"+u.getEmail()+"'";
		
		return update();
	}
	
	/**
	 * Modifica la password dell'utente
	 * @param u Utente da modificare
	 * @param p Nuova password
	 * @return true se l'operazione è stata eseguita con successo, false altrimenti
	 * @throws SQLException
	 */
	public boolean modificaPassword(Utente u, String p) throws SQLException{
		
		u.setPassword(p);
		query = "UPDATE "+DBNames.TABLE_UTENTE+" "
					+" SET "+DBNames.ATTR_UTENTE_PASSWORD+"='"+p+"'"
					+" WHERE "+DBNames.ATTR_UTENTE_EMAIL+"='"+u.getEmail()+"'";
		
		return update();
	}
	
	/**
	 * Modifica l'indirizzo email dell'utente
	 * @param u Utente da modificare
	 * @param e Nuovo indirizzo email
	 * @return true se l'operazione è stata eseguita con successo, false altrimenti
	 * @throws SQLException
	 */
	public boolean modificaEmail(Utente u, String e) throws SQLException{
		
		query = "UPDATE "+DBNames.TABLE_UTENTE+" "
					+" SET "+DBNames.ATTR_UTENTE_EMAIL+"='"+e+"'"
					+" WHERE "+DBNames.ATTR_UTENTE_EMAIL+"='"+u.getEmail()+"'";
		u.setEmail(e);
		
		return update();
	}

	/**
	 * Modifica il dipartimento a cui afferisce l'utente
	 * @param u Utente da modificare
	 * @param d Nuovo dipartimento
	 * @return true se l'operazione è stata eseguita con successo, false altrimenti
	 * @throws SQLException
	 */
	public boolean modificaDipartimento(Utente u, String d) throws SQLException{
		
		u.setDipartimento(d);
		query = "UPDATE "+DBNames.TABLE_UTENTE+" "
					+" SET "+DBNames.ATTR_UTENTE_DIPARTIMENTO_NOME+"='"+d+"'"
					+" WHERE "+DBNames.ATTR_UTENTE_EMAIL+"='"+u.getEmail()+"'";
		
		return update();
	}
	
	/**
	 * Modifica la tipologia associata all'utente
	 * @param u Utente da modificare
	 * @param t Nuova tipologia
	 * @return true se l'operazione è stata eseguita con successo, false altrimenti
	 * @throws SQLException
	 */
	public boolean modificaTipologia(Utente u, String t) throws SQLException{
		
		u.setTipologia(t);
		query = "UPDATE "+DBNames.TABLE_UTENTE+" "
					+" SET "+DBNames.ATTR_UTENTE_TIPOLOGIA+"='"+t+"'"
					+" WHERE "+DBNames.ATTR_UTENTE_EMAIL+"='"+u.getEmail()+"'";
		
		return update();
	}
	
	// LOGIN
	
	/**
	 * Effettua l'autenticazione di un utente per il login
	 * @param email Email dell'utente
	 * @param password Passord dell'utente
	 * @return Utente autenticato, null se l'utente non è presente nel sistema
	 * @throws SQLException
	 */
	public Utente authenticate(String email, String password) throws SQLException{
		
		Utente u = null;
		
		try{
			conn = DBConnectionPool.getConnection();
			query = "SELECT * FROM "+DBNames.TABLE_UTENTE
					+" WHERE "+DBNames.ATTR_UTENTE_EMAIL+"= ?"
					+" AND "+DBNames.ATTR_UTENTE_PASSWORD+"=?";
			st = conn.prepareStatement(query);
			st.setString(1, email);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();
			/*Statement st = null;
			ResultSet rs = null;
			query="SELECT * FROM "+DBNames.TABLE_UTENTE+" where "+
			DBNames.ATTR_UTENTE_EMAIL+"='" + email + 
			"' and "+DBNames.ATTR_UTENTE_PASSWORD+"='"+password+"'";
			st=conn.createStatement();
			rs = st.executeQuery(query);
			*/
			if(rs.next()){
				u= new Utente();
				u.setNome(rs.getString(DBNames.ATTR_UTENTE_NOME));
				u.setCognome(rs.getString(DBNames.ATTR_UTENTE_COGNOME));
				u.setDataDiNascita(CommonMethod.stringToDate(rs.getString(DBNames.ATTR_UTENTE_DATADINASCITA)));
				u.setProvinciaDiNascita(rs.getString(DBNames.ATTR_UTENTE_PROVINCIADINASCITA));
				u.setComuneDiNascita(rs.getString(DBNames.ATTR_UTENTE_COMUNEDINASCITA));
				u.setCodiceFiscale(rs.getString(DBNames.ATTR_UTENTE_CODICEFISCALE));
				u.setPassword(password);
				u.setEmail(email);
				u.setDipartimento(rs.getString(DBNames.ATTR_UTENTE_DIPARTIMENTO_NOME));
				u.setTipologia(rs.getString(DBNames.ATTR_UTENTE_TIPOLOGIA));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			st.close();
			DBConnectionPool.releaseConnection(conn);
		}
		
		return u;
		
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
			GregorianCalendar dn = CommonMethod.stringToDate(rs.getString(DBNames.ATTR_UTENTE_DATADINASCITA));
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
	 * Esegue un aggiornamento al database
	 * @return true se l'aggiornamento è stato eseguito con successo, false altrimenti
	 * @throws SQLException
	 */
	private boolean update() throws SQLException {
		try{
			conn = DBConnectionPool.getConnection();
			st = conn.prepareStatement(query);
			
			if(st.executeUpdate() > 0){
				conn.commit();
				return true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			st.close();
			DBConnectionPool.releaseConnection(conn);
		}
		return false;
	}
	
	/**
	 * @author Romano Simone
	 * @param email	
	 * @return Utente relativo alla email data in input o null se l'utente non è presente nel Database
	 * @throws SQLException 
	 */
	public Utente getUtente(String email) throws SQLException{
		Utente u = null;
		
		try{
			conn = DBConnectionPool.getConnection();
			query = "SELECT * FROM "+DBNames.TABLE_UTENTE
					+" WHERE "+DBNames.ATTR_UTENTE_EMAIL+"= ?";
			st = conn.prepareStatement(query);
			st.setString(1, email);
			ResultSet rs = st.executeQuery();
			if(rs.next()){
				u= new Utente();
				u.setNome(rs.getString(DBNames.ATTR_UTENTE_NOME));
				u.setCognome(rs.getString(DBNames.ATTR_UTENTE_COGNOME));
				u.setDataDiNascita(CommonMethod.stringToDate(rs.getString(DBNames.ATTR_UTENTE_DATADINASCITA)));
				u.setProvinciaDiNascita(rs.getString(DBNames.ATTR_UTENTE_PROVINCIADINASCITA));
				u.setComuneDiNascita(rs.getString(DBNames.ATTR_UTENTE_COMUNEDINASCITA));
				u.setCodiceFiscale(rs.getString(DBNames.ATTR_UTENTE_CODICEFISCALE));
				u.setPassword(rs.getString(DBNames.ATTR_UTENTE_PASSWORD));
				u.setEmail(email);
				u.setDipartimento(rs.getString(DBNames.ATTR_UTENTE_DIPARTIMENTO_NOME));
				u.setTipologia(rs.getString(DBNames.ATTR_UTENTE_TIPOLOGIA));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			st.close();
			DBConnectionPool.releaseConnection(conn);
		}
		
		return u;
	}
}

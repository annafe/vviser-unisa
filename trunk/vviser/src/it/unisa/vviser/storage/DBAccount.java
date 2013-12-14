package it.unisa.vviser.storage;

import it.unisa.vviser.entity.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 
 * @author Michele Roviello
 *
 */
public class DBAccount {
	
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
		Connection conn = null;
		PreparedStatement st = null;
		String query;
		
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
	
	/**
	 * Elimina un account dal sistema
	 * @param a Account da eliminare
	 * @return true se l'operazione è stata eseguita con successo, false altrimenti
	 * @throws SQLException
	 */
	public boolean removeAccount(Account a) throws SQLException{
		Connection conn = null;
		PreparedStatement st = null;
		String query;
		
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
}

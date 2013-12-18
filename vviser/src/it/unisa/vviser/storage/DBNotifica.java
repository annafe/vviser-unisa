package it.unisa.vviser.storage;

import it.unisa.vviser.entity.Notifica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBNotifica {
	private Connection conn = null;
	private PreparedStatement pstm = null;
	
	public DBNotifica(){}
	
	// METODI DI AGGIUNTA
	
	public boolean addNotifica(Notifica e) throws SQLException{
		conn = DBConnectionPool.getConnection();
		try{
			String queryParam = "INSERT INTO "+DBNames.TABLE_NOTIFICA+
					" ("+DBNames.ATTR_NOTIFICA_TIPO+","+DBNames.ATTR_NOTIFICA_MESSAGGIO+","+
					DBNames.ATTR_NOTIFICA_MITTENTE+","+DBNames.ATTR_NOTIFICA_DESTINATARIO+")"+
					" VALUES ("+e.getTipo()+","+e.getMessaggio()+","+e.getMittente()+","+
					e.getDestinatario()+");";
			
			pstm = conn.prepareStatement(queryParam);
			
			ResultSet toR = pstm.executeQuery();
			if (toR!=null)
				return true;
			else return false;
		}finally{
			pstm.close();
            DBConnectionPool.releaseConnection(conn);
		}
		
		
		
	}	
	
	/**
	 * metodo di servizio per la gestione Sottomissione a Valutazione
	 * @param isbn
	 * @return
	 * @throws SQLException
	 */
	public void addNotificaConflitto(String isbn) throws SQLException{
		
		String tipo = "conflitto";
		String amministratore="Amministratore";
		
		conn = DBConnectionPool.getConnection();
		try{
			
			//query nella tabella prodottolista
			//estrae tutti gli indirizzi email dei ricercatori coinvolti nel conflitto
			//del prodotto con isbn dato
			String query="SELECT "+DBNames.ATTR_PRODOTTOLISTA_UTENTE_EMAIL+
					" FROM "+DBNames.TABLE_PRODOTTOLISTA+" "+
					"WHERE "+DBNames.ATTR_PRODOTTOLISTA_PRODOTTO_ISBN+"="+isbn+";";
			
			pstm = conn.prepareStatement(query);
			ResultSet rs = pstm.executeQuery();
			
			DBNotifica notifiche = new DBNotifica();
			
			while(rs.next()){
				Notifica note = new Notifica(rs.getString(DBNames.ATTR_PRODOTTOLISTA_UTENTE_EMAIL),tipo, amministratore);
				notifiche.addNotifica(note);
			}
		}finally{
			pstm.close();
            DBConnectionPool.releaseConnection(conn);
		}
		
		
		
	}	
	
	/**
	 * metodo di servizio per la sottogestione validazione
	 * @param messaggio
	 * @return
	 * @throws SQLException
	 */
	public void addNotificaConflittoValidazione(String mittente, String isbn, String messaggio) throws SQLException{
		String tipo = "messaggio";
		
		conn = DBConnectionPool.getConnection();
		try{
			
			String query="SELECT "+DBNames.ATTR_PRODOTTO_EMAILPROPRIETARIO+
					" FROM "+DBNames.TABLE_PRODOTTO+" "+
					"WHERE "+DBNames.ATTR_PRODOTTO_ISBN+"="+isbn+";";
			
			pstm = conn.prepareStatement(query);
			ResultSet rs = pstm.executeQuery();
			
			DBNotifica notifiche = new DBNotifica();
			
			while(rs.next()){
				Notifica note = new Notifica(rs.getString(DBNames.ATTR_PRODOTTO_EMAILPROPRIETARIO),tipo, mittente, messaggio);
				notifiche.addNotifica(note);
			}
		}finally{
			pstm.close();
            DBConnectionPool.releaseConnection(conn);
		}
		
		
		
	}	
	
	// metodo di visualizzazione delle notifiche per un determinato utente
	
}

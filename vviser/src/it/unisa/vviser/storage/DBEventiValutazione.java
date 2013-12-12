package it.unisa.vviser.storage;

import it.unisa.vviser.storage.DBNames;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;



import java.util.List;

import it.unisa.vviser.entity.EventoValutazione;

/**
 * 
 * @author Maria Vittoria Coda
 *
 */

public class DBEventiValutazione {
	
	private Connection conn = null;
	private PreparedStatement pstm = null;
	private boolean statoConnessione;
	
	
	// Costruttore vuoto
	public DBEventiValutazione(){}
	
	
	
	// METODI DI AGGIUNTA
	
	/**
	 * inserisce nel database un nuovo evento di valutazione
	 * @param e nuovo evento di valutazione
	 * @return true se non si sono verificati problemi nella connesione al database o nell'esecuzione della query
	 * @throws SQLException
	 */
	public boolean addEvento(EventoValutazione e) throws SQLException{
		
		statoConnessione = this.connettiAlDatabase();
		if (!statoConnessione) return false;
		
		String queryParam = "INSERT INTO "+ DBNames.TABLE_EVENTOVALUTAZIONE+
				"("+DBNames.ATTR_EVENTOVALUTAZIONE_NOME+","+	DBNames.ATTR_EVENTOVALUTAZIONE_NUMERODIPUBBLICAZIONI+","+
				DBNames.ATTR_EVENTOVALUTAZIONE_DADATA+","+ DBNames.ATTR_EVENTOVALUTAZIONE_ADATA+","+
				DBNames.ATTR_EVENTOVALUTAZIONE_SCADENZA+") "+
				" VALUES ("+e.getNomeEvento()+","+e.getNumeroPubblicazioni()+
				","+e.getDataInizio()+","+e.getDataFine()+","+e.getScadenza()+";";
		
		if (this.eseguiQuery(queryParam)==null)
			return false;
		else
			return true;
	}

	
	// METODI DI MODIFICA 
	
	/**
	 * modifica il nome dell'evento in base all'ID
	 * @param id ID associato all'evento di cui modificare il nome
	 * @param nome nuovo nome dell'evento
	 * @return true true se non si sono verificati problemi nella connesione al database o nell'esecuzione della query
	 * @throws SQLException
	 */
	public boolean modifyNomeEventoByID(int id, String nome) throws SQLException{
		
		statoConnessione = this.connettiAlDatabase();
		if (!statoConnessione) return false;
		
		String queryParam = "UPDATE "+DBNames.TABLE_EVENTOVALUTAZIONE+
				" SET "+DBNames.ATTR_EVENTOVALUTAZIONE_NOME+" = "+nome+
				" WHERE "+DBNames.ATTR_EVENTOVALUTAZIONE_ID+" = "+id+";";
		
		if (this.eseguiQuery(queryParam)==null)
			return false;
		else
			return true;
	}
	
	/**
	 * modifica il nome dell'evento con un dato nome
	 * @param prevNome nome attuale dell'evento, da modificare
	 * @param nuovoNome nuovo nome dell'evento, da sostituire a quello precedente
	 * @return true se non si sono verificati problemi nella connesione al database o nell'esecuzione della query
	 * @throws SQLException
	 */
	public boolean modifyDataFineEventoByNome(String prevNome, String nuovoNome) throws SQLException{
		statoConnessione = this.connettiAlDatabase();
		if (!statoConnessione) return false;
		
		String queryParam = "UPDATE "+DBNames.TABLE_EVENTOVALUTAZIONE+
				" SET "+ DBNames.ATTR_EVENTOVALUTAZIONE_NOME +" = "+nuovoNome+
				" WHERE "+DBNames.ATTR_EVENTOVALUTAZIONE_NOME+" = "+prevNome+";";
		
		if (this.eseguiQuery(queryParam)==null)
			return false;
		else
			return true;
		
	}
	
	/**
	 * modifica la scadenza relativa ad un evento di valutazione in base al suo nome
	 * @param nome nome dell'evento di cui modificare la data di scadenza
	 * @param scadenza nuovo valore della data di scadenza
	 * @return true se non si sono verificati problemi nella connesione al database o nell'esecuzione della query
	 * @throws SQLException
	 */
	public boolean modifyScadenzaByNome(String nome, GregorianCalendar scadenza) throws SQLException{
		statoConnessione = this.connettiAlDatabase();
		if (!statoConnessione) return false;
		
		String queryParam = "UPDATE "+DBNames.TABLE_EVENTOVALUTAZIONE+
		" SET scadenza = "+scadenza.toString()+
		" WHERE "+DBNames.ATTR_EVENTOVALUTAZIONE_NOME+" = "+nome+";";
		
		if (this.eseguiQuery(queryParam)==null)
			return false;
		else
			return true;
	}
	
	/**
	 * modifica il numero di pubblicazioni relative ad un evento di associazione in base al suo nome
	 * @param num nuovo numero di pubblicazioni relative all'evento
	 * @param nome nome dell'evento da modificare
	 * @return true se non si sono verificati problemi nella connesione al database o nell'esecuzione della query
	 * @throws SQLException
	 */
	public boolean modifyNumPubblicazioniByNome(int num, String nome) throws SQLException{
		statoConnessione = this.connettiAlDatabase();
		if (!statoConnessione) return false;
		
		String queryParam = "UPDATE "+DBNames.ATTR_EVENTOVALUTAZIONE_NOME+
				" SET "+DBNames.ATTR_EVENTOVALUTAZIONE_NUMERODIPUBBLICAZIONI+" = "+num+
				" WHERE "+DBNames.ATTR_EVENTOVALUTAZIONE_NOME+" = "+nome+";";
		
		if (this.eseguiQuery(queryParam)==null)
			return false;
		else
			return true;
	}
	
	/**
	 * modifica la data di inizio di un evento di valutazione in base al nome
	 * @param inizio nuova data di inizio
	 * @param nome nome dell'evento di cui modificare la data di inizio  
	 * @return true se non si sono verificati problemi nella connesione al database o nell'esecuzione della query
	 * @throws SQLException
	 */
	public boolean modifyDataInizioByNome(GregorianCalendar inizio, String nome) throws SQLException{
		statoConnessione = this.connettiAlDatabase();
		if (!statoConnessione) return false;
		
		String queryParam = "UPDATE "+DBNames.TABLE_EVENTOVALUTAZIONE+
					"SET "+DBNames.ATTR_EVENTOVALUTAZIONE_DADATA +" = "+inizio.get(Calendar.YEAR)+"/"+inizio.get(Calendar.MONTH)+"/"+inizio.get(Calendar.DAY_OF_MONTH)+
					" WHERE "+DBNames.ATTR_EVENTOVALUTAZIONE_NOME+" = "+nome+";";
		
		if (this.eseguiQuery(queryParam)==null)
			return false;
		else
			return true;
	}
	
	/**
	 * modifica la data di fine di un evento di valutazione in base al nome
	 * @param fine data di fine dell'evento di valutazione
	 * @param nome nome dell'evento di cui si vuole modificare la data di fine
	 * @return true se non si sono verificati problemi nella connesione al database o nell'esecuzione della query
	 * @throws SQLException
	 */
	public boolean modifyDataFineByNome(GregorianCalendar fine, String nome) throws SQLException{
		statoConnessione = this.connettiAlDatabase();
		if (!statoConnessione) return false;
		
		String queryParam = "UPDATE "+DBNames.TABLE_EVENTOVALUTAZIONE+
					" SET "+DBNames.ATTR_EVENTOVALUTAZIONE_ADATA+" = "+fine.get(Calendar.YEAR)+"/"+fine.get(Calendar.MONTH)+"/"+fine.get(Calendar.DAY_OF_MONTH)+
					" WHERE "+DBNames.ATTR_EVENTOVALUTAZIONE_NOME+" = "+nome+";";
		
		if (this.eseguiQuery(queryParam)==null)
			return false;
		else
			return true;
	}
	
	
	
	
	// METODI DI VISUALIZZAZIONE (?)
	
	public List<EventoValutazione> visualizzaEventiPerNome(EventoValutazione e) throws SQLException{
        List<EventoValutazione> toReturn = new ArrayList<EventoValutazione>();
        
		statoConnessione = this.connettiAlDatabase();
		if (!statoConnessione) {
			throw new SQLException();
		}
		
		String queryParam = "SELECT * FROM "+DBNames.TABLE_EVENTOVALUTAZIONE+
					" WHERE "+DBNames.ATTR_EVENTOVALUTAZIONE_ID+" = "+e.getDataFine()+";";
		try{
			pstm = conn.prepareStatement(queryParam);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()){
				GregorianCalendar scadenza, inizio, fine;
				
				scadenza = creaData(rs.getString("scadenza"));
				inizio = creaData(rs.getString("daData"));
				fine = creaData(rs.getString("aData"));
				
				EventoValutazione evento = new EventoValutazione(rs.getString("nome"), rs.getInt("numeroPubblicazioni"), scadenza, inizio, fine);			
				toReturn.add(evento);
			}
		}	
		catch(SQLException exc){
				exc.printStackTrace();
		}
		finally{
			if (conn != null) conn.close();
		}
		
        return toReturn;
	}
	
	public List<EventoValutazione> visualizzaEventiPerScadenza(EventoValutazione e) throws SQLException{
        List<EventoValutazione> toReturn = new ArrayList<EventoValutazione>();
        
		statoConnessione = this.connettiAlDatabase();
		if (!statoConnessione) {
			throw new SQLException();
		}
		
		String queryParam = "SELECT * FROM "+DBNames.TABLE_EVENTOVALUTAZIONE+
					" WHERE "+DBNames.ATTR_EVENTOVALUTAZIONE_SCADENZA+" = "+e.getScadenza()+";";
		try{
			pstm = conn.prepareStatement(queryParam);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()){
				GregorianCalendar scadenza, inizio, fine;
				
				scadenza = creaData(rs.getString("scadenza"));
				inizio = creaData(rs.getString("daData"));
				fine = creaData(rs.getString("aData"));
				
				EventoValutazione evento = new EventoValutazione(rs.getString("nome"), rs.getInt("numeroPubblicazioni"), scadenza, inizio, fine);			
				toReturn.add(evento);
			}
		}	
		catch(SQLException exc){
				exc.printStackTrace();
		}
		finally{
			if (conn != null) conn.close();
		}
		
        return toReturn;
	}
	
	public List<EventoValutazione> visualizzaEventiPerDataFine(EventoValutazione e) throws SQLException{
        List<EventoValutazione> toReturn = new ArrayList<EventoValutazione>();
        
		statoConnessione = this.connettiAlDatabase();
		if (!statoConnessione) {
			throw new SQLException();
		}
		
		String queryParam = "SELECT * FROM eventovalutazione"+
					"WHERE aData = "+e.getDataFine()+";";
		try{
			pstm = conn.prepareStatement(queryParam);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()){
				GregorianCalendar scadenza, inizio, fine;
				
				scadenza = creaData(rs.getString("scadenza"));
				inizio = creaData(rs.getString("daData"));
				fine = creaData(rs.getString("aData"));
				
				EventoValutazione evento = new EventoValutazione(rs.getString("nome"), rs.getInt("numeroPubblicazioni"), scadenza, inizio, fine);			
				toReturn.add(evento);
			}
		}	
		catch(SQLException exc){
				exc.printStackTrace();
		}
		finally{
			if (conn != null) conn.close();
		}
		
        return toReturn;
	}
	
	
	
	
	// METODI DI ELIMINAZIONE
	
	/**
	 * elimina dal database un evento in base all'ID
	 * @param e evento di valutazione da eliminare
	 * @return true se l'eliminazione ha avuto successo e se non si sono verificati problemi nella connessione
	 * @throws SQLException
	 */
	public boolean deleteEventoByID(EventoValutazione e) throws SQLException{
		statoConnessione = this.connettiAlDatabase();
		if (!statoConnessione) return false;
		
		String queryParam = "DELETE FROM "+DBNames.TABLE_EVENTOVALUTAZIONE+
				" WHERE "+DBNames.ATTR_EVENTOVALUTAZIONE_ID+" = "+e.getID()+";";
		
		if (this.eseguiQuery(queryParam)==null)
			return false;
		else
			return true;
		
	}
	
	/**
	 * elimina dal database un evento in base al nome
	 * @param nome nome dell'evento da eliminare
	 * @return true se l'eliminazione ha avuto successo e se non si sono verificati problemi nella connessione
	 * @throws SQLException
	 */
	public boolean deleteEventoByNome(String nome) throws SQLException{
		statoConnessione = this.connettiAlDatabase();
		if (!statoConnessione) return false;
		
		String queryParam = "DELETE FROM "+DBNames.TABLE_EVENTOVALUTAZIONE+
				" WHERE "+DBNames.ATTR_EVENTOVALUTAZIONE_NOME+" = "+nome+";";
		
		if (this.eseguiQuery(queryParam)==null)
			return false;
		else
			return true;
	}
	
	
	
	
	// ALTRI METODI
	
	public boolean invioNotificaConflitto(String tipoNotifica) throws SQLException{
		String query = "SELECT "+DBNames.ATTR_PRODOTTOINCONFLITTO_PRODOTTO_ISBN+
				" FROM "+DBNames.TABLE_PRODOTTOINCONFLITTO;
		
		String queryParam = "INSERT INTO "+DBNames.TABLE_NOTIFICA+
				" ( "+DBNames.ATTR_NOTIFICA_TIPO+")"+
				" VALUES ( "+tipoNotifica+" );";
		if (this.eseguiQuery(queryParam)==null)
			return false;
		else
			return true;
	}
	
	public void prodottiInStatoBozza(){
		
	}
	
	
	// METODI DI SUPPORTO PER LA CLASSE
	
	/**
	 * esegue la connessione al database
	 * @return true se la connessione con il database è stata stabilita, false altrimenti
	 * @throws SQLException
	 */
	private boolean connettiAlDatabase() throws SQLException{
		
		try{
			Class.forName("com.mysql.jdbc.Driver"); //caricamento driver
			conn = DriverManager.getConnection("jdbc:mysql://localhost/vviser?user=root&password=");
			return true;
		}
		catch (Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	/**
	 * esegue la query sul database
	 * @param query stringa che contiene la query da eseguire
	 * @return un valore diverso da null se la query è andata a buon fine
	 * @throws SQLException
	 */
	private ResultSet eseguiQuery(String query) throws SQLException{
		ResultSet toR=null;
		try{
			pstm = conn.prepareStatement(query);
			toR = pstm.executeQuery();
			return toR;
		} 
		catch(SQLException exc){
			exc.printStackTrace();
			return toR;
		}
		finally{
			if (conn != null) conn.close();
			pstm.close();
			
		}
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
		
		g[0]=Integer.parseInt((tokens[0])); 
		g[1]=Integer.parseInt((tokens[1])); 
		g[2]=Integer.parseInt((tokens[2]));
		
		toReturn = new GregorianCalendar(g[0],g[1],g[2]);
		return toReturn;
	}
}

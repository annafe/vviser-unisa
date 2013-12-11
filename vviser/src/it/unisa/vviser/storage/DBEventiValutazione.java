package it.unisa.vviser.storage;

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

	private final static String table = DBNames.TABLE_EVENTOVALUTAZIONE;
	private final static String nomeEvento = DBNames.ATTR_EVENTOVALUTAZIONE_NOME;
	private final static String idEvento = DBNames.ATTR_EVENTOVALUTAZIONE_ID;
	private final static String numPubbEvento = DBNames.ATTR_EVENTOVALUTAZIONE_NUMERODIPUBBLICAZIONI;
	private final static String dataInizioEvento = DBNames.ATTR_EVENTOVALUTAZIONE_DADATA;
	private final static String dataFineEvento = DBNames.ATTR_EVENTOVALUTAZIONE_ADATA;
	private final static String scadenzaEvento = DBNames.ATTR_EVENTOVALUTAZIONE_SCADENZA;
	
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
		
		String queryParam = "INSERT INTO "+ table+
				"("+nomeEvento+","+	numPubbEvento+","+
				dataInizioEvento+","+ dataFineEvento+","+
				scadenzaEvento+") "+
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
		
		String queryParam = "UPDATE "+table+
				" SET "+nomeEvento+" = "+nome+
				" WHERE "+idEvento+" = "+id+";";
		
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
	public boolean modifyNomeEventoByNome(String prevNome, String nuovoNome) throws SQLException{
		statoConnessione = this.connettiAlDatabase();
		if (!statoConnessione) return false;
		
		String queryParam = "UPDATE "+table+
				" SET "+ nomeEvento +" = "+nuovoNome+
				" WHERE "+nomeEvento+" = "+prevNome+";";
		
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
		
		String queryParam = "UPDATE "+table+
		" SET scadenza = "+scadenza.toString()+
		" WHERE "+nomeEvento+" = "+nome+";";
		
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
		
		String queryParam = "UPDATE "+nomeEvento+
				" SET "+numPubbEvento+" = "+num+
				" WHERE "+nomeEvento+" = "+nome+";";
		
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
		
		String queryParam = "UPDATE "+table+
					"SET "+dataInizioEvento +" = "+inizio.get(Calendar.YEAR)+"/"+inizio.get(Calendar.MONTH)+"/"+inizio.get(Calendar.DAY_OF_MONTH)+
					" WHERE "+nomeEvento+" = "+nome+";";
		
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
		
		String queryParam = "UPDATE "+table+
					" SET "+dataFineEvento+" = "+fine.get(Calendar.YEAR)+"/"+fine.get(Calendar.MONTH)+"/"+fine.get(Calendar.DAY_OF_MONTH)+
					" WHERE "+nomeEvento+" = "+nome+";";
		
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
		
		String queryParam = "SELECT * FROM "+table+
					" WHERE "+idEvento+" = "+e.getNomeEvento()+";";
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
		
		String queryParam = "SELECT * FROM "+table+
					" WHERE "+scadenzaEvento+" = "+e.getScadenza()+";";
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
		
		String queryParam = "DELETE FROM "+table+
				" WHERE "+idEvento+" = "+e.getID()+";";
		
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
		
		String queryParam = "DELETE FROM "+table+
				" WHERE "+nomeEvento+" = "+nome+";";
		
		if (this.eseguiQuery(queryParam)==null)
			return false;
		else
			return true;
	}
	
	
	
	
	// ALTRI METODI
	
	public void prodottiInStatoBozza(){
		
	}
	
	
	// METODI DI SUPPORTO PER LA CLASSE
	
	/**
	 * esegue la connessione al database
	 * @return true se la connessione con il database ? stata stabilita, false altrimenti
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
	 * @return un valore diverso da null se la query ? andata a buon fine
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

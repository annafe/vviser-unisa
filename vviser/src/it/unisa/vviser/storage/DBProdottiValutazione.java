package it.unisa.vviser.storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import it.unisa.vviser.entity.EventoValutazione;
import it.unisa.vviser.entity.ListaProdottiValutazione;
import it.unisa.vviser.entity.Prodotto;
import it.unisa.vviser.entity.ProdottoValutazione;
import it.unisa.vviser.entity.Utente;
import it.unisa.vviser.exception.InsertProdottiValutazioneException;
import it.unisa.vviser.exception.InvalidModifyListaProdottiValutazione;
import it.unisa.vviser.exception.NotFoundListeValutazioneException;
import it.vviser.common.CommonMethod;


/**
 * 
 * @author Giuseppe Sabato
 *
 */
public class DBProdottiValutazione {
	
	private static DBProdottiValutazione manager;
	
	/**
	 * Costruttore vuoto
	 */
	private DBProdottiValutazione()
	{
		
	}
	
	/**
	 * Metodo che implementa il design pattern singleton
	 * @return manager istanza di DBProdottiValutazione
	 */
	public static DBProdottiValutazione getInstance()
	{
		if(manager==null)
			manager=new DBProdottiValutazione();
		return manager;
		
	}
	
	/**
	 * Metodo che inserisce nel database i prodotti sottomessi a valutazione
	 * ed inserisce nell'apposita tabella anche gli eventuali conflitti
	 * @param listaProdottiValutazione da inserire nel database
	 * @param conn connessione al database
	 */
	private void inserisciProdottiValutazione(ListaProdottiValutazione listaProdottiValutazione,Connection conn) throws SQLException
	{
		for(int i=0;i<listaProdottiValutazione.getListaProdottiValutazione().size();i++)
        {
			PreparedStatement st=null;
			String query;
        	
            //Se in conflitto, inserisco il prodotto nella tabella dei prodotti in conflitto.
            if(isInConflitto(listaProdottiValutazione.getListaProdottiValutazione().get(i),listaProdottiValutazione.getIdEventoValutazione(),conn))
            {
            	query= "INSERT INTO "+DBNames.TABLE_PRODOTTOINCONFLITTO+"("
	            		+DBNames.ATTR_PRODOTTOINCONFLITTO_PRODOTTO_ISBN+","
	            		+DBNames.ATTR_PRODOTTOINCONFLITTO_UTENTE_EMAIL+","
	            		+DBNames.ATTR_PRODOTTOINCONFLITTO_EVENTOVALUTAZIONE_ID+
	            		") values (?,?,?)";
	            
	            st = conn.prepareStatement(query);
	            st.setString(1, listaProdottiValutazione.getListaProdottiValutazione().get(i).getIsbn());
	            st.setString(2, listaProdottiValutazione.getEmailUtente());
	            st.setInt(3, listaProdottiValutazione.getIdEventoValutazione());
	
	            st.executeUpdate();
	            conn.commit();
	            //Chiamo il DB del sistema, e richiama il metodo per inviare la notifica
	            //addNotificaConflitto(String isbn)
            }
            
            
            query= "INSERT INTO "+DBNames.TABLE_PRODOTTOLISTA+"("
            		+DBNames.ATTR_PRODOTTOLISTA_PRODOTTO_ISBN+","
            		+DBNames.ATTR_PRODOTTOLISTA_UTENTE_EMAIL+","
            		+DBNames.ATTR_PRODOTTOLISTA_EVENTOVALUTAZIONE_ID+","
            		+DBNames.ATTR_PRODOTTOLISTA_PRIORITA+
            		") values (?,?,?,?)";
            
            st = conn.prepareStatement(query);
            st.setString(1, listaProdottiValutazione.getListaProdottiValutazione().get(i).getIsbn());
            st.setString(2, listaProdottiValutazione.getEmailUtente());
            st.setInt(3, listaProdottiValutazione.getIdEventoValutazione());
            st.setInt(4, listaProdottiValutazione.getListaProdottiValutazione().get(i).getPriority());

            st.executeUpdate();
            conn.commit();
            
        }
	}
	
	/**
	 * Metodo che crea ed inserisce nel database una lista di prodotti da sottomettere per valutazione
	 * @param prodottiValutazione lista prodotti da sottomettere a valutazione
	 * @param emailUtente identificativo dell'utente
	 * @throws SQLException
	 */
	public void sottomettiListaProdottiValutazione(ArrayList<ProdottoValutazione> prodottiValutazione,String emailUtente) throws SQLException,InsertProdottiValutazioneException
	{
		Connection conn=null;
		PreparedStatement st=null;
		String query;
		
		
        try 
        {
            conn = DBConnectionPool.getConnection();
            
            
            int idEvento=getEventoValutazione(emailUtente).getID();
			
			ListaProdottiValutazione lp=new ListaProdottiValutazione(prodottiValutazione,emailUtente, idEvento,"");
			
			//Stabilisco la partecipazione dell'utente all'evento di valutazione
			query="INSERT INTO "+DBNames.TABLE_PARTECIPAZIONEAVALUTAZIONE+"("
					+DBNames.ATTR_PARTECIPAZIONEAVALUTAZIONE_UTENTE_EMAIL+","
					+DBNames.ATTR_PARTECIPAZIONEAVALUTAZIONE_EVENTOVALUTAZIONE_ID+
					") values (?,?)";
			
			st=conn.prepareStatement(query);
			st.setString(1,lp.getEmailUtente());
			st.setInt(2, lp.getIdEventoValutazione());
			
			st.executeUpdate();
			conn.commit();
			
            
            
            query="INSERT INTO "+DBNames.TABLE_LISTAVALUTAZIONE+"("
            		+DBNames.ATTR_LISTAVALUTAZIONE_UTENTE_EMAIL+","
            		+DBNames.ATTR_LISTAVALUTAZIONE_EVENTOVALUTAZIONE_ID+
            		") values (?,?)";
            
            st=conn.prepareStatement(query);
            st.setString(1, lp.getEmailUtente());
            st.setInt(2, lp.getIdEventoValutazione());
            
            st.executeUpdate();
            conn.commit();
            
            inserisciProdottiValutazione(lp,conn);
            
	       
        } 
        finally 
        {
            st.close();
            DBConnectionPool.releaseConnection(conn);
        }
	}
	
	/**
	 *Metodo che mostra i prodotti sottomessi a valutazione dell'utente in input
	 * @param emailUtente identificativo dell'utente
	 * @param idEvento identificativo dell'evento di valutazione
	 * @return listaProdottiValutazione lista prodotti sottomessi a valutazione dall'utente
	 * @throws SQLException
	 */
	public ListaProdottiValutazione showProdottiVal(String emailUtente,int idEvento) throws SQLException
	{
		Connection conn=null;
		Statement st=null;
		ResultSet ris=null;
		String query;
		ListaProdottiValutazione listaProdottiValutazione=null;
		
		try
		{
			conn=DBConnectionPool.getConnection();
			
			//ricavo il suggerimento relativo alla lista
			query="SELECT "+DBNames.ATTR_LISTAVALUTAZIONE_SUGGERIMENTO+","
					+DBNames.ATTR_LISTAVALUTAZIONE_BLOCCATO
					+" FROM "+DBNames.TABLE_LISTAVALUTAZIONE
					+" WHERE "+DBNames.ATTR_LISTAVALUTAZIONE_UTENTE_EMAIL+"="
					+"'"+emailUtente+"'"+" AND "+DBNames.ATTR_LISTAVALUTAZIONE_EVENTOVALUTAZIONE_ID+"="
					+idEvento;
			
			st=conn.createStatement();
			ris=st.executeQuery(query);
			String suggerimento=null;
			String stato=null;
			boolean bloccato=true;
			while (ris.next())
			{
				suggerimento=ris.getString(DBNames.ATTR_LISTAVALUTAZIONE_SUGGERIMENTO);
				stato=ris.getString(DBNames.ATTR_LISTAVALUTAZIONE_BLOCCATO);
			}
			
			if(stato.equals("si"))
				bloccato=true;
			else
			{
				if(stato.equals("no"))
					bloccato=false;
			}
				
			listaProdottiValutazione=new ListaProdottiValutazione(new ArrayList<ProdottoValutazione>(),emailUtente,idEvento,suggerimento);
			listaProdottiValutazione.setBloccato(bloccato);
					
			query="SELECT "+DBNames.ATTR_PRODOTTO_ISBN+","
					+DBNames.ATTR_PRODOTTO_TITOLO+","
					+DBNames.ATTR_PRODOTTOLISTA_PRIORITA
					+ " FROM " +DBNames.TABLE_PRODOTTO+","+DBNames.TABLE_PRODOTTOLISTA
					+ " WHERE "+DBNames.ATTR_PRODOTTO_ISBN+"="+DBNames.ATTR_PRODOTTOLISTA_PRODOTTO_ISBN
					+" and "+DBNames.ATTR_PRODOTTOLISTA_UTENTE_EMAIL+"="+"'"+listaProdottiValutazione.getEmailUtente()+"'"
					+" and "+DBNames.ATTR_PRODOTTOLISTA_EVENTOVALUTAZIONE_ID+"="+listaProdottiValutazione.getIdEventoValutazione();
			
			st=conn.createStatement();
			ris=st.executeQuery(query);
			while(ris.next())
			{
				String isbn=ris.getString(DBNames.ATTR_PRODOTTO_ISBN);
				String title=ris.getString(DBNames.ATTR_PRODOTTO_TITOLO);
				int priority=ris.getInt(DBNames.ATTR_PRODOTTOLISTA_PRIORITA);
				
				ProdottoValutazione p=new ProdottoValutazione(isbn,title,priority);
				listaProdottiValutazione.addProdottoValutazione(p);
			}
		}
		finally
		{
			st.close();
			DBConnectionPool.releaseConnection(conn);
		}
		
		return listaProdottiValutazione;
	}
	
	/**
	 * Metodo che restituisce una lista di utenti che hanno almeno una lista di
	 * prodotti sottomessi a valutazione
	 * @param emailUtente identificativo dell'utente
	 * @return utenti lista di utenti
	 * @throws SQLException
	 */
	public ArrayList<Utente> showListaUtenti(String emailUtente) throws SQLException
	{
		Connection conn=null;
		Statement st=null;
		ResultSet ris=null;
		String query;
		
		ArrayList<Utente> utenti=new ArrayList<Utente>();
		try
		{
			conn=DBConnectionPool.getConnection();
			//Seleziono tutti i ricercatori che hanno almeno una lista di prodotti
			//sottomessi a valutazione, eccetto il ricercatore che accede alla
			//funzionalita'
			query="SELECT DISTINCT "+DBNames.ATTR_UTENTE_EMAIL+","//Distinct, perche' altrimenti possono presentarsi duplicati di nomi di ricercatori che hanno partecipato a piu' eventi di valutazione
					+DBNames.ATTR_UTENTE_PASSWORD+","+DBNames.ATTR_UTENTE_CODICEFISCALE+","
					+DBNames.ATTR_UTENTE_PROVINCIADINASCITA+","+DBNames.ATTR_UTENTE_COMUNEDINASCITA+","
					+DBNames.ATTR_UTENTE_COGNOME+","+DBNames.ATTR_UTENTE_NOME+","
					+DBNames.ATTR_UTENTE_DATADINASCITA+","+DBNames.ATTR_UTENTE_TIPOLOGIA+","
					+DBNames.ATTR_UTENTE_DIPARTIMENTO_NOME
					+" FROM "+DBNames.TABLE_UTENTE+","
					+DBNames.TABLE_LISTAVALUTAZIONE
					+" WHERE "+DBNames.ATTR_UTENTE_EMAIL+"="
					+DBNames.ATTR_LISTAVALUTAZIONE_UTENTE_EMAIL
					+" AND "+DBNames.ATTR_UTENTE_EMAIL+" NOT IN("
					+"SELECT "+DBNames.ATTR_UTENTE_EMAIL
					+" FROM "+DBNames.TABLE_UTENTE
					+" WHERE "+DBNames.ATTR_UTENTE_EMAIL+"="+"'"+emailUtente+"')";
			st=conn.createStatement();
			ris=st.executeQuery(query);
			while(ris.next())
			{
				String email=ris.getString(DBNames.ATTR_UTENTE_EMAIL);
				String password=ris.getString(DBNames.ATTR_UTENTE_PASSWORD);
				String codiceFiscale=ris.getString(DBNames.ATTR_UTENTE_CODICEFISCALE);
				String provinciaNascita=ris.getString(DBNames.ATTR_UTENTE_PROVINCIADINASCITA);
				String comuneNascita=ris.getString(DBNames.ATTR_UTENTE_COMUNEDINASCITA);
				String cognome=ris.getString(DBNames.ATTR_UTENTE_COGNOME);
				String nome=ris.getString(DBNames.ATTR_UTENTE_NOME);
				GregorianCalendar dataNascita=CommonMethod.stringToDate(ris.getString(DBNames.ATTR_UTENTE_DATADINASCITA));
				String tipologia=ris.getString(DBNames.ATTR_UTENTE_TIPOLOGIA);
				String dipartimento=ris.getString(DBNames.ATTR_UTENTE_DIPARTIMENTO_NOME);
				Utente utente=new Utente(nome,cognome,dataNascita,comuneNascita,provinciaNascita,codiceFiscale,password,email,dipartimento,tipologia);
				utenti.add(utente);
			}
		}
		finally
		{
			st.close();
			DBConnectionPool.releaseConnection(conn);
		}
		return utenti;
	}
	
	/**
	 *Metodo che permette di sostituire nel database un prodotto sottomesso a valutazione
	 *in conflitto con un altro prodotto
	 * @param lp lista prodotti sottomessi a valutazione che contiene il prodotto in conflitto
	 * @param prodottiValutazione prodotti sottomessi a valutazione in conflitto
	 * @param prodotti per sostituire quelli in conflitto
	 * @throws SQLException
	 */
	public void modifyProdottiValutazione(ArrayList<ProdottoValutazione> newProdottiSottomessi,ArrayList<ProdottoValutazione> oldProdottiSottomessi,ArrayList<ProdottoValutazione> oldProdottiSottomessiUpdate,String emailUtente,int idEvento) throws SQLException
	{
		Connection conn=null;
		PreparedStatement st=null;
		String query;
		Statement st1=null;
		ResultSet ris=null;
		String query1;
		
			
			try
			{
				conn=DBConnectionPool.getConnection();
				
				
				if(oldProdottiSottomessi.size()!=0)
				{
					
					for(int i=0;i<oldProdottiSottomessi.size();i++)
					{
						//cancello i prodotti di valutazione deselezionati
						query="DELETE FROM "+DBNames.TABLE_PRODOTTOLISTA
								+" WHERE "+DBNames.ATTR_PRODOTTOLISTA_PRODOTTO_ISBN+"="+"'"+oldProdottiSottomessi.get(i).getIsbn()+"'"
								+" and "+DBNames.ATTR_PRODOTTOLISTA_UTENTE_EMAIL+"="+"'"+emailUtente+"'"
								+" and "+DBNames.ATTR_PRODOTTOLISTA_EVENTOVALUTAZIONE_ID+"="+idEvento;
						
						st=conn.prepareStatement(query);
						st.executeUpdate();
						conn.commit();
						
						query1="SELECT count(*) as numero"
								+" FROM "+DBNames.TABLE_PRODOTTOINCONFLITTO
								+" WHERE "+DBNames.ATTR_PRODOTTOINCONFLITTO_PRODOTTO_ISBN+"="+"'"+oldProdottiSottomessi.get(i).getIsbn()+"'"
								+" AND "+DBNames.ATTR_PRODOTTOINCONFLITTO_EVENTOVALUTAZIONE_ID+"="+idEvento;
						
						st1=conn.createStatement();
						ris=st1.executeQuery(query1);
						ris.next();
						int numProdottiValutazione=ris.getInt("numero");
						
						if(numProdottiValutazione==2)//se uno dei due utenti toglie dalla lista il prodotto sottomesso a valutazione, il conflitto si risolve e quindi 
						{							//i due prodotti presenti nella tabella dei conflitti vanno eliminati.
							
							
							query="DELETE FROM "+DBNames.TABLE_PRODOTTOINCONFLITTO
									+" WHERE "+DBNames.ATTR_PRODOTTOINCONFLITTO_PRODOTTO_ISBN+"="+"'"+oldProdottiSottomessi.get(i).getIsbn()+"'"
									+" AND "+DBNames.ATTR_PRODOTTOLISTA_EVENTOVALUTAZIONE_ID+"="+idEvento;
							
							st=conn.prepareStatement(query);
							st.executeUpdate();
							conn.commit();
						}
						else
						{
							if(numProdottiValutazione>2)//elimino il prodotto dalla tabella, ma il conflitto non e'ancora risolto
							{
								query="DELETE FROM "+DBNames.TABLE_PRODOTTOINCONFLITTO
										+" WHERE "+DBNames.ATTR_PRODOTTOINCONFLITTO_PRODOTTO_ISBN+"="+"'"+oldProdottiSottomessi.get(i).getIsbn()+"'"
										+" AND "+DBNames.ATTR_PRODOTTOLISTA_UTENTE_EMAIL+"="+"'"+emailUtente+"'"
										+" AND "+DBNames.ATTR_PRODOTTOLISTA_EVENTOVALUTAZIONE_ID+"="+idEvento;
								
								st=conn.prepareStatement(query);
								st.executeUpdate();
								conn.commit();
							}
						}
					}
				}
					
				if(oldProdottiSottomessiUpdate.size()!=0)
				{
					for(int i=0;i<oldProdottiSottomessiUpdate.size();i++)
					{
						//aggiorno gli eventuali camibamenti di priorita' dei prodotti sottomessi prima della modifica
						query="UPDATE "+DBNames.TABLE_PRODOTTOLISTA
								+" SET "+DBNames.ATTR_PRODOTTOLISTA_PRIORITA+"=?"
								+ " WHERE "+DBNames.ATTR_PRODOTTOLISTA_PRODOTTO_ISBN+"="+"'"+oldProdottiSottomessiUpdate.get(i).getIsbn()+"'"
								+" and "+DBNames.ATTR_PRODOTTOLISTA_UTENTE_EMAIL+"="+"'"+emailUtente+"'"
								+" and "+DBNames.ATTR_PRODOTTOLISTA_EVENTOVALUTAZIONE_ID+"="+idEvento;
						
						st=conn.prepareStatement(query);
						st.setInt(1, oldProdottiSottomessiUpdate.get(i).getPriority());
						st.executeUpdate();
						conn.commit();
					}
				}	
				
				if(newProdottiSottomessi.size()!=0)
				{
					//suggerimento e blocco della lista fittizi, in quanto andiamo ad inserire i prodotti all'interno della lista, e non quest'ultima.
					ListaProdottiValutazione listaNewProdottiValutazione=new ListaProdottiValutazione(newProdottiSottomessi,emailUtente,idEvento,"");
					//inserisco i nuovi prodotti di valutazione selezionati e gli eventuali
					//conflitti nell'apposita tabella
					inserisciProdottiValutazione(listaNewProdottiValutazione, conn);
				}
				
			}
			finally
			{
				if(oldProdottiSottomessi.size()!=0)
					st1.close();
				st.close();
				DBConnectionPool.releaseConnection(conn);
			}
		}
		
	
	/**
	 * Questo metodo restituisce tutte le liste di prodotti sottomessi a valutazione dall'utente specificato
	 * @param emailUtente identificativo dell'utente
	 * @return listeProdottiValutazione dell'utente specificato
	 * @throws SQLException
	 */
	public ArrayList<ListaProdottiValutazione> getListeProdottiValutazione(String emailUtente) throws SQLException
	{
		ArrayList<ListaProdottiValutazione> listeProdottiValutazione=new ArrayList<ListaProdottiValutazione>();
		Connection conn=null;
		Statement st=null;
		ResultSet ris=null;
		String query;
		
		try
		{
			conn=DBConnectionPool.getConnection();
			
			//Conto le liste di prodotti sottomessi a valutazione
			//dell'utente con email dato in input
			query="SELECT count(*) as numListe"
					+" FROM "+DBNames.TABLE_LISTAVALUTAZIONE
					+" WHERE "+DBNames.ATTR_LISTAVALUTAZIONE_UTENTE_EMAIL+"="+"'"+emailUtente+"'";
			
			st=conn.createStatement();
			ris=st.executeQuery(query);
			ris.next();
			int numeroListe=ris.getInt("numListe");
			
			if(numeroListe==0)
				throw new NotFoundListeValutazioneException("Non sono presenti liste di prodotti sottomessi a valutazione !");
			else
			{
				//recupero le informazini per costruire la/e lista/e dell'utente specificato
				query="SELECT "+DBNames.ATTR_LISTAVALUTAZIONE_EVENTOVALUTAZIONE_ID+","
						+DBNames.ATTR_LISTAVALUTAZIONE_SUGGERIMENTO+","
						+DBNames.ATTR_LISTAVALUTAZIONE_BLOCCATO
						+" FROM "+DBNames.TABLE_LISTAVALUTAZIONE
						+" WHERE "+DBNames.ATTR_LISTAVALUTAZIONE_UTENTE_EMAIL+"="+"'"+emailUtente+"'";
				
				st=conn.createStatement();
				ris=st.executeQuery(query);
				while(ris.next())
				{
					int idEvento=ris.getInt(DBNames.ATTR_LISTAVALUTAZIONE_EVENTOVALUTAZIONE_ID);
					String suggestion=ris.getString(DBNames.ATTR_LISTAVALUTAZIONE_SUGGERIMENTO);
					String bloccato=ris.getString(DBNames.ATTR_LISTAVALUTAZIONE_BLOCCATO);
					boolean lock;
					if (bloccato.equals("si"))
						lock=true;
					else
						lock=false;
						
					ListaProdottiValutazione lista=new ListaProdottiValutazione(new ArrayList<ProdottoValutazione>(),emailUtente,idEvento,suggestion);
					if(!lock)
						lista.setBloccato(lock);
					listeProdottiValutazione.add(lista);
				}
				
				//ora inserisco i prodotti sottomessi a valutazione all'interno di ogni lista
				for(int i=0;i<listeProdottiValutazione.size();i++)
				{
					int idEvento=listeProdottiValutazione.get(i).getIdEventoValutazione();
					
					query="SELECT "+DBNames.ATTR_PRODOTTOLISTA_PRODOTTO_ISBN+","
							+DBNames.ATTR_PRODOTTO_TITOLO+","+DBNames.ATTR_PRODOTTOLISTA_PRIORITA
							+" FROM "+DBNames.TABLE_PRODOTTO+","+DBNames.TABLE_PRODOTTOLISTA
							+" WHERE "+DBNames.ATTR_PRODOTTOLISTA_PRODOTTO_ISBN+"="+DBNames.ATTR_PRODOTTO_ISBN
							+" AND "+DBNames.ATTR_PRODOTTOLISTA_UTENTE_EMAIL+"="+"'"+emailUtente+"'"
							+" AND "+DBNames.ATTR_PRODOTTOLISTA_EVENTOVALUTAZIONE_ID+"="+idEvento;
					
					st=conn.createStatement();
					ris=st.executeQuery(query);
					while(ris.next())
					{
						String isbn=ris.getString(DBNames.ATTR_PRODOTTOLISTA_PRODOTTO_ISBN);
						String titolo=ris.getString(DBNames.ATTR_PRODOTTO_TITOLO);
						int priorita=ris.getInt(DBNames.ATTR_PRODOTTOLISTA_PRIORITA);
						
						listeProdottiValutazione.get(i).getListaProdottiValutazione().add(new ProdottoValutazione(isbn,titolo,priorita));
					}	
				}
			}
			return listeProdottiValutazione;
		}
		finally
		{
			st.close();
			DBConnectionPool.releaseConnection(conn);
		}
	}
	
	
	/**
	 *Metodo che controlla se il prodotto sottomesso a valutazione e' in conflitto
	 *con qualche altro prodotto, di un alto utente, sottomesso in precedenza
	 * @param p prodotto sottomesso a valutazione
	 * @param conn connessiona al database
	 * @return true se il prodotto e' in conflitto, false altrimenti
	 * @throws SQLException
	 */
	private boolean isInConflitto(ProdottoValutazione p, int idEvento,Connection conn) throws SQLException
	{
		Statement st1=null;
		ResultSet ris1=null;
		String query1;
		//verifico se ci sono due prodotti con lo stesso isbn
		query1="SELECT count(*) as numero"
				+" FROM "+DBNames.TABLE_PRODOTTOLISTA
				+" WHERE "+DBNames.ATTR_PRODOTTOLISTA_PRODOTTO_ISBN+"="+"'"+p.getIsbn()+"'"
				+" AND "+DBNames.ATTR_PRODOTTOLISTA_EVENTOVALUTAZIONE_ID+"="+idEvento;
		
		st1=conn.createStatement();
		ris1=st1.executeQuery(query1);
		ris1.next();
		int numProdottiValutazione=ris1.getInt("numero");
		if(numProdottiValutazione<1)
		{
			st1.close();
			return false;
		}
		else
		{
			if(numProdottiValutazione==1)
			{
				//ottengo l'email dell'utente che ha precedentemente sottomesso
				//il prodotto a valutazione (utente che ha sottomesso per primo)
				query1="SELECT "+DBNames.ATTR_PRODOTTOLISTA_UTENTE_EMAIL
						+" FROM "+DBNames.TABLE_PRODOTTOLISTA
						+" WHERE "+DBNames.ATTR_PRODOTTOLISTA_PRODOTTO_ISBN+"="+"'"+p.getIsbn()+"'"
						+" AND "+DBNames.ATTR_PRODOTTOLISTA_EVENTOVALUTAZIONE_ID+"="+idEvento;
				
				st1=conn.createStatement();
				ris1=st1.executeQuery(query1);
				ris1.next();
				String emailUtente=ris1.getString(DBNames.ATTR_PRODOTTOLISTA_UTENTE_EMAIL);
				
				PreparedStatement st=null;
				String query;
				//inserisco l'utente precedentemente ottuenuto nella tabella dei conflitti
				//altrimenti si perde il riferimento ad esso, perche' quando ha sottomesso 
				//il prodotto non c'era nessun conflitto.
				query="INSERT INTO "+DBNames.TABLE_PRODOTTOINCONFLITTO+"("
						+DBNames.ATTR_PRODOTTOINCONFLITTO_PRODOTTO_ISBN+","
						+DBNames.ATTR_PRODOTTOINCONFLITTO_UTENTE_EMAIL+","
						+DBNames.ATTR_PRODOTTOINCONFLITTO_EVENTOVALUTAZIONE_ID
						+") values (?,?,?)";
				
				st=conn.prepareStatement(query);
				st.setString(1,p.getIsbn());
				st.setString(2, emailUtente);
				st.setInt(3, idEvento);
				
				st.executeUpdate();
				conn.commit();
				
				st.close();
			}
			
			st1.close();
			return true;
		}
	}
	
	/**
	 * Metodo che restituisce l'identificativo dell'evento a cui l'utente vuole partecipare
	 * @return identificativo dell'evento di valutazione
	 * @throws SQLException
	 */
	public EventoValutazione getEventoValutazione(String emailUtente) throws SQLException,InsertProdottiValutazioneException
	{
		Connection conn=null;
		Statement st1=null;
		ResultSet ris=null;
		String query1;
		
		try
		{
			conn = DBConnectionPool.getConnection();
			
			//Recupero l'id dell'evento di valutazione a cui l'utente partecipa
            //supponiamo che la query restitisca un unico risultato......
            query1="SELECT "+DBNames.ATTR_EVENTOVALUTAZIONE_ID+","
            		+DBNames.ATTR_EVENTOVALUTAZIONE_DADATA+","
            		+DBNames.ATTR_EVENTOVALUTAZIONE_ADATA+","
            		+DBNames.ATTR_EVENTOVALUTAZIONE_NUMERODIPUBBLICAZIONI
            		+" FROM "+DBNames.TABLE_EVENTOVALUTAZIONE
            		+" WHERE NOW()<="+DBNames.ATTR_EVENTOVALUTAZIONE_SCADENZA;
        
            
            
            st1=conn.createStatement();
			ris=st1.executeQuery(query1);
			
			EventoValutazione evento=new EventoValutazione();
			while(ris.next())//
			{
				int idEvento=ris.getInt(DBNames.ATTR_EVENTOVALUTAZIONE_ID);
				GregorianCalendar daData=CommonMethod.stringToDate(ris.getString(DBNames.ATTR_EVENTOVALUTAZIONE_DADATA));
				GregorianCalendar aData=CommonMethod.stringToDate(ris.getString(DBNames.ATTR_EVENTOVALUTAZIONE_ADATA));
				int numeroPubblicazioni=ris.getInt(DBNames.ATTR_EVENTOVALUTAZIONE_NUMERODIPUBBLICAZIONI);
				
				evento.setID(idEvento);
				evento.setDataInizio(daData);
				evento.setDataFine(aData);
				evento.setNumeroPubblicazioni(numeroPubblicazioni);
			}
				
			//controllo se l'utente ha gia' partecipato a quell'evento
			query1="SELECT COUNT(*) as numPartecipazioni"
					+" FROM "+DBNames.TABLE_PARTECIPAZIONEAVALUTAZIONE
					+" WHERE "+DBNames.ATTR_PARTECIPAZIONEAVALUTAZIONE_EVENTOVALUTAZIONE_ID+"="+evento.getID()
					+" AND "+DBNames.ATTR_PARTECIPAZIONEAVALUTAZIONE_UTENTE_EMAIL+"="+"'"+emailUtente+"'";
			
			st1=conn.createStatement();
			ris=st1.executeQuery(query1);
			
			int numPartecipazioni;
			ris.next();
			numPartecipazioni=ris.getInt("numPartecipazioni");
			
			if(numPartecipazioni==1)
				throw new InsertProdottiValutazioneException("Hai gia' partecipato all'evento di valutazione !");
			
		return evento;
		}
		finally
		{
			st1.close();
            DBConnectionPool.releaseConnection(conn);
		}
		
	}
	
	/**
	 * Metodo che restituisce i prodotti che hanno la data di pubblicazione
	 * compresa tra il range di date di un determinato evento
	 * @param evento evento a cui l'utente partecipa
	 * @param prodotti dell'utente specificato 
	 * @return prodottiFiltrati idonei per essere sottomessi a quel determinato evento di valutazione
	 */
	public ArrayList<Prodotto> prodottiFiltrati(EventoValutazione evento, ArrayList<Prodotto> prodotti)
	{
		ArrayList<Prodotto> prodottiFiltrati=new ArrayList<Prodotto>();
		GregorianCalendar dataInizio=evento.getDataInizio();
		GregorianCalendar dataFine=evento.getDataFine();
		
		
		for(int i=0;i<prodotti.size();i++)
		{
			GregorianCalendar dataPubb=prodotti.get(i).getAnnoPubblicazione();
			//se la data di pubblicazione del prodotto e' compresa tra il range di date richieste dall'evento
			if((dataPubb.after(dataInizio)||dataPubb.equals(dataInizio)) && (dataPubb.before(dataFine)||dataPubb.equals(dataFine)))
				prodottiFiltrati.add(prodotti.get(i));
		}
		
		return prodottiFiltrati;
	}
	
	public EventoValutazione ottieniEventoValutazione(int idEvento) throws SQLException
	{
		Connection conn=null;
		Statement st=null;
		ResultSet ris=null;
		String query;
		
		try
		{
			conn = DBConnectionPool.getConnection();
			
			query=" SELECT *"
					+" FROM "+DBNames.TABLE_EVENTOVALUTAZIONE
					+" WHERE "+DBNames.ATTR_EVENTOVALUTAZIONE_ID+"="+idEvento;
			
			st=conn.createStatement();
			ris=st.executeQuery(query);
			
			EventoValutazione evento=null;
			while(ris.next())
			{
				String nome=ris.getString(DBNames.ATTR_EVENTOVALUTAZIONE_NOME);
				int numPubblicazioni=ris.getInt(DBNames.ATTR_EVENTOVALUTAZIONE_NUMERODIPUBBLICAZIONI);
				GregorianCalendar daData=CommonMethod.stringToDate(ris.getString(DBNames.ATTR_EVENTOVALUTAZIONE_DADATA));
				GregorianCalendar aData=CommonMethod.stringToDate(ris.getString(DBNames.ATTR_EVENTOVALUTAZIONE_ADATA));
				GregorianCalendar scadenza=CommonMethod.stringToDate(ris.getString(DBNames.ATTR_EVENTOVALUTAZIONE_SCADENZA));
				evento=new EventoValutazione(nome,numPubblicazioni,scadenza,daData,aData);
				evento.setID(idEvento);
			}
		return evento;
		}
		finally
		{
			st.close();
			DBConnectionPool.releaseConnection(conn);
		}
	}
	
	/**
	 * Metodo che sblocca la lista di prodotti sottoposti a valutazione dall'utente
	 * (a causa di qualche conflitto), e permette all'utente di poter modificare la lista
	 * risolvendo l'eventuale conflitto
	 * @param listaProdottiValutazione sottomessa dall'utente
	 * @throws SQLException
	 */
	public void convalidaORifiutaListaProdottiValutazione(ListaProdottiValutazione listaProdottiValutazione, String bloccato) throws SQLException
	{
		Connection conn=null;
		PreparedStatement st=null;
		String query;
		
		try
		{
			conn = DBConnectionPool.getConnection();
			
			query="UPDATE "+DBNames.TABLE_LISTAVALUTAZIONE
					+" SET "+DBNames.ATTR_LISTAVALUTAZIONE_BLOCCATO+"=?"
					+ " WHERE "+DBNames.ATTR_LISTAVALUTAZIONE_UTENTE_EMAIL+"="+"'"+listaProdottiValutazione.getEmailUtente()+"'"
					+" and "+DBNames.ATTR_LISTAVALUTAZIONE_EVENTOVALUTAZIONE_ID+"="+listaProdottiValutazione.getIdEventoValutazione();
			
			st=conn.prepareStatement(query);
			st.setString(1, bloccato);
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
	 * Metodo che permette di settare un suggerimento che per default e' a null
	 * settare: inteso come inserimento se il suggerimento e' a null
	 * e come modifica se il suggerimento gia'esiste
	 * @param listaProdottiValutazione sottomessi dall'utente
	 * @param suggestion per la risoluzione dei conflitti
	 * @throws SQLException
	 */
	public void settaSuggerimentoListaValutazione(ListaProdottiValutazione listaProdottiValutazione,String suggestion)throws SQLException
	{
		Connection conn=null;
		PreparedStatement st=null;
		String query;
		
		try
		{
			conn = DBConnectionPool.getConnection();
			
			query="UPDATE "+DBNames.TABLE_LISTAVALUTAZIONE
					+" SET "+DBNames.ATTR_LISTAVALUTAZIONE_SUGGERIMENTO+"=?"
					+ " WHERE "+DBNames.ATTR_LISTAVALUTAZIONE_UTENTE_EMAIL+"="+"'"+listaProdottiValutazione.getEmailUtente()+"'"
					+" and "+DBNames.ATTR_LISTAVALUTAZIONE_EVENTOVALUTAZIONE_ID+"="+listaProdottiValutazione.getIdEventoValutazione();
			
			st=conn.prepareStatement(query);
			st.setString(1, suggestion);
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
	 * Metodo che restituisce una lista di prodotti in conflitto dell'utente e dell'evento specificati
	 * @param emailUtente identificativo dell'utente
	 * @param idEvento identificativo dell'evento
	 * @return prodottiValutazioneConflitto lista prodotti sottomessi a valutazione in conflitto
	 * @throws SQLException
	 */
	public ArrayList<ProdottoValutazione> getProdottiValutazioneInConflitto(String emailUtente, int idEvento) throws SQLException
	{
		Connection conn=null;
		Statement st=null;
		ResultSet ris=null;
		String query;
		ArrayList<ProdottoValutazione> prodottiValutazioneConflitto=new ArrayList<ProdottoValutazione>();
		
		try
		{
			conn=DBConnectionPool.getConnection();
			
			//ottengo gli isbn dei prodotti sottomessi a valutazione in conflitto
			query="SELECT "+DBNames.ATTR_PRODOTTOINCONFLITTO_PRODOTTO_ISBN
					+" FROM "+DBNames.TABLE_PRODOTTOINCONFLITTO
					+" WHERE "+DBNames.ATTR_PRODOTTOINCONFLITTO_UTENTE_EMAIL+"="+"'"+emailUtente+"'"
					+" AND "+DBNames.ATTR_PRODOTTOINCONFLITTO_EVENTOVALUTAZIONE_ID+"="+idEvento;
			
			st=conn.createStatement();
			ris=st.executeQuery(query);
			
			
			while (ris.next())
			{
				ProdottoValutazione prodottoValutazione=new ProdottoValutazione();
				String isbn=ris.getString(DBNames.ATTR_PRODOTTOINCONFLITTO_PRODOTTO_ISBN);
				prodottoValutazione.setIsbn(isbn);
				prodottiValutazioneConflitto.add(prodottoValutazione);
			}
		}
		finally
		{
			st.close();
			DBConnectionPool.releaseConnection(conn);	
		}
		
		return prodottiValutazioneConflitto;
	}
	
}

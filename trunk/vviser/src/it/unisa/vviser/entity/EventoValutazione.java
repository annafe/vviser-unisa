package it.unisa.vviser.entity;

import it.vviser.common.CommonMethod;

import java.util.Calendar;
import java.util.GregorianCalendar;



/**
 * 
 * @author Maria Vittoria Coda
 *
 */
public class EventoValutazione {
	
	private int ID;
	private String nomeEvento=null;
	private int numeroPubblicazioni=0;
	private GregorianCalendar scadenza=null;
	private GregorianCalendar dataInizio=null;
	private GregorianCalendar dataFine=null;

	public EventoValutazione(){	}
	
	public EventoValutazione(String nomeEvento, int numeroPubblicazioni, GregorianCalendar scadenza, GregorianCalendar dataInizio, GregorianCalendar dataFine){
		this.nomeEvento = nomeEvento;
		this.numeroPubblicazioni = numeroPubblicazioni;
		this.scadenza = scadenza;
		this.dataInizio=dataInizio;
		this.dataFine=dataFine;
	}
	
	public EventoValutazione(String nomeEvento, String numeroPubblicazioni, String scadenza, String dataInizio, String dataFine){
		this.nomeEvento = nomeEvento;
		this.numeroPubblicazioni = Integer.parseInt(numeroPubblicazioni);
		this.scadenza = CommonMethod.stringToDate(scadenza);
		this.dataInizio=CommonMethod.stringToDate(dataInizio);
		this.dataFine=CommonMethod.stringToDate(dataFine);
	}
	
	public EventoValutazione(int id,String nomeEvento, int numeroPubblicazioni, GregorianCalendar scadenza, GregorianCalendar dataInizio, GregorianCalendar dataFine){
		this.ID = id;
		this.nomeEvento = nomeEvento;
		this.numeroPubblicazioni = numeroPubblicazioni;
		this.scadenza = scadenza;
		this.dataInizio=dataInizio;
		this.dataFine=dataFine;
	}
	
	/**
	 * restituisce il nome dell'evento
	 * @return nome dell'evento
	 */
	public String getNomeEvento(){
		return this.nomeEvento;
	}
	/**
	 * restituisce il numero delle pubblicazioni minime per il relativo evento in formato stringa
	 * @return il minimo numero di pubblicazioni
	 */
	public int getNumeroPubblicazioni(){
		return this.numeroPubblicazioni;
	}
	/**
	 * restituisce la durata dell'evento
	 * @return la durata dell'evento
	 */
	public String getScadenza(){
		return CommonMethod.dateToString(scadenza);
	}
	
	/**
	 * modifica il nome dell'evento con il nome contenuto in name
	 * @param name nuovo nome dell'evento
	 */
	public void setNomeEvento(String name){
		this.nomeEvento = name;
	}
	/**
	 * modifica il numero di pubblicazioni minime relative all'evento
	 * @param num il nuovo numero di pubblicazioni minime
	 */
	public void setNumeroPubblicazioni(int num){
		this.numeroPubblicazioni = num;
	}
	/**
	 * modifica la durata dell'evento
	 * @param GregorianCalendar la nuova durata dell'evento 
	 */
	public void setScadenza(GregorianCalendar GregorianCalendar){
		this.scadenza = GregorianCalendar;
	}
	
	/**
	 * restituisce la data di inizio del periodo al quale appartengono i prodotti che possono essere sottomessi a valutazione
	 * @return la data di inizio del periodo di validita' di un prodotto
	 */
	public GregorianCalendar getDataInizio() {
		return dataInizio;
	}
	
//	public String getDataInizio() {
//		return dataInizio.get(Calendar.YEAR)+"/"+scadenza.get(Calendar.MONTH)+"/"+scadenza.get(Calendar.DAY_OF_MONTH);
//	}

	/**
	 * modifica la data di inizio del periodo di validita' dei prodotti che possono essere sottomessi a valutazione 
	 * @param dataInizio la nuova data di inizio
	 */
	public void setDataInizio(GregorianCalendar dataInizio) {
		this.dataInizio = dataInizio;
	}
	
	/**
	 * restituisce la data di inizio del periodo al quale appartengono i prodotti che possono essere sottomessi a valutazione
	 * @return la data di inizio del periodo di validita' di un prodotto
	 */
	public GregorianCalendar getDataFine() {
		return dataFine;
	}

	/**
	 * modifica la data di inizio del periodo di validita' dei prodotti che possono essere sottomessi a valutazione 
	 * @param dataInizio la nuova data di inizio
	 */
	public void setDataFine(GregorianCalendar dataFine) {
		this.dataFine = dataFine;
	}
	
	/**
	 * restituisce l'ID univoco dell'evento di valutazione 
	 * @return ID univoco associato all'evento di valutazione
	 */
	public int getID(){
		return this.ID;
	}
	
	/**
	 * modifica il valore dell'ID univoco dell'evento di valutazione
	 * @param id il nuovo valore dell'id associato all'evento di valutazione
	 */
	public void setID(int id){
		ID = id;
	}

}

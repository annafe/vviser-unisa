package it.unisa.vviser.entity;

import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * 
 * @author Eugenio Gigante, Michele Roviello
 *
 */
public class Utente {
	
	private int id;	
	private String nome;
	private String cognome;
	private GregorianCalendar dataDiNascita;
	private String comuneDiNascita;
	private String provinciaDiNascita;
	private String codiceFiscale;
	private String password;
	private String email;
	private String dipartimento;
	private String tipologia;

	/**
	 * Il costruttore vuoto della classe Utente
	 */
	public Utente(){ }
	
	public Utente(String nome, String cognome, 
			GregorianCalendar dataDiNascita, String comuneDiNascita, 
			String provinciaDiNascita, String codiceFiscale, 
			String password, String email, String dipartimento, String tipologia){
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.comuneDiNascita = comuneDiNascita;
		this.provinciaDiNascita = provinciaDiNascita;
		this.codiceFiscale = codiceFiscale;
		this.password = password;
		this.email = email;
		this.dipartimento = dipartimento;
		this.tipologia = tipologia;
	}
	
	/**
	 * Imposta il nome associato all'Utente col valore specificato
	 * sostituendo il valore precedente
	 * @param nome Nome da memorizzare
	 */
	public void setNome (String nome){
		this.nome=nome;
	}
	
	/**
	 * Restituisce il nome associato all'Utente
	 * @return Nome associato all'Utente
	 */
	public String getNome(){
		return this.nome;
	}
	
	/**
	 * Imposta il cognome associato all'Utente col valore specificato
	 * sostituendo il valore precedente
	 * @param cognome Cognome da memorizzare
	 */
	public void setCognome (String cognome){
		this.cognome=cognome;
	}
	
	/**
	 * Restituisce il cognome associato all'Utente
	 * @return Cognome associato all'Utente
	 */
	public String getCognome(){
		return this.cognome;
	}
	
	/**
	 * Imposta la data di nascita associata all'Utente con la data specificata 
	 * sostituendo il valore precedente
	 * @param date Data di nascita da memorizzare
	 */
	public void setDataDiNascita (GregorianCalendar date){
		this.dataDiNascita=date;
	}
	
	/**
	 * Restituisce la data di nascita associata all'Utente
	 * @return Data di nascita
	 */
	public String getDataDiNascita(){
		return dataDiNascita.get(Calendar.YEAR)+"/"+dataDiNascita.get(Calendar.MONTH)+"/"+dataDiNascita.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * Imposta il comune di nascita associato all'Utente col valore specificato
	 * sostituendo il valore precedente
	 * @param comuneDiNascita Comune di nascita da memorizzare
	 */
	public void setComuneDiNascita (String comuneDiNascita){
		this.comuneDiNascita=comuneDiNascita;
	}
	
	/**
	 * Restituisce il comune di nascita associato all'Utente
	 * @return Comune di nascita
	 */
	public String getComuneDiNascita(){
		return this.comuneDiNascita;
	}
	
	/**
	 * Imposta la provincia di nascita associata all'Utente col valore specificato 
	 * sostituendo il valore precedente
	 * @param provinciaDiNascita Provincia di nascita da memorizzare
	 */
	public void setProvinciaDiNascita (String provinciaDiNascita){
		this.provinciaDiNascita=provinciaDiNascita;
	}
	
	/**
	 * Restituisce la provincia di nascita associata all'Utente
	 * @return Provincia di nascita
	 */
	public String getProvinciaDiNascita(){
		return this.provinciaDiNascita;
	}
	
	/**
	 * Imposta il codice fiscale associato all'Utente col valore specificato
	 * sostituendo il valore precedente
	 * @param codiceFiscale Codice fiscale da memorizzare
	 */
	public void setCodiceFiscale (String codiceFiscale){
		this.codiceFiscale=codiceFiscale;
	}
	
	/**
	 * Restituisce il codice fiscale associato all'Utente
	 * @return Codice fiscale
	 */
	public String getCodiceFiscale(){
		return this.codiceFiscale;
	}
	
	/**
	 * Imposta la password  associata all'Utente col valore specificato
	 * sostituendo il valore precedente
	 * @param password Password da memorizzare
	 */
	public void setPassword (String password){
		this.password=password;
	}
	
	/**
	 * Restituisce la password associata all'Utente
	 * @return Password
	 */
	public String getPassword(){
		return this.password;
	}
	
	/**
	 * Imposta l'indirizzo email asscociato all'Utente col valore specificato
	 * sostituendo il valore precedente
	 * @param email Indirizzo Email da memorizzare
	 */
	public void setEmail (String email){
		this.email=email;
	}
	
	/**
	 * Restituisce l'indirizzo email associato all'Utente
	 * @return Indirizzo email
	 */
	public String getEmail(){
		return this.email;
	}
	
	/**
	 * Imposta il nome del dipartimento associato all'Utente col valore specificato
	 * sostituendo il valore precedente
	 * @param dipartimento_Nome Nome del dipartimento
	 */
	public void setDipartimento(String dipartimento){
		this.dipartimento =dipartimento;
	}
	
	/**
	 * Restituisce il nome del dipartimento associato all'Utente
	 * @return Nome del dipartimento
	 */
	public String getDipartimento(){
		return this.dipartimento;
	}
	
	/**
	 * Imposta la tipologia di appartenenza associata all'Utente 
	 * sostituendo il valore precedente
	 * @param tipologia Tipologia da memorizzare
	 */
	public void setTipologia (String tipologia ){
		this.tipologia  =tipologia  ;
	}
	
	/**
	 * Restituisce la tipologia associata all'Utente
	 * @return Tipolodia di Utente
	 */
	public String getTipologia  (){
		return this.tipologia  ;
	}
	
}
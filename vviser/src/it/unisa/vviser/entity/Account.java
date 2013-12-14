package it.unisa.vviser.entity;

import java.util.Date;
import java.util.GregorianCalendar;


/**
 * 
 * @author Eugenio Gigante, Michele Roviello
 *
 */
public class Account {
	
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
	 * Il costruttore vuoto della classe Account
	 */
	public Account(){ }
	
	public Account(int id, String nome, String cognome, 
			GregorianCalendar dataDiNascita, String comuneDiNascita, 
			String provinciaDiNascita, String codiceFiscale, 
			String password, String email, String dipartimento, String tipologia){
		this.id = id;
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
	 * Imposta il nome associato all'account col valore specificato
	 * sostituendo il valore precedente
	 * @param nome Nome da memorizzare
	 */
	public void setNome (String nome){
		this.nome=nome;
	}
	
	/**
	 * Restituisce il nome associato all'account
	 * @return Nome associato all'account
	 */
	public String getNome(){
		return this.nome;
	}
	
	/**
	 * Imposta il cognome associato all'account col valore specificato
	 * sostituendo il valore precedente
	 * @param cognome Cognome da memorizzare
	 */
	public void setCognome (String cognome){
		this.cognome=cognome;
	}
	
	/**
	 * Restituisce il cognome associato all'account
	 * @return Cognome associato all'account
	 */
	public String getCognome(){
		return this.cognome;
	}
	
	/**
	 * Imposta la data di nascita associata all'account con la data specificata 
	 * sostituendo il valore precedente
	 * @param date Data di nascita da memorizzare
	 */
	public void setDataDiNascita (GregorianCalendar date){
		this.dataDiNascita=date;
	}
	
	/**
	 * Restituisce la data di nascita associata all'account
	 * @return Data di nascita
	 */
	public GregorianCalendar getDataDiNascita(){
		return this.dataDiNascita;
	}
	
	/**
	 * Imposta il comune di nascita associato all'account col valore specificato
	 * sostituendo il valore precedente
	 * @param comuneDiNascita Comune di nascita da memorizzare
	 */
	public void setComuneDiNascita (String comuneDiNascita){
		this.comuneDiNascita=comuneDiNascita;
	}
	
	/**
	 * Restituisce il comune di nascita associato all'account
	 * @return Comune di nascita
	 */
	public String getComuneDiNascita(){
		return this.comuneDiNascita;
	}
	
	/**
	 * Imposta la provincia di nascita associata all'account col valore specificato 
	 * sostituendo il valore precedente
	 * @param provinciaDiNascita Provincia di nascita da memorizzare
	 */
	public void setProvinciaDiNascita (String provinciaDiNascita){
		this.provinciaDiNascita=provinciaDiNascita;
	}
	
	/**
	 * Restituisce la provincia di nascita associata all'account
	 * @return Provincia di nascita
	 */
	public String getProvinciaDiNascita(){
		return this.provinciaDiNascita;
	}
	
	/**
	 * Imposta il codice fiscale associato all'account col valore specificato
	 * sostituendo il valore precedente
	 * @param codiceFiscale Codice fiscale da memorizzare
	 */
	public void setCodiceFiscale (String codiceFiscale){
		this.codiceFiscale=codiceFiscale;
	}
	
	/**
	 * Restituisce il codice fiscale associato all'account
	 * @return Codice fiscale
	 */
	public String getCodiceFiscale(){
		return this.codiceFiscale;
	}
	
	/**
	 * Imposta la password  associata all'account col valore specificato
	 * sostituendo il valore precedente
	 * @param password Password da memorizzare
	 */
	public void setPassword (String password){
		this.password=password;
	}
	
	/**
	 * Restituisce la password associata all'account
	 * @return Password
	 */
	public String getPassword(){
		return this.password;
	}
	
	/**
	 * Imposta l'indirizzo email asscociato all'account col valore specificato
	 * sostituendo il valore precedente
	 * @param email Indirizzo Email da memorizzare
	 */
	public void setEmail (String email){
		this.email=email;
	}
	
	/**
	 * Restituisce l'indirizzo email associato all'account
	 * @return Indirizzo email
	 */
	public String getEmail(){
		return this.email;
	}
	
	/**
	 * Imposta il nome del dipartimento associato all'account col valore specificato
	 * sostituendo il valore precedente
	 * @param dipartimento_Nome Nome del dipartimento
	 */
	public void setDipartimento_Nome (String dipartimento){
		this.dipartimento =dipartimento;
	}
	
	/**
	 * Restituisce il nome del dipartimento associato all'account
	 * @return Nome del dipartimento
	 */
	public String getDipartimento_Nome (){
		return this.dipartimento;
	}
	
	/**
	 * Imposta la tipologia di appartenenza associata all'account 
	 * sostituendo il valore precedente
	 * @param tipologia Tipologia da memorizzare
	 */
	public void setTipologia (String tipologia ){
		this.tipologia  =tipologia  ;
	}
	
	/**
	 * Restituisce la tipologia associata all'account
	 * @return Tipolodia di account
	 */
	public String getTipologia  (){
		return this.tipologia  ;
	}
	
	/**
	 * Imposta il codice ID associato all'account 
	 * sostituendo il valore precedente
	 * @param id Codice ID da memorizzare
	 */
	public void setID (int id ){
		this.id = id  ;
	}
	
	/**
	 * Restituisce il codice ID associato all'account
	 * @return Codice ID
	 */
	public int getID  (){
		return this.id  ;
	}
}

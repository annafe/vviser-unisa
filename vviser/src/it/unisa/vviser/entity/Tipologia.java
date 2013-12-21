
package it.unisa.vviser.entity;

import java.util.GregorianCalendar;

/**
 * @author Anna
 *
 */

public class Tipologia {



	private String nome;
	private String descrizione;
	private boolean validita;
	private GregorianCalendar datainizio; 
	private GregorianCalendar datafine;


	/**
	 * Costruttore vuoto della classe Tipologia
	 */
	public Tipologia() {

	}
	
	/**
	 * Costruttore parametrico 
	 * @param nome
	 * @param descrizione
	 * @param validita
	 * @param datainizio
	 * @param datafine
	 */
	public Tipologia(String nome, String descrizione, boolean validita,
			GregorianCalendar datainizio, GregorianCalendar datafine) {
		super();
		this.nome = nome;
		this.descrizione = descrizione;
		this.validita = validita;
		this.datainizio = datainizio;
		this.datafine = datafine;
	}
	/**
	 * @return
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public boolean isValidita() {
		return validita;
	}

	public void setValidita(boolean validita) {
		this.validita = validita;
	}

	public GregorianCalendar getDatainizio() {
		return datainizio;
	}

	public void setDatainizio(GregorianCalendar da) {
		this.datainizio = da;
	}

	public GregorianCalendar getDatafine() {
		return datafine;
	}

	public void setDatafine(GregorianCalendar a) {
		this.datafine = a;
	}



}

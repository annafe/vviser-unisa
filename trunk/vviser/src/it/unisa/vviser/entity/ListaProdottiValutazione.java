package it.unisa.vviser.entity;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author Giuseppe Sabato
 *
 */
public class ListaProdottiValutazione {
	
	private ArrayList<ProdottoValutazione> listaProdottiValutazione;
	private String emailUtente;
	private int idEventoValutazione;
	private String suggestion;
	private boolean bloccato;
	
	
	/**
	 * Il costruttore della classe ListaProdottiValutazione senza parametri
	 */
	public ListaProdottiValutazione()
	{
		listaProdottiValutazione=new ArrayList<ProdottoValutazione>();
	}
	
	/**
	 * Il costruttore della classe ListaProdottiValutazione con parametri
	 * @param listaProdottiValutazione prodotti sottomessi a valutazione
	 * @param emailUtente identificativo utente proprietario della lista
	 * @param suggestion per la risoluzione dei conflitti
	 * @param bloccato indica se la lista e'modificabile o meno
	 */
	public ListaProdottiValutazione(ArrayList<ProdottoValutazione> listaProdottiValutazione, String emailUtente, int idEventoValutazione, String suggestion)
	{
		this.listaProdottiValutazione=listaProdottiValutazione;
		this.emailUtente=emailUtente;
		this.idEventoValutazione=idEventoValutazione;
		this.suggestion=suggestion;
		this.bloccato=true;
	}
	
	/**
	 * Metodo che restituisce una lista di prodotti sottomessi a valutazione
	 * @return listaProdottiValutazione il parametro inserito
	 */
	public ArrayList<ProdottoValutazione> getListaProdottiValutazione() 
	{
		return this.listaProdottiValutazione;
	}
	
	/**
	 * Metodo che setta una lista di prodotti sottomessi a valutazione
	 * @param listaProdottiValutazione il parametro fissato
	 */
	public void setListaProdottiValutazione(ArrayList<ProdottoValutazione> listaProdottiValutazione) 
	{
		this.listaProdottiValutazione = listaProdottiValutazione;
	}
	
	/**
	 * Metodo che aggiunge un prodotto alla lista di sottomissioni a valutazione
	 * @param p prodotto sottomesso a valutazione
	 */
	public void addProdottoValutazione(ProdottoValutazione p)
	{
		this.listaProdottiValutazione.add(p);
	}
	
	/**
	 * Metodo che restituisce il parametro emailUtente
	 * @return emailUtente il parametro inserito
	 */
	public String getEmailUtente() 
	{
		return this.emailUtente;
	}
	
	/**
	 * Metodo che setta il parametro emailUtente
	 * @param emailUtente il parametro fissato
	 */
	public void setEmailUtente(String emailUtente) 
	{
		this.emailUtente = emailUtente;
	}
	
	/**
	 * Metodo che restituisce il parametro idEventoValutazione
	 * @return idEventoValutazione il parametro inserito
	 */
	public int getIdEventoValutazione() 
	{
		return this.idEventoValutazione;
	}
	
	/**
	 * Metodo che setta il parametro idEventoValutazione
	 * @param idEventoValutazione il parametro fissato
	 */
	public void setIdEventoValutazione(int idEventoValutazione) 
	{
		this.idEventoValutazione = idEventoValutazione;
	}
	
	/**
	 * Metodo che restitusce il parametro suggestion
	 * @return suggestion il parametro inserito
	 */
	public String getSuggestion() 
	{
		return this.suggestion;
	}
	
	/**
	 * Metodo che setta il parametro suggestion
	 * @param suggestion il parametro fissato
	 */
	public void setSuggestion(String suggestion) 
	{
		this.suggestion = suggestion;
	}
	
	/**
	 * Metodo che restituisce il parametro bloccato
	 * @return bloccato il parametro inserito
	 */
	public boolean getBloccato() 
	{
		return this.bloccato;
	}
	
	/**
	 * Metodo che setta il parametro bloccato
	 * @param bloccato il parametro fissato
	 */
	public void setBloccato(boolean bloccato) 
	{
		this.bloccato = bloccato;
	}
	
}

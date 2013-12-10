package it.unisa.vviser.entity;

import java.util.ArrayList;

/**
 * 
 * @author Giuseppe Sabato
 *
 */
public class ListaProdottiValutazione {
	
	private int idLista;
	private ArrayList<ProdottoValutazione> listaProdottiValutazione;
	private int idUtente;
	private int idEventoValutazione;
	private String suggestion;
	
	
	/**
	 * Il costruttore della classe ListaProdottiValutazione senza parametri
	 */
	public ListaProdottiValutazione()
	{
	
	}
	
	/**
	 * Il costruttore della classe ListaProdottiValutazione con parametri
	 * @param listaProdottiValutazione prodotti sottomessi a valutazione
	 * @param idUtente identificativo utente proprietario della lista
	 * @param suggestion per la risoluzione dei conflitti
	 */
	public ListaProdottiValutazione(ArrayList<ProdottoValutazione> listaProdottiValutazione, int idUtente, int idEventoValutazione, String suggestion)
	{
		this.listaProdottiValutazione=listaProdottiValutazione;
		this.idUtente=idUtente;
		this.idEventoValutazione=idEventoValutazione;
		this.suggestion=suggestion;
	}
	
	/**
	 * Metodo che restituisce il parametro idLista
	 * @return idLista il parametro inserito
	 */
	public int getIdLista() 
	{
		return this.idLista;
	}
	
	/**
	 * Metodo che setta il parametro idLista
	 * @param idLista il parametro fissato
	 */
	public void setIdLista(int idLista) 
	{
		this.idLista = idLista;
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
	 * Metodo che restituisce il parametro idUtente
	 * @return idUtente il parametro inserito
	 */
	public int getIdUtente() 
	{
		return this.idUtente;
	}
	
	/**
	 * Metodo che setta il parametro idUtente
	 * @param idUtente il parametro fissato
	 */
	public void setIdUtente(int idUtente) 
	{
		this.idUtente = idUtente;
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

}

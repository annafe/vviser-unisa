package it.unisa.vviser.entity;

import java.util.ArrayList;

/**
 * 
 * @author Giuseppe Sabato
 *
 */
public class ListaProdottiValutazione {
	
	private ArrayList<ProdottoValutazione> listaProdottiValutazione;
	private String suggestion;
	
	
	/**
	 * Il costruttore della classe ListaProdottiValutazione senza parametri
	 */
	public ListaProdottiValutazione()
	{
		this.listaProdottiValutazione=new ArrayList<ProdottoValutazione>();
		this.suggestion="";
	}
	
	/**
	 * Il costruttore della classe ListaProdottiValutazione con parametri
	 * @param listaProdottiValutazione prodotti sottomessi a valutazione
	 * @param suggestion per la risoluzione dei conflitti
	 */
	public ListaProdottiValutazione(ArrayList<ProdottoValutazione> listaProdottiValutazione, String suggestion)
	{
		this.listaProdottiValutazione=listaProdottiValutazione;
		this.suggestion=suggestion;
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

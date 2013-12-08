package it.unisa.vviser.entity;

/**
 * 
 * @author Giuseppe Sabato
 *
 */
public class ProdottoValutazione {
	
	private String isbn;
	private String title;
	private int priority;
	
	
	/**
	 * Il costruttore della classe ProdottoValutazione vuoto
	 */
	public ProdottoValutazione()
	{
		
	}
	
	/**
	 * Il costruttore della classe ProdottoValutazione con parametri
	 * @param isbn codice identificativo prodotto
	 * @param priority del prodotto sottomesso a valutazione
	 */
	public ProdottoValutazione(String isbn, String title, int priority)
	{
		this.isbn=isbn;
		this.title=title;
		this.priority=priority;
	}
	
	/**
	 * Metodo che restitiusce il campo isbn
	 * @return isbn il parametro inserito
	 */
	public String getIsbn() 
	{
		return this.isbn;
	}
	
	/**
	 * Metodo che setta il campo isbn
	 * @param isbn il parametro fissato
	 */
	public void setIsbn(String isbn) 
	{
		this.isbn = isbn;
	}
	
	/**
	 * Metodo che restituisce il campo title
	 * @return title il parametro inserito
	 */
	public String getTitle() 
	{
		return this.title;
	}
	
	/**
	 * Metodo che setta il campo title
	 * @param title il parametro fissato
	 */
	public void setTitle(String title) 
	{
		this.title = title;
	}
	
	/**
	 * Metodo che restitiusce il campo priority
	 * @return priority il parametro inserito
	 */
	public int getPriority()
	{
		return this.priority;
	}
	
	/**
	 * Metodo che setta il campo priority
	 * @param priority il parametro fissato
	 */
	public void setPriority(int priority)
	{
		this.priority=priority;
	}
	

}

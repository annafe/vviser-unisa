package it.unisa.vviser.entity;

/**
 * 
 * @author Salvatore Angiuoli
 * @author Antonio De Piano
 *
 */
public class Prodotto
{
	private String anno;
	private String formato;
	private String codiceDOI;
	private String diffusione;
	private String note; 
	private String categoria;
	private String isbn;
	private String title;
	
	/**
	 * Il costruttore della classe Prodotto vuoto
	 */
	public Prodotto()
	{
		
	}
	
	/**
	 * Il costruttore della classe Prodotto con parametri
	 * @param isbn codice identificativo prodotto
	 * @param anno anno della pubblicazione
	 * @param formato formato della pubblicazione
	 * @param codiceDOI codiceDOI del prodotto
	 * @param diffusione numero di copie del prodotto
	 * @param note note relative al prodotto
	 * @param categoria categoria a cui appartine il prodotto
	 * @title titolo del prodotto
	 */
	public Prodotto(String anno,String formato,String codiceDOI,String diffusione,String note,String categoria,String isbn,String title)
	{
		this.anno=anno;
		this.formato=formato;
		this.categoria=categoria;
		this.codiceDOI=codiceDOI;
		this.isbn=isbn;
		this.note=note;
		this.title=title;
		this.diffusione=diffusione;
	}
	
	/**
	 * Metodo che restituisce ISBN del prodotto
	 * @return isbn del prodotto
	 */
	public String getIsbn()
	{
		return isbn;
	}
	
	/**
	 * Metodo che setta l'ISBN al prodotto
	 * @param isbn da attribuire al prodotto
	 */
	public void setIsbn(String isbn)
	{
		this.isbn = isbn;
	}
	
	/**
	 * Metodo che restituisce il titolo del prodotto
	 * @return titolo del prodotto
	 */
	public String getTitle()
	{
		return title;
	}
	
	/**
	 * Metodo che setta il titolo al prodotto
	 * @param title da attribuire al prodotto
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * Metodo che restituisce il codice DOI del prodotto
	 * @return codice DOI del prodotto
	 */
	public String getCodiceDOI()
	{
		return this.codiceDOI;
	}
	
	/**
	 * Metodod che setta il codice DOI al prodotto 
	 * @param codiceDOI da attribuire al prodotto
	 */
	public void setCodiceDOI(String codiceDOI)
	{
		this.codiceDOI = codiceDOI;
	}
	
	/**
	 * Metodo che restituisce la 
	 * @return
	 */
	public String getDiffusione()
	{
		return this.diffusione;
	}
	
	/**
	 * 
	 * @param diffusione
	 */
	public void setDiffusione(String diffusione) {
		this.diffusione = diffusione;
	}
	
	/**
	 * Metodo che restituisce le note legate al prodotto
	 * @return note relative al prodotto
	 */
	public String getNote()
	{
		return this.note;
	}
	
	/**
	 * Metodo che setta le note per un prodotto
	 * @param note da attribuire a un prodotto
	 */
	public void setNote(String note)
	{
		this.note = note;
	}
	
	/**
	 * Metodo che restituisce la categoria a cui appartine un prodotto
	 * @return categoria relativa al prodotto
	 */
	public String getCategoria()
	{
		return this.categoria;
	}
	
	/**
	 * Metodo che setta la categoria a un prodtto
	 * @param categoria da attribuire al prodotto
	 */
	public void setCategoria(String categoria)
	{
		this.categoria= categoria;
	}
	
	/**
	 * Metodo che restituisce l'anno della pubblicazione del prodotto
	 * @return anno della pubblicazione
	 */
	public String getAnno()
	{
		return this.anno;
	}
	
	/**
	 * Metodo che setta l'anno della pubblicazione del prodotto
	 * @param anno della pubblicazione da attribuire al prodotto
	 */
	public void setAnno(String anno)
	{
		this.anno = anno;
	}
	
	/**
	 * Metodo che restituisce il formato della pubblicazione del prodotto
	 * @return formato della pubblicazione
	 */
	public String getFormato()
	{
		return this.formato;
	}
	
	/**
	 * Metodo che setta il formato della pubblicazione del prodotto
	 * @param formato della pubblicazione relativa al prodotto
	 */
	public void setFormato(String formato)
	{
		this.formato = formato;
	}
}

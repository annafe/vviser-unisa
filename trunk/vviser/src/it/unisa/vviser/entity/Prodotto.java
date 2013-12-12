package it.unisa.vviser.entity;

/**
 * 
 * @author Salvatore Angiuoli
 * @author Antonio De Piano
 *
 */
public class Prodotto
{
	private String annoPubblicazione;
	private String formatoPubblicazione;
	private String codiceDOI;
	private String diffusione;
	private String note; 
	private String categoria;
	private String isbn;
	private String titolo;
	private String proprietario;
	private boolean bozza;
	private String stato;
	
	/**
	 * Il costruttore della classe Prodotto vuoto
	 */
	public Prodotto()
	{
		
	}
	
	/**
	 * Il costruttore della classe Prodotto con parametri
	 * @param isbn codice identificativo prodotto
	 * @param annoPubblicazione anno della pubblicazione
	 * @param formatoPubblicazione formato della pubblicazione
	 * @param codiceDOI codiceDOI del prodotto
	 * @param diffusione numero di copie del prodotto
	 * @param note note relative al prodotto
	 * @param categoria categoria a cui appartine il prodotto
	 * @param bozza prodotto bozza(Si/No)
	 * @param stato stato del prodotto(Definitivo,provvisorio)
	 * @param titolo titolo del prodotto
	 * @param proprietario Il proprietario del prodotto
	 * 
	 */
	public Prodotto(String annoPubblicazione,String formatoPubblicazione,String codiceDOI,String diffusione,String note,String categoria,String isbn,String titolo,String proprietario,String stato,boolean bozza)
	{
		this.annoPubblicazione=annoPubblicazione;
		this.formatoPubblicazione=formatoPubblicazione;
		this.categoria=categoria;
		this.codiceDOI=codiceDOI;
		this.isbn=isbn;
		this.note=note;
		this.titolo=titolo;
		this.diffusione=diffusione;
		this.bozza=bozza;
		this.stato=stato;
		this.proprietario=proprietario;
		
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
	public String getTitolo()
	{
		return titolo;
	}
	
	/**
	 * Metodo che setta il titolo al prodotto
	 * @param titolo da attribuire al prodotto
	 */
	public void setTitolo(String titolo)
	{
		this.titolo = titolo;
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
	 * Metodo che restituisce la diffusione
	 * @return diffusione legata al prodotto
	 */
	public String getDiffusione()
	{
		return this.diffusione;
	}
	
	/**
	 * Metodo che setta la diffusione al prodotto
	 * @param diffusione da fissare al prodotto
	 */
	public void setDiffusione(String diffusione)
	{
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
	public String getAnnoPubblicazione()
	{
		return this.annoPubblicazione;
	}
	
	/**
	 * Metodo che setta l'anno della pubblicazione del prodotto
	 * @param anno della pubblicazione da attribuire al prodotto
	 */
	public void setAnnoPubblicazione(String annoPubblicazione)
	{
		this.annoPubblicazione = annoPubblicazione;
	}
	
	/**
	 * Metodo che restituisce il formato della pubblicazione del prodotto
	 * @return formato della pubblicazione
	 */
	public String getFormatoPubblicazione()
	{
		return this.formatoPubblicazione;
	}
	
	/**
	 * Metodo che setta il formato della pubblicazione del prodotto
	 * @param formato della pubblicazione relativa al prodotto
	 */
	public void setFormatoPubblicazione(String formatoPubblicazione)
	{
		this.formatoPubblicazione = formatoPubblicazione;
	}
	
	/**
	 * Metodo che restituisce lo stato del prodotto
	 * @return stato del prodotto
	 */
	public String getStato()
	{
		return this.stato;
	}
	
	/**
	 * Metodo che setta lo stato al prodotto
	 * @param stato da attribuire al prodotto
	 */
	public void setStato(String stato)
	{
		this.stato=stato;
	}
	
	/**
	 * Metodo per conoscere se il prodotto è una bozza
	 * @return bozza stato del prodotto
	 */
	public boolean getBozza()
	{
		return this.bozza;
	}
	
	/**
	 * Metodo per settare o non settare la bozza
	 * @param bozza stato del prodotto
	 */
	public void setBozza(boolean bozza)
	{
		this.bozza=bozza;
	}
	
	/**
	 * Metodo che restituisce il proprietario del prodotto
	 * @return proprietario del prodotto
	 */
	public String getProprietario()
	{
		return this.proprietario;
	}
	
	/**
	 * Metodo che fissa il proprietario del prodotto
	 * @param nuovo proprietario del prodotto
	 */
	public void setProprietario(String proprietario)
	{
		this.proprietario=proprietario;
	}
}

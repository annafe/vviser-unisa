package it.unisa.vviser.entity;

import org.json.JSONException;
import org.json.JSONObject;

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
	private String tipologia;
	private String isbn;
	private String titolo;
	private String proprietario;
	private boolean bozza;
	private String stato;
	private String listaCollaboratori;
	
	private String descrizioneContenuti;
	private String indirizzoWeb;
	private String paroleChiavi;
	private String editore;
	private int numVolume;
	private int totalePagine;
	private int daPagina;
	private int aPagina;
	
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
	 * @param tipologia a cui appartine il prodotto
	 * @param bozza prodotto bozza(Si/No)
	 * @param stato stato del prodotto(Definitivo,provvisorio)
	 * @param titolo titolo del prodotto
	 * @param proprietario Il proprietario del prodotto
	 * @param listaCollaboratori contiene tutti i coautori del prodotto
	 * @param descrizioneContenuti breve descrizione del prodotto
	 * @param indirizzoWeb del prodotto
	 * @param paroleChiavi relative al prodotto
	 * @param editore del prodotto
	 * @param numVolume del prodotto
	 * @param totalePagine del prodotto
	 * @param daPagina indice della pagina iniziale del prodotto
	 * @param aPagina indice della pagine finale del prodotto
	 * 
	 */
	public Prodotto(String isbn,String titolo,String annoPubblicazione,String formatoPubblicazione,String codiceDOI,String diffusione,String note,String stato,boolean bozza,String tipologia,String proprietario,String listaCollaboratori,String descrizioneContenuti,String indirizzoWeb,String paroleChiavi,String editore,int numVolume,int totalePagine,int daPagina,int aPagina)
	{
		this.annoPubblicazione=annoPubblicazione;
		this.formatoPubblicazione=formatoPubblicazione;
		this.tipologia=tipologia;
		this.codiceDOI=codiceDOI;
		this.isbn=isbn;
		this.note=note;
		this.titolo=titolo;
		this.diffusione=diffusione;
		this.bozza=bozza;
		this.stato=stato;
		this.proprietario=proprietario;
		this.listaCollaboratori=listaCollaboratori;
		this.descrizioneContenuti=descrizioneContenuti;
		this.indirizzoWeb=indirizzoWeb;
		this.paroleChiavi=paroleChiavi;
		this.editore=editore;
		this.numVolume=numVolume;
		this.totalePagine=totalePagine;
		this.daPagina=daPagina;
		this.aPagina=aPagina;
		
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
	 * Metodo che restituisce la tipologia del prodotto
	 * @return  tipologia del relativa al prodotto
	 */
	public String getTipologia()
	{
		return this.tipologia;
	}
	
	/**
	 * Metodo che setta la tipologia a un prodtto
	 * @param tipologia da attribuire al prodotto
	 */
	public void setTipologia(String tipologia)
	{
		this.tipologia=tipologia;
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
	 * Metodo per conoscere se il prodotto e' una bozza
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
	
	/**
	 * Metodo che restituisce la lista di utenti che collaborano al prodotto 
	 * @return listaCollaborazioni
	 */
	public String getListaCollaboratori()
	{
		return this.listaCollaboratori;
	}
	
	/**
	 * Metodo che setta la lista di collaboratori al prodotto
	 * @param listaCollaboratori da attribuire al prodotto
	 */
	public void setListaCollaboratori(String listaCollaboratori)
	{
		this.listaCollaboratori=listaCollaboratori;
	}
	
	/**
	 * Metodo che restituisce la descrizione del prodotto
	 * @return descrizione del prodotto
	 */
	public String getDescrizioneContenuti()
	{
		return this.descrizioneContenuti;
	}
	
	/**
	 * Metodo che setta la descrizione al prodotto
	 * @param descrizioneContenuti descrizione da attribuire al prodotto
	 */
	public void setDescrizioneContenuti(String descrizioneContenuti)
	{
		this.descrizioneContenuti=descrizioneContenuti;
	}
	
	/**
	 * Metodo che restituisce il sito web del prodotto
	 * @return sito web del prodotto
	 */
	public String getIndirizzoWeb()
	{
		return this.indirizzoWeb;
	}
	
	/**
	 * Metodo che setta l'indirizzo web al prodotto
	 * @param indirizzoWeb del prodotto
	 */
	public void setIndirizzoWeb(String indirizzoWeb)
	{
		this.indirizzoWeb=indirizzoWeb;
	}
	
	/**
	 * Metodo che restituisce le parole chiavi relative al prodotto
	 * @return parole chiavi del prodotto(Separate da ;)
	 */
	public String getParoleChiavi()
	{
		return this.paroleChiavi;
	}
	
	/**
	 * Metodo che setta le parole chiavi al prodotto
	 * @param paroleChiavi del prodotto
	 */
	public void setParoleChiavi(String paroleChiavi)
	{
		this.paroleChiavi=paroleChiavi;
	}
	
	/**
	 * Metodo che restituisce l'editore del prodotto
	 * @return editore del prodotto
	 */
	public String getEditore()
	{
		return this.editore;
	}
	
	/**
	 * Metodo che setta l'editore al prodotto
	 * @param editore del prodotto
	 */
	public void setEditore(String editore)
	{
		this.editore=editore;
	}
	
	/**
	 * Metodo che restituisce il numero del volume relativo al prodotto
	 * @param numVolume
	 * @return
	 */
	public int getNumVolume()
	{
		return this.numVolume;
	}
	
	/**
	 * Metodo che setta il numero del volume relativo al prodotto
	 * @param numVolume numero del volume del prodotto
	 */
	public void setNumVolume(int numVolume)
	{
		this.numVolume=numVolume;
	}
	
	/**
	 * Metodo che restituisce il totale delle pagine del prodotto
	 * @return numero pagine del totale
	 */
	public int getTotalePagine()
	{
		return this.totalePagine;
	}
	
	/**
	 * Metodo che setta il totale delle pagine al prodotto
	 * @param totalePagine numero della pagine del prodotto
	 */
	public void setTotalePagine(int totalePagine)
	{
		this.totalePagine=totalePagine;
	}
	
	/**
	 * Metodo che restuisce l'indice della prima pagina del prodotto
	 * @return l'indice prima pagina del prodotto
	 */
	public int getDaPagina()
	{
		return this.daPagina;
	}
	
	/**
	 * Metodo che setta l'indice alla prima pagina del prodotto
	 * @param daPagina l'indice della prima pagina del prodotto
	 */
	public void setDaPagina(int daPagina)
	{
		this.daPagina=daPagina;
	}
	
	/**
	 * Metodo che restituisce l'indice dell'ultima pagina del prodotto
	 * @return l'indice ultima pagina del prodotto
	 */
	public int getApagina()
	{
		return this.aPagina;
	}
	
	/**
	 * Metodo che setta l'indice dell'ultima pagina del prodotto
	 * @param aPagina l'indice ultima pagina del prodotto
	 */
	public void setApagina(int aPagina)
	{
		this.aPagina=aPagina;
	}
	
	/**
	 * Metodo che crea la stringa json contenente isbn e titolo del prodotto
	 * @return stringa json
	 */
	public String getIsbnTitleProdotto()
	{
		JSONObject obj = new JSONObject();
		try {
			obj.put("isbn",getIsbn());
			obj.put("titolo",getTitolo());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return obj.toString();
	}
}

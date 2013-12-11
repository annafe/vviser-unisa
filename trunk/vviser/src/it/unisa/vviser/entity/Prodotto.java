package it.unisa.vviser.entity;

/**
 * 
 * @author Salvatore Angiuoli
 * @author Antonio De Piano
 *
 */
public class Prodotto
{
	private String Anno;
	private String Formato;
	private String CodiceDOI;
	private String Diffusione;
	private String Note; 
	private String Categoria;
	private String isbn;
	private String title;
	
	
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
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public String getCodiceDOI() {
		return CodiceDOI;
	}
	public void setCodiceDOI(String codiceDOI) {
		CodiceDOI = codiceDOI;
	}
	public String getDiffusione() {
		return Diffusione;
	}
	public void setDiffusione(String diffusione) {
		Diffusione = diffusione;
	}
	public String getNote() {
		return Note;
	}
	public void setNote(String note) {
		Note = note;
	}
	public String getCategoria() {
		return Categoria;
	}
	public void setCategoria(String categoria) {
		Categoria= categoria;
	}
	public String getAnno() {
		return Anno;
	}
	public void setAnno(String anno) {
		Anno = anno;
	}
	public String getFormato() {
		return Formato;
	}
	public void setFormato(String formato) {
		Formato = formato;
	}
}

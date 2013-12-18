package it.unisa.vviser.entity;


/**
 * 
 * @author Maria Vittoria Coda
 *
 */
public class Notifica {
	private String mittente=null;;
	private String destinatario=null;
	private String messaggio=null;
	private String tipo=null;
	private int id;
	
	public Notifica(String dest, String tipologia){
		this.destinatario=dest;
		this.tipo=tipologia;
	}
	
	public Notifica(String dest, String tipologia, String mitt){
		this.destinatario=dest;
		this.tipo=tipologia;
		this.mittente=mitt;
	}

	public String getMittente() {
		return mittente;
	}

	public void setMittente(String mittente) {
		this.mittente = mittente;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getMessaggio() {
		return messaggio;
	}

	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

}

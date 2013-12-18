package it.unisa.vviser.storage;

/**
 * 
 * @author Giuseppe Sabato
 *
 */
public final class DBNames {
	
	private DBNames()
	{
		
	}
	
	//Nomi tabelle del database
	public static final String TABLE_COLLABORAZIONI="collaborazioni";
	public static final String TABLE_DIPARTIMENTO="dipartimento";
	public static final String TABLE_EVENTOVALUTAZIONE="eventovalutazione";
	public static final String TABLE_LISTAVALUTAZIONE="listavalutazione";
	public static final String TABLE_NOTIFICA="notifica";
	public static final String TABLE_PARTECIPAZIONEAVALUTAZIONE="partecipazioneavalutazione";
	public static final String TABLE_PRODOTTO="prodotto";
	public static final String TABLE_PRODOTTOINCONFLITTO="prodottoinconflitto";
	public static final String TABLE_PRODOTTOLISTA="prodottolista";
	public static final String TABLE_PUBBLICAZIONESURIVISTA="pubblicazionesurivista";
	public static final String TABLE_RIVISTA="rivista";
	public static final String TABLE_SOTTOMETTIMIUR="sottomettiMIUR";
	public static final String TABLE_TIPOLOGIA="tipologia";
	public static final String TABLE_UTENTE="utente";
	
	//Attributi collaborazioni
	public static final String ATTR_COLLABORAZIONI_COLLABORATORE="collaboratore";
	public static final String ATTR_COLLABORAZIONI_PRODOTTO_ISBN="prodotto_isbn";
	public static final String ATTR_COLLABORAZIONI_PROPRIETARIO="proprietario";
	public static final String ATTR_COLLABORAZIONI_CONVALIDATO="convalidato";
	
	//Attributi dipartimento
	public static final String ATTR_DIPARTIMENTO_NOME="nome";
	public static final String ATTR_DIPARTIMENTO_FACOLTA="facolta";
	
	//Attributi eventovalutazione
	public static final String ATTR_EVENTOVALUTAZIONE_ID="id";
	public static final String ATTR_EVENTOVALUTAZIONE_NOME="nome";
	public static final String ATTR_EVENTOVALUTAZIONE_NUMERODIPUBBLICAZIONI="numeroDiPubblicazioni";
	public static final String ATTR_EVENTOVALUTAZIONE_DADATA="daDAta";
	public static final String ATTR_EVENTOVALUTAZIONE_ADATA="aData";
	public static final String ATTR_EVENTOVALUTAZIONE_SCADENZA="scadenza";
	
	//Attributi listavalutazione
	public static final String ATTR_LISTAVALUTAZIONE_UTENTE_EMAIL="utente_email";
	public static final String ATTR_LISTAVALUTAZIONE_EVENTOVALUTAZIONE_ID="eventoValutazione_id";
	public static final String ATTR_LISTAVALUTAZIONE_SUGGERIMENTO="suggerimento";
	public static final String ATTR_LISTAVALUTAZIONE_BLOCCATO="bloccato";
	
	//Attributi notifica
	public static final String ATTR_NOTIFICA_ID="id";
	public static final String ATTR_NOTIFICA_TIPO="tipo";
	public static final String ATTR_NOTIFICA_STATO="stato";
	public static final String ATTR_NOTIFICA_MESSAGGIO="messaggio";
	public static final String ATTR_NOTIFICA_MITTENTE="mittente";
	public static final String ATTR_NOTIFICA_DESTINATARIO="destinatario";
	
	//Attributi partecipazioneavalutazione
	public static final String ATTR_PARTECIPAZIONEAVALUTAZIONE_UTENTE_EMAIL="utente_email";
	public static final String ATTR_PARTECIPAZIONEAVALUTAZIONE_EVENTOVALUTAZIONE_ID="eventoValutazione_id";
	
	//Attributi prodotto
	public static final String ATTR_PRODOTTO_ISBN="isbn";
	public static final String ATTR_PRODOTTO_TITOLO="titolo";
	public static final String ATTR_PRODOTTO_ANNOPUBBLICAZIONE="annoPubblicazione";
	public static final String ATTR_PRODOTTO_FORMATOPUBBLICAZIONE="formatoPubblicazione";
	public static final String ATTR_PRODOTTO_CODICEDOI="codiceDoi";
	public static final String ATTR_PRODOTTO_DIFFUSIONE="diffusione";
	public static final String ATTR_PRODOTTO_LISTACOLLABORATORI="listacollaboratori";
	public static final String ATTR_PRODOTTO_DESCRIZIONECONTENUTI="descrizionecontenuti";
	public static final String ATTR_PRODOTTO_INDIRIZZOWEB="indirizzoweb";
	public static final String ATTR_PRODOTTO_PAROLECHIAVI="parolechiavi";
	public static final String ATTR_PRODOTTO_EDITORE="editore";
	public static final String ATTR_PRODOTTO_NUMVOLUME="numvolume";
	public static final String ATTR_PRODOTTO_TOTALEPAGINE="totalepagine";
	public static final String ATTR_PRODOTTO_DAPAGINA="dapagina";
	public static final String ATTR_PRODOTTO_APAGINA="apagina";
	public static final String ATTR_PRODOTTO_NOTE="note";
	public static final String ATTR_PRODOTTO_STATO="stato";
	public static final String ATTR_PRODOTTO_BOZZA="bozza";
	public static final String ATTR_PRODOTTO_TIPOLOGIA="tipologia";
	public static final String ATTR_PRODOTTO_EMAILPROPRIETARIO="email_proprietario";
	
	//Attributi prodottoinconflitto
	public static final String ATTR_PRODOTTOINCONFLITTO_PRODOTTO_ISBN="Prodotto_isbn";
	public static final String ATTR_PRODOTTOINCONFLITTO_UTENTE_EMAIL="utente_email";
	public static final String ATTR_PRODOTTOINCONFLITTO_EVENTOVALUTAZIONE_ID="eventoValutazione_id";
	
	
	//Attributi prodottolista
	public static final String ATTR_PRODOTTOLISTA_PRODOTTO_ISBN="prodotto_isbn";
	public static final String ATTR_PRODOTTOLISTA_UTENTE_EMAIL="utente_email";
	public static final String ATTR_PRODOTTOLISTA_EVENTOVALUTAZIONE_ID="eventoValutazione_id";
	public static final String ATTR_PRODOTTOLISTA_PRIORITA="priorita";
	
	//Attributi pubblicazionesurivista
	public static final String ATTR_PUBBLICAZIONESURIVISTA_RIVISTA_ISSN="rivista_issn";
	public static final String ATTR_PUBBLICAZIONESURIVISTA_PRODOTTO_ISBN="prodotto_isbn";
	public static final String ATTR_PUBBLICAZIONESURIVISTA_VOLUME="volume";
	public static final String ATTR_PUBBLICAZIONESURIVISTA_DAPAGINA="daPagina";
	public static final String ATTR_PUBBLICAZIONESURIVISTA_APAGINA="aPagina";
	public static final String ATTR_PUBBLICAZIONESURIVISTA_TOTALEPAGINE="totalePagine";
	
	//Attributi rivista
	public static final String ATTR_RIVISTA_ISSN="issn";
	public static final String ATTR_RIVISTA_NOME="nome";
	public static final String ATTR_RIVISTA_NOMEALTERNATIVO="nomeAlternativo";
	public static final String ATTR_RIVISTA_EDITORE="editore";
	public static final String ATTR_RIVISTA_DA_ANNO="daAnno";
	public static final String ATTR_RIVISTA_AD_ANNO="adAnno";
	public static final String ATTR_RIVISTA_VALIDITA="validita";
	
	//Attributi sottomettiMIUR
	public static final String ATTR_SOTTOMETTIMIUR_UTENTE_EMAIL="utente_email";
	public static final String ATTR_SOTTOMETTIMIUR_PRODOTTO_ISBN="prodotto_isbn";
	
	//Attributi tipologia
	public static final String ATTR_TIPOLOGIA_NOME="nome";
	public static final String ATTR_TIPOLOGIA_DESCRIZIONE="descrizione";
	public static final String ATTR_TIPOLOGIA_VALIDITA="validita";
	public static final String ATTR_TIPOLOGIA_DA="da";
	public static final String ATTR_TIPOLOGIA_A="a";
	
	//Attributi utente
	public static final String ATTR_UTENTE_EMAIL="email";
	public static final String ATTR_UTENTE_PASSWORD="password";
	public static final String ATTR_UTENTE_CODICEFISCALE="codiceFiscale";
	public static final String ATTR_UTENTE_PROVINCIADINASCITA="provinciaDiNascita";
	public static final String ATTR_UTENTE_COMUNEDINASCITA="comuneDiNascita";
	public static final String ATTR_UTENTE_COGNOME="cognome";
	public static final String ATTR_UTENTE_NOME="nome";
	public static final String ATTR_UTENTE_DATADINASCITA="dataDiNascita";
	public static final String ATTR_UTENTE_TIPOLOGIA="tipologia";
	public static final String ATTR_UTENTE_DIPARTIMENTO_NOME="dipartimento_nome";
	
	
	
}

package it.unisa.vviser.storage;

import it.unisa.vviser.entity.EventoValutazione;
import it.unisa.vviser.entity.ListaProdottiValutazione;
import it.unisa.vviser.entity.Prodotto;
import it.unisa.vviser.entity.ProdottoValutazione;
import it.unisa.vviser.entity.Utente;
import it.unisa.vviser.exception.InsertProdottiValutazioneException;
import it.unisa.vviser.exception.NotAvailableProdottiPerValutazioneException;
import it.unisa.vviser.exception.NotFoundListeValutazioneException;
import it.vviser.common.CommonMethod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface FacadeValutazione {
	
	public void sottomettiListaProdottiValutazione(ArrayList<ProdottoValutazione> prodottiValutazione,String emailUtente) throws SQLException,InsertProdottiValutazioneException;
	
	public ListaProdottiValutazione showProdottiVal(String emailUtente,int idEvento) throws SQLException;
	
	public ArrayList<Utente> showListaUtenti(String emailUtente) throws SQLException;
	
	public void modifyProdottiValutazione(ArrayList<ProdottoValutazione> newProdottiSottomessi,ArrayList<ProdottoValutazione> oldProdottiSottomessi,ArrayList<ProdottoValutazione> oldProdottiSottomessiUpdate,String emailUtente,int idEvento) throws SQLException;
	
	public ArrayList<ListaProdottiValutazione> getListeProdottiValutazione(String emailUtente) throws SQLException,NotFoundListeValutazioneException;
	
	public EventoValutazione getEventoValutazione(String emailUtente) throws SQLException,InsertProdottiValutazioneException;
	
	public ArrayList<Prodotto> prodottiFiltrati(EventoValutazione evento, ArrayList<Prodotto> prodotti) throws NotAvailableProdottiPerValutazioneException;
	
	public EventoValutazione ottieniEventoValutazione(int idEvento) throws SQLException;
	
	
	public void convalidaORifiutaListaProdottiValutazione(ListaProdottiValutazione listaProdottiValutazione, String bloccato) throws SQLException;
	
	public void settaSuggerimentoListaValutazione(ListaProdottiValutazione listaProdottiValutazione,String suggestion)throws SQLException;
	
	public ArrayList<ProdottoValutazione> getProdottiValutazioneInConflitto(String emailUtente, int idEvento) throws SQLException;
	
}

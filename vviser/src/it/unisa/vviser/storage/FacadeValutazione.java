package it.unisa.vviser.storage;


import it.unisa.vviser.entity.EventoValutazione;
import it.unisa.vviser.entity.ListaProdottiValutazione;
import it.unisa.vviser.entity.Prodotto;
import it.unisa.vviser.entity.ProdottoValutazione;
import it.unisa.vviser.entity.Utente;
import it.unisa.vviser.exception.InsertProdottiValutazioneException;
import it.unisa.vviser.exception.NotAvailableProdottiPerValutazioneException;
import it.unisa.vviser.exception.NotFoundListeValutazioneException;
import java.sql.SQLException;
import java.util.ArrayList;

public class FacadeValutazione {
	
	DBProdottiValutazione prodottiValutazione=DBProdottiValutazione.getInstance();
	DBGestioneProdotto prodotto=DBGestioneProdotto.getInstance();
	
	public void sottomettiListaProdottiValutazione(ArrayList<ProdottoValutazione> prodottiValutazione,String emailUtente) throws SQLException,InsertProdottiValutazioneException
	{
		
	}
	
	public ListaProdottiValutazione showProdottiVal(String emailUtente,int idEvento) throws SQLException
	{
		return prodottiValutazione.showProdottiVal(emailUtente, idEvento);
	}
	
	public ArrayList<Utente> showListaUtenti(String emailUtente) throws SQLException
	{
		return prodottiValutazione.showListaUtenti(emailUtente);
	}
	
	public void modifyProdottiValutazione(ArrayList<ProdottoValutazione> newProdottiSottomessi,ArrayList<ProdottoValutazione> oldProdottiSottomessi,ArrayList<ProdottoValutazione> oldProdottiSottomessiUpdate,String emailUtente,int idEvento) throws SQLException
	{
		prodottiValutazione.modifyProdottiValutazione(newProdottiSottomessi, oldProdottiSottomessi, oldProdottiSottomessiUpdate, emailUtente, idEvento);
	}
	
	public ArrayList<ListaProdottiValutazione> getListeProdottiValutazione(String emailUtente) throws SQLException,NotFoundListeValutazioneException
	{
		return prodottiValutazione.getListeProdottiValutazione(emailUtente);
	}
	
	public EventoValutazione getEventoValutazione(String emailUtente) throws SQLException,InsertProdottiValutazioneException
	{
		return prodottiValutazione.getEventoValutazione(emailUtente);
	}
	
	public ArrayList<Prodotto> prodottiFiltrati(EventoValutazione evento, ArrayList<Prodotto> prodotti) throws NotAvailableProdottiPerValutazioneException
	{
		return prodottiValutazione.prodottiFiltrati(evento, prodotti);
	}
	
	public EventoValutazione ottieniEventoValutazione(int idEvento) throws SQLException
	{
		return prodottiValutazione.ottieniEventoValutazione(idEvento);
	}
	
	
	public void convalidaORifiutaListaProdottiValutazione(ListaProdottiValutazione listaProdottiValutazione, String bloccato) throws SQLException
	{
		prodottiValutazione.convalidaORifiutaListaProdottiValutazione(listaProdottiValutazione, bloccato);
	}
	
	public void settaSuggerimentoListaValutazione(ListaProdottiValutazione listaProdottiValutazione,String suggestion)throws SQLException
	{
		prodottiValutazione.settaSuggerimentoListaValutazione(listaProdottiValutazione, suggestion);
	}
	
	public ArrayList<ProdottoValutazione> getProdottiValutazioneInConflitto(String emailUtente, int idEvento) throws SQLException
	{
		return prodottiValutazione.getProdottiValutazioneInConflitto(emailUtente, idEvento);
	}

}

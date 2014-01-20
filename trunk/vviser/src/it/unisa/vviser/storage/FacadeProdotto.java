package it.unisa.vviser.storage;

import it.unisa.vviser.entity.Prodotto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FacadeProdotto {
	public ArrayList<Prodotto> visualizzaProdottiProprietarioCoautore(String utente)throws SQLException;

}

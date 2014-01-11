package it.unisa.vviser.servlet;

import it.unisa.vviser.entity.EventoValutazione;
import it.unisa.vviser.storage.DBEventiValutazione;
import it.unisa.vviser.storage.DBGestioneProdotto;
import it.unisa.vviser.storage.DBProdottiValutazione;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Maria Vittoria Coda
 *
 */

//@WebServlet("/VisualizzaModificaEventoValutazioneServlet")
public class VisualizzaModificaDettagliEventoValutazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBEventiValutazione manager;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaModificaDettagliEventoValutazioneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		manager = DBEventiValutazione.getInstance(); 
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt((String)request.getAttribute("idEvento"));
		String nome = (String) request.getAttribute("nomeEvento");
		
		String azioneModifica = (String) request.getAttribute("modificaEvento");
		String azioneRimuovi = (String) request.getAttribute("rimuoviEvento");
		
		EventoValutazione evento=null;
		
		try {
			evento = (EventoValutazione) this.manager.visualizzaEventoPerId(id, nome);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		if (azioneModifica != null && azioneRimuovi==null){
			request.setAttribute("eventoSelezionato", evento);
			
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/vviser/gsi/visualizzaModificaDettagliEvento.jsp");
			rd.forward(request,response);
		}
		else if (azioneRimuovi != null && azioneModifica==null){
			try{
				manager.deleteEventoByID(evento);
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/vviser/gsi/visualizzaEventi.jsp");
			rd.forward(request,response);
		}
	}

}

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
		String s=(String) request.getParameter("idEvento");
	//	System.out.println(s);
		int id = Integer.parseInt(s);
	//	String nome = (String) request.getParameter("nomeEvento");
		
		String azioneModifica = (String) request.getParameter("modificaEvento"); 
		String azioneRimuovi = (String) request.getParameter("rimuoviEvento"); 
		
		EventoValutazione evento=null;
		
		try {
			evento = (EventoValutazione) this.manager.visualizzaEventoPerId(id);
			//System.out.println("Ha eseguito la query: ");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	
		
		if (azioneModifica != null && azioneRimuovi==null){
			request.setAttribute("eventoSelezionato", evento);
			
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/gsi/visualizzaModificaDettagliEvento.jsp");
			rd.forward(request,response);
			return;
		}
		else if (azioneRimuovi != null && azioneModifica==null){
			try{
				manager.deleteEventoByID(evento);
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/gsi/visualizzaListaEventi.jsp");
			rd.forward(request,response);
			return;
		}
		
		else 
			System.out.println("");
			
		}
	}



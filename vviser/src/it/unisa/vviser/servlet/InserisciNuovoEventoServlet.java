/**
 * Author: Maria Vittoria Coda
 */
package it.unisa.vviser.servlet;

import it.unisa.vviser.entity.EventoValutazione;
import it.unisa.vviser.storage.DBEventiValutazione;

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
 * Servlet implementation class InserisciNuovoEventoServlet
 */
//@WebServlet("/InserisciNuovoEventoServlet")
public class InserisciNuovoEventoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBEventiValutazione eventiValutazioneManager;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserisciNuovoEventoServlet() {
        super();
    }

    public void init(ServletConfig config) throws ServletException{
    	super.init(config);
    	eventiValutazioneManager = DBEventiValutazione.getInstance();
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
		String nome = request.getParameter("nomeEvento");
		String num = request.getParameter("numPubb");
		String scadenza = request.getParameter("scadenza");
		String da = request.getParameter("dataInizio");
		String a = request.getParameter("dataFine");
		
		ServletContext sc = getServletContext();
		ArrayList<EventoValutazione> lista = null;
		
		EventoValutazione e = new EventoValutazione(nome, num, scadenza, da, a);
		try{
			boolean resp = eventiValutazioneManager.addEvento(e);
			System.out.println("Risultato  query: "+resp);
			lista = (ArrayList<EventoValutazione>) eventiValutazioneManager.visualizzaEventi();
		} catch (SQLException ex){
			ex.printStackTrace();
			RequestDispatcher reqd = sc.getRequestDispatcher("/vviser/gsi/error.jsp");
			reqd.forward(request, response); return;
		}
		
		request.setAttribute("listaEventi", lista);
		
//		ServletContext sc = getServletContext();
		// ridirezione alla pagina con la lista di tutti gli eventi di valutazione
		RequestDispatcher rd = sc.getRequestDispatcher("/gsi/visualizzaListaEventi.jsp");
		
		rd.forward(request,response);
		return;
	}

}

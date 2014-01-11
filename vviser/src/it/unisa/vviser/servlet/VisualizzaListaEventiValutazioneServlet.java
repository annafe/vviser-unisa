/**
 * 
    Author: Maria Vittoria Coda
 */

// CIAO MICHELE!

package it.unisa.vviser.servlet;

import it.unisa.vviser.entity.EventoValutazione;
import it.unisa.vviser.storage.DBEventiValutazione;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class VisualizzaEventiValutazioneServlet
 */
@WebServlet("/VisualizzaEventiValutazioneServlet")
public class VisualizzaListaEventiValutazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBEventiValutazione eventiValutazioneManager;
	private ArrayList<EventoValutazione> listaEventi;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaListaEventiValutazioneServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		try{
			listaEventi = (ArrayList<EventoValutazione>) eventiValutazioneManager.visualizzaEventi();
		} 
		catch (SQLException e){
			e.printStackTrace();
		}
		
		request.setAttribute("eventi", listaEventi);
		
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/visualizzaEventi.jsp");
		rd.forward(request,response);
	}

}

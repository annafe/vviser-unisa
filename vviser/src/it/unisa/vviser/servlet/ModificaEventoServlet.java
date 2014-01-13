package it.unisa.vviser.servlet;

/**
 * 
 * @author Maria Vittoria Coda
 *
 */

import it.unisa.vviser.storage.DBEventiValutazione;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ModificaEventoServlet
 */
@WebServlet("/ModificaEventoServlet")
public class ModificaEventoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBEventiValutazione manager;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaEventoServlet() {
        super();
    }
    
    public void init(ServletConfig config) throws ServletException{
    	super.init(config);
    	this.manager = DBEventiValutazione.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		System.out.println();
		String nome = request.getParameter("eventoName");
		String num = request.getParameter("eventoNumPubb");
		String scad = request.getParameter("eventoScadenza");
		String da = request.getParameter("eventoDataInizio");
		String a = request.getParameter("eventoDataFine");
		
		try{
			manager.modifyAllByID(id,nome,num,scad,da,a);
		}catch(SQLException e1){
			e1.printStackTrace();
		}
		
		ServletContext sc = getServletContext();
		// ridirezione alla pagina con la lista di tutti gli eventi di valutazione
		RequestDispatcher rd = sc.getRequestDispatcher("/gsi/visualizzaListaEventi.jsp");
		rd.forward(request,response);
		return;
	}

}

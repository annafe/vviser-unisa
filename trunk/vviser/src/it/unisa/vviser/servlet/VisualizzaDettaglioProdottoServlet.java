package it.unisa.vviser.servlet;

import it.unisa.vviser.entity.Prodotto;
import it.unisa.vviser.storage.DBGestioneProdotto;

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
 * Servlet implementation class VisualizzaDettaglioProdottoServlet
 */

/**
 * 
 * @author Antonio De Piano
 *
 */
@WebServlet("/VisualizzaDettaglioProdottoServlet")
public class VisualizzaDettaglioProdottoServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private DBGestioneProdotto gprodotto;
       
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		this.gprodotto=DBGestioneProdotto.getInstance();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 processRequest(request, response);
	}

	/**
	 * Dettaglio del prodotto
	 * @param request servlet request
	 * @param response servlet response
	 */
	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) {
		
		String isbn = request.getParameter("isbn");
		
		try
		{
			DBGestioneProdotto gp=DBGestioneProdotto.getInstance();
			Prodotto pr=gp.visualizzaDettagliProdotto(isbn);
			//Come faccio a inviare il prodotto a una jsp ?? 
			
			//help
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
	}
}

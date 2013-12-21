package it.unisa.vviser.servlet;

import it.unisa.vviser.entity.Prodotto;
import it.unisa.vviser.storage.DBGestioneProdotto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ItuoiProdotti
 */
@WebServlet("/ItuoiProdotti")
public class ItuoiProdotti extends HttpServlet
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		 processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		 processRequest(request, response);
	}
	
	/**
	 * Visualizza i prodotti personali
	 * @param request
	 * @param response
	 */
	private void processRequest(HttpServletRequest request,HttpServletResponse response)
	{
		
		String utente = request.getParameter("utente");
		
		try
		{
			DBGestioneProdotto gp=DBGestioneProdotto.getInstance();
			ArrayList<Prodotto> pr=gp.visualizzaProdottiProprietarioCoautore(utente);
			//Come faccio a inviare quest'output a una jsp ?? 
			
			//help
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
	}

}

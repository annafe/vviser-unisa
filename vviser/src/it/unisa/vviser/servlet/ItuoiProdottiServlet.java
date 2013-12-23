package it.unisa.vviser.servlet;

import it.unisa.vviser.entity.Prodotto;
import it.unisa.vviser.storage.DBGestioneProdotto;

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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ItuoiProdotti
 */
@WebServlet("/ItuoiProdotti")


/**
 * 
 * @author Antonio De Piano
 *
 */


public class ItuoiProdottiServlet extends HttpServlet
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
		
//da modificare
		HttpSession s = request.getSession();
		String emailUtente=(String)s.getAttribute("sessEmail");
		
		try
		{
			DBGestioneProdotto gp=DBGestioneProdotto.getInstance();
			ArrayList<Prodotto> pr=gp.visualizzaProdottiProprietarioCoautore(emailUtente);
			

			request.setAttribute("listprodotti",pr);
			
			PrintWriter out = response.getWriter();
			
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/ituoiprodotti.jsp");
			rd.forward(request,response);
		
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

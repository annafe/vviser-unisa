package it.unisa.vviser.servlet;

import it.unisa.vviser.entity.Prodotto;
import it.unisa.vviser.storage.DBGestioneProdotto;
import it.unisa.vviser.storage.DBProdottiValutazione;
import it.vviser.common.CommonMethod;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Antonio De Piano
 *
 */
public class RicercaPubblicaProdottoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private DBGestioneProdotto gprodotto;

	/**
	 * 
	 */
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		this.gprodotto=DBGestioneProdotto.getInstance();
	}
	
	/**
     * Gestisce il metodo HTTP <code>GET</code>
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException in presenza di un errore servlet
     * @throws IOException in presenza di un errore I/O 
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
        processRequest(request, response);
    }
	
	/**
     * Gestisce il metodo HTTP <code>POST</code>
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException in presenza di un errore servlet
     * @throws IOException in presenza di un errore I/O 
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

	/**
	 * Sottomette un prodotto al MIUR
	 * @param request servlet request
	 * @param response servlet response 
	 * @throws ServletException 
	 * @throws IOException 
	 */
	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		String tipo_ricerca = request.getParameter("tipo_ricerca");
		
		try
		{
			DBGestioneProdotto gp=DBGestioneProdotto.getInstance();
			ArrayList<Prodotto> pr = new ArrayList<Prodotto>();
			
			if(tipo_ricerca.equals("tipologia"))
			{
				pr=gp.ricercaProdottoPerTipologia(request.getParameter("tipologia"));
			}
			
			if(tipo_ricerca.equals("titolo_prodotto"))
			{
				pr=gp.ricercaProdottoPerTitoloProdotto(request.getParameter("titolo_prodotto"));
			}
			if(tipo_ricerca.equals("titolo_rivista"))
			{
				pr=gp.ricercaProdottoPerTitoloRivista(request.getParameter("titolo_rivista"));
			}
			if(tipo_ricerca.equals("issn_rivista"))
			{
				pr=gp.ricercaProdottoPerIssnRivista(request.getParameter("issn_rivista"));
			}
			if(tipo_ricerca.equals("anni"))
			{
				pr=gp.ricercaProdottoPerAnni(Integer.parseInt(request.getParameter("da")),Integer.parseInt(request.getParameter("a")));
			}
			
			request.setAttribute("results", pr);
			request.getServletContext().getRequestDispatcher("/gpr/RicercaPubblicaProdotto.jsp").forward(request, response);
			
			
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
	}
}
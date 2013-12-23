package it.unisa.vviser.servlet;

import it.unisa.vviser.entity.Prodotto;
import it.unisa.vviser.storage.DBGestioneProdotto;
import it.unisa.vviser.storage.DBProdottiValutazione;
import it.vviser.common.CommonMethod;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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
	 */
	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) {
		
		String tipo_ricerca = request.getParameter("tipo_ricerca");
		
		try
		{
			DBGestioneProdotto gp=DBGestioneProdotto.getInstance();
			if(tipo_ricerca.equals("titolo_prodotto"))
			{
				ArrayList<Prodotto> pr=gp.ricercaProdottoPerTitoloProdotto(request.getParameter("titolo"));
				
			}
			if(tipo_ricerca.equals("titolo_rivista"))
			{
				ArrayList<Prodotto> pr=gp.ricercaProdottoPerTitoloRivista(request.getParameter("titolo"));
			}
			if(tipo_ricerca.equals("issn_rivista"))
			{
				ArrayList<Prodotto> pr=gp.ricercaProdottoPerIssnRivista(request.getParameter("issn_rivista"));
			}
			if(tipo_ricerca.equals("anni"))
			{
				ArrayList<Prodotto> pr=gp.ricercaProdottoPerAnni(Integer.parseInt(request.getParameter("da")),Integer.parseInt(request.getParameter("a")));
			}
			
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
	}
}
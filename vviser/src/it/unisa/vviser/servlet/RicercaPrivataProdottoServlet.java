package it.unisa.vviser.servlet;

import it.unisa.vviser.entity.Prodotto;
import it.unisa.vviser.entity.Utente;
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
import javax.servlet.http.HttpSession;

/**
 * 
 * @author Antonio De Piano
 *
 */
public class RicercaPrivataProdottoServlet extends HttpServlet {
	
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
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		
		String tipo_ricerca = request.getParameter("tipo_ricerca");
		String catalogo = request.getParameter("catalogo");
		

		HttpSession s = request.getSession();
		Utente u = (Utente)s.getAttribute("utente");
		
		try
		{
			DBGestioneProdotto gp=DBGestioneProdotto.getInstance();
			ArrayList<Prodotto> pr = new ArrayList<Prodotto>();
			
			if(tipo_ricerca.equals("tipologia"))
			{
				if(catalogo.equalsIgnoreCase("pubblico"))
					pr=gp.ricercaProdottoPerTipologia(request.getParameter("tipologia"));
				else
					pr=gp.ricercaPrivataProdottoPerTipologia(request.getParameter("tipologia"), u);
			}
			if(tipo_ricerca.equals("titolo_prodotto"))
			{
				if(catalogo.equalsIgnoreCase("pubblico"))
					pr=gp.ricercaProdottoPerTitoloProdotto(request.getParameter("titolo_prodotto"));
				else
					pr=gp.ricercaPrivataProdottoPerTitoloProdotto(request.getParameter("titolo_prodotto"), u);
				
			}
			if(tipo_ricerca.equals("titolo_rivista"))
			{
				if(catalogo.equalsIgnoreCase("pubblico"))
					pr=gp.ricercaProdottoPerTitoloRivista(request.getParameter("titolo_rivista"));
				else
					pr=gp.ricercaPrivataProdottoPerTitoloRivista(request.getParameter("titolo_rivista"), u);
			}
			if(tipo_ricerca.equals("issn_rivista"))
			{
				if(catalogo.equalsIgnoreCase("pubblico"))
					pr=gp.ricercaProdottoPerIssnRivista(request.getParameter("issn_rivista"));
				else
					pr=gp.ricercaPrivataProdottoPerIssnRivista(request.getParameter("issn_rivista"), u);
			}
			if(tipo_ricerca.equals("anni"))
			{
				if(catalogo.equalsIgnoreCase("pubblico"))
					pr=gp.ricercaProdottoPerAnni(Integer.parseInt(request.getParameter("da")),Integer.parseInt(request.getParameter("a")));
				else
					pr=gp.ricercaPrivataProdottoPerAnni(Integer.parseInt(request.getParameter("da")),Integer.parseInt(request.getParameter("a")), u);
			}
			
			request.setAttribute("results", pr);
			request.getServletContext().getRequestDispatcher("/gpr/RicercaProdotto.jsp").forward(request, response);
			
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
	}
}
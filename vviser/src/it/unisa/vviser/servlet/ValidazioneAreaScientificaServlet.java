package it.unisa.vviser.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.vviser.entity.Prodotto;
import it.unisa.vviser.storage.DBGestioneValidazione;



/**
 * 
 * @author Angiuoli Salvatore
 *
 */
public class ValidazioneAreaScientificaServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private DBGestioneValidazione gprodotto;

	/**
	 * 
	 */
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		this.gprodotto=DBGestioneValidazione.getInstance();
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
	 * validazione area scientifica
	 * @param request servlet request
	 * @param response servlet response
	 */
	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) {
		String[] isbn = request.getParameterValues("selProd");
		DBGestioneValidazione gp=DBGestioneValidazione.getInstance();
		
		for(int i=0;i<isbn.length;i++)
		{
			try
			{
				gp.ValidatoAreaScientifica(isbn[i]);
			}
			catch (SQLException ex)
			{
				ex.printStackTrace();
			}
		}
		ServletContext sc = getServletContext();
		// ridirezione
		RequestDispatcher rd = sc.getRequestDispatcher("/areascientifica/home_area.jsp");
		try
		{
			rd.forward(request,response);
		}
		catch (ServletException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}

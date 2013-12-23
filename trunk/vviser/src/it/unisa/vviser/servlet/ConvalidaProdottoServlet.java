package it.unisa.vviser.servlet;

import it.unisa.vviser.entity.Prodotto;
import it.unisa.vviser.storage.DBGestioneProdotto;
import it.unisa.vviser.storage.DBProdottiValutazione;
import it.vviser.common.CommonMethod;

import java.io.IOException;
import java.sql.SQLException;

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
public class ConvalidaProdottoServlet extends HttpServlet {
	
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
	 *Convalida un prodotto dove compaio come coautore
	 * @param request servlet request
	 * @param response servlet response
	 */
	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) {
		
		String isbn = request.getParameter("isbn");
		//Recuperato dalla sessione

		HttpSession s = request.getSession();
		String emailUtente=(String)s.getAttribute("sessEmail");
		try
		{
			DBGestioneProdotto gp=DBGestioneProdotto.getInstance();
			gp.convalidaProdotto(emailUtente,isbn);
			
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		
		ServletContext sc = getServletContext();
		// ridirezione alla pagina con la lista di tutti i prodotti
		RequestDispatcher rd = sc.getRequestDispatcher("/ituoiprodotti.jsp");
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
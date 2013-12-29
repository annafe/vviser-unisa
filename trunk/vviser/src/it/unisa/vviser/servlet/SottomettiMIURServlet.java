package it.unisa.vviser.servlet;

import it.unisa.vviser.entity.Prodotto;
import it.unisa.vviser.storage.DBGestioneProdotto;
import it.unisa.vviser.entity.Utente;
import it.unisa.vviser.storage.DBUtente;
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
public class SottomettiMIURServlet extends HttpServlet {
	
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
		
		String[] checkProduct=request.getParameterValues("selProd");
		HttpSession s = request.getSession();
		Utente currentUser=(Utente)s.getAttribute("utente");
		DBGestioneProdotto gp=DBGestioneProdotto.getInstance();
		
		for(int i=0;i<checkProduct.length;i++)
		{
			try
			{
				gp.sottomettiAlMIUR(currentUser.getEmail(),checkProduct[i]);
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			
		}
/*
		ServletContext sc = getServletContext();
		// ridirezione
		RequestDispatcher rd = sc.getRequestDispatcher("../SottomettiMIUR.jsp");
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
		}*/
	}
}
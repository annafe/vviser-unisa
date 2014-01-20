package it.unisa.vviser.servlet;

import it.unisa.vviser.entity.ListaProdottiValutazione;
import it.unisa.vviser.storage.DBProdottiValutazione;
import it.unisa.vviser.storage.FacadeValutazione;

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
 * @author Giuseppe Sabato
 *
 */
public class ServletSuggerimentoListaValutazione extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private FacadeValutazione prodottiValutazioneManager;

	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		prodottiValutazioneManager= new FacadeValutazione();
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		try
		{
			HttpSession s=request.getSession();
			ListaProdottiValutazione listaProdottiValutazione=(ListaProdottiValutazione)s.getAttribute("listaProdottiValutazione");
			String suggestion=request.getParameter("sugg");
			prodottiValutazioneManager.settaSuggerimentoListaValutazione(listaProdottiValutazione, suggestion);
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/gsva/gsva.jsp");
			rd.forward(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
		
	}

}

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
public class ServletConvalidaORifiutaListaValutazione extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private DBProdottiValutazione prodottiValutazioneManager;

	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		prodottiValutazioneManager=DBProdottiValutazione.getInstance();
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
			String bloccato=(String)request.getParameter("bloccato");
			System.out.println(bloccato);
			prodottiValutazioneManager.convalidaORifiutaListaProdottiValutazione(listaProdottiValutazione,bloccato);
			ServletContext sc = getServletContext();
			if(bloccato.equals("si"))
			{
				RequestDispatcher rd = sc.getRequestDispatcher("/gsva/gNotificaConvalidaList.jsp");
				rd.forward(request,response);
			}
			else
			{
				RequestDispatcher rd = sc.getRequestDispatcher("/gsva/gNotificaRifiutaList.jsp");
				rd.forward(request,response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
		
	}

}

package it.unisa.vviser.servlet;

import it.unisa.vviser.entity.ListaProdottiValutazione;
import it.unisa.vviser.storage.DBProdottiValutazione;

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

public class ServletVerificaListeVisualizza extends HttpServlet {
	
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
			HttpSession s = request.getSession();
			String emailUtente=(String)s.getAttribute("sessEmail");
			ArrayList<ListaProdottiValutazione> listeProdottiValutazione=prodottiValutazioneManager.getListeProdottiValutazione(emailUtente);
			
			
			if(listeProdottiValutazione.size()>1)
			{
				s.setAttribute("operazione", "visualizza");//setto la sessione per indicare alla jsp che si tratta di una visualizzazione
				request.setAttribute("liste", listeProdottiValutazione);
				ServletContext sc = getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/selezListe.jsp");
				rd.forward(request,response);
			}
			else
			{
				if(listeProdottiValutazione.size()==1)
				{
					request.setAttribute("lista", listeProdottiValutazione.get(0));
					ServletContext sc = getServletContext();
					RequestDispatcher rd = sc.getRequestDispatcher("/visualizzaLista.jsp");
					rd.forward(request,response);
				}
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

package it.unisa.vviser.servlet;

import it.unisa.vviser.entity.ListaProdottiValutazione;
import it.unisa.vviser.entity.ProdottoValutazione;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author Giuseppe Sabato
 *
 */
public class ServletSelezionaListe extends HttpServlet {
	
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
		
		
			HttpSession s = request.getSession();
			String emailUtente=(String)s.getAttribute("email");
			int idEvento=Integer.parseInt(request.getParameter("idList"));//cambiare nome da list a id
	
			ListaProdottiValutazione listaProdottiValutazione=new ListaProdottiValutazione();
			
			try {
				listaProdottiValutazione=prodottiValutazioneManager.showProdottiVal(emailUtente, idEvento);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute("lista", listaProdottiValutazione);
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/visualizzaLista.jsp");
			rd.forward(request,response);
			
		
	}

}




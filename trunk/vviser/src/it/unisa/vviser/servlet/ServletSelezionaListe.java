package it.unisa.vviser.servlet;

import it.unisa.vviser.entity.ListaProdottiValutazione;
import it.unisa.vviser.entity.ProdottoValutazione;
import it.unisa.vviser.entity.Utente;
import it.unisa.vviser.storage.DBProdottiValutazione;
import it.unisa.vviser.storage.FacadeValutazione;

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
	private FacadeValutazione prodottiValutazioneManager;

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
			int idEvento=Integer.parseInt(request.getParameter("idList"));
			Utente utente=(Utente)s.getAttribute("utente");
			String tipoShow=(String)s.getAttribute("visualizza");
	
			ListaProdottiValutazione listaProdottiValutazione=new ListaProdottiValutazione();
			
			try {
				listaProdottiValutazione=prodottiValutazioneManager.showProdottiVal(emailUtente, idEvento);
				s.setAttribute("segnalaConflitti", "off");
				if((utente.getTipologia().equals("direttoreDiDipartimento") || utente.getTipologia().equals("membroDelComitatoDiAreaDidattica")) && tipoShow.equals("tutti"))
				{
					s.setAttribute("segnalaConflitti", "on");
					ArrayList<ProdottoValutazione> conflitti=prodottiValutazioneManager.getProdottiValutazioneInConflitto(listaProdottiValutazione.getEmailUtente(), listaProdottiValutazione.getIdEventoValutazione());
					request.setAttribute("conflitti", conflitti);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute("lista", listaProdottiValutazione);
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/gsva/gVisualizzaLista.jsp");
			rd.forward(request,response);
			
		
	}

}




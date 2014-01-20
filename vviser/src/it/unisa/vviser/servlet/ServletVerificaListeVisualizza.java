package it.unisa.vviser.servlet;

import it.unisa.vviser.entity.ListaProdottiValutazione;
import it.unisa.vviser.entity.ProdottoValutazione;
import it.unisa.vviser.entity.Utente;
import it.unisa.vviser.exception.NotFoundListeValutazioneException;
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

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author Giuseppe Sabato
 *
 */
public class ServletVerificaListeVisualizza extends HttpServlet {
	
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
		
		try 
		{
			HttpSession s = request.getSession();
			Utente utente=(Utente)s.getAttribute("utente");
			String emailUtente="";
			String tipoShow;
			if(request.getParameter("utente")==null)
			{
				tipoShow="personale";
				s.setAttribute("visualizza", "personale");
			}
			else
				tipoShow=(String)s.getAttribute("visualizza");
			if(tipoShow.equals("personale"))
			{
				emailUtente=(String)s.getAttribute("sessEmail");
			}
			else
			{
				if(tipoShow.equals("tutti"))
				{
					String checkUtente=request.getParameter("utente");
					JSONObject o=new JSONObject(checkUtente);
					emailUtente=o.getString("email");
				}
			}
			s.setAttribute("email", emailUtente);//setto la sessione con l'email dell'utente di cui vogliamo visualizzare i prodotti di val.
			ArrayList<ListaProdottiValutazione> listeProdottiValutazione=prodottiValutazioneManager.getListeProdottiValutazione(emailUtente);
			
			
			
			
			if(listeProdottiValutazione.size()>1)
			{
				
				request.setAttribute("liste", listeProdottiValutazione);
				ServletContext sc = getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/gsva/gSelezListe.jsp");
				rd.forward(request,response);
			}
			else
			{
				if(listeProdottiValutazione.size()==1)
				{
					s.setAttribute("segnalaConflitti", "off");
					if((utente.getTipologia().equals("direttoreDiDipartimento") || utente.getTipologia().equals("membroDelComitatoDiAreaDidattica")) && tipoShow.equals("tutti"))
					{
						s.setAttribute("segnalaConflitti", "on");
						ArrayList<ProdottoValutazione> conflitti=prodottiValutazioneManager.getProdottiValutazioneInConflitto(listeProdottiValutazione.get(0).getEmailUtente(), listeProdottiValutazione.get(0).getIdEventoValutazione());
						request.setAttribute("conflitti", conflitti);
					}
					request.setAttribute("lista", listeProdottiValutazione.get(0));
					ServletContext sc = getServletContext();
					RequestDispatcher rd = sc.getRequestDispatcher("/gsva/gVisualizzaLista.jsp");
					rd.forward(request,response);
				}
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (JSONException e) 
		{
			e.printStackTrace();
		}
		catch (NotFoundListeValutazioneException e) 
		{
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/gsva/gVisualizzaImpossibile.jsp");
			rd.forward(request,response);
		}
		
		
		
	}

}

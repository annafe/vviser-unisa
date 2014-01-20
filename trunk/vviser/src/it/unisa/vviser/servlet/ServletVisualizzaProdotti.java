package it.unisa.vviser.servlet;

import it.unisa.vviser.entity.EventoValutazione;
import it.unisa.vviser.entity.Prodotto;
import it.unisa.vviser.exception.InsertProdottiValutazioneException;
import it.unisa.vviser.exception.NotAvailableProdottiPerValutazioneException;
import it.unisa.vviser.storage.DBGestioneProdotto;
import it.unisa.vviser.storage.DBProdottiValutazione;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
public class ServletVisualizzaProdotti extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private DBGestioneProdotto prodottiManager;
	private DBProdottiValutazione prodottiValutazioneManager;

	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		prodottiManager=DBGestioneProdotto.getInstance();
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
			
			EventoValutazione evento=prodottiValutazioneManager.getEventoValutazione(emailUtente);
			
			int numeroProdottiMax=evento.getNumeroPubblicazioni();
			ArrayList<Prodotto> prodotti=prodottiManager.visualizzaProdottiProprietarioCoautore(emailUtente);
			ArrayList<Prodotto> prodottiFiltrati=prodottiValutazioneManager.prodottiFiltrati(evento, prodotti);
			
			request.setAttribute("numProdMax", numeroProdottiMax);
			request.setAttribute("prod", prodottiFiltrati);//
			
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/gsva/gSelezProdotti.jsp");
			rd.forward(request,response); 
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InsertProdottiValutazioneException e)
		{
			// TODO Auto-generated catch block
			//e.printStackTrace();
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/gsva/gSottomissioneImpossibile.jsp");
			rd.forward(request,response); 
		}
		catch (NotAvailableProdottiPerValutazioneException e) 
		{
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/gsva/gVisualizzaProdottiPerSottomissioneImpossibile.jsp");
			rd.forward(request,response);
		}
		
		
		
	
	}

}

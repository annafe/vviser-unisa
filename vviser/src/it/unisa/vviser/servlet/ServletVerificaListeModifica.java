package it.unisa.vviser.servlet;

import it.unisa.vviser.entity.ListaProdottiValutazione;
import it.unisa.vviser.entity.Prodotto;
import it.unisa.vviser.storage.DBGestioneProdotto;
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

/**
 * 
 * @author Giuseppe Sabato
 *
 */
public class ServletVerificaListeModifica extends HttpServlet{
	
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
			ArrayList<ListaProdottiValutazione> listeSbloccate=new ArrayList<ListaProdottiValutazione>();

			//Ottengo tutte le liste sbloccate
			for(int i=0;i<listeProdottiValutazione.size();i++)
			{
				if(!listeProdottiValutazione.get(i).getBloccato())
				{
					listeSbloccate.add(listeProdottiValutazione.get(i));
				}
			}
			
			//se tutte le liste sono bloccate, non posso modificare nulla
			if(listeSbloccate.size()==0)
			{
				ServletContext sc = getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/modificaImpossibile.jsp");
				rd.forward(request,response);
				
			}
			
			if(listeSbloccate.size()>1)
			{
				s.setAttribute("operazione", "modifica");//setto la sessione per indicare alla jsp che si tratta di una modifica
				request.setAttribute("liste", listeSbloccate);
				ServletContext sc = getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/selezListe.jsp");
				rd.forward(request,response);
			}
			else
			{
				if(listeSbloccate.size()==1)
				{
					request.setAttribute("lista", listeSbloccate.get(0));
					ServletContext sc = getServletContext();
					RequestDispatcher rd = sc.getRequestDispatcher("/modificaLista.jsp");
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

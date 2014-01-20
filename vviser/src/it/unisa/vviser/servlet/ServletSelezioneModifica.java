package it.unisa.vviser.servlet;

import it.unisa.vviser.entity.EventoValutazione;
import it.unisa.vviser.entity.ListaProdottiValutazione;
import it.unisa.vviser.entity.Prodotto;
import it.unisa.vviser.entity.ProdottoValutazione;
import it.unisa.vviser.exception.InsertProdottiValutazioneException;
import it.unisa.vviser.exception.NotAvailableProdottiPerValutazioneException;
import it.unisa.vviser.storage.DBGestioneProdotto;
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

/**
 * 
 * @author Giuseppe Sabato
 *
 */
public class ServletSelezioneModifica extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private FacadeValutazione prodottiValutazioneManager;
	

	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		prodottiValutazioneManager=new FacadeValutazione();
		
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		
		
			try 
			{
				
				HttpSession s = request.getSession();
				String emailUtente=(String)s.getAttribute("email");
				ListaProdottiValutazione listaProdottiValutazione=(ListaProdottiValutazione)s.getAttribute("listaProdottiValutazione");
				EventoValutazione evento=prodottiValutazioneManager.ottieniEventoValutazione(listaProdottiValutazione.getIdEventoValutazione());
				s.setAttribute("evento", evento);//setto l'evento di valutazione
				ArrayList<Prodotto> prodotti=prodottiValutazioneManager.visualizzaProdottiProprietarioCoautore(emailUtente);
				ArrayList<Prodotto> prodottiFiltrati=prodottiValutazioneManager.prodottiFiltrati(evento, prodotti);
				ArrayList<Prodotto> prodottiNonSottomessi=new ArrayList<Prodotto>();
				
				//Seleziono i prodotti che l'utente deve selezionare per modificare l'attuale lista di valutazione
				ArrayList<ProdottoValutazione> prodottiValutazione=listaProdottiValutazione.getListaProdottiValutazione();
				
				
				//Seleziono tra i prodotti filtrati solo quelli che non sono gia' stati sottomessi a valutazione
				for(int i=0;i<prodottiFiltrati.size();i++)
				{
						boolean trovato=false;
						for(int j=0;j<prodottiValutazione.size();j++)
						{
							
							if(prodottiFiltrati.get(i).getIsbn().equals(prodottiValutazione.get(j).getIsbn()))
							{
								trovato=true;
								break;
							}
						}
						if(!trovato)
							prodottiNonSottomessi.add(prodottiFiltrati.get(i));
				}
				
				request.setAttribute("listaValutazione", listaProdottiValutazione);
				request.setAttribute("prodottiNonSottomessi", prodottiNonSottomessi);
				ServletContext sc = getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/gsva/gModificaLista.jsp");
				rd.forward(request,response);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (NotAvailableProdottiPerValutazioneException e) 
			{
				ServletContext sc = getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/gsva/visualizzaProdottiPerSottomissioneImpossibile.jsp");
				rd.forward(request,response);
			}
			
			

			

}
	
}

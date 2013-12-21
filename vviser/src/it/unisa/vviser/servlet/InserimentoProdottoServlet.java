package it.unisa.vviser.servlet;

import it.unisa.vviser.entity.EventoValutazione;
import it.unisa.vviser.entity.Prodotto;
import it.unisa.vviser.storage.DBGestioneProdotto;
import it.vviser.common.CommonMethod;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Antonio De Piano
 *
 */
public class InserimentoProdottoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * Gestisce il metodo HTTP <code>GET</code>
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException in presenza di un errore servlet
     * @throws IOException in presenza di un errore I/O 
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
	 * Effettua la registrazione di un nuovo utente
	 * @param request servlet request
	 * @param response servlet response
	 */
	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) {
		
		String isbn = request.getParameter("isbn");
		String cognome = request.getParameter("titolo");
		String dataPubblicazione = request.getParameter("data");
		String formatoPubblicazione = request.getParameter("formato_pub");
		String codiceDoi = request.getParameter("doi");
		String diffusione = request.getParameter("diffusione");
		String tipologie = request.getParameter("tipologia");
		String note = request.getParameter("note");
		String collaboratori = request.getParameter("collaboratori");
		String descrizione = request.getParameter("descrizione");

		String indirizzoweb = request.getParameter("indirizzoweb");
		String key = request.getParameter("key");
		String editore = request.getParameter("editore");
		String num_volume = request.getParameter("num_volume");
		String totalePagine = request.getParameter("totalePagine");

		String daPagina = request.getParameter("daPagina");
		String aPagin = request.getParameter("aPagina");
		
		Prodotto prod=new Prodotto();
		request.setAttribute("error", "Email non presente nel Database");
		
	/*	EventoValutazione e = new EventoValutazione(nome, num, scadenza, da, a);
		try{
			eventiValutazioneManager.addEvento(e);
		} catch (SQLException ex){
			ex.printStackTrace();
		}
		
	
		ServletContext sc = getServletContext();
		// ridirezione alla pagina con la lista di tutti gli eventi di valutazione
		RequestDispatcher rd = sc.getRequestDispatcher("/visualizzaEventi.jsp");
		rd.forward(request,response);
		*/
		
	}
}
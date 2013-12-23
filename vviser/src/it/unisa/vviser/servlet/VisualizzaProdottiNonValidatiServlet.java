package it.unisa.vviser.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.vviser.entity.Prodotto;
import it.unisa.vviser.storage.DBGestioneProdotto;
import it.unisa.vviser.storage.DBGestioneValidazione;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* 
* @author Angiuoli Salvatore
*
*/
public class VisualizzaProdottiNonValidatiServlet extends HttpServlet {
		
		private static final long serialVersionUID = 1L;
		private DBGestioneValidazione gvalidazione;

		/**
		 * 
		 */
		public void init(ServletConfig config) throws ServletException
		{
			super.init(config);
			this.gvalidazione=DBGestioneValidazione.getInstance();
		}
		
		/**
	     * Gestisce il metodo HTTP <code>GET</code>
	     *
	     * @param request servlet request
	     * @param response servlet response
	     * @throws ServletException in presenza di un errore servlet
	     * @throws IOException in presenza di un errore I/O 
	     */
		protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
		{
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
		 * Visualizza i prodotti personali
		 * @param request
		 * @param response
		 */
		private void processRequest(HttpServletRequest request,HttpServletResponse response)
		{
			
			
			try
			{
				DBGestioneValidazione gp=DBGestioneValidazione.getInstance();
				ArrayList<Prodotto> pr=gp.visualizzaProdotti();
				//Come faccio a inviare quest'output a una jsp ?? 
				
				//help
			}
			catch (SQLException ex)
			{
				ex.printStackTrace();
			}
		}

	}


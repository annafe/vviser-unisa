package it.unisa.vviser.servlet;

import it.unisa.vviser.entity.Utente;
import it.unisa.vviser.storage.DBUtente;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Michele Roviello
 *
 */
public class LoginServlet extends HttpServlet{
	
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
	 * Effettua il controllo per il login
	 * @param request servlet request
	 * @param response servlet response
	 */
	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) {
		
		try{
			String email = request.getParameter("email");
			String password = request.getParameter("password");
		
			DBUtente dbUtente = new DBUtente();
		
			Utente utente = dbUtente.authenticate(email, password);
		
			if(utente != null){
				request.getSession().setAttribute("utente", utente);
				if(utente.getTipologia().equalsIgnoreCase("Amministratore"))
					request.getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);
				else
					request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			}
			else{
				request.getServletContext().getRequestDispatcher("/error.jsp");
			}	
		} catch (Exception e){
			e.printStackTrace();
		}	
	}
}
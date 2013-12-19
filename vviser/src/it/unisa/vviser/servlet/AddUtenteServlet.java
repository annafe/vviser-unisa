package it.unisa.vviser.servlet;

import it.unisa.vviser.entity.Utente;
import it.unisa.vviser.storage.DBUtente;
import it.vviser.common.CommonMethod;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Michele Roviello
 *
 */
public class AddUtenteServlet extends HttpServlet {
	
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
		
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String dataNascita = request.getParameter("datadinascita");
		String comuneDiNascita = request.getParameter("comunedinascita");
		String provinciaDiNascita = request.getParameter("provinciadinascita");
		String codiceFiscale = request.getParameter("codicefiscale");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String dipartimento = request.getParameter("dipartimento");
		String tipologia = request.getParameter("tipologia");
		
		Utente u = new Utente();
		u.setNome(nome);
		u.setCognome(cognome);
		u.setDataDiNascita(CommonMethod.stringToDate(dataNascita));
		u.setComuneDiNascita(comuneDiNascita);
		u.setProvinciaDiNascita(provinciaDiNascita);
		u.setCodiceFiscale(codiceFiscale);
		u.setPassword(password);
		u.setEmail(email);
		u.setDipartimento(dipartimento);
		u.setTipologia(tipologia);
		
		DBUtente dbu = new DBUtente();
		try {
			if(dbu.addUtente(u))
				request.getServletContext().getRequestDispatcher("/");
			else
				request.getServletContext().getRequestDispatcher("/");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

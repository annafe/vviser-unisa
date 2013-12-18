package it.unisa.vviser.servlet;

import it.unisa.vviser.entity.Utente;
import it.unisa.vviser.storage.DBUtente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteAccountServlet
 * @author Romano Simone
 */
@WebServlet("/DeleteAccountServlet")
public class DeleteAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		execute(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		execute(request, response);
	}

	/**
	 * Elimina l'account selezionato dal Dababase
	 * @param request
	 * @param response
	 */
	private void execute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		//amministratore; non è possibile eliminare l'account corrente

		DBUtente dbUser = new DBUtente();
		Utente user = new Utente();
		//check user's typology
		if (user.getTipologia().equalsIgnoreCase("amministratore")){
			String email = (String) request.getAttribute("utente");
			user = dbUser.getUser(email);
			
			if (user==null){	//error
				request.setAttribute("error", "Email non presente nel Database");
				request.getServletContext().getRequestDispatcher("/gu/showAccount.jsp").forward(request, response);
			}
			else{	//account deleted
				dbUser.removeUtente(user);
				request.getServletContext().getRequestDispatcher("/gu/showAccount.jsp").forward(request, response);
			}
		}
		else{
			request.setAttribute("error", "Non hai i permessi per effettuare l'operazione");
			request.getServletContext().getRequestDispatcher("/gu/showAccount.jsp").forward(request, response);
		}
	}
}

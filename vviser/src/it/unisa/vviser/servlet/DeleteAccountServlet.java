package it.unisa.vviser.servlet;

import it.unisa.vviser.entity.Utente;
import it.unisa.vviser.storage.DBUtente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteAccountServlet
 * @author Romano Simone
 */
public class DeleteAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Logger log = Logger.getLogger("global");
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
		try {
			execute(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.severe("SQLException");
			e.printStackTrace();
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			execute(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Elimina l'account selezionato dal Dababase
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws SQLException 
	 */
	private void execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub

		DBUtente dbUser = new DBUtente();
		Utente amministratore = new Utente();
		amministratore = (Utente) request.getSession().getAttribute("utente");
		if (amministratore==null){
			//non loggato
			request.setAttribute("error", "Non sei loggato");
			request.getServletContext().getRequestDispatcher("/gu/login.jsp").forward(request, response);
			return;
		}
		//check user's typology
		if (amministratore.getTipologia().equalsIgnoreCase("amministratore")){		
			Utente toDelete = new Utente();
			toDelete = dbUser.getUtente((String) request.getParameter("daEliminare"));
			if (toDelete==null){	//error
				log.info("email non presente nel database");
				request.setAttribute("error", "Email non presente nel Database");
				request.getServletContext().getRequestDispatcher("/gu/admin.jsp").forward(request, response);
				return;
			}
			else{	//delete account
				//can't remove mySelf
				if (toDelete.getEmail().equals(amministratore.getEmail())){
					request.getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
					return;
				}
				dbUser.removeUtente(toDelete);
				log.info("account deleted: " + toDelete.getEmail());
				request.getServletContext().getRequestDispatcher("/gu/admin.jsp").forward(request, response);
				return;
			}
		}
		else{
			request.setAttribute("error", "Non hai i permessi per effettuare l'operazione");
			request.getServletContext().getRequestDispatcher("/gu/login.jsp").forward(request, response);
		}
	}
}

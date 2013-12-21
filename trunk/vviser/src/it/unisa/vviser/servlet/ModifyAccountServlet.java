package it.unisa.vviser.servlet;

import it.unisa.vviser.entity.Utente;
import it.unisa.vviser.storage.DBUtente;
import it.vviser.common.CommonMethod;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Romano Simone 0512101343
 * Servlet implementation class ModifyAccountServlet
 */
@WebServlet("/ModifyAccountServlet")
public class ModifyAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("global");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyAccountServlet() {
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


	private void execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//check permission
		try{
			Utente administrator = (Utente) request.getSession().getAttribute("utente");
			log.info("utente recuperato");
			if(!(administrator.getTipologia().equalsIgnoreCase("amministratore"))){	//not administrator
				log.severe("Non hai i permessi per effettuare l'operazione");
				request.setAttribute("error", "Non hai i permessi per effettuare l'operazione");
				request.getServletContext().getRequestDispatcher("/gu/login.jsp").forward(request, response);
				return;
			}
			else{	//administrator 
				//user to modify recovery
				DBUtente dbUser = new DBUtente();
				Utente userToModify = new Utente();
				userToModify = dbUser.getUtente((String)request.getParameter("oldEmail"));
				//update user to modify
				log.info("modifiche in corso...");
				dbUser.modificaNome(userToModify, request.getParameter("nome"));
				dbUser.modificaCognome(userToModify, request.getParameter("cognome"));
				dbUser.modificaComuneDiNascita(userToModify, request.getParameter("comuneDiNascita"));
				dbUser.modificaProvinciaDiNascita(userToModify, request.getParameter("provinciaDiNascita"));
				dbUser.modificaCodiceFiscale(userToModify, request.getParameter("codiceFiscale"));
				dbUser.modificaEmail(userToModify, request.getParameter("email"));
				dbUser.modificaDataDiNascita(userToModify, CommonMethod.stringToDate(request.getParameter("dataDiNascita")));
				dbUser.modificaPassword(userToModify, request.getParameter("password"));
				dbUser.modificaDipartimento(userToModify, request.getParameter("dipartimento"));
				dbUser.modificaTipologia(userToModify, request.getParameter("tipologia"));
				
				//redirect
				log.info("modifiche apportate...ridirezione alla pagina di amministrazione");
				request.getServletContext().getRequestDispatcher("/gu/admin.jsp").forward(request, response);				
			}
		}catch(Exception e){
			log.severe("Exception: "+e.getMessage());
			e.printStackTrace();
			request.getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
		
	}

}

package it.unisa.vviser;

import it.unisa.vviser.entity.Account;
import it.unisa.vviser.storage.Connessione;

import java.beans.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public login() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		execute(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		execute(request,response);
	}
	protected void execute(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
	String userid = null;
	String pwd = null;
	userid = request.getParameter("eml"); //--raccoglie il parametro eml
	pwd = request.getParameter("pass");  //--raccoglie il parametro pass
	if(userid == null || userid.equalsIgnoreCase("null") ||
			userid.equalsIgnoreCase("")) {
			request.getSession().setAttribute("error", "user id obbligatorio");
			response.sendRedirect("index.jsp");
			return;
			}
	
	if(pwd == null || pwd.equalsIgnoreCase("null") || pwd.equalsIgnoreCase("")) {
		request.getSession().setAttribute("error", "password obbligatoria");
		response.sendRedirect("index.jsp");
		return;
		}
	
	//------------------------------
	Connessione connessione = new Connessione();        
	Connection con = connessione.connetti();

	Statement st = null;
	ResultSet rs = null;

	try {   //--------compone la query e la esegue         
		String query="SELECT * FROM account where username=" + userid;
		st = (Statement) con.createStatement();
		rs = ((java.sql.Statement) st).executeQuery(query);
		int id=0;
		String userName="";
		String password="";
		if (rs.next()){
			Account account=new Account();
			account.setNome(rs.getString("nome"));
			account.setCognome(rs.getString("cognome"));
			account.setUserName(rs.getString("username"));
			account.setPassword(rs.getString("password"));
			request.getSession().setAttribute("utenteConnesso", account);
			request.getSession().setAttribute("account", account);
			response.sendRedirect("home.jsp");
			request.setAttribute("account", account);
			try{
				request.getRequestDispatcher("/home.jsp").include(request,response);
				}catch(ServletException e) {}			
		}
		else { //------------Login fallita
			request.getSession().setAttribute("error", "login error");
			response.sendRedirect("login.jsp");
			}
	
	connessione.liberaRisorse(con, (java.sql.Statement) st, rs);
	} catch (SQLException e) { 
		System.out.println("errore in lettura dati");
		}
	}	
}

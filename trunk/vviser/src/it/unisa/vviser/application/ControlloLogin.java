package it.unisa.vviser.application;

import it.unisa.vviser.entity.Account;
import it.unisa.vviser.storage.Connessione;
import it.unisa.vviser.storage.DBNames;

import java.beans.Statement;
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
 * Servlet implementation class ControlloLogin
 */
@WebServlet("/ControlloLogin")
public class ControlloLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlloLogin() {
        super();
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
		String query="SELECT * FROM utente where username=" + userid + " and password="+pwd;
		st = (Statement) con.createStatement();
		rs = ((java.sql.Statement) st).executeQuery(query);
		int id=0;
		String userName="";
		String password="";
		if (rs.next()){
			Account account=new Account();
			
			account.setCodiceFiscale(rs.getString(DBNames.ATTR_UTENTE_CODICEFISCALE));
			account.setCognome(rs.getString(DBNames.ATTR_UTENTE_COGNOME));
			account.setComuneDiNascita(rs.getString(DBNames.ATTR_UTENTE_COMUNEDINASCITA));
			account.setDataDiNascita(rs.getString(DBNames.ATTR_UTENTE_DATADINASCITA));
			account.setDipartimento_Nome(rs.getString(DBNames.ATTR_UTENTE_DIPARTIMENTO_NOME));
			account.setEmail(rs.getString(DBNames.ATTR_UTENTE_EMAIL));
			account.setNome(rs.getString(DBNames.ATTR_UTENTE_NOME));
			account.setPassword(rs.getString(DBNames.ATTR_UTENTE_PASSWORD));
			account.setProvinciaDiNascita(rs.getString(DBNames.ATTR_UTENTE_PROVINCIADINASCITA));
			account.setTipologia(rs.getString(DBNames.ATTR_UTENTE_TIPOLOGIA));
			account.setUserName(rs.getString(DBNames.ATTR_UTENTE_USERNAME));
			
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

package it.unisa.vviser.servlet;



import it.unisa.vviser.storage.DBConnectionPool;
import it.unisa.vviser.storage.DBNames;

import java.sql.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControlloLogin
 */
@WebServlet("/ControlloLogin")
public class ControlloLogin_rimuovere extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlloLogin_rimuovere() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			execute(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			execute(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException
	{
	String email = null;
	String pwd = null;
	email = request.getParameter("email"); //--raccoglie il parametro eml
	pwd = request.getParameter("pass");  //--raccoglie il parametro pass
	//System.out.println("username="+userName);
	//System.out.println("password="+pwd);
	if(email == null || email.equalsIgnoreCase("null") ||
			email.equalsIgnoreCase("")) {
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
	//Connessione connessione = new Connessione();      
	//Connection con = connessione.connetti();
	Connection conn = null;//connessione.connetti();
	conn = DBConnectionPool.getConnection();
	Statement st = null;
	ResultSet rs = null;

	try {   //--------compone la query e la esegue         
		String query="SELECT * FROM "+DBNames.TABLE_UTENTE+" where "+DBNames.ATTR_UTENTE_EMAIL+"='" + email + "' and "+DBNames.ATTR_UTENTE_PASSWORD+"='"+pwd+"'";
		System.out.println("query="+query);
		st=conn.createStatement();
		rs = st.executeQuery(query);
		if (rs.next()){
//			
//			Utente account=new Utente();
//			account.setNome(rs.getString(DBNames.ATTR_UTENTE_NOME));
//			account.setCognome(rs.getString(DBNames.ATTR_UTENTE_COGNOME));
//			account.setComuneDiNascita(rs.getString(DBNames.ATTR_UTENTE_COMUNEDINASCITA));
//			//account.setDataDiNascita(rs.getDate(DBNames.ATTR_UTENTE_DATADINASCITA));
//			account.setDipartimento(rs.getString(DBNames.ATTR_UTENTE_DIPARTIMENTO_NOME));
//			account.setEmail(rs.getString(DBNames.ATTR_UTENTE_EMAIL));
//			account.setCodiceFiscale(rs.getString(DBNames.ATTR_UTENTE_CODICEFISCALE));
//			account.setPassword(rs.getString(DBNames.ATTR_UTENTE_PASSWORD));
//			account.setProvinciaDiNascita(rs.getString(DBNames.ATTR_UTENTE_PROVINCIADINASCITA));
//			account.setTipologia(rs.getString(DBNames.ATTR_UTENTE_TIPOLOGIA));
//			//account.setUserName(rs.getString(DBNames.ATTR_UTENTE_USERNAME));
//			//account.setID(rs.getString(DBNames.ATTR_UTENTE_id));
//			
//			System.out.println("setNome= "+account.getNome());
//			//getServletContext().setAttribute("account", account);
//			//request.getSession().setAttribute("utenteConnesso", account);
//			request.getSession().setAttribute("account", account);
//			//response.sendRedirect("main/testLogin.jsp");
//			request.setAttribute("account", account);
//			try{
//				RequestDispatcher dispatcher =request.getRequestDispatcher("main/testok.jsp");//.include(request,response);
//				dispatcher.forward( request, response );
//				}catch(ServletException e) {}			
		}
		else { //------------Login fallita
			try{
			request.getSession().setAttribute("error", "login error");
			RequestDispatcher dispatcher =request.getRequestDispatcher("main/login.jsp");
			dispatcher.forward( request, response );
			}catch(ServletException e) {}
			}
	
	//connessione.liberaRisorse(con, (java.sql.Statement) st, rs);
		DBConnectionPool.releaseConnection(conn);
	} catch (SQLException e) { 
		System.out.println("errore in lettura dati");
		}
	}
}

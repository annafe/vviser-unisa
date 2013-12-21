<%@page import="java.sql.ResultSet"%>
<%@page import="it.unisa.vviser.storage.DBNames"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@page import="it.unisa.vviser.storage.DBConnectionPool"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrazione Utente</title>
<script type="text/javascript">
	function testPass(modulo){
	  if (modulo.password.value != modulo.password_2.value) {
	    alert("Le password non corrispondono!");
	    return false;
	  }
	  return true;
	}
</script>
</head>
<body>
	<%@include file="/gu/header.jsp" %>
	
	<% 
 	//check permission
 	try{
	 	Utente admin = (Utente)session.getAttribute("utente");
	 	if (!(admin.getTipologia().equalsIgnoreCase("amministratore"))){
			request.setAttribute("error", "Non hai i permessi per effettuare l'operazione");
			if(!response.isCommitted()){	//header can alredy forward response
				request.getRequestDispatcher("/gu/login.jsp").forward(request, response);	
				return;		
			}
	 	}
 	}catch(Exception e){
		request.setAttribute("error", "Sessione non settata");
		if(!response.isCommitted()){	//header can alredy forward response
			request.getRequestDispatcher("/gu/login.jsp").forward(request, response);	
			return;		
		}
 	}
	%>
	<fieldset>
		<legend>Registarzione Utente</legend>
		<form action="/vviser/AddUtenteServlet" method="POST" onsubmit="return testPass(this)">
			<p><label>Nome: <input type="text" name="nome" required/></label></p>
			<p><label>Cognome: <input type="text" name="cognome" required/></label></p>
			<p><label>Email: <input type="text" name="email" required/></label></p>
			<p><label>Password: <input type="password" name="password" required/></label></p>
			<p><label>Conferma la password: <input type="password" name="password_2" required/></label></p>
			<p>Data di Nascita: 
			<input type="text" name="giornoNascita" placeholder="Giorno"required/>
			<select id="mese" name="meseNascita">
				<option value="01">Gennaio</option>
				<option value="02">Febbraio</option>
				<option value="03">Marzo</option>
				<option value="04">Aprile</option>
				<option value="05">Maggio</option>
				<option value="06">Giugno</option>
				<option value="07">Luglio</option>
				<option value="08">Agosto</option>
				<option value="09">Settembre</option>
				<option value="10">Ottobre</option>
				<option value="11">Novembre</option>
				<option value="12">Dicembre</option>
			</select>
			<input type="text" name="annoNascita" placeholder="Anno"required/>
			</p>
			<p><label>Comune di nascita: <input type="text" name="comunedinascita" required/></label></p>
			<p><label>Provincia di nascita: <input type="text" name="provinciadinascita" 
			pattern="[a-z A-Z][a-z A-Z]" size="2" required/></label></p>
			<p><label>Codice Fiscale: <input type="text" name="codicefiscale" 
			pattern="[a-zA-Z]{6}[0-9]{2}[a-zA-Z][0-9]{2}[a-zA-Z][0-9]{3}[a-zA-Z]"
			size="16" required/></label></p>
			<p><label>Dipartimento: 
				<select id="dipartimento" name="dipartimento">
				<%
					try{
						Connection conn = DBConnectionPool.getConnection();
						String query = "SELECT "+DBNames.ATTR_DIPARTIMENTO_NOME
									+" FROM "+DBNames.TABLE_DIPARTIMENTO;
						PreparedStatement st = conn.prepareStatement(query);
						ResultSet rs = st.executeQuery();
						
						while(rs.next()){
							out.print("<option value="); 
							out.print("\""+rs.getString(DBNames.ATTR_DIPARTIMENTO_NOME)+"\">");
							out.print(rs.getString(DBNames.ATTR_DIPARTIMENTO_NOME));
							out.print("</option>");
						}
						st.close();
						DBConnectionPool.releaseConnection(conn);
					}catch(SQLException e){
						e.printStackTrace();
					}
				%>
				</select>
			</label></p>
			<p><label>Tipologia:
				<select id="tipologia" name="tipologia">
					<option value="amministratore">Amministratore</option>
					<option value="ricercatore">Ricercatore</option>
					<option value="direttoreDiDipartimento">Direttore di dipartimento</option>
					<option value="membroDelComitatoDiAreaDidattica">Membro del comitato di area didattica</option>
					<option value="membroDelComitatoDiAteneo">Membro del comitato di Ateneo</option>
				</select>
				</label></p>
			<p><input type="submit"/>
			<input type="reset"/></p>
		</form> 
	</fieldset>
</body>
</html>
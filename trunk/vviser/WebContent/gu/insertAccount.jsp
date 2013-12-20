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
</head>
<body>
	<%@include file="/gu/header.jsp" %>
	<fieldset>
		<legend>Registarzione Utente</legend>
		<form action="/vviser/AddUtenteServlet" method="POST">
			<p><label>Nome: <input type="text" name="nome"/></label></p>
			<p><label>Cognome: <input type="text" name="cognome"/></label></p>
			<p><label>Comune di nascita: <input type="text" name="comunedinascita"/></label></p>
			<p><label>Provincia di nascita: <input type="text" name="provinciadinascita"/></label></p>
			<p><label>Codice Fiscale: <input type="text" name="codicefiscale"/></label></p>
			<p><label>Email: <input type="text" name="email"/></label></p>
			
			<!-- Provvisorio -->
			<p><label>Data di Nascita (Provvisorio): <input type="date" name="datadinascita"/></label></p>
			<p><label>Password: <input type="password" name="password"/></label></p>
			<p><label>Reinserisci password (Provvisorio): <input type="password" name="password1"/></label></p>
			<!-- Fine provvisorio -->
			
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
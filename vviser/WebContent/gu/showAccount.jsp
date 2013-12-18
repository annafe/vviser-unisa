<%@page import="java.lang.ProcessBuilder.Redirect"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="it.unisa.vviser.storage.DBUtente"%>
<%@ page import="it.unisa.vviser.entity.Utente"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visualizza utenti</title>
</head>
<body>
<!-- 
	<table border=1>
		<% 	
			for (int i=0; i<10; i++){
				%><tr><td><%out.println(i+"");%></td></tr> <% 
			}
		%>
	</table>
 -->
 <%
 	session.setAttribute("email", "prova");
 	if (session.getAttribute("email")!=null){
 		//session present
 		
 	}
 	else{
 		response.sendRedirect("/error.jsp");
 	}
 %>
 <table border=1>
 	<tr><th>COGNOME</th><th>NOME</th><th>EMAIL</th><th>TIPOLOGIA</th></tr>
		<% 	
			DBUtente dbUser = new DBUtente();
			List<Utente> l = dbUser.visualizzaUtenti();
			for (int i=0; i<l.size(); i++){
				%><tr><td><%out.println(l.get(i).getCognome());%></td><td><%out.println(l.get(i).getNome()); %></td><td><%out.println(l.get(i).getEmail()); %></td><td><%out.println(l.get(i).getTipologia()); %></td>
				</tr> 
				<% 
			}
		%>
</table>
</body>
</html>
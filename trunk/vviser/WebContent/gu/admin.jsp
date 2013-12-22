<!-- ROMANO SIMONE -->
<%@ page import="java.lang.ProcessBuilder.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="it.unisa.vviser.storage.DBUtente"%>
<%@ page import="it.unisa.vviser.entity.Utente"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin</title>
</head>
<body>
 <%@include file="/gu/header.jsp" %>
 
 <script type="text/javascript">
 	function elimina(input){
 		form = input.parentNode;
 		var r=confirm("Vuoi eliminare l'utente?");
 		if (r==true){
 			form.submit();
 		}
 		else{
 		  return;
 		}
 	}
 </script>
 <h1>ADMIN PAGE</h1>
 <table border=1>
 	<tr><th>COGNOME</th><th>NOME</th><th>EMAIL</th><th>DIPARTIMENTO</th><th>TIPOLOGIA</th></tr>
		<% 	
			DBUtente dbUser = new DBUtente();
			List<Utente> l = dbUser.visualizzaUtenti();
			for (int i=0; i<l.size(); i++){
				%><tr><td><%out.println(l.get(i).getCognome());%></td><td><%out.print(l.get(i).getNome()); %></td><td><%out.print(l.get(i).getEmail()); %></td><td><%out.print(l.get(i).getDipartimento()); %></td><td><%out.print(l.get(i).getTipologia()); %></td>
				<td><!-- form modifica -->
					<form action="/vviser/gu/modifyAccount.jsp" method="POST">		
						<input type="hidden" name="daModificare" value="<%out.print(l.get(i).getEmail());%>"/>			
						<input type="submit" value="Modifica"/>
					</form>
				</td>
				<td><!-- form elimina -->
					<form action="/vviser/DeleteAccountServlet" method="POST">
						<input type="hidden" name="daEliminare" value="<%out.print(l.get(i).getEmail());%>"/>
						<input type="button" onclick="elimina(this)" value="Elimina"/>
					</form>				
				</td>
				</tr> 
				<% 
			}
		%>
</table>

<a href="/vviser/gu/insertAccount.jsp">Inserisci Account</a>
</body>
</html>
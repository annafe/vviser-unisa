<%-- 
    Author: Giuseppe Sabato
--%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="it.unisa.vviser.entity.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="org.json.JSONObject"%>
<%@ page session="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<title>Seleziona Utente</title>
</head>
<body>
<%
	ArrayList<Utente> utenti=new ArrayList<Utente>();
	utenti=(ArrayList<Utente>)request.getAttribute("listaUtenti");
	
	HttpSession s=request.getSession();
	s.setAttribute("visualizza","tutti");
%>

<form id="ssl" action="/vviser/ServletVerificaListeVisualizza" method="POST">
	<table>
		<tr>
			<th colspan="3">Scegli un Utente</th>
		</tr>
		<tr>
			<th>Scegli</th>
			<th>Nome</th>
			<th>Cognome</th>
		</tr>
		<%
			for(int i=0;i<utenti.size();i++)
			{
				out.println("<tr>");
					out.println("<td><input type=\"radio\" name=\"utente\" value="+utenti.get(i).getEmailNomeCognomeUtente()+" /></td>");
					out.println("<td>"+utenti.get(i).getNome()+"</td>");
					out.println("<td>"+utenti.get(i).getCognome()+"</td>");
				out.println("<tr>");
			}
			
		%>
	</table>
	<button class="pulsante" type="submit" name="invia">Conferma</button>
</form>
</body>
</html>
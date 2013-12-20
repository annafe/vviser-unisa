<%-- 
    Author: Giuseppe Sabato
--%>
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
    <title>Seleziona una Lista</title>
</head>

<body>

<%
	ArrayList<ListaProdottiValutazione> listeProdottiValutazione=new ArrayList<ListaProdottiValutazione>();
	listeProdottiValutazione=(ArrayList<ListaProdottiValutazione>)request.getAttribute("liste");
%>
<form id="f1" action="ServletSelezionaListeModifica" method="POST">
	<table>
		<tr>
			<th colspan="2">Scegli una lista</th>
		</tr>
		<tr>
			<th>Scegli</th>
			<th>Id Evento</th>
		</tr>
		<%
			for(int i=0;i<listeProdottiValutazione.size();i++)
			{
				out.println("<tr>");
					out.println("<td><input type=\"radio\" name=\"list\" value="+listeProdottiValutazione.get(i).toString()+"/></td>");
					out.println("<td>"+listeProdottiValutazione.get(i).getIdEventoValutazione()+"</td>");
				out.println("<tr>");
			}
			
		%>
	</table>
	<button type="submit" name="conf">Conferma</button>
</form>



</body>
</html>
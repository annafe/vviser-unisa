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
	ListaProdottiValutazione listaProdottiValutazione=new ListaProdottiValutazione();
	listaProdottiValutazione=(ListaProdottiValutazione)request.getAttribute("lista");
	
%>

<table>
	<tr>
		<th colspan="2">Lista prodotti sottomessi a valutazione</th>
	</tr>
	<tr>
		<th>Titolo</th>
		<th>Priorita'</th>
	</tr>
	<%
		ArrayList<ProdottoValutazione> prodottiValutazione=listaProdottiValutazione.getListaProdottiValutazione();
		for(int i=0;i<prodottiValutazione.size();i++)
		{
			out.println("<tr>");
				out.println("<td>"+prodottiValutazione.get(i).getTitle()+"</td>");
				out.println("<td>"+prodottiValutazione.get(i).getPriority()+"</td>");
			out.println("<tr>");
		}
		
	%>
</table>




</body>
</html>
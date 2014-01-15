<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%-- 
    Author: Antonio De Piano
--%>
    <%@ page import="it.unisa.vviser.storage.DBGestioneProdotto"%>
<%@ page import="it.unisa.vviser.entity.*"%>
<%@ page import="java.util.*"%>
<%@page import="it.vviser.common.CommonMethod"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title></title>
<style>
table{padding-top:5%;}
	th{color:ORANGERED;}
	td{text-align:center;}
</style>
</head>
<body>
<% 	
			DBGestioneProdotto dbGpr = new DBGestioneProdotto();
			Utente currentUser = (Utente) session.getAttribute("utente");
			List<Prodotto> l = dbGpr.visualizzaProdottiPersonali(currentUser.getEmail());
			if(l.isEmpty())
				out.println("<p></p><p>Non ci sono prodotti</p>");
			else
			{
				%>
				<table>
			 	<tr>
			 	<th>ISBN</th>
			 	<th>TITOLO</th>
			 	<th>DESCRIZIONE</th>
			 	<th>TIPOLOGIA</th>
			 	<th>NOTE</th>
			 	<th>STATO</th>
			 	</tr>
 		<%
			//List<Prodotto> l = dbGpr.visualizzaProdottiInBozza("deufemia@unisa.it");
			for (int i=0; i<l.size(); i++)
			{
				%>
				<tr>	
				<% out.println("<td><a href='/vviser/gpr/ModificaProdottoDiretta.jsp?titolo="+l.get(i).getTitolo()+"&proprietario="+l.get(i).getProprietario()+
				"&annoDiPubblicazione="+CommonMethod.dateToString(l.get(i).getAnnoPubblicazione())+"&tipologia="+l.get(i).getTipologia()+"'>"+l.get(i).getIsbn()+"</a></td>"); %>
					<td><%out.println(l.get(i).getTitolo());%></td>
					<td><%out.println(l.get(i).getDescrizioneContenuti());%></td>
					<td><%out.println(l.get(i).getTipologia());%></td>
					<td><%out.println(l.get(i).getNote());%></td>
					<td>In bozza</td>
				</tr>
				<% 
			}
		%>
</table>
<% } %>
</body>
</html>
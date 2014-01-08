<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%-- 
    Author: Antonio De Piano
--%>
    <%@ page import="it.unisa.vviser.storage.DBGestioneProdotto"%>
<%@ page import="it.unisa.vviser.entity.Prodotto"%>
<%@ page import="java.util.*"%>
<%@page import="it.vviser.common.CommonMethod"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title></title>
<style>
table{overflow:auto;
	padding-top:5%;}
	th{color:ORANGERED;}
	td{text-align:center;}
</style>
</head>
<body>
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
			DBGestioneProdotto dbGpr = new DBGestioneProdotto();
			//Utente currentUser = (Utente) session.getAttribute("utente");
			//List<Prodotto> l = dbGpr.visualizzaProdottiPersonali(currentUser.getEmail());
			List<Prodotto> l = dbGpr.visualizzaProdottiInBozza("deufemia@unisa.it");
			for (int i=0; i<l.size(); i++)
			{
				%>
				<tr>
				<% out.println("<td><a href='gpr_dettaglio_prod.jsp?isbn="+l.get(i).getIsbn()+"'>"+l.get(i).getIsbn()+"</a></td>"); %>
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
</body>
</html>
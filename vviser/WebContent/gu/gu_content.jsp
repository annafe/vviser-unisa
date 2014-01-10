<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%-- 
    Author: Antonio De Piano
--%>
    <%@ page import="it.unisa.vviser.storage.*"%>
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
	fieldset{width:20%;}
</style>
</head>
<body>
<fieldset>
<legend>Dati personali</legend>
	<table>
	<tr>
	<th>Nome</th>
	<td><%	 Utente currentUser = (Utente) session.getAttribute("utente");
		out.println(currentUser.getNome());
		%>
		</td>
	</tr>
	
	<tr>
	<th>Cognome</th>
	<td><% out.println(currentUser.getCognome());
		%>
		</td>
	</tr>
	<tr>
	<th>E-mail</th>
	<td><%
		out.println(currentUser.getEmail());
		%>
		</td>
	</tr>
	
	<tr>
	<th>CF</th>
	<td><% out.println(currentUser.getCodiceFiscale());
		%>
		</td>
	</tr>
	
	<tr>
	<th>Comune di nascita</th>
	<td><% out.println(currentUser.getComuneDiNascita());
		%>
		</td>
	</tr>
	
	<tr>
	<th>Provincia di nascita</th>
	<td><% out.println(currentUser.getProvinciaDiNascita());
		%>
		</td>
	</tr>
	
	<tr>
	<th>Tipologia di utente</th>
	<td><% out.println(currentUser.getTipologia());
		%>
		</td>
	</tr>
	
	<tr>
	<th>Dipartimento</th>
	<td><% out.println(currentUser.getDipartimento());
		%>
		</td>
	</tr>
	</table>
</fieldset>
</body>
</html>
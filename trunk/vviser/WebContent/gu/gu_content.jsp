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
</style>
</head>
<body>
Info del ricercatore<br/>
questo sotto lo devo cancellare
<% 	
			Utente currentUser = (Utente) session.getAttribute("utente");
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
</table>
</body>
</html>
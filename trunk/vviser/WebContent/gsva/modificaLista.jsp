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
    <title>Modifica Lista</title>
</head>

<body>

<%
	ListaProdottiValutazione listaProdottiValutazione=new ListaProdottiValutazione();
	listaProdottiValutazione=(ListaProdottiValutazione)request.getAttribute("lista");
	
	
		//gestire in seguito nel momento in cui viene sbloccata la lista
%>



</body>
</html>
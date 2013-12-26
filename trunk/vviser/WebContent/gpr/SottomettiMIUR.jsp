<%@ page import="java.lang.ProcessBuilder.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>

<%@ page import="it.unisa.vviser.storage.DBUtente"%>
<%@ page import="it.unisa.vviser.entity.Utente"%>
<%@ page import="it.unisa.vviser.storage.DBGestioneProdotto"%>
<%@ page import="it.unisa.vviser.entity.Prodotto"%>
<%-- 
    Author: Antonio De Piano
--%>
<html>
<head>
    <title>VViSeR-MIUR</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/stile.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
body{height:620px;width:850px;}
header#container-header{width:850px;}
nav{width:850px;}
section#container-section{height:140px;width:850px;}
footer#container-footer{top:580px;width:850px;}
section#login{width:150px;left:30px;height:80px;top:30px;}
section#search-prod{width:150px;left:190px;height:80px;top:30px;}
section#documenti{width:150px;left:350px;height:80px;top:30px;}
section#logout{position:absolute;width:150px;left:670px;height:80px;top:30px;background-color:blue;}
section#profilo{width:850px;}
</style>
</head>
<body>
<header id="container-header">
	<header id="header-main"></header>
	<nav>
		today is 28 Settembre 2013	
	</nav>
</header>
<section id="container-section">
	<section id="login">
		<a href="../gpr/gpr.html">Ricerca Prodotto Personale</a>
	</section>
	<section id="search-prod">
		<a href="main/search_prod.jsp">Gestione Prodotto</a>
	</section>
	<!--  
		<section id="documenti">
			<a href="main/doc.jsp">Gestione Validazione</a>
		</section>
		<section id="notifiche">
			<a href="./notifiche.html">Notifiche</a>
		</section>
		<section id="logout">
			<a href="../index.html">Logout</a>
		</section>
	 -->
</section>

<section id="profilo">
<table>
<tr>
<th>Titolo</th>
<th>Descrizione</th>
<th>Stato</th>
</tr>
<%
DBGestioneProdotto dbgp=DBGestioneProdotto.getInstance();

//Utente currentUser = (Utente) session.getAttribute("utente");
//currentUser.getEmail()
ArrayList<Prodotto> l = dbgp.visualizzaProdottiProprietarioCoautore("deufemia@unisa.it");
for(int i=0;i<l.size();i++)
{
	%>
	<tr><td><% l.get(i).getTitolo(); %></td><td><% l.get(i).getDescrizioneContenuti(); %></td><td> <% l.get(i).getStato(); %></td></tr>
	<%
}
%></table>
</section>
<footer id="container-footer">
TEAM EIGHT
</footer>
</body>
</html>

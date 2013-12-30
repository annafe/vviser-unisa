<%@ page import="java.lang.ProcessBuilder.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%-- 
    Author: Antonio De Piano
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>VViSeR</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/stile.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
body{width:700px;height:710px;}
header#container-header{
	height: 76px;
	width: 220px;
	top: 7px;
}
header#header-main
{
	width: 895px;
	height: 128px;
	background-image: url(../images/wisernobook.png);
	background-repeat: no-repeat;
	background-position: left bottom;
	background-size: 10%;
}

nav{
	width: 882px;
	height: 30px;
	top: 143px;
	text-align: left;
}

section#container-section{
	top: 200px;
	height: 637px;
	width: 162px;
}
footer#container-footer{
	top: 850px;
	width: 882px;
}
section#login{
	width: 150px;
	left: 6px;
	height: 80px;
	top: 12px;
}
section#search-prod{
	width: 150px;
	left: 6px;
	height: 80px;
	top: 102px;
}
section#documenti{
	width: 150px;
	left: 6px;
	height: 80px;
	top: 192px;
}
section#profilo{
	width: 700px;
	top: 248px;
	height: 532px;
}
section#profilo>table{text-align:center;color:black;width:700px;padding-left:40px;}
th{background-color:tomato;color:white;}
section#notifiche{
	left: 6px;
}
section#elimina-prod{
	width: 150px;
	left: 6px;
	height: 80px;
	top: 552px;
}
section#convalida{
	width: 150px;
	left: 6px;
	height: 80px;
	top: 372px;
}
section#pro-utente{
	position: absolute;
	width: 150px;
	left: 6px;
	height: 80px;
	top: 462px;
	background-color: lightblue;
}
</style>
</head>
<body>
<% 
/*@include file="../gu/header.jsp" */
%>
<header id="container-header">
	<header id="header-main"></header>
	<nav>
			
	</nav>
</header>
<section id="container-section">
	<section id="login">
		<a href="../gpr/insert.jsp">Inserisci Prodotto</a>
  </section>
	<section id="search-prod">
		<a href="main/search.html">Ricerca Prodotto</a>
	</section>
	<section id="documenti">
		<a href="main/my_prod.html">I tuoi prodotti</a>
	</section>
	<section id="notifiche">
		<a href="./sottometti_miur.jsp">Sottometti al MIUR</a>
	</section>
	<section id="elimina-prod">
		<a href="./delete.html">Elimina prodotto</a>
	</section>
	<section id="convalida">
		<a href="./convalida.jsp">Convalida prodotto</a>
	</section>
	<section id="pro-utente">
		<a href="../gu/gu.html">Profilo utente</a>
	</section>
</section>

<section id="profilo">
<table>
<tr>
	<th>Prodotto</th>
	<th>Stato</th>
</tr>
<tr>
	<td>Descrizione del prodotto</td>
	<td>Definitivo/Provvisorio IMG</td>
</tr>
</table>
</section>
<footer id="container-footer">
TEAM EIGHT
</footer>
</body>
</html>

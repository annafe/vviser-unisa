<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>VViSeR</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/stile.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
body{width:1000px;height:630px;}
header#container-header{height:70px;
			width:1000px;
			top:10px;}
header#header-main
{
	width:1000px;
	height:70px;
	background-image: url('../images/logo.png');
	background-repeat:no-repeat;
	background-position:left bottom;
	background-size:10%;
}

nav{
width:1000px;
height:30px;
top:80px;
text-align:left;
}

section#container-section{top:130px;height:140px;width:1000px;}
footer#container-footer{top:590px;width:1000px;}
section#login{width:150px;left:20px;height:80px;top:30px;}
section#search-prod{width:150px;left:180px;height:80px;top:30px;}
section#documenti{width:150px;left:340px;height:80px;top:30px;}
section#profilo{width:1000px;top:270px;height:300px;}
section#profilo>table{text-align:center;color:black;width:940px;padding-left:40px;}
th{background-color:tomato;color:white;}
section#notifiche{left:500px;}
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
		<a href="../gpr/insert.html">Inserisci Prodotto</a>
	</section>
	<section id="search-prod">
		<a href="main/search.html">Ricerca Prodotto</a>
	</section>
	<section id="documenti">
		<a href="main/my_prod.html">I tuoi prodotti</a>
	</section>
	<section id="notifiche">
		<a href="./sottometti_miur.html">Sottometti al MIUR</a>
	</section>
	<section id="elimina-prod">
		<a href="./delete.html">Elimina prodotto</a>
	</section>
	<section id="convalida">
		<a href="./convalida.html">Convalida prodotto</a>
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

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>VViSeR</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/stile.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
body{height:620px;width:850px;border:5px solid red;}
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
		<a href="../gpr/gpr.html">Gestione Prodotto</a>
	</section>
	<section id="search-prod">
		<a href="main/search_prod.jsp">Gestione Valutazione</a>
	</section>
	<section id="documenti">
		<a href="main/doc.jsp">Gestione Validazione</a>
	</section>
	<section id="notifiche">
		<a href="./notifiche.html">Notifiche</a>
	</section>
	<section id="logout">
		<a href="../index.html">Logout</a>
	</section>
</section>

<section id="profilo">
Informazioni del ricercatore
</section>
<footer id="container-footer">
TEAM EIGHT
</footer>
</body>
</html>

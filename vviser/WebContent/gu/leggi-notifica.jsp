
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><html>
<head>
    <title>VViSeR</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/stile.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
body{color:white;height:670px;}
input#x.pulsante{background-color:TOMATO;}
section#container-section{height:400px;top:220px; background-color:yellowgreen;}
footer#container-footer{top:630px;}
table{color:white;
	text-align:left;
	font-size:12pt;}
fieldset{border:5px solid orangered;height:350px;text-align:left;}
section#search-catal{height:290px;}
section#mod-search{height:290px; width:530px; left:160px;padding-right:20px;}
a.not:link {color:yellow;
	text-decoration:none;}
p{display:block;}
fieldset>p{font-size:12pt;color:SEAGREEN;}
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
	<section id="search-catal">
		<p><a href="./gu.html">Profilo utente</a></p>
		<p><a href="./notifiche.html">Box notifiche</a></p>
	</section>
	
	<section id="mod-search">
			<form method="GET" action="#" name="modulo">
	<fieldset>
		<legend>Notifica</legend>
	<p>Mittente</p>
	<p>Data e ora invio</p>
	<p>Oggetto</p>
	<p>Messaggio stampato</p>
	</fieldset>
	</form>
	</section>
</section>
<footer id="container-footer">
TEAM EIGHT
</footer>
</body>
</html>

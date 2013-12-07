<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><html>
<head>
    <title>VViSeR</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/stile.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
body{color:white;}
nav{padding-top:0px;
	background-color:white;
	text-align:left;}
input#x.pulsante{background-color:TOMATO;}
section#container-section{height:290px;top:220px; background-color:yellowgreen;}
footer#container-footer{top:520px;}
table{color:white;
	text-align:left;
	font-size:12pt;}
fieldset{border:5px solid orangered;}
section#search-catal{height:290px;}
section#mod-search{height:290px; width:530px; left:160px;padding-right:20px;}
</style>
</head>
<body>
<header id="container-header">
	<header id="header-main"></header>
	<nav>
		<input type="button" value="Home" class="pulsante" onclick="document.location.href='../index.jsp';">
		<input type="button" value="Documenti" class="pulsante" onclick="document.location.href='./doc.jsp';">
		<input type="button" value="Presentazione" class="pulsante" onclick="document.location.href='./presentazione.jsp';">
		<input type="button" value="F.A.Q" class="pulsante" onclick="document.location.href='./faq.jsp';">
		<input type="button" value="altro.." class="pulsante" onclick="document.location.href='./altro.jsp';">
	</nav>
</header>
<section id="container-section">
	<section id="search-catal">
		Catalogo Prodotti
	</section>
	
	<section id="mod-search">
			<form method="GET" action="#" name="modulo">
	<fieldset>
		<legend>Ricerca</legend>
	<table>
			<tr>
				<td>Tipologia</td>
				<td><select name="tipologia" class="sel"></select></td>
			</tr>
			<tr>
				<td colspan="2">Anno dal <select name="anno" class="sel"></select> al <select name="anno1" class="sel"></select></td>
			</tr>
			<tr>
				<td>Titolo prodotto</td>
				<td><input type="text" name="titolo_prod" class="testo"></td>
			</tr>
			<tr>
				<td>Titolo rivista</td>
				<td><input type="text" name="titolo_riv" class="testo"></td>
			</tr>
			<tr>
				<td>ISSN rivista</td>
				<td><input type="text" name="issn_riv" class="testo"></td>
			</tr>
			<tr>
				<td colspan="2"><a href="#" style="color:orangered;font-size:10pt;">Compila almeno un campo della ricerca</a></td>
			</tr>
			<tr>
				<td colspan="2"><div class="centro"><input type="submit" value=" Ricerca " class="pulsante" style="background-color:lightgreen;"><input type="reset" value=" Annulla " class="pulsante" style="background-color:lightgreen;"></div></td>
			</tr>
			
		</table>
	</fieldset>
	</form>
	</section>
</section>
<footer id="container-footer">
TEAM EIGHT
</footer>
</body>
</html>

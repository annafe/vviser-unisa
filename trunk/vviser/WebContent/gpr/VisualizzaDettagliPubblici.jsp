<%@page import="java.util.ArrayList"%>
<%@page import="it.unisa.vviser.entity.Prodotto"%>
<%@page import="java.util.Calendar"%>
<%@page import="it.unisa.vviser.entity.Tipologia"%>
<%@page import="java.util.List"%>
<%@page import="it.unisa.vviser.storage.DBTipologie"%>
<%@ page import="it.unisa.vviser.storage.DBGestioneProdotto"%>
<%@ page import="it.vviser.common.*"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>VViSeR</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/vviser/css/stile.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
	body{color:white;}
	nav{
		padding-top:0px;
		background-color:white;
		text-align:left;
	}
	input#x.pulsante{background-color:TOMATO;}
	section#container-section{height:290px;top:220px; background-color:yellowgreen; overflow:auto;}
	footer#container-footer{top:520px;}
	table{
		color:white;
		text-align:left;
		font-size:12pt;
	}
	fieldset{border:5px solid orangered;}
	section#search-catal{height:290px;}
	section#mod-search{height:290px; width:530px; left:160px;padding-right:20px;}
	#risultati{
		position:absolute;
		top:180px;
		left:160px;
		width:calc(100% - 165px);
		height:100px;
	}
	#risultati>table{
		width:100%;
	}
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
				
	<h4>DETTAGLI PRODOTTO</h4>
	<%
		//recupero prodotto
		Prodotto prodotto = (Prodotto)request.getAttribute("prodotto");
	%>
	<table border=0>
	<tr><th>TITOLO</th><td><%out.print(prodotto.getTitolo()); %></td></tr>
	<tr><th>ISBN</th><td><%out.print(prodotto.getIsbn()); %></td></tr>
	<tr><th>PROPRIETARIO</th><td><%out.print(prodotto.getProprietario()); %></td></tr>
	<tr><th>ANNO PUBBLICAZIONE</th><td><%out.print(CommonMethod.dateToString(prodotto.getAnnoPubblicazione())); %></td></tr>
	<tr><th>FORMATO</th><td><%out.print(prodotto.getFormatoPubblicazione()); %></td></tr>
	<tr><th>DOI</th><td><%out.print(prodotto.getCodiceDOI()); %></td></tr>
	<tr><th>DIFFUSIONE</th><td><%out.print(prodotto.getDiffusione()); %></td></tr>
	<tr><th>COLLABORATORI</th><td><%out.print(prodotto.getListaCollaboratori()); %></td></tr>
	<tr><th>DESCRIZIONE</th><td><%out.print(prodotto.getDescrizioneContenuti()); %></td></tr>
	<tr><th>INDIRIZZO WEB</th><td><%out.print(prodotto.getIndirizzoWeb()); %></td></tr>
	<tr><th>PAROLE CHIAVE</th><td><%out.print(prodotto.getParoleChiavi()); %></td></tr>
	<tr><th>EDITORE</th><td><%out.print(prodotto.getEditore()); %></td></tr>
	<tr><th>NUMERO VOLUME</th><td><%out.print(prodotto.getNumVolume()); %></td></tr>
	<tr><th>TOTALE PAGINE</th><td><%out.print(prodotto.getTotalePagine()); %></td></tr>
	<tr><th>DA PAGINA</th><td><%out.print(prodotto.getDaPagina()); %></td></tr>
	<tr><th>A PAGINA</th><td><%out.print(prodotto.getApagina()); %></td></tr>
	<tr><th>NOTE</th><td><%out.print(prodotto.getNote()); %></td></tr>
	<tr><th>STATO</th><td><%out.print(prodotto.getStato()); %></td></tr>
	<tr><th>BOZZA</th><td><%out.print(prodotto.getBozza()); %></td></tr>
	<tr><th>TIPOLOGIA</th><td><%out.print(prodotto.getTipologia()); %> </td></tr>
	</table>
			
</section>
	
<footer id="container-footer">
TEAM EIGHT
</footer>
</body>
</html>

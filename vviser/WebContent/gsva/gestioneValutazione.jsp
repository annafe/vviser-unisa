<%-- 
    Author: Giuseppe Sabato
--%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="it.unisa.vviser.entity.*"%>
<!DOCTYPE html>
<html>
<head>
<!--meta charset="UTF-8"/-->
<title>Sottometti Prodotti</title>
<script type="text/javascript">
	//viualizza le opportune funzionalita' a seconda del tipo di utente
	function controlShowButton()
	{
		tipo=document.getElementById("tipoNasc").value;
		if(tipo=="ricercatore")
			document.getElementById("butVT").hidden=true;	
	}
</script>
<style>
table{padding-top:5%;}
	th{color:ORANGERED;}
	td{text-align:center;}
</style>
</head>
<body>
<%@include file="/gu/header.jsp" %>
<%
	HttpSession s= request.getSession();
	s.setAttribute("sessEmail",utente.getEmail());
	String tipoUtente=utente.getTipologia();
	out.println("<input type=\"text\" id=\"tipoNasc\" value="+tipoUtente+" hidden />");
%>

<form id="mod" action="ServletVisualizzaProdotti" method="POST">
	<button type="submit" id="butSot" name="butSot">Sottometti Prodotti a Valutazione</button>
	<button type="submit" id="butVP" name="butVP" formaction="ServletVerificaListeVisualizza">Visualizza Prodotti Valutazione Personali</button>
	<button type="submit" id="butVT" name="butVT" formaction="ServletListaUtentiValutazione">Visualizza Prodotti Valutazione Utenti</button>
</form> 


<!--  <form id="mod2" action="ServletVerificaListeVisualizza" method="POST">
	
	<button type="submit" id="butVP" name="butVP">Visualizza Prodotti Valutazione Personali</button>
</form>

<form id="mod3" action="ServletListaUtentiValutazione" method="POST">
	<button type="submit" id="butVT" name="butVT">Visualizza Prodotti Valutazione Utenti</button>
</form>-->
<script type="text/javascript">
	controlShowButton();
</script>
</body>
</html>
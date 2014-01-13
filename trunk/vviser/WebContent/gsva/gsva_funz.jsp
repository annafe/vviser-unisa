<%-- 
    Author: Giuseppe Sabato
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="it.unisa.vviser.entity.*"%>
       
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title></title>
<script type="text/javascript">
	//viualizza le opportune funzionalita' a seconda del tipo di utente
	function controlShowLink()
	{
		tipo=document.getElementById("tipoNasc").value;
		if(tipo=="ricercatore")
			document.getElementById("lnkVT").style.visibility="hidden";	
	}
</script>
</head>
<body>

<p><a id="lnkSot" href="/vviser/ServletVisualizzaProdotti">Visualizza Prodotti Valutazione Personali</a></p>
<p><a id="lnkVP" href="/vviser/ServletVerificaListeVisualizza">Visualizza Prodotti Valutazione Personali</a></p>
<p><a id="lnkVT" href="/vviser/ServletListaUtentiValutazione">Visualizza Prodotti Valutazione Utenti</a></p>

<script type="text/javascript">
	controlShowLink();
</script>
</body>
</html>
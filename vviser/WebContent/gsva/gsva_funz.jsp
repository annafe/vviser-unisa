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
	
	function p1()
	{
		document.forms['mod1'].submit();
	}
	
	function p2()
	{
		document.forms['mod2'].submit();
	}
	
	function p3()
	{
		document.forms['mod3'].submit();
	}
</script>
</head>
<body>
<%
	
	HttpSession s1= request.getSession();
	s1.getAttribute("sessTipoUtente");
	String tpu=(String)s1.getAttribute("sessTipoUtente");
	out.println("<input type=\"text\" id=\"tipoNasc\" value="+tpu+" hidden />");
%>

<form id="f1" name="mod1" action="/vviser/ServletVisualizzaProdotti" method="post">

</form>
<form id="f2" name="mod2" action="/vviser/ServletVerificaListeVisualizza" method="post">

</form>
<form id="f3" name="mod3" action="/vviser/ServletListaUtentiValutazione" method="post">

</form>
<p><a id="lnkSot" href="#" onclick="p1()">Sottometti Prodotti a Valutazione</a></p>
<p><a id="lnkVP" href="#" onclick="p2()">Visualizza Prodotti Valutazione Personali</a></p>
<p><a id="lnkVT" href="#" onclick="p3()">Visualizza Prodotti Valutazione Utenti</a></p>

<script type="text/javascript">
	controlShowLink();
</script>
</body>
</html>
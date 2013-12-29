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
    <title>VViSeR</title>
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
section#profilo{width:850px;color:tomato;}
table{color:tomato;}
</style>
<script type="text/javascript">
function controlla()
{
	 var chk = document.getElementsByTagName('input');
	    var len = chk.length;

	    for (var i = 0; i < len; i++)
	    {
	        if (chk[i].type === 'checkbox' && chk[i].checked==true)
	        {
	        	return true;
	        }
	    }
	    return false;
}
</script>
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
		<a href="../gpr/gpr.jsp">Gestione Prodotto</a>
	</section>
	
</section>

<section id="profilo">
<form method="post" action="../SottomettiMIURServlet" name="modulo" onsubmit="return controlla();">
<table>
<tr>
<th>&nbsp;</th>
<th>Titolo</th>
<th>Descrizione</th>
<th>Stato</th>
</tr>
<%
DBGestioneProdotto dbgp=DBGestioneProdotto.getInstance();
Utente currentUser = (Utente) session.getAttribute("utente");
ArrayList<Prodotto> l = dbgp.visualizzaProdottiProprietarioCoautoreMIUR(currentUser.getEmail());
for(int i=0;i<l.size();i++)
{
	%>
	<tr>
	<% out.println("<td><input type=\"checkbox\" name=\"selProd\" id='"+i+"' value='"+l.get(i).getIsbn()+"' /></td>");
     %>      
	<td><% out.print(l.get(i).getTitolo()); %></td><td><% out.print(l.get(i).getDescrizioneContenuti()); %></td><td> <% out.print(l.get(i).getStato()); %></td></tr>
	<%
}
%>
<tr>
<td colspan="3"><input type="submit" value=" Invia "></td>
</tr>
</table>
</form>
</section>
<footer id="container-footer">
TEAM EIGHT
</footer>
</body>
</html>

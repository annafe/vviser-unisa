<%@ page import="java.lang.ProcessBuilder.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<link href="/vviser/css/stile1.css" rel="stylesheet" type="text/css"/>
<link href="/vviser/css/stile2.css" rel="stylesheet" type="text/css"/>
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
<style type="text/css">

fieldset{width:60%;}
	th{color:ORANGERED;}
	td{text-align:center;}
table{padding-top:3%;}
</style>
</head>
<body>

<% 
/*@include file="../gu/header.jsp" */
%>
<%
DBGestioneProdotto dbgp=DBGestioneProdotto.getInstance();
//Utente currentUser = (Utente) session.getAttribute("utente");
//currentUser.getEmail();
ArrayList<Prodotto> l=dbgp.ricercaProdottiDoveSonoIndicatoCoautoreENonHoAncoraConvalidato("adinolfi@unisa.it");
if(l.isEmpty())
{
	%>
	<p>Non ci sono prodotti</p>
	<%
}
else
{
%>

<form method="post" action="../ConvalidaProdottoServlet" name="modulo" onsubmit="return controlla();">
<fieldset>
<legend>Convalida prodotto</legend>
<table>
<tr>
<th>&nbsp;</th>
<th>ISBN</th>
<th>TITOLO</th>
<th>DESCRIZIONE</th>
<th>TIPOLOGIA</th>
<th>NOTE</th>
<th>STATO</th>
</tr>
<%
	for(int i=0;i<l.size();i++)
	{
		%>
		<tr>
		<% out.println("<td><input type=\"checkbox\" name=\"selProd\" id='"+i+"' value='"+l.get(i).getIsbn()+"' /></td>");
	     %>
	     <td><% out.print(l.get(i).getIsbn()); %></td>      
		<td><% out.print(l.get(i).getTitolo()); %></td>
		<td><% out.print(l.get(i).getDescrizioneContenuti()); %></td>
		<td><% out.print(l.get(i).getTipologia()); %></td>
		<td><% out.print(l.get(i).getNote()); %></td>
		<td> <% out.print(l.get(i).getStato()); %></td></tr>
		<%
	}
%>
<tr>
<td colspan="7"><div class="centro"><input type="submit" value=" Convalida " class="pulsante"></div></td>
</tr>
</table>
</fieldset>
</form>
<%
}%>
</body>
</html>
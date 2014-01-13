<%-- 
    Author: Maria Vittoria Coda
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page session="true"%>
<%@page import="it.unisa.vviser.entity.*"%>
<%@page import="it.unisa.vviser.storage.*"%>
<%@page import="it.vviser.common.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.*"%>


<%
EventoValutazione e = (EventoValutazione) request.getAttribute("eventoSelezionato");
%>

<form action="../ModificaEventoServlet" method="post">
<table>
<tr><th>ID</th><td><input type="text" name="id" value=<%=e.getID()%> readonly></td></tr>
<tr><th>Nome</th><td><input type="text" name="eventoName" value=<%=e.getNomeEvento()%>></td></tr>
<tr><th>Num. pubblicazioni</th><td><input type="text" name="eventoNumPubb" value=<%=e.getNumeroPubblicazioni() %>></td></tr>
<tr><th>Scadenza</th><td><input type="text" name="eventoScadenza" value=<%=e.getScadenza() %>></td></tr>
<tr><th>Periodo: da</th><td><input type="text" name="eventoDataInizio" value=<%=CommonMethod.dateToString(e.getDataInizio()) %>></td></tr>
<tr><th>Periodo: a</th><td><input type="text" name="eventoDataFine" value=<%=CommonMethod.dateToString(e.getDataFine())%>></td></tr>
</table>

<input type="submit" class="pulsanti" value="Conferma modifiche"/>
</form>

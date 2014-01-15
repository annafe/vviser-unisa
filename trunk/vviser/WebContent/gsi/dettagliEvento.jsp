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

<script>
function hasWhiteSpace(s) {
	val = s.value;
	  if (val.indexOf(' ') == 0){
		  alert("Insere dati validi!");
		  s.value = "";
	  }
	}
</script>

<form action="ModificaEventoServlet" method="POST">
<table>
<tr><th>ID</th><td><input type="text" name="id" value=<%=e.getID()%> readonly></td></tr>
<tr><th>Nome</th><td><input type="text" name="eventoName" value=<%=e.getNomeEvento()%> required onkeyup="hasWhiteSpace(this)"></td></tr>
<tr><th>Num. pubblicazioni</th><td><input type="text" name="eventoNumPubb" value=<%=e.getNumeroPubblicazioni() %> required onkeyup="hasWhiteSpace(this)"></td></tr>
<tr><th>Scadenza</th><td><input type="text" name="eventoScadenza" value=<%=e.getScadenza() %> required onkeyup="hasWhiteSpace(this)"></td></tr>
<tr><th>Periodo: da</th><td><input type="text" name="eventoDataInizio" value=<%=CommonMethod.dateToString(e.getDataInizio()) %> required onkeyup="hasWhiteSpace(this)"></td></tr>
<tr><th>Periodo: a</th><td><input type="text" name="eventoDataFine" value=<%=CommonMethod.dateToString(e.getDataFine())%> required onkeyup="hasWhiteSpace(this)"></td></tr>
</table>

<input type="submit" class="pulsanti" value="Conferma modifiche"/>
<input type="button" value="Annulla" onclick="document.location.href='/vviser/gsi/visualizzaListaEventi.jsp';">
</form>

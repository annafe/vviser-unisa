<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="it.unisa.vviser.entity.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dettagli evento selezionato</title>

</head>
<body>

<%
EventoValutazione e = (EventoValutazione) request.getAttribute("eventoSelezionato");
%>

<form action="ModificaEventoServlet" method="post">
<table>
<tr><th>ID</th><td><input type="text" name="id" value=<%=e.getID()%>></td></tr>
<tr><th>Nome</th><td><input type="text" name="eventoName" value=<%=e.getNomeEvento()%>></td></tr>
<tr><th>Num. pubblicazioni</th><td><input type="text" name="eventoNumPubb" value=<%=e.getNumeroPubblicazioni() %>></td></tr>
<tr><th>Scadenza</th><td><input type="text" name="eventoScadenza" value=<%=e.getScadenza() %>></td></tr>
<tr><th>Periodo: da</th><td><input type="text" name="eventoDataInizio" value=<%=e.getDataInizio() %>></td></tr>
<tr><th>Periodo: a</th><td><input type="text" name="eventoDataFine" value=<%=e.getDataFine() %>></td></tr>
</table>
</form>
<input type="submit" value="Conferma modifiche"/>


</body>
</html>
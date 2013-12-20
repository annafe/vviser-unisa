<%-- 
    Author: Maria Vittoria Coda
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page session="true"%>
<%@page import="it.unisa.vviser.entity.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.*"%>
<%@page import="javax.servlet.*"%>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
	ArrayList<EventoValutazione> lista=new ArrayList<EventoValutazione>();
	lista=(ArrayList<EventoValutazione>) request.getAttribute("eventi");
%>

<form>
	<table>
		<thead>
		<tr colspan=5>Lista eventi di valutazione</tr>
			<tr>
				<th></th>
				<th>Nome</th>
				<th>Num. pubblicazioni</th>
				<th>Scadenza</th>
				<th colspan=2>Periodo</th>
			</tr>
		</thead>
		<tbody>
		<%
			for (int i=0; i<lista.size(); i++){
				EventoValutazione e=lista.get(i);
				
				out.println("<tr onclick=\"VisualizzaDettagliEventoValutazione\">");
				out.println("<td value="+e.getNomeEvento()+"></td>");
				out.println("<td value="+e.getNumeroPubblicazioni()+"></td>");
				out.println("<td value="+e.getScadenza()+"></td>");
				out.println("<td value="+e.getDataInizio()+"></td>");
				out.println("<td value="+e.getDataFine()+"></td>");
				out.println("</tr>");
			}
		%>
		</tbody>
	</table>
</form>

</body>
</html>

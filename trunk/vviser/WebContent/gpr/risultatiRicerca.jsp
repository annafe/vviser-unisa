<%@page import="java.util.Calendar"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="it.unisa.vviser.entity.Prodotto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Risultati della ricerca</title>
<style type="text/css">
	td{
		padding:5px;
	}
</style>
</head>
<body>
	<table>
		<tr><th>Anno</th><th>Titolo</th><th>Descrizione</th></tr>
		<% 
			List<Prodotto> pr = (ArrayList<Prodotto>) request.getAttribute("results"); 
			for(Prodotto p : pr){
				out.println("<tr>");
				out.print("<td>"+p.getAnnoPubblicazione().get(Calendar.YEAR)+"</td><td>"+p.getTitolo() +"</td><td>"+p.getDescrizioneContenuti()+"</td>");
				out.println("<td>");
				out.println("<form action=\"/vviser/VisualizzaDettaglioProdottoServlet\" method=\"POST\">");
				out.println("<input type=\"hidden\" name=\"isbn\" value=\""+p.getIsbn()+"\"/>");
				out.println("<input type=\"submit\" value=\"Dettagli\"/>");
				out.println("</form></td></tr>");
			}
		%>
	</table>
</body>
</html>
<%-- 
    Author: Maria Vittoria Coda
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page session="true"%>
<%@page import="it.unisa.vviser.entity.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.*"%>


    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista eventi</title>

<script type="text/javascript">
function funz(e){

	
	//questo dovrebbe essere un metodo per linkare ad una servlet tramite javascript
	document.location.href = '${pageContext.request.contextPath}/visualizzaModificaDettagliEventoValutazioneServlet';
	
<%--	
	EventoValutazione evt = new EventoValutazione(nome,num,scad,da,a);
	<%-- NON SO COME SETTARE NELLA request LO SPECIFICO EVENTO SELEZIONATO
	request.setAttribute("eventoSel", evt);
--%>	

//	document.location.href = 'VisualizzaModificaDettagliEventoValutazione';

}

</script>
</head>
<body>

<%
	ArrayList<EventoValutazione> lista=new ArrayList<EventoValutazione>();
	lista=(ArrayList<EventoValutazione>) request.getAttribute("listaEventi");
%>


<table>
	<thead>
	<tr colspan=3>Lista eventi di valutazione</tr>
		<tr>
			<th></th>
			<th>ID</th>
			<th>Nome</th>
		</tr>
	</thead>
	<tbody>
	<%
		for (int i=0; i<lista.size(); i++){
			EventoValutazione e=lista.get(i);
			
			out.println("<tr><form name=\"formVisualizza\" action\"visualizzaModificaDettagliEventoValutazioneServlet\" method=\"POST\">");
			//out.println("<td name=\"idEvento\">"+e.getID()+"</td>");
			out.println("<td name=\"nomeEvento\">"+e.getNomeEvento()+"</td>");
			out.println("<td name=\"datainizio\">"+e.getDataInizio()+"</td>");
			out.println("<td name=\"datafine\">"+e.getDataFine()+"</td>");
			out.println("<td><input type=\"submit\" name=\"modificaEvento\" value=\"Modifica\"/></td>");
			out.println("<td><input type=\"submit\" name=\"rimuoviEvento\" value=\"Rimuovi\"/></td>");
			out.println("</tr>");
		}
	%>	
	</tbody>
</table>


</body>
</html>

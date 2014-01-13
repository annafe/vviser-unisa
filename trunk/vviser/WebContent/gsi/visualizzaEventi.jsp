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


<%--
	ArrayList<EventoValutazione> lista=new ArrayList<EventoValutazione>();
	lista=(ArrayList<EventoValutazione>) request.getAttribute("listaEventi");
--%>
<%
	DBEventiValutazione gestore = DBEventiValutazione.getInstance();
	ArrayList<EventoValutazione> lista = (ArrayList<EventoValutazione>) gestore.visualizzaEventi();
%>


<table>
	<thead>
	<tr colspan=3>Lista eventi di valutazione</tr>
		<tr>
			<th></th>
			<th>Nome</th>
			<th>Data Inizio</th>
			<th>Data Fine</th>
		</tr>
	</thead>
	<tbody>
	<%
		for (int i=0; i<lista.size(); i++){
			EventoValutazione e=lista.get(i);
			
			out.println("<tr>");
			
			out.println("<form name=\"formVisualizza\" action=\"../VisualizzaModificaDettagliEventoValutazioneServlet\" method=\"POST\">");
			out.println("<td><input hidden name=\"idEvento\" value=\""+e.getID()+"\"></td>");
			out.println("<td name=\"nomeEvento\">"+e.getNomeEvento()+"</td>");
			out.println("<td name=\"datainizio\">"+CommonMethod.dateToString(e.getDataInizio())+"</td>");
			out.println("<td name=\"datafine\">"+CommonMethod.dateToString(e.getDataFine())+"</td>");
			out.println("<td><input type=\"submit\" name=\"modificaEvento\" value=\"Modifica\"/></td>");
			out.println("<td><input type=\"submit\" name=\"rimuoviEvento\" value=\"Rimuovi\"/></td>");
		//	out.println("<td><input type=\"button\" name=\"modificaEvento\" value=\"Modifica\" href=\"visualizzaModificaDettagliEventoValutazioneServlet\"/></td>");
		//	out.println("<td><input type=\"submit\" name=\"rimuoviEvento\" value=\"Rimuovi\" href=\"visualizzaModificaDettagliEventoValutazioneServlet\"\"/></td>");
			out.println("</form>");
			out.println("</tr>");
		}
	%>	
	</tbody>
</table>


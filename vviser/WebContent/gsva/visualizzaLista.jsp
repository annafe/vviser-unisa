<%-- 
    Author: Giuseppe Sabato
--%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="it.unisa.vviser.entity.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="org.json.JSONObject"%>
<%@ page session="true"%>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8"/>
    <title>Seleziona una Lista</title>
    <script type="text/javascript">
    	//disabilita il button modifica se la lista e' bloccata
    	function buttonModify()
    	{
    		stato=document.getElementById("statoL").value;
    		if(stato=="true")
    			document.getElementById("buttMod").disabled=true;
    	}
    	
    	//disabilita il button inserisci suggerimento se il sugg e' null
    	//e disabilita il button modifica suggerimento se il sugg e' diverso da null
    	function buttonSuggestion()
    	{
    		sugg=document.getElementById("sugg").value;
    		if(sugg=="null")
    			document.getElementById("buttms").disabled=true;
    		else
    			document.getElementById("buttis").disabled=true;
    		
    	}
    	function setActionModSuggerimento()
    	{
    		document.getElementById("formMod").action="ServletSuggerimentoListaValutazione";
    	}
    </script>
</head>

<body>

<%
	ListaProdottiValutazione listaProdottiValutazione=new ListaProdottiValutazione();
	listaProdottiValutazione=(ListaProdottiValutazione)request.getAttribute("lista");
	HttpSession s=request.getSession();
	s.setAttribute("listaProdottiValutazione", listaProdottiValutazione);//setto la sessione per passare la lista alla servlet della modifica
	
    boolean statoLista=listaProdottiValutazione.getBloccato();
	out.println("<input id=\"statoL\" type=\"text\" value="+statoLista+" hidden/>");
	
%>

<form id="formMod" action="ServletSelezioneModifica" method="POST">
	<table>
		<tr>
			<th colspan="2">Lista prodotti sottomessi a valutazione</th>
		</tr>
		<tr>
			<th>Note</th>
			<td><input type="text" id="sugg" name="sugg" value="<%out.println(listaProdottiValutazione.getSuggestion()); %>" /></td>
		</tr>
		<tr>
			<th>Titolo</th>
			<th>Priorita'</th>
		</tr>
		<%
			ArrayList<ProdottoValutazione> prodottiValutazione=listaProdottiValutazione.getListaProdottiValutazione();
			for(int i=0;i<prodottiValutazione.size();i++)
			{
				out.println("<tr>");
					out.println("<td>"+prodottiValutazione.get(i).getTitle()+"</td>");
					out.println("<td>"+prodottiValutazione.get(i).getPriority()+"</td>");
				out.println("<tr>");
			}
			
		%>
	</table>
	<button type="submit" id="buttMod" name="buttMod">Modifica</button>
	<script type="text/javascript">
		buttonModify();
	</script>
	<button type="submit" id="buttis" name="buttis" onclick="setActionModSuggerimento()">Inserisci Suggerimento</button>
	<button type="submit" id="buttms" name="buttms" onclick="setActionModSuggerimento()">Modifica Suggerimento</button>
	<script type="text/javascript">
		buttonSuggestion();
	</script>
</form>

<form id="formMod1" action="ServletRifiutaListaValutazione" method="POST">
<button type="submit" id="buttRif" name="buttRif">Rifiuta Lista</button>
</form>






</body>
</html>
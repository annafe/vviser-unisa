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
    <title>Modifica Lista</title>
    <script type="text/javascript">

    	function deselezionaProdottiValutazione(check)
    	{
    		
    		idDesel=check.getAttribute('id');
    		if(document.getElementById(idDesel).checked==false)
    		{
    			i--;
    			document.getElementById("txNasc"+idDesel).disabled=false;
    			document.getElementById("txmd"+idDesel).disabled=true;
    			
    		}
    		else
    		{
    			if(i<n)
    			{
    				i++;
    				document.getElementById("txNasc"+idDesel).disabled=true;
    				document.getElementById("txmd"+idDesel).disabled=false;
    			}
    			else
    			{
    				alert("Puoi sottomettere a valutazione max "+n+" prodotti !!");
    				document.getElementById(idDesel).checked=false;
    			}
    		}
    	}
    	
    	function selezionaProdotti(check)
    	{
    		idSel=check.getAttribute('id');
    		if(document.getElementById(idSel).checked==false)
    		{
    			i--;
    			document.getElementById("pr"+idSel).disabled=true;
    		}
    		else
    		{
    			if(i<n)
    			{
    				i++;
    				document.getElementById("pr"+idSel).disabled=false;
    			}
    			else
    			{
    				alert("Puoi sottomettere a valutazione max "+n+" prodotti !!");
    				document.getElementById(idSel).checked=false;
    			}
    		}
    	}
    </script>
</head>

<body>

<%
	ListaProdottiValutazione listaProdottiValutazione=new ListaProdottiValutazione();
	listaProdottiValutazione=(ListaProdottiValutazione)request.getAttribute("listaValutazione");
	ArrayList<Prodotto> prodotti=new ArrayList<Prodotto>();
	prodotti=(ArrayList<Prodotto>)request.getAttribute("prodottiNonSottomessi");
	HttpSession s=request.getSession();
	EventoValutazione evento=(EventoValutazione)s.getAttribute("evento");
	s.setAttribute("emailUt",listaProdottiValutazione.getEmailUtente());
	s.setAttribute("idEv", listaProdottiValutazione.getIdEventoValutazione());
	int numPubblicazioni=evento.getNumeroPubblicazioni();
	out.println("<input id=\"npHidd\" type=\"text\" value="+numPubblicazioni+" hidden/>");
	out.println("<input id=\"numPV\" type=\"text\" value="+listaProdottiValutazione.getListaProdottiValutazione().size()+" hidden/>");
%>
<script type="text/javascript">
	i=document.getElementById("numPV").value;
	n=document.getElementById("npHidd").value;
</script>

<form id="fsm" action="/vviser/ServletModificaProdottiValutazione" method="POST">
	<table>
		<tr>
			<th colspan="3">Modifica Lista</th>
		</tr>
		<tr>
			<th>Note</th>
			<td><%out.println(listaProdottiValutazione.getSuggestion()); %></td>
		</tr>
		<tr>
			<th>Seleziona</th>
			<th>Titolo</th>
			<th>Priorita'</th>
		</tr>
		<%
			ArrayList<ProdottoValutazione> prodottiValutazione=listaProdottiValutazione.getListaProdottiValutazione();
			for(int i=0;i<prodottiValutazione.size();i++)
			{
				String idC=""+i;
				String idTp="txmd"+i;
				String idTh="txNasc"+i;
				out.println("<tr>");
					out.println("<td><input type=\"checkbox\" id="+idC+" name=\"cbmd\" value="+prodottiValutazione.get(i).toString()+" checked onclick=\"deselezionaProdottiValutazione(this)\" /></td>");
					out.println("<td>"+prodottiValutazione.get(i).getTitle()+"</td>");
					out.println("<td><input type=\"text\" id="+idTp+" value="+prodottiValutazione.get(i).getPriority()+" pattern=\"[1-5]{1}\" name=\"pri\" /></td>");
					out.println("<td><input type=\"text\" id="+idTh+" value="+prodottiValutazione.get(i).toString()+" name=\"desel\" disabled hidden /></td>");//hidden per contenere i prod. val. deselezionati
				out.println("</tr>");
			}
			out.println("<tr>");
				out.println("<td colspan=\"3\">prodotti con cui sostituire</td>");
			out.println("</tr>");
			for(int i=0;i<prodotti.size();i++)
			{
				String idCr="cb"+i;
				String idPr="pr"+idCr;
				out.println("<tr>");
					out.println("<td><input type=\"checkbox\" id="+idCr+" value="+prodotti.get(i).getIsbnTitleProdotto()+" name=\"cb\" onclick=\"selezionaProdotti(this)\" /></td>");
					out.println("<td>"+prodotti.get(i).getTitolo()+"</td>");
					out.println("<td><input type=\"text\" id="+idPr+" name=\"newPri\" disabled=\"true\" /></td>");
				out.println("</tr>");
			}
			
		%>
	</table>
	<button class="pulsante" type="submit" name="modifica">Conferma</button>
</form>



</body>
</html>
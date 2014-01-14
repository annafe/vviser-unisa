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
    		document.getElementById("buttis").type="submit";
    		document.getElementById("buttms").type="submit";
    		if (document.getElementById("sugg").value=="" || document.getElementById("sugg").value=="null")
    		{
    			alert("Nessun suggerimento inserito o modificato !");
    			document.getElementById("buttis").type="button";
        		document.getElementById("buttms").type="button";
    		}
    		else
    		{
    			document.getElementById("formMod").action="/vviser/ServletSuggerimentoListaValutazione";
    		}
    	}
    	
    	//Mostra le funzionalita' a seconda del tipo di utente
    	//che si autentica
    	function controlButton()
    	{
    		tipo=document.getElementById("tpn").value;
    		tipoVis=document.getElementById("tipoV").value;
    		if(tipo=="ricercatore")
    		{
    			document.getElementById("buttMod").hidden=false;
    			document.getElementById("sugg").readOnly=true;
    		}
    		if(tipo=="direttoreDiDipartimento" && tipoVis=="tutti")
    		{
    			document.getElementById("buttis").hidden=false;	
    			document.getElementById("buttms").hidden=false;
    			document.getElementById("buttRif").hidden=false;
    			document.getElementById("buttConv").hidden=false;
    		}
    		if(tipo=="direttoreDiDipartimento" && tipoVis=="personale")
    		{
    			document.getElementById("buttMod").hidden=false;
    			document.getElementById("sugg").readOnly=true;
 
    			document.getElementById("buttRif").hidden=false;
    			document.getElementById("buttConv").hidden=false;
    		}
    		if(tipo=="membroDelComitatoDiAreaDidattica" && tipoVis=="tutti")
    		{
    			document.getElementById("buttis").hidden=false;	
    			document.getElementById("buttms").hidden=false;
    		}
    		if(tipo=="membroDelComitatoDiAreaDidattica" && tipoVis=="personale")
    		{
    			document.getElementById("buttMod").hidden=false;
    			document.getElementById("sugg").readOnly=true;
    		}
    		
    		if(tipo=="membroDelComitatoDiAteneo" && tipoVis=="personale")
    		{
    			document.getElementById("sugg").readOnly=true;
    			document.getElementById("buttMod").hidden=false;
    		}
    		if(tipo=="membroDelComitatoDiAteneo" && tipoVis=="tutti")
    		{
    			document.getElementById("sugg").readOnly=true;
    		}
    	}
    	
    	//evidenzia i prodotti sottomessi a valutazione in conflitto
    	function evidConflitti()
    	{
    		var str=document.getElementById("confl").value;
    		var s=str.split(";");
    		for (var i=0;i<s.length-1;i++)
    		{
    			document.getElementById(s[i]).style.backgroundColor="red";
    		}
   
    	}
    	
    </script>
</head>

<body>

<%
	ListaProdottiValutazione listaProdottiValutazione=new ListaProdottiValutazione();
	listaProdottiValutazione=(ListaProdottiValutazione)request.getAttribute("lista");
	HttpSession s=request.getSession();
	s.setAttribute("listaProdottiValutazione", listaProdottiValutazione);//setto la sessione per passare la lista alla servlet della modifica
	String tipoVisualizzazione=(String)s.getAttribute("visualizza");//ottengo se si tratta di una visual. personale oppure della visual di tutti
    boolean statoLista=listaProdottiValutazione.getBloccato();
    out.println("<input id=\"tipoV\" type=\"text\" value="+tipoVisualizzazione+" hidden/>");
	out.println("<input id=\"statoL\" type=\"text\" value="+statoLista+" hidden/>");
	
	String evidenziaConflitti=(String)s.getAttribute("segnalaConflitti");//recupero la sessione per vedere se segnalare o meno i conflitti
	Utente utente=(Utente)s.getAttribute("utente");
	String tipoUtente=utente.getTipologia();
	out.println("<input type=\"text\" id=\"tpn\" value="+tipoUtente+" hidden />");
	
%>

<form id="formMod" action="/vviser/ServletSelezioneModifica" method="POST">
	<table>
		<tr>
			<th colspan="2">Lista prodotti sottomessi a valutazione</th>
		</tr>
		<tr>
			<th>Note</th>
			<td><input type="text" id="sugg" name="sugg" maxlength="500" value="<%out.println(listaProdottiValutazione.getSuggestion()); %>" /></td>
		</tr>
		<tr>
			<th>Titolo</th>
			<th>Priorita'</th>
		</tr>
		<%
			ArrayList<ProdottoValutazione> prodottiValutazione=listaProdottiValutazione.getListaProdottiValutazione();
			for(int i=0;i<prodottiValutazione.size();i++)
			{
				String idP="pv"+i;
				out.println("<tr id="+idP+">");
					out.println("<td>"+prodottiValutazione.get(i).getTitle()+"</td>");
					out.println("<td>"+prodottiValutazione.get(i).getPriority()+"</td>");
				out.println("<tr>");
			}
			
			if(evidenziaConflitti.equals("on"))//mostra solo agli utenti (direttore dipartimento, comitato area)
			{
				//System.out.println("entro in on");
				ArrayList<ProdottoValutazione> prodottiConflitto=new ArrayList<ProdottoValutazione>();
				prodottiConflitto=(ArrayList<ProdottoValutazione>)request.getAttribute("conflitti");
				if(prodottiConflitto.size()!=0)
				{
					String idConflitti="";
					for(int i=0;i<prodottiConflitto.size();i++)
					{
						for(int j=0;j<prodottiValutazione.size();j++)
						{
							if(prodottiConflitto.get(i).getIsbn().equals(prodottiValutazione.get(j).getIsbn()))
							{
								idConflitti+="pv"+j+";";
							}
						}
					}
					out.println("<input type=\"text\" id=\"confl\" value="+idConflitti+" hidden />");
					//System.out.println(idConflitti);
				}
			}
			
		%>
	</table>
	<script type="text/javascript">
		evidConflitti();
	</script>
	<button type="submit" id="buttMod" name="buttMod" class="pulsante" hidden>Modifica</button>
	<script type="text/javascript">
		buttonModify();
	</script>
	<button type="submit" id="buttis" name="buttis" onclick="setActionModSuggerimento()" class="pulsante" hidden>Inserisci Suggerimento</button>
	<button type="submit" id="buttms" name="buttms" onclick="setActionModSuggerimento()" class="pulsante" hidden>Modifica Suggerimento</button>
	<script type="text/javascript">
		buttonSuggestion();
	</script>
</form>

<form id="formMod1" action="/vviser/ServletConvalidaORifiutaListaValutazione" method="POST">
	<input type="text" name="bloccato" value="si" hidden />
	<button type="submit" id="buttConv" name="buttConv" class="pulsante" hidden>Convalida Lista</button>
</form>

<form id="formMod2" action="/vviser/ServletConvalidaORifiutaListaValutazione" method="POST">
	<input type="text" name="bloccato" value="no" hidden />
	<button type="submit" id="buttRif" name="buttRif" class="pulsante" hidden>Rifiuta Lista</button>
</form>

<script type="text/javascript">
	controlButton();
</script>
</body>
</html>
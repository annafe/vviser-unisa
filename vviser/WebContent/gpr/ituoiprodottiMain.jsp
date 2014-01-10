<!-- ROMANO SIMONE 
	Antonio De Piano -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="it.vviser.common.CommonMethod"%>
<%@ page import="it.unisa.vviser.storage.DBGestioneProdotto"%>
<%@ page import="it.unisa.vviser.entity.Prodotto"%>
<%@ page import="it.unisa.vviser.entity.Utente"%>
<%@ page import="java.util.*"%>
 <html>
 <head>
 	<title>VViSeR</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
 
	<link href="/vviser/css/stile1.css" rel="stylesheet" type="text/css"/>
	<link href="/vviser/css/stile2.css" rel="stylesheet" type="text/css"/>
 
 <script type="text/javascript">
 	function elimina(input){
 		form = input.parentNode;
 		var r=confirm("Vuoi eliminare il prodotto?");
 		if (r==true){
 			form.submit();
 		}
 		else{
 		  return;
 		}
 	}
 </script>
 
 <style type="text/css">
table{padding-top:5%;}
fieldset{width:50%;}
td{text-align:center;}
</style>
</head>
<body>
<fieldset>
		<legend>Prodotti</legend>
 <table>
 	<tr>
 	<th style="color:orangered;">TITOLO PRODOTTO</th>
 	</tr>
		<% 	
			DBGestioneProdotto dbGpr = new DBGestioneProdotto();
			Utente currentUser = (Utente) session.getAttribute("utente");
			List<Prodotto> l = dbGpr.visualizzaProdottiPersonali(currentUser.getEmail());
			for (int i=0; i<l.size(); i++){
				%><tr><td><%out.println(l.get(i).getTitolo());%></td>
				<td><!-- form dettagli -->
					<form action="/vviser/VisualizzaDettaglioProdottoServlet" method="POST">
						<!-- la servlet prende l'isbn e, dopo aver recuperato il prodotto
							reindirizza sulla pagina di dettagli -->
						<input type="hidden" name="isbn" value="<%out.print(l.get(i).getIsbn());%>"/>
						<input type="submit" value="Dettagli" class="pulsante" />
					</form>				
				</td>
				<td><!-- form modifica -->
					<form action="/vviser/gpr/ModificaProdotto.jsp" method="POST">	
						<!-- value contiene i campi obbligatori di "prodotto"(dovrebbe essere simile
							per modifica -->	
						<input type="hidden" name="titolo" value="<%out.print(l.get(i).getTitolo());%>"/>	
						<input type="hidden" name="proprietario" value="<%out.print(l.get(i).getProprietario());%>"/>	
						<input type="hidden" name="annoDiPubblicazione" value="<%out.print(CommonMethod.dateToString(l.get(i).getAnnoPubblicazione()));%>"/>
						<input type="hidden" name="tipologia" value="<%out.print(l.get(i).getTipologia());%>"/>			
						<input type="submit" value="Modifica" class="pulsante"/>
					</form>
				</td>
				<td><!-- form elimina -->
					<form action="/vviser/EliminaProdottoServlet" method="POST">
						<input type="hidden" name="isbn" value="<%out.print(l.get(i).getIsbn());%>"/>
						<input type="button" onclick="elimina(this)" value="Elimina" class="pulsante" />
					</form>				
				</td>
				</tr> 
				<% 
			}
		%>
</table>
</fieldset>
</body>
</html>
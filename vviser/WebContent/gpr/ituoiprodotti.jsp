<!-- ROMANO SIMONE -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="it.unisa.vviser.storage.DBGestioneProdotto"%>
<%@ page import="it.unisa.vviser.entity.Prodotto"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>I tuoi prodotti</title>
</head>
<body>
 <%@include file="/gu/header.jsp" %>
 
 <h1>ADMIN PAGE - GESTIONE PRODOTTI</h1>
 
 <script type="text/javascript">
 	function elimina(input){
 		form = input.parentNode;
 		var r=confirm("Vuoi eliminare l'utente?");
 		if (r==true){
 			form.submit();
 		}
 		else{
 		  return;
 		}
 	}
 </script>
 <table border=1>
 	<tr><th>TITOLO</th></tr>
		<% 	
			DBGestioneProdotto dbGpr = new DBGestioneProdotto();
			Utente currentUser = (Utente) session.getAttribute("utente");
			List<Prodotto> l = dbGpr.visualizzaProdottiPersonali(currentUser.getEmail());
			for (int i=0; i<l.size(); i++){
				%><tr><td><%out.println(l.get(i).getTitolo());%></td>
				<td><!-- form dettagli -->
					<form action="/vviser/VisualizzaDettaglioProdottoServlet" method="POST">
						<input type="hidden" name="isbn" value="<%out.print(l.get(i).getIsbn());%>"/>
						<input type="submit" value="Dettagli"/>
					</form>				
				</td>
				<td><!-- form modifica -->
					<form action="/vviser/gpr/ModificaProdotto.jsp" method="POST">		
						<input type="hidden" name="daModificare" value="<%out.print(l.get(i).getIsbn());%>"/>			
						<input type="submit" value="Modifica"/>
					</form>
				</td>
				<td><!-- form elimina -->
					<form action="/vviser/EliminaProdottoServlet" method="POST">
						<input type="hidden" name="isbn" value="<%out.print(l.get(i).getIsbn());%>"/>
						<input type="button" onclick="elimina(this)" value="Elimina"/>
					</form>				
				</td>
				</tr> 
				<% 
			}
		%>
</table>
 
</body>
</html>
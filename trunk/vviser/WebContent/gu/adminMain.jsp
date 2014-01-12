<!-- ROMANO SIMONE -->
<%@ page import="java.lang.ProcessBuilder.*"%>
<%@ page import="java.util.*"%>
<%@ page import="it.unisa.vviser.storage.DBUtente"%>
<%@ page import="it.unisa.vviser.entity.Utente"%>

 <%@include file="/gu/header.jsp" %>
 
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
 <h1>ADMIN PAGE - GESTIONE UTENTI</h1>
 <table border=1>
 	<tr><th>COGNOME</th><th>NOME</th><th>EMAIL</th><th>DIPARTIMENTO</th><th>TIPOLOGIA</th></tr>
		<% 	
			DBUtente dbUser = new DBUtente();
			List<Utente> l = dbUser.visualizzaUtenti();
			for (int i=0; i<l.size(); i++){
				%><tr><td><%out.println(l.get(i).getCognome());%></td><td><%out.print(l.get(i).getNome()); %></td><td><%out.print(l.get(i).getEmail()); %></td><td><%out.print(l.get(i).getDipartimento()); %></td><td><%out.print(l.get(i).getTipologia()); %></td>
				<td><!-- form modifica -->
					<form action="/vviser/gu/modifyAccount.jsp" method="POST">		
						<input type="hidden" name="daModificare" value="<%out.print(l.get(i).getEmail());%>"/>			
						<input type="submit" value="Modifica"/>
					</form>
				</td>
				<td><!-- form elimina -->
					<form action="/vviser/DeleteAccountServlet" method="POST">
						<input type="hidden" name="daEliminare" value="<%out.print(l.get(i).getEmail());%>"/>
						<input type="button" onclick="elimina(this)" value="Elimina"/>
					</form>				
				</td>
				</tr> 
				<% 
			}
		%>
</table>

<a href="/vviser/gu/insertAccount.jsp">Inserisci Account</a>
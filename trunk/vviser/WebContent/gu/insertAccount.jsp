<%@page import="it.unisa.vviser.entity.Utente"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="it.unisa.vviser.storage.DBNames"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@page import="it.unisa.vviser.storage.DBConnectionPool"%>

<script type="text/javascript">
	function testPass(modulo){
	  if (modulo.password.value != modulo.password_2.value) {
	    alert("Le password non corrispondono!");
	    return false;
	  }
	  return true;
	}
</script>

<% 
//check permission
 try{
	Utente admin = (Utente)session.getAttribute("utente");
	 if (!(admin.getTipologia().equalsIgnoreCase("amministratore"))){
		request.setAttribute("error", "Non hai i permessi per effettuare l'operazione");
		if(!response.isCommitted()){	//header can alredy forward response
			request.getRequestDispatcher("/gu/login.jsp").forward(request, response);	
			return;		
		}
	 }
 }catch(Exception e){
	request.setAttribute("error", "Sessione non settata");
	if(!response.isCommitted()){	//header can alredy forward response
		request.getRequestDispatcher("/gu/login.jsp").forward(request, response);	
		return;		
	}
 }
%>
<div id="new_account">	
	<fieldset>
		<legend>Registrazione Utente</legend>
		<form action="/vviser/AddUtenteServlet" method="POST" onsubmit="return testPass(this)">
			<table>
				<tr><td>Nome:</td><td><input type="text" name="nome" required/></td></tr>
				<tr><td>Cognome:</td><td><input type="text" name="cognome" required/></td></tr>
				<tr><td>Email:</td><td><input type="text" name="email" required/></td></tr>
				<tr><td>Password:</td><td> <input type="password" autocomplete="off" name="password" required/></td></tr>
				<tr><td>Conferma la password:</td><td><input type="password" name="password_2" required/></td></tr>
				<tr>
					<td>Data di Nascita:</td><td><input type="number" name="giornoNascita" placeholder="Giorno"required/>
						<select id="mese" name="meseNascita">
							<option value="01" selected>Gennaio</option>
							<option value="02">Febbraio</option>
							<option value="03">Marzo</option>
							<option value="04">Aprile</option>
							<option value="05">Maggio</option>
							<option value="06">Giugno</option>
							<option value="07">Luglio</option>
							<option value="08">Agosto</option>
							<option value="09">Settembre</option>
							<option value="10">Ottobre</option>
							<option value="11">Novembre</option>
							<option value="12">Dicembre</option>
						</select>
					<input type="number" name="annoNascita" placeholder="Anno"required/></td>
				</tr>
				<tr><td>Comune di nascita:</td><td><input type="text" name="comunedinascita" required/></td></tr>
				<tr><td>Provincia di nascita:</td><td><input type="text" name="provinciadinascita" 
						pattern="[a-z A-Z][a-z A-Z]" size="2" required/></td></tr>
				<tr><td>Codice Fiscale:</td><td><input type="text" name="codicefiscale" 
						pattern="[a-zA-Z]{6}[0-9]{2}[a-zA-Z][0-9]{2}[a-zA-Z][0-9]{3}[a-zA-Z]"
						size="16" required/></td></tr>
				<tr><td>Dipartimento:</td>
					<td>
						<select id="dipartimento" name="dipartimento">
						<%
						try{
							Connection conn = DBConnectionPool.getConnection();
							String query = "SELECT "+DBNames.ATTR_DIPARTIMENTO_NOME
										+" FROM "+DBNames.TABLE_DIPARTIMENTO;
							PreparedStatement st = conn.prepareStatement(query);
							ResultSet rs = st.executeQuery();
						
							while(rs.next()){
								out.print("<option value="); 
								out.print("\""+rs.getString(DBNames.ATTR_DIPARTIMENTO_NOME)+"\">");
								out.print(rs.getString(DBNames.ATTR_DIPARTIMENTO_NOME));
								out.print("</option>");
							}
							st.close();
							DBConnectionPool.releaseConnection(conn);
						}catch(SQLException e){
							e.printStackTrace();
						}
						%>
						</select>
					</td></tr>
				<tr><td>Tipologia:</td>
					<td>
					<select id="tipologia" name="tipologia">
						<option value="amministratore">Amministratore</option>
						<option value="ricercatore">Ricercatore</option>
						<option value="direttoreDiDipartimento">Direttore di dipartimento</option>
						<option value="membroDelComitatoDiAreaDidattica">Membro del comitato di area didattica</option>
						<option value="membroDelComitatoDiAteneo">Membro del comitato di Ateneo</option>
					</select>
					</td></tr>
				<tr><td colspan="2" style="text-align:center;"><input class="pulsante" type="submit"/>
				<input class="pulsante" type="reset"/></td></tr>
			</table>
		</form> 
	</fieldset>
</div>	
<!-- ROMANO SIMONE -->
<%@ page import="it.unisa.vviser.storage.DBUtente"%>
<%@ page import="it.unisa.vviser.entity.Utente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@page import="it.unisa.vviser.storage.DBConnectionPool"%>
<%@page import="it.unisa.vviser.storage.DBNames"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="it.vviser.common.CommonMethod"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modifica Account</title>
</head>
<body>
 <%//@include file="/gu/header.jsp" %>
 
 <script type="text/javascript">
 	function checkSubmit(){ 		
 		//check campi obbligatori
 		if(document.getElementById("nome").value=="" ||
 			document.getElementById("cognome").value=="" ||
 			document.getElementById("email").value=="" ||
 			document.getElementById("password").value=="" ||
 			document.getElementById("repeatPassword").value==""){
 			alert("Compila i campi obbligatori!");
 			return;
 		}
 		
 		//check coerenza tra password e ripetiPassword
 		password = document.getElementById("password").value;
 		repeatPassword = document.getElementById("repeatPassword").value;
 		if (password!=repeatPassword){
 			alert("Le password non coincidono!");
 			return;
 		}
 		//ok->submit
 		formModifica = document.getElementById("formModifica");
 		formModifica.submit();
 	}
 </script>
  
<h1>MODIFICA ACCOUNT</h1>
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
 	
 	//get user to modify
 	DBUtente dbUtente = new DBUtente();
	String email = request.getParameter("daModificare"); 
	Utente userToModify = dbUtente.getUtente(email);	
	if (userToModify==null){
		request.setAttribute("error", "Email non presente nel Database");
		response.sendRedirect("/vviser/gu/admin.jsp");
	}
	else{%>
		<form id="formModifica" action="/vviser/ModifyAccountServlet" method="POST">
			<fieldset style="float: left;">
				<legend>Modifica account</legend>
					<table border=0>
						<tr><td></td><td><input type="hidden" name="oldEmail" value="<%out.print(userToModify.getEmail());%>"/></td></tr>
						<tr><td align="right">Nome*:</td><td><input id="nome" type="text" name="nome" value="<%out.print(userToModify.getNome());%>"/></td></tr>
						<tr><td align="right">Cognome*:</td><td><input id="cognome" type="text" name="cognome" value="<%out.print(userToModify.getCognome());%>"/></td></tr>
						<tr><td align="right">Comune di nascita:</td><td><input type="text" name="comuneDiNascita" value="<%out.print(userToModify.getComuneDiNascita());%>"/></td></tr>
						<tr><td align="right">Provincia di nascita:</td><td><input type="text" name="provinciaDiNascita" value="<%out.print(userToModify.getProvinciaDiNascita());%>"/></td></tr>
						<tr><td align="right">Codice fiscale:</td><td><input type="text" name="codiceFiscale" value="<%out.print(userToModify.getCodiceFiscale());%>"/></td></tr>
						<tr><td align="right">Email*:</td><td><input id="email" type="text" name="email" value="<%out.print(userToModify.getEmail());%>"/></td></tr>
						<tr><td align="right">Data di nascita(Provvisorio):</td><td><input type="text" name="dataDiNascita" value="<%out.print(CommonMethod.dateToString(userToModify.getDataDiNascita()));%>"/></td></tr>
						<tr><td align="right">Password*:</td><td><input id="password" type="password" autocomplete="off" name="password" value="<%out.print(userToModify.getPassword());%>"/></td></tr>
						<tr><td align="right">Reinserisci password(Provvisorio)*:</td><td><input id="repeatPassword" type="password" autocomplete="off" name="reinserisciPassword" value="<%out.print(userToModify.getPassword());%>"/></td></tr>
						<tr><td align="right">Dipartimento:</td>
						<td>
							<select name="dipartimento">
								<option value="<%out.print(userToModify.getDipartimento());%>"><%out.print(userToModify.getDipartimento());%></option>
								<%
									try{
										Connection conn = DBConnectionPool.getConnection();
										String query = "SELECT "+DBNames.ATTR_DIPARTIMENTO_NOME
													+" FROM "+DBNames.TABLE_DIPARTIMENTO
													+ " WHERE "+DBNames.ATTR_DIPARTIMENTO_NOME+"<>'"
													+userToModify.getDipartimento()+"';";
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
						<tr><td align="right">Tipologia:</td><td>
							<select id="tipologia" name="tipologia">
								<option value="<%out.print(userToModify.getTipologia());%>"><%out.print(userToModify.getTipologia());%></option>
								<%
									if (!(userToModify.getTipologia().equalsIgnoreCase("amministratore"))){
										%><option value="amministratore">Amministratore</option><%
									}
								
								%>
								<%
									if (!(userToModify.getTipologia().equalsIgnoreCase("ricercatore"))){
										%><option value="ricercatore">Ricercatore</option><%
									}
								
								%>
								<%
									if (!(userToModify.getTipologia().equalsIgnoreCase("direttoreDiDipartimento"))){
										%><option value="direttoreDiDipartimento">Direttore di dipartimento</option><%
									}
								
								%>
								<%
									if (!(userToModify.getTipologia().equalsIgnoreCase("membroDelComitatoDiAreaDidattica"))){
										%><option value="membroDelComitatoDiAreaDidattica">Membro del comitato di area didattica</option><%
									}
								
								%>
								<%
									if (!(userToModify.getTipologia().equalsIgnoreCase("membroDelComitatoDiAteneo"))){
										%><option value="membroDelComitatoDiAteneo">Membro del comitato di ateneo</option><%
									}
								
								%>
								</select>
						</td>
						</tr>
						<tr><td></td><td>
							<table border=0>
								<tr>
									<td><input type="button" onclick="checkSubmit()" value="Modifica"/></form></td>
									<td><form action="/vviser/gu/admin.jsp" method="POST"><input type="submit" value="Annulla"/></form></td>
								</tr>
							</table>				
							
						</td></tr>
					</table>
			</fieldset>
	<%}
%>
</body>
</html>
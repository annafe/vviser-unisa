<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrazione Utente</title>
</head>
<body>
	<%@include file="/gu/header.jsp" %>
	<fieldset>
		<legend>Registarzione Utente</legend>
		<form action="/vviser/AddUtenteServlet" method="POST">
			<p><label>Nome: <input type="text" name="nome"/></label></p>
			<p><label>Cognome: <input type="text" name="cognome"/></label></p>
			<p><label>Data di Nascita: <input type="date" name="datadinascita"/></label></p>
			<p><label>Comune di nascita: <input type="text" name="comunedinascita"/></label></p>
			<p><label>Provincia di nascita: <input type="text" name="provinciadinascita"/></label></p>
			<p><label>Codice Fiscale: <input type="text" name="codicefiscale"/></label></p>
			<p><label>Email: <input type="text" name="email"/></label></p>
			
			<!-- Provvisorio -->
			<p><label>Dipartimento(Provvisorio): <input type="text" name="dipartimento"/></label></p>
			<p><label>Tipologia (Provvisorio): <input type="text" name="tipologia"/></label></p>
			<p><label>Password: <input type="password" name="password"/></label></p>
			<p><label>Reinserisci password (Provvisorio): <input type="text" name="password1"/></label></p>
			
			<p><input type="submit"/>
			<input type="reset"/></p>
		</form> 
	</fieldset>
</body>
</html>
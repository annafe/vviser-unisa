<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%@page import="it.unisa.vviser.entity.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.*"%>
<%@page import="javax.servlet.*"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inserisci un nuovo evento di valutazione</title>
</head>
<body>
	<form action="InserisciNuovoEventoValutazione" method="POST">
		<label>Nome evento:</label><input type="text" name="nomeEvento"></input><br>
		<label>Numero pubblicazioni:</label><input type="text" name="numPubb"></input><br>
		<label>Scadenza:</label><input type="date" name="scadenza"></input><br>
		<label>Data Inizio:</label><input type="date" name="dataInizio"></input><br>
		<label>Data Fine:</label><input type="date" name="dataFine"></input><br>
		
		<input type="submit" value="Crea"></input>
		<input type="reset" value="Resetta"></input>
	</form>
</body>
</html>
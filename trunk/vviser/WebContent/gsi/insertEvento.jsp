<%-- 
    Author: Maria Vittoria Coda
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@page import="it.unisa.vviser.entity.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.*"%>

<form action="InserisciNuovoEventoServlet" method="POST">
		<label>Nome evento:</label><input type="text" name="nomeEvento"></input><br>
		<label>Numero pubblicazioni:</label><input type="text" value="numPubb"></input><br>
		<label>Scadenza:</label><input type="date" value="scadenza"></input><br>
		<label>Data Inizio:</label><input type="date" value="dataInizio"></input><br>
		<label>Data Fine:</label><input type="date" value="dataFine"></input><br>
		
		<input type="submit" name="creaEvento" value="Crea evento"></input>
		<input type="reset" value="Resetta"></input>
	</form>
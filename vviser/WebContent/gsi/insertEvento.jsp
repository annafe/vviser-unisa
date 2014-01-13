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
		<table>
		<tr><td><label>Nome evento:</label></td><td><input type="text" name="nomeEvento" required></input><td></tr>
		<tr><td><label>Numero pubblicazioni:</label></td><td><input type="text" name="numPubb" placeholder="numero pubblicazioni" required></input><td></tr>
		<tr><td><label>Scadenza:</label></td><td><input type="date" name="scadenza"  placeholder="YYYY-MM-DD" required></input><td></tr>
		<tr><td><label>Data Inizio:</label></td><td><input type="date" name="dataInizio"  placeholder="YYYY-MM-DD" required></input><td></tr>
		<tr><td><label>Data Fine:</label></td><td><input type="date" name="dataFine"  placeholder="YYYY-MM-DD" required></input><td></tr>
		
		<tr><td><input type="submit" name="creaEvento" value="Crea evento"></input></td>
		<td><input type="reset" value="Resetta"></input></td></tr>
		</table>
	</form>
	
 
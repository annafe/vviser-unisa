<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<header>
	<%@page import="it.unisa.vviser.entity.Utente"%>
	<%
		Utente utente = (Utente) session.getAttribute("utente");
		if(utente != null)
			out.println("Benvenuto "+utente.getNome());
		else
			out.println("Effettua il login");
			
	%>
</header>

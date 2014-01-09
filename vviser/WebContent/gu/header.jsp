<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%-- 
    Author: Antonio De Piano
--%>
	<%@page import="it.unisa.vviser.entity.Utente" %>
	<%
		Utente utente = (Utente) session.getAttribute("utente");
		if(utente == null)
		{
			//bisogna redirezionare alla pagina di errore
			 String redirectURL = "http://www.google.com/";
        response.sendRedirect(redirectURL);
			//request.getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
		}
	%>

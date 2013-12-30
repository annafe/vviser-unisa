<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<header>
	<%@page import="it.unisa.vviser.entity.Utente"%>
	<%
		Utente utente = (Utente) session.getAttribute("utente");
		if(utente != null){
			%>
			<table border=0>
			<tr>
				<td><image src="/vviser/images/logo.png"/ style="widht:100px;height:100px;"></td>
				<td><%
					out.println("Benvenuto "+utente.getNome());
					out.println("<form action=\"/vviser/LogoutServlet\" method=\"POST\">");
					out.println("<input type=\"submit\" value=\"Logout\"/>");
					out.println("</form>");%></td>
			</tr>
			</table><%
		}
		else
			request.getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
	%>
</header>

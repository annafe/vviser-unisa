<%@page import="java.lang.ProcessBuilder.Redirect"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visualizza utenti</title>
</head>
<body>
<!-- 
	<table border=1>
		<% 	
			for (int i=0; i<10; i++){
				%><tr><td><%out.println(i+"");%></td></tr> <% 
			}
		%>
	</table>
 -->
 <%
 	if (session.getAttribute("email")!=null){
 		//session present
 		
 	}
 	else{
 		response.sendRedirect("/error.jsp");
 	}
 %>
</body>
</html>
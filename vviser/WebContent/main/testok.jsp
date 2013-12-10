<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <jsp:useBean id="account" scope="session" class="it.unisa.vviser.entity.Account"></jsp:useBean> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
login ok
<%
if (account.getPassword()==null){
	String redirectURL = "main/login.jsp";
    response.sendRedirect(redirectURL);
	%>
	account null
	<%
}
else{
	String nome;
	String password;
	out.println(account.getNome());
	out.println(account.getCognome());
	out.println(account.getPassword());
	
}
%>
</body>
</html>
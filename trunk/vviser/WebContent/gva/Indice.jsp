<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
    Author: Angiuoli Salvatore
--%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Validazione prodotti dipartimento</title>
</head>
<body>
<%
//setto la sessione(provvisorio per provare)

	//HttpSession sessione = request.getSession();
	
	
%>
<form id="mod" action="../VisualizzaProdottiNonValidatiServlet" method="POST">
	<button type="submit" name="sel">Visualizza prodotti da validare</button>
</form>
</body>
</html>
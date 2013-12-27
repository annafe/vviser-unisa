<%-- 
    Author: Angiuoli Salvatore
--%>
<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<title>Validazione prodotti dipartimento</title>
</head>
<body>
<%
//setto la sessione(provvisorio per provare)

	HttpSession sessione = request.getSession();
	
	
%>
<form id="mod" action="/VisualizzaProdottiNonValidatiServlet" method="POST">
	<button type="submit" name="sel">Visualizza prodotti da validare</button>
</form>
</body>
</html>
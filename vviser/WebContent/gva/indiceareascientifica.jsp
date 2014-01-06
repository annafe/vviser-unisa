<%-- 
    Author: Angiuoli Salvatore
--%>
<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<title>Validazione prodotti area sceintifica</title>
</head>
<body>
<%
//setto la sessione(provvisorio per provare)
//in realtà l'email si deve recuperare dalla 
//sessione settata con il login
	HttpSession sessione = request.getSession();
	
	
%>
<form id="mod" action="../VisualizzaProdottiValidatiDipartimentoServlet" method="POST">
	<button type="submit" name="sel">Visualizza prodotti da validare</button>
</form>
</body>
</html>
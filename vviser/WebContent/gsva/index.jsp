<%-- 
    Author: Giuseppe Sabato
--%>
<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<title>Sottometti Prodotti</title>
</head>
<body>
<%
//setto la sessione(provvisorio per provare)
//in realtà l'email si deve recuperare dalla 
//sessione settata con il login
	HttpSession sessione = request.getSession();
	sessione.setAttribute("sessEmail","ivisconti@unia.it");
	
%>
<form id="mod" action="ServletVisualizzaProdotti" method="POST">
	<button type="submit" name="sel">Sottometti Prodotti a Valutazione</button>
</form>
</body>
</html>
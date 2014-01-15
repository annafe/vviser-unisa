<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%-- 
    Author: Antonio De Piano
--%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title></title>
</head>
<body>
<input type="button" value="Home" class="pulsante" onclick="document.location.href='/vviser/gu/gu.jsp';">
		<input type="button" value="Ricerca Prodotti" class="pulsante" onclick="document.location.href='/vviser/gpr/RicercaPrivataProdotto.jsp';">
		<input type="button" value="Gestione Prodotti" class="pulsante" onclick="document.location.href='/vviser/gpr/gpr.jsp';">
		<input type="button" value="Gestione Validazione" class="pulsante" onclick="document.location.href='/vviser/direttore/home_direttore.jsp';">
		<input type="button" value="Gestione Valutazione" class="pulsante" onclick="document.location.href='/vviser/gsva/gsva.jsp';">
		<input type="button" value="Gestione Notifiche" class="pulsante">
		<input type="button" value="Guida" class="pulsante" onclick="document.location.href='/vviser/ManualeUtente.docx';">
		<input type="button" value="Logout" class="pulsante" onclick="document.location.href='/vviser/main/login.jsp';" style="background-color:tomato;">
</body>
</html>
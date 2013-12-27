<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ricerca privata prodotti</title>

</head>
<body>
	<%@include file="/gu/header.jsp" %>
	<fieldset style="width:200px;">
		<legend>Catalogo</legend>
		<input type="radio" form="searchForm" name="catalogo" value="pubblico">Pubblico
		<input type="radio" form="searchForm" name="catalogo" value="personale">Personale
	</fieldset>
	<%@include file="/gpr/ricercaProdotto.jsp" %>
</body>
</html>
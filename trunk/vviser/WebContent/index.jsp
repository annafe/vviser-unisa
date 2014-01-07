<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%-- 
    Author: Antonio De Piano
--%>
<html>
<head>
    <title>VViSeR</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/stile.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<header id="container-header">
	<header id="header-main"></header>
	<nav>
	<!--
		<input type="button" value="Home" class="pulsante">
		<input type="button" value="Contatti" class="pulsante">
		<input type="button" value="Documenti" class="pulsante">
		<input type="button" value="Presentazione" class="pulsante">
		<input type="button" value="F.A.Q" class="pulsante">
	-->	
	</nav>
</header>
<section id="container-section">
	<section id="login">
		<a href="main/login.jsp">Accedi al sistema..</a>
	</section>
	<section id="search-prod">
		<a href="main/search_prod.jsp">Ricerca prodotti</a>
	</section>
	<section id="documenti">
		<a href="main/doc.jsp">Documenti</a>
	</section>
	<section id="presentazione">
		<a href="main/presentazione.jsp">Presentazione</a>
	</section>
	<section id="faq">
		<a href="main/faq.jsp">F.A.Q</a>
	</section>
	<section id="altro">
		<a href="main/altro.jsp">altro..</a>		
	</section>
</section>
<footer id="container-footer">
		<!--  Pagina contenete il messaggio da inglobare nel footer -->
		<%@ include file="../layout/footer.jsp" %>
</footer>
</body>
</html>

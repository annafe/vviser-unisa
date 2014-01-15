<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%-- 
    Author: Antonio De Piano
--%>
<html>
<head>
	<title>VViSeR</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<link href="/vviser/css/stile1.css" rel="stylesheet" type="text/css"/>
</head>

<body>

<header id="container-header">
<!--  contiene il logo  -->
</header>	

<nav>
		<!-- Pagina contenente i bottoni del menu -->
		<%@ include file="../gu/gu_menu.jsp" %>
</nav>

<section id="container-section">

	<section id="section-menu"> 
		<!-- Pagina contenente le funzionalità -->
		<%@ include file="direttore_funz.jsp" %>
    </section>

    <section id="section-main">
    	<!--  Pagina contenente il contenuto -->
    	<%
    	Utente utente = (Utente) session.getAttribute("utente");
    	String tipoUtente=utente.getTipologia();
    	if(tipoUtente.equalsIgnoreCase("direttoreDiDipartimento"))
    	{			
    	%>
    	<%@ include file="direttore_content.jsp" %>
    	<%}
    	else if (tipoUtente.equalsIgnoreCase("membroDelComitatoDiAreaDidattica"))
    	{%>
    <%@ include file="../areascientifica/area_content.jsp" %>
    <%}
    else 
    	{%>
    <%@ include file="../error.jsp" %>
    <%} %>
    </section>

</section>

<footer id="container-footer">
		<!--  Pagina contenete il messaggio da inglobare nel footer -->
		<%@ include file="../layout/footer.jsp" %>
</footer>

</body>
</html>
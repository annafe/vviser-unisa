<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%-- 
    Author: Maria Vittoria Coda
--%>
<%@page import="it.unisa.vviser.entity.*"%>


<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dettagli evento selezionato</title>
<link href="/vviser/css/stile1.css" rel="stylesheet" type="text/css"/>
</head>

<body>

<header id="container-header">
<!--  contiene il logo  -->
</header>	

<nav>
		<!-- Pagina contenente i bottoni del menu -->
		<%@ include file="gsi_menu.jsp" %>
</nav>

<section id="container-section">

	<section id="section-menu"> 
		<!-- Pagina contenente le funzionalità -->
		<%@ include file="gestioneEventiValutazione_funz.jsp" %>
		
    </section>

    <section id="section-main">
    	<!--  Pagina contenente il contenuto -->
    	<%@ include file="dettagliEvento.jsp" %>
    </section>

</section>

<footer id="container-footer">
		<!--  Pagina contenete il messaggio da inglobare nel footer -->
		<%@ include file="../layout/footer.jsp" %>
</footer>

</body>
</html>
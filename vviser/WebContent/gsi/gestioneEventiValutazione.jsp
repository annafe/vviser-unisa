<!--  Author: Maria Vittoria Coda -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>Gestione Eventi di Valutazione</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
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
		<!-- Pagina contenente le funzionalita  -->
			<%@ include file="gestioneEventiValutazione_content.jsp" %>
    </section>

    <section id="section-main">
    	<!--  Pagina contenente il contenuto -->
    	<%@ include file="gsi_content.jsp" %>
    </section>

</section>


<footer id="container-footer">
		<!--  Pagina contenete il messaggio da inglobare nel footer -->
		<%@ include file="../layout/footer.jsp" %>
</footer>

</body>
</html>
<%-- 
    Author: Giuseppe Sabato
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="it.unisa.vviser.entity.*"%>
   
<html>
<head>
	<title>VViSeR</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<link href="/vviser/css/stile1.css" rel="stylesheet" type="text/css"/>
	<link href="/vviser/css/stile2.css" rel="stylesheet" type="text/css"/>
	<style>
		table{padding-top:10%;}
		th{color:ORANGERED;}
		td{text-align:center;}
	</style>
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
		<%@ include file="gsva_funz.jsp" %>
    </section>

    <section id="section-main">
	    	
	    	<section id="gpr_content">
	    	<nav class="navigation">
					<ul>
						<li>
							<a href="/vviser/gpr/gpr.jsp">Gestione Valutazione</a>
						</li>
						<li>
							>>fhfffghhh
						</li>
					</ul>
				</nav>
		    	<!--  Pagina contenente il contenuto -->
		    	<%@ include file="visualizzaLista.jsp" %>
	    	</section>
	    	
	    	<section id="gpr_fast_menu">
	    	<%@ include file="gsva_fast_menu.jsp" %>
    		</section>
    
    </section>

</section>

<footer id="container-footer">
		<!--  Pagina contenete il messaggio da inglobare nel footer -->
		<%@ include file="../layout/footer.jsp" %>
</footer>

</body>
</html>
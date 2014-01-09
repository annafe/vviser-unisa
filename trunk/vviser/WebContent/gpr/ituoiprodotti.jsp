<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%-- 
    Author: Romano Simone 0512101343
--%>
<html>
<head>
	<title>VViSeR</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<link href="../css/stile1.css" rel="stylesheet" type="text/css"/>
</head>

<body>

<header id="container-header">
<!--  contiene il logo  -->
</header>	

<nav>
		<!-- Pagina contenente i bottoni del menu -->
		<%@ include file="gpr_menu.jsp" %>
</nav>

<section id="container-section">

	<section id="section-menu"> 
		<!-- Pagina contenente le funzionalità -->
		<%@ include file="gpr_funz.jsp" %>
    </section>

    <section id="section-main">
	    	
	    	<section id="gpr_content">
		    	<!--  Pagina contenente il contenuto -->
		    	<%@ include file="ituoiprodottiMain.jsp" %>
	    	</section>
	    	
	    	<section id="gpr_fast_menu">
	    	<%@ include file="gpr_fast_menu.jsp" %>
    		</section>
    
    </section>

</section>

<footer id="container-footer">
		<!--  Pagina contenete il messaggio da inglobare nel footer -->
		<%@ include file="../layout/footer.jsp" %>
</footer>

</body>
</html>

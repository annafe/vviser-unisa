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
<%
/*@include file="/gu/header.jsp"; */
%>

<header id="container-header">
<!--  contiene il logo  -->
</header>	

<nav>
		<!-- Pagina contenente i bottoni del menu -->
		<%@ include file="gu_menu.jsp" %>
</nav>

<section id="container-section">

	<section id="section-menu"> 
		<!-- Pagina contenente le funzionalità -->
		<%@ include file="gu_funz.jsp" %>
    </section>

    <section id="section-main">
	    	
	    	<section id="gpr_content">
		    	<!--  Pagina contenente il contenuto -->
		    	<%@ include file="gu_content.jsp" %>
	    	</section>
	    	
	    	<section id="gpr_fast_menu">
	    	<%@ include file="gu_fast_menu.jsp" %>
    		</section>
    </section>

</section>

<footer id="container-footer">
		<!--  Pagina contenete il messaggio da inglobare nel footer -->
		<%@ include file="../layout/footer.jsp" %>
</footer>

</body>
</html>
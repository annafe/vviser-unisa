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
		<%@ include file="/gsi/gsi_menu.jsp" %>
</nav>

<section id="container-section">

	<section id="section-menu"> 
		<!-- Pagina contenente le funzionalità -->
		<%@ include file="/gsi/gsi_funz.jsp" %>
    </section>

    <section id="section-main" style="overflow:scroll;">
    	<!--  Pagina contenente il contenuto -->
    	<table align="center">
    		<tr><td><%@ include file="modifyAccountMain.jsp" %></td></tr>
    	</table>
    </section>

</section>

<footer id="container-footer">
		<!--  Pagina contenete il messaggio da inglobare nel footer -->
		<%@ include file="../layout/footer.jsp" %>
</footer>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%-- 
    Author: Angiuoli Salvatore
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
		<%@ include file="../areascientifica/area_menu.jsp" %>
</nav>

<section id="container-section">

	<section id="section-menu"> 
		<!-- Pagina contenente le funzionalità -->
		<%@ include file="../areascientifica/area_funz.jsp" %>
    </section>

    <section id="section-main">
    	<!--  Pagina contenente il contenuto -->
    	<table>
        <tr>
            <th colspan="2">Seleziona Prodotti</th>    
        </tr>
        <tr>
        	<th>Spunta</th>
            <th>elenco della notifica</th>  
        </tr>
        
        <% 
        
        out.println("<tr>");
            out.println("<td><input type=\"checkbox\"' value='"+"errore lessicale"+"' /></td>");
            out.println("<td>"+"errore lessicale"+"</td>");
        out.println("<tr>");
        
        out.println("<tr>");
      out.println("<td><input type=\"checkbox\"' value='"+"errore rivista"+"' /></td>");
        out.println("<td>"+"errore rivista"+"</td>");
    out.println("<tr>");
        
        %>
    </table>
    </section>

</section>

<footer id="container-footer">
		<!--  Pagina contenete il messaggio da inglobare nel footer -->
		<%@ include file="../layout/footer.jsp" %>
</footer>

</body>
</html>
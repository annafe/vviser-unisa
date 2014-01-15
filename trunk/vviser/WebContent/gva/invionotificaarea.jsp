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
		<%@ include file="../gu/gu_menu.jsp" %>
</nav>

<section id="container-section">

	<section id="section-menu"> 
		<!-- Pagina contenente le funzionalità -->
		<%@ include file="../areascientifica/area_funz.jsp" %>
    </section>

    <section id="section-main">
    	<!--  Pagina contenente il contenuto -->
    	<form id="mod1" action="/vviser/areascientifica/home_area.jsp" method="POST" onsubmit="return controlla();">
    	<table>
        <tr>
            <th colspan="2">Seleziona Prodotti</th>    
        </tr>
        <tr>
        	<th>Spunta</th>
            <th>elenco della notifica</th>  
        </tr>
    
    
    
       
        <% 
        
        
        String[] p= request.getParameterValues("selProd");

        
        out.println("<tr>");
        out.println("<td colspan=\"2\"><input type=\"text\"' value='"+p[0]+"' name=\"isbn\" /></td></tr>");
        
        out.println("<tr>");
            out.println("<td><input type=\"radio\"' value='"+"errore lessicale"+"' name=\"messaggio\"></td>");
            out.println("<td>"+"errore lessicale"+"</td>");
        out.println("<tr>");
        
        out.println("<tr>");
      out.println("<td><input type=\"radio\"' value='"+"errore rivista"+"' name=\"messaggio\"></td>");
        out.println("<td>"+"errore rivista"+"</td>");
    out.println("<tr>");
    
    
        %>
    </table>
    <button type="submit" name="Sottometti">Annulla</button>
    <button type="submit" name="invia" formaction="../InvioNotificaValidazioneServlet">Notifica</button>
        
    </section>

</section>

<footer id="container-footer">
		<!--  Pagina contenete il messaggio da inglobare nel footer -->
		<%@ include file="../layout/footer.jsp" %>
</footer>

</body>
</html>
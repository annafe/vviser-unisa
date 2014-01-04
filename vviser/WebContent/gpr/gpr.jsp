<%@ page import="java.lang.ProcessBuilder.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%-- 
    Author: Antonio De Piano
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>VViSeR</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/stile1.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<header id="container-header">
	
</header>	

<nav>
<input type="button" value="Profilo" class="pulsante" onclick="document.location.href='../index.jsp';">
		<input type="button" value="Prodotti" class="pulsante" onclick="document.location.href='./doc.jsp';">
		<input type="button" value="Ricerca" class="pulsante" onclick="document.location.href='./presentazione.jsp';">
		<input type="button" value="Validazione" class="pulsante" onclick="document.location.href='./faq.jsp';">
		<input type="button" value="Valutazione" class="pulsante" onclick="document.location.href='./altro.jsp';">
        
		<input type="button" value="Miur" class="pulsante" onclick="document.location.href='./altro.jsp';">
</nav>

<section id="container-section">
	<section id="section-menu">
    </section>
    <section id="section-main">
    </section>
</section>

<footer id="container-footer">
TEAM EIGHT
</footer>

</body>
</html>

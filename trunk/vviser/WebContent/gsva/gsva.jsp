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
</head>

<body>
<%
	Utente utente = (Utente) session.getAttribute("utente");
	HttpSession s= request.getSession();
	s.setAttribute("sessEmail",utente.getEmail());
	String tipoUtente=utente.getTipologia();
	s.setAttribute("sessTipoUtente", tipoUtente);
	//out.println("<input type=\"text\" id=\"tipoNasc\" value="+tipoUtente+" hidden />");
	
%>
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
							<a href="/vviser/gsva/gsva.jsp">Gestione Valutazione</a>
						</li>
						<li>
						
						</li>
					</ul>
				</nav>
		    	<!--  Pagina contenente il contenuto -->
		    	<!--%@ include file="gestioneValutazione.jsp" %-->
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

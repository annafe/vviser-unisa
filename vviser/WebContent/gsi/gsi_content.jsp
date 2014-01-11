   <%-- 
    Author: Maria Vittoria Coda
--%>
<%@ page import="it.unisa.vviser.storage.*"%>
<%@ page import="it.unisa.vviser.entity.*"%>
<!-- 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title></title>
</head>
<body>
 -->
 <% Utente currentUser = (Utente) session.getAttribute("utente"); %>
Benvenuto 
<% out.println(currentUser.getNome()); %>
<% out.println(currentUser.getCognome()); %>
<br/>
seleziona una funzionalità per iniziare
<!-- 
</body>
</html>
 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="it.unisa.vviser.storage.DBGestioneProdotto"%>
<%@ page import="it.unisa.vviser.entity.Prodotto"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>I tuoi prodotti</title>
</head>
<body>
<table>
<tr>
<td><% 
DBGestioneProdotto dbg=DBGestioneProdotto.getInstance();


dbg.visualizzaProdottiPersonali(emailUtente);
%>
</table>
</body>
</html>
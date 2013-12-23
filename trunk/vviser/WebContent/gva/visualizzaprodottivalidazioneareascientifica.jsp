<%-- 
    Author: Angiuoli Salvatore
--%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="it.unisa.vviser.entity.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="org.json.JSONObject"%>
<%@ page session="true"%>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8"/>
    <title>Seleziona un prodotto</title>
</head>

<body>

		<th colspan="1">Lista prodotti sottomessi a validazione</th>
	</tr>
	<tr>
		<th>Titolo</th>
		
	</tr>
	<%
	Prodotto prodotti;
	ArrayList<Prodotto>prodotto =(Prodotto)request.getAttribute("lista");

		for(int i=0;i<prodotto.size();i++)
		{
			out.println("<tr>");
				out.println("<td>"+prodotto.get(i).getTitolo()+"</td>");
			out.println("<tr>");
		}
		
	%>
</table>




</body>
</html>
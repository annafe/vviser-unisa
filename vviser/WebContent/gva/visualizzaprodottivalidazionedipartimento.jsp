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
    <title>Seleziona prodotti</title>
    <script type="text/javascript">
    	function controlla()
    	{
    		 var chk = document.getElementsByTagName('input');
    		    var len = chk.length;

    		    for (var i = 0; i < len; i++)
    		    {
    		        if (chk[i].type === 'checkbox' && chk[i].checked==true)
    		        {
    		        	return true;
    		        }
    		    }
    		    return false;
    	}
    </script>
</head>

<body>

<%
	ArrayList<Prodotto> prodotti=new ArrayList<Prodotto>();
	prodotti=(ArrayList<Prodotto>)request.getAttribute("listaprodotti");
    
    
   
%>
<form id="mod1" action="../ValidazioneDipartimentoServlet" method="POST" onsubmit="return controlla();">
    <table>
        <tr>
            <th colspan="2">Seleziona Prodotti</th>    
        </tr>
        <tr>
        	<th>Spunta</th>
            <th>Titolo</th>  
        </tr>
        <% 
        for(int i=0;i<prodotti.size();i++)
        {
        out.println("<tr>");
            out.println("<td><input type=\"checkbox\" name=\"selProd\" id='"+i+"' value='"+prodotti.get(i).getIsbn()+"' /></td>");
            out.println("<td>"+prodotti.get(i).getTitolo()+"</td>");
        
        out.println("<tr>");
        }
        
        %>
    </table>
    <button type="submit" name="sottometti">Validazione</button>
    <button type="submit" name="sottometti" formaction="../InvioNotificaValidazioneServlet">Notifica</button>
</form>


</body>
</html>
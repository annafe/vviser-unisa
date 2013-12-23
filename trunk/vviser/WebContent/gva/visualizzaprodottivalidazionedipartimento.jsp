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
    	i=0;
    	function attivaP(check)
    	{
    		id=check.getAttribute('id');
    		n=document.getElementById("tn").value;
    		if(document.getElementById(id).checked==true)
    		{
    			document.getElementById("t"+id).disabled=false;
    			
    		}
    		else
    		{
    			i--;
    			document.getElementById("t"+id).disabled=true;
    		}
    		

    	}
    </script>
</head>

<body>

<%
	ArrayList<Prodotto> prodotti=new ArrayList<Prodotto>();
	prodotti=(ArrayList<Prodotto>)request.getAttribute("prod");
    
    
   
%>
<form id="mod1" action="VisualizzaProdottiValidatiDipartimentoServlet" method="POST">
    <table>
        <tr>
            <th colspan="3">Seleziona Prodotti</th>    
        </tr>
        <tr>
        	<th>Spunta</th>
            <th>Titolo</th>  
        </tr>
        <% 
        for(int i=0;i<prodotti.size();i++)
        {
        out.println("<tr>");
            out.println("<td><input type=\"checkbox\" name=\"selProd\" id="+i+" value="+prodotti.get(i).getIsbnTitleProdotto()+" onclick=\"attivaP(this)\""+"/></td>");
            out.println("<td>"+prodotti.get(i).getTitolo()+"</td>");
            String idInput="t"+i;
            //System.out.println(idInput);
            out.println("<td><input id="+idInput+" type=\"text\" name=\"priorita\" disabled=\"true\" /></td>");
        out.println("<tr>");
        }
        
        %>
    </table>
    <button type="submit" name="sottometti">Validazione</button>
</form>


</body>
</html>

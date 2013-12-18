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
    	function attivaP(check)
    	{
    		/*id=check.getAttribute('id');
    		if(document.getElementById(id).checked==true)
    			alert("entro");
    		else
    			alert("esco");*/
    		

    	}
    </script>
</head>

<body>

<%
	ArrayList<Prodotto> p=new ArrayList<Prodotto>();
	p=(ArrayList<Prodotto>)request.getAttribute("prod");
    int numProdMax=(Integer)request.getAttribute("numProdMax");
    //out.println("<input id=\"tn\" type=\"text\" value="+numProdMax+" hidden/>");
	ArrayList<ProdottoValutazione> prodottiValutazione=new ArrayList<ProdottoValutazione>();
	for (int i=0;i<p.size();i++)
	{
		ProdottoValutazione pv=new ProdottoValutazione(p.get(i).getIsbn(),p.get(i).getTitolo(),0);
		prodottiValutazione.add(pv);
	}

%>
<form id="mod1" action="ServletInsertProdottiValutazione" method="POST">
    <table>
        <tr>
            <th colspan="3">Seleziona Prodotti</th>    
        </tr>
        <tr>
        	<th>Spunta</th>
            <th>Titolo</th>
            <th>Priorita'</th>    
        </tr>
        <% 
        for(int i=0;i<prodottiValutazione.size();i++)
        {
        out.println("<tr>");
            out.println("<td><input type=\"checkbox\" name=\"selProd\" id="+i+" value="+prodottiValutazione.get(i).toString()+" onclick=\"attivaP(this)\""+"/></td>");
            out.println("<td>"+prodottiValutazione.get(i).getTitle()+"</td>");
            String idInput="t"+i;
            //System.out.println(idInput);
            out.println("<td><input id="+idInput+" type=\"text\" name=\"priorita\"/></td>");
        out.println("<tr>");
        }
        
        %>
    </table>
    <button type="submit" name="sottometti">Sottometti</button>
</form>


</body>
</html>

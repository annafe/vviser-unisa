<%@page import="java.util.List"%>
<%@page import="it.unisa.vviser.entity.Tipologia"%>
<%@page import="it.unisa.vviser.storage.DBTipologie"%>
<%@page import="it.unisa.vviser.storage.DBGestioneProdotto"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="javax.xml.crypto.Data"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Catalogo dei prodotti di ricerca</title>
</head>
<body>
	<form action="/vviser/RicercaPubblicaProdottoServlet" method="POST">
		<fieldset>
			<legend>Selezionare i parametri di ricerca</legend>
			
			<p>
				<label for="tipologia" >Tipologia</label>
				<select name="tipologia" id="tipologia">
	    			<% 
	    				DBTipologie dbt = new DBTipologie();
						List<Tipologia> list = dbt.getTipologie();
						for(Tipologia t : list)
							out.println("<option value=\""+t.getNome()+"\">"+t.getNome()+"</option>");
	    				
	    			%>
	    		</select>	
			</p>
			
			<p>			
				<label for="anno_dal">Anno dal</label>
				<select name="anno_dal" id="anno_dal">
	    			<%
	    				Calendar c = Calendar.getInstance();
						int year = c.get(Calendar.YEAR);
	    				for(int i=0;i<30;i++){ 
	    					out.println("<option value=\""+year+"\">"+year+"</option>");
	    					year--;
	    				}
	    			%>
				</select>
				
				<label for="anno_al">Anno al</label>
				<select name="anno_al" id="anno_al">
	    			<%
	    				c = Calendar.getInstance();
						year = c.get(Calendar.YEAR);
	    				for(int i=0;i<30;i++){ 
	    					out.println("<option value=\""+year+"\">"+year+"</option>");
	    					year--;
	    				}
	    			%>
				</select>
			</p>	
			
			<p>
				<label for="titolo">Titolo</label>
				<input type="text" name="titolo" placeholder="Titolo prodotto"/>
			</p>
			<p>
				<label for="titolo_rivista">Titolo Rivista</label>
				<input type="text" name="titolo_rivista" placeholder="Titolo rivista"/>
			</p>
			<p>
				<label for="issn">ISSN rivista</label>
				<input type="text" name="issn" placeholder="ISSN rivista"/>
			</p>
			<p>
				<input type="submit"/>
			</p>
		</fieldset>
	</form>
</body>
</html>
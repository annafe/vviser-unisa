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
<style type="text/css">
	.criterio{
		display:none;
		position:absolute;
		left:264px;
		top:94px;
	}
</style>

<script type="text/javascript">
	function show(criterio){
		document.getElementById("tipologia").style.display="none";
		document.getElementById("titolo_prodotto").style.display="none";
		document.getElementById("titolo_rivista").style.display="none";
		document.getElementById("issn_rivista").style.display="none";
		document.getElementById("anni").style.display="none";
		document.getElementById(criterio.value).style.display="block";
	}
</script>


</head>
<body>
	<form action="/vviser/RicercaPubblicaProdottoServlet" method="POST">
		<fieldset>
			<legend>Selezionare i parametri di ricerca</legend>
			<div>
				<fieldset style="width:200px;">
					<legend>Criterio di ricerca</legend>
					<input type="radio" name="tipo_ricerca" value="tipologia" onchange="show(this)">Tipologia<br>
					<input type="radio" name="tipo_ricerca" value="titolo_prodotto" onchange="show(this)">Titolo prodotto<br>
		    		<input type="radio" name="tipo_ricerca" value="titolo_rivista" onchange="show(this)">Titolo rivista<br>
		    		<input type="radio" name="tipo_ricerca" value="issn_rivista" onchange="show(this)">ISSN rivista<br>
		    		<input type="radio" name="tipo_ricerca" value="anni" onchange="show(this)">Periodo<br>
				</fieldset>
			</div>
			<div class="criterio" id="tipologia">
				<label for="tipologia" >Tipologia</label>
				<select name="tipologia" id="tipologia">
	    			<% 
	    				DBTipologie dbt = new DBTipologie();
						List<Tipologia> list = dbt.getTipologie();
						for(Tipologia t : list)
							out.println("<option value=\""+t.getNome()+"\">"+t.getNome()+"</option>");
	    				
	    			%>
	    		</select>	
	    		<input type="submit"/>
			</div>
			<div class="criterio" id="anni">			
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
				<input type="submit"/>
			</div>	
			
			<div class="criterio" id="titolo_prodotto">
				<label for="titolo">Titolo</label>
				<input type="text" name="titolo" placeholder="Titolo prodotto"/>
				<input type="submit"/>
			</div>
			<div class="criterio" id="titolo_rivista">
				<label for="titolo_rivista">Titolo Rivista</label>
				<input type="text" name="titolo_rivista" placeholder="Titolo rivista"/>
				<input type="submit"/>
			</div>
			<div class="criterio" id="issn_rivista">
				<label for="issn_rivista">ISSN rivista</label>
				<input type="text" name="issn_rivista" placeholder="ISSN rivista"/>
				<input type="submit"/>
			</div>
		</fieldset>
	</form>
	<script type="text/javascript">
	radio=document.getElementsByName("tipo_ricerca");
	for(i=0; i<radio.length;i++)
		if(radio[i].checked){
			show(radio[i]);
			break;
		}
	</script>
</body>
</html>
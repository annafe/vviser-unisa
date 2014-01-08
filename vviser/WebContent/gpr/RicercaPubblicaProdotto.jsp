<%@page import="java.util.ArrayList"%>
<%@page import="it.unisa.vviser.entity.Prodotto"%>
<%@page import="java.util.Calendar"%>
<%@page import="it.unisa.vviser.entity.Tipologia"%>
<%@page import="java.util.List"%>
<%@page import="it.unisa.vviser.storage.DBTipologie"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>VViSeR</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/vviser/css/stile.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
	body{color:white;}
	nav{
		padding-top:0px;
		background-color:white;
		text-align:left;
	}
	input#x.pulsante{background-color:TOMATO;}
	section#container-section{height:290px;top:220px; background-color:yellowgreen;}
	footer#container-footer{top:520px;}
	table{
		color:white;
		text-align:left;
		font-size:12pt;
	}
	fieldset{border:5px solid orangered;}
	section#search-catal{height:290px;}
	section#mod-search{height:290px; width:530px; left:160px;padding-right:20px;}
	#risultati{
		position:absolute;
		top:180px;
		left:160px;
		width:calc(100% - 165px);
		height:100px;
	}
	#risultati>table{
		width:100%;
	}
</style>
</head>
<body>
	<header id="container-header">
	<header id="header-main"></header>
	<nav>
		<input type="button" value="Home" class="pulsante" onclick="document.location.href='../index.jsp';">
		<input type="button" value="Documenti" class="pulsante" onclick="document.location.href='./doc.jsp';">
		<input type="button" value="Presentazione" class="pulsante" onclick="document.location.href='./presentazione.jsp';">
		<input type="button" value="F.A.Q" class="pulsante" onclick="document.location.href='./faq.jsp';">
		<input type="button" value="altro.." class="pulsante" onclick="document.location.href='./altro.jsp';">
	</nav>
	</header>
	<section id="container-section">
	<section id="search-catal">
		Catalogo Prodotti
	</section>
	
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
	
	<section id="mod-search">
			<form action="/vviser/RicercaPubblicaProdottoServlet" method="POST" id="searchForm">
		<fieldset>
			<legend>Ricerca</legend>
			<table>
				<tr>	
					<td style="width:200px;">
						<% 
							String value="";
							if(request.getParameter("tipo_ricerca")!=null) 
								value=request.getParameter("tipo_ricerca");
						%>
						
						<input type="radio" name="tipo_ricerca" value="tipologia" 
						<% if(value.equals("tipologia")) out.print(" checked "); %>
						onchange="show(this)">Tipologia<br>
						
						<input type="radio" name="tipo_ricerca" value="titolo_prodotto" 
						<% if(value.equals("titolo_prodotto")) out.print(" checked "); %>
						onchange="show(this)">Titolo prodotto<br>
				    	
				    	<input type="radio" name="tipo_ricerca" value="titolo_rivista" 
				    	<% if(value.equals("titolo_rivista")) out.print(" checked "); %>
				    	onchange="show(this)">Titolo rivista<br>
				    	
				    	<input type="radio" name="tipo_ricerca" value="issn_rivista" 
				    	<% if(value.equals("issn_rivista")) out.print(" checked "); %>
				    	onchange="show(this)">ISSN rivista<br>
				    	
				    	<input type="radio" name="tipo_ricerca" value="anni" 
				    	<% if(value.equals("anni")) out.print(" checked "); %>
				    	onchange="show(this)">Periodo<br>
					</td>
					
					<td style="width:400px;">
						<div class="criterio" id="tipologia" style="display:none;">
							<% 
								String tipologia="";
								if(value.equals("tipologia")) 
									tipologia = request.getParameter("tipologia");
							%>
							<label for="tipologia" >Tipologia</label>
							<select name="tipologia" id="tipologia">
					    		<% 
					    			DBTipologie dbt = new DBTipologie();
									List<Tipologia> list = dbt.getTipologie();
									for(Tipologia t : list){
										out.print("<option value=\""+t.getNome()+"\"");
										if(tipologia.equals(t.getNome()))
											out.print(" selected ");
										out.print(">");
										out.println(t.getNome()+"</option>");
									}
					    		%>
					    	</select>	
					    	<button type="submit"><img src="/vviser/images/search-icon.png" alt="Cerca"></button>
						</div>
						<div class="criterio" id="anni" style="display:none;">
							<%
								String da="";
								String a="";
								if(value.equals("anni")){
									da=request.getParameter("da");
									a=request.getParameter("a");
								}
									
							%>			
							<label for="da">Anno dal</label>
							<select name="da">
					    		<%
					    			Calendar c = Calendar.getInstance();
									int year = c.get(Calendar.YEAR);
					    			for(int i=0;i<30;i++){ 
					    				out.print("<option value=\""+year+"\"");
					    				if(da.equals(""+year)){
					    					out.print(" selected ");
					    				}
					    				out.println(">"+year+"</option>");
					    				year--;
					    			}
					    		%>
							</select>
							
							<label for="a"> al</label>
							<select name="a">
				    			<%
					    			c = Calendar.getInstance();
									year = c.get(Calendar.YEAR);
					    			for(int i=0;i<30;i++){ 
					    				out.print("<option value=\""+year+"\"");
					    				if(a.equals(""+year)){
					    					out.print(" selected ");
					    				}
					    				out.println(">"+year+"</option>");
					    				year--;
					    			}
				    			%>
							</select>
							<button type="submit"><img src="/vviser/images/search-icon.png" alt="Cerca"></button>
						</div>	
							
						<div class="criterio" id="titolo_prodotto" style="display:none;">
							<% 
								String text=null;
								if(value.equals("titolo_prodotto")) 
									text = request.getParameter("titolo_prodotto");
							%>
							<label for="titolo_prodotto">Titolo</label>
							<input type="text" name="titolo_prodotto" placeholder="Titolo prodotto"
							<% if(text != null) out.print(" value=\""+text+"\" "); %>
							/>
							<button type="submit"><img src="/vviser/images/search-icon.png" alt="Cerca"></button>
						</div>
						
						
						<div class="criterio" id="titolo_rivista" style="display:none;">
							<% 
								text=null;
								if(value.equals("titolo_rivista")) 
									text = request.getParameter("titolo_rivista");
							%>
							<label for="titolo_rivista">Titolo Rivista</label>
							<input type="text" name="titolo_rivista" placeholder="Titolo rivista"
							<% if(text != null) out.print(" value=\""+text+"\" "); %>
							/>
							<button type="submit"><img src="/vviser/images/search-icon.png" alt="Cerca"></button>
						</div>
						
						
						<div class="criterio" id="issn_rivista" style="display:none;">
							<% 
								text=null;
								if(value.equals("issn_rivista")) 
									text = request.getParameter("issn_rivista");
							%>
							<label for="issn_rivista">ISSN rivista</label>
							<input type="text" name="issn_rivista" placeholder="ISSN rivista"
							<% if(text != null) out.print(" value=\""+text+"\" "); %>
							/>
							<button type="submit"><img src="/vviser/images/search-icon.png" alt="Cerca"></button>
						</div>
					</td>
				</tr>
			</table>
		</fieldset>
		</form>
	</section>
	<script type="text/javascript">
		radio=document.getElementsByName("tipo_ricerca");
		for(i=0; i<radio.length;i++)
			if(radio[i].checked){
				show(radio[i]);
					break;
			}
	</script>

	
	<section id="risultati" style="overflow:auto;">
		<table>
		<%
			if(request.getAttribute("results") !=null){
				List<Prodotto> pr = (ArrayList<Prodotto>) request.getAttribute("results"); 
				for(Prodotto p : pr){
					out.println("<tr>");
					out.print("<td>"+p.getAnnoPubblicazione().get(Calendar.YEAR)+"</td><td>"+p.getTitolo() +"</td>");
					out.println("<td>");
					out.println("<form action=\"/vviser/VisualizzaDettaglioProdottoServlet\" method=\"POST\">");
					out.println("<input type=\"hidden\" name=\"isbn\" value=\""+p.getIsbn()+"\"/>");
					out.println("<input class=\"pulsante\" type=\"submit\" value=\"Dettagli\"/>");
					out.println("</form></td></tr>");
				}
			}
		%>
		</table>	
	</section>
</section>
<footer id="container-footer">
TEAM EIGHT
</footer>
</body>
</html>

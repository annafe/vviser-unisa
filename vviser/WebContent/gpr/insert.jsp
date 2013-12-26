<%@ page import="java.lang.ProcessBuilder.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="it.unisa.vviser.storage.DBTipologie"%>
<%@ page import="it.unisa.vviser.entity.Tipologia"%>
<%@ page import="it.unisa.vviser.storage.DBUtente"%>
<%@ page import="it.unisa.vviser.entity.Utente"%>
<%-- 
    Author: Antonio De Piano
--%>
<html>
<head>
<title>VViSeR</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/stile.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
body{color:white;height:670px;}
legend{color:ORANGERED;}
fieldset{border:5px solid ORANGERED;font-size:10pt;}
table{color:YELLOWGREEN;}
section#container-section{width:700px; height:380px;top:225px;}
section#prd{position:absolute;width:400px; height:410px;left:250px;}
section#menu{position:absolute;width:190px; height:150px;top:10px; left:10px;}
td{text-align:center;}
footer#container-footer{top:625px;}
p{display:block;}
p>a:link {color:yellowgreen;
	text-decoration:none;}      /* unvisited link */
p>a:visited {text-decoration:none;
	color:yellowgreen;}  /* visited link */
p>a:hover {cursor:pointer;
	text-decoration:none;
	color:yellowgreen;}  /* mouse over link */
p>a:active {color:yellowgreen;
	text-decoration:none;}  /* selected link */
</style>
<script type="text/javascript">
function controlla()
{
	document.modulo.isbn
}
function controlla()
{
	var f = document.modulo;
	if(((f.isbn.value == "" || f.titolo.value=="")|| f.data.value=="" ) || f.tipologia.value=="")
	{
		alert("Attenzione\n alcuni campi non sono stati compilati")
		f.isbn.focus();
		return false;
	}
	return true;
}
</script>
</head>
<body>

<% 
/*@include file="../gu/header.jsp" */
%>

<header id="container-header">
	
	<nav>
today is 28 Settembre 2013
	</nav>
</header>
<section id="container-section">
	<section id="menu">
		<p><a href="./gpr.html">Gestione prodotto</a>
	</section>
	<section id="prd">
	<form method="GET" action="../InserimentoProdottoServlet" name="modulo" onsubmit="return controlla();">
	<fieldset>
		<legend>Prodotto</legend>
		<table>
			<tr>
				<td>ISBN*</td>
				<td><input type="text" name="isbn" size="20" class="testo"></td>
			</tr>
			<tr>
				<td>Titolo*</td>
				<td><input type="text" name="titolo" size="20" class="testo"></td>
			</tr>
			<tr>
				<td>Anno pubblicazione*</td>
				<td><input type="text" name="data" size="20" class="testo"></td>
			</tr>
			<tr>
				<td>Formato Pubblicazione</td>
				<td><select name="formato_pub"><option>PDF</option><option>DOCX</option></select></td>
			</tr>
			<tr>
				<td>Codice DOI</td>
				<td><input type="text" name="doi" size="20" class="testo"></td>
			</tr>
			<tr>
				<td>Diffusione</td>
				<td><input type="text" name="diffusione" size="20" class="testo"></td>
			</tr>
			<tr>
				<td>Tipologia*</td>
				<td><select name="tipologia">
				<% 	
				DBTipologie dbt = DBTipologie.getInstance();
				ArrayList<Tipologia> list = dbt.getTipologie();
				for (int i=0; i<list.size(); i++)
				{
					%>
					<option><% 	out.print(list.get(i).getNome());
								%>
					</option>
					<% 
				}
				%>
			</select></td>
			</tr>
			<tr>
				<td>Note</td>
				<td><textarea name="note" class="testo"></textarea></td>
			</tr>
			<tr>
				<td>Collaboratori</td>
				<td><select name="collaboratori" multiple>
				<% 	
				DBUtente dbUser = new DBUtente();
				List<Utente> l = dbUser.visualizzaUtenti();
				for (int i=0; i<l.size(); i++)
				{
					%>
					<option><% String utente=l.get(i).getCognome();
						utente=utente.concat(" "+l.get(i).getNome()+" ");
						utente=utente.concat(l.get(i).getEmail());
						out.print(utente);
								%>
					</option>
					<% 
				}
				%>
			</select></td>
			</tr>
			<tr>
				<td>Descrizione Contenuti</td>
				<td><textarea name="descrizione" class="testo"></textarea></td>
			</tr>
			<tr>
				<td>Indirizzo web</td>
				<td><input type="text" name="indirizzoweb" size="20" class="testo"></td>
			</tr>
			<tr>
				<td>Parole chiavi</td>
				<td><input type="text" name="key" class="testo"></td>
			</tr>
			<tr>
				<td>Editore</td>
				<td><input type="text" name="editore" size="20" class="testo"></td>
			</tr>
			<tr>
				<td>Numero volume</td>
				<td><input type="text" name="num_volume" size="10" class="testo"></td>
			</tr>
			<tr>
				<td>Totale pagine</td>
				<td><input type="text" name="totalePagine" size="10" class="testo"></td>
			</tr>
			<tr>
				<td>Da Pagina</td>
				<td><input type="text" name="daPagina" size="10" class="testo"></td>
			</tr>
			<tr>
				<td>A Pagina</td>
				<td><input type="text" name="aPagina" size="10" class="testo"></td>
			</tr>
			<tr>
				<td colspan="2"><div class="centro"><input type="submit" value=" Salva " class="pulsante"><input type="reset" value=" Annulla " class="pulsante"></div></td>
			</tr>
			<tr>
				<td colspan="2">* I campi sono obbligatori</td>
			</tr>
		</table>
	</fieldset>
	</form>
	</section>
</section>
<footer id="container-footer">
TEAM EIGHT
</footer>
</body>
</html>

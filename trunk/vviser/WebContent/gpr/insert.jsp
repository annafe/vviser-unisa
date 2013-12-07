<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>VViSeR</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/stile.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
body{color:white;height:675px; border:5px solid blue;}
legend{color:ORANGERED;}
fieldset{border:5px solid ORANGERED;}
table{color:YELLOWGREEN;}
section#container-section{width:700px; height:400px;
	top:225px;}
section#prd{position:absolute;width:400px; height:400px;left:250px;}

section#menu{position:absolute;width:190px; height:390px;top:10px; left:10px;border:5px solid red;}
td{text-align:center;}



footer#container-footer{border:5px solid black;
	top:635px;}
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
</head>
<body>
<header id="container-header">
	<header id="header-main"></header>
	<nav>
today is 28 Settembre 2013
	</nav>
</header>
<section id="container-section">
	<section id="menu">
		<p><a href="./gpr.html">Gestione prodotto</a>
	</section>
	<section id="prd">
	<form method="GET" action="#" name="modulo">
	<fieldset>
		<legend>Prodotto</legend>
		<table>
			<tr>
				<td>ISBN</td>
				<td><input type="email" name="eml" size="20" class="testo"></td>
			</tr>
			<tr>
				<td>Titolo</td>
				<td><input type="password" name="pass" size="20" class="testo"></td>
			</tr>
			<tr>
				<td>Data Pubblicazione(mod database)</td>
				<td><input type="date" name="data" size="20" class="testo"></td>
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
				<td>Categoria</td>
				<td><select name="cat"><option>Articolo su rivista</option><option>...</option></select></td>
			</tr>
			<tr>
				<td>Note</td>
				<td><textarea name="note" class="testo"></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><div class="centro"><input type="submit" value=" Salva " class="pulsante"><input type="reset" value=" Annulla " class="pulsante"></div></td>
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

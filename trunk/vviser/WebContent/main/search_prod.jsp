<!DOCTYPE HTML>
<html>
<head>
    <title>VViSeR</title>
    <meta charset="UTF-8"/>
<link href="../css/stile.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
body{color:white;}
section#container-section{
	/*margin-top:15px;*/}
nav{padding-top:0px;
	background-color:white;
	text-align:left;}
input#x.pulsante{background-color:TOMATO;}
section#container-section{background-color:LIGHTGREEN;
		top:220px;
	}
footer#container-footer{top:490px;}
</style>
</head>
<body>
<header id="container-header">
	<header id="header-main"></header>
	<nav>
		<input type="button" value="Home" class="pulsante" onclick="document.location.href='index.html';">
		<input type="button" value="Documenti" class="pulsante">
		<input type="button" value="Presentazione" class="pulsante">
		<input type="button" value="F.A.Q" class="pulsante">
		<input type="button" value="altro.." class="pulsante">
	</nav>
</header>
<section id="container-section">
	<section id="search-catal">
		<input type="button" value="Catalogo Prodotti" class="pulsante" id="x">
	</section>
	
	<section id="mod-search">
			<form method="GET" action="#" name="modulo">
	<fieldset>
		<legend>Ricerca Prodotto</legend>
		<table>
			<tr>
				<td>E-mail</td>
				<td><input type="email" name="eml" size="20" class="testo"></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="pass" size="20" class="testo"></td>
			</tr>
			<tr>
				<td colspan="2"><div class="centro"><input type="submit" value=" Accedi.. " class="pulsante"><input type="reset" value=" Annulla " class="pulsante"></div></td>
			</tr>
			<tr>
				<td colspan="2"><a href="#" style="color:yellowgreen;font-size:12pt;">Password dimenticata ?</a></td>
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

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%-- 
    Author: Antonio De Piano
--%>
<html>
<head>
<title>VViSeR</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/stile.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
body{color:white;}
legend{color:ORANGERED;}
fieldset{border:5px solid ORANGERED; height:180px;}
table{color:YELLOWGREEN;}
section#container-section{
	margin-top:15px;}
nav{padding-top:0px;
	background-color:white;
	text-align:left;}
</style>
<script type="text/javascript">
function controlla()
{
	if(document.ControlloLogin.email.value== "" && document.ControlloLogin.password.value== "")
		return false;
	return true;
}
</script>
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
	<form method="POST" action="../LoginServlet" name="ControlloLogin" onsubmit="return controlla();">
	<fieldset>
		<legend>Accedi</legend>
		<table>
			<tr>
				<td>E-mail</td>
				<td><input type="text" name="email" size="20" class="testo"></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password" size="20" class="testo"></td>
			</tr>
			<tr>
				<td colspan="2"><div class="centro"><input type="submit" value=" Accedi.. " class="pulsante"><input type="reset" value=" Annulla " class="pulsante"></div></td>
			</tr>
		</table>
	</fieldset>
	</form>
</section>
<footer id="container-footer">
		<!--  Pagina contenete il messaggio da inglobare nel footer -->
		<%@ include file="../layout/footer.jsp" %>
</footer>
</body>
</html>

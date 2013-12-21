<!-- ROMANO SIMONE -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<style type="text/css">
	#main{
		position: absolute;
		width: 223px;
		height: 80px;
	}
</style>
</head>
<body>
<div id="main">
	<image src="/vviser/images/logo.png"/ style="widht:100px;height:100px;">
	<table border=0>
		<form action="/vviser/LoginServlet" method="POST">
		<tr>
			<td style="text-align:right;">E-mail:</td><td><input type="text" name="email"/></td>
		</tr>
		<tr>
			<td style="text-align:right">Password:</td><td><input type="password" name="password"/></td>
		</tr>
		<tr>
			<td></td><td><input type="submit" value="LOGIN"/></td>
		</tr>
		</form>
	</table>
</div>
</body> 
</html>
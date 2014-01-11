<%@ page import="java.lang.ProcessBuilder.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<link href="/vviser/css/stile1.css" rel="stylesheet" type="text/css"/>
<link href="/vviser/css/stile2.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
fieldset{width:20%;}
</style>
<script type="text/javascript">
function controlla()
{
	var f = document.modulo;
	if(f.isbn.value!="" && f.titolo.value!="" && f.data.value!="" && f.tipologia.value!="")
	{
		return true;
	}
	alert("Attenzione\n alcuni campi non sono stati compilati")
	f.isbn.focus();
	return false;
}
</script>
</head>
<body>

	<form method="GET" action="../InserimentoProdottoServlet" name="modulo" onsubmit="return controlla();">
	<fieldset>
		<legend>Prodotto</legend>
		<table>
			<tr>
				<td class="color">ISBN*</td>
				<td><input type="text" name="isbn" size="20" class="testo"></td>
			</tr>
			<tr>
				<td class="color">Titolo*</td>
				<td><input type="text" name="titolo" size="20" class="testo"></td>
			</tr>
			<tr>
				<td class="color">Data Pubblicazione*</td>
				<td><input type="date" name="data" class="testo"/></td>
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
				<td class="color">Tipologia*</td>
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
				<td><input type="text" name="num_volume" size="20" class="testo"></td>
			</tr>
			<tr>
				<td>Totale pagine</td>
				<td><input type="text" name="totalePagine" size="20" class="testo"></td>
			</tr>
			<tr>
				<td>Da Pagina</td>
				<td><input type="text" name="daPagina" size="20" class="testo"></td>
			</tr>
			<tr>
				<td>A Pagina</td>
				<td><input type="text" name="aPagina" size="20" class="testo"></td>
			</tr>
			<tr>
				<td colspan="2"><div class="centro"><input type="submit" value=" Salva " class="pulsante"><input type="reset" value=" Annulla " class="pulsante"></div></td>
			</tr>
			<tr>
				<td colspan="2"><div class="centro">* I campi sono obbligatori</div></td>
			</tr>
		</table>
		</fieldset>
	</form>
</body>
</html>

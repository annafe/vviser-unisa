<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--
	 ROMANO SIMONE
	ANTONIO DE PIANO
	-->
<%@page import="it.unisa.vviser.storage.DBProdottiValutazione"%>
<%@page import="it.vviser.common.CommonMethod"%>
<%@ page import="it.unisa.vviser.storage.DBGestioneProdotto"%>
<%@ page import="it.unisa.vviser.storage.DBTipologie"%>
<%@ page import="it.unisa.vviser.entity.Prodotto"%>
<%@ page import="it.unisa.vviser.entity.Tipologia"%>
<%@ page import="it.unisa.vviser.entity.Utente"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.logging.Logger"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title></title>
<script type="text/javascript">
	function checkSubmit(){
		form = document.getElementById("formModifica");
		//campi obbligatori:
		titolo = document.getElementById("titolo");
		data = document.getElementById("data");
		tipologia = document.getElementById("tipologia");

 		if(titolo.value=="" || tipologia.value=="" || data.value==""){
 			alert("Compila i campi obbligatori!");
 			return;
 		}
 		//submit
 		form.submit();
	}
</script>
<style type="text/css">
td.x{text-align:center;}
</style>
</head>
<body>
 <% 
 	//check permission
 	Prodotto product = null;
    String titolo, proprietario, tipologia, dataDiPubblicazione;
 	try{
	 	Utente user = (Utente)session.getAttribute("utente");
	 	//check product existence
	 	titolo = request.getParameter("titolo");
	 	proprietario = request.getParameter("proprietario");
	 	tipologia = request.getParameter("tipologia");
	 	dataDiPubblicazione = request.getParameter("annoDiPubblicazione");
	 	System.out.println("titolo:"+titolo+" proprietario:"+proprietario+" tipologia:"+tipologia+" dataDiPubblicazione:"+dataDiPubblicazione);
	 	product = new DBGestioneProdotto().ricercaProdottoPerTitoloProprietarioAnnoTipologia(titolo, proprietario, dataDiPubblicazione, tipologia);
		if (product==null){
			request.setAttribute("error", "Prodotto non presente nel Database");
			response.sendRedirect("/vviser/gpr/ituoiprodotti.jsp");
		}
	 	//current user has to coincide with owner
	 	 if (!(user.getEmail().equals(product.getProprietario()))){
			request.setAttribute("error", "Non hai i permessi per effettuare l'operazione");
			if(!response.isCommitted()){	//header can alredy forward response
				request.getRequestDispatcher("/vviser/main/login.jsp").forward(request, response);	
				return;		
			}
	 	} 
 	}catch(Exception e){
		 request.setAttribute("error", "Sessione non settata");
		if(!response.isCommitted()){	//header can alredy forward response
			request.getRequestDispatcher("/vviser/gu/login.jsp").forward(request, response);	
			return;		
		} 
 	}
 	
	%>
	<form id="formModifica" action="/vviser/ModificaProdottoServlet" method="POST">
		<fieldset style="float: left;">
		<legend>Modifica prodotto</legend>
			<table border=0>
				<tr><th align="right">Titolo*:</th><td><input id="titolo" type="text" maxlength="50" name="titolo" value="<%out.print(product.getTitolo());%>"/></td></tr>
				<tr><th align="right">Autore*:</th><td><%out.print(product.getProprietario()); %></td></tr>
				<tr><th align="right">Anno di pubblicazione*:</th><td><input id="data" type="text" name="data" value="<%out.print(CommonMethod.dateToString(product.getAnnoPubblicazione()));%>"/></td></tr>		
				<tr><th align="right">Tipologia*:</th><td>
					<select id="tipologia" name="tipologia">
						<option value="<%out.print(product.getTipologia()); %>"><%out.print(product.getTipologia()); %></option>
						<%String toNotAdd = product.getTipologia(); %>
						<%//get tipologie from database
						DBTipologie dbT = new DBTipologie();
						ArrayList<Tipologia> lista = new ArrayList<Tipologia>();
						lista = dbT.getTipologie();
						for (int i=0; i<lista.size(); i++){
							if (!(lista.get(i).getNome().equalsIgnoreCase(toNotAdd)))
								%><option value="<%out.print(lista.get(i).getNome());%>"><%out.print(lista.get(i).getNome());%></option><%
						}
						%>
					</select>
				</td></tr>	
				<tr><th align="right">Formato:</th><td><input type="text" name="formato_pub" maxlength="10" value="<%out.print(product.getFormatoPubblicazione());%>"/></td></tr>
				<tr><th align="right">Collaboratori:</th><td><textarea name="collaboratori" maxlength="100" rows="4" cols="25"><%out.print(product.getListaCollaboratori());%></textarea></td></tr>
				<tr><th align="right">Da pagina:</th><td><input type="text" name="daPagina" value="<%out.print(product.getDaPagina());%>"/></td></tr>
				<tr><th align="right">A pagina:</th><td><input type="text" name="aPagina" value="<%out.print(product.getApagina());%>"/></td></tr>
				<tr><th align="right">Totale pagine:</th><td><input type="text" name="totalePagine" value="<%out.print(product.getTotalePagine());%>"/></td></tr>
				<tr><th align="right">Volume n°:</th><td><input type="text" name="num_volume" value="<%out.print(product.getNumVolume());%>"/></td></tr>
				<tr><th align="right">Codice DOI:</th><td><input type="text" name="doi" value="<%out.print(product.getCodiceDOI());%>"/></td></tr>
				<tr><th align="right">Diffusione:</th><td><input type="text" name="diffusione" maxlength="20" value="<%out.print(product.getDiffusione());%>"/></td></tr>
				<tr><th align="right">Descrizione:</th><td><textarea name="descrizione" maxlength="100" rows="4" cols="25"><%out.print(product.getDescrizioneContenuti());%></textarea></td></td></tr>
				<tr><th align="right">Note:</th><td><textarea name="note" rows="4" cols="25"><%out.print(product.getNote());%></textarea></td></tr>
				<tr><th align="right">Indirizzo web:</th><td><input type="text" name="indirizzoweb" value="<%out.print(product.getIndirizzoWeb());%>"/></td></tr>
				<tr><th align="right">Parole chiave:</th><td><input type="text" name="key" value="<%out.print(product.getParoleChiavi());%>"/></td></tr>
				<tr><th align="right">Editore:</th><td><input type="text" name="editore" value="<%out.print(product.getEditore());%>"/></td></tr>
				<tr><th align="right">Codice ISBN:</th><td><input type="text" name="isbn" maxlength="13" value="<%out.print(product.getIsbn());%>"/></td></tr>
				<tr>
				<td colspan="2"><div class="centro"><input type="button" onclick="checkSubmit()" value="Modifica" class="pulsante" />
									<input type="submit" value="Annulla" class="pulsante"  formaction="/vviser/gpr/ituoiprodotti.jsp"/></div>
									</td>
								</tr>
							</table>
		</fieldset>
	</form>
	</body>
	</html>
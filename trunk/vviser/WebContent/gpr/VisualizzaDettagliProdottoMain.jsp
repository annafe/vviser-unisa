<!-- ROMANO SIMONE -->
<%@ page import="it.unisa.vviser.storage.DBGestioneProdotto"%>
<%@ page import="it.unisa.vviser.entity.Prodotto"%>
<%@ page import="it.vviser.common.*"%>
<%@ page import="java.util.*"%>
	<style type="text/css">
		th{
			text-align: left;
		}
	</style>
</head>
<%
	//recupero prodotto
	Prodotto prodotto = (Prodotto)request.getAttribute("prodotto");
%>
<table border=0>
<tr><th>TITOLO</th><td><%out.print(prodotto.getTitolo()); %></td></tr>
<tr><th>ISBN</th><td><%out.print(prodotto.getIsbn()); %></td></tr>
<tr><th>PROPRIETARIO</th><td><%out.print(prodotto.getProprietario()); %></td></tr>
<tr><th>ANNO PUBBLICAZIONE</th><td><%out.print(CommonMethod.dateToString(prodotto.getAnnoPubblicazione())); %></td></tr>
<tr><th>FORMATO</th><td><%out.print(prodotto.getFormatoPubblicazione()); %></td></tr>
<tr><th>DOI</th><td><%out.print(prodotto.getCodiceDOI()); %></td></tr>
<tr><th>DIFFUSIONE</th><td><%out.print(prodotto.getDiffusione()); %></td></tr>
<tr><th>COLLABORATORI</th><td><%out.print(prodotto.getListaCollaboratori()); %></td></tr>
<tr><th>DESCRIZIONE</th><td><%out.print(prodotto.getDescrizioneContenuti()); %></td></tr>
<tr><th>INDIRIZZO WEB</th><td><%out.print(prodotto.getIndirizzoWeb()); %></td></tr>
<tr><th>PAROLE CHIAVE</th><td><%out.print(prodotto.getParoleChiavi()); %></td></tr>
<tr><th>EDITORE</th><td><%out.print(prodotto.getEditore()); %></td></tr>
<tr><th>NUMERO VOLUME</th><td><%out.print(prodotto.getNumVolume()); %></td></tr>
<tr><th>TOTALE PAGINE</th><td><%out.print(prodotto.getTotalePagine()); %></td></tr>
<tr><th>DA PAGINA</th><td><%out.print(prodotto.getDaPagina()); %></td></tr>
<tr><th>A PAGINA</th><td><%out.print(prodotto.getApagina()); %></td></tr>
<tr><th>NOTE</th><td><%out.print(prodotto.getNote()); %></td></tr>
<tr><th>STATO</th><td><%out.print(prodotto.getStato()); %></td></tr>
<tr><th>BOZZA</th><td><%out.print(prodotto.getBozza()); %></td></tr>
<tr><th>TIPOLOGIA</th><td><%out.print(prodotto.getTipologia()); %> </td></tr>
</table>
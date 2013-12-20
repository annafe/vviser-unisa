package it.unisa.vviser.servlet;

import it.unisa.vviser.entity.ListaProdottiValutazione;
import it.unisa.vviser.entity.ProdottoValutazione;
import it.unisa.vviser.storage.DBProdottiValutazione;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ServletSelezionaListeModifica extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private DBProdottiValutazione prodottiValutazioneManager;

	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		prodottiValutazioneManager=DBProdottiValutazione.getInstance();
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		
			HttpSession s = request.getSession();
			String emailUtente=(String)s.getAttribute("sessEmail");
			String[] radioSelected=request.getParameterValues("list");
			ListaProdottiValutazione listaProdottiValutazione=new ListaProdottiValutazione();
			JSONObject o;
			try 
			{
				o = new JSONObject(radioSelected[0]);
		
				listaProdottiValutazione.setEmailUtente(o.getString("emailUtente"));
				listaProdottiValutazione.setIdEventoValutazione(o.getInt("idEvento"));
				listaProdottiValutazione.setSuggestion(o.getString("suggerimento"));
				listaProdottiValutazione.setBloccato(o.getBoolean("bloccato"));
				JSONArray ja=o.getJSONArray("listaProdottiValutazione");
				for(int i=0;i<ja.length();i++)
				{
					String stringJson=(String)ja.get(i);
					JSONObject o1 =new JSONObject(stringJson);
					String isbn=o1.getString("isbn");
					String titolo=o1.getString("titolo");
					int priorita=o1.getInt("priorita");
					ProdottoValutazione prodottoValutazione=new ProdottoValutazione(isbn,titolo,priorita);
					listaProdottiValutazione.addProdottoValutazione(prodottoValutazione);
				}
				
			} 
			catch (JSONException e) 
			{
				e.printStackTrace();
			}
			
			request.setAttribute("lista", listaProdottiValutazione);
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/modificaLista.jsp");
			rd.forward(request,response);
			
		
	}

}




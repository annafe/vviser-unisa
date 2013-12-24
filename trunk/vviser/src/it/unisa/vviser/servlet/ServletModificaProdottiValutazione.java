package it.unisa.vviser.servlet;

import it.unisa.vviser.entity.EventoValutazione;
import it.unisa.vviser.entity.ProdottoValutazione;
import it.unisa.vviser.exception.InsertProdottiValutazioneException;
import it.unisa.vviser.storage.DBProdottiValutazione;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

public class ServletModificaProdottiValutazione extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private PrintWriter out;
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
		
		HttpSession s=request.getSession();
		String emailUtente=(String)s.getAttribute("emailUt");
		int idEvento=(int)s.getAttribute("idEv");
		
		String[] uncheckProduct=request.getParameterValues("desel");//prodottidi valutazione da eliminare
		String[] rimanentiProduct=request.getParameterValues("cbmd");//prodotti valutazione non modificati(la priorita' potrebbe essere cambiata)
		String[] changePriority=request.getParameterValues("pri");//eventuali priorita' da aggiornare 
		
		String[] checkProduct=request.getParameterValues("cb");//nuovi prodotti da sottomettere a valutazione
		String[] checkPriority=request.getParameterValues("newPri");//priorita' relative ai nuovi prodotti sottomessi
		
		ArrayList<ProdottoValutazione> prodottiValutazioneDaSottomettere=new ArrayList<ProdottoValutazione>();
		ArrayList<ProdottoValutazione> prodottiValutazioneDaEliminare=new ArrayList<ProdottoValutazione>();
		ArrayList<ProdottoValutazione> prodottiValutazioneAggiornaPriorita=new ArrayList<ProdottoValutazione>();
		
		boolean modificato1=true;
		boolean modificato2=true;
		boolean modificato3=true;
		
		if(checkProduct==null)
			modificato1=false;
		else
		{
			for(int i=0;i<checkProduct.length;i++)
			{
				ProdottoValutazione prodottoValutazione=new ProdottoValutazione();
				JSONObject o;
				try 
				{
					o = new JSONObject(checkProduct[i]);
					prodottoValutazione.setIsbn(o.getString("isbn"));
					prodottoValutazione.setTitle(o.getString("titolo"));
					if(!checkPriority[i].equals(""))
						prodottoValutazione.setPriority(Integer.parseInt(checkPriority[i]));
					else
						prodottoValutazione.setPriority(0);
				} 
				catch (JSONException e) 
				{
					e.printStackTrace();
				}
				
				prodottiValutazioneDaSottomettere.add(prodottoValutazione);
			}
		}
		
		if(rimanentiProduct!=null)
		{
			for(int i=0;i<rimanentiProduct.length;i++)
			{
				ProdottoValutazione prodottoValutazione=new ProdottoValutazione();
				JSONObject o;
				try 
				{
					o = new JSONObject(rimanentiProduct[i]);
					prodottoValutazione.setIsbn(o.getString("isbn"));
					prodottoValutazione.setTitle(o.getString("titolo"));
					if(changePriority[i].equals(""))
						changePriority[i]=""+0;
					if(o.getInt("priorita")!=Integer.parseInt(changePriority[i]))
					{
						modificato2=false;
						prodottoValutazione.setPriority(Integer.parseInt(changePriority[i]));
						prodottiValutazioneAggiornaPriorita.add(prodottoValutazione);	
					}	
				} 
				catch (JSONException e) 
				{
					e.printStackTrace();
				}
				
			}
		}
		
		if(uncheckProduct==null)
			modificato3=false;
		else
		{
			for(int i=0;i<uncheckProduct.length;i++)
			{
				ProdottoValutazione prodottoValutazione=new ProdottoValutazione();
				JSONObject o;
				try 
				{
					o = new JSONObject(uncheckProduct[i]);
					prodottoValutazione.setIsbn(o.getString("isbn"));
					prodottoValutazione.setTitle(o.getString("titolo"));
					prodottoValutazione.setPriority(o.getInt("priorita"));
				} 
				catch (JSONException e) 
				{
					e.printStackTrace();
				}
				
				prodottiValutazioneDaEliminare.add(prodottoValutazione);
			}
		}
		
		//prova stampa
		/*System.out.println("Prodotti da sottomettere:");
		for(int i=0;i<prodottiValutazioneDaSottomettere.size();i++)
		{
			System.out.println(prodottiValutazioneDaSottomettere.get(i).toString());
		}
		System.out.println("Prodotti da eliminare:");
		for(int i=0;i<prodottiValutazioneDaEliminare.size();i++)
		{
			System.out.println(prodottiValutazioneDaEliminare.get(i).toString());
		}
		System.out.println("Prodotti da agg la priorita':");
		for(int i=0;i<prodottiValutazioneAggiornaPriorita.size();i++)
		{
			System.out.println(prodottiValutazioneAggiornaPriorita.get(i).toString());
		}*/
		
		
		if(!modificato1 && modificato2 && !modificato3)
			System.out.println("non hai apportato alcuna modifica");
		else
		{
			try {
				prodottiValutazioneManager.modifyProdottiValutazione(prodottiValutazioneDaSottomettere,prodottiValutazioneDaEliminare,prodottiValutazioneAggiornaPriorita,emailUtente,idEvento);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

}
}

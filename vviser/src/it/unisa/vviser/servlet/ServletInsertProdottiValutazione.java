package it.unisa.vviser.servlet;

import it.unisa.vviser.entity.ListaProdottiValutazione;
import it.unisa.vviser.entity.ProdottoValutazione;
import it.unisa.vviser.storage.DBProdottiValutazione;

import java.io.IOException;
import java.io.PrintWriter;
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

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author Giuseppe Sabato
 *
 */

public class ServletInsertProdottiValutazione extends HttpServlet {
	
	/**
	 * 
	 */
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
		
		//out=response.getWriter();
		String[] checkProduct=request.getParameterValues("selProd");
		String[] checkPriority=request.getParameterValues("priorita");
		ArrayList<ProdottoValutazione> prodottiValutazione=new ArrayList<ProdottoValutazione>();
		HttpSession s = request.getSession();
		String emailUtente=(String)s.getAttribute("sessEmail");//recupero l'email utente settato nella sessione
		System.out.println(emailUtente);
        //String emailUtente=account.getEmail();
        //l.setEmailUtente(emailUtente);
		
		ArrayList<String> chPry=new ArrayList<String>();
		//elimino le priorita' dei prodotti non selezionati
		for(int i=0;i<checkPriority.length;i++)
		{	int k=0;
			if (!checkPriority[i].equals(""))
			{
				chPry.add(checkPriority[i]);
				k++;
			}
		}
		for(int i=0;i<checkProduct.length;i++)
		{
			ProdottoValutazione prodottoValutazione=new ProdottoValutazione();
			JSONObject o;
			try 
			{
				o = new JSONObject(checkProduct[i]);
				prodottoValutazione.setIsbn(o.getString("isbn"));
				prodottoValutazione.setTitle(o.getString("titolo"));
				prodottoValutazione.setPriority(Integer.parseInt(chPry.get(i)));
			} 
			catch (JSONException e) 
			{
				e.printStackTrace();
			}
			
			prodottiValutazione.add(prodottoValutazione);
		}
		
		
		try 
		{
			prodottiValutazioneManager.insertProdottiVal(prodottiValutazione, emailUtente);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
    }

}

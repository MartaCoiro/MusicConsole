package gestioneProdotti;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import gestioneCarrello.Carrello;
import gestioneCarrello.CarrelloModelDS;
import it.unisa.utils.DBConnectionPool;
import it.unisa.utils.Utility;

import java.io.FileNotFoundException;
import java.io.FileReader;



@WebServlet("/ServletInf")
public class ServletInf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		//DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		
		Connection ds=null;
		try {
			ds=DBConnectionPool.getConnection();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		response.setContentType("application/json");//tipo di file
		
		String a = request.getParameter("prezzo");
		Float prezzo = Float.parseFloat(a);
		String autore = request.getParameter("autore");
		String nome = request.getParameter("nome");
		String tipo = request.getParameter("tipo");
		
		HttpSession currentSession = request.getSession();
		Collection<Carrello> car = (Collection<Carrello>)currentSession.getAttribute("carrello");
		String ut = (String)currentSession.getAttribute("acc");
		ArrayList<Carrello> c = new ArrayList<Carrello>();
		
		CarrelloModelDS model = new CarrelloModelDS(ds);
		
		Carrello ca = new Carrello();
		ca.setAutore(autore);
		ca.setCosto(prezzo);
		ca.setNome(nome);
		ca.setTipo(tipo);
		ca.setUtente(ut);
		if(tipo.toLowerCase().equals("streaming")) {
			//ca = new Carrello(prezzo,0,ut,nome,autore,tipo,prezzo);
			ca.setQuantità(0);
		}else {
			//ca = new Carrello(prezzo,1,ut,nome,autore,tipo,prezzo);
			ca.setQuantità(1);
		}
		ca.setTotq(prezzo);
		
		boolean bbb;
		boolean ripet = false;
		
		try {
			
			//controllo per non far inserire un prodotto streaming più volte
			if(tipo.toLowerCase().equals("streaming")) {
				for(Iterator<Carrello> i = car.iterator();i.hasNext();) {//carrello attuale dell'utente
					Carrello ele = (Carrello)i.next();
					if((ele.getAutore().equals(autore))&&(ele.getNome().equals(nome))&&(ele.getTipo().equals(tipo))) {
							bbb=false;
							JSONObject flag = new JSONObject(bbb);
							flag.put("flag", bbb);
							response.getWriter().println(flag);
							return;
					}
				}
			}
			
			//controllo per non far inserire un prodotto di tipo cd e vinile più volte
		
		for(Iterator<Carrello> i = car.iterator();i.hasNext();) {//carrello attuale dell'utente
			Carrello ele = (Carrello)i.next();
			if((ele.getAutore().equals(autore))&&(ele.getNome().equals(nome))&&(ele.getTipo().equals(tipo))) {
				int q = ele.getQuantità();
				model.doUpdate((q+1),(prezzo*(q+1)), ele.getCod());
				ripet=true;
				}
		}
			if(ripet==false) {
				model.doSave(ca); 
			}
				bbb=true;
				Collection<Carrello> p = model.doRetrieveAll();
				for(Iterator<Carrello> i = p.iterator();i.hasNext();) {
					Carrello ele = (Carrello)i.next();
					if(ele.getUtente().equals(ut)) {
						c.add(ele);
					}
				}
			
				currentSession.setAttribute("carrello", c);
				JSONArray jsArray = new JSONArray(c);
				JSONObject flag = new JSONObject(bbb);
				flag.put("flag", bbb);
				response.getWriter().println(flag);
	}
		catch(SQLException | JSONException e){ 
			Utility.print(e);
			request.setAttribute("error", e.getMessage());
			}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

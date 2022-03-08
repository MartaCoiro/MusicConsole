package gestioneCarrello;

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

import gestioneProdotti.Magazzino;
import gestioneProdotti.MagazzinoModelDS;
import gestioneProdotti.Playlist;
import it.unisa.utils.DBConnectionPool;
import it.unisa.utils.Utility;


@WebServlet("/ServletElimina")
public class ServletElimina extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		//DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		Connection ds = null;
		try {
			ds = DBConnectionPool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String codprod = request.getParameter("codprod");
		Integer cod = Integer.parseInt(codprod);
		
		HttpSession currentSession = request.getSession();
		String ut = (String)currentSession.getAttribute("acc");
		
		ArrayList<Carrello> c = new ArrayList<Carrello>();
		
		CarrelloModelDS model = new CarrelloModelDS(ds);
		MagazzinoModelDS model1 = new MagazzinoModelDS(ds);
		
		boolean repet = false;
		try {
			//controllo quantità e poi elimino
			Collection<Carrello> p = model.RestXUtente(ut);
			for(Iterator<Carrello> i = p.iterator();i.hasNext();) {
				Carrello ele = (Carrello)i.next();
				if(ele.getCod().equals(cod)) {
					if(ele.getQuantità()>1){
						repet = true;
						int q = ele.getQuantità();
						float pp = ele.getCosto();
						float pq = ele.getTotq();
					model.doUpdate((q-1),(pq-pp), ele.getCod());
					}
				}
			}
			
			if(repet==false) {	
			model.doDelete(cod);
			}
			Float tot=model.doSum(ut);
			currentSession.setAttribute("tot", tot);
			
				Collection<Carrello> b = model.doRetrieveAll();
				for(Iterator<Carrello> i = b.iterator();i.hasNext();) {
					Carrello ele = (Carrello)i.next();
				if(ele.getUtente().equals(ut)) {
					c.add(ele);
					if((ele.getTipo().equals("CD"))||(ele.getTipo().equals("Vinile"))) { 
					request.setAttribute("sip", true);
					}
				}
			}
				
				//controllo se c'è disponibilità
				Collection<Carrello> car = model.RestXUtente(ut);
				for(Iterator<Carrello> cc = car.iterator();cc.hasNext();) {
					Carrello ele = (Carrello)cc.next();
					if((ele.getTipo().equals("CD"))||(ele.getTipo().equals("Vinile"))) {
						Collection<Magazzino> m = model1.doRetrieveAll();
						for(Iterator<Magazzino> i = m.iterator();i.hasNext();) {
							Magazzino ele1 = (Magazzino)i.next();
							if((ele1.getNome().equals(ele.getNome())&&(ele1.getAutore().equals(ele.getAutore()))&&(ele1.getTipo().equals(ele.getTipo())))){
								if(ele1.getQuantità()<ele.getQuantità()) {
									currentSession.setAttribute("noD", true);
								}
								else {
									currentSession.setAttribute("noD", false);
								}
							}
					}
				}
			}
				currentSession.setAttribute("carrello", c);
				request.getServletContext().getRequestDispatcher(response.encodeURL("/Carrello.jsp")).forward(request, response);
	
		}

		 catch(SQLException e){
				Utility.print(e);
				request.setAttribute("error", e.getMessage());
				}
	}
	
}
package it.unisa.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.servlet.http.HttpSession;

import it.unisa.model.AccountModelDS;
import it.unisa.utils.Utility;
import it.unisa.model.AccountUtente;
import it.unisa.model.Carrello;
import it.unisa.model.CarrelloModelDS;
import it.unisa.model.Playlist;
import it.unisa.model.PlaylistModelDS;
import it.unisa.model.Profilo;
import it.unisa.model.ProfiloModelDS;

@WebServlet("/ServletElUtenti")
public class ServletElUtenti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		
		AccountModelDS model = new AccountModelDS(ds);
		ProfiloModelDS model1 = new ProfiloModelDS(ds);
		CarrelloModelDS model2 = new CarrelloModelDS(ds);
		PlaylistModelDS model3 = new PlaylistModelDS(ds);
		
		response.setContentType("text/html");//tipo di file
		
		HttpSession currentSession = request.getSession();
		String ut = (String)currentSession.getAttribute("acc");
		//String ut = request.getParameter("nome");
		//ArrayList<String> newu = new ArrayList<String>();
		
		try {
			//cancellazione utente da account 
			boolean a = model.doDelete(ut);
			//cancellazione utente da profilo
			Profilo el = model1.doRetrieveByKey(ut);
			model1.doDelete(el.getId());
			//cancellazione utente da carrello
			Collection<Carrello> ca = model2.RestXUtente(ut);
			for(Iterator<Carrello> i = ca.iterator();i.hasNext();) {
				Carrello ele = (Carrello)i.next();
				model2.doDelete(ele.getCod());
			}
			//cancellazione utente da playlist
			Collection<Playlist> pp = model3.doRetrieveAll();
			for(Iterator<Playlist> i = pp.iterator();i.hasNext();) {
				Playlist ele = (Playlist)i.next();
				if(ele.getNomeUtente().equals(ut)) {
					model3.doDeletep(ele.getId());
				}
			}
			
			/*if(a==true) {
				Collection<AccountUtente> p = model.doRetrieveAll();
				for(Iterator<AccountUtente> i = p.iterator();i.hasNext();) {
					AccountUtente ele = (AccountUtente)i.next();
					newu.add(ele.getNickname());
			}*/
				//currentSession.setAttribute("lutenti", newu);
				getServletContext().getRequestDispatcher(response.encodeURL("/index.jsp")).forward(request, response); //reindiriziamo alla view
		
	}
		catch(SQLException e){
			Utility.print(e);
			request.setAttribute("error", e.getMessage());
			}
	}

}

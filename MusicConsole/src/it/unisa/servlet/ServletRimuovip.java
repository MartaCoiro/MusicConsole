package it.unisa.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import Class.Playlist;
import it.unisa.model.PlaylistModelDS;
import it.unisa.utils.Utility;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


@WebServlet("/ServletRimuovip")
public class ServletRimuovip extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
	
		String playl=request.getParameter("nomep");
		
		PlaylistModelDS model = new PlaylistModelDS(ds);
		
		HttpSession currentSession = request.getSession();
		
		ArrayList<String> lplay = new ArrayList<String>();
		String ut = (String)currentSession.getAttribute("acc");
		
		
	try {
		Collection<Playlist> p = model.doRetrieveAll();
		for(Iterator<Playlist> i = p.iterator();i.hasNext();) {
			Playlist ele = (Playlist)i.next();
			if(ele.getNomeUtente().equals(ut)) {
				if(ele.getNome().equals(playl)) {
					int cod = ele.getId();
					model.doDeletep(cod);
				}
			}
		}
		
		p=model.doRetrieveAll();
		for(Iterator<Playlist> i = p.iterator();i.hasNext();) {
			Playlist ele = (Playlist)i.next();
			if(ele.getNomeUtente().equals(ut)) {
				if(!lplay.contains(ele.getNome())) {
					lplay.add(ele.getNome());
				}
				
				}
		}
		currentSession.setAttribute("namep", lplay);
		getServletContext().getRequestDispatcher(response.encodeURL("/listaplaylist.jsp")).forward(request, response); //reindiriziamo alla view
	}
	
	catch(SQLException e){ //se invece l'utente è presente
		Utility.print(e);
		request.setAttribute("error", e.getMessage());
		}
	
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}

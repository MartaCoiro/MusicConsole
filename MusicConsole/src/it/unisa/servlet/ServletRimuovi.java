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

import it.unisa.model.Playlist;
import it.unisa.model.PlaylistModelDS;
import it.unisa.utils.Utility;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@WebServlet("/ServletRimuovi")
public class ServletRimuovi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
	
	String brano=request.getParameter("nomeb");
	String artista=request.getParameter("nomea");
	String playl=request.getParameter("nomep");
	
	PlaylistModelDS model = new PlaylistModelDS(ds);
	
	HttpSession currentSession = request.getSession();
	
	ArrayList<String> namep = (ArrayList<String>)currentSession.getAttribute("namep");
	String ut = (String)currentSession.getAttribute("acc");
	ArrayList<String> blist = new ArrayList<String>();
	try {
		boolean val = model.doDelete(brano, artista, playl, ut);
		if(val==true) {
			Collection<Playlist> p = model.doRetrieveAll();
			for(int k=0;k<namep.size();k++) {
				if(namep.get(k).equals(playl)) {
				
				for(Iterator<Playlist> i = p.iterator();i.hasNext();) {
					Playlist ele = (Playlist)i.next();
				if((ele.getNome().equals(playl))&&(ele.getNomeUtente().equals(ut))) {
					blist.add(ele.getNomeBrano());
					blist.add(ele.getNomeArtista());
					}
				}
				currentSession.setAttribute("blist1"+k, blist);	
				
				}
			}
				
			getServletContext().getRequestDispatcher(response.encodeURL("/playlist.jsp")).forward(request, response); //reindiriziamo alla view
		}
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

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

import it.unisa.utils.DBConnectionPool;
import it.unisa.utils.Utility;

@WebServlet("/ServletSend")
public class ServletSend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		Connection ds = null;
		try {
			ds = DBConnectionPool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String plist = request.getParameter("plist");
		
		PlaylistModelDS model = new PlaylistModelDS(ds);
		
		
		HttpSession currentSession = request.getSession();
		ArrayList<String> namep = (ArrayList<String>)currentSession.getAttribute("namep");
		String ut = (String)currentSession.getAttribute("acc");
		ArrayList<String> blist = new ArrayList<String>();

		try {
			Collection<Playlist> p = model.doRetrieveAll();
		for(int k=0;k<namep.size();k++) {
			if(namep.get(k).equals(plist)) {
				for(Iterator<Playlist> i = p.iterator();i.hasNext();) {
					Playlist ele = (Playlist)i.next();
					if((ele.getNome().equals(plist))&&(ele.getNomeUtente().equals(ut))) {
						blist.add(ele.getNomeBrano());
						blist.add(ele.getNomeArtista());
						}
					}
				currentSession.setAttribute("blist1"+k, blist);
		}
	}
		
		}
		catch(SQLException e){ //se invece l'utente è presente
			Utility.print(e);
			request.setAttribute("error", e.getMessage());
			}
		
		
		currentSession.setAttribute("nn", plist);
		getServletContext().getRequestDispatcher("/playlist.jsp").forward(request, response); //reindiriziamo alla view	
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}

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


@WebServlet("/ServletPlaylist")
public class ServletPlaylist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		   throws ServletException, IOException {
		
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		
		String plist = request.getParameter("plist");
	
		HttpSession currentSession = request.getSession();
		Collection<?> prB = (Collection<?>)currentSession.getAttribute("prB");
		String ut = (String)currentSession.getAttribute("acc");
		
				String qstring=request.getQueryString();
				String frase = qstring.replace("%20", " ");
				int ec =frase.indexOf("&"); 
				int j =frase.indexOf("="); 
				String brano=frase.substring(j+1, ec);
				String frasea=frase.substring(ec+1);
				j=frasea.indexOf("=");
				ec=frasea.indexOf("&");
				String artista=frasea.substring(j+1, ec);
				
		PlaylistModelDS model = new PlaylistModelDS(ds);
		Playlist pl = new Playlist();
		pl.setNome(plist);
		pl.setNomeArtista(artista);
		pl.setNomeBrano(brano);
		pl.setNomeUtente(ut);
		
		ArrayList<String> namep = (ArrayList<String>)currentSession.getAttribute("namep");
		ArrayList<String> blist = new ArrayList<String>();
		
		try {
			Collection<Playlist> p = model.doRetrieveAll();
		
			for(Iterator<Playlist> i = p.iterator();i.hasNext();) {
				Playlist ele = (Playlist)i.next();
				if((ele.getNome().equals(plist))&&(ele.getNomeUtente().equals(ut))) {
				if((ele.getNomeBrano().equals(brano))&&(ele.getNomeArtista().equals(artista))) {
					request.setAttribute("no", true);
					getServletContext().getRequestDispatcher("/home.jsp").forward(request, response); //reindiriziamo alla view
					return;
				}
				}
			}
			
			model.doSave(pl);
			p= model.doRetrieveAll();
					for(int k=0;k<namep.size();k++) {
						if(namep.get(k).equals(plist)) {
							for(Iterator<Playlist> i = p.iterator();i.hasNext();) {
								Playlist ele = (Playlist)i.next();
								if((ele.getNome().equals(plist))&&(ele.getNomeUtente().equals(ut))) {
									blist.add(ele.getNomeBrano());
									blist.add(ele.getNomeArtista());
									}
								}
							currentSession.setAttribute("blist"+k, blist);
					}
				}
				
			request.setAttribute("no", false);
			getServletContext().getRequestDispatcher("/home.jsp").forward(request, response); //reindiriziamo alla view
		}
		catch(SQLException e){ 
			Utility.print(e);
			request.setAttribute("error", e.getMessage());
			}
}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		doGet(request, response);
	}

}

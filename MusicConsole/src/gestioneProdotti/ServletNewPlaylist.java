package gestioneProdotti;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import it.unisa.utils.DBConnectionPool;
import it.unisa.utils.Utility;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@WebServlet("/ServletNewPlaylist")
public class ServletNewPlaylist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		Connection ds = null;
		try {
			ds = DBConnectionPool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		PlaylistModelDS model = new PlaylistModelDS(ds);
		
		response.setContentType("text/html");//tipo di file
		
		String namep = request.getParameter("nome");
		
		ArrayList<String> lplay = new ArrayList<String>();
		
		HttpSession currentSession = request.getSession();
		
		String nut =(String)currentSession.getAttribute("acc");
		
		Playlist pl = new Playlist();
		//Playlist pl = new Playlist(nut,namep,"",0,"");
		pl.setNomeUtente(nut);
		pl.setNome(namep);
		try {
			if(namep.equals("")) {
				request.setAttribute("si", false);
				getServletContext().getRequestDispatcher("/listaplaylist.jsp").forward(request, response); //reindiriziamo alla view
				return;
			}
			
			Collection<Playlist> p = model.doRetrieveAll();
			for(Iterator<Playlist> i = p.iterator();i.hasNext();) {
				Playlist ele = (Playlist)i.next();
				if((ele.getNome().equals(namep))&&(ele.getNomeUtente().equals(nut))) {
					request.setAttribute("si", true);
					getServletContext().getRequestDispatcher("/listaplaylist.jsp").forward(request, response); //reindiriziamo alla view
					return;
				}
			}
			model.doSave(pl);
			p=model.doRetrieveAll();
			for(Iterator<Playlist> i = p.iterator();i.hasNext();) {
				Playlist ele = (Playlist)i.next();
				if(ele.getNomeUtente().equals(nut)) {
					if(!lplay.contains(ele.getNome())) {
						lplay.add(ele.getNome());
					}
					
					}
			}
			
			currentSession.setAttribute("namep", lplay);
			getServletContext().getRequestDispatcher("/listaplaylist.jsp").forward(request, response); //reindiriziamo alla view
		}
		catch(SQLException e){ //se invece l'utente è presente
			Utility.print(e);
			request.setAttribute("error", e.getMessage());
			}
			
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
	}

}

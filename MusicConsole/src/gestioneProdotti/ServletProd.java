package gestioneProdotti;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.servlet.http.HttpSession;

import it.unisa.utils.DBConnectionPool;
import it.unisa.utils.Utility;


@WebServlet("/ServletProd")
public class ServletProd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		Connection ds = null;
		try {
			ds = DBConnectionPool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.setContentType("text/html");//tipo di file
		
		AlbumModelDS model1 = new AlbumModelDS(ds);
		BraniModelDS model2 = new BraniModelDS(ds);
		PodcastModelDS model3 = new PodcastModelDS(ds);
		
		HttpSession currentSession = request.getSession();
		String ruolo = request.getParameter("tipo");
		ArrayList<String> prod = new ArrayList<String>();
		
		try {
			
			Collection<Album> al = model1.doRetrieveAll();
			Collection<Brano> br = model2.doRetrieveAll();
			Collection<Podcast> po = model3.doRetrieveAll();
			
			for (Iterator<Album> i = al.iterator();i.hasNext();) {
				Album ele1 = (Album)i.next();
				prod.add(ele1.getNomeAlbum());
				prod.add(ele1.getNartista());
				prod.add(ele1.getTipo());
			 	}

			for (Iterator<Brano> i = br.iterator();i.hasNext();) {
				 Brano ele2 = (Brano)i.next();
				 prod.add(ele2.getTitolo());
				 prod.add(ele2.getCantante());
				 prod.add(ele2.getTipo());
				 }
			for (Iterator<Podcast> i = po.iterator();i.hasNext();) {
				 Podcast ele3 = (Podcast)i.next();
				 prod.add((ele3.getNomePodcast()));
				 prod.add(ele3.getIdeatore());
				 prod.add(ele3.getTipo());
			}
			currentSession.setAttribute("lprod", prod);
			if(ruolo.equals("magazzino")) {
			getServletContext().getRequestDispatcher("/magazzino.jsp").forward(request, response); //reindiriziamo alla view
			}else if(ruolo.equals("catalogo")) {
				getServletContext().getRequestDispatcher("/prodotti.jsp").forward(request, response); //reindiriziamo alla view
				}
		}
		catch(SQLException e){
			Utility.print(e);
			request.setAttribute("error", e.getMessage());
			}
		
	}

}

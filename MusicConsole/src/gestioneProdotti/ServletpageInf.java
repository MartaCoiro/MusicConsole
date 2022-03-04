package gestioneProdotti;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import it.unisa.utils.DBConnectionPool;
import it.unisa.utils.Utility;

import javax.servlet.http.HttpSession;

@WebServlet("/ServletpageInf")
public class ServletpageInf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		Connection ds = null;
		try {
			ds = DBConnectionPool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HttpSession currentSession = request.getSession();
		
		response.setContentType("text/html");//tipo di file
		
		Collection<?> prB = (Collection<?>)currentSession.getAttribute("prB");
		Collection<?> prA = (Collection<?>)currentSession.getAttribute("prA");
		Collection<?> prP = (Collection<?>)currentSession.getAttribute("prP");
		
		String nome = request.getParameter("nome");
		String artista = request.getParameter("artista");
		String tipo = request.getParameter("tipo");
		
		AlbumModelDS model1 = new AlbumModelDS(ds);
		BraniModelDS model2 = new BraniModelDS(ds);
		PodcastModelDS model3 = new PodcastModelDS(ds);
		
		try {
			if(tipo.toLowerCase().equals("album")) {
				if(prA != null && prA.size()>0){
					Iterator<?> it = prA.iterator();
					while(it.hasNext()){
						Album bean = (Album)it.next();
						if((bean.getNomeAlbum().equals(nome))&&(bean.getNartista().equals(artista))){
							Album a = model1.doRetrieveByKey(nome, artista);
				currentSession.setAttribute("AlbumI", a);
				getServletContext().getRequestDispatcher("/Album.jsp").forward(request, response); //reindiriziamo alla view	
				return;
			}
					}
				}
			}
			else if(tipo.toLowerCase().equals("brano")) {
				if(prB != null && prB.size()>0){
					Iterator<?> it = prB.iterator();
					while(it.hasNext()){
						Brano bean = (Brano)it.next();
						if((bean.getTitolo().equals(nome))&&(bean.getCantante().equals(artista))){
				Brano b = model2.doRetrieveByKey(nome, artista);
				currentSession.setAttribute("BranoI", b);
				getServletContext().getRequestDispatcher("/Brano.jsp").forward(request, response); //reindiriziamo alla view	
				return;
			}
					}
				}
			}
			else if(tipo.toLowerCase().equals("podcast")) {
				if(prP != null && prP.size()>0){
					Iterator<?> it = prP.iterator();
					while(it.hasNext()){
						Podcast bean = (Podcast)it.next();
						if((bean.getNomePodcast().equals(nome))&&(bean.getIdeatore().equals(artista))){
							Podcast p = model3.Restituisci(nome);
							currentSession.setAttribute("PodcastI", p);
							getServletContext().getRequestDispatcher("/Podcast.jsp").forward(request, response); //reindiriziamo alla view	
								return;
			}
		}
	}

}
		}catch(SQLException e){
			Utility.print(e);
			request.setAttribute("error", e.getMessage());
			}
	}
}

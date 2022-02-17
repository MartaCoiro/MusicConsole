package gestioneProdotti;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import it.unisa.utils.Utility;

@WebServlet("/ServletpageInfH")
public class ServletpageInfH extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		
		HttpSession currentSession = request.getSession();
		
		response.setContentType("text/html");//tipo di file
		
		Collection<?> prB = (Collection<?>)request.getAttribute("prB");
		Collection<?> prA = (Collection<?>)request.getAttribute("prA");
		Collection<?> prP = (Collection<?>)request.getAttribute("prP");
		
		String nome = request.getParameter("nome");
		String artista = request.getParameter("artista");
		String tipo = request.getParameter("tipo");
		
		AlbumModelDS model1 = new AlbumModelDS(ds);
		BraniModelDS model2 = new BraniModelDS(ds);
		PodcastModelDS model3 = new PodcastModelDS(ds);
		
		try {
			if(tipo.toLowerCase().equals("album")) {
				Collection<Album> a = model1.doRetrieveAll();
				for(Iterator<Album> i = a.iterator(); i.hasNext();) {
					Album al = (Album)i.next();
					if(al.getDat()!=null) {
						if((al.getNomeAlbum().equals(nome))&&(al.getNartista().equals(artista))) {
							request.setAttribute("AlbumI", al);
							getServletContext().getRequestDispatcher("/AlbumU.jsp").forward(request, response); //reindiriziamo alla view	
							return;
					}
				}
			}
		}
					
				else if(tipo.toLowerCase().equals("brano")) {
					Collection<Brano> b = model2.doRetrieveAll();
					for(Iterator<Brano> i = b.iterator(); i.hasNext();) {
						Brano al = (Brano)i.next();
						if(al.getDat()!=null) {
							if((al.getTitolo().equals(nome))&&(al.getCantante().equals(artista))){
								request.setAttribute("BranoI", al);
								getServletContext().getRequestDispatcher("/BranoU.jsp").forward(request, response); //reindiriziamo alla view	
								return;
							}
							
						}
					}
				}
					
			else if(tipo.toLowerCase().equals("podcast")) {
				Collection<Podcast> po = model3.doRetrieveAll();
				for(Iterator<Podcast> i = po.iterator(); i.hasNext();) {
					Podcast al = (Podcast)i.next();
					if(al.getDat()!=null) {
						if((al.getNomePodcast().equals(nome))&&(al.getIdeatore().equals(artista))){
							request.setAttribute("PodcastI", al);
							getServletContext().getRequestDispatcher("/PodcastU.jsp").forward(request, response); //reindiriziamo alla view	
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

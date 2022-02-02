package it.unisa.servlet;

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
import Class.Album;
import Class.Brano;
import it.unisa.model.AlbumModelDS;
import it.unisa.model.BraniModelDS;
import Class.Carrello;
import Class.Podcast;
import it.unisa.model.CarrelloModelDS;
import it.unisa.model.PlaylistModelDS;
import it.unisa.model.PodcastModelDS;
import it.unisa.utils.Utility;


@WebServlet("/ServletElProd")
public class ServletElProd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		
		response.setContentType("text/html");//tipo di file
		
		String nome = request.getParameter("nome");
		String autore = request.getParameter("autore");
		String tipo = request.getParameter("tipo");
		
		AlbumModelDS model1 = new AlbumModelDS(ds);
		BraniModelDS model2 = new BraniModelDS(ds);
		PodcastModelDS model3 = new PodcastModelDS(ds);
		PlaylistModelDS model4 = new PlaylistModelDS(ds);
		CarrelloModelDS model5 = new CarrelloModelDS(ds);
		
		HttpSession currentSession = request.getSession();
		
		ArrayList<String> newp = new ArrayList<String>();
		
		try {
			Collection<Album> a = model1.doRetrieveAll();
			Collection<Brano> b = model2.doRetrieveAll();
			Collection<Podcast> p = model3.doRetrieveAll();
			Collection<Carrello> cc = model5.doRetrieveAll();
			if(tipo.toLowerCase().equals("album")) {
				for(Iterator<Album> i = a.iterator(); i.hasNext();) {
					Album ele1 = (Album)i.next();
					if((ele1.getNomeAlbum().equals(nome))&&(ele1.getNartista().equals(autore))) {
						model1.doDelete(ele1.getCodiceAlbum());
						for(Iterator<Carrello> ii = cc.iterator(); ii.hasNext();) {
							Carrello elcar = (Carrello)ii.next();
							if((elcar.getNome().equals(nome))&&(elcar.getAutore().equals(autore))) {
								model5.doDelete(elcar.getCod());
							}
						}
					}
				}
			}
			else if(tipo.toLowerCase().equals("brano")) {
				for(Iterator<Brano> i = b.iterator(); i.hasNext();) {
					Brano ele2 = (Brano)i.next();
					if((ele2.getTitolo().equals(nome))&&(ele2.getCantante().equals(autore))) {
						model2.doDelete(ele2.getCodice());
						model4.doDeleteProd(nome, autore);//lo stiamo eliminando dalla playlist
						for(Iterator<Carrello> ii = cc.iterator(); ii.hasNext();) {
							Carrello elcar = (Carrello)ii.next();
							if((elcar.getNome().equals(nome))&&(elcar.getAutore().equals(autore))) {
								model5.doDelete(elcar.getCod());
							}
						}
					}
				}
			}
			else if(tipo.toLowerCase().equals("podcast")) {
				for(Iterator<Podcast> i = p.iterator(); i.hasNext();) {
					Podcast ele3 = (Podcast)i.next();
					if((ele3.getNomePodcast().equals(nome))&&(ele3.getIdeatore().equals(autore))) {
						model3.doDelete(ele3.getNomePodcast());
						for(Iterator<Carrello> ii = cc.iterator(); ii.hasNext();) {
							Carrello elcar = (Carrello)ii.next();
							if((elcar.getNome().equals(nome))&&(elcar.getAutore().equals(autore))) {
								model5.doDelete(elcar.getCod());
							}
						}
					}
				}
			}
			
			a=model1.doRetrieveAll();
			b=model2.doRetrieveAll();
			p=model3.doRetrieveAll();
			
			for (Iterator<Album> i = a.iterator();i.hasNext();) {
				Album ele1 = (Album)i.next();
				newp.add(ele1.getNomeAlbum());
				newp.add(ele1.getNartista());
				newp.add(ele1.getTipo());
			 	}

			for (Iterator<Brano> i = b.iterator();i.hasNext();) {
				 Brano ele2 = (Brano)i.next();
				 newp.add(ele2.getTitolo());
				 newp.add(ele2.getCantante());
				 newp.add(ele2.getTipo());
				 }
			for (Iterator<Podcast> i = p.iterator();i.hasNext();) {
				 Podcast ele3 = (Podcast)i.next();
				 newp.add((ele3.getNomePodcast()));
				 newp.add(ele3.getIdeatore());
				 newp.add(ele3.getTipo());
			}
			currentSession.setAttribute("lprod", newp);
			getServletContext().getRequestDispatcher(response.encodeURL("/magazzino.jsp")).forward(request, response); //reindiriziamo alla view
	}
		catch(SQLException e){
			Utility.print(e);
			request.setAttribute("error", e.getMessage());
			}
	}
}

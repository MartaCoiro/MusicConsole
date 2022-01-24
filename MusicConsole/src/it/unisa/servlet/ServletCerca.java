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
import it.unisa.utils.Utility;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


import it.unisa.model.AlbumModelDS;
import it.unisa.model.Artista;
import it.unisa.model.ArtistaModelDS;
import it.unisa.model.BraniModelDS;
import it.unisa.model.Brano;
import it.unisa.model.GenereModelDS;
import it.unisa.model.GenereMusicale;
import it.unisa.model.Podcast;
import it.unisa.model.PodcastModelDS;
import it.unisa.model.Album;


@WebServlet("/ServletCerca")
public class ServletCerca extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
				
				DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
				
				AlbumModelDS model1 = new AlbumModelDS(ds);
				ArtistaModelDS model2 = new ArtistaModelDS(ds);
				BraniModelDS model3 = new BraniModelDS(ds);
				GenereModelDS model4 = new GenereModelDS(ds);
				PodcastModelDS model5 = new PodcastModelDS(ds);
				
				response.setContentType("text/html");//tipo di file
				
				String input = request.getParameter("parola").toLowerCase();
				
				ArrayList<Album> risAlbum = new ArrayList<Album>();
				ArrayList<Artista> risArtista = new ArrayList<Artista>();
				ArrayList<Brano> risBrano = new ArrayList<Brano>();
				ArrayList<GenereMusicale> risGM = new ArrayList<GenereMusicale>();
				ArrayList<Podcast> risPodcast = new ArrayList<Podcast>();
				
				try {	
					Collection<Album> al = model1.doRetrieveAll();
					Collection<Artista> ar = model2.doRetrieveAll();
					Collection<Brano> br = model3.doRetrieveAll();
					Collection<GenereMusicale> gm = model4.doRetrieveAll();
					Collection<Podcast> po = model5.doRetrieveAll();
					 
					for (Iterator<Album> i = al.iterator();i.hasNext();) {
						Album elemento1 = (Album)i.next();
					 if(elemento1.getNomeAlbum().toLowerCase().equals(input)) {
							 risAlbum.add(elemento1);
					 	}
					 if(elemento1.getNartista().toLowerCase().equals(input)) {
						 risAlbum.add(elemento1);
					 }
					}

					
					 for (Iterator<Brano> i = br.iterator();i.hasNext();) {
						 Brano elemento3 = (Brano)i.next();
						 if(elemento3.getTitolo().toLowerCase().equals(input)) {
							 risBrano.add(elemento3);
						 }
						 if(elemento3.getCantante().toLowerCase().equals(input)) {
							 risBrano.add(elemento3);
						 }
						 if(elemento3.getGenere().toLowerCase().equals(input)) {
							 risBrano.add(elemento3);
						 }
					}
					
					 for (Iterator<Podcast> i = po.iterator();i.hasNext();) {
						 Podcast elemento5 = (Podcast)i.next();
						 if(elemento5.getNomePodcast().toLowerCase().equals(input)) {
							 risPodcast.add(elemento5);
						 }
						 if(elemento5.getIdeatore().toLowerCase().equals(input)) {
							 risPodcast.add(elemento5);
						 }
						}
					
					request.setAttribute("ris1", risAlbum);
					request.setAttribute("ris2", risArtista);
					request.setAttribute("ris3", risBrano);
					request.setAttribute("ris4", risGM);
					request.setAttribute("ris5", risPodcast);
					
					HttpSession currentSession = request.getSession();
					
					if(currentSession.getAttribute("acc")!=null) {
					getServletContext().getRequestDispatcher(response.encodeURL("/cercaa.jsp")).forward(request, response); //reindiriziamo alla view
					}
					else {
						getServletContext().getRequestDispatcher(response.encodeURL("/cerca.jsp")).forward(request, response); //reindiriziamo alla view
					}
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

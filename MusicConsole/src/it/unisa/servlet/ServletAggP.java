package it.unisa.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.DataSource;
import Class.Album;
import Class.Podcast;
import it.unisa.model.PodcastModelDS;
import it.unisa.utils.Utility;

@MultipartConfig
@WebServlet("/ServletAggP")
public class ServletAggP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		
		response.setContentType("text/html");//tipo di file
		
		HttpSession currentSession = request.getSession();
		ArrayList<String> l = (ArrayList<String>)currentSession.getAttribute("lprod");
		
		String nome = request.getParameter("nome");
		
		String picName = null;
		String path = "C:\\Users\\utente\\eclipse-workspace1\\MusicConsole\\WebContent\\imgs";
		Part pic = request.getPart("image");
		if(pic!=null) {
			picName = request.getPart("image").getSubmittedFileName();
			if(!picName.equals("")) {
				pic.write(path + File.separator + picName);
			}
		}
		
		String artista = request.getParameter("artista");
		String tipo = request.getParameter("tipo");
		String descrizione = request.getParameter("descrizione");
		
		String prez = request.getParameter("prezzo");
		Float prezzo = Float.parseFloat(prez);
		
		PodcastModelDS model = new PodcastModelDS(ds);
		
		Podcast p = new Podcast();
		//Podcast p = new Podcast(nome,artista,descrizione,0,0,picName,tipo,prezzo);
		p.setNomePodcast(nome);
		p.setIdeatore(artista);
		p.setImgPod(picName);
		p.setTipo(tipo);
		p.setDescrizione(descrizione);
		p.setPrezzo(prezzo);
		
		try {
			
			Collection<Podcast> a = model.doRetrieveAll();
			//controllo nome
			for (Iterator<Podcast> i = a.iterator();i.hasNext();) {
				Podcast ele1 = (Podcast)i.next();
				if(ele1.getNomePodcast().toLowerCase().equals(nome.toLowerCase())) {
					request.setAttribute("err", true);
					getServletContext().getRequestDispatcher("/NuovoPodcast.jsp").forward(request, response); //reindiriziamo alla view
					return;
					}
				}
			//controllo tipo
			if(tipo.toLowerCase().equals("podcast")) {
			model.doSave(p);
			l.add(nome);
			l.add(artista);
			l.add(tipo);
			currentSession.setAttribute("yes", true);
			currentSession.setAttribute("lprod", l);
			getServletContext().getRequestDispatcher("/magazzino.jsp").forward(request, response); //reindiriziamo alla view
				}
			else {
				request.setAttribute("sb", true);
				getServletContext().getRequestDispatcher("/NuovoPodcast.jsp").forward(request, response); //reindiriziamo alla view
				return;
				}
			
		}
		catch(SQLException e){
			Utility.print(e);
			request.setAttribute("error", e.getMessage());
			}
	}

}

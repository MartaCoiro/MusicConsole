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
import javax.servlet.http.Part;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import it.unisa.model.Album;
import it.unisa.model.AlbumModelDS;
import it.unisa.model.Magazzino;
import it.unisa.model.MagazzinoModelDS;
import it.unisa.utils.Utility;

@MultipartConfig
@WebServlet("/ServletAgg")
public class ServletAgg extends HttpServlet {
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
		
		String c = request.getParameter("codice");
		Integer codice = Integer.parseInt(c);
		
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
		
		String q = request.getParameter("quantita");
		int quant = Integer.parseInt(q);
		
		String prez1 = request.getParameter("prezzoS");
		Float prezzoS = Float.parseFloat(prez1);
		
		String prez2 = request.getParameter("prezzoV");
		Float prezzoV = Float.parseFloat(prez2);
		
		String prez3 = request.getParameter("prezzoC");
		Float prezzoC = Float.parseFloat(prez3);
		
		AlbumModelDS model = new AlbumModelDS(ds);
		MagazzinoModelDS model1 = new MagazzinoModelDS(ds);
	
		Album al = new Album();
		al.setCodiceAlbum(codice);
		al.setNomeAlbum(nome);
		al.setNartista(artista);
		al.setImgAlbum(picName);
		al.setTipo(tipo);
		al.setDescrizione(descrizione);
		al.setPrezzoS(prezzoS);
		al.setPrezzoV(prezzoV);
		al.setPrezzoC(prezzoC);
		
		try {
			Collection<Album> a = model.doRetrieveAll();
			//controllo codice
			for (Iterator<Album> i = a.iterator();i.hasNext();) {
				Album ele1 = (Album)i.next();
				if(ele1.getCodiceAlbum().equals(codice)) {
					request.setAttribute("err", true);
					getServletContext().getRequestDispatcher("/NuovoAlbum.jsp").forward(request, response); //reindiriziamo alla view
					return;
					}
				}
			//controllo che un artista non pubblichi un album con lo stesso nome
			for (Iterator<Album> i = a.iterator();i.hasNext();) {
				Album ele1 = (Album)i.next();
				if(ele1.getNartista().toLowerCase().equals(artista.toLowerCase())) {
					if(ele1.getNomeAlbum().toLowerCase().equals(nome.toLowerCase())) {
					request.setAttribute("berr", true);
					getServletContext().getRequestDispatcher("/NuovoAlbum.jsp").forward(request, response); //reindiriziamo alla view
					return;
						}
					}
				}
			//controllo tipo
			if(tipo.toLowerCase().equals("album")) {
			model.doSave(al);
			l.add(nome);
			l.add(artista);
			l.add(tipo);
			
			//aggiunta in magazzino
			Magazzino m1 = new Magazzino();
			m1.setNome(nome);
			m1.setAutore(artista);
			m1.setTipo("CD");
			m1.setCosto(prezzoC);
			m1.setQuantità(quant);
			
			Magazzino m2 = new Magazzino();
			m2.setNome(nome);
			m2.setAutore(artista);
			m2.setTipo("Vinile");
			m2.setCosto(prezzoV);
			m2.setQuantità(quant);
			
			model1.doSave(m1);
			model1.doSave(m2);
			
			currentSession.setAttribute("yes", true);
			currentSession.setAttribute("lprod", l);
			getServletContext().getRequestDispatcher("/magazzino.jsp").forward(request, response); //reindiriziamo alla view
				}
			else {
				request.setAttribute("sb", true);
				getServletContext().getRequestDispatcher("/NuovoAlbum.jsp").forward(request, response); //reindiriziamo alla view
				return;
				}
			}
		catch(SQLException e){
			Utility.print(e);
			request.setAttribute("error", e.getMessage());
			}
	}

}

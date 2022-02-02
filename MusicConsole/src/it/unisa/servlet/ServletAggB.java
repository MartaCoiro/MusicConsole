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
import Class.Brano;
import it.unisa.model.BraniModelDS;
import it.unisa.utils.Utility;
@MultipartConfig
@WebServlet("/ServletAggB")
public class ServletAggB extends HttpServlet {
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
		String genere = request.getParameter("genere");
		String descrizione = request.getParameter("descrizione");
		
		String prez = request.getParameter("prezzo");
		Float prezzo = Float.parseFloat(prez);
		
		String soundname = null;
		String pathsound = "C:\\Users\\ACER\\eclipse-workspace1\\MusicConsole\\WebContent\\brani";
		Part sound = request.getPart("suono");
		if(sound!=null) {
			soundname = request.getPart("suono").getSubmittedFileName();
			if(!soundname.equals("")) {
				sound.write(pathsound + File.separator + soundname);
			}
		}
		
		BraniModelDS model = new BraniModelDS(ds);
		
		Brano b = new Brano();
		b.setCodice(codice);
		b.setTitolo(nome);
		b.setCantante(artista);
		b.setImgBrano(picName);
		b.setGenere(genere);
		b.setTipo(tipo);
		b.setSuono(soundname);
		b.setDescrizione(descrizione);
		b.setPrezzo(prezzo);
		
		try {
			Collection<Brano> br = model.doRetrieveAll();
			//controllo codice
			for (Iterator<Brano> i = br.iterator();i.hasNext();) {
				Brano ele1 = (Brano)i.next();
				if(ele1.getCodice().equals(codice)) {
					request.setAttribute("err", true);
					getServletContext().getRequestDispatcher("/NuovoBrano.jsp").forward(request, response); //reindiriziamo alla view
					return;
					}
			}
			//controllo che un artista non pubblichi un brano con lo stesso nome
			for (Iterator<Brano> i = br.iterator();i.hasNext();) {
				Brano ele1 = (Brano)i.next();
				if(ele1.getCantante().toLowerCase().equals(artista.toLowerCase())) {
					if(ele1.getTitolo().toLowerCase().equals(nome.toLowerCase())) {
						request.setAttribute("berr", true);
						getServletContext().getRequestDispatcher("/NuovoBrano.jsp").forward(request, response); //reindiriziamo alla view
						return;
					}
				}
			}
			//controllo tipo 
			if(tipo.toLowerCase().equals("brano")) {
				model.doSave(b);
				l.add(nome);
				l.add(artista);
				l.add(tipo);
				currentSession.setAttribute("yes", true);
				currentSession.setAttribute("lprod", l);
				getServletContext().getRequestDispatcher("/magazzino.jsp").forward(request, response); //reindiriziamo alla view
					}
			else {
				request.setAttribute("sb", true);
				getServletContext().getRequestDispatcher("/NuovoBrano.jsp").forward(request, response); //reindiriziamo alla view
				return;
				}
		}
		catch(SQLException e){
			Utility.print(e);
			request.setAttribute("error", e.getMessage());
			}
		
	}

}

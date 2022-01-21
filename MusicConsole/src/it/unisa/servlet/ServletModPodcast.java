package it.unisa.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
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

import it.unisa.model.Album;
import it.unisa.model.AlbumModelDS;
import it.unisa.model.BraniModelDS;
import it.unisa.model.Brano;
import it.unisa.model.Podcast;
import it.unisa.model.PodcastModelDS;
import it.unisa.utils.Utility;

@MultipartConfig
@WebServlet("/ServletModPodcast")
public class ServletModPodcast extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		
		PodcastModelDS model = new PodcastModelDS(ds);
		BraniModelDS model2 = new BraniModelDS(ds);
		AlbumModelDS model1 = new AlbumModelDS(ds);
		
		String nomeA = request.getParameter("artista");
		
		String picName = null;
		String path = "C:\\Users\\utente\\eclipse-workspace1\\MusicConsole\\WebContent\\imgs";
		Part pic = request.getPart("immagine");
		if(pic!=null) {
			picName = request.getPart("immagine").getSubmittedFileName();
			if(!picName.equals("")) {
				pic.write(path + File.separator + picName);
			}
		}
		
		String tipo = request.getParameter("tipo");
		String descrizione = request.getParameter("descrizione");
		
		String prez = request.getParameter("prezzo");
		
		
		HttpSession currentSession = request.getSession();
		
		try {
			Podcast a = (Podcast)currentSession.getAttribute("pod");
			Podcast aa = model.Restituisci(a.getNomePodcast());
//podcast
		if(tipo.toLowerCase().equals("podcast")) {
			
			//controllo nome artista
			if(!nomeA.toLowerCase().equals(a.getIdeatore().toLowerCase())) {
					if(nomeA.length()==0) {
						request.setAttribute("pre", true);
						getServletContext().getRequestDispatcher("/editPodcast.jsp").forward(request, response); //reindiriziamo alla view		
						return;
					}else {
							model.doUpdate("artista", nomeA, aa.getNomePodcast());
					}
			}
			//controllo descrizione
			if(descrizione.length()>0) {
				if(!descrizione.toLowerCase().equals(a.getDescrizione().toLowerCase())) {
				model.doUpdate("descrizione", descrizione, aa.getNomePodcast());
						}
				}
			
			if(descrizione.length()==0) {
				descrizione=aa.getDescrizione();
			}
			//controllo dei prezzi
			if(prez.length()==0) {
				request.setAttribute("pre", true);
				getServletContext().getRequestDispatcher("/editPodcast.jsp").forward(request, response); //reindiriziamo alla view		
				return;
			}
			Float prezzo = Float.parseFloat(prez);
			if(!prezzo.equals(a.getPrezzo())) {
				model.doUpdatePrezzo("prezzo", prezzo, aa.getNomePodcast());
			}
			//controllo immagine
			if((picName!=null)&&(picName.length()>0)) {
				if(!picName.toLowerCase().equals(aa.getImgPod().toLowerCase())) {
					model.doUpdate("img", picName, aa.getNomePodcast());
				}
			}
			if((picName==null)||(picName.length()==0)) {
				picName=aa.getImgPod();
			}
		}
//album
			if(!tipo.toLowerCase().equals(a.getTipo().toLowerCase())) {
				/*model.doUpdate("tipo", tipo, aa.getNomePodcast());*/
				if(tipo.toLowerCase().equals("album")) {
					//controllo nome
					String nome = request.getParameter("nome");
					if(nome.length()==0) {
						request.setAttribute("pre", true);
						getServletContext().getRequestDispatcher("/editPodcast.jsp").forward(request, response); //reindiriziamo alla view		
						return;
					}
					//controllo nome artista
					if(nomeA.length()==0) {
						request.setAttribute("pre", true);
						getServletContext().getRequestDispatcher("/editPodcast.jsp").forward(request, response); //reindiriziamo alla view		
						return;
					}
					//Controllo codice
					String c = request.getParameter("codice");
					if(c.length()==0) {
						request.setAttribute("pre", true);
						getServletContext().getRequestDispatcher("/editPodcast.jsp").forward(request, response); //reindiriziamo alla view		
						return;
					}
					Integer cod = Integer.parseInt(c);
	
					//controllo prezzi
					String prez1 = request.getParameter("prezzoS");
					String prez2 = request.getParameter("prezzoV");
					String prez3 = request.getParameter("prezzoC");
					if((prez1.length()==0)||(prez2.length()==0)||(prez3.length()==0)) {
						request.setAttribute("pre", true);
						getServletContext().getRequestDispatcher("/editPodcast.jsp").forward(request, response); //reindiriziamo alla view		
						return;
					}
					Float prezzoS = Float.parseFloat(prez1);
					Float prezzoV = Float.parseFloat(prez2);
					Float prezzoC = Float.parseFloat(prez3);
					//controllo descrizione
					if(descrizione.length()>0) {
						if(!descrizione.toLowerCase().equals(a.getDescrizione().toLowerCase())) {
						model.doUpdate("descrizione", descrizione, aa.getNomePodcast());
								}
						}
					
					if(descrizione.length()==0) {
						descrizione=aa.getDescrizione();
					}
					//controllo immagine
					if((picName!=null)&&(picName.length()>0)) {
						if(!picName.toLowerCase().equals(aa.getImgPod().toLowerCase())) {
							model.doUpdate("img", picName, aa.getNomePodcast());
						}
					}
					if((picName==null)||(picName.length()==0)) {
						picName=aa.getImgPod();
					}
					
					Album b = new Album();
					b.setCodiceAlbum(cod);
					b.setNomeAlbum(nome);
					b.setNartista(nomeA);
					b.setImgAlbum(picName);
					b.setTipo(tipo);
					b.setDescrizione(descrizione);
					b.setPrezzoS(prezzoS);
					b.setPrezzoV(prezzoV);
					b.setPrezzoC(prezzoC);
					Collection<Album> p = model1.doRetrieveAll();
					for (Iterator<Album> i = p.iterator();i.hasNext();) {
						 Album ele2 = (Album)i.next();
						if(ele2.getCodiceAlbum()==cod) {
							request.setAttribute("ercod", true);
							getServletContext().getRequestDispatcher("/editPodcast.jsp").forward(request, response); //reindiriziamo alla view		
							return;
								}
						 }
					model1.doSave(b);
					model.doDelete(aa.getNomePodcast());
					}
//Brano

					if(tipo.toLowerCase().equals("brano")) {
					//controllo nome
					String nome = request.getParameter("nome");
					if(nome.length()==0) {
						request.setAttribute("pre", true);
						getServletContext().getRequestDispatcher("/editPodcast.jsp").forward(request, response); //reindiriziamo alla view		
						return;
					}
					//controllo nome artista
					if(nomeA.length()==0) {
						request.setAttribute("pre", true);
						getServletContext().getRequestDispatcher("/editPodcast.jsp").forward(request, response); //reindiriziamo alla view		
						return;
					}
					String genere = request.getParameter("genere");
					//Controllo codice
					String c = request.getParameter("codice");
					if(c.length()==0) {
						request.setAttribute("pre", true);
						getServletContext().getRequestDispatcher("/editPodcast.jsp").forward(request, response); //reindiriziamo alla view		
						return;
					}
					Integer cod = Integer.parseInt(c);
					//controllo suono
					String soundname = null;
					String pathsound = "C:\\Users\\utente\\eclipse-workspace1\\MusicConsole\\WebContent\\brani";
					Part sound = request.getPart("suono");
					if(sound!=null) {
						soundname = request.getPart("suono").getSubmittedFileName();
						if(!soundname.equals("")) {
							sound.write(pathsound + File.separator + soundname);
						}
					}
					if(soundname.length()==0) {
						request.setAttribute("pre", true);
						getServletContext().getRequestDispatcher("/editPodcast.jsp").forward(request, response); //reindiriziamo alla view		
						return;
					}
					//controllo dei prezzi
					if(prez.length()==0) {
						request.setAttribute("pre", true);
						getServletContext().getRequestDispatcher("/editPodcast.jsp").forward(request, response); //reindiriziamo alla view		
						return;
					}
					Float prezzo = Float.parseFloat(prez);
					//controllo descrizione
					if(descrizione.length()>0) {
						if(!descrizione.toLowerCase().equals(a.getDescrizione().toLowerCase())) {
						model.doUpdate("descrizione", descrizione, aa.getNomePodcast());
								}
						}
					
					if(descrizione.length()==0) {
						descrizione=aa.getDescrizione();
					}
					//controllo immagine
					if((picName!=null)&&(picName.length()>0)) {
						if(!picName.toLowerCase().equals(aa.getImgPod().toLowerCase())) {
							model.doUpdate("img", picName, aa.getNomePodcast());
						}
					}
					if((picName==null)||(picName.length()==0)) {
						picName=aa.getImgPod();
					}
					
					Brano b = new Brano();
					b.setCodice(cod);
					b.setTitolo(nome);
					b.setCantante(nomeA);
					b.setImgBrano(picName);
					b.setTipo(tipo);
					b.setGenere(genere);
					b.setSuono(soundname);
					b.setDescrizione(descrizione);
					b.setPrezzo(prezzo);
					Collection<Brano> p = model2.doRetrieveAll();
					for (Iterator<Brano> i = p.iterator();i.hasNext();) {
						 Brano ele2 = (Brano)i.next();
						if(ele2.getCodice()==cod) {
							request.setAttribute("ercod", true);
							getServletContext().getRequestDispatcher("/editPodcast.jsp").forward(request, response); //reindiriziamo alla view		
							return;
								}
						 }
					model2.doSave(b);
					model.doDelete(aa.getNomePodcast());
					
				}
			}
			request.setAttribute("buon", true);
			getServletContext().getRequestDispatcher("/ServletProd?tipo=catalogo").forward(request, response); //reindiriziamo alla view		
		
		}
		catch(SQLException e){
			Utility.print(e);
			request.setAttribute("error", e.getMessage());
			}
	}

}

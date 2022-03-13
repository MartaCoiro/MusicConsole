package gestioneProdotti;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
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

import it.unisa.utils.DBConnectionPool;
import it.unisa.utils.Utility;

@MultipartConfig
@WebServlet("/ServletModBrano")
public class ServletModBrano extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		Connection ds = null;
		try {
			ds = DBConnectionPool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		BraniModelDS model = new BraniModelDS(ds);
		AlbumModelDS model1 = new AlbumModelDS(ds);
		PodcastModelDS model2 = new PodcastModelDS(ds);
		
		String nome = request.getParameter("nome");
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
		String genere = request.getParameter("genere").toLowerCase();
		String descrizione = request.getParameter("descrizione");
		
		String prez = request.getParameter("prezzo");
		
		
		String soundname = null;
		String pathsound = "C:\\Users\\utente\\eclipse-workspace1\\MusicConsole\\WebContent\\brani";
		Part sound = request.getPart("suono");
		if(sound!=null) {
			soundname = request.getPart("suono").getSubmittedFileName();
			if(!soundname.equals("")) {
				sound.write(pathsound + File.separator + soundname);
			}
		}
		
		HttpSession currentSession = request.getSession();
		
		try {
			Brano a = (Brano)currentSession.getAttribute("bra");
			Brano aa = model.doRetrieveByKey(a.getTitolo(), a.getCantante());
//brano			
			if(tipo.toLowerCase().equals("brano")) {
			
				//controllo nome
			if(!nome.toLowerCase().equals(a.getTitolo().toLowerCase())) {
				if(nome.length()==0) {
					request.setAttribute("pre", true);
					request.getServletContext().getRequestDispatcher("/editBrano.jsp").forward(request, response); //reindiriziamo alla view		
					return;
				}else {
				model.doUpdate("nome", nome, aa.getCodice());
				}
			}
		//controllo descrizione
			if(descrizione.length()>0) {
				if(!descrizione.toLowerCase().equals(a.getDescrizione().toLowerCase())) {
				model.doUpdate("descrizione", descrizione, aa.getCodice());
						}
				}
			
			if(descrizione.length()==0) {
				descrizione=aa.getDescrizione();
			}
			//controllo prezzo
			if(prez.length()==0) {
				request.setAttribute("pre", true);
				request.getServletContext().getRequestDispatcher("/editBrano.jsp").forward(request, response); //reindiriziamo alla view		
				return;
			}
			Float prezzo = Float.parseFloat(prez);
			if(!prezzo.equals(a.getPrezzo())) {
				model.doUpdatePrezzo("prezzo", prezzo, aa.getCodice());
			}
			//controllo nome artista
			if(!nomeA.toLowerCase().equals(a.getCantante().toLowerCase())) {
				if(nomeA.length()==0) {
					request.setAttribute("pre", true);
					request.getServletContext().getRequestDispatcher("/editBrano.jsp").forward(request, response); //reindiriziamo alla view		
					return;
				}else {
				model.doUpdate("artista", nomeA, aa.getCodice());
				}
			}
			//controllo immagine
			if((picName!=null)&&(picName.length()>0)) {
				if(!picName.toLowerCase().equals(a.getImgBrano().toLowerCase())) {
					model.doUpdate("img", picName, aa.getCodice());
				}
			}
			if((picName==null)||(picName.length()==0)) {
				picName=aa.getImgBrano();
			}
			
			if(!genere.toLowerCase().equals(a.getGenere().toLowerCase())) {
				if(genere.equals("pop")||(genere.equals("classico"))||(genere.equals("indie"))||(genere.equals("rap"))||(genere.equals("neomelodico"))||(genere.equals("freestyle"))||(genere.endsWith("hip-hop"))) {
					model.doUpdate("genere", genere, aa.getCodice());
				}else {
					request.setAttribute("g", true);
					request.getServletContext().getRequestDispatcher("/editBrano.jsp").forward(request, response); //reindiriziamo alla view		
					return;
				}
			}
			
			//controllo suono
			if(soundname.length()>0) {
				if(!soundname.toLowerCase().equals(a.getSuono().toLowerCase())) {
					model.doUpdate("suono", soundname, aa.getCodice());
				}
			}
			
			if(soundname.length()==0) {
				soundname=aa.getSuono();
			}
			
			
		}
	
//Album
			if(!tipo.toLowerCase().equals(a.getTipo().toLowerCase())) {
				/*model.doUpdate("tipo", tipo, aa.getCodice());*/
				if(tipo.toLowerCase().equals("album")) {
					//controllo nome
					if(nome.length()==0) {
						request.setAttribute("pre", true);
						request.getServletContext().getRequestDispatcher("/editBrano.jsp").forward(request, response); //reindiriziamo alla view		
						return;
					}
					//controllo nome artista
					if(nomeA.length()==0) {
						request.setAttribute("pre", true);
						request.getServletContext().getRequestDispatcher("/editBrano.jsp").forward(request, response); //reindiriziamo alla view		
						return;
					}
					//controllo codice
					String c = request.getParameter("codice");
					if(c.length()==0) {
						request.setAttribute("pre", true);
						request.getServletContext().getRequestDispatcher("/editBrano.jsp").forward(request, response); //reindiriziamo alla view		
						return;
					}
					Integer cod = Integer.parseInt(c);
					//controllo prezzi
					String prez1 = request.getParameter("prezzoS");
					String prez2 = request.getParameter("prezzoV");
					String prez3 = request.getParameter("prezzoC");
					if((prez1.length()==0)||(prez2.length()==0)||(prez3.length()==0)) {
						request.setAttribute("pre", true);
						request.getServletContext().getRequestDispatcher("/editBrano.jsp").forward(request, response); //reindiriziamo alla view		
						return;
					}
					Float prezzoC = Float.parseFloat(prez3);
					Float prezzoS = Float.parseFloat(prez1);
					Float prezzoV = Float.parseFloat(prez2);
					//controllo descrizione
					if(descrizione.length()>0) {
						if(!descrizione.toLowerCase().equals(a.getDescrizione().toLowerCase())) {
						model.doUpdate("descrizione", descrizione, aa.getCodice());
								}
						}
					
					if(descrizione.length()==0) {
						descrizione=aa.getDescrizione();
					}
					//controllo immagine
					if((picName!=null)&&(picName.length()>0)) {
						if(!picName.toLowerCase().equals(aa.getImgBrano().toLowerCase())) {
							model.doUpdate("img", picName, aa.getCodice());
						}
					}
					if((picName==null)||(picName.length()==0)) {
						picName=aa.getImgBrano();
					}
					Album b = new Album();
					//Album b = new Album(cod,nome,picName,nomeA,tipo,prezzoS,prezzoV,prezzoC,descrizione);
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
							request.getServletContext().getRequestDispatcher("/editBrano.jsp").forward(request, response); //reindiriziamo alla view		
							return;
								}
						 }
					model1.doSave(b);
					model.doDelete(aa.getCodice());
					}
//podcast
				if(tipo.toLowerCase().equals("podcast")) {
					//controllo nome artista
					if(nomeA.length()==0) {
						request.setAttribute("pre", true);
						request.getServletContext().getRequestDispatcher("/editBrano.jsp").forward(request, response); //reindiriziamo alla view		
						return;
					}
					//controllo nome
					if(nome.length()==0) {
						request.setAttribute("pre", true);
						request.getServletContext().getRequestDispatcher("/editBrano.jsp").forward(request, response); //reindiriziamo alla view		
						return;
					}
					//controllo dei prezzi
					if(prez.length()==0) {
						request.setAttribute("pre", true);
						request.getServletContext().getRequestDispatcher("/editBrano.jsp").forward(request, response); //reindiriziamo alla view		
						return;
					}
					Float prezzo = Float.parseFloat(prez);
					//controllo descrizione
					if(descrizione.length()>0) {
						if(!descrizione.toLowerCase().equals(a.getDescrizione().toLowerCase())) {
						model.doUpdate("descrizione", descrizione, aa.getCodice());
								}
						}
					
					if(descrizione.length()==0) {
						descrizione=aa.getDescrizione();
					}
					//controllo immagine
					if((picName!=null)&&(picName.length()>0)) {
						if(!picName.toLowerCase().equals(aa.getImgBrano().toLowerCase())) {
							model.doUpdate("img", picName, aa.getCodice());
						}
					}
					if((picName==null)||(picName.length()==0)) {
						picName=aa.getImgBrano();
					}
					Podcast o = new Podcast();
					//Podcast o = new Podcast(nome,nomeA,descrizione,0,0,picName,tipo,prezzo);
					o.setNomePodcast(nome);
					o.setIdeatore(nomeA);
					o.setImgPod(picName);
					o.setTipo(tipo);
					o.setDescrizione(descrizione);
					o.setPrezzo(prezzo);
					Collection<Podcast> p = model2.doRetrieveAll();
					for (Iterator<Podcast> i = p.iterator();i.hasNext();) {
						 Podcast ele2 = (Podcast)i.next();
						if(ele2.getNomePodcast().toLowerCase().equals(nome)) {
							request.setAttribute("ercod1", true);
							request.getServletContext().getRequestDispatcher("/editBrano.jsp").forward(request, response); //reindiriziamo alla view		
							return;
								}
						 }
					model2.doSave(o);
					model.doDelete(aa.getCodice());
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

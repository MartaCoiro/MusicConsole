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
@WebServlet("/ServletModAlbum")
public class ServletModAlbum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		
		Connection ds=null;
		try {
			ds=DBConnectionPool.getConnection();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		AlbumModelDS model = new AlbumModelDS(ds);
		BraniModelDS model1 = new BraniModelDS(ds);
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
		String descrizione = request.getParameter("descrizione");
		
		String prez1 = request.getParameter("prezzoS");
		String prez2 = request.getParameter("prezzoV");
		String prez3 = request.getParameter("prezzoC");
		
		
		HttpSession currentSession = request.getSession();
		
		try {
			Album a = (Album)currentSession.getAttribute("alb");
			Album aa = model.doRetrieveByKey(a.getNomeAlbum(), a.getNartista());
//Album			
			if(tipo.toLowerCase().equals("album")) {
				//controllo nome
			if(!nome.toLowerCase().equals(a.getNomeAlbum().toLowerCase())) {
				if(nome.length()==0) {
					request.setAttribute("pre", true);
					getServletContext().getRequestDispatcher("/editAlbum.jsp").forward(request, response); //reindiriziamo alla view		
					return;
				}else {
				model.doUpdate("nome", nome, aa.getCodiceAlbum());
				}
			}
			//controllo descrizione
			if(descrizione.length()>0) {
				if(!descrizione.toLowerCase().equals(a.getDescrizione().toLowerCase())) {
				model.doUpdate("descrizione", descrizione, aa.getCodiceAlbum());
						}
				}
			
			if(descrizione.length()==0) {
				descrizione=aa.getDescrizione();
			}
			//controllo prezzi
			if((prez1.length()==0)||(prez2.length()==0)||(prez3.length()==0)) {
				request.setAttribute("pre", true);
				getServletContext().getRequestDispatcher("/editAlbum.jsp").forward(request, response); //reindiriziamo alla view		
				return;
			}
			Float prezzoS = Float.parseFloat(prez1);
			Float prezzoV = Float.parseFloat(prez2);
			Float prezzoC = Float.parseFloat(prez3);
			
			if(!prezzoS.equals(a.getPrezzoS())) {
				model.doUpdatePrezzo("prezzoS", prezzoS, aa.getCodiceAlbum());
			}
			
			if(!prezzoV.equals(a.getPrezzoV())) {
				model.doUpdatePrezzo("prezzoV", prezzoV, aa.getCodiceAlbum());
			}
			
			if(!prezzoC.equals(a.getPrezzoC())) {
				model.doUpdatePrezzo("prezzoC", prezzoC, aa.getCodiceAlbum());
			}
		//controllo nome artista
			if(!nomeA.toLowerCase().equals(a.getNartista().toLowerCase())) {
				if(nomeA.length()==0) {
					request.setAttribute("pre", true);
					getServletContext().getRequestDispatcher("/editAlbum.jsp").forward(request, response); //reindiriziamo alla view		
					return;
				}else {
						model.doUpdate("artista", nomeA, aa.getCodiceAlbum());
					}
				}
			//controllo immagine	
			if((picName!=null)&&(picName.length()>0)) {
				if(!picName.toLowerCase().equals(aa.getImgAlbum().toLowerCase())) {
					model.doUpdate("img", picName, aa.getCodiceAlbum());
				}
			}
			if((picName==null)||(picName.length()==0)) {
				picName=aa.getImgAlbum();
			}
}
//brano
			if(!tipo.toLowerCase().equals(a.getTipo().toLowerCase())) {
				/*model.doUpdate("tipo", tipo, aa.getCodiceAlbum());*/
				if(tipo.toLowerCase().equals("brano")) {
					String genere = request.getParameter("genere");
					String c = request.getParameter("codice");
					//controllo prezzo
					String pre = request.getParameter("prezzo");
					if(pre.length()==0) {
						request.setAttribute("pre", true);
						getServletContext().getRequestDispatcher("/editAlbum.jsp").forward(request, response); //reindiriziamo alla view		
						return;
					}
					Float prezzo = Float.parseFloat(pre);
					//controllo e recupero suono
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
						getServletContext().getRequestDispatcher("/editAlbum.jsp").forward(request, response); //reindiriziamo alla view		
						return;
					}
					//Controllo codice
					if(c.length()==0) {
						request.setAttribute("pre", true);
						getServletContext().getRequestDispatcher("/editAlbum.jsp").forward(request, response); //reindiriziamo alla view		
						return;
					}
					Integer cod = Integer.parseInt(c);
					//controllo nome
					if(nome.length()==0) {
						request.setAttribute("pre", true);
						getServletContext().getRequestDispatcher("/editAlbum.jsp").forward(request, response); //reindiriziamo alla view		
						return;
					}
					//controllo nome artista
					if(nomeA.length()==0) {
						request.setAttribute("pre", true);
						getServletContext().getRequestDispatcher("/editAlbum.jsp").forward(request, response); //reindiriziamo alla view		
						return;
					}
					//controllo descrizione
					if(descrizione.length()>0) {
						if(!descrizione.toLowerCase().equals(a.getDescrizione().toLowerCase())) {
						model.doUpdate("descrizione", descrizione, aa.getCodiceAlbum());
								}
						}
					
					if(descrizione.length()==0) {
						descrizione=aa.getDescrizione();
					}
					//controllo immagine
					if((picName!=null)&&(picName.length()>0)) {
						if(!picName.toLowerCase().equals(aa.getImgAlbum().toLowerCase())) {
							model.doUpdate("img", picName, aa.getCodiceAlbum());
						}
					}
					if((picName==null)||(picName.length()==0)) {
						picName=aa.getImgAlbum();
					}
					Brano b = new Brano();
					//Brano b = new Brano(cod,nome,0,nomeA,picName,genere,tipo,soundname,prezzo,descrizione);
					b.setCodice(cod);
					b.setTitolo(nome);
					b.setCantante(nomeA);
					b.setImgBrano(picName);
					b.setTipo(tipo);
					b.setGenere(genere);
					b.setSuono(soundname);
					b.setDescrizione(descrizione);
					b.setPrezzo(prezzo);
					Collection<Brano> p = model1.doRetrieveAll();
					for (Iterator<Brano> i = p.iterator();i.hasNext();) {
						 Brano ele2 = (Brano)i.next();
						if(ele2.getCodice()==cod) {
							request.setAttribute("ercod", true);
							getServletContext().getRequestDispatcher("/editAlbum.jsp").forward(request, response); //reindiriziamo alla view		
							return;
								}
						 }
					model1.doSave(b);
					model.doDelete(aa.getCodiceAlbum());
					
				}
//podcast
				if(tipo.toLowerCase().equals("podcast")) {
					String pre = request.getParameter("prezzo");
					//controllo nome artista
					if(nomeA.length()==0) {
						request.setAttribute("pre", true);
						getServletContext().getRequestDispatcher("/editAlbum.jsp").forward(request, response); //reindiriziamo alla view		
						return;
					}
					//controllo nome
					if(nome.length()==0) {
						request.setAttribute("pre", true);
						getServletContext().getRequestDispatcher("/editAlbum.jsp").forward(request, response); //reindiriziamo alla view		
						return;
					}
					//controllo dei prezzi
					if(pre.length()==0) {
						request.setAttribute("pre", true);
						getServletContext().getRequestDispatcher("/editAlbum.jsp").forward(request, response); //reindiriziamo alla view		
						return;
					}
					Float prezzo = Float.parseFloat(pre);
					//controllo descrizione
					if(descrizione.length()>0) {
						if(!descrizione.toLowerCase().equals(a.getDescrizione().toLowerCase())) {
						model.doUpdate("descrizione", descrizione, aa.getCodiceAlbum());
								}
						}
					
					if(descrizione.length()==0) {
						descrizione=aa.getDescrizione();
					}
					//controllo immagine
					if((picName!=null)&&(picName.length()>0)) {
						if(!picName.toLowerCase().equals(aa.getImgAlbum().toLowerCase())) {
							model.doUpdate("img", picName, aa.getCodiceAlbum());
						}
					}
					if((picName==null)||(picName.length()==0)) {
						picName=aa.getImgAlbum();
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
							getServletContext().getRequestDispatcher("/editAlbum.jsp").forward(request, response); //reindiriziamo alla view		
							return;
								}
						 }
					model2.doSave(o);
					model.doDelete(aa.getCodiceAlbum());
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

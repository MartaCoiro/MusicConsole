package gestioneProdotti;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import it.unisa.utils.DBConnectionPool;
import it.unisa.utils.Utility;

import javax.servlet.http.HttpSession;


@WebServlet("/ServletPass")
public class ServletPass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		Connection ds = null;
		try {
			ds = DBConnectionPool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.setContentType("text/html");//tipo di file
		
		AlbumModelDS model1 = new AlbumModelDS(ds);
		BraniModelDS model2 = new BraniModelDS(ds);
		PodcastModelDS model3 = new PodcastModelDS(ds);
		
		HttpSession currentSession = request.getSession();
		
		ArrayList<String> prod = new ArrayList<String>();
		
		String nome = request.getParameter("nome");
		String artista = request.getParameter("autore");
		String tipo = request.getParameter("tipo");
		
		try {
			if(tipo.toLowerCase().equals("album")) {
				Album a = model1.doRetrieveByKey(nome, artista);
				currentSession.setAttribute("alb", a);
				getServletContext().getRequestDispatcher(response.encodeURL("/editAlbum.jsp")).forward(request, response); //reindiriziamo alla view*/	
				return;
			}
			else if(tipo.toLowerCase().equals("brano")) {
				Brano b = model2.doRetrieveByKey(nome, artista);
				currentSession.setAttribute("bra", b);
				getServletContext().getRequestDispatcher(response.encodeURL("/editBrano.jsp")).forward(request, response); //reindiriziamo alla view	
				return;
			}
			else if(tipo.toLowerCase().equals("podcast")) {
				Podcast p = model3.Restituisci(nome);
				currentSession.setAttribute("pod", p);
				getServletContext().getRequestDispatcher(response.encodeURL("/editPodcast.jsp")).forward(request, response); //reindiriziamo alla view	
				return;
			}
		}
		catch(SQLException e){
			Utility.print(e);
			request.setAttribute("error", e.getMessage());
			}
	}

}

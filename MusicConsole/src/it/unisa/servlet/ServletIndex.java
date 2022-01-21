package it.unisa.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import it.unisa.model.Album;
import it.unisa.model.AlbumModelDS;
import it.unisa.model.BraniModelDS;
import it.unisa.model.Brano;
import it.unisa.model.Podcast;
import it.unisa.model.PodcastModelDS;
import it.unisa.utils.Utility;

@WebServlet("/ServletIndex")
public class ServletIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		
		AlbumModelDS model1 = new AlbumModelDS(ds);
		BraniModelDS model2 = new BraniModelDS(ds);
		PodcastModelDS model3 = new PodcastModelDS(ds);
	
		response.setContentType("text/html");//tipo di file
		
		ArrayList<Album> prA = new ArrayList<Album>();
		ArrayList<Brano> prB = new ArrayList<Brano>();
		ArrayList<Podcast> prP = new ArrayList<Podcast>();
		
		
		try {
			
			//recupero prodotti
			Collection<Album> a = model1.doRetrieveAll();
			for(Iterator<Album> i = a.iterator(); i.hasNext();) {
				Album al = (Album)i.next();
				if(al.getDat()!=null) {
					prA.add(al);
				}
			}
			
			Collection<Brano> b = model2.doRetrieveAll();
			for(Iterator<Brano> i = b.iterator(); i.hasNext();) {
				Brano al = (Brano)i.next();
				if(al.getDat()!=null) {
					prB.add(al);
				}
			}
			
			Collection<Podcast> po = model3.doRetrieveAll();
			for(Iterator<Podcast> i = po.iterator(); i.hasNext();) {
				Podcast al = (Podcast)i.next();
				if(al.getDat()!=null) {
					prP.add(al);
				}
			}
			
			request.setAttribute("prA", prA);
			request.setAttribute("prB", prB);
			request.setAttribute("prP", prP);
			getServletContext().getRequestDispatcher("/index.jsp").forward(request, response); //reindiriziamo alla view	
		}
		 catch(SQLException e){
				Utility.print(e);
				request.setAttribute("error", e.getMessage());
				}
	}

}

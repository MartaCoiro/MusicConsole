package gestioneAcquisti;

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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import gestioneProdotti.Album;
import gestioneProdotti.AlbumModelDS;
import it.unisa.utils.DBConnectionPool;
import it.unisa.utils.Utility;

@WebServlet("/ServletOrdini")
public class ServletOrdini extends HttpServlet {
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
		
		OrdiniModelDS model = new OrdiniModelDS(ds);
		
		HttpSession currentSession = request.getSession();
		
		ArrayList<Ordini> ordini = new ArrayList<Ordini>();
		
		try {
			
			Collection<Ordini> or = model.doRetrieveAllOrdinato();
			for (Iterator<Ordini> i = or.iterator();i.hasNext();) {
				Ordini ele = (Ordini)i.next();
				ordini.add(ele);
			}
			currentSession.setAttribute("lordini", ordini);
			getServletContext().getRequestDispatcher("/ordini.jsp").forward(request, response); //reindiriziamo alla view
			}
		
		catch(SQLException e){
			Utility.print(e);
			request.setAttribute("error", e.getMessage());
			}
	}
}

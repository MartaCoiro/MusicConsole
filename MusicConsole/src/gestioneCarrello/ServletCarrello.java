package gestioneCarrello;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
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

import gestioneProdotti.Magazzino;
import gestioneProdotti.MagazzinoModelDS;
import it.unisa.utils.DBConnectionPool;
import it.unisa.utils.Utility;

@WebServlet("/ServletCarrello")
public class ServletCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		//DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		Connection ds = null;
		try {
			ds = DBConnectionPool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HttpSession currentSession = request.getSession();
		String ut = (String)currentSession.getAttribute("acc");
		
		CarrelloModelDS model = new CarrelloModelDS(ds);
		MagazzinoModelDS model1 = new MagazzinoModelDS(ds);
		
		try {
			Float tot=model.doSum(ut);
			
			
			//controllo se c'� disponibilit�
			Collection<Carrello> car = model.RestXUtente(ut);
			for(Iterator<Carrello> c = car.iterator();c.hasNext();) {
				Carrello ele = (Carrello)c.next();
				if((ele.getTipo().equals("CD"))||(ele.getTipo().equals("Vinile"))) {
					Collection<Magazzino> m = model1.doRetrieveAll();
					for(Iterator<Magazzino> i = m.iterator();i.hasNext();) {
						Magazzino ele1 = (Magazzino)i.next();
						if((ele1.getNome().toLowerCase().equals(ele.getNome().toLowerCase())&&(ele1.getAutore().toLowerCase().equals(ele.getAutore().toLowerCase()))&&(ele1.getTipo().toLowerCase().equals(ele.getTipo().toLowerCase())))){
							if(ele1.getQuantit�()<ele.getQuantit�()) {
								currentSession.setAttribute("noD", true);
								request.setAttribute("NomeprodND",ele.getNome());
								request.setAttribute("AutoreprodND",ele.getAutore());
								request.setAttribute("TipoprodND",ele.getTipo());
							}
							else {
								currentSession.setAttribute("noD", false);
							}
						}
				}
			}
		}
			
			currentSession.setAttribute("tot", tot);
			request.getServletContext().getRequestDispatcher("/Carrello.jsp").forward(request, response); //reindiriziamo alla view	
		
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

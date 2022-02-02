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
import Class.Ordini;
import it.unisa.model.OrdiniModelDS;
import it.unisa.utils.Utility;

@WebServlet("/ServletAggStato")
public class ServletAggStato extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
		
			DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
			
			HttpSession currentSession = request.getSession();
			
			OrdiniModelDS model = new OrdiniModelDS(ds);
			
			int indice = (int)currentSession.getAttribute("indice");
			
			ArrayList<Ordini> ordini = new ArrayList<Ordini>();
			
			try {
				Collection<Ordini> or = model.doRetrieveAllOrdinato();
				for (Iterator<Ordini> i = or.iterator();i.hasNext();) { //settiamo lo stato dell'ordine spedito
					Ordini ele = (Ordini)i.next();
					if(ele.getIndice().equals(indice)) {
						ele.setStato("true");
						model.doUpdate("true", indice);
						}
					ordini.add(ele);
					}
				
				currentSession.setAttribute("lordini", ordini);
				request.setAttribute("conf", "true");
				getServletContext().getRequestDispatcher(response.encodeURL("/ordini.jsp")).forward(request, response); //reindiriziamo alla view	
				
		}
			catch(SQLException e){
				Utility.print(e);
				request.setAttribute("error", e.getMessage());
				}
	}
}

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

import it.unisa.model.Ordini;
import it.unisa.model.OrdiniModelDS;
import it.unisa.utils.Utility;


@WebServlet("/ServletPreparaOrdine")
public class ServletPreparaOrdine extends HttpServlet {
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
		
		ArrayList<String> lprocar = new ArrayList<String>();
		
		String np = request.getParameter("numProd");
		int numProd = Integer.parseInt(np);
		String ind = request.getParameter("indice");
		int indice = Integer.parseInt(ind);
		
		try {
			Collection<Ordini> or = model.doRetrieveAll();
			for (Iterator<Ordini> i = or.iterator();i.hasNext();) {
				Ordini ele = (Ordini)i.next();
				if(ele.getIndice().equals(indice)) {
					currentSession.setAttribute("indice", ele.getIndice());
					request.setAttribute("utente", ele.getUtente());
					request.setAttribute("numProd", numProd);
					request.setAttribute("tot", ele.getTot());
					Float pre = ele.getCosto();
					String s = String.valueOf(pre);
					lprocar.add(ele.getAutore());
					lprocar.add(ele.getNome());
					lprocar.add(s);
					lprocar.add(ele.getTipo());
					if(ele.getQuantità()==0) {
						lprocar.add("-");
					}
					else {
						lprocar.add(String.valueOf(ele.getQuantità()));
					}
				}
			
		}
			request.setAttribute("lprocar", lprocar);
			getServletContext().getRequestDispatcher(response.encodeURL("/EmissioneOrdine.jsp")).forward(request, response); //reindiriziamo alla view	
	}
		catch(SQLException e){
			Utility.print(e);
			request.setAttribute("error", e.getMessage());
			}
	}

}

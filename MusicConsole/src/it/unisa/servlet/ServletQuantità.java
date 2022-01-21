package it.unisa.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import it.unisa.model.Carrello;
import it.unisa.model.Magazzino;
import it.unisa.model.MagazzinoModelDS;
import it.unisa.utils.Utility;

@WebServlet("/ServletQuantità")
public class ServletQuantità extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		
		response.setContentType("text/html");//tipo di file
		
		MagazzinoModelDS model = new MagazzinoModelDS(ds);
		
		HttpSession currentSession = request.getSession();
		
		String operazione = request.getParameter("op");
		String c = request.getParameter("cod");
		int cod = Integer.parseInt(c);
		
		try {
			Collection<Magazzino> al = model.doRetrieveAll();
			for(Iterator<Magazzino> i = al.iterator();i.hasNext();) {
				Magazzino bean = (Magazzino)i.next();
				if(bean.getCod()==cod) {
					if((operazione.equals("piu"))&&(bean.getQuantità()<200)) {
						model.doUpdate((bean.getQuantità()+1),cod);
					}
					else {
						currentSession.setAttribute("noP", true);
					}
					
				}
			}
			getServletContext().getRequestDispatcher("/ServletMagazzino").forward(request, response); //reindiriziamo alla view
			
		}
		catch(SQLException e){
			Utility.print(e);
			request.setAttribute("error", e.getMessage());
			}
	}

}

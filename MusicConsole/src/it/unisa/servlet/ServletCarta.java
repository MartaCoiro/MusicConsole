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
import Class.Carta;
import Class.Carrello;
import it.unisa.model.CartaModelDS;
import it.unisa.utils.Utility;


@WebServlet("/ServletCarta")
public class ServletCarta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		
		CartaModelDS model = new CartaModelDS(ds);
		
		HttpSession currentSession = request.getSession();
		String ut = (String)currentSession.getAttribute("acc");
		
		try {
			Collection<Carta> cart = model.doRetrieveAll();
			if(cart.size()>0) {
				for(Iterator<Carta> i = cart.iterator();i.hasNext();) {
					Carta ele = (Carta)i.next();
					if(ele.getUtente().equals(ut)) {
						currentSession.setAttribute("posCard", true);
						currentSession.setAttribute("cart", ele);
					}
					else {currentSession.setAttribute("posCard", false);}
				}
			}
			else {currentSession.setAttribute("posCard", false);}
			
			getServletContext().getRequestDispatcher("/checkout.jsp").forward(request, response);
	}catch(SQLException e){
		Utility.print(e);
		request.setAttribute("error", e.getMessage());
		}
	}
}

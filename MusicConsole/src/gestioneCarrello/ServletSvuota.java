package gestioneCarrello;

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

import gestioneAccount.AccountUtente;
import gestioneProdotti.PlaylistModelDS;
import it.unisa.utils.DBConnectionPool;
import it.unisa.utils.Utility;

@WebServlet("/ServletSvuota")
public class ServletSvuota extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		
		Connection ds=null;
		try {
			ds=DBConnectionPool.getConnection();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		CarrelloModelDS model = new CarrelloModelDS(ds);
		
		HttpSession currentSession = request.getSession();
		String ut = (String)currentSession.getAttribute("acc");
		
		ArrayList<String> ca = new ArrayList<String>();
		
		try {
			Collection<Carrello> el = model.RestXUtente(ut);//restituisce tutti i carrelli dell'utente in sessione
			for(Iterator<Carrello> i = el.iterator();i.hasNext();) {
				Carrello ele = (Carrello)i.next();
				 model.doDelete(ele.getCod());
			}
				Float tot=model.doSum(ut);
				currentSession.setAttribute("tot", tot);
				currentSession.setAttribute("carrello", ca);
				getServletContext().getRequestDispatcher("/Carrello.jsp").forward(request, response);
			
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

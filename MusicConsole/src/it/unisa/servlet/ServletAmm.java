package it.unisa.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import it.unisa.model.AmmModelDS;
import it.unisa.model.Amm;
import it.unisa.utils.Utility;


@WebServlet("/ServletAmm")
public class ServletAmm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		AmmModelDS model = new AmmModelDS(ds);
	
		response.setContentType("text/html");//tipo di file
		
		String name = request.getParameter("username");
		String p = request.getParameter("password");
	
		try {
			HttpSession currentSession = request.getSession();
			currentSession.setMaxInactiveInterval(60*60);
			
			Amm m =model.doRetrieveByKey(name,p); 
			String k=m.getUtente();
			
			if(k.equals("") ){
				request.setAttribute("presente", true);
				getServletContext().getRequestDispatcher("/amministratore.jsp").forward(request, response); //reindiriziamo alla view
				
			}else {
				if(m.getRuolo().equals("gestore ordini")) {
				currentSession.setAttribute("yes", false);
				System.out.println("Katia");
				request.setAttribute("ruolo", "ordini");
				getServletContext().getRequestDispatcher("/homeAmm.jsp").forward(request, response); //reindiriziamo alla view	
			} else if(m.getRuolo().equals("gestore catalogo")) {
				currentSession.setAttribute("yes", false);
				request.setAttribute("ruolo", "catalogo");
				getServletContext().getRequestDispatcher("/homeAmm.jsp").forward(request, response); //reindiriziamo alla view	
			}else if(m.getRuolo().equals("gestore magazzino")) {
				currentSession.setAttribute("yes", false);
				request.setAttribute("ruolo", "magazzino");
				getServletContext().getRequestDispatcher("/homeAmm.jsp").forward(request, response); //reindiriziamo alla view	
			}
		}
	}
			
			 catch(SQLException e){
			Utility.print(e);
			request.setAttribute("error", e.getMessage());
			}
		
	}

}

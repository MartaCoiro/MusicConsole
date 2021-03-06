package gestioneAccount;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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

import it.unisa.utils.DBConnectionPool;
import it.unisa.utils.Utility;

 
@WebServlet("/ServletAmm")
public class ServletAmm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		Connection ds = null;
		try {
			ds = DBConnectionPool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		AmmModelDS model = new AmmModelDS(ds);
	
		
		
		String name = request.getParameter("username");
		String p = request.getParameter("password");
	
		//try {
			HttpSession currentSession = request.getSession();
			currentSession.setMaxInactiveInterval(60*60);
			
			if((name.equals(""))||(p.equals(""))){
				request.setAttribute("presente", true);
				request.getServletContext().getRequestDispatcher("/amministratore.jsp").forward(request, response); //reindiriziamo alla view
				return;
			}
			
			Amm m =model.doRetrieveByKey(name,p); 
			
			if(m==null) {
				request.setAttribute("presente", true);
				request.getServletContext().getRequestDispatcher("/amministratore.jsp").forward(request, response); //reindiriziamo alla view
				return;
			}
			
			
			if(m.getRuolo().equals("gestore ordini")) {
				currentSession.setAttribute("yes", false);
				request.setAttribute("ruolo", "ordini");
				request.getServletContext().getRequestDispatcher("/homeAmm.jsp").forward(request, response); //reindiriziamo alla view	
			} else if(m.getRuolo().equals("gestore catalogo")) {
				currentSession.setAttribute("yes", false);
				request.setAttribute("ruolo", "catalogo");
				request.getServletContext().getRequestDispatcher("/homeAmm.jsp").forward(request, response); //reindiriziamo alla view	
			}else if(m.getRuolo().equals("gestore magazzino")) {
				currentSession.setAttribute("yes", false);
				request.setAttribute("ruolo", "magazzino");
				request.getServletContext().getRequestDispatcher("/homeAmm.jsp").forward(request, response); //reindiriziamo alla view	
			}
		}
	//}
			
			/* catch(SQLException e){
			Utility.print(e);
			request.setAttribute("error", e.getMessage());
			}*/
		
	

}

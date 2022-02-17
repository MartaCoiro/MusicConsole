package gestioneProdotti;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import it.unisa.utils.DBConnectionPool;
import it.unisa.utils.Utility;

@WebServlet("/ServletMagazzino")
public class ServletMagazzino extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		
		Connection ds=null;
		try {
			ds=DBConnectionPool.getConnection();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		response.setContentType("text/html");//tipo di file
		
		MagazzinoModelDS model = new MagazzinoModelDS(ds);
		
		HttpSession currentSession = request.getSession();
		
		try {
			Collection<Magazzino> al = model.doRetrieveAll();
			
			currentSession.setAttribute("lprod", al);
			getServletContext().getRequestDispatcher("/Quantità.jsp").forward(request, response); //reindiriziamo alla view
		}
		catch(SQLException e){
			Utility.print(e);
			request.setAttribute("error", e.getMessage());
			}
	}

}

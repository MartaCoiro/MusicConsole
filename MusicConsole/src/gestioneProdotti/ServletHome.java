package gestioneProdotti;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import it.unisa.utils.DBConnectionPool;
import it.unisa.utils.Utility;

@WebServlet("/ServletHome")
public class ServletHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	
		//DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
	
		Connection ds=null;
		try {
			ds=DBConnectionPool.getConnection();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		BraniModelDS model = new BraniModelDS(ds);
		
		response.setContentType("text/html");//tipo di file
		
		try {
			Collection<Brano> br = model.doRetrieveAll();
			request.setAttribute("bb", br);
			getServletContext().getRequestDispatcher("/home.jsp").forward(request, response); //reindiriziamo alla view	
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

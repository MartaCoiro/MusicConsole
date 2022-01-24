package it.unisa.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import it.unisa.model.AccountModelDS;
import it.unisa.utils.Utility;
import it.unisa.model.AccountUtente;
import it.unisa.model.Profilo;
import it.unisa.model.ProfiloModelDS;



@WebServlet("/ServletMyAccount")
public class ServletMyAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		
		ProfiloModelDS model1 = new ProfiloModelDS(ds);
		AccountModelDS model = new AccountModelDS(ds);
		
		response.setContentType("text/html");//tipo di file
		
		String ele1 = request.getParameter("nome");
		String ele2 = request.getParameter("cognome");
		String ele3 = request.getParameter("email");
		String ele4 = request.getParameter("telefono");
		String ele5 = request.getParameter("indirizzo");
		String ele6 = request.getParameter("citta");
		String ele8 = request.getParameter("password");
		
		try {
			HttpSession currentSession = request.getSession();
			Profilo p = (Profilo) currentSession.getAttribute("p");
			Profilo profil = model1.doRetrieveByKey(p.getUsername());
			
			Collection<AccountUtente> cpr = model.doRetrieveAll();
			AccountUtente pr = model.doRetrieveByKey(p.getUsername(),p.getPassword());
			
			if(!ele1.equals(p.getNome())){
				p.setNome(ele1);
				model1.doUpdate("nome", ele1, profil.getUsername());
			}
			if(!ele2.equals(p.getCognome())){
				p.setCognome(ele2);
				model1.doUpdate("cognome", ele2, profil.getUsername());
			}
			if(!ele3.equals(p.getEmail())){
				p.setEmail(ele3);
				model1.doUpdate("email", ele3, profil.getUsername());
			}
			if(!ele4.equals(p.getTelefono())){
				p.setTelefono(ele4);
				model1.doUpdate("telefono", ele4, profil.getUsername());
			}
			if(!ele5.equals(p.getIndirizzo())){
				p.setIndirizzo(ele5);
				model1.doUpdate("indirizzo", ele5, profil.getUsername());
			}
			if(!ele6.equals(p.getCitta())){
				p.setCitta(ele6);
				model1.doUpdate("città", ele6, profil.getUsername());
			}
			
			if(!ele8.equals(p.getPassword())){
				p.setPassword(ele8);
				model1.doUpdate("password", ele8, profil.getUsername());
			}
			
			if(!pr.getPassword().equals(ele8)){
				model.doUpdate("password", ele8, pr.getNickname());
			}
		
			getServletContext().getRequestDispatcher("/MyAccount.jsp").forward(request, response); //reindiriziamo alla view		
	}
		catch(SQLException e){
			Utility.print(e);
			request.setAttribute("error", e.getMessage());
			}
		
	}

}

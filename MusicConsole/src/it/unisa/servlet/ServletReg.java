package it.unisa.servlet;

import it.unisa.servlet.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import it.unisa.model.Profilo;
import it.unisa.model.ProfiloModelDS;
import it.unisa.model.AccountModelDS;
import it.unisa.utils.Utility;
import it.unisa.model.AccountUtente;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

//SERVLET

@WebServlet("/ServletReg")
public class ServletReg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		
		AccountModelDS model = new AccountModelDS(ds);
		ProfiloModelDS model1 = new ProfiloModelDS(ds);
		
		response.setContentType("text/html");//tipo di file
		
		 SecretKeySpec key;
		 String encryptedPassword;
		 
		//prendo i valori passati
		String nome= request.getParameter("nome");
		String cognome= request.getParameter("cognome");
		String citta= request.getParameter("citta'");
		String indirizzo= request.getParameter("indirizzo");
		String telefono= request.getParameter("telefono");
		String email= request.getParameter("email");
		
		String name = request.getParameter("nickname");//prendo i valori passati
		String p = request.getParameter("password");//prendo i valori passati
	
		AccountUtente account = new AccountUtente();
		Profilo pro = new Profilo();
		ArrayList<Profilo> profil = new ArrayList<Profilo>();
		//li inseriamo in account
		
		account.setNickname(name);
		
		
		byte[] salt = new String("12345678").getBytes();
        int iterationCount = 40000;
        int keyLength = 128;
       
		try {
			key = Encryption.createSecretKey(p.toCharArray(), salt, iterationCount, keyLength);
			 String originalPassword = p;
		        System.out.println("Original password: " + originalPassword);
				encryptedPassword = Encryption.encrypt(originalPassword, key);
				account.setPassword(encryptedPassword);
				 System.out.println("Encrypted password: " + encryptedPassword);
				 String decryptedPassword;
				 decryptedPassword = Encryption.decrypt(encryptedPassword, key);
				 System.out.println("Decrypted password: " + decryptedPassword);
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidKeySpecException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 //li inseriamo nel profilo
		pro.setNome(nome);
		pro.setCognome(cognome);
		pro.setCitta(citta);
		pro.setIndirizzo(indirizzo);
		pro.setTelefono(telefono);
		pro.setEmail(email);
		pro.setUsername(name);
		pro.setPassword(p);
		
	try {
			//recupero la sessione corrente
		HttpSession oldSession = request.getSession(false);//se non c'è non crearla
		if(oldSession != null) {
			oldSession.invalidate();//la invalido
		}
		//creo una nuova sessione
		HttpSession currentSession = request.getSession();
		currentSession.setMaxInactiveInterval(60*60);
		currentSession.setAttribute("p", pro);
		currentSession.setAttribute("nome", name);
			/*request.setAttribute("p", profil);*/
			
			
			AccountUtente m =model.doRetrieveByKey(name,p); 
			String k=m.getNickname();
				boolean val = model.doSave(account);//controlla se quell'utente è presente o meno, se non presente entra
				if(val==true) {
				model1.doSave(pro);
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response); //reindiriziamo alla view		
				}
			}
			 catch(SQLException e){ //se invece l'utente è presente
			Utility.print(e);
			request.setAttribute("presente", true);
			getServletContext().getRequestDispatcher("/registrazione.jsp").forward(request, response); //reindiriziamo alla view
			request.setAttribute("error", e.getMessage());
			}
		}
}
		
	
		

			

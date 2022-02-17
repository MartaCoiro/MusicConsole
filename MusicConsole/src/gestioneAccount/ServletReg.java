package gestioneAccount;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
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

import it.unisa.utils.DBConnectionPool;
import it.unisa.utils.PasswordHasher;
import it.unisa.utils.Utility;
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
			throws ServletException, IOException{
		
		//DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		Connection ds = null;
		try {
			ds = DBConnectionPool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		AccountModelDS model = new AccountModelDS(ds);
		ProfiloModelDS model1 = new ProfiloModelDS(ds);
		
		response.setContentType("text/html");//tipo di file
		  
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
		//Profilo pro = new Profilo(nome,cognome,citta,indirizzo,telefono,email,name,p);
		ArrayList<Profilo> profil = new ArrayList<Profilo>();
		//li inseriamo in account
		
		account.setNickname(name);
       
		//try {
			//criptiamo la password
				encryptedPassword = PasswordHasher.scramble(p);
				account.setPassword(encryptedPassword);
				 
		//}
		
		 //account = new AccountUtente(name,encryptedPassword);

		 //li inseriamo nel profilo
		pro.setNome(nome);
		pro.setCognome(cognome);
		pro.setCitta(citta);
		pro.setIndirizzo(indirizzo);
		pro.setTelefono(telefono);
		pro.setEmail(email);
		pro.setUsername(name);
		pro.setPassword(p);
		
	//try {
		
		/*
		//criptiamo la password
		encryptedPassword = PasswordHasher.scramble(p);
		//account.setPassword(encryptedPassword);
		account = new AccountUtente(name,encryptedPassword);
*/
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
			
			if(model.doRetrieveByKey(name,encryptedPassword)==null) {
				model.doSave(account);//controlla se quell'utente è presente o meno, se non presente entra
				model1.doSave(pro);
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response); //reindiriziamo alla view		
				return;
			}
				else {
					request.setAttribute("presente", true);
					getServletContext().getRequestDispatcher("/registrazione.jsp").forward(request, response); //reindiriziamo alla view
					return;	
				}
			
	//}
		}
}
		
	
		

			

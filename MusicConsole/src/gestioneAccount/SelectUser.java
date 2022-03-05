package gestioneAccount;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.mysql.cj.util.Base64Decoder;

import it.unisa.utils.DBConnectionPool;
import it.unisa.utils.PasswordHasher;
import it.unisa.utils.Utility;
import gestioneCarrello.Carrello;
import gestioneCarrello.CarrelloModelDS;
import gestioneProdotti.Album;
import gestioneProdotti.AlbumModelDS;
import gestioneProdotti.BraniModelDS;
import gestioneProdotti.Brano;
import gestioneProdotti.Playlist;
import gestioneProdotti.PlaylistModelDS;
import gestioneProdotti.Podcast;
import gestioneProdotti.PodcastModelDS;



//SERVLET

@WebServlet("/select")
public class SelectUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
        
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();//stampa solo caratteri
		
		//DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		Connection ds = null;
		try {
			ds = DBConnectionPool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ProfiloModelDS model1 = new ProfiloModelDS(ds);
		AccountModelDS model = new AccountModelDS(ds);
		PlaylistModelDS model2 = new PlaylistModelDS(ds);
		CarrelloModelDS model3 = new CarrelloModelDS(ds);
		AlbumModelDS model4 = new AlbumModelDS(ds);
		BraniModelDS model5 = new BraniModelDS(ds);
		PodcastModelDS model6 = new PodcastModelDS(ds);
		
		
		
		AccountUtente m;
		
		HttpSession currentSession = request.getSession();
		
		String name = request.getParameter("nickname");
		String pas = request.getParameter("password");
		
		ArrayList<String> lplay = new ArrayList<String>();
		ArrayList<Carrello> car = new ArrayList<Carrello>();
		ArrayList<Album> prA = new ArrayList<Album>();
		ArrayList<Brano> prB = new ArrayList<Brano>();
		ArrayList<Podcast> prP = new ArrayList<Podcast>();
		
		try {
			
			//Profilo pr = model1.doRetrieveByKey(name);
			
		if((name.length()<1)||(pas.length()<1) ){
			request.setAttribute("presente", true);
			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response); //reindiriziamo alla view
			return;
			}
		else {
			Collection<AccountUtente> au =  model.doRetrieveAll();
			//System.out.println("hey");
			for(Iterator<AccountUtente> aa = au.iterator();aa.hasNext();) {
				 AccountUtente el = (AccountUtente)aa.next();
				//criptiamo la password inserita per controllare se è giusta
			   String cryptedPasI = PasswordHasher.scramble(pas);
			       
			   String pass = el.getPassword();//STRINGA RECUPERATA DAL DB
					
			   if((pass.equals(cryptedPasI))&&(el.getNickname().equals(name))) {
					//System.out.println("ok");
					Profilo pr = model1.doRetrieveByKey(name);

								 currentSession.setMaxInactiveInterval(60*60);
								 currentSession.setAttribute("acc", name);
								 currentSession.setAttribute("p", pr);
				
								 //recupero lista playlist
								 Collection<Playlist> p = model2.doRetrieveAll();
								 for(Iterator<Playlist> i = p.iterator();i.hasNext();) {
									 Playlist ele = (Playlist)i.next();
									 if(ele.getNomeUtente().equals(name)) {
										 if(!lplay.contains(ele.getNome())) {
											 lplay.add(ele.getNome());
										 }
						
									 }
								 }
				
								 //recupero carrello
								 Collection<Carrello> c = model3.doRetrieveAll();
								 if(c==null) {
									 request.setAttribute("vuoto", true);
								 }
								 for(Iterator<Carrello> i = c.iterator();i.hasNext();) {
									 Carrello ele = (Carrello)i.next();
									 if(ele.getUtente().equals(name)) {
										 car.add(ele);
									 }
								 }
				
								 //recupero prodotti
				
								 Collection<Album> a = model4.doRetrieveAll();
								 for(Iterator<Album> i = a.iterator(); i.hasNext();) {
									 Album al = (Album)i.next();
									 if(al.getDat()!=null) {
										 prA.add(al);
									 }
								 }
				
								 Collection<Brano> b = model5.doRetrieveAll();
								 for(Iterator<Brano> i = b.iterator(); i.hasNext();) {
									 Brano al = (Brano)i.next();
									 if(al.getDat()!=null) {
										 prB.add(al);
									 }
								 }
				
								 Collection<Podcast> po = model6.doRetrieveAll();
								 for(Iterator<Podcast> i = po.iterator(); i.hasNext();) {
									 Podcast al = (Podcast)i.next();
									 if(al.getDat()!=null) {
										 prP.add(al);
									 }
								 }
								 
								 currentSession.setAttribute("prA", prA);
									currentSession.setAttribute("prB", prB);
									currentSession.setAttribute("prP", prP);
									currentSession.setAttribute("conf", false);
									currentSession.setAttribute("carrello", car);
									currentSession.setAttribute("namep", lplay);
									
									getServletContext().getRequestDispatcher("/home.jsp").forward(request, response); //reindiriziamo alla view	
									return;
							 }
						 }
			 
				
					 request.setAttribute("presente", true);
						getServletContext().getRequestDispatcher("/login.jsp").forward(request, response); //reindiriziamo alla view
						return; 
				
			 }
		}		
			 
				/*currentSession.setAttribute("prA", prA);
				currentSession.setAttribute("prB", prB);
				currentSession.setAttribute("prP", prP);
				currentSession.setAttribute("conf", false);
				currentSession.setAttribute("carrello", car);
				currentSession.setAttribute("namep", lplay);
				
				getServletContext().getRequestDispatcher("/home.jsp").forward(request, response); //reindiriziamo alla view	
			*/
		
	
			
			 catch(SQLException e){
			Utility.print(e);
			request.setAttribute("error", e.getMessage());
			}  catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
	}
}
		
	
		

			


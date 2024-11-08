package Test.Servlet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import gestioneProdotti.Playlist;
import gestioneProdotti.PlaylistModelDS;
import gestioneProdotti.ServletPlaylist;
import it.unisa.utils.DBConnectionPool;

public class ServletPlaylistTest extends Mockito {
	
	Connection db;
	private PlaylistModelDS daoP;
	Playlist p1,p2;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	ArrayList<String> namep = new ArrayList<String>();
	
	 @InjectMocks
	    private ServletPlaylist servlet;
	 
	 @BeforeEach
	    void setUp() throws Exception {
		 try {
	    		db = DBConnectionPool.getConnection();
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    	}
		  daoP = new PlaylistModelDS(db);
		  p1 = creaPlaylist(50,"k.buonocore","Music"," ",0," ");//presente nel db
		  p2 = creaPlaylist(51,"k.buonocore","Music","Blu Celeste",0,"Blanco");//non presente nel db
		  daoP.doSave(p1);
		  daoP.doSave(p2);
		  namep.add("Music");
	      request = new MockHttpServletRequest();
	      response = new MockHttpServletResponse(); 
	      MockitoAnnotations.initMocks(this);
	    }
	 
	 @AfterEach
	    void tearDown() throws Exception {
		 daoP.doDelete(p2.getNomeBrano(),p2.getNomeArtista(),p2.getNome(),p2.getNomeUtente());
		 daoP.doDeletep(p1.getId());
		 namep.remove(0);
	     request=null;
	     response=null;
	    }
	 
	 @Test
	 public void TestAggBranogi�EsistenteinPlaylist() throws ServletException, IOException {
		 request.setParameter("plist", "Music");
		 HttpSession currentsession = request.getSession();
		 currentsession.setAttribute("acc", "k.buonocore");
		 request.setQueryString("nomeb=Blu Celeste&nomea=Blanco&plist=Music");
		 currentsession.setAttribute("namep",namep);
		 servlet.doGet(request, response);
		 assertEquals(true,request.getAttribute("no"));
	 }
	 
	 @Test
	 public void TestAggBranoinPlaylist() throws ServletException, IOException, SQLException {
		 request.setParameter("plist", "Music");
		 HttpSession currentsession = request.getSession();
		 currentsession.setAttribute("acc", "k.buonocore");
		 request.setQueryString("nomeb=Taxi Driven&nomea=Rkomi&plist=Music");
		 currentsession.setAttribute("namep",namep);
		 servlet.doGet(request, response);
		 assertFalse(Boolean.getBoolean(String.valueOf(request.getAttribute("no"))));
		 daoP.doDelete("Taxi Driven","Rkomi","Music","k.buonocore");
	 }
	 
	 private Playlist creaPlaylist(int cod,String nomeUt,String nome,String nomeBrano,int numBrani,String nomeArtista) {
			Playlist x = new Playlist();
			x.setId(cod);
			x.setNomeUtente(nomeUt);
			x.setNome(nome);
			x.setNomeBrano(nomeBrano);
			x.setNumBrani(numBrani);
			x.setNomeArtista(nomeArtista);
			return x;
		}
}

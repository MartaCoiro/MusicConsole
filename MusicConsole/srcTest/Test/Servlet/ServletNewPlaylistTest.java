package Test.Servlet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

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
import gestioneProdotti.ServletNewPlaylist;
import it.unisa.utils.DBConnectionPool;

public class ServletNewPlaylistTest extends Mockito {
	Connection db;
	private PlaylistModelDS daoP;
	Playlist p1;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	  
	  @InjectMocks
	    private ServletNewPlaylist servlet;
	  
	  @BeforeEach
	    void setUp() throws Exception {
		  try {
	    		db = DBConnectionPool.getConnection();
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    	}
		  daoP = new PlaylistModelDS(db);
		  p1 = creaPlaylist(50,"k.buonocore","Music"," ",0," ");//presente nel db
		  daoP.doSave(p1);
		  request = new MockHttpServletRequest();
	      response = new MockHttpServletResponse(); 
	      MockitoAnnotations.initMocks(this);
	    }
	  
	  @AfterEach
	    void tearDown() throws Exception {
		  	daoP.doDeletep(p1.getId());
	        request=null;
	        response=null;
	    }

	  @Test
	  public void TestCreazionePlaylistConCampoVuoto() throws ServletException, IOException{
		  HttpSession currentsession = request.getSession();
		  request.setParameter("nome", "");
	      currentsession.setAttribute("acc", "k.buonocore");
	      servlet.doGet(request, response);
	      assertFalse(Boolean.getBoolean(String.valueOf(request.getAttribute("si"))));
	  }
	  
	  @Test
	  public void TestCreazionePlaylistgi‡Esistente() throws ServletException, IOException{
		  HttpSession currentsession = request.getSession();
		  request.setParameter("nome", "Music");
	      currentsession.setAttribute("acc", "k.buonocore");
	      servlet.doGet(request, response);
	      assertEquals(true,request.getAttribute("si"));
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

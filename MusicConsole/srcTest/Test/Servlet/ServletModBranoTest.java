package Test.Servlet;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

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

import gestioneProdotti.BraniModelDS;
import gestioneProdotti.Brano;
import gestioneProdotti.ServletModBrano;
import it.unisa.utils.DBConnectionPool;


public class ServletModBranoTest extends Mockito{

	Connection db;
	Brano b1;
	private BraniModelDS daoB;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	Date data = new Date();
	java.sql.Date dat = new java.sql.Date(data.getTime());
	
	
	@InjectMocks
    private ServletModBrano servlet;
  
	@BeforeEach
    void setUp() throws Exception {
		try {
			   db = DBConnectionPool.getConnection();
		   }catch(SQLException e){
			   e.printStackTrace();
		   }
		    daoB = new BraniModelDS(db);
		    b1 = creaBrano(15,"Blu Celeste",2.34f,"Blanco","img15.png","pop","Brano","bluCeleste.mp3",5.2f,"Bellissimo");//presente nel db
		    b1.setDat(dat);
		    daoB.doSave(b1);
		    request = new MockHttpServletRequest();
		    response = new MockHttpServletResponse(); 
		    MockitoAnnotations.initMocks(this);
    }
	
	@AfterEach
    void tearDown() throws Exception {
		daoB.doDelete(b1.getCodice());
        request=null;
        response=null;
    }
	
	@Test
	public void TestModificaNomeBranoCampoVuoto() throws  ServletException, IOException {
		request.setParameter("nome","");
		request.setParameter("codice","15");
		request.setParameter("immagine","img15.png");
		request.setParameter("artista","Blanco");
		request.setParameter("tipo","Brano");
		request.setParameter("genere","pop");
		request.setParameter("descrizione","Interessante");
		request.setParameter("prezzo","0.9");
		request.setParameter("suono","bluCeleste.mp3");
		HttpSession currentsession = request.getSession();
        currentsession.setAttribute("bra", b1);
        servlet.doPost(request, response);
		assertEquals(true,request.getAttribute("pre"));
	}
	
	@Test
	public void TestModificaPrezzoBranoCampoVuoto() throws  ServletException, IOException {
		request.setParameter("nome","Blu Celeste");
		request.setParameter("codice","15");
		request.setParameter("immagine","img15.png");
		request.setParameter("artista","Blanco");
		request.setParameter("tipo","Brano");
		request.setParameter("genere","pop");
		request.setParameter("descrizione","Interessante");
		request.setParameter("prezzo","");
		request.setParameter("suono","bluCeleste.mp3");
		HttpSession currentsession = request.getSession();
        currentsession.setAttribute("bra", b1);
        servlet.doPost(request, response);
		assertEquals(true,request.getAttribute("pre"));
	}
	
	@Test
	public void TestModificaArtistaBranoCampoVuoto() throws  ServletException, IOException {
		request.setParameter("nome","Blu Celeste");
		request.setParameter("codice","15");
		request.setParameter("immagine","img15.png");
		request.setParameter("artista","");
		request.setParameter("tipo","Brano");
		request.setParameter("genere","pop");
		request.setParameter("descrizione","Interessante");
		request.setParameter("prezzo","0.9");
		request.setParameter("suono","bluCeleste.mp3");
		HttpSession currentsession = request.getSession();
        currentsession.setAttribute("bra", b1);
        servlet.doPost(request, response);
		assertEquals(true,request.getAttribute("pre"));
	}
	
	@Test
	public void TestModificaBranodconGenerenonValido() throws  ServletException, IOException {
		request.setParameter("nome","Blu Celeste");
		request.setParameter("codice","15");
		request.setParameter("immagine","img15.png");
		request.setParameter("artista","Blanco");
		request.setParameter("tipo","Brano");
		request.setParameter("genere","bohh");
		request.setParameter("descrizione","Interessante");
		request.setParameter("prezzo","10.2");
		request.setParameter("suono","bluCeleste.mp3");
		HttpSession currentsession = request.getSession();
        currentsession.setAttribute("bra", b1);
        servlet.doPost(request, response);
		assertEquals(true,request.getAttribute("g"));
	}
	
	@Test
	public void TestModificaNomeBranoditipoAlbumCampoVuoto() throws  ServletException, IOException {
		request.setParameter("nome","");
		request.setParameter("codice","15");
		request.setParameter("immagine","img15.png");
		request.setParameter("artista","Blanco");
		request.setParameter("tipo","Album");
		request.setParameter("genere","pop");
		request.setParameter("descrizione","Interessante");
		request.setParameter("prezzo","0.9");
		request.setParameter("suono","bluCeleste.mp3");
		HttpSession currentsession = request.getSession();
        currentsession.setAttribute("bra", b1);
        servlet.doPost(request, response);
		assertEquals(true,request.getAttribute("pre"));
	}
	
	@Test
	public void TestModificaArtistaBranodiTipoAlbumCampoVuoto() throws  ServletException, IOException {
		request.setParameter("nome","Blu Celeste");
		request.setParameter("codice","15");
		request.setParameter("immagine","img15.png");
		request.setParameter("artista","");
		request.setParameter("tipo","Album");
		request.setParameter("genere","pop");
		request.setParameter("descrizione","Interessante");
		request.setParameter("prezzo","0.9");
		request.setParameter("suono","bluCeleste.mp3");
		HttpSession currentsession = request.getSession();
        currentsession.setAttribute("bra", b1);
        servlet.doPost(request, response);
		assertEquals(true,request.getAttribute("pre"));
	}
	
	@Test
	public void TestModificaCodiceBranodiTipoAlbumCampoVuoto() throws  ServletException, IOException {
		request.setParameter("nome","Blu Celeste");
		request.setParameter("codice","");
		request.setParameter("immagine","img15.png");
		request.setParameter("artista","Blanco");
		request.setParameter("tipo","Album");
		request.setParameter("genere","pop");
		request.setParameter("descrizione","Interessante");
		request.setParameter("prezzo","0.9");
		request.setParameter("suono","bluCeleste.mp3");
		HttpSession currentsession = request.getSession();
        currentsession.setAttribute("bra", b1);
        servlet.doPost(request, response);
		assertEquals(true,request.getAttribute("pre"));
	}
	
	@Test
	public void TestModificaPrezzoBranodiTipoAlbumCampoVuoto() throws  ServletException, IOException {
		request.setParameter("nome","Blu Celeste");
		request.setParameter("codice","15");
		request.setParameter("immagine","img15.png");
		request.setParameter("artista","Blanco");
		request.setParameter("tipo","Album");
		request.setParameter("genere","pop");
		request.setParameter("descrizione","Interessante");
		request.setParameter("prezzoS","");
		request.setParameter("prezzoV","");
		request.setParameter("prezzoC","");
		request.setParameter("suono","bluCeleste.mp3");
		HttpSession currentsession = request.getSession();
        currentsession.setAttribute("bra", b1);
        servlet.doPost(request, response);
		assertEquals(true,request.getAttribute("pre"));
	}
	
	@Test
	public void TestModificaBranodiTipoAlbumConcodicegi‡Esistente() throws  ServletException, IOException {
		request.setParameter("nome","Blu Celeste");
		request.setParameter("codice","100");
		request.setParameter("immagine","img15.png");
		request.setParameter("artista","Blanco");
		request.setParameter("tipo","Album");
		request.setParameter("genere","pop");
		request.setParameter("descrizione","Interessante");
		request.setParameter("prezzoS","10.2");
		request.setParameter("prezzoV","6.5");
		request.setParameter("prezzoC","7.1");
		request.setParameter("suono","bluCeleste.mp3");
		HttpSession currentsession = request.getSession();
        currentsession.setAttribute("bra", b1);
        servlet.doPost(request, response);
		assertEquals(true,request.getAttribute("ercod"));
	}
	

	@Test
	public void TestModificaArtistaBranodiTipoPodcastCampoVuoto() throws  ServletException, IOException {
		request.setParameter("nome","Blu Celeste");
		request.setParameter("codice","15");
		request.setParameter("immagine","img15.png");
		request.setParameter("artista","");
		request.setParameter("tipo","Podcast");
		request.setParameter("genere","pop");
		request.setParameter("descrizione","Interessante");
		request.setParameter("prezzo","0.9");
		request.setParameter("suono","bluCeleste.mp3");
		HttpSession currentsession = request.getSession();
        currentsession.setAttribute("bra", b1);
        servlet.doPost(request, response);
		assertEquals(true,request.getAttribute("pre"));
	}
	
	@Test
	public void TestModificaNomeBranoditipoPodcastCampoVuoto() throws  ServletException, IOException {
		request.setParameter("nome","");
		request.setParameter("codice","15");
		request.setParameter("immagine","img15.png");
		request.setParameter("artista","Blanco");
		request.setParameter("tipo","Podcast");
		request.setParameter("genere","pop");
		request.setParameter("descrizione","Interessante");
		request.setParameter("prezzo","0.9");
		request.setParameter("suono","bluCeleste.mp3");
		HttpSession currentsession = request.getSession();
        currentsession.setAttribute("bra", b1);
        servlet.doPost(request, response);
		assertEquals(true,request.getAttribute("pre"));
	}
	
	@Test
	public void TestModificaPrezzoBranodiTipoPodcastCampoVuoto() throws  ServletException, IOException {
		request.setParameter("nome","Blu Celeste");
		request.setParameter("codice","15");
		request.setParameter("immagine","img15.png");
		request.setParameter("artista","Blanco");
		request.setParameter("tipo","Podcast");
		request.setParameter("genere","pop");
		request.setParameter("descrizione","Interessante");
		request.setParameter("prezzo","");
		request.setParameter("suono","bluCeleste.mp3");
		HttpSession currentsession = request.getSession();
        currentsession.setAttribute("bra", b1);
        servlet.doPost(request, response);
		assertEquals(true,request.getAttribute("pre"));
	}
	
	@Test
	public void TestModificaBranodiTipoPodcastConnomegi‡Esistente() throws  ServletException, IOException {
		request.setParameter("nome","audio racconti");
		request.setParameter("codice","15");
		request.setParameter("immagine","img15.png");
		request.setParameter("artista","Blanco");
		request.setParameter("tipo","Podcast");
		request.setParameter("genere","pop");
		request.setParameter("descrizione","Interessante");
		request.setParameter("prezzo","10.2");
		request.setParameter("suono","bluCeleste.mp3");
		HttpSession currentsession = request.getSession();
        currentsession.setAttribute("bra", b1);
        servlet.doPost(request, response);
		assertEquals(true,request.getAttribute("ercod1"));
	}
	
	 private Brano creaBrano(int cod,String nome,float durata,String artista,String img,String genere,String tipo,String suono,float prezzo,String descrizione) {
			Brano x = new Brano();
			x.setCodice(cod);
			x.setTitolo(nome);
			x.setDurata(durata);
			x.setImgBrano(img);
			x.setCantante(artista);
			x.setGenere(genere);
			x.setTipo(tipo);
			x.setSuono(suono);
			x.setPrezzo(prezzo);
			x.setDescrizione(descrizione);
			return x;
		}
}

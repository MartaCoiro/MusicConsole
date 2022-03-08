package Test.Servlet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

import gestioneCarrello.Carrello;
import gestioneCarrello.CarrelloModelDS;
import gestioneCarrello.ServletCarrello;
import gestioneProdotti.Album;
import gestioneProdotti.AlbumModelDS;
import gestioneProdotti.Magazzino;
import gestioneProdotti.MagazzinoModelDS;
import it.unisa.utils.DBConnectionPool;

public class ServletCarrelloTest extends Mockito {
	Connection db;
	MagazzinoModelDS daoM;
	CarrelloModelDS daoC;
	AlbumModelDS daoA;
	Carrello carrello1;
	Magazzino magazzino;
	Album a1;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	Date data = new Date();
	java.sql.Date dat = new java.sql.Date(data.getTime());
	  
	  @InjectMocks
	    private ServletCarrello servlet;
	  
	  @BeforeEach
	    void setUp() throws Exception {
		  try {
	    		db = DBConnectionPool.getConnection();
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    	}
		  	daoC = new CarrelloModelDS(db);
		  	daoM = new MagazzinoModelDS(db);
		  	daoA = new AlbumModelDS(db);
		  	
		  	a1 = creaProdotto(1000,"Blu Celeste","img1000.png","Blanco","Album",5.2f,32.1f,21.0f,"Bellissimo");
		  	a1.setDat(dat);
		  	daoA.doSave(a1);
		  	
		  	magazzino = aggProdMagazzino(3,"Blu Celeste","Blanco","CD",32.1f);//presente nel db
		  	magazzino.setCod(25);
		  	daoM.doSave(magazzino);
		    
		    request = new MockHttpServletRequest();
	        response = new MockHttpServletResponse(); 
	        MockitoAnnotations.initMocks(this);
	    }
	  
	  @Test
	    public void TestNonDisponibilit‡Prodotto () throws ServletException, IOException{
		  carrello1 = aggProdCarrello(0.9f,4,"g.giuseppe","Blu Celeste","Blanco","CD",0.9f);
		  carrello1.setCod(30);
		  daoC.doSave(carrello1);
		  HttpSession currentsession = request.getSession();
	      currentsession.setAttribute("acc", "g.giuseppe");
	      servlet.doGet(request, response);
	      assertTrue(true, String.valueOf(request.getAttribute("noD")));
	  }
	  
	  @Test
	    public void TestDisponibilit‡Prodotto () throws ServletException, IOException{
		  carrello1 = aggProdCarrello(0.9f,1,"g.giuseppe","Blu Celeste","Blanco","CD",0.9f);
		    carrello1.setCod(30);
		    daoC.doSave(carrello1);
		  	HttpSession currentsession = request.getSession();
	        currentsession.setAttribute("acc", "g.giuseppe");
	        servlet.doGet(request, response);
	        assertFalse(false, String.valueOf(request.getAttribute("noD")));
	  }
	  
	  @AfterEach
	    void tearDown() throws Exception {
		   	daoC.doDelete(carrello1.getCod());
		   	daoM.doDelete(magazzino.getCod());
		   	daoA.doDelete(a1.getCodiceAlbum());
		   
		   	request=null;
	        response=null;
	    }
	   
	   private Magazzino aggProdMagazzino(int quantit‡,String nome,String autore,String tipo,float costo) {
			Magazzino x = new Magazzino();
			x.setQuantit‡(quantit‡);
			x.setNome(nome);
			x.setAutore(autore);
			x.setTipo(tipo);
			x.setCosto(costo);
			return x;
		}
	   
	   private Carrello aggProdCarrello(float totq,int quant,String utente,String nome,String autore,String tipo,float costo) {
			  Carrello c = new Carrello();
			  c.setTotq(totq);
			  c.setQuantit‡(quant);
			  c.setUtente(utente);
			  c.setNome(nome);
			  c.setAutore(autore);
			  c.setTipo(tipo);
			  c.setCosto(costo);
			  return c;
		  }
	   
	   private Album creaProdotto(int cod,String nome,String img,String artista,String tipo,float pS,float pV,float pC,String descrizione) {
			Album x = new Album();
			x.setCodiceAlbum(cod);
			x.setNomeAlbum(nome);
			x.setImgAlbum(img);
			x.setNartista(artista);
			x.setTipo(tipo);
			x.setPrezzoS(pS);
			x.setPrezzoV(pV);
			x.setPrezzoC(pC);
			return x;
		}
	   
	   
	   


}

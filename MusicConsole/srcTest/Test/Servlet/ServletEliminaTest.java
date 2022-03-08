package Test.Servlet;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

import gestioneAccount.AccountModelDS;
import gestioneCarrello.Carrello;
import gestioneCarrello.CarrelloModelDS;
import gestioneCarrello.ServletElimina;
import it.unisa.utils.DBConnectionPool;


public class ServletEliminaTest extends Mockito {
	Connection db;
	CarrelloModelDS daoA;
	private MockHttpServletRequest request;
	  private MockHttpServletResponse response;
	  
	  
	  @InjectMocks
	    private ServletElimina servlet;
	  
	  @BeforeEach
	    void setUp() throws Exception {
		  try {
	    		db = DBConnectionPool.getConnection();
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    	}
		     daoA = new CarrelloModelDS(db);
		    Carrello carrello1 = aggProdCarrello(0.9f,0,"g.giuseppe","Collera","Nicola Siciliano","Streaming",0.9f);
		    carrello1.setCod(17);
		    Carrello carrello2 = aggProdCarrello(45.0f,1,"g.giuseppe","Gemelli","Ernia","CD",22.5f);
		    carrello2.setCod(18);
		    daoA.doSave(carrello1);
		    daoA.doSave(carrello2);
		    request = new MockHttpServletRequest();
	        response = new MockHttpServletResponse(); 
	        MockitoAnnotations.initMocks(this);
	    }
	  
	  @Test
	    public void TestCostoSpedizione () throws ServletException, IOException{
	        HttpSession currentsession = request.getSession();
	        currentsession.setAttribute("acc", "g.giuseppe");
	    	request.setParameter("codprod", "17");
	        servlet.doPost(request, response);
	        assertTrue(true, String.valueOf(request.getAttribute("sip")));

	  }
	  
	  @Test
	    public void TestCostoSpedizioneNonPresente () throws ServletException, IOException{
	        HttpSession currentsession = request.getSession();
	        currentsession.setAttribute("acc", "g.giuseppe");
	    	request.setParameter("codprod", "18");
	    	servlet.doPost(request, response);
	        assertNull(request.getAttribute("sip"));

	  }
	  
	  @AfterEach
	    void tearDown() throws Exception {
		 
	    	daoA.doDelete(17);
	        daoA.doDelete(18);
		  
	        request=null;
	        response=null;
	    }
	  
	  private Carrello aggProdCarrello(float totq,int quant,String utente,String nome,String autore,String tipo,float costo) {
		  Carrello c = new Carrello();
		  c.setTotq(totq);
		  c.setQuantità(quant);
		  c.setUtente(utente);
		  c.setNome(nome);
		  c.setAutore(autore);
		  c.setTipo(tipo);
		  c.setCosto(costo);
		  return c;
	  }
}

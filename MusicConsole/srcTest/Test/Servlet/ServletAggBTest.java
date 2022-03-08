package Test.Servlet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import gestioneProdotti.ServletAggB;



public class ServletAggBTest extends Mockito {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	
	@InjectMocks
    private ServletAggB servlet;
  
	@BeforeEach
    void setUp() throws Exception {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse(); 
        MockitoAnnotations.initMocks(this);
    }
	
	@AfterEach
    void tearDown() throws Exception {
        request=null;
        response=null;
    }
	
	@Test
	public void TestCodiceEsistenteNuovoProdotto() throws ServletException, IOException{
		request.setParameter("nome","Blu Celeste");
		request.setParameter("codice","1");
		request.setParameter("image","img15.png");
		request.setParameter("artista","Blanco");
		request.setParameter("tipo","Brano");
		request.setParameter("genere","pop");
		request.setParameter("descrizione","Interessante");
		request.setParameter("prezzo","0.9");
		request.setParameter("suono","BluCeleste.mp3");
		servlet.doPost(request, response);
		assertEquals(true,request.getAttribute("err"));
	}
	
	@Test
	public void TestTititoloEsistenteNuovoProdotto() throws ServletException, IOException{
		request.setParameter("nome","16 Marzo");
		request.setParameter("codice","15");
		request.setParameter("image","img15.png");
		request.setParameter("artista","Achille Lauro");
		request.setParameter("tipo","Brano");
		request.setParameter("genere","pop");
		request.setParameter("descrizione","Interessante");
		request.setParameter("prezzo","0.9");
		request.setParameter("suono","BluCeleste.mp3");
		servlet.doPost(request, response);
		assertEquals(true,request.getAttribute("berr"));
	}
	
	@Test
	public void TestTipoNonCorrettoNuovoProdotto() throws ServletException, IOException{
		request.setParameter("nome","Blu Celeste");
		request.setParameter("codice","15");
		request.setParameter("image","img15.png");
		request.setParameter("artista","Blanco");
		request.setParameter("tipo","Album");
		request.setParameter("genere","pop");
		request.setParameter("descrizione","Interessante");
		request.setParameter("prezzo","0.9");
		request.setParameter("suono","BluCeleste.mp3");
		servlet.doPost(request, response);
		assertEquals(true,request.getAttribute("sb"));
	}
}

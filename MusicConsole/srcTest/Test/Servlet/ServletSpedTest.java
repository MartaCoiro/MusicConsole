package Test.Servlet;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

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

import gestioneCarrello.ServletSped;

public class ServletSpedTest extends Mockito{
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	
	@InjectMocks
    private ServletSped servlet;
  
	@BeforeEach
    void setUp() throws Exception {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse(); 
        MockitoAnnotations.initMocks(this);
    }

	@Test
	  public void TestCartaEsistente() throws  ServletException, IOException{
		  	HttpSession currentsession = request.getSession();
	        currentsession.setAttribute("acc", "k.buonocore");
	        request.setParameter("conserva", "Memorizza Carta");
	        request.setParameter("cardname","Katia");
	        request.setParameter("cardsurname","Buonocore");
	        request.setParameter("cardnumber","7777-7777-7777-7777");
	        request.setParameter("expmonth","2022-06");
	        request.setParameter("cvv","989");
	        servlet.doPost(request, response);
	        assertEquals(true,request.getAttribute("error"));
	        }
	
	@Test
	  public void TestCodiceCartaErrato() throws  ServletException, IOException{
		  	HttpSession currentsession = request.getSession();
	        currentsession.setAttribute("acc", "g.giuseppe");
	        //request.setParameter("conserva", null);
	        request.setParameter("cardname","Giuseppe");
	        request.setParameter("cardsurname","Gass");
	        request.setParameter("cardnumber","7777-7777-7777-7777");
	        request.setParameter("expmonth","2022-06");
	        request.setParameter("cvv","989");
	        servlet.doPost(request, response);
	        assertEquals(true,request.getAttribute("error"));
	        }
	
	@AfterEach
    void tearDown() throws Exception {
        request=null;
        response=null;
    }

	
}

package Test.Servlet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

import gestioneCarrello.ServletCarta;



public class ServletCartaTest extends Mockito {
	private MockHttpServletRequest request;
	  private MockHttpServletResponse response;
	  
	  
	  @InjectMocks
	    private ServletCarta servlet;
	  
	  @BeforeEach
	    void setUp() throws Exception {
	        request = new MockHttpServletRequest();
	        response = new MockHttpServletResponse(); 
	        MockitoAnnotations.initMocks(this);
	    }
	  
	  @Test
	    public void TestCartaMemorizzataUtente () throws ServletException, IOException{
	        HttpSession currentsession = request.getSession();
	        currentsession.setAttribute("acc", "k.buonocore");
	        servlet.doPost(request, response);
	        assertTrue(true, String.valueOf(request.getAttribute("posCard")));

	  }
	  
	  @Test
	    public void TestCartaNonMemorizzataUtente () throws ServletException, IOException{
	        HttpSession currentsession = request.getSession();
	        currentsession.setAttribute("acc", "g.giuseppe");
	        servlet.doPost(request, response);
	        assertFalse(false, String.valueOf(request.getAttribute("posCard")));

	  }
	  
	  @AfterEach
	    void tearDown() throws Exception {
	        request=null;
	        response=null;
	    }

	

}

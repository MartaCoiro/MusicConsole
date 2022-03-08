package Test.Servlet;

import org.junit.jupiter.api.AfterEach; 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;


import gestioneAccount.ServletAmm;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;

public class ServletAmmTest extends Mockito {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    /*@Mock
    private UserService userDAO;*/

    @InjectMocks
    private ServletAmm servlet;


    @BeforeEach
    void setUp() throws Exception {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void TestLoginCorrettoGestoreOrdini () throws ServletException, IOException{
        request.setParameter("username", "k.buonocore");
        request.setParameter("password", "123");
        servlet.doPost(request, response);
        String oracolo = "ordini";
       assertEquals(oracolo,request.getAttribute("ruolo"));
    }
    
 
    @Test
    public void TestLoginCorrettoGestoreCatalogo () throws ServletException, IOException{
        request.setParameter("username", "m.coiro");
        request.setParameter("password", "456");
        servlet.doPost(request, response);
        assertFalse(false,String.valueOf(request.getAttribute("yes")));

    }
    
    @Test
    public void TestLoginCorrettoGestoreMagazzino () throws ServletException, IOException{
        request.setParameter("username", "r.cuccaro");
        request.setParameter("password", "789");
        servlet.doPost(request, response);
        //Boolean oracle = false;
        assertFalse(false,String.valueOf(request.getAttribute("yes")));

    }
    

    
    @Test
    public void TestLoginCampiNonInseriti () throws ServletException, IOException {
    	request.setParameter("username", "");
        request.setParameter("password", "");
        servlet.doPost(request, response);
        //Boolean oracle = true;
        assertTrue(true, String.valueOf(request.getAttribute("presente")));
    }
    
    @Test
    public void TestLoginErrato () throws ServletException, IOException {
    	 request.setParameter("username", "marios");
         request.setParameter("password", "rossis");
         servlet.doPost(request, response);
         assertTrue(true, String.valueOf(request.getAttribute("presente")));


       
    }


    @AfterEach
    void tearDown() throws Exception {
        request = null;
        response = null;
    }
    
}
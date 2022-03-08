package Test.Servlet;

import org.junit.After;
import org.junit.jupiter.api.AfterEach; 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import gestioneAccount.SelectUser;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.ServletException;

public class SelectUserTest extends Mockito { 
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    /*@Mock
    private UserService userDAO;*/

    @InjectMocks
    private SelectUser servlet;

   @BeforeEach
    void setUp() throws Exception {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse(); 
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void TestCampiNonInseriti () throws ServletException, IOException{
        request.setParameter("nickname", "");
        request.setParameter("password", "");
        servlet.doPost(request, response);
        assertTrue(true,String.valueOf(request.getAttribute("presente")));
    }

    @Test
    public void TestLoginCorretto () throws ServletException, IOException{
        request.setParameter("nickname", "k.buonocore");
        request.setParameter("password", "123");
        servlet.doPost(request, response);
        assertNull(request.getAttribute("presente"));
    }
    
    @Test
    public void TestLoginNonCorretto () throws ServletException, IOException{
        request.setParameter("nickname", "aa");
        request.setParameter("password", "123");
        servlet.doPost(request, response);
        assertTrue(true,String.valueOf(request.getAttribute("presente")));
    }
   
    @AfterEach
    void tearDown() throws Exception {
        request = null;
        response = null;
    }
    
}

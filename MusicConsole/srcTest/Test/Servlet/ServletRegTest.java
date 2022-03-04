package Test.Servlet;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import gestioneAccount.AccountModelDS;
import gestioneAccount.ServletReg;
import it.unisa.utils.DBConnectionPool;



public class ServletRegTest extends Mockito {
	Connection db;
	
	private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    
   
    
    @InjectMocks
    private ServletReg servlet;
    
    
    @BeforeEach
    void setUp() throws Exception {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        MockitoAnnotations.initMocks(this);
    }
    
   
    
    @Test
    public void TestNuovaRegistrazione() throws ServletException, IOException {
    	request.setParameter("nome", "Maria");
        request.setParameter("cognome", "Rossi");
        request.setParameter("citta'", "Firenze");
        request.setParameter("indirizzo", "Via dei palazzi");
        request.setParameter("telefono", "1234567892");
        request.setParameter("email", "mariarossi@gmail.com");
        request.setParameter("nickname", "m.rossi");
        request.setParameter("password", "maria");
        servlet.doPost(request, response);
        assertNull(request.getAttribute("presente"));

    }
    
    
    @Test
    public void TestRegistrazioneUsernameEsistente() throws ServletException, IOException {
    	request.setParameter("nome", "Paolo");
        request.setParameter("cognome", "Bianchi");
        request.setParameter("citta'", "Napoli");
        request.setParameter("indirizzo", "Via dei fiori");
        request.setParameter("telefono", "1234567890");
        request.setParameter("email", "paolobianchi@gmail.com");
        request.setParameter("nickname", "p.bianchi");
        request.setParameter("password", "paolo");
        servlet.doPost(request, response);
        assertTrue(true, String.valueOf(request.getAttribute("presente")));

    }
    
    
    
    
    @AfterEach
    void tearDown() throws Exception {
    	try {
    		db = DBConnectionPool.getConnection();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
        
        AccountModelDS daoA = new AccountModelDS(db);
        daoA.doDelete("m.rossi");
 
        
        
        request=null;
        response=null;
    }
}
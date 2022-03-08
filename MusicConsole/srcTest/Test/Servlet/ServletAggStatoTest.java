package Test.Servlet;
import org.checkerframework.checker.units.qual.Current;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import gestioneAccount.SelectUser;
import gestioneAcquisti.ServletAggStato;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


class ServletAggStatoTest extends Mockito {
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;
	  
	  
	  @InjectMocks
	    private ServletAggStato servlet;


	  @BeforeEach
	    void setUp() throws Exception {
	        request = new MockHttpServletRequest();
	        response = new MockHttpServletResponse(); 
	        MockitoAnnotations.initMocks(this);
	    }
	  
	  @Test
	    public void TestOrdineSpedito () throws ServletException, IOException{
	        HttpSession currentsession = request.getSession();
	        currentsession.setAttribute("indice", 2);
	        servlet.doPost(request, response);
	        assertEquals("true",request.getAttribute("conf"));
	  }
	  
	  @AfterEach
	    void tearDown() throws Exception {
	        request=null;
	        response=null;
	    }

    }
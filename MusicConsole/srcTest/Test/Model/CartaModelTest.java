package Test.Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;

import gestioneAccount.AccountModelDS;
import gestioneAccount.AccountUtente;
import gestioneCarrello.Carta;
import gestioneCarrello.CartaModelDS;
import it.unisa.utils.DBConnectionPool;

public class CartaModelTest {

	private AccountModelDS daoU;
	private CartaModelDS daoC;
	private static IDatabaseTester t;
	Connection db;
	AccountUtente ut1,ut2, ut3;
	Carta c1,c2;
	Date data = new Date();
	java.sql.Date dat = new java.sql.Date(data.getTime());

	@BeforeAll
	   public static void setUpAll() throws ClassNotFoundException {
		   t = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
				   "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'", "sa","");
		   t.setSetUpOperation(DatabaseOperation.REFRESH);
		   t.setTearDownOperation(DatabaseOperation.DELETE_ALL);
	   }
	  
	  private static void refreshDataSet(String filename) throws Exception {//legge un file per popolare il db
		   IDataSet initialState = new FlatXmlDataSetBuilder().build(CartaModelTest.class.getClassLoader().getResourceAsStream(filename));
		   t.setDataSet(initialState);
		   t.onSetup();
		   }
	  
	  @Before
	   public void setUp(){//impostiamo il db ad uno stato iniziale
		   try {
			   db = DBConnectionPool.getConnection();
		   }catch(SQLException e){
			   e.printStackTrace();
		   }
		    daoU = new AccountModelDS(db);
		    daoC = new CartaModelDS(db);
		    
		    ut1 = creaUtenteRegistrato("l.bella","e21fc56c1a272b630e0d1439079d0598cf8b8329");//presente nel db
		    ut2 = creaUtenteRegistrato("m.maria","825e064b2c85b54b1e40c143e31f24c19bbac07b");//non presente nel db
		   
		    daoU.doSave(ut1);
		    
		    c1 = creaCarta(String.valueOf(dat),555,"Laura","Bella","7474-7474-7474-7474",ut1.getNickname());
		    c2 = creaCarta(String.valueOf(dat),222,"Maria","Massi","4444-4444-4444-4444",ut2.getNickname());
			   
		   daoC.doSave(c1);
		   }

	   @After
	   public void tearDown() throws Exception {
		   System.out.println("Sono entrato nella tearDown");
		   daoU.doDelete(ut1.getNickname());
		   daoC.doDelete(c1.getCvv());
		   }
	   
	   @Test
	    public void TestGetCardExisting () throws Exception  {
	        assertNotNull(daoC.doRetrieveByKey(c1.getCvv()));
	    }

	   @Test
	    public void TestGetCardNotExisting () throws Exception  {
	        assertNull(daoC.doRetrieveByKey(c2.getCvv()));
	    }

	   @Test
	    public void TestGetAllCards () throws Exception{
	        assertEquals(true, daoC.doRetrieveAll().size()>0);
	    }

	    @Test
	    public void TestInsertCard ()  throws Exception {
	        assertEquals(true, daoC.doSave(c2));
	       assertEquals(true,daoC.doDelete(c2.getCvv()));
	    }

	    @Test
	    public void TestRemoveCardExisting() throws Exception {
	        assertEquals(true,daoC.doDelete(c1.getCvv()));
	        assertEquals(true,daoC.doSave(c1));
	      }

	    @Test
	    public void TestRemoveCardNotExisting () throws Exception{
	        assertEquals(false,daoC.doDelete(c2.getCvv()));
	    }

	    @Test
	    public void TestUpdateCartExisting () throws Exception{
	    	
	    }
	    
	    private AccountUtente creaUtenteRegistrato(String nickname,String pass) {
			AccountUtente x = new AccountUtente();
			x.setNickname(nickname);
			x.setPassword(pass);
			return x;
		}
	    
	   private Carta creaCarta(String dat,int cvv,String nome,String cognome,String numero,String utente) {
		   Carta c = new Carta();
		   c.setDataa(dat);
		   c.setCvv(cvv);
		   c.setNome(nome);
		   c.setCognome(cognome);
		   c.setNumero(numero);
		   c.setUtente(utente);
		   return c;
	   }
}

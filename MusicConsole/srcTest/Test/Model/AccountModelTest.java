package Test.Model;



import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.sql.Connection;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.operation.DatabaseOperation;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mockito;

import gestioneAccount.AccountModelDS;
import gestioneAccount.AccountUtente;

public class AccountModelTest {
	private AccountModelDS dao;
	private static IDatabaseTester t;
	private DataSource ds;
	AccountUtente ut1,ut2;
	
	public AccountModelTest() {
		
	}
	
	
  /* @BeforeAll
   public static void setUpAll() throws ClassNotFoundException {
	   t = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
			   "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'classpath:db/init/schema.sql'", "sa","");
	  
	   t.setSetUpOperation(DatabaseOperation.REFRESH);
	   t.setTearDownOperation(DatabaseOperation.DELETE_ALL);
   }*/
   
   @Before
   public void setUp() throws Exception, SQLException {//impostiamo il db ad uno stato iniziale
	   t = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
			   "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'","sa","");
	  t.setSetUpOperation(DatabaseOperation.REFRESH);
	  t.setTearDownOperation(DatabaseOperation.DELETE_ALL);
	   //refreshDataSet("db/init/init.xml");
    //dao = new AccountModelDS(t.getConnection().getConnection());
   
	  ds = Mockito.mock(DataSource.class);
	  Mockito.when(ds.getConnection()).thenReturn(t.getConnection().getConnection());
	  dao = new AccountModelDS(ds);
	  ut1 = creaUtenteRegistrato("p.paola","e21fc56c1a272b630e0d1439079d0598cf8b8329");
	  ut2 = creaUtenteRegistrato("m.maria","825e064b2c85b54b1e40c143e31f24c19bbac07b");
   }

  /* @AfterEach
   public void tearDown() throws Exception {
    t.onTearDown();
    }*/
   
   /*private static void refreshDataSet(String filename) throws Exception {//legge un file per popolare il db
	   IDataSet initialState = new FlatXmlDataSetBuilder()
	   .build(AccountModelTest.class.getClassLoader()
	   .getResourceAsStream(filename));
	   t.setDataSet(initialState);
	   t.onSetup();
	   }*/

   @Test
	public void testDoRetrieveAll() throws Exception {
	   //risultato che ci aspettiamo
		Collection<AccountUtente> listA = new LinkedList<AccountUtente>();
		listA.add(ut1);
		listA.add(ut2);
		//test di una funzione
		Collection<AccountUtente> actual = dao.doRetrieveAll();
		assertEquals(2, actual.size());
		assertEquals(actual,dao.doRetrieveAll());
		/*model.doDelete("l.laura");
		model.doDelete("p.paolo");
		model.doDelete("m.maria");*/
}

	 /* @Test
	  public void doRetrieveByKeyNull() {
	    assertThrows(IllegalArgumentException.class, () -> {
	      dao.doRetrieveByKey(null,null);
	    });
	  }*/
	  
	  /*@Test
	  public void doSave() {
	    assertThrows(IllegalArgumentException.class, () -> {
	      model.doSave(null);
	    });
	  }*/
	
	 
	  
	  private AccountUtente creaUtenteRegistrato(String nickname,String pass) {
			AccountUtente x = new AccountUtente();
			x.setNickname(nickname);
			x.setPassword(pass);
			return x;
		}
	  
}

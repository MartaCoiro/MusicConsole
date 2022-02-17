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
import java.util.List;
import java.sql.Connection;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import gestioneAccount.AccountModelDS;
import gestioneAccount.AccountUtente;
import it.unisa.utils.DBConnectionPool;

public class AccountModelTest {
	private AccountModelDS dao;
	private static IDatabaseTester t;
	Connection db;
	AccountUtente ut1,ut2;
	 
	 
  @BeforeAll
   public static void setUpAll() throws ClassNotFoundException {
	   t = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
			   "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'", "sa","");
	   t.setSetUpOperation(DatabaseOperation.REFRESH);
	   t.setTearDownOperation(DatabaseOperation.DELETE_ALL);
   }
  
  private static void refreshDataSet(String filename) throws Exception {//legge un file per popolare il db
	   IDataSet initialState = new FlatXmlDataSetBuilder().build(AccountModelTest.class.getClassLoader().getResourceAsStream(filename));
	   t.setDataSet(initialState);
	   t.onSetup();
  }
 
   
   @Before
   public void setUp() throws Exception {//impostiamo il db ad uno stato iniziale
	   try {
		   db = DBConnectionPool.getConnection();
	   }catch(SQLException e){
		   e.printStackTrace();
	   }
	    dao = new AccountModelDS(db);
	    ut1 = creaUtenteRegistrato("p.paola","e21fc56c1a272b630e0d1439079d0598cf8b8329");
		//ut2 = creaUtenteRegistrato("m.maria","825e064b2c85b54b1e40c143e31f24c19bbac07b");
		ut2 = new AccountUtente();
		ut2.setNickname("m.maria");
		ut2.setPassword("825e064b2c85b54b1e40c143e31f24c19bbac07b");
	    dao.doSave(ut1);
		//dao.doSave(ut2);
		//dao.doDelete(ut2.getNickname());
	 }

   @After
   public void tearDown() throws Exception {
	   System.out.println("Sono entrato nella tearDown");
	   //t.onTearDown();
	   dao.doDelete(ut1.getNickname());
	   
    }
   
 
   @Test
	public void testDoRetrieveAll() throws Exception {
	    List<AccountUtente> utente = new ArrayList<>();
		assertNotEquals(utente,dao.doRetrieveAll());
	} 
   
   @Test
   public void TestGetUserExisting () {
       assertNotNull(dao.doRetrieveByKey(ut1.getNickname(),ut1.getPassword()));
   }

   @Test
   public void TestGetUserNotExisting () {
       assertNull(dao.doRetrieveByKey(ut2.getNickname(),ut2.getPassword()));
   }


	  
	  private AccountUtente creaUtenteRegistrato(String nickname,String pass) {
			AccountUtente x = new AccountUtente();
			x.setNickname(nickname);
			x.setPassword(pass);
			return x;
		}
	  
}

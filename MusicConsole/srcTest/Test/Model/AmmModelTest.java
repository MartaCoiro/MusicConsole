package Test.Model;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import gestioneAccount.AccountModelDS;
import gestioneAccount.AccountUtente;
import gestioneAccount.Amm;
import gestioneAccount.AmmModelDS;
import it.unisa.utils.DBConnectionPool;

public class AmmModelTest {

	private AmmModelDS dao;
	private static IDatabaseTester t;
	Connection db;
	Amm am1,am2;
	
	 @BeforeAll
	   public static void setUpAll() throws ClassNotFoundException {
		   t = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
				   "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'", "sa","");
		   t.setSetUpOperation(DatabaseOperation.REFRESH);
		   t.setTearDownOperation(DatabaseOperation.DELETE_ALL);
	   }
	  
	  private static void refreshDataSet(String filename) throws Exception {//legge un file per popolare il db
		   IDataSet initialState = new FlatXmlDataSetBuilder().build(AmmModelTest.class.getClassLoader().getResourceAsStream(filename));
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
		    dao = new AmmModelDS(db);
		    am1 = creaAmministratore("p.paola","111","gestore ordini");
			am2 = new Amm();
			am2.setUtente("m.maria");
			am2.setPassword("222");
			am2.setRuolo("gestore catalogo");
		    dao.doSave(am1);
		 }
	   
	   @After
	   public void tearDown() throws Exception {
		   System.out.println("Sono entrato nella tearDown");
		   //t.onTearDown();
		   dao.doDelete(am1.getUtente());
		   }
	   
	   @Test
		public void testDoRetrieveAll() throws Exception {
		    List<Amm> amm = new ArrayList<>();
			assertNotEquals(amm,dao.doRetrieveAll());
		} 
	   
	   @Test
	   public void TestGetAmmExisting () {
	       assertNotNull(dao.doRetrieveByKey(am1.getUtente(),am1.getPassword()));
	   }

	   @Test
	   public void TestGetAmmNotExisting () {
	       assertNull(dao.doRetrieveByKey(am2.getUtente(),am2.getPassword()));
	   }

	   @Test
	   public void doRetrieveByNickname() {
	   	Amm ammSucc = dao.doRetrieveByKey(am1.getUtente(),am1.getPassword());
	       assertEquals("p.paola", ammSucc.getUtente());
	   }

	   @Test
	   public void doRetrieveByPassword() {
	   	Amm ammSucc = dao.doRetrieveByKey(am1.getUtente(),am1.getPassword());
	       assertEquals("111", ammSucc.getPassword());
	   }

	   @Test
	   public void doSave() {
		   Amm amN = creaAmministratore("c.carlo","44","gestore magazzino");
		   boolean succ = dao.doSave(amN);
	       assertEquals(true, succ);
	       amN = dao.doRetrieveByKey(amN.getUtente(), amN.getPassword());
	       assertEquals(true,dao.doDelete(amN.getUtente()));
	   }
	   
	   @Test
	   public void TestDeleteAmmExisting () {
	       assertEquals(true,dao.doDelete(am1.getUtente()));
	       assertEquals(true,dao.doSave(am1));
	       //ut1 = userDAO.getUserByUsername("SampleUsername");
	       assertNotEquals("000", am1.getUtente());
	   }
	   
	   
	   private Amm creaAmministratore(String nickname,String pass,String ruolo) {
			Amm x = new Amm();
			x.setUtente(nickname);
			x.setPassword(pass);
			x.setRuolo(ruolo);
			return x;
		}

}

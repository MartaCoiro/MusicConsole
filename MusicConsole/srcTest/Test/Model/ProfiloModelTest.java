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
import gestioneAccount.Profilo;
import gestioneAccount.ProfiloModelDS;
import it.unisa.utils.DBConnectionPool;

public class ProfiloModelTest {

	private ProfiloModelDS dao;
	private static IDatabaseTester t;
	Connection db;
	Profilo ut1,ut2;
	 
	 
  @BeforeAll
   public static void setUpAll() throws ClassNotFoundException {
	   t = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
			   "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'", "sa","");
	   t.setSetUpOperation(DatabaseOperation.REFRESH);
	   t.setTearDownOperation(DatabaseOperation.DELETE_ALL);
   }
  
  private static void refreshDataSet(String filename) throws Exception {//legge un file per popolare il db
	   IDataSet initialState = new FlatXmlDataSetBuilder().build(ProfiloModelTest.class.getClassLoader().getResourceAsStream(filename));
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
	    dao = new ProfiloModelDS(db);
	    ut1 = creaProfilo(30,"Paola","Rossi","Roma","Via delle fontane","1234567894","p.paola@gmail.com","p.paola","pp");
		ut2 = creaProfilo(31,"Maria","Bianchi","Firenze","Via dei fiori","1234567810","m.maria@gmail.com","m.maria","mm");
		dao.doSave(ut1);
	 }

   @After
   public void tearDown() throws Exception {
	   System.out.println("Sono entrato nella tearDown");
	   dao.doDelete(ut1.getId());
	   }
   
   @Test
  	public void testDoRetrieveAll() throws Exception {
  	    List<Profilo> pro = new ArrayList<>();
  		assertNotEquals(pro,dao.doRetrieveAll());
  	} 
     
     @Test
     public void TestGetProfiloExisting () {
         assertNotNull(dao.doRetrieveByKey(ut1.getUsername()));
     }

     @Test
     public void TestGetProfiloNotExisting () {
         assertNull(dao.doRetrieveByKey(ut2.getUsername()));
     }
     
     @Test
     public void doRetrieveById() {
     	Profilo proSucc = dao.doRetrieveByKey(ut1.getUsername());
         assertEquals(30, proSucc.getId());
     }
     
     @Test
     public void doRetrieveByNome() {
     	Profilo proSucc = dao.doRetrieveByKey(ut1.getUsername());
         assertEquals("Paola", proSucc.getNome());
     }
     
     @Test
     public void doRetrieveByCognome() {
     	Profilo proSucc = dao.doRetrieveByKey(ut1.getUsername());
         assertEquals("Rossi", proSucc.getCognome());
     }
     
     @Test
     public void doRetrieveByCitta() {
     	Profilo proSucc = dao.doRetrieveByKey(ut1.getUsername());
         assertEquals("Roma", proSucc.getCitta());
     }
     
     @Test
     public void doRetrieveByIndirizzo() {
     	Profilo proSucc = dao.doRetrieveByKey(ut1.getUsername());
         assertEquals("Via delle fontane", proSucc.getIndirizzo());
     }
     
     @Test
     public void doRetrieveByTelefono() {
     	Profilo proSucc = dao.doRetrieveByKey(ut1.getUsername());
         assertEquals("1234567894", proSucc.getTelefono());
     }
     
     @Test
     public void doRetrieveByEmail() {
     	Profilo proSucc = dao.doRetrieveByKey(ut1.getUsername());
         assertEquals("p.paola@gmail.com", proSucc.getEmail());
     }
     
     @Test
     public void doRetrieveByUsername() {
     	Profilo proSucc = dao.doRetrieveByKey(ut1.getUsername());
         assertEquals("p.paola", proSucc.getUsername());
     }

     @Test
     public void doRetrieveByPassword() {
     	Profilo proSucc = dao.doRetrieveByKey(ut1.getUsername());
         assertEquals("pp", proSucc.getPassword());
     }
     
     @Test
     public void doSave() {
  	   Profilo pN = creaProfilo(32,"Carlo","Verdi","Milano","Via dei palazzi","1234567841","c.carlo@gmail.com","c.carlo","cc");
  	   boolean succ = dao.doSave(pN);
         assertEquals(true, succ);
         pN = dao.doRetrieveByKey(pN.getUsername());
         assertEquals(true,dao.doDelete(pN.getId()));
     }
     
     @Test
     public void TestDeleteUserExisting () {
         assertEquals(true,dao.doDelete(ut1.getId()));
         assertEquals(true,dao.doSave(ut1));
         //ut1 = userDAO.getUserByUsername("SampleUsername");
         assertNotEquals(10, ut1.getId());
     }
     
     @Test 
     public void TestUpdateUtente() {
    	 ut1.setNome("Rosa");
    	 assertEquals(true,dao.doUpdate("nome","Rosa",ut1.getUsername()));
     }
     

     @Test
     public void TestUpdateUtenteNotExisting () {
         assertEquals(false, dao.doUpdate("nome","Rosa",ut2.getUsername()));
     }

 
   
   private Profilo creaProfilo(int id,String nome,String cognome,String citta,String indirizzo,String telefono,String email,String username,String password) {
		Profilo x = new Profilo();
		x.setId(id);
		x.setNome(nome);
		x.setCognome(cognome);
		x.setCitta(citta);
		x.setIndirizzo(indirizzo);
		x.setTelefono(telefono);
		x.setEmail(email);
		x.setUsername(username);
		x.setPassword(password);
		return x;
	}
   
}

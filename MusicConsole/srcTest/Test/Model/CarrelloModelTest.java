package Test.Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

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
import gestioneAcquisti.Ordini;
import gestioneAcquisti.OrdiniModelDS;
import gestioneCarrello.Carrello;
import gestioneCarrello.CarrelloModelDS;
import it.unisa.utils.DBConnectionPool;


public class CarrelloModelTest {

	private AccountModelDS daoU;
	private CarrelloModelDS daoO;
	private static IDatabaseTester t;
	Connection db;
	AccountUtente ut1,ut2,ut3;
	Carrello car1,car2;
	
	 @BeforeAll
	   public static void setUpAll() throws ClassNotFoundException {
		   t = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
				   "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'", "sa","");
		   t.setSetUpOperation(DatabaseOperation.REFRESH);
		   t.setTearDownOperation(DatabaseOperation.DELETE_ALL);
	   }
	  
	  private static void refreshDataSet(String filename) throws Exception {//legge un file per popolare il db
		   IDataSet initialState = new FlatXmlDataSetBuilder().build(CarrelloModelTest.class.getClassLoader().getResourceAsStream(filename));
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
		    daoO = new CarrelloModelDS(db);
		    
		    ut1 = creaUtenteRegistrato("l.bella","e21fc56c1a272b630e0d1439079d0598cf8b8329");//presente nel db
		    ut2 = creaUtenteRegistrato("m.maria","825e064b2c85b54b1e40c143e31f24c19bbac07b");//non presente nel db
		    ut3 = creaUtenteRegistrato("p.paola","06b08b32563194c5d89d83b119a25c21b9ce4cd7");//presente nel db
		    
		    daoU.doSave(ut3);
		    daoU.doSave(ut1);
		    
		    car1 = aggProdCarrello(1.45f,0,ut1.getNickname(),"Crepe","Irama","Streaming",1.45f);
		    car1.setCod(15);
		    car2 = aggProdCarrello(45.0f,2,ut1.getNickname(),"Gemelli","Ernia","CD",22.5f);
			car2.setCod(16);
		    
			daoO.doSave(car1);
		    daoO.doSave(car2);
	  }
	 
	 @After
	   public void tearDown() throws Exception {
		   System.out.println("Sono entrato nella tearDown");
		   daoU.doDelete(ut1.getNickname());
		   //daoU.doDelete(ut3.getNickname());
		   daoO.doDelete(car1.getCod());
		   daoO.doDelete(car2.getCod());
		   }
	   
	  @Test
	    public void TestGetCarrelloByUser () throws Exception {
		 assertEquals(true,daoO.RestXUtente(ut1.getNickname()).size()>0);
		 }

	    @Test
	    public void TestGetCarrelloByUserNotExisting ()  throws Exception{
	    	 assertEquals(true,daoO.RestXUtente(ut2.getNickname()).size()==0);
	    }
	    
	    @Test
	    public void TestGetCarrelloByUserNotOrdini () throws Exception{
	    	 assertEquals(true,daoO.RestXUtente(ut3.getNickname()).size()==0);
	    }
	    
	    @Test
	    public void TestGetCarrello() throws Exception {
	    	 assertEquals(true,daoO.doRetrieveAll().size()>0);
		 }

	    @Test
	    public void TestInsertProd() throws Exception {
	    	Carrello car = aggProdCarrello(0.9f,0,ut1.getNickname(),"Collera","Nicola Siciliano","Streaming",0.9f);		
	    	car.setCod(17);
	    	assertTrue(daoO.doSave(car));
	    	assertTrue(daoO.doDelete(car.getCod()));
	        }
	    
	    @Test
	    public void TestInsertProdStreamingAlreadyExist() throws Exception {
	    	assertFalse(daoO.doSave(car1));
	    }
	    
	    @Test
	    public void TestInsertProdNotStreamingAlreadyExist() throws Exception {
	    	assertTrue(daoO.doUpdate(car2.getQuantità()+1,car2.getCosto()*(car2.getQuantità()+1),car2.getCod()));
	    }
	    
	    @Test
	    public void TestRimuovereProdNotStreamingAlreadyExist() throws Exception {
	    	assertTrue(daoO.doUpdate(car2.getQuantità()-1,car2.getCosto()*(car2.getQuantità()-1),car2.getCod()));
	    }
	    
	    @Test
	    public void TestdeleteProdExist() throws Exception {
	    	assertTrue(daoO.doDelete(car1.getCod()));
	    	assertTrue(daoO.doSave(car1));
	    }
	    
	    @Test
	    public void TestdeleteProdNotExist() throws Exception {
	    	Carrello car = aggProdCarrello(0.9f,0,ut1.getNickname(),"Collera","Nicola Siciliano","Streaming",0.9f);		
	    	car.setCod(17);
	    	assertFalse(daoO.doDelete(car.getCod()));
	    }
	    
	    @Test 
	    public void TestSumCarrelloByUser() throws Exception{
	    	 assertEquals("46.45",String.valueOf(daoO.doSum(ut1.getNickname())));	
	    }
	    
	    @Test 
	    public void TestSumCarrelloByUserNotExsisting() throws Exception{
	    	 assertEquals("0.0",String.valueOf(daoO.doSum(ut2.getNickname())));	
	    }
	    
	   private Carrello aggProdCarrello(float totq,int quant,String utente,String nome,String autore,String tipo,float costo) {
			  Carrello c = new Carrello();
			  c.setTotq(totq);
			  c.setQuantità(quant);
			  c.setUtente(utente);
			  c.setNome(nome);
			  c.setAutore(autore);
			  c.setTipo(tipo);
			  c.setCosto(costo);
			  return c;
		  }
	    
	  private AccountUtente creaUtenteRegistrato(String nickname,String pass) {
			AccountUtente x = new AccountUtente();
			x.setNickname(nickname);
			x.setPassword(pass);
			return x;
		}
	   
	           
}

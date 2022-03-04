package Test.Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
 
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;

import gestioneAccount.AccountModelDS;
import gestioneAccount.AccountUtente;
import gestioneAcquisti.Ordini;
import gestioneAcquisti.OrdiniModelDS;
import it.unisa.utils.DBConnectionPool;

public class OrdiniModelTest {

	private AccountModelDS daoU;
	private OrdiniModelDS daoO;
	private static IDatabaseTester t;
	Connection db;
	AccountUtente ut1,ut2, ut3;
	Ordini ord1,ord2;
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
		   IDataSet initialState = new FlatXmlDataSetBuilder().build(OrdiniModelTest.class.getClassLoader().getResourceAsStream(filename));
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
		    daoO = new OrdiniModelDS(db);
		    
		    ut1 = creaUtenteRegistrato("l.bella","e21fc56c1a272b630e0d1439079d0598cf8b8329");//presente nel db
		    ut2 = creaUtenteRegistrato("m.maria","825e064b2c85b54b1e40c143e31f24c19bbac07b");//non presente nel db
		    ut3 = creaUtenteRegistrato("p.paola","06b08b32563194c5d89d83b119a25c21b9ce4cd7");//presente nel db
		    
		    daoU.doSave(ut3);
		    daoU.doSave(ut1);
		   
			ord1 = creaOrdine(10,0,"false",11,ut1.getNickname(),"Moncler","Geolier","Streaming",1.25f,61.25f);
			ord2 = creaOrdine(11,2,"false",11,ut1.getNickname(),"Crepe","Irama","CD",27.5f,61.25f);		
			ord1.setData(dat);
		    ord2.setData(dat);
			
			daoO.doSave(ord1);
			daoO.doSave(ord2);
		}
 
	   @After
	   public void tearDown() throws Exception {
		   System.out.println("Sono entrato nella tearDown");
		   daoU.doDelete(ut1.getNickname());
		   daoU.doDelete(ut3.getNickname());
		   daoO.doDelete(ord1.getIndice());
		   }
	   
	   @Test
	    public void TestGetOrdiniByUser () throws Exception {
		 assertEquals(true,daoO.doRetrieveByKey(ut1.getNickname()).size()>0);
		 }

	    @Test
	    public void TestGetOrdiniByUserNotExisting () {
	        assertEquals(true,daoO.doRetrieveByKey(ut2.getNickname()).size()==0);
	    }
	    
	    @Test
	    public void TestGetOrdiniByUserNotOrdini () {
	    	assertEquals(true,daoO.doRetrieveByKey(ut3.getNickname()).size()==0);
	    }
	    
	    @Test
	    public void TestGetOrdini() throws Exception {
	        assertEquals(true,daoO.doRetrieveAll().size()>0);
		 }

	    @Test
	    public void TestInsertOrdine() throws Exception {
	    	Ordini ord = creaOrdine(15,2,"false",12,ut1.getNickname(),"Achille Lauro","1969","Vinile",25.5f,25.5f);		
	    	ord.setData(dat);
	    	assertTrue(daoO.doSave(ord));
	    	assertTrue(daoO.doDelete(ord.getIndice()));
	        }
	    
	    @Test
	    public void TestInsertOrdineAlreadyExist() throws Exception {
	    	assertFalse(daoO.doSave(ord1));
	    }
	    
	    @Test
	    public void TestdeleteOrdineExist() throws Exception {
	    	assertTrue(daoO.doDelete(ord1.getIndice()));
	    	assertTrue(daoO.doSave(ord1));
	    	assertTrue(daoO.doSave(ord2));
	    }
	    
	    @Test
	    public void TestdeleteOrdineNotExist() throws Exception {
	    	Ordini ord = creaOrdine(15,2,"false",12,ut1.getNickname(),"Achille Lauro","1969","Vinile",25.5f,25.5f);		
	    	ord.setData(dat);
	    	assertFalse(daoO.doDelete(ord.getIndice()));
	    }

	    @Test
	    public void TestGetIndici() throws Exception {
	        assertEquals(true,daoO.getIndici().size()>0);
		 }
	   
	   @Test
	    public void TestGetOrdiniOrdinato() throws Exception {
	    	 assertEquals(true,daoO.doRetrieveAllOrdinato().size()>0);
		 }
	   
	   @Test
	    public void TestUpdateStatusOrdine() throws Exception {
	    	assertTrue(daoO.doUpdate("true",ord1.getIndice()));
	    }
	   
	  private AccountUtente creaUtenteRegistrato(String nickname,String pass) {
			AccountUtente x = new AccountUtente();
			x.setNickname(nickname);
			x.setPassword(pass);
			return x;
		}
	   
	   private Ordini creaOrdine(int cod, int qua,String stato,int indice,String utente,String nome,String autore,String tipo,float costo,float tot) {
			Ordini x = new Ordini();
			x.setCod(cod);
			x.setQuantità(qua);
			x.setStato(stato);
			x.setIndice(indice);
			x.setUtente(utente);
			x.setNome(nome);
			x.setAutore(autore);
			x.setTipo(tipo);
			x.setCosto(costo);
			x.setTot(tot);
			return x;
		}
}

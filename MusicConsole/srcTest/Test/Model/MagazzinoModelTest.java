package Test.Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
import gestioneAcquisti.Ordini;
import gestioneAcquisti.OrdiniModelDS;
import gestioneProdotti.Magazzino;
import gestioneProdotti.MagazzinoModelDS;
import it.unisa.utils.DBConnectionPool;

public class MagazzinoModelTest {

	private MagazzinoModelDS daoM;
	Magazzino m1,m2;
	private static IDatabaseTester t;
	Connection db;
	
	@BeforeAll
	   public static void setUpAll() throws ClassNotFoundException {
		   t = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
				   "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'", "sa","");
		   t.setSetUpOperation(DatabaseOperation.REFRESH);
		   t.setTearDownOperation(DatabaseOperation.DELETE_ALL);
	   }
	  
	  private static void refreshDataSet(String filename) throws Exception {//legge un file per popolare il db
		   IDataSet initialState = new FlatXmlDataSetBuilder().build(MagazzinoModelTest.class.getClassLoader().getResourceAsStream(filename));
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
		    daoM = new MagazzinoModelDS(db);
		    
		    m1 = creaProdotto(200,"Blu Celeste","Blanco","Vinile",21.5f);//presente nel db
		    m2 = creaProdotto(200,"Taxi Driver","Rkomi","Vinile",24.1f);//non presente nel db
		    m1.setCod(50);
		    m2.setCod(51);
		    daoM.doSave(m1);
		}

	   @After
	   public void tearDown() throws Exception {
		   System.out.println("Sono entrato nella tearDown");
		   daoM.doDelete(m1.getCod());
		   }
	   
	   @Test
	    public void TestGetProdExisting ()  throws Exception {
	        assertNotNull(daoM.doRetrieveByKey(m1.getCod()));
	    }

	    @Test
	    public void TestGetProdNotExisting () throws Exception {
	        assertNull(daoM.doRetrieveByKey(m2.getCod()));
	    }
	    
	    @Test
	    public void TestGetAllProd () throws Exception {
	        assertNotNull(daoM.doRetrieveAll().size()>0);
	    }
	    
	    @Test
	    public void TestAddProd() throws Exception {
	        assertEquals(true,daoM.doSave(m2));
	        daoM.doDelete(m2.getCod());
	    }

	    @Test
	    public void TestDeleteProdExisting ()  throws Exception{
	        assertEquals(true,daoM.doDelete(m1.getCod()));
	        daoM.doSave(m1);
	        m1 = daoM.doRetrieveByKey(m1.getCod());
	        assertNotEquals(-1,m1.getCod().intValue());
	    }
	    
	    @Test
	    public void TestDeleteProdNotExisting () throws Exception {
	        assertEquals(false, daoM.doDelete(m2.getCod()));
	    }
	    
	    @Test
	    public void TestUpdateQuantProdExisting () throws Exception {
	        assertEquals(true, daoM.doUpdate(100,m1.getCod()));
	    }
	    @Test
	    public void TestUpdateQuantProdNotExisting () throws Exception {
	    	assertEquals(false, daoM.doUpdate(100,m2.getCod()));
	    }
	    
	    private Magazzino creaProdotto(int quantità,String nome,String autore,String tipo,float costo) {
			Magazzino x = new Magazzino();
			x.setQuantità(quantità);
			x.setNome(nome);
			x.setAutore(autore);
			x.setTipo(tipo);
			x.setCosto(costo);
			return x;
		}
}

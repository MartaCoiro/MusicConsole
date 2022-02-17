package Test.Model;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
	AccountUtente ut1,ut2;
	Ordini ord1,ord2;
	Date data = new Date();
	java.sql.Date dat = new java.sql.Date(data.getTime());
	

    public OrdiniModelTest() {}
/*	
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
	   
*/	    
	   @Before
	   public void setUp() throws Exception {//impostiamo il db ad uno stato iniziale
		   try {
			   db = DBConnectionPool.getConnection();
		   }catch(SQLException e){
			   e.printStackTrace();
		   }
		    daoU = new AccountModelDS(db);
		    ut1 = creaUtenteRegistrato("l.bella","e21fc56c1a272b630e0d1439079d0598cf8b8329");
		    ut2 = creaUtenteRegistrato("m.maria","825e064b2c85b54b1e40c143e31f24c19bbac07b");
		    daoU.doSave(ut1);
		    daoO = new OrdiniModelDS(db);
			ord1 = creaOrdine(10,0,"false",11,ut1.getNickname(),"Moncler","Geolier","Streaming",1.25f,61.25f);
			//ord2 = creaOrdine(11,2,"false",11,ut1.getNickname(),"Crepe","Irama","CD",27.5f,61.25f);		
		    ord1.setData(dat);
		    //ord2.setData(dat);
			
			daoO.doSave(ord1);
			//daoO.doSave(ord2);
		}
 
	   @After
	   public void tearDown() throws Exception {
		   System.out.println("Sono entrato nella tearDown");
		   daoU.doDelete(ut1.getNickname());
		   daoO.doDelete(ord1.getIndice());
		   }
	   
	   @Test
	    public void TestGetOrdiniByUser () {
		  Collection<Ordini> oo = daoO.doRetrieveAll();
		  assertEquals(true,oo.size()>0);
		   
		  // System.out.println(daoO.doRetrieveByKey(ut1.getNickname()).size()>0);
	        //assertEquals(true,daoO.doRetrieveByKey(ut1.getNickname()).size()>0);
	    }
/*
	    @Test
	    public void TestGetOrdiniByUserNotExisting () {
	        assertEquals(true,(daoO.doRetrieveByKey(ut2.getNickname())).size()==0);
	    }
	  */  
	   private AccountUtente creaUtenteRegistrato(String nickname,String pass) {
			AccountUtente x = new AccountUtente();
			x.setNickname(nickname);
			x.setPassword(pass);
			return x;
		}
	   
	   private Ordini creaOrdine(int cod,int qua,String stato,int indice,String utente,String nome,String autore,String tipo,float costo,float tot) {
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

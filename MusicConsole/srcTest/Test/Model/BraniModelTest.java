package Test.Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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

import gestioneProdotti.Album;
import gestioneProdotti.AlbumModelDS;
import gestioneProdotti.BraniModelDS;
import gestioneProdotti.Brano;
import it.unisa.utils.DBConnectionPool;

public class BraniModelTest {

	private BraniModelDS daoB;
	private static IDatabaseTester t;
	Brano b1,b2;
	Connection db;
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
		   IDataSet initialState = new FlatXmlDataSetBuilder().build(BraniModelTest.class.getClassLoader().getResourceAsStream(filename));
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
		    daoB = new BraniModelDS(db);
		    
		    b1 = creaBrano(1000,"Blu Celeste",2.34f,"Blanco","img1000.png","pop","Brano","bluCeleste.mp3",5.2f,"Bellissimo");//presente nel db
		    b2 = creaBrano(1001,"Taxi Driver",3.14f,"Rkomi","img1001.png","pop","Brano","taxiDriver.mp3",7.2f,"Stupendo");//non presente nel db
		    b1.setDat(dat);
		    b2.setDat(dat);
		    
		    daoB.doSave(b1);
		}

	   @After
	   public void tearDown() throws Exception {
		   System.out.println("Sono entrato nella tearDown");
		   daoB.doDelete(b1.getCodice());
	   }
	   
	   @Test
	    public void TestGetBranoExisting ()  throws Exception {
	        assertNotNull(daoB.doRetrieveByKey(b1.getTitolo(),b1.getCantante()));
	    }

	    @Test
	    public void TestGetBranoNotExisting () throws Exception {
	        assertNull(daoB.doRetrieveByKey(b2.getTitolo(), b2.getCantante()));
	    }
	    
	    @Test
	    public void TestGetAllBrani () throws Exception {
	        assertNotNull(daoB.doRetrieveAll().size()>0);
	    }

	    @Test
	    public void TestAddBrano() throws Exception {
	        assertEquals(true,daoB.doSave(b2));
	        daoB.doDelete(b2.getCodice());
	    }

	    @Test
	    public void TestDeleteBranoExisting ()  throws Exception{
	        assertEquals(true,daoB.doDelete(b1.getCodice()));
	        daoB.doSave(b1);
	        b1 = daoB.doRetrieveByKey(b1.getTitolo(), b1.getCantante());
	        assertNotEquals(-1,b1.getCodice().intValue());
	    }
	    
	    @Test
	    public void TestDeleteBranoNotExisting () throws Exception {
	        assertEquals(false, daoB.doDelete(b2.getCodice()));
	    }
	    
	    @Test
	    public void TestUpdateBranoExisting () throws Exception {
	        assertEquals(true, daoB.doUpdate("nome","Giostra",b1.getCodice()));
	    }
	    @Test
	    public void TestUpdateBranoNotExisting () throws Exception {
	    	assertEquals(false, daoB.doUpdate("nome","Giostra",b2.getCodice()));
	    }
	    
	    @Test
	    public void TestUpdatePrezzoBranoExisting () throws Exception {
	        assertEquals(true, daoB.doUpdatePrezzo("prezzo",15.4f,b1.getCodice()));
	    }
	    
	    @Test
	    public void TestUpdatePrezzoBranoNotExisting () throws Exception {
	    	assertEquals(false, daoB.doUpdatePrezzo("prezzo",15.4f,b2.getCodice()));
	    }
	   
	   private Brano creaBrano(int cod,String nome,float durata,String artista,String img,String genere,String tipo,String suono,float prezzo,String descrizione) {
			Brano x = new Brano();
			x.setCodice(cod);
			x.setTitolo(nome);
			x.setDurata(durata);
			x.setImgBrano(img);
			x.setCantante(artista);
			x.setGenere(genere);
			x.setTipo(tipo);
			x.setSuono(suono);
			x.setPrezzo(prezzo);
			x.setDescrizione(descrizione);
			return x;
		}
}

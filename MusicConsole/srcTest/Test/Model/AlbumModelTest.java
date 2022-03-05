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

import gestioneAccount.AccountModelDS;
import gestioneAccount.AccountUtente;
import gestioneProdotti.Album;
import gestioneProdotti.AlbumModelDS;
import it.unisa.utils.DBConnectionPool;

public class AlbumModelTest {

	private AlbumModelDS daoA;
	Album a1,a2;
	private static IDatabaseTester t;
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
		   IDataSet initialState = new FlatXmlDataSetBuilder().build(AlbumModelTest.class.getClassLoader().getResourceAsStream(filename));
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
		    daoA = new AlbumModelDS(db);
		    
		    a1 = creaAlbum(1000,"Blu Celeste","img1000.png","Blanco","Album",5.2f,32.1f,21.0f,"Bellissimo");//presente nel db
		    a2 = creaAlbum(1001,"Taxi Driver","img1001.png","Rkomi","Album",7.2f,33.1f,25.0f,"Stupendo");//non presente nel db
		    a1.setDat(dat);
		    a2.setDat(dat);
		    
		    daoA.doSave(a1);
		}

	   @After
	   public void tearDown() throws Exception {
		   System.out.println("Sono entrato nella tearDown");
		   daoA.doDelete(a1.getCodiceAlbum());
	   }
	   
	   @Test
	    public void TestGetAlbumExisting ()  throws Exception {
	        assertNotNull(daoA.doRetrieveByKey(a1.getNomeAlbum(),a1.getNartista()));
	    }

	    @Test
	    public void TestGetAlbumNotExisting () throws Exception {
	        assertNull(daoA.doRetrieveByKey(a2.getNomeAlbum(), a2.getNartista()));
	    }
	    
	    @Test
	    public void TestGetAllAlbum () throws Exception {
	        assertNotNull(daoA.doRetrieveAll().size()>0);
	    }

	    @Test
	    public void TestAddAlbum() throws Exception {
	        assertEquals(true,daoA.doSave(a2));
	        daoA.doDelete(a2.getCodiceAlbum());
	    }

	    @Test
	    public void TestDeleteAlbumExisting ()  throws Exception{
	        assertEquals(true,daoA.doDelete(a1.getCodiceAlbum()));
	        daoA.doSave(a1);
	        a1 = daoA.doRetrieveByKey(a1.getNomeAlbum(), a1.getNartista());
	        assertNotEquals(-1,a1.getCodiceAlbum().intValue());
	    }
	    
	    @Test
	    public void TestDeleteAlbumNotExisting () throws Exception {
	        assertEquals(false, daoA.doDelete(a2.getCodiceAlbum()));
	    }
	    
	    @Test
	    public void TestUpdateAlbumExisting () throws Exception {
	        assertEquals(true, daoA.doUpdate("nome","Giostra",a1.getCodiceAlbum()));
	    }
	    @Test
	    public void TestUpdateAlbumNotExisting () throws Exception {
	    	assertEquals(false, daoA.doUpdate("nome","Giostra",a2.getCodiceAlbum()));
	    }
	    
	    @Test
	    public void TestUpdatePrezzoAlbumExisting () throws Exception {
	        assertEquals(true, daoA.doUpdatePrezzo("prezzoS",15.4f,a1.getCodiceAlbum()));
	    }
	    
	    @Test
	    public void TestUpdatePrezzoAlbumNotExisting () throws Exception {
	    	assertEquals(false, daoA.doUpdatePrezzo("prezzoS",15.4f,a2.getCodiceAlbum()));
	    }
	    
	    private Album creaAlbum(int cod,String nome,String img,String artista,String tipo,float pS,float pV,float pC,String descrizione) {
			Album x = new Album();
			x.setCodiceAlbum(cod);
			x.setNomeAlbum(nome);
			x.setImgAlbum(img);
			x.setNartista(artista);
			x.setTipo(tipo);
			x.setPrezzoS(pS);
			x.setPrezzoV(pV);
			x.setPrezzoC(pC);
			return x;
		}
	   
}

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
import gestioneProdotti.Podcast;
import gestioneProdotti.PodcastModelDS;
import it.unisa.utils.DBConnectionPool;

public class PodcastModelTest {

	private PodcastModelDS daoP;
	Podcast p1,p2;
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
		   IDataSet initialState = new FlatXmlDataSetBuilder().build(PodcastModelTest.class.getClassLoader().getResourceAsStream(filename));
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
		    daoP = new PodcastModelDS(db);
		    
		    p1 = creaPodcast("La città dei vivi","Nicola Lagioia","Bellissimo",10f,14,"img1000.png","Podcast",5.2f);//presente nel db
		    p2 = creaPodcast("Fottuti geni","Massimo Temporelli","Stupendo",10f,12,"img1001.png","Podcast",7.2f);//non presente nel db
		    p1.setDat(dat);
		    p2.setDat(dat);
		    
		    daoP.doSave(p1);
		}

	   @After
	   public void tearDown() throws Exception {
		   System.out.println("Sono entrato nella tearDown");
		   daoP.doDelete(p1.getNomePodcast());
	   }
	   
	   @Test
	    public void TestGetPodcastExisting ()  throws Exception {
	        assertNotNull(daoP.doRetrieveByKey(p1.getNomePodcast()));
	    }

	    @Test
	    public void TestGetPodcastNotExisting () throws Exception {
	        assertNull(daoP.doRetrieveByKey(p2.getNomePodcast()));
	    }
	    
	    @Test
	    public void TestGetAllPodcast () throws Exception {
	        assertNotNull(daoP.doRetrieveAll().size()>0);
	    }

	    @Test
	    public void TestAddPodcast() throws Exception {
	        assertEquals(true,daoP.doSave(p2));
	        daoP.doDelete(p2.getNomePodcast());
	    }

	    @Test
	    public void TestDeletePodcastExisting ()  throws Exception{
	        assertEquals(true,daoP.doDelete(p1.getNomePodcast()));
	        daoP.doSave(p1);
	        p1 = daoP.doRetrieveByKey(p1.getNomePodcast());
	        assertNotEquals(null,p1.getNomePodcast());
	    }
	    
	    @Test
	    public void TestDeletePodcastNotExisting () throws Exception {
	        assertEquals(false, daoP.doDelete(p2.getNomePodcast()));
	    }
	    
	    @Test
	    public void TestUpdatePodcastExisting () throws Exception {
	        assertEquals(true, daoP.doUpdate("artista","Fabrizio Moro",p1.getNomePodcast()));
	    }
	    @Test
	    public void TestUpdatePodcastNotExisting () throws Exception {
	    	assertEquals(false, daoP.doUpdate("artista","Fabrizio Moro",p2.getNomePodcast()));
	    }
	    
	    @Test
	    public void TestUpdatePrezzoPodcastExisting () throws Exception {
	        assertEquals(true, daoP.doUpdatePrezzo("prezzo",15.4f,p1.getNomePodcast()));
	    }
	    
	    @Test
	    public void TestUpdatePrezzoPodcastNotExisting () throws Exception {
	    	assertEquals(false, daoP.doUpdatePrezzo("prezzo",15.4f,p2.getNomePodcast()));
	    }
	   
	   private Podcast creaPodcast(String nome,String artista,String descrizione,float durata,int numEpisodi,String img,String tipo,float prezzo) {
			Podcast x = new Podcast();
			x.setNomePodcast(nome);
			x.setIdeatore(artista);
			x.setDescrizione(descrizione);
			x.setImgPod(img);
			x.setDurata(durata);
			x.setTipo(tipo);
			x.setNumEpisodi(numEpisodi);
			x.setPrezzo(prezzo);
			return x;
		}
}

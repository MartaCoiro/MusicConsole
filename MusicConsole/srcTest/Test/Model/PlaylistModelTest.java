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
import gestioneAccount.AccountUtente;
import gestioneAcquisti.Ordini;
import gestioneProdotti.AlbumModelDS;
import gestioneProdotti.Playlist;
import gestioneProdotti.PlaylistModelDS;
import it.unisa.utils.DBConnectionPool;

public class PlaylistModelTest {

	private PlaylistModelDS daoP;
	private AccountModelDS daoA;
	Playlist p1, p2, p3, p4;
	AccountUtente ut1,ut2;
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
		   IDataSet initialState = new FlatXmlDataSetBuilder().build(PlaylistModelTest.class.getClassLoader().getResourceAsStream(filename));
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
		    daoP = new PlaylistModelDS(db);
		    daoA = new AccountModelDS(db);
		    
		    ut1 = creaUtenteRegistrato("l.bella","e21fc56c1a272b630e0d1439079d0598cf8b8329");//presente nel db
		    ut2 = creaUtenteRegistrato("m.maria","825e064b2c85b54b1e40c143e31f24c19bbac07b");//non presente nel db
		    
		    daoA.doSave(ut1);
		    
		    p1 = creaPlaylist(50,ut1.getNickname(),"Music"," ",0," ");//presente nel db
		    p2 = creaPlaylist(51,ut1.getNickname(),"Music","Taxi Driver",0,"Rkomi");//non presente nel db
		   
		    p3 = creaPlaylist(52,ut2.getNickname(),"Baila"," ",0," ");//presente nel db
		    p4 = creaPlaylist(53,ut2.getNickname(),"Baila","Blu Celeste",0,"Blanco");//non presente nel db
		    
		    daoP.doSave(p1);
		    daoP.doSave(p2);
		}

	   @After
	   public void tearDown() throws Exception {
		   System.out.println("Sono entrato nella tearDown");
		   daoP.doDelete(p2.getNomeBrano(),p2.getNomeArtista(),p2.getNome(),p2.getNomeUtente());
		   daoP.doDeletep(p1.getId());
		   //daoP.doDeleteProd(p2.getNomeBrano(), p2.getNomeArtista());
	   }
	   
	   @Test
	    public void TestGetPlaylistExisting () throws Exception {
		 assertNotNull(daoP.doRetrieveByKey(p1.getNome()));
		 }

	   @Test
	    public void TestGetPlaylistNotExisting () throws Exception {
	        assertNull(daoP.doRetrieveByKey(p3.getNome()));
	    }
   
	   @Test
	    public void TestGetAllPlaylist () throws Exception {
	        assertNotNull(daoP.doRetrieveAll().size()>0);
	    }
	   
	   @Test
	    public void TestAddPlaylist() throws Exception {
	        assertEquals(true,daoP.doSave(p3));
	        assertEquals(true,daoP.doSave(p4));
	        daoP.doDelete(p4.getNomeBrano(), p4.getNomeArtista(), p4.getNome(), p4.getNomeUtente());
	        daoP.doDeletep(p3.getId());
	    }
	   
	    @Test
	    public void TestDeletePlaylistExisting ()  throws Exception{
	        assertEquals(true,daoP.doDelete(p2.getNomeBrano(),p2.getNomeArtista(),p2.getNome(),p2.getNomeUtente()));
	        assertEquals(true,daoP.doDeletep(p1.getId().intValue()));
	        daoP.doSave(p1);
	        daoP.doSave(p2);
	        p1 = daoP.doRetrieveByKey(p1.getNome());
	        assertNotEquals(-1,p1.getId().intValue());
	    }
	    
	    @Test
	    public void TestDeletePlaylistNotExisting () throws Exception {
	        assertEquals(false, daoP.doDelete(p4.getNomeBrano(),p4.getNomeArtista(),p4.getNome(),p4.getNomeUtente()));
	        assertEquals(false, daoP.doDeletep(p3.getId().intValue()));
	    }
	    
	   private AccountUtente creaUtenteRegistrato(String nickname,String pass) {
			AccountUtente x = new AccountUtente();
			x.setNickname(nickname);
			x.setPassword(pass);
			return x;
		}
	   
	   private Playlist creaPlaylist(int cod,String nomeUt,String nome,String nomeBrano,int numBrani,String nomeArtista) {
			Playlist x = new Playlist();
			x.setId(cod);
			x.setNomeUtente(nomeUt);
			x.setNome(nome);
			x.setNomeBrano(nomeBrano);
			x.setNumBrani(numBrani);
			x.setNomeArtista(nomeArtista);
			return x;
		}
}
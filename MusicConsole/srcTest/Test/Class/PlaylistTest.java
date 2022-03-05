package Test.Class;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import gestioneProdotti.Playlist;

public class PlaylistTest {
	
	@Test
	   public void getNomeUtente() {
	    Playlist u = new Playlist("m.rossi","Estate", "Vertigine", 10 ,"Elodie");
	    assertEquals("m.rossi", u.getNomeUtente());
	  }
	
	@Test
	  public void setNomeUtente() {
	    Playlist u = new Playlist("m.rossi","Estate", "Vertigine", 10 ,"Elodie");
	    u.setNomeUtente("p.bianchi");
	    assertEquals("p.bianchi", u.getNomeUtente());
	  }
	
	@Test
	   public void getNome() {
	    Playlist u = new Playlist("m.rossi","Estate", "Vertigine", 10 ,"Elodie");
	    assertEquals("Estate", u.getNome());
	  }
	
	@Test
	  public void setNome() {
	    Playlist u = new Playlist("m.rossi","Estate", "Vertigine", 10 ,"Elodie");
	    u.setNome("Inverno");
	    assertEquals("Inverno", u.getNome());
	  }
	
	@Test
	   public void getNomeBrano() {
	    Playlist u = new Playlist("m.rossi","Estate", "Vertigine", 10 ,"Elodie");
	    assertEquals("Vertigine", u.getNomeBrano());
	  }
	
	@Test
	  public void setNomeBrano() {
	    Playlist u = new Playlist("m.rossi","Estate", "Vertigine", 10 ,"Elodie");
	    u.setNomeBrano("Chimica");
	    assertEquals("Chimica", u.getNomeBrano());
	  }
	
	@Test
	   public void getNumBrani() {
	    Playlist u = new Playlist("m.rossi","Estate", "Vertigine", 10 ,"Elodie");
	    assertEquals(Integer.valueOf(10), u.getNumBrani());
	  }
	
	@Test
	  public void setNumBrani() {
	    Playlist u = new Playlist("m.rossi","Estate", "Vertigine", 10 ,"Elodie");
	    u.setNumBrani(15);
	    assertEquals(Integer.valueOf(15), u.getNumBrani());
	  }
	
	@Test
	   public void getNomeArtista() {
	    Playlist u = new Playlist("m.rossi","Estate", "Vertigine", 10 ,"Elodie");
	    assertEquals("Elodie", u.getNomeArtista());
	  }
	
	@Test
	  public void setNomeArtista() {
	    Playlist u = new Playlist("m.rossi","Estate", "Vertigine", 10 ,"Elodie");
	    u.setNomeArtista("Rettore");
	    assertEquals("Rettore", u.getNomeArtista());
	  }
	
	

}

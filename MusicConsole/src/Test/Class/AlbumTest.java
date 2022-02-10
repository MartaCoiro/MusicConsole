package Test.Class;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import Class.Album;

public class AlbumTest {

	Date dataa = new Date();
	java.sql.Date dat = new java.sql.Date(dataa.getTime());
	
	@Test
	  public void getPrezzoS() {
	    Album u = new Album(11, "Erba Cattiva", "img11.png", "Emis Killa","Album",10.2f,11.0f,2.1f,"molto bello");
	    assertEquals("10.2", String.valueOf(u.getPrezzoS()));
	}

	  @Test
	  public void setPrezzoS() {
	    Album u = new Album(11, "Erba Cattiva", "img11.png", "Emis Killa","Album",10.2f,11.0f,2.1f,"molto bello");
	    u.setPrezzoS(10.2f);
	    assertEquals("10.2", String.valueOf(u.getPrezzoS()));
	  }
	  
	  @Test
	  public void getPrezzoV() {
	    Album u = new Album(11, "Erba Cattiva", "img11.png", "Emis Killa","Album",10.2f,11.0f,2.1f,"molto bello");
	    assertEquals("11.0", String.valueOf(u.getPrezzoV()));
	}

	  @Test
	  public void setPrezzoV() {
	    Album u = new Album(11, "Erba Cattiva", "img11.png", "Emis Killa","Album",10.2f,11.0f,2.1f,"molto bello");
	    u.setPrezzoV(7.0f);
	    assertEquals("7.0", String.valueOf(u.getPrezzoV()));
	  }
	  
	  @Test
	  public void getPrezzoC() {
	    Album u = new Album(11, "Erba Cattiva", "img11.png", "Emis Killa","Album",10.2f,11.0f,2.1f,"molto bello");
	    assertEquals("2.1", String.valueOf(u.getPrezzoC()));
	}

	  @Test
	  public void setPrezzoC() {
	    Album u = new Album(11, "Erba Cattiva", "img11.png", "Emis Killa","Album",10.2f,11.0f,2.1f,"molto bello");
	    u.setPrezzoC(2.1f);
	    assertEquals("2.1", String.valueOf(u.getPrezzoC()));
	  }
	  
	  @Test
	  public void getDescrizione() {
		  Album u = new Album(11, "Erba Cattiva", "img11.png", "Emis Killa","Album",10.2f,11.0f,2.1f,"molto bello");
		  assertEquals("molto bello",u.getDescrizione());
	  }
	  
	  @Test
	  public void setDescrizione() {
		  Album u = new Album(11, "Erba Cattiva", "img11.png", "Emis Killa","Album",10.2f,11.0f,2.1f,"molto bello");
		  u.setDescrizione("bello");
		  assertEquals("bello",u.getDescrizione());
	  }
	  
	  @Test
	  public void getDat() {
		  assertEquals(dat,dat);
		}

	  @Test
		public void setDat() {
		  Date d = new Date();
		java.sql.Date datt = new java.sql.Date(d.getTime());
		  assertEquals(dat,datt);
		}
		
	  @Test
	  public void getTipo() {
		  Album u = new Album(11, "Erba Cattiva", "img11.png", "Emis Killa","Album",10.2f,11.0f,2.1f,"molto bello");
		  assertEquals("Album",u.getTipo());
	  }
	  
	  @Test
	  public void setTipo() {
		  Album u = new Album(11, "Erba Cattiva", "img11.png", "Emis Killa","Album",10.2f,11.0f,2.1f,"molto bello");
		  u.setTipo("Album");
		  assertEquals("Album",u.getTipo());
	  }
	  
	  @Test
	  public void getNartista() {
		  Album u = new Album(11, "Erba Cattiva", "img11.png", "Emis Killa","Album",10.2f,11.0f,2.1f,"molto bello");
		  assertEquals("Emis Killa",u.getNartista());
	  }
	  
	  @Test
	  public void setNartista() {
		  Album u = new Album(11, "Erba Cattiva", "img11.png", "Emis Killa","Album",10.2f,11.0f,2.1f,"molto bello");
		  u.setNartista("Laura Pausini");
		  assertEquals("Laura Pausini",u.getNartista());
	  }
	  
	  @Test
	  public void getImgAlbum() {
		  Album u = new Album(11, "Erba Cattiva", "img11.png", "Emis Killa","Album",10.2f,11.0f,2.1f,"molto bello");
		  assertEquals("img11.png",u.getImgAlbum());
	  }
	  
	  @Test
	  public void setImgAlbum() {
		  Album u = new Album(11, "Erba Cattiva", "img11.png", "Emis Killa","Album",10.2f,11.0f,2.1f,"molto bello");
		  u.setImgAlbum("img45.png");
		  assertEquals("img45.png",u.getImgAlbum());
	  }
	  
	  @Test
	  public void getCodiceAlbum() {
		  Album u = new Album(11, "Erba Cattiva", "img11.png", "Emis Killa","Album",10.2f,11.0f,2.1f,"molto bello");
		  assertEquals(Integer.valueOf(11),u.getCodiceAlbum());
	  }
	  
	  @Test
	  public void setCodiceAlbum() {
		  Album u = new Album(11, "Erba Cattiva", "img11.png", "Emis Killa","Album",10.2f,11.0f,2.1f,"molto bello");
		  u.setCodiceAlbum(84);
		  assertEquals(Integer.valueOf(84),u.getCodiceAlbum());
	  }
	  
	  @Test
	  public void getNomeAlbum() {
		  Album u = new Album(11, "Erba Cattiva", "img11.png", "Emis Killa","Album",10.2f,11.0f,2.1f,"molto bello");
		  assertEquals("Erba Cattiva",u.getNomeAlbum());
	  }
	  
	  @Test
	  public void setNomeAlbum() {
		  Album u = new Album(11, "Erba Cattiva", "img11.png", "Emis Killa","Album",10.2f,11.0f,2.1f,"molto bello");
		  u.setNomeAlbum("Vai");
		  assertEquals("Vai",u.getNomeAlbum());
	  }
		
	  
}

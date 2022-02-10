package Test.Class;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import Class.Podcast;

public class PodcastTest {
	
	Date dataa = new Date();
	java.sql.Date dat = new java.sql.Date(dataa.getTime());
	
	@Test
	   public void getNomePodcast() {
	    Podcast u = new Podcast("Rilassamento", "MarinaGalatioto","Podcast innovativo", 30.5f , 13, "img2.png", "Podcast",15.5f);
	    assertEquals("Rilassamento", u.getNomePodcast());
	  }
	
	@Test
	  public void setNomePodcast() {
	    Podcast u = new Podcast("Rilassamento","MarinaGalatioto","Podcast innovativo", 30.5f , 13, "img2.png", "Podcast",15.5f);
	    u.setNomePodcast("Mare");
	    assertEquals("Mare", u.getNomePodcast());
	  }
	
	@Test
	   public void getIdeatore() {
	    Podcast u = new Podcast("Rilassamento", "MarinaGalatioto","Podcast innovativo", 30.5f , 13, "img2.png", "Podcast",15.5f);
	    assertEquals("MarinaGalatioto", u.getIdeatore());
	  }
	
	@Test
	  public void setIdeatore() {
	    Podcast u = new Podcast("Rilassamento","MarinaGalatioto","Podcast innovativo", 30.5f , 13, "img2.png", "Podcast",15.5f);
	    u.setIdeatore("Mare");
	    assertEquals("Mare", u.getIdeatore());
	  }
	
	@Test
	   public void getDescrizione() {
	    Podcast u = new Podcast("Rilassamento", "MarinaGalatioto","Podcast innovativo", 30.5f , 13, "img2.png", "Podcast",15.5f);
	    assertEquals("Podcast innovativo", u.getDescrizione());
	  }
	
	@Test
	  public void setDescrizione() {
	    Podcast u = new Podcast("Rilassamento","MarinaGalatioto","Podcast innovativo", 30.5f , 13, "img2.png", "Podcast",15.5f);
	    u.setDescrizione("Podcast interessante");
	    assertEquals("Podcast interessante", u.getDescrizione());
	  }
	
	@Test
	   public void getDurata() {
	    Podcast u = new Podcast("Rilassamento","MarinaGalatioto","Podcast innovativo", 30.5f , 13, "img2.png", "Podcast",15.5f);
	    assertEquals("30.5",String.valueOf( u. getDurata()));
	  }
	
	@Test
	  public void setDurata() {
	    Podcast u = new Podcast("Rilassamento","MarinaGalatioto","Podcast innovativo", 30.5f , 13, "img2.png", "Podcast",15.5f);
	    u.setDurata(20.5f);
	    assertEquals("20.5",String.valueOf(u. getDurata()));
	  }
	
	@Test
	   public void getNumeroEpisodi() {
	    Podcast u = new Podcast("Rilassamento","MarinaGalatioto","Podcast innovativo", 30.5f , 13, "img2.png", "Podcast",15.5f);
	    assertEquals(Integer.valueOf(13), u.getNumeroEpisodi());
	  }
	
	@Test
	  public void setNumeroEpisodi() {
	    Podcast u = new Podcast("Rilassamento","MarinaGalatioto","Podcast innovativo", 30.5f , 13, "img2.png", "Podcast",15.5f);
	    u.setNumeroEpisodi(10);
	    assertEquals(Integer.valueOf(10), u.getNumeroEpisodi());
	  }
	
	@Test
	   public void getImgPod() {
	    Podcast u = new Podcast("Rilassamento", "MarinaGalatioto","Podcast innovativo", 30.5f , 13, "img2.png", "Podcast",15.5f);
	    assertEquals("img2.png", u.getImgPod());
	  }
	
	@Test
	  public void setImgPod() {
	    Podcast u = new Podcast("Rilassamento","MarinaGalatioto","Podcast innovativo", 30.5f , 13, "img2.png", "Podcast",15.5f);
	    u.setImgPod("img5.png");
	    assertEquals("img5.png", u.getImgPod());
	  }
	
	@Test
	   public void getTipo() {
	    Podcast u = new Podcast("Rilassamento", "MarinaGalatioto","Podcast innovativo", 30.5f , 13, "img2.png", "Podcast",15.5f);
	    assertEquals("Podcast", u.getTipo());
	  }
	
	@Test
	  public void setTipo() {
	    Podcast u = new Podcast("Rilassamento","MarinaGalatioto","Podcast innovativo", 30.5f , 13, "img2.png", "Podcast",15.5f);
	    u.setTipo("Podcast");
	    assertEquals("Podcast", u.getTipo());
	  }
	
	@Test
	   public void getPrezzo() {
	    Podcast u = new Podcast("Rilassamento","MarinaGalatioto","Podcast innovativo", 30.5f , 13, "img2.png", "Podcast",15.5f);
	    assertEquals("15.5",String.valueOf( u. getPrezzo()));
	  }
	
	@Test
	  public void setPrezzo() {
	    Podcast u = new Podcast("Rilassamento","MarinaGalatioto","Podcast innovativo", 30.5f , 13, "img2.png", "Podcast",15.5f);
	    u.setPrezzo(10.5f);
	    assertEquals("10.5",String.valueOf( u. getPrezzo()));
	  }

	
	@Test
	public void getDat() {
		assertEquals(dat,dat);
	}
	
	@Test
	public void setDat() {
		Date d = new Date();
		java.sql.Date datt = new java.sql.Date(d.getTime());
		assertEquals(dat, datt);
	}
}

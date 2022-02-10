package Test.Class;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import Class.Brano;

public class BranoTest {

	Date dataa = new Date();
	java.sql.Date dat = new java.sql.Date(dataa.getTime());
	
	@Test
	  public void getPrezzo() {
	    Brano u = new Brano(11, "Chimica", 3.1f, "Rettore", "img17.png", "pop","Brano","Chimica.mp3",10.1f,"forte");
	    assertEquals("10.1", String.valueOf(u.getPrezzo()));
	}

	  @Test
	  public void setPrezzo() {
		  Brano u = new Brano(11, "Chimica", 3.1f, "Rettore", "img17.png", "pop","Brano","Chimica.mp3",10.1f,"forte");
		  u.setPrezzo(10.2f);
		  assertEquals("10.2", String.valueOf(u.getPrezzo()));
	  }
	  
	  @Test
	  public void getDescrizione() {
		  Brano u = new Brano(11, "Chimica", 3.1f, "Rettore", "img17.png", "pop","Brano","Chimica.mp3",10.1f,"forte");
		  assertEquals("forte",u.getDescrizione());
	  }
	  
	  @Test
	  public void setDescrizione() {
		  Brano u = new Brano(11, "Chimica", 3.1f, "Rettore", "img17.png", "pop","Brano","Chimica.mp3",10.1f,"forte");
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
	  public void getSuono() {
		  Brano u = new Brano(11, "Chimica", 3.1f, "Rettore", "img17.png", "pop","Brano","Chimica.mp3",10.1f,"forte");
		  assertEquals("Chimica.mp3",u.getSuono());
	  }
	  
	  @Test
	  public void setSuono() {
		  Brano u = new Brano(11, "Chimica", 3.1f, "Rettore", "img17.png", "pop","Brano","Chimica.mp3",10.1f,"forte");
		  u.setSuono("CC.mp3");
		  assertEquals("CC.mp3",u.getSuono());
	  }
	  
	  @Test
	  public void getTipo() {
		  Brano u = new Brano(11, "Chimica", 3.1f, "Rettore", "img17.png", "pop","Brano","Chimica.mp3",10.1f,"forte");
		  assertEquals("Brano",u.getTipo());
	  }
	  
	  @Test
	  public void setTipo() {
		  Brano u = new Brano(11, "Chimica", 3.1f, "Rettore", "img17.png", "pop","Brano","Chimica.mp3",10.1f,"forte");
		  u.setTipo("Brano");
		  assertEquals("Brano",u.getTipo());
	  }
	  
	  @Test
	  public void getGenere() {
		  Brano u = new Brano(11, "Chimica", 3.1f, "Rettore", "img17.png", "pop","Brano","Chimica.mp3",10.1f,"forte");
		  assertEquals("pop",u.getGenere());
	  }
	  
	  @Test
	  public void setGenere() {
		  Brano u = new Brano(11, "Chimica", 3.1f, "Rettore", "img17.png", "pop","Brano","Chimica.mp3",10.1f,"forte");
		  u.setGenere("rock");
		  assertEquals("rock",u.getGenere());
	  }
	  
	  @Test
	  public void getImgBrano() {
		  Brano u = new Brano(11, "Chimica", 3.1f, "Rettore", "img17.png", "pop","Brano","Chimica.mp3",10.1f,"forte");
		  assertEquals("img17.png",u.getImgBrano());
	  }
	  
	  @Test
	  public void setImgBrano() {
		  Brano u = new Brano(11, "Chimica", 3.1f, "Rettore", "img17.png", "pop","Brano","Chimica.mp3",10.1f,"forte");
		  u.setImgBrano("img45.png");
		  assertEquals("img45.png",u.getImgBrano());
	  }
	  
	  @Test
	  public void getCantante() {
		  Brano u = new Brano(11, "Chimica", 3.1f, "Rettore", "img17.png", "pop","Brano","Chimica.mp3",10.1f,"forte");
		  assertEquals("Rettore",u.getCantante());
	  }
	  
	  @Test
	  public void setCantante() {
		  Brano u = new Brano(11, "Chimica", 3.1f, "Rettore", "img17.png", "pop","Brano","Chimica.mp3",10.1f,"forte");
		  u.setCantante("Rkomi");
		  assertEquals("Rkomi",u.getCantante());
	  }
	  
	  @Test
	  public void getCodice() {
		  Brano u = new Brano(11, "Chimica", 3.1f, "Rettore", "img17.png", "pop","Brano","Chimica.mp3",10.1f,"forte");
		  assertEquals(Integer.valueOf(11),u.getCodice());
	  }
	  
	  @Test
	  public void setCodice() {
		  Brano u = new Brano(11, "Chimica", 3.1f, "Rettore", "img17.png", "pop","Brano","Chimica.mp3",10.1f,"forte");
		  u.setCodice(84);
		  assertEquals(Integer.valueOf(84),u.getCodice());
	  }
	  
	  @Test
	  public void getTitolo() {
		  Brano u = new Brano(11, "Chimica", 3.1f, "Rettore", "img17.png", "pop","Brano","Chimica.mp3",10.1f,"forte");
		  assertEquals("Chimica",u.getTitolo());
	  }
	  
	  @Test
	  public void setTitolo() {
		  Brano u = new Brano(11, "Chimica", 3.1f, "Rettore", "img17.png", "pop","Brano","Chimica.mp3",10.1f,"forte");
		  u.setTitolo("Insuperabile");
		  assertEquals("Insuperabile",u.getTitolo());
	  }
	  
	  @Test
	  public void getDurata() {
	    Brano u = new Brano(11, "Chimica", 3.1f, "Rettore", "img17.png", "pop","Brano","Chimica.mp3",10.1f,"forte");
	    assertEquals("3.1", String.valueOf(u.getDurata()));
	}

	  @Test
	  public void setDurata() {
		  Brano u = new Brano(11, "Chimica", 3.1f, "Rettore", "img17.png", "pop","Brano","Chimica.mp3",10.1f,"forte");
		  u.setDurata(2.2f);
		  assertEquals("2.2", String.valueOf(u.getDurata()));
	  }
}

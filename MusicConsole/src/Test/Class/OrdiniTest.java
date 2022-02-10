package Test.Class;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import Class.Ordini;

public class OrdiniTest {
	
	Date dataa = new Date();
	java.sql.Date dat = new java.sql.Date(dataa.getTime());

	@Test
	  public void getQuantità() {
	    Ordini u = new Ordini(11, "false",1, "l.laura", "Chimica","Rettore","CD",10.1f,50.0f);
	    assertEquals(11, u.getQuantità());
	}

	  @Test
	  public void setQuantità() {
		  Ordini u = new Ordini(11, "false",1, "l.laura", "Chimica","Rettore","CD",10.1f,50.0f);
		  u.setQuantità(5);
		  assertEquals(5, u.getQuantità());
	  }
	  
	  @Test
	  public void getStato() {
		  Ordini u = new Ordini(11, "false",1, "l.laura", "Chimica","Rettore","CD",10.1f,50.0f);
		  assertEquals("false",u.getStato());
	  }
	  
	  @Test
	  public void setStato() {
		  Ordini u = new Ordini(11, "false",1, "l.laura", "Chimica","Rettore","CD",10.1f,50.0f);
		  u.setStato("true");
		  assertEquals("true",u.getStato());
	  }
	  
	  @Test
	  public void getTot() {
	    Ordini u = new Ordini(11, "false",1, "l.laura", "Chimica","Rettore","CD",10.1f,50.0f);
	    assertEquals("50.0", String.valueOf(u.getTot()));
	}

	  @Test
	  public void setTot() {
		  Ordini u = new Ordini(11, "false",1, "l.laura", "Chimica","Rettore","CD",10.1f,50.0f);
		  u.setTot(30.2f);
		  assertEquals("30.2", String.valueOf(u.getTot()));
	  }
	
	  @Test
	  public void getUtente() {
		  Ordini u = new Ordini(11, "false",1, "l.laura", "Chimica","Rettore","CD",10.1f,50.0f);
		  assertEquals("l.laura",u.getUtente());
	  }
	  
	  @Test
	  public void setUtente() {
		  Ordini u = new Ordini(11, "false",1, "l.laura", "Chimica","Rettore","CD",10.1f,50.0f);
		  u.setUtente("p.paola");
		  assertEquals("p.paola",u.getUtente());
	  }
	  
	  @Test
	  public void getNome() {
		  Ordini u = new Ordini(11, "false",1, "l.laura", "Chimica","Rettore","CD",10.1f,50.0f);
		  assertEquals("Chimica",u.getNome());
	  }
	  
	  @Test
	  public void setNome() {
		  Ordini u = new Ordini(11, "false",1, "l.laura", "Chimica","Rettore","CD",10.1f,50.0f);
		  u.setNome("Insuperabile");
		  assertEquals("Insuperabile",u.getNome());
	  }
	  
	  @Test
	  public void getAutore() {
		  Ordini u = new Ordini(11, "false",1, "l.laura", "Chimica","Rettore","CD",10.1f,50.0f);
		  assertEquals("Rettore",u.getAutore());
	  }
	  
	  @Test
	  public void setAutore() {
		  Ordini u = new Ordini(11, "false",1, "l.laura", "Chimica","Rettore","CD",10.1f,50.0f);
		  u.setAutore("Rkomi");
		  assertEquals("Rkomi",u.getAutore());
	  }
	  
	  @Test
	  public void getTipo() {
		  Ordini u = new Ordini(11, "false",1, "l.laura", "Chimica","Rettore","CD",10.1f,50.0f);
		  assertEquals("CD",u.getTipo());
	  }
	  
	  @Test
	  public void setTipo() {
		  Ordini u = new Ordini(11, "false",1, "l.laura", "Chimica","Rettore","CD",10.1f,50.0f);
		  u.setTipo("Vinile");
		  assertEquals("Vinile",u.getTipo());
	  }
	  
	@Test
	  public void getCosto() {
	    Ordini u = new Ordini(11, "false",1, "l.laura", "Chimica","Rettore","CD",10.1f,50.0f);
	    assertEquals("10.1", String.valueOf(u.getCosto()));
	}

	  @Test
	  public void setCosto() {
		  Ordini u = new Ordini(11, "false",1, "l.laura", "Chimica","Rettore","CD",10.1f,50.0f);
		  u.setCosto(5.2f);
		  assertEquals("5.2", String.valueOf(u.getCosto()));
	  }
	  
	  @Test
	  public void getIndice() {
	    Ordini u = new Ordini(11, "false",1, "l.laura", "Chimica","Rettore","CD",10.1f,50.0f);
	    assertEquals(Integer.valueOf(1), u.getIndice());
	}

	  @Test
	  public void setIndice() {
		  Ordini u = new Ordini(11, "false",1, "l.laura", "Chimica","Rettore","CD",10.1f,50.0f);
		  u.setIndice(3);
		  assertEquals(Integer.valueOf(3), u.getIndice());
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
}

package Test.Class;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import gestioneAccount.Profilo;

public class ProfiloTest {
	
	@Test
	   public void getNome() {
	    Profilo u = new Profilo("Mario","Rossi", "Roma", "Via dei fiori","1234567898","m.rossi@studenti.unisa.it","m.rossi","2:02:44e9f86136f9b41ce62a1d2605e79ac4be5d5793dac00302553500d1dff4af65d2baa89503990c2114a9b95184");
	    assertEquals("Mario", u.getNome());
	  }
	
	@Test
	  public void setNome() {
	    Profilo u = new Profilo("Mario","Rossi", "Roma", "Via dei fiori","1234567898","m.rossi@studenti.unisa.it","m.rossi","2:02:44e9f86136f9b41ce62a1d2605e79ac4be5d5793dac00302553500d1dff4af65d2baa89503990c2114a9b95184");
	    u.setNome("Laura");
	    assertEquals("Laura", u.getNome());
	  }
	
	@Test
	   public void getCognome() {
	    Profilo u = new Profilo("Mario","Rossi", "Roma", "Via dei fiori","1234567898","m.rossi@studenti.unisa.it","m.rossi","2:02:44e9f86136f9b41ce62a1d2605e79ac4be5d5793dac00302553500d1dff4af65d2baa89503990c2114a9b95184");
	    assertEquals("Rossi", u.getCognome());
	  }
	
	@Test
	  public void setCognome() {
	    Profilo u = new Profilo("Mario","Rossi", "Roma", "Via dei fiori","1234567898","m.rossi@studenti.unisa.it","m.rossi","2:02:44e9f86136f9b41ce62a1d2605e79ac4be5d5793dac00302553500d1dff4af65d2baa89503990c2114a9b95184");
	    u.setCognome("Bianchi");
	    assertEquals("Bianchi", u.getCognome());
	  }
	
	@Test
	   public void getCitta() {
	    Profilo u = new Profilo("Mario","Rossi", "Roma", "Via dei fiori","1234567898","m.rossi@studenti.unisa.it","m.rossi","2:02:44e9f86136f9b41ce62a1d2605e79ac4be5d5793dac00302553500d1dff4af65d2baa89503990c2114a9b95184");
	    assertEquals("Roma", u.getCitta());
	  }
	
	@Test
	  public void setCitta() {
	    Profilo u = new Profilo("Mario","Rossi", "Roma", "Via dei fiori","1234567898","m.rossi@studenti.unisa.it","m.rossi","2:02:44e9f86136f9b41ce62a1d2605e79ac4be5d5793dac00302553500d1dff4af65d2baa89503990c2114a9b95184");
	    u.setCitta("Torino");
	    assertEquals("Torino", u.getCitta());
	  }
	
	@Test
	   public void getIndirizzo() {
	    Profilo u = new Profilo("Mario","Rossi", "Roma", "Via dei fiori","1234567898","m.rossi@studenti.unisa.it","m.rossi","2:02:44e9f86136f9b41ce62a1d2605e79ac4be5d5793dac00302553500d1dff4af65d2baa89503990c2114a9b95184");
	    assertEquals("Via dei fiori", u.getIndirizzo());
	  }
	
	@Test
	  public void setIndirizzo() {
	    Profilo u = new Profilo("Mario","Rossi", "Roma", "Via dei fiori","1234567898","m.rossi@studenti.unisa.it","m.rossi","2:02:44e9f86136f9b41ce62a1d2605e79ac4be5d5793dac00302553500d1dff4af65d2baa89503990c2114a9b95184");
	    u.setIndirizzo("Via delle fontane");
	    assertEquals("Via delle fontane", u.getIndirizzo());
	  }
	
	@Test
	   public void getTelefono() {
	    Profilo u = new Profilo("Mario","Rossi", "Roma", "Via dei fiori","1234567898","m.rossi@studenti.unisa.it","m.rossi","2:02:44e9f86136f9b41ce62a1d2605e79ac4be5d5793dac00302553500d1dff4af65d2baa89503990c2114a9b95184");
	    assertEquals("1234567898", u.getTelefono());
	  }
	
	@Test
	  public void setTelefono() {
	    Profilo u = new Profilo("Mario","Rossi", "Roma", "Via dei fiori","1234567898","m.rossi@studenti.unisa.it","m.rossi","2:02:44e9f86136f9b41ce62a1d2605e79ac4be5d5793dac00302553500d1dff4af65d2baa89503990c2114a9b95184");
	    u.setTelefono("1234567894");
	    assertEquals("1234567894", u.getTelefono());
	  }
	
	@Test
	   public void getEmail() {
	    Profilo u = new Profilo("Mario","Rossi", "Roma", "Via dei fiori","1234567898","m.rossi@studenti.unisa.it","m.rossi","2:02:44e9f86136f9b41ce62a1d2605e79ac4be5d5793dac00302553500d1dff4af65d2baa89503990c2114a9b95184");
	    assertEquals("m.rossi@studenti.unisa.it", u.getEmail());
	  }
	
	@Test
	  public void setEmail() {
	    Profilo u = new Profilo("Mario","Rossi", "Roma", "Via dei fiori","1234567898","m.rossi@studenti.unisa.it","m.rossi","2:02:44e9f86136f9b41ce62a1d2605e79ac4be5d5793dac00302553500d1dff4af65d2baa89503990c2114a9b95184");
	    u.setEmail("l.bianchi@studenti.unisa.it");
	    assertEquals("l.bianchi@studenti.unisa.it", u.getEmail());
	  }
	
	@Test
	   public void getUsername() {
	    Profilo u = new Profilo("Mario","Rossi", "Roma", "Via dei fiori","1234567898","m.rossi@studenti.unisa.it","m.rossi","2:02:44e9f86136f9b41ce62a1d2605e79ac4be5d5793dac00302553500d1dff4af65d2baa89503990c2114a9b95184");
	    assertEquals("m.rossi", u.getUsername());
	  }
	
	@Test
	  public void setUsername() {
	    Profilo u = new Profilo("Mario","Rossi", "Roma", "Via dei fiori","1234567898","m.rossi@studenti.unisa.it","m.rossi","2:02:44e9f86136f9b41ce62a1d2605e79ac4be5d5793dac00302553500d1dff4af65d2baa89503990c2114a9b95184");
	    u.setUsername("l.bianchi");
	    assertEquals("l.bianchi", u.getUsername());
	  }
	
	@Test
	  public void getPassword() {
	    Profilo u = new Profilo("Mario","Rossi", "Roma", "Via dei fiori","1234567898","m.rossi@studenti.unisa.it","m.rossi","2:02:44e9f86136f9b41ce62a1d2605e79ac4be5d5793dac00302553500d1dff4af65d2baa89503990c2114a9b95184");
	    assertEquals("2:02:44e9f86136f9b41ce62a1d2605e79ac4be5d5793dac00302553500d1dff4af65d2baa89503990c2114a9b95184", u.getPassword());
	  }
	
	@Test
	  public void setPassword() {
	    Profilo u = new Profilo("Mario","Rossi", "Roma", "Via dei fiori","1234567898","m.rossi@studenti.unisa.it","m.rossi","2:02:44e9f86136f9b41ce62a1d2605e79ac4be5d5793dac00302553500d1dff4af65d2baa89503990c2114a9b95184");
	    u.setPassword("password2");
	    assertEquals("password2", u. getPassword());
	  }
	
	

}

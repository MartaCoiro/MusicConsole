package Test.Class;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import gestioneAccount.AccountUtente;
import gestioneAccount.Amm;

public class AmmTest {

	 @Test
	  public void getRuolo() {
		  Amm u = new Amm("p.paolo","2:02:44e9f86136f9b41ce62a1d2605e79ac4be5d5793dac00302553500d1dff4af65d2baa89503990c2114a9b95184","gestore ordini");
		  assertEquals("gestore ordini",u.getRuolo());
	  }
	  
	  @Test
	  public void setRuolo() {
		  Amm u = new Amm("p.paolo","2:02:44e9f86136f9b41ce62a1d2605e79ac4be5d5793dac00302553500d1dff4af65d2baa89503990c2114a9b95184","gestore ordini");
		  u.setRuolo("gestore catalogo");
		  assertEquals("gestore catalogo",u.getRuolo());
	  }
	  
	  @Test
	  public void getUtente() {
		  Amm u = new Amm("p.paolo","2:02:44e9f86136f9b41ce62a1d2605e79ac4be5d5793dac00302553500d1dff4af65d2baa89503990c2114a9b95184","gestore ordini");
		  assertEquals("p.paolo", u.getUtente());
	  }

	  @Test
	  public void setUtente() {
		  Amm u = new Amm("p.paolo","2:02:44e9f86136f9b41ce62a1d2605e79ac4be5d5793dac00302553500d1dff4af65d2baa89503990c2114a9b95184","gestore ordini");
		  u.setUtente("k.buonocore");
		  assertEquals("k.buonocore", u.getUtente());
	  }
	  
	  @Test
	  public void getPassword() {
		  Amm u = new Amm("p.paolo","2:02:44e9f86136f9b41ce62a1d2605e79ac4be5d5793dac00302553500d1dff4af65d2baa89503990c2114a9b95184","gestore ordini");
		  assertEquals("2:02:44e9f86136f9b41ce62a1d2605e79ac4be5d5793dac00302553500d1dff4af65d2baa89503990c2114a9b95184", u.getPassword());
	  }

	  @Test
	  public void setPassword() {
		  Amm u = new Amm("p.paolo","2:02:44e9f86136f9b41ce62a1d2605e79ac4be5d5793dac00302553500d1dff4af65d2baa89503990c2114a9b95184","gestore ordini");
		  u.setPassword("password2");
		  assertEquals("password2", u.getPassword());
	  }
}

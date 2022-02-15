package Test.Class;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import gestioneAccount.AccountUtente;

public class UtenteTest {

	@Test
	  public void getNickname() {
	    AccountUtente u = new AccountUtente("Biagio", "2:02:44e9f86136f9b41ce62a1d2605e79ac4be5d5793dac00302553500d1dff4af65d2baa89503990c2114a9b95184");
	    assertEquals("Biagio", u.getNickname());
	  }

	  @Test
	  public void setNickname() {
	    AccountUtente u = new AccountUtente( "Biagio", "2:02:44e9f86136f9b41ce62a1d2605e79ac4be5d5793dac00302553500d1dff4af65d2baa89503990c2114a9b95184");
	    u.setNickname("Katia");
	    assertEquals("Katia", u.getNickname());
	  }
	  
	  @Test
	  public void getPassword() {
	    AccountUtente u = new AccountUtente("Biagio", "2:02:44e9f86136f9b41ce62a1d2605e79ac4be5d5793dac00302553500d1dff4af65d2baa89503990c2114a9b95184");
	    assertEquals("2:02:44e9f86136f9b41ce62a1d2605e79ac4be5d5793dac00302553500d1dff4af65d2baa89503990c2114a9b95184", u.getPassword());
	  }

	  @Test
	  public void setPassword() {
	    AccountUtente u = new AccountUtente("Biagio", "2:02:44e9f86136f9b41ce62a1d2605e79ac4be5d5793dac00302553500d1dff4af65d2baa89503990c2114a9b95184");
	    u.setPassword("password2");
	    assertEquals("password2", u.getPassword());
	  }
}

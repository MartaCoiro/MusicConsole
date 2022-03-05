package Test.Class;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import gestioneCarrello.Carrello;

public class CarrelloTest {

	@Test
	public void getTotq() {
		Carrello c=new Carrello(40.0f, 2, "rita", "Farfalle", "SanGiovanni", "Brano", 20.0f);
		assertEquals("40.0", String.valueOf(c.getTotq()));
	}

	@Test
	public void setTotq() {
		Carrello c=new Carrello(40.0f, 2, "rita", "Farfalle", "SanGiovanni", "Brano", 20.0f);
		c.setTotq(20.02f);
		assertEquals("20.02", String.valueOf(c.getTotq()));
	}

	@Test
	public void getQuantità() {
		Carrello c=new Carrello(40.0f, 2, "rita", "Farfalle", "SanGiovanni", "Brano", 20.0f);
		assertEquals(2, c.getQuantità());
	}

	@Test
	public void setQuantità() {
		Carrello c=new Carrello(40.0f, 2, "rita", "Farfalle", "SanGiovanni", "Brano", 20.0f);
		c.setQuantità(1);
		assertEquals(1, c.getQuantità());
	}
	
	@Test
	public void getUtente() {
		Carrello c=new Carrello(40.0f, 2, "rita", "Farfalle", "SanGiovanni", "Brano", 20.0f);
		assertEquals("rita", c.getUtente());
	}

	@Test
	public void setUtente() {
		Carrello c=new Carrello(40.0f, 2, "rita", "Farfalle", "SanGiovanni", "Brano", 20.0f);
		c.setUtente("gnappy");
		assertEquals("gnappy", c.getUtente());
	}

	@Test
	public void getNome() {
		Carrello c=new Carrello(40.0f, 2, "rita", "Farfalle", "SanGiovanni", "Brano", 20.0f);
		assertEquals("Farfalle", c.getNome());
	}

	@Test
	public void setNome() {
		Carrello c=new Carrello(40.0f, 2, "rita", "Farfalle", "SanGiovanni", "Brano", 20.0f);
		c.setNome("Insuperabile");
		assertEquals("Insuperabile", c.getNome());
	}

	@Test
	public void getAutore() {
		Carrello c=new Carrello(40.0f, 2, "rita", "Farfalle", "SanGiovanni", "Brano", 20.0f);
		assertEquals("SanGiovanni", c.getAutore());
	}

	@Test
	public void setAutore() {
		Carrello c=new Carrello(40.0f, 2, "rita", "Farfalle", "SanGiovanni", "Brano", 20.0f);
		c.setAutore("Rkomi");
		assertEquals("Rkomi", c.getAutore());
	}

	@Test
	public void getTipo() {
		Carrello c=new Carrello(40.0f, 2, "rita", "Farfalle", "SanGiovanni", "Brano", 20.0f);
		assertEquals("Brano", c.getTipo());
	}
	
	@Test
	public void setTipo() {
		Carrello c=new Carrello(40.0f, 2, "rita", "Farfalle", "SanGiovanni", "Brano", 20.0f);
		c.setTipo("Album");
		assertEquals("Album", c.getTipo());
	}

	@Test
	public void getCosto() {
		Carrello c=new Carrello(40.0f, 2, "rita", "Farfalle", "SanGiovanni", "Brano", 20.0f);
		assertEquals("20.0", String.valueOf(c.getCosto()));
	}

	@Test
	public void setCosto() {
		Carrello c=new Carrello(40.0f, 2, "rita", "Farfalle", "SanGiovanni", "Brano", 20.0f);
		c.setCosto(20.01f);
		assertEquals("20.01", String.valueOf(c.getCosto()));
	}
}

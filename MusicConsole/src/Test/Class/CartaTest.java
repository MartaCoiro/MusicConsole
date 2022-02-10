package Test.Class;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Class.Carta;

public class CartaTest {

	@Test
	public void getCvv() {
		Carta c=new Carta("12/02/2025", 247, "Biagio", "Cuccaro", "2222-4444-5555-6666", "biagio");
		assertEquals(247, c.getCvv());
	}
	
	@Test
	public void setCvv() {
		Carta c=new Carta("12/02/2025", 247, "Biagio", "Cuccaro", "2222-4444-5555-6666", "biagio");
		c.setCvv(147);
		assertEquals(147, c.getCvv());
	}

	@Test
	public void getUtente() {
		Carta c=new Carta("19/02/2023", 111, "Giuseppe", "Sarno", "1111-4444-8888-9999", "giuseppe");
		assertEquals("giuseppe", c.getUtente());
	}
	
	@Test
	public void setUtente() {
		Carta c=new Carta("19/02/2023", 111, "Giuseppe", "Sarno", "1111-4444-8888-9999", "giuseppe");
		c.setUtente("biagio");
		assertEquals("biagio", c.getUtente());
	}
	
	@Test
	public void getNome() {
		Carta c=new Carta("12/12/2030", 147, "Annapia", "Attanasio", "1234-5678-9112-3333", "annapia");
		assertEquals("Annapia", c.getNome());
	}
	
	@Test
	public void setNome() {
		Carta c=new Carta("12/12/2030", 147, "Annapia", "Attanasio", "1234-5678-9112-3333", "annapia");
		c.setNome("biagio");
		assertEquals("biagio", c.getNome());
	}
	
	@Test
	public void getCognome() {
		Carta c=new Carta("01/09/2026", 456, "Rosanna", "De Prisco", "2452-4774-5965-6106", "rosanna");
		assertEquals("De Prisco", c.getCognome());
	}
	
	@Test
	public void setCognome() {
		Carta c=new Carta("01/09/2026", 456, "Rosanna", "De Prisco", "2452-4774-5965-6106", "rosanna");
		c.setCognome("Bianchi");
		assertEquals("Bianchi", c.getCognome());
	}
	
	@Test
	public void getNumero() {
		Carta c=new Carta("17/04/2028", 123, "Rita", "Cuccaro", "1221-7896-5455-6246", "rita");
		assertEquals("1221-7896-5455-6246", c.getNumero());
	}
	
	@Test
	public void setNumero() {
		Carta c=new Carta("17/04/2028", 123, "Rita", "Cuccaro", "1221-7896-5455-6246", "rita");
		c.setNumero("1221-7896-5455-6247");
		assertEquals("1221-7896-5455-6247", c.getNumero());
	}
	
	@Test
	public void getDataa() {
		Carta c=new Carta("22/08/2024", 192, "Domenico", "Pepe", "9999-8888-7777-6666", "mimmo");
		assertEquals("22/08/2024", c.getDataa());
	}
	
	@Test
	public void setDataa() {
		Carta c=new Carta("22/08/2024", 192, "Domenico", "Pepe", "9999-8888-7777-6666", "mimmo");
		c.setDataa("22/08/2025");
		assertEquals("22/08/2025", c.getDataa());
	}
}

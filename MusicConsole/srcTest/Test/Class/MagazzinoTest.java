package Test.Class;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import gestioneProdotti.Magazzino;

public class MagazzinoTest {

	@Test
	public void getQuantità() {
		Magazzino m=new Magazzino(600, "Brividi", "Mahmood e Blanco", "Brano", 7.02f);
		assertEquals(600, m.getQuantità());
	}
	
	@Test
	public void setQuantità() {
		Magazzino m=new Magazzino(600, "Brividi", "Mahmood e Blanco", "Brano", 7.02f);
		m.setQuantità(300);
		assertEquals(300, m.getQuantità());
	}
	
	@Test
	public void getNome() {
		Magazzino m=new Magazzino(600, "Brividi", "Mahmood e Blanco", "Brano", 7.02f);
		assertEquals("Brividi", m.getNome());
	}
	
	@Test
	public void setNome() {
		Magazzino m=new Magazzino(600, "Brividi", "Mahmood e Blanco", "Brano", 7.02f);
		m.setNome("Soldi");
		assertEquals("Soldi", m.getNome());
	}
	
	@Test
	public void getAutore() {
		Magazzino m=new Magazzino(600, "Brividi", "Mahmood e Blanco", "Brano", 7.02f);
		assertEquals("Mahmood e Blanco", m.getAutore());
	}
	
	@Test
	public void setAutore() {
		Magazzino m=new Magazzino(600, "Brividi", "Mahmood e Blanco", "Brano", 7.02f);
		m.setAutore("Emis Killa");
		assertEquals("Emis Killa", m.getAutore());
	}
	
	@Test
	public void getTipo() {
		Magazzino m=new Magazzino(600, "Brividi", "Mahmood e Blanco", "Brano", 7.02f);
		assertEquals("Brano", m.getTipo());
	}
	
	@Test
	public void setTipo() {
		Magazzino m=new Magazzino(600, "Brividi", "Mahmood e Blanco", "Brano", 7.02f);
		m.setTipo("Album");
		assertEquals("Album", m.getTipo());
	}
	
	@Test
	public void getCosto() {
		Magazzino m=new Magazzino(600, "Brividi", "Mahmood e Blanco", "Brano", 7.02f);
		assertEquals("7.02", String.valueOf(m.getCosto()));
	}
	
	@Test
	public void setCosto() {
		Magazzino m=new Magazzino(600, "Brividi", "Mahmood e Blanco", "Brano", 7.02f);
		m.setCosto(8.05f);
		assertEquals("8.05", String.valueOf(m.getCosto()));
	}
}

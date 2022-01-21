package it.unisa.model;

import java.io.Serializable;

public class GenereMusicale implements Serializable {

	private static final long serialVersionUID = 1L;
	//valori della tabella
	Integer idGenere;
	String categoria;
	
	public GenereMusicale() { 
		idGenere = 0;
		categoria = "";
		}
	
	public Integer getIdGenere() {
		return idGenere;
	}
	
	public void setIdGenere(Integer idGenere) {
		this.idGenere = idGenere;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public void print() {
		System.out.printf("%20d | %10s\n", idGenere,categoria);
	}

	@Override
	public String toString() {
		return "genereMusicale(" + idGenere + "):" + categoria;
	}
	
	@Override  //ci dice se ci sono errori
	public boolean equals(Object other) { //restituisce true se vero
		return this.getCategoria() == ((GenereMusicale) other).getCategoria();
	}
	
}

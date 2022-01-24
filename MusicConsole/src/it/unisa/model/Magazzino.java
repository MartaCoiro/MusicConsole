package it.unisa.model;

import java.util.Date;

public class Magazzino {

	private static final long serialVersionUID = 1L;
	int quantit�;
	Integer cod;
	String nome;
	String autore;
	String tipo;
	float costo;
	
	public Magazzino() {
		quantit� = 0;
		cod = 0;
		nome = "";
		autore = "";
		tipo= "";
		costo = 0.0f;
	}

	public int getQuantit�() {
		return quantit�;
	}

	public void setQuantit�(int quantit�) {
		this.quantit� = quantit�;
	}

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}
	
	
}

package it.unisa.model;

import java.io.Serializable;

public class Carrello implements Serializable {

	private static final long serialVersionUID = 1L;
	float totq;
	int quantità;
	Integer cod;
	String utente;
	String nome;
	String autore;
	String tipo;
	float costo;

	public Carrello() {
		totq = 0.0f;
		quantità = 0;
		cod = 0;
		utente = "";
		nome = "";
		autore = "";
		tipo= "";
		costo = 0.0f;
		}
	
	public float getTotq() {
		return totq;
	}

	public void setTotq(float totq) {
		this.totq = totq;
	}

	public int getQuantità() {
		return quantità;
	}

	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}
	
	public String getUtente() {
		return utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
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

	public Float getCosto() {
		return costo;
	}

	public void setCosto(Float costo) {
		this.costo = costo;
	}

	public void print() {
		System.out.printf("%10d | %20s | %20s | %10s | %10.2f | %20s \n", cod,nome,autore,tipo,costo,utente);
	}
	
	@Override
	public String toString() {
		return cod + "," +  nome + "," + autore + "," + tipo + "," + costo + "," + utente;
	}
	
	@Override  //ci dice se ci sono errori
	public boolean equals(Object other) { //restituisce true se vero
		return this.getNome() == ((Carrello) other).getNome();
	}
	
}


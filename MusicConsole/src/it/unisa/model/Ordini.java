package it.unisa.model;

import java.io.Serializable;
import java.util.Date;

public class Ordini {

	private static final long serialVersionUID = 1L;
	int quantità;
	String stato;
	Integer cod;
	Integer indice;
	String utente;
	String nome;
	String autore;
	String tipo;
	float costo;
	float tot;
	Date data = new Date();
	java.sql.Date dat = new java.sql.Date(data.getTime());

	public Ordini() {
		quantità = 0;
		stato = "";
		cod = 0;
		indice = 0;
		utente = "";
		nome = "";
		autore = "";
		tipo= "";
		costo = 0.0f;
		tot = 0.0f;
		}

	public int getQuantità() {
		return quantità;
	}

	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public float getTot() {
		return tot;
	}

	public void setTot(float tot) {
		this.tot = tot;
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

	public void print() {
		System.out.printf("%10d | %20s | %20s | %10s | %10.2f | %20s \n", cod,nome,autore,tipo,costo,utente);
	}
	
	public Integer getIndice() {
		return indice;
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}

	public java.sql.Date getData() {
		return dat;
	}

	public void setData(java.sql.Date dat) {
		this.dat = dat;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	@Override
	public String toString() {
		return cod + "," +  nome + "," + autore + "," + tipo + "," + costo + "," + utente;
	}
	
	@Override  //ci dice se ci sono errori
	public boolean equals(Object other) { //restituisce true se vero
		return this.getIndice() == ((Ordini) other).getIndice();
	}
}

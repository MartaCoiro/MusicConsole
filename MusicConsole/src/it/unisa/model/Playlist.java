package it.unisa.model;

import java.io.Serializable;

public class Playlist implements Serializable{

	private static final long serialVersionUID = 1L;
	Integer id ;
	String nomeUtente;
	String nome;
	String nomeBrano;
	Integer numBrani;
	String nomeArtista;
	
	public Playlist() {
		id = 0;
		nomeUtente = "";
		nome = "";
		nomeBrano= "";
		numBrani = 0;
		nomeArtista = "";
		}

	public String getNomeArtista() {
		return nomeArtista;
	}


	public void setNomeArtista(String nomeArtista) {
		this.nomeArtista = nomeArtista;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeUtente() {
		return nomeUtente;
	}


	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}


	public String getNomeBrano() {
		return nomeBrano;
	}


	public void setNomeBrano(String nomeBrano) {
		this.nomeBrano = nomeBrano;
	}


	public Integer getNumBrani() {
		return numBrani;
	}

	public void setNumBrani(Integer numbrani) {
		this.numBrani = numbrani;
	}
	
	public void print() {
		System.out.printf("%20s | %20s | %10d | %50s | %20s \n", nomeUtente,nome,numBrani,nomeBrano,nomeArtista);
	}
	
	@Override
	public String toString() {
		return nomeUtente + "," +  nome + "," + numBrani + "," + nomeBrano + "," + nomeArtista;
	}
	
	@Override  //ci dice se ci sono errori
	public boolean equals(Object other) { //restituisce true se vero
		return this.getNome() == ((Playlist) other).getNome();
	}
	
	
}

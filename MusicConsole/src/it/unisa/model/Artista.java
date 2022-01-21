package it.unisa.model;

import java.io.Serializable;

public class Artista implements Serializable {

	private static final long serialVersionUID = 1L;
	//valori della tabella
	String NomeArtista;
    String descrizione;
    String offerta; 
    String IdCollaborazioni;
    Integer NumeroAscoltatori;

	public Artista() { 
			
		NomeArtista = "";
	    descrizione = "";
	    offerta = ""; 
	    IdCollaborazioni = "";
	    NumeroAscoltatori = 0;
		}

	public String getNomeArtista() {
		return NomeArtista;
	}

	public void setNomeArtista(String nomeArtista) {
		NomeArtista = nomeArtista;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getOfferta() {
		return offerta;
	}

	public void setOfferta(String offerta) {
		this.offerta = offerta;
	}

	public String getIdCollaborazioni() {
		return IdCollaborazioni;
	}

	public void setIdCollaborazioni(String idCollaborazioni) {
		IdCollaborazioni = idCollaborazioni;
	}

	public Integer getNumeroAscoltatori() {
		return NumeroAscoltatori;
	}

	public void setNumeroAscoltatori(Integer numeroAscoltatori) {
		NumeroAscoltatori = numeroAscoltatori;
	}
	
	public void print() {
		System.out.printf("%20s | %70s | %10s | %50s | %10d\n", NomeArtista,descrizione,offerta,IdCollaborazioni,NumeroAscoltatori);
	}

	@Override
	public String toString() {
		return "Artista(" + NomeArtista + "):" + descrizione + " " + offerta + " " + IdCollaborazioni + " "+ NumeroAscoltatori;
	}
	
	@Override  //ci dice se ci sono errori
	public boolean equals(Object other) { //restituisce true se vero
		return this.getNomeArtista() == ((Artista) other).getNomeArtista();
	}
}

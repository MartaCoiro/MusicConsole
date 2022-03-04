package gestioneCarrello;

import java.io.Serializable;
import java.util.Date;

public class Carta implements Serializable{

	private static final long serialVersionUID = 1L;
	//valori della tabella
	int cvv;
	String utente;
	String nome;
	String cognome;
	String numero;
	String dataa;

	public Carta(String dataa, int cvv, String nome, String cognome, String numero, String utente) { 
		this.dataa = dataa;
		this.cvv = cvv;
		this.nome = nome;
		this.cognome = cognome;
		this.numero = numero;
		this.utente = utente;
		}
	
	public Carta() { 
		dataa = "";
		cvv = 0;
		nome = "";
		cognome = "";
		numero = "";
		utente = "";
		
		}
	
	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public String getUtente() {
		return utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	
	public String getDataa() {
		return dataa;
	}

	public void setDataa(String dataa) {
		this.dataa = dataa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + cvv;
		result = prime * result + ((dataa == null) ? 0 : dataa.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((utente == null) ? 0 : utente.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Carta))
			return false;
		Carta other = (Carta) obj;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (cvv != other.cvv)
			return false;
		if (dataa == null) {
			if (other.dataa != null)
				return false;
		} else if (!dataa.equals(other.dataa))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (utente == null) {
			if (other.utente != null)
				return false;
		} else if (!utente.equals(other.utente))
			return false;
		return true;
	}	
	
}

package gestioneCarrello;

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

	public Carrello(float totq, int quantità, String utente, String nome, String autore, String tipo, float costo) {
		this.totq = totq;
		this.quantità = quantità;
		this.utente = utente;
		this.nome = nome;
		this.autore = autore;
		this.tipo= tipo;
		this.costo = costo;
		}
	
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autore == null) ? 0 : autore.hashCode());
		result = prime * result + ((cod == null) ? 0 : cod.hashCode());
		result = prime * result + Float.floatToIntBits(costo);
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + quantità;
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + Float.floatToIntBits(totq);
		result = prime * result + ((utente == null) ? 0 : utente.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Carrello))
			return false;
		Carrello other = (Carrello) obj;
		if (autore == null) {
			if (other.autore != null)
				return false;
		} else if (!autore.equals(other.autore))
			return false;
		if (cod == null) {
			if (other.cod != null)
				return false;
		} else if (!cod.equals(other.cod))
			return false;
		if (Float.floatToIntBits(costo) != Float.floatToIntBits(other.costo))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (quantità != other.quantità)
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		if (Float.floatToIntBits(totq) != Float.floatToIntBits(other.totq))
			return false;
		if (utente == null) {
			if (other.utente != null)
				return false;
		} else if (!utente.equals(other.utente))
			return false;
		return true;
	}
	
	
	
}


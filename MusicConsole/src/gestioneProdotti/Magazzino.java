package gestioneProdotti;

import java.util.Date;

public class Magazzino {

	private static final long serialVersionUID = 1L;
	int quantità;
	Integer cod;
	String nome;
	String autore;
	String tipo;
	float costo;
	
	public Magazzino(int quantità, String nome, String autore, String tipo, float costo) {
		this.quantità = quantità;
		this.nome = nome;
		this.autore = autore;
		this.tipo= tipo;
		this.costo = costo;
	}
	
	public Magazzino() {
		quantità = 0;
		cod = 0;
		nome = "";
		autore = "";
		tipo= "";
		costo = 0.0f;
	}


	public int getQuantità() {
		return quantità;
	}

	public void setQuantità(int quantità) {
		this.quantità = quantità;
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
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Magazzino))
			return false;
		Magazzino other = (Magazzino) obj;
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
		return true;
	}
	
	
	
}

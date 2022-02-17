package gestioneAcquisti;

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

	public Ordini(int quantità,String stato,int indice,String utente,String nome,String autore,String tipo,float costo,float tot) {
		this.quantità = quantità;
		this.stato = stato;
		this.indice = indice;
		this.utente = utente;
		this.nome = nome;
		this.autore = autore;
		this.tipo= tipo;
		this.costo = costo;
		this.tot = tot;
		}

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autore == null) ? 0 : autore.hashCode());
		result = prime * result + ((cod == null) ? 0 : cod.hashCode());
		result = prime * result + Float.floatToIntBits(costo);
		result = prime * result + ((dat == null) ? 0 : dat.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((indice == null) ? 0 : indice.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + quantità;
		result = prime * result + ((stato == null) ? 0 : stato.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + Float.floatToIntBits(tot);
		result = prime * result + ((utente == null) ? 0 : utente.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Ordini))
			return false;
		Ordini other = (Ordini) obj;
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
		if (dat == null) {
			if (other.dat != null)
				return false;
		} else if (!dat.equals(other.dat))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (indice == null) {
			if (other.indice != null)
				return false;
		} else if (!indice.equals(other.indice))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (quantità != other.quantità)
			return false;
		if (stato == null) {
			if (other.stato != null)
				return false;
		} else if (!stato.equals(other.stato))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		if (Float.floatToIntBits(tot) != Float.floatToIntBits(other.tot))
			return false;
		if (utente == null) {
			if (other.utente != null)
				return false;
		} else if (!utente.equals(other.utente))
			return false;
		return true;
	}

	
}

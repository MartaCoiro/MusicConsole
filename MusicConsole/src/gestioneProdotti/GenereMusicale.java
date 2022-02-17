package gestioneProdotti;

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((idGenere == null) ? 0 : idGenere.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof GenereMusicale))
			return false;
		GenereMusicale other = (GenereMusicale) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (idGenere == null) {
			if (other.idGenere != null)
				return false;
		} else if (!idGenere.equals(other.idGenere))
			return false;
		return true;
	}

	
	
}

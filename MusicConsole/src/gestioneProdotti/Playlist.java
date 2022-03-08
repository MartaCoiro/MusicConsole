package gestioneProdotti;

import java.io.Serializable;

public class Playlist implements Serializable{

	private static final long serialVersionUID = 1L;
	Integer id ;
	String nomeUtente;
	String nome;
	String nomeBrano;
	Integer numBrani;
	String nomeArtista;
	
	public Playlist(String nomeUtente, String nome, String nomeBrano, int numBrani, String nomeArtista ) {
		this.nomeUtente = nomeUtente;
		this.nome = nome;
		this.nomeBrano= nomeBrano;
		this.numBrani = numBrani;
		this.nomeArtista = nomeArtista;
		}
	
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((nomeArtista == null) ? 0 : nomeArtista.hashCode());
		result = prime * result + ((nomeBrano == null) ? 0 : nomeBrano.hashCode());
		result = prime * result + ((nomeUtente == null) ? 0 : nomeUtente.hashCode());
		result = prime * result + ((numBrani == null) ? 0 : numBrani.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Playlist))
			return false;
		Playlist other = (Playlist) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (nomeArtista == null) {
			if (other.nomeArtista != null)
				return false;
		} else if (!nomeArtista.equals(other.nomeArtista))
			return false;
		if (nomeBrano == null) {
			if (other.nomeBrano != null)
				return false;
		} else if (!nomeBrano.equals(other.nomeBrano))
			return false;
		if (nomeUtente == null) {
			if (other.nomeUtente != null)
				return false;
		} else if (!nomeUtente.equals(other.nomeUtente))
			return false;
		if (numBrani == null) {
			if (other.numBrani != null)
				return false;
		} else if (!numBrani.equals(other.numBrani))
			return false;
		return true;
	}
}

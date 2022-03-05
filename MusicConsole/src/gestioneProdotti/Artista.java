package gestioneProdotti;

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((IdCollaborazioni == null) ? 0 : IdCollaborazioni.hashCode());
		result = prime * result + ((NomeArtista == null) ? 0 : NomeArtista.hashCode());
		result = prime * result + ((NumeroAscoltatori == null) ? 0 : NumeroAscoltatori.hashCode());
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + ((offerta == null) ? 0 : offerta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Artista))
			return false;
		Artista other = (Artista) obj;
		if (IdCollaborazioni == null) {
			if (other.IdCollaborazioni != null)
				return false;
		} else if (!IdCollaborazioni.equals(other.IdCollaborazioni))
			return false;
		if (NomeArtista == null) {
			if (other.NomeArtista != null)
				return false;
		} else if (!NomeArtista.equals(other.NomeArtista))
			return false;
		if (NumeroAscoltatori == null) {
			if (other.NumeroAscoltatori != null)
				return false;
		} else if (!NumeroAscoltatori.equals(other.NumeroAscoltatori))
			return false;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (offerta == null) {
			if (other.offerta != null)
				return false;
		} else if (!offerta.equals(other.offerta))
			return false;
		return true;
	}

	
}

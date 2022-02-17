package gestioneProdotti;

import java.io.Serializable;
import java.util.Date;

public class Podcast implements Serializable{

	private static final long serialVersionUID = 1L;
	//valori della tabella
	 String nomePodcast;
	 String ideatore;
	 String descrizione;
	 float durata;
	 Integer numeroEpisodi;
	 String imgPod;
	 String tipo;
	 Date dataa = new Date();
	java.sql.Date dat = new java.sql.Date(dataa.getTime()); 
	Float prezzo;
	 
	 public Podcast(String nomePodcast, String ideatore, String descrizione, float durata, int numeroEpisodi, String imgPod, String tipo, float prezzo ) { 
		 this.nomePodcast = nomePodcast;
		 this.ideatore = ideatore;
		 this.descrizione = descrizione;
		 this.durata = durata;
		 this.numeroEpisodi = numeroEpisodi;
		 this.imgPod = imgPod;
		 this.tipo = tipo;
		 this.prezzo = prezzo;
			}
	 
	 public Podcast() { 
		 nomePodcast = "";
		 ideatore = "";
		 descrizione = "";
		 durata = 0.0f;
		 numeroEpisodi = 0;
		 imgPod = "";
		 tipo = "";
		 prezzo = 0.0f;
			}
	 
	public Float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Float prezzo) {
		this.prezzo = prezzo;
	}

	public Integer getNumeroEpisodi() {
		return numeroEpisodi;
	}

	public void setNumeroEpisodi(Integer numeroEpisodi) {
		this.numeroEpisodi = numeroEpisodi;
	}

	public java.sql.Date getDat() {
		return dat;
	}

	public void setDat(java.sql.Date dat) {
		this.dat = dat;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getImgPod() {
		return imgPod;
	}

	public void setImgPod(String imgPod) {
		this.imgPod = imgPod;
	}

	public String getNomePodcast() {
		return nomePodcast;
	}
	
	public void setNomePodcast(String nomePodcast) {
		this.nomePodcast = nomePodcast;
	}
	
	public String getIdeatore() {
		return ideatore;
	}
	
	public void setIdeatore(String ideatore) {
		this.ideatore = ideatore;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public Float getDurata() {
		return durata;
	}
	
	public void setDurata(Float durata) {
		this.durata = durata;
	}
	
	public Integer getNumEpisodi() {
		return numeroEpisodi;
	}
	
	public void setNumEpisodi(Integer numEpisodi) {
		this.numeroEpisodi = numEpisodi;
	}
	
	public void print() {
		System.out.printf("%20s | %20s | %50s | %20.2f | %10d | %10s | %20s\n",nomePodcast, ideatore,descrizione,durata,numeroEpisodi,imgPod);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dat == null) ? 0 : dat.hashCode());
		result = prime * result + ((dataa == null) ? 0 : dataa.hashCode());
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + Float.floatToIntBits(durata);
		result = prime * result + ((ideatore == null) ? 0 : ideatore.hashCode());
		result = prime * result + ((imgPod == null) ? 0 : imgPod.hashCode());
		result = prime * result + ((nomePodcast == null) ? 0 : nomePodcast.hashCode());
		result = prime * result + ((numeroEpisodi == null) ? 0 : numeroEpisodi.hashCode());
		result = prime * result + ((prezzo == null) ? 0 : prezzo.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Podcast))
			return false;
		Podcast other = (Podcast) obj;
		if (dat == null) {
			if (other.dat != null)
				return false;
		} else if (!dat.equals(other.dat))
			return false;
		if (dataa == null) {
			if (other.dataa != null)
				return false;
		} else if (!dataa.equals(other.dataa))
			return false;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (Float.floatToIntBits(durata) != Float.floatToIntBits(other.durata))
			return false;
		if (ideatore == null) {
			if (other.ideatore != null)
				return false;
		} else if (!ideatore.equals(other.ideatore))
			return false;
		if (imgPod == null) {
			if (other.imgPod != null)
				return false;
		} else if (!imgPod.equals(other.imgPod))
			return false;
		if (nomePodcast == null) {
			if (other.nomePodcast != null)
				return false;
		} else if (!nomePodcast.equals(other.nomePodcast))
			return false;
		if (numeroEpisodi == null) {
			if (other.numeroEpisodi != null)
				return false;
		} else if (!numeroEpisodi.equals(other.numeroEpisodi))
			return false;
		if (prezzo == null) {
			if (other.prezzo != null)
				return false;
		} else if (!prezzo.equals(other.prezzo))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}
	
	
}

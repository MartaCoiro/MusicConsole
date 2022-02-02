package Class;

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
	public String toString() {
		return "Podcast(" + nomePodcast + "):" + ideatore + " " + descrizione + " " + durata + " " + numeroEpisodi + " " + " " + imgPod;
	}
	 

	@Override  //ci dice se ci sono errori
	public boolean equals(Object other) { //restituisce true se vero
		return this.getNomePodcast() == ((Podcast) other).getNomePodcast();
	}
}

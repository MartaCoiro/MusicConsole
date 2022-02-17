package gestioneProdotti;

import java.io.Serializable;
import java.util.Date;

public class Album implements Serializable {

	private static final long serialVersionUID = 1L;
	//valori della tabella
	Integer codiceAlbum;
	String nomeAlbum;
	String imgAlbum;
	String nartista;
	String tipo;
	Date dataa = new Date();
	java.sql.Date dat = new java.sql.Date(dataa.getTime());
	float prezzoS;
	float prezzoV;
	float prezzoC;
	String descrizione;

	public Album(int codiceAlbum, String nomeAlbum, String imgAlbum,String nartista, String tipo, float prezzoS,float prezzoV,float prezzoC, String descrizione) { 
		this.codiceAlbum = codiceAlbum;
		this.nomeAlbum = nomeAlbum;
		this.imgAlbum = imgAlbum;
		this.nartista = nartista;
		this.tipo = tipo;
		this.prezzoS = prezzoS;
		this.prezzoV = prezzoV;
		this.prezzoC = prezzoC;
		this.descrizione= descrizione;
		}
	
	public Album() { 
		codiceAlbum = 0;
		nomeAlbum = "";
		imgAlbum = "";
		nartista = "";
		tipo = "";
		prezzoS = 0.0f;
		prezzoV = 0.0f;
		prezzoC = 0.0f;
		descrizione= "";
		}

	
	public Float getPrezzoS() {
		return prezzoS;
	}

	public void setPrezzoS(Float prezzoS) {
		this.prezzoS = prezzoS;
	}

	public Float getPrezzoV() {
		return prezzoV;
	}

	public void setPrezzoV(Float prezzoV) {
		this.prezzoV = prezzoV;
	}

	public Float getPrezzoC() {
		return prezzoC;
	}

	public void setPrezzoC(Float prezzoC) {
		this.prezzoC = prezzoC;
	}
	
	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
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

	public String getNartista() {
		return nartista;
	}

	public void setNartista(String nartista) {
		this.nartista = nartista;
	}

	public String getImgAlbum() {
		return imgAlbum;
	}

	public void setImgAlbum(String imgAlbum) {
		this.imgAlbum = imgAlbum;
	}

	public Integer getCodiceAlbum() {
		return codiceAlbum;
	}
	
	public void setCodiceAlbum(Integer codiceAlbum) {
		this.codiceAlbum = codiceAlbum;
	}
	
	public String getNomeAlbum() {
		return nomeAlbum;
	}
	
	public void setNomeAlbum(String nomeAlbum) {
		this.nomeAlbum = nomeAlbum;
	}
	
	public void print() {
		System.out.printf("%10d | %-20s | %-10s | %-20s | %-20s\n", codiceAlbum, nomeAlbum,imgAlbum,nartista);
	}

	@Override
	public String toString() {
		return "Album(" + codiceAlbum + "):" + nomeAlbum + " " + " " + imgAlbum + " " + nartista;
	}
	
	@Override  //ci dice se ci sono errori
	public boolean equals(Object other) { //restituisce true se vero
		return this.getNomeAlbum() == ((Album) other).getNomeAlbum();
	}
	
}

package Class;

import java.io.Serializable;
import java.util.Date;


public class Brano  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//valori della tabella
	Integer codice;
	String	titolo;
	float	durata;
	String cantante;
	String imgBrano;
	String genere;
	String tipo;
	String suono;
	Date dataa = new Date();
	java.sql.Date dat = new java.sql.Date(dataa.getTime()); 
	Float prezzo;
	String descrizione;

	public Brano(int codice,String titolo,float durata,String cantante,String imgBrano,String genere,String tipo,String suono,float prezzo,String descrizione) { 
		 this.codice = codice; 
		 this.titolo = titolo;
		 this.durata = durata;
		 this.cantante = cantante;
		 this.imgBrano = imgBrano;
		 this.genere = genere;
		 this.tipo = tipo;
		 this.suono = suono;
		 this.prezzo = prezzo;
		 this.descrizione = descrizione;
		 }
	
	public Brano() { 
		 codice = 0; 
		 titolo = "";
		 durata = 0.0f;
		 cantante = "";
		 imgBrano = "";
		 genere = "";
		 tipo = "";
		 suono = "";
		 prezzo = 0.0f;
		 descrizione = "";
		 }

	
	public Float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Float prezzo) {
		this.prezzo = prezzo;
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
	
	public String getSuono() {
		return suono;
	}

	public void setSuono(String suono) {
		this.suono = suono;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public String getImgBrano() {
		return imgBrano;
	}

	public void setImgBrano(String imgBrano) {
		this.imgBrano = imgBrano;
	}

	public String getCantante() {
		return cantante;
	}

	public void setCantante(String cantante) {
		this.cantante = cantante;
	}

	public Integer getCodice() {
		return codice;
	}
	
	public void setCodice(Integer codice) {
		this.codice = codice;
	}
	
	public String getTitolo() {
		return titolo;
	}
	
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	public Float getDurata() {
		return durata;
	}
	
	public void setDurata(Float durata) {
		this.durata = durata;
	}
	
	public void print() {
		System.out.printf("%10d | %-20s | %-10.2f | %10d | %-20s | %-20s | %-20s\n", codice,titolo,durata,cantante,imgBrano,genere);
	}

	@Override
	public String toString() {
		return "Brano(" + codice + "):" + titolo + " " + durata + " " + " " + cantante + " " + imgBrano + " " + genere;
	}
	
	@Override  //ci dice se ci sono errori
	public boolean equals(Object other) { //restituisce true se vero
		return this.getTitolo() == ((Brano) other).getTitolo();
	}
}


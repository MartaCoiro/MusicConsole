package gestioneProdotti;

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cantante == null) ? 0 : cantante.hashCode());
		result = prime * result + ((codice == null) ? 0 : codice.hashCode());
		result = prime * result + ((dat == null) ? 0 : dat.hashCode());
		result = prime * result + ((dataa == null) ? 0 : dataa.hashCode());
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + Float.floatToIntBits(durata);
		result = prime * result + ((genere == null) ? 0 : genere.hashCode());
		result = prime * result + ((imgBrano == null) ? 0 : imgBrano.hashCode());
		result = prime * result + ((prezzo == null) ? 0 : prezzo.hashCode());
		result = prime * result + ((suono == null) ? 0 : suono.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((titolo == null) ? 0 : titolo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Brano))
			return false;
		Brano other = (Brano) obj;
		if (cantante == null) {
			if (other.cantante != null)
				return false;
		} else if (!cantante.equals(other.cantante))
			return false;
		if (codice == null) {
			if (other.codice != null)
				return false;
		} else if (!codice.equals(other.codice))
			return false;
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
		if (genere == null) {
			if (other.genere != null)
				return false;
		} else if (!genere.equals(other.genere))
			return false;
		if (imgBrano == null) {
			if (other.imgBrano != null)
				return false;
		} else if (!imgBrano.equals(other.imgBrano))
			return false;
		if (prezzo == null) {
			if (other.prezzo != null)
				return false;
		} else if (!prezzo.equals(other.prezzo))
			return false;
		if (suono == null) {
			if (other.suono != null)
				return false;
		} else if (!suono.equals(other.suono))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		if (titolo == null) {
			if (other.titolo != null)
				return false;
		} else if (!titolo.equals(other.titolo))
			return false;
		return true;
	}

	
}


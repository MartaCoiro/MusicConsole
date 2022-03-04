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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codiceAlbum == null) ? 0 : codiceAlbum.hashCode());
		result = prime * result + ((dat == null) ? 0 : dat.hashCode());
		result = prime * result + ((dataa == null) ? 0 : dataa.hashCode());
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + ((imgAlbum == null) ? 0 : imgAlbum.hashCode());
		result = prime * result + ((nartista == null) ? 0 : nartista.hashCode());
		result = prime * result + ((nomeAlbum == null) ? 0 : nomeAlbum.hashCode());
		result = prime * result + Float.floatToIntBits(prezzoC);
		result = prime * result + Float.floatToIntBits(prezzoS);
		result = prime * result + Float.floatToIntBits(prezzoV);
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Album))
			return false;
		Album other = (Album) obj;
		if (codiceAlbum == null) {
			if (other.codiceAlbum != null)
				return false;
		} else if (!codiceAlbum.equals(other.codiceAlbum))
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
		if (imgAlbum == null) {
			if (other.imgAlbum != null)
				return false;
		} else if (!imgAlbum.equals(other.imgAlbum))
			return false;
		if (nartista == null) {
			if (other.nartista != null)
				return false;
		} else if (!nartista.equals(other.nartista))
			return false;
		if (nomeAlbum == null) {
			if (other.nomeAlbum != null)
				return false;
		} else if (!nomeAlbum.equals(other.nomeAlbum))
			return false;
		if (Float.floatToIntBits(prezzoC) != Float.floatToIntBits(other.prezzoC))
			return false;
		if (Float.floatToIntBits(prezzoS) != Float.floatToIntBits(other.prezzoS))
			return false;
		if (Float.floatToIntBits(prezzoV) != Float.floatToIntBits(other.prezzoV))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

	
}

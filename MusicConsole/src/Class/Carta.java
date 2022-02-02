package Class;

import java.io.Serializable;
import java.util.Date;

public class Carta implements Serializable{

	private static final long serialVersionUID = 1L;
	//valori della tabella
	int cvv;
	String utente;
	String nome;
	String cognome;
	String numero;
	String dataa;

	public Carta() { 
		dataa = "";
		cvv = 0;
		nome = "";
		cognome = "";
		numero = "";
		utente = "";
		
		}
	
	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public String getUtente() {
		return utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	
	public String getDataa() {
		return dataa;
	}

	public void setDataa(String dataa) {
		this.dataa = dataa;
	}

	@Override  //ci dice se ci sono errori
	public boolean equals(Object other) { //restituisce true se vero
		return this.getUtente() == ((Carta) other).getUtente();
	}
	
	
}

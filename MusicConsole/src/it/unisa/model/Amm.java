package it.unisa.model;

import java.io.Serializable;

public class Amm implements Serializable{

	private static final long serialVersionUID = 1L;
	//valori della tabella
		String ruolo;
		String utente;
		String password;
		
		public Amm() { 
			ruolo = "";
			utente = "";
			password = "";
			}
		
		public String getRuolo() {
			return ruolo;
		}

		public void setRuolo(String ruolo) {
			this.ruolo = ruolo;
		}

		public String getUtente() {
			return utente;
		}
		
		public void setUtente(String utente) {
			this.utente = utente;
		}
		
		public String getPassword() {
			return password;
		}
		
		public void setPassword(String password) {
			this.password = password;
		}
		
		public void print() {
			System.out.printf("%10s | %8s \n", utente,password);
		}
	
		@Override
		public String toString() {
			return utente + "," +  password;
		}
		
		@Override  //ci dice se ci sono errori
		public boolean equals(Object other) { //restituisce true se vero
			return this.getUtente() == ((Amm) other).getUtente();
		}
		
	
}

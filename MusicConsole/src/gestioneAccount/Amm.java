package gestioneAccount;

import java.io.Serializable;

public class Amm implements Serializable{

	private static final long serialVersionUID = 1L;
	//valori della tabella
		String ruolo;
		String utente;
		String password;
		
		public Amm(String utente,String password,String ruolo) { 
			this.utente = utente;
			this.password = password;
			this.ruolo = ruolo;
			}
		
		public Amm() { 
			utente = "";
			password = "";
			ruolo = "";
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
			return utente + " " +  password + " " + ruolo;
		} 
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((password == null) ? 0 : password.hashCode());
			result = prime * result + ((ruolo == null) ? 0 : ruolo.hashCode());
			result = prime * result + ((utente == null) ? 0 : utente.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!(obj instanceof Amm))
				return false;
			Amm other = (Amm) obj;
			if (password == null) {
				if (other.password != null)
					return false;
			} else if (!password.equals(other.password))
				return false;
			if (ruolo == null) {
				if (other.ruolo != null)
					return false;
			} else if (!ruolo.equals(other.ruolo))
				return false;
			if (utente == null) {
				if (other.utente != null)
					return false;
			} else if (!utente.equals(other.utente))
				return false;
			return true;
		}
	
		
		
	
}

package gestioneAccount;

import java.io.Serializable;

public class Profilo implements Serializable {

	private static final long serialVersionUID = 1L;
	//valori della tabella
		int id;
		String nome;
		String cognome;
		String citta;
		String indirizzo;
		String telefono;
		String email;
		String username;
		String password;

		public Profilo(String nome, String cognome, String citta, String indirizzo, String telefono, String email, String username, String password) { 
			this.nome = nome;
			this.cognome = cognome;
			this.citta = citta;
			this.indirizzo = indirizzo;
			this.telefono = telefono;
			this.email = email;
			this.username = username;
			this.password = password;
			}
		
		public Profilo() { 
			id = 0;
			nome = "";
			cognome = "";
			citta = "";
			indirizzo = "";
			telefono = "";
			email = "";
			username = "";
			password = "";
			}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
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

		public String getCitta() {
			return citta;
		}

		public void setCitta(String citta) {
			this.citta = citta;
		}

		public String getIndirizzo() {
			return indirizzo;
		}

		public void setIndirizzo(String indirizzo) {
			this.indirizzo = indirizzo;
		}

		public String getTelefono() {
			return telefono;
		}

		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}
		
		public void print() {
			System.out.printf("%20s | %20s | %20s | %20s | %10s | %20s | %20s | %20s \n", nome,cognome,citta,indirizzo,telefono,email,username,password);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((citta == null) ? 0 : citta.hashCode());
			result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
			result = prime * result + ((email == null) ? 0 : email.hashCode());
			result = prime * result + id;
			result = prime * result + ((indirizzo == null) ? 0 : indirizzo.hashCode());
			result = prime * result + ((nome == null) ? 0 : nome.hashCode());
			result = prime * result + ((password == null) ? 0 : password.hashCode());
			result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
			result = prime * result + ((username == null) ? 0 : username.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!(obj instanceof Profilo))
				return false;
			Profilo other = (Profilo) obj;
			if (citta == null) {
				if (other.citta != null)
					return false;
			} else if (!citta.equals(other.citta))
				return false;
			if (cognome == null) {
				if (other.cognome != null)
					return false;
			} else if (!cognome.equals(other.cognome))
				return false;
			if (email == null) {
				if (other.email != null)
					return false;
			} else if (!email.equals(other.email))
				return false;
			if (id != other.id)
				return false;
			if (indirizzo == null) {
				if (other.indirizzo != null)
					return false;
			} else if (!indirizzo.equals(other.indirizzo))
				return false;
			if (nome == null) {
				if (other.nome != null)
					return false;
			} else if (!nome.equals(other.nome))
				return false;
			if (password == null) {
				if (other.password != null)
					return false;
			} else if (!password.equals(other.password))
				return false;
			if (telefono == null) {
				if (other.telefono != null)
					return false;
			} else if (!telefono.equals(other.telefono))
				return false;
			if (username == null) {
				if (other.username != null)
					return false;
			} else if (!username.equals(other.username))
				return false;
			return true;
		}
		
		
}

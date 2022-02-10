package Class;

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
		public String toString() {
			return nome + "," +  cognome + "," + citta + "," + indirizzo + "," + telefono + "," + email + "," + username + "," + password;
		}
		
		@Override  //ci dice se ci sono errori
		public boolean equals(Object other) { //restituisce true se vero
			return this.getNome() == ((Profilo) other).getNome();
		}
}

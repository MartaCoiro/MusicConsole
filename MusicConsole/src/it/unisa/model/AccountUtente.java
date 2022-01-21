package it.unisa.model;

import java.io.Serializable;

public class AccountUtente implements Serializable {

	private static final long serialVersionUID = 1L;
	//valori della tabella
		String nickname;
		String password;

		public AccountUtente() { 
			nickname = "";
			password = "";
			}
		
		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public void print() {
			System.out.printf("%10s | %8s \n", nickname,password);
		}
		
		/*public boolean valid(String utente) {
			if(this.getNickname()==utente) {
				return true;
			}
				else return false;
		}*/
		
		@Override
		public String toString() {
			return nickname + "," +  password;
		}
		
		@Override  //ci dice se ci sono errori
		public boolean equals(Object other) { //restituisce true se vero
			return this.getNickname() == ((AccountUtente) other).getNickname();
		}
		
		
		
}

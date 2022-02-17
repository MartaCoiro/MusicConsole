package gestioneAccount;

import java.io.Serializable;

public class AccountUtente implements Serializable {

	private static final long serialVersionUID = 1L;
	//valori della tabella
		private String nickname;
		private String password;

		public AccountUtente(String nickname,String password) { 
			this.nickname = nickname;
			this.password = password;
			}
		
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
			return nickname + " " +  password;
		}
 
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
			result = prime * result + ((password == null) ? 0 : password.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (!(obj instanceof AccountUtente)) {
				return false;
			}
			AccountUtente other = (AccountUtente) obj;
			if (nickname == null) {
				if (other.nickname != null) {
					return false;
				}
			} else if (!nickname.equals(other.nickname)) {
				return false;
			}
			if (password == null) {
				if (other.password != null) {
					return false;
				}
			} else if (!password.equals(other.password)) {
				return false;
			}
			return true;
		}
		
		
		
		
}

package gestioneAccount;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.Collection;
	import java.util.LinkedList;

	import javax.sql.DataSource;

import it.unisa.utils.Utility;

public class AccountModelDS implements ProductModel<AccountUtente> {

		/*private DataSource ds;*/
		private DataSource ds = null;
		private Connection connection;
		
		public AccountModelDS(DataSource ds) {
			this.ds = ds;
			/*System.out.println(ds);*/
		}
		
		public AccountModelDS(Connection connection) {
			this.connection = connection;
		}
		
		@Override
		public Collection<AccountUtente> doRetrieveAll() throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			String selectSQL = "SELECT * FROM  AccountUtente";
			
			/*if(order != null && !order.equals("")) {
				selectSQL += " ORDER BY " + order;
			}*/
			
			Collection<AccountUtente> account = new LinkedList<AccountUtente>();
			
			try {
				connection = ds.getConnection(); //recupero connessione dal data source
				preparedStatement = connection.prepareStatement(selectSQL);
				
				Utility.print("doRetrieveAll: " + preparedStatement.toString());
				
				ResultSet rs = preparedStatement.executeQuery();			
			
				while(rs.next()) {
					AccountUtente bean = new AccountUtente();
					bean.setNickname(rs.getString("nickname"));
					bean.setPassword(rs.getString("password"));
					
					account.add(bean);
				}
			} finally {
				try {
				if(preparedStatement != null)
					preparedStatement.close();
				}finally {
				if(connection != null)
					connection.close();
				}
			}
			return account;
		}

		@Override
		public AccountUtente doRetrieveByKey(String utente, String pass) throws SQLException {
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			AccountUtente bean = new AccountUtente();
			
			String selectSQL = " SELECT * FROM  AccountUtente  WHERE NICKNAME = ? AND PASSWORD = ? ";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, utente);
				preparedStatement.setString(2, pass);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					bean.setNickname(rs.getString("nickname"));
					bean.setPassword(rs.getString("password"));
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			return bean;
			}
		
		
		@Override
		public boolean doSave(AccountUtente item) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			String insertSQL = "INSERT INTO AccountUtente (NICKNAME, PASSWORD) VALUES (?, ?)";

			try {
				connection = ds.getConnection();
				
				connection.setAutoCommit(false);
				
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setString(1, item.getNickname());
				preparedStatement.setString(2, item.getPassword());

				preparedStatement.executeUpdate();

				connection.commit();
			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			return true;
		}

		@Override
		public void doUpdate(String p1, String p2, String p3) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			if(p1.equals("password")) {
			String updateSQL = "UPDATE accountutente SET PASSWORD=? where NICKNAME=?";
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			}else if(p1.equals("nickname")) {
				String updateSQL = "UPDATE accountutente SET NICKNAME=? where NICKNAME=?";
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(updateSQL);
			}
			
			try {
				
				preparedStatement.setString(1, p2);
				preparedStatement.setString(2, p3);
				
				Utility.print("doUpdate: " + preparedStatement.toString());
				
				 preparedStatement.executeUpdate();
			}
			finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
		}
	

		

		@Override
		public synchronized boolean doDelete(String utente) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			int result = 0;

			String deleteSQL = "DELETE FROM AccountUtente WHERE NICKNAME = ?";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(deleteSQL);
				preparedStatement.setString(1, utente);

				result = preparedStatement.executeUpdate();

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			return (result != 0);
		}
		
}



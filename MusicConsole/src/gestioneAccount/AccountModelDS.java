package gestioneAccount;

	import java.io.Serializable;
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
		//private DataSource ds = null;
		private Connection connection;
		 
		/*public AccountModelDS(DataSource ds) {
			this.ds = ds;
			//System.out.println(ds);
		}*/
		
		public AccountModelDS(Connection connection) {
			this.connection = connection;
		}
		
		public Collection<AccountUtente> doRetrieveAll()throws SQLException{
			//Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			String selectSQL = "SELECT * FROM  AccountUtente";
			
			/*if(order != null && !order.equals("")) {
				selectSQL += " ORDER BY " + order;
			}*/
			
			Collection<AccountUtente> account = new LinkedList<AccountUtente>();
			
			try {
				//connection = ds.getConnection(); //recupero connessione dal data source
				preparedStatement = connection.prepareStatement(selectSQL);
				
				Utility.print("doRetrieveAll: " + preparedStatement.toString());
				
				ResultSet rs = preparedStatement.executeQuery();	
				
				/*if(!rs.next())
					return null;*/
			
				while(rs.next()) {
					AccountUtente bean = new AccountUtente();
					bean.setNickname(rs.getString("nickname"));
					bean.setPassword(rs.getString("password"));
					
					account.add(bean);
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return account;
		}
 

		public AccountUtente doRetrieveByKey(String utente, String pass) {
			
			//Connection connection = null;
			PreparedStatement preparedStatement = null;

			AccountUtente bean = null;
			 
			String selectSQL = " SELECT * FROM  AccountUtente  WHERE NICKNAME = ? AND PASSWORD = ? ";
			 
			try {
				//connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, utente);
				preparedStatement.setString(2, pass);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					bean = new AccountUtente();
					bean.setNickname(rs.getString("nickname"));
					bean.setPassword(rs.getString("password"));
				}

			} catch(SQLException e) {
				e.printStackTrace();
			}
			return bean;
			}
		
		
		public boolean doSave(AccountUtente item)  {
			//Connection connection = null;
			PreparedStatement preparedStatement = null;

			String insertSQL = "INSERT INTO AccountUtente (NICKNAME,PASSWORD) VALUES (?, ?)";

			try {
				//connection = ds.getConnection();
				
				//connection.setAutoCommit(false);
				
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setString(1, item.getNickname());
				preparedStatement.setString(2, item.getPassword());

				preparedStatement.executeUpdate();

				//connection.commit();
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return true;
		}
 

		public void doUpdate(String p1, String p2) throws SQLException  {
			//Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			//if(p1.equals("password")) {
			String updateSQL = "UPDATE accountutente SET PASSWORD=? where NICKNAME=?";
			//connection = ds.getConnection();
			
				preparedStatement = connection.prepareStatement(updateSQL);
			
			/*}else if(p1.equals("nickname")) {
				String updateSQL = "UPDATE accountutente SET NICKNAME=? where NICKNAME=?";
				//connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(updateSQL);
			}*/
			
			try {
				
				preparedStatement.setString(1, p1);
				preparedStatement.setString(2, p2);
				
				Utility.print("doUpdate: " + preparedStatement.toString());
				
				 preparedStatement.executeUpdate();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	

		

		public synchronized boolean doDelete(String utente)  {
			//Connection connection = null;
			PreparedStatement preparedStatement = null;

			int result = 0;

			String deleteSQL = "DELETE FROM AccountUtente WHERE NICKNAME = ?";

			try {
				//connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(deleteSQL);
				preparedStatement.setString(1, utente);

				result = preparedStatement.executeUpdate();

			} catch(SQLException e) {
				e.printStackTrace();
			}
			return (result != 0);
		}
		
}


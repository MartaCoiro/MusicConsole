package gestioneAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.utils.Utility;

public class AmmModelDS implements ProductModelAmm<Amm> {

	private DataSource ds = null;
	
	public AmmModelDS(DataSource ds) {
		this.ds = ds;
	}
	
	@Override
	public Collection<Amm> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL = "SELECT * FROM  Amm";
		
		if(order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		Collection<Amm> account = new LinkedList<Amm>();
		
		try {
			connection = ds.getConnection(); //recupero connessione dal data source
			preparedStatement = connection.prepareStatement(selectSQL);
			
			Utility.print("doRetrieveAll: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();			
		
			while(rs.next()) {
				Amm bean = new Amm();
				bean.setUtente(rs.getString("utente"));
				bean.setPassword(rs.getString("password"));
				bean.setRuolo(rs.getString("ruolo"));
				
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
	public Amm doRetrieveByKey(String utente, String pass) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		//Amm bean;
		Amm bean = new Amm();
		
		String selectSQL = " SELECT * FROM  Amm  WHERE UTENTE = ? AND PASSWORD = ? ";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, utente);
			preparedStatement.setString(2, pass);

			ResultSet rs = preparedStatement.executeQuery();

			//bean = new Amm(rs.getString("ruolo"),rs.getString("utente"),rs.getString("password"));
			
			while (rs.next()) {
				bean.setUtente(rs.getString("utente"));
				bean.setPassword(rs.getString("password"));
				bean.setRuolo(rs.getString("ruolo"));
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
	public void doSave(Amm item) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO Amm (UTENTE, PASSWORD, RUOLO) VALUES (?, ?, ?)";

		try {
			connection = ds.getConnection();
			
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, item.getUtente());
			preparedStatement.setString(2, item.getPassword());
			preparedStatement.setString(3, item.getRuolo());

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

	}

	@Override
	public void doUpdate(Amm item) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public synchronized boolean doDelete(String nickname) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM Amm WHERE CODE = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, nickname);

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





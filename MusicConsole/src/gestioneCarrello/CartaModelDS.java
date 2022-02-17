package gestioneCarrello;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import it.unisa.utils.Utility;

public class CartaModelDS implements ProductModelCarta<Carta>{

//private DataSource ds = null;

private Connection connection;
	
	public CartaModelDS(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public Collection<Carta> doRetrieveAll() throws SQLException {
		//Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL = "SELECT * FROM  Carta";
		
		Collection<Carta> al = new LinkedList<Carta>();
		
		try {
			//connection = ds.getConnection(); //recupero connessione dal data source
			preparedStatement = connection.prepareStatement(selectSQL);
			
			Utility.print("doRetrieveAll: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();			
		
			while(rs.next()) {
				Carta bean = new Carta();
				bean.setNome(rs.getString("nome"));
				bean.setCvv(rs.getInt("cvv"));
				bean.setCognome(rs.getString("cognome"));
				bean.setUtente(rs.getString("utente"));
				bean.setNumero(rs.getString("numero"));
				bean.setDataa(rs.getString("dataScadenza"));
				al.add(bean);
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return al;
	}

	@Override
	public Carta doRetrieveByKey(int cvv) throws SQLException {
		
		//Connection connection = null;
		PreparedStatement preparedStatement = null;

		//Carta bean;
		Carta bean = new Carta();
		
		String selectSQL = " SELECT * FROM  Carta  WHERE CVV = ? ";
		
		try {
			//connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, cvv);
		
			ResultSet rs = preparedStatement.executeQuery();

			//bean = new Carta(rs.getString("dataScadenza"),rs.getInt("cvv"),rs.getString("nome"),rs.getString("cognome"),rs.getString("numero"),rs.getString("utente"));
			
			while (rs.next()) {
				bean.setNome(rs.getString("nome"));
				bean.setCvv(rs.getInt("cvv"));
				bean.setCognome(rs.getString("cognome"));
				bean.setUtente(rs.getString("utente"));
				bean.setNumero(rs.getString("numero"));
				bean.setDataa(rs.getString("dataScadenza"));
			}

		} catch(SQLException e){
			e.printStackTrace();
		}
		return bean;
		}
	
	
	@Override
	public void doSave(Carta item) throws SQLException {
		//Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO Carta (UTENTE, CVV, NOME, COGNOME, NUMERO, DATASCADENZA) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			//connection = ds.getConnection();
			
			//connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, item.getUtente());
			preparedStatement.setInt(2, item.getCvv());
			preparedStatement.setString(3, item.getNome());
			preparedStatement.setString(4, item.getCognome());
			preparedStatement.setString(5, item.getNumero());
			preparedStatement.setString(6, item.getDataa());
			
			preparedStatement.executeUpdate();

			//connection.commit();
		} catch(SQLException e){
			e.printStackTrace();
		}

	}

	@Override
	public void doUpdate(String data, int cvv) throws SQLException {
		//Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL = "UPDATE Carta SET DATASCADENZA=? where CVV = ?";
		//connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(updateSQL);
		
		try {
			
			preparedStatement.setString(1, data);
			preparedStatement.setInt(2, cvv);
			
			 preparedStatement.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}

	}
	
	@Override
	public synchronized boolean doDelete(int cvv) throws SQLException {
		//Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM Carta WHERE CVV = ?";

		try {
			//connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, cvv);

			result = preparedStatement.executeUpdate();

		} catch(SQLException e){
			e.printStackTrace();
		}
		return (result != 0);
	}
}

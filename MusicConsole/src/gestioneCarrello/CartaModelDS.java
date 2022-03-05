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
		Carta bean = null;
		
		String selectSQL = " SELECT * FROM  Carta  WHERE CVV = ? ";
		
		try {
			//connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, cvv);
		
			ResultSet rs = preparedStatement.executeQuery();

			//bean = new Carta(rs.getString("dataScadenza"),rs.getInt("cvv"),rs.getString("nome"),rs.getString("cognome"),rs.getString("numero"),rs.getString("utente"));
			
			while (rs.next()) {
				bean = new Carta();
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
	public boolean doSave(Carta item) {
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
			
			if(preparedStatement.executeUpdate()==0) {
				return false;
			}
			return true;

			//connection.commit();
		} catch(SQLException e){
			e.printStackTrace();
		}
			return false;
	}

	@Override
	public void doUpdate(int cvv) throws SQLException {
		// TODO Auto-generated method stub
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
			
			if(result==0) {
				return false;
			}
			return true;
			
		} catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
}

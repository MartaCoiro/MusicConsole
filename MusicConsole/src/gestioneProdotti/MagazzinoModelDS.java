package gestioneProdotti;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.utils.Utility;

public class MagazzinoModelDS implements ProductModelMagazzino<Magazzino> {

//private DataSource ds = null;
	private Connection connection;
	/*
	public MagazzinoModelDS(DataSource ds) {
		this.ds = ds;
	}*/
	public MagazzinoModelDS(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public Collection<Magazzino> doRetrieveAll() throws SQLException {
		//Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL = "SELECT * FROM  Magazzino";
				
		Collection<Magazzino> mag = new LinkedList<Magazzino>();
		
		try {
			//connection = ds.getConnection(); //recupero connessione dal data source
			preparedStatement = connection.prepareStatement(selectSQL);
			
			Utility.print("doRetrieveAll: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();			
		
			while(rs.next()) {
				Magazzino bean = new Magazzino();
				bean.setNome(rs.getString("nome"));
				bean.setAutore(rs.getString("autore"));
				bean.setTipo(rs.getString("tipo"));
				bean.setCod(rs.getInt("cod"));
				bean.setCosto(rs.getFloat("costo"));
				bean.setQuantit�(rs.getInt("quantit�"));
				
				mag.add(bean);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		
		}
		return mag;
	}

	@Override
	public Magazzino doRetrieveByKey(int cod) throws SQLException {
		
		//Connection connection = null;
		PreparedStatement preparedStatement = null;

		//Magazzino bean;
		Magazzino bean = null;
		
		String selectSQL = " SELECT * FROM  Magazzino  WHERE COD = ? ";
		
		try {
			//connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, cod);

			ResultSet rs = preparedStatement.executeQuery();

			//bean = new Magazzino(rs.getInt("quantit�"),rs.getString("nome"),rs.getString("autore"),rs.getString("tipo"),rs.getFloat("costo"));
			
			while (rs.next()) {
				bean = new Magazzino();
				bean.setNome(rs.getString("nome"));
				bean.setAutore(rs.getString("autore"));
				bean.setTipo(rs.getString("tipo"));
				bean.setCod(rs.getInt("cod"));
				bean.setCosto(rs.getFloat("costo"));
				bean.setQuantit�(rs.getInt("quantit�"));
			}

		} catch(SQLException e) {
			e.printStackTrace();
		}
		return bean;
		}
	
	
	@Override
	public boolean doSave(Magazzino item) {
		//Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO Magazzino (COD, NOME, AUTORE, TIPO, COSTO, QUANTIT�) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			//connection = ds.getConnection();
			
			//connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, item.getCod());
			preparedStatement.setString(2, item.getNome());
			preparedStatement.setString(3, item.getAutore());
			preparedStatement.setString(4, item.getTipo());
			preparedStatement.setFloat(5, item.getCosto());
			preparedStatement.setInt(6, item.getQuantit�());

			if(preparedStatement.executeUpdate()==0) {
				return false;
			}
			return true;

		} catch(SQLException e) {
			e.printStackTrace();
		}
			return false;
	}

	@Override
	public boolean doUpdate(int quantit�, int cod) throws SQLException {
		//Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE Magazzino SET quantit� = ? where COD = ?";
		//connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(updateSQL);

		try {
			preparedStatement.setInt(1, quantit�);
			preparedStatement.setInt(2, cod);
			
			 if(preparedStatement.executeUpdate()==0) {
				 return false;
			 }
			 return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public synchronized boolean doDelete(int cod) throws SQLException {
		//Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM Magazzino WHERE COD = ?";

		try {
			//connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, cod);

			result = preparedStatement.executeUpdate();

			if(result == 0) {
				return false;
			}
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}



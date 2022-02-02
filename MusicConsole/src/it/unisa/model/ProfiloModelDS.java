package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import Class.Profilo;
import javax.sql.DataSource;
import it.unisa.utils.Utility;

public class ProfiloModelDS implements ProductModelProfilo<Profilo> {

private DataSource ds = null;
	
	public ProfiloModelDS(DataSource ds) {
		this.ds = ds;
	}
	
	@Override
	public Collection<Profilo> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL = "SELECT * FROM  profilo";
		
		Collection<Profilo> al = new LinkedList<Profilo>();
		
		try {
			connection = ds.getConnection(); //recupero connessione dal data source
			preparedStatement = connection.prepareStatement(selectSQL);
			
			Utility.print("doRetrieveAll: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();			
		
			while(rs.next()) {
				Profilo bean = new Profilo();
				bean.setId(rs.getInt("id"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setCitta(rs.getString("citta"));
				bean.setIndirizzo(rs.getString("indirizzo"));
				bean.setTelefono(rs.getString("telefono"));
				bean.setEmail(rs.getString("email"));
				bean.setUsername(rs.getString("username"));
				bean.setPassword(rs.getString("password"));
				
				al.add(bean);
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
		return al;
	}

	@Override
	public Profilo doRetrieveByKey(String nome) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Profilo bean = new Profilo();
		
		String selectSQL = " SELECT * FROM  profilo  WHERE USERNAME = ? ";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, nome);
		

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setId(rs.getInt("id"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setCitta(rs.getString("citta"));
				bean.setIndirizzo(rs.getString("indirizzo"));
				bean.setTelefono(rs.getString("telefono"));
				bean.setEmail(rs.getString("email"));
				bean.setUsername(rs.getString("username"));
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
	public void doSave(Profilo item) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO profilo (ID, NOME, COGNOME, CITTA, INDIRIZZO, TELEFONO, EMAIL, USERNAME, PASSWORD) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, item.getId());
			preparedStatement.setString(2, item.getNome());
			preparedStatement.setString(3, item.getCognome());
			preparedStatement.setString(4, item.getCitta());
			preparedStatement.setString(5, item.getIndirizzo());
			preparedStatement.setString(6, item.getTelefono());
			preparedStatement.setString(7, item.getEmail());
			preparedStatement.setString(8, item.getUsername());
			preparedStatement.setString(9, item.getPassword());
			

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
	public void doUpdate(String p1, String p2, String p3) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		if(p1.equals("nome")) {
		String updateSQL = "UPDATE profilo SET NOME=? where USERNAME=?";
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(updateSQL);
		}else if(p1.equals("cognome")) {
			String updateSQL = "UPDATE profilo SET COGNOME=? where USERNAME=?";
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
		}else if(p1.equals("email")) {
			String updateSQL = "UPDATE profilo SET EMAIL=? where USERNAME=?";
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
		}else if(p1.equals("telefono")) {
			String updateSQL = "UPDATE profilo SET TELEFONO=? where USERNAME=?";
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
		}else if(p1.equals("indirizzo")) {
			String updateSQL = "UPDATE profilo SET INDIRIZZO=? where USERNAME=?";
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
		}else if(p1.equals("città")) {
			String updateSQL = "UPDATE profilo SET CITTA=? where USERNAME=?";
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
		}else if(p1.equals("password")) {
			String updateSQL = "UPDATE profilo SET PASSWORD=? where USERNAME=?";
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
		}else if(p1.equals("username")) {
			String updateSQL = "UPDATE profilo SET USERNAME=? where USERNAME=?";
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
		}
		try {
			
			preparedStatement.setString(1, p2);
			preparedStatement.setString(2, p3);
			
			/*Utility.print("doUpdate: " + preparedStatement.toString());*/
			
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
	public synchronized boolean doDelete(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM profilo WHERE ID = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);

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

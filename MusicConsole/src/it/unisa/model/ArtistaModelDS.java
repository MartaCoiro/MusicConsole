package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.utils.Utility;

public class ArtistaModelDS implements ProductModelArtista<Artista> {

	private DataSource ds = null;
	
	public ArtistaModelDS(DataSource ds) {
		this.ds = ds;
	}
	
	@Override
	public Collection<Artista> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL = "SELECT * FROM  Artista";
		
		/*if(order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}*/
		
		Collection<Artista> ar = new LinkedList<Artista>();
		
		try {
			connection = ds.getConnection(); //recupero connessione dal data source
			preparedStatement = connection.prepareStatement(selectSQL);
			
			Utility.print("doRetrieveAll: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();			
		
			while(rs.next()) {
				Artista bean = new Artista();
				bean.setNomeArtista(rs.getString("NomeArtista"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setOfferta(rs.getString("offerta"));
				bean.setIdCollaborazioni(rs.getString("IdCollaborazioni"));
				bean.setNumeroAscoltatori(rs.getInt("NumeroAscoltatori"));
				
				ar.add(bean);
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
		return ar;
	}

	@Override
	public Artista doRetrieveByKey(String parola) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Artista bean = new Artista();
		
		String selectSQL = " SELECT * FROM  Artista  WHERE NOMEARTISTA = ? ";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, parola);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setNomeArtista(rs.getString("NomeArtista"));
				bean.setIdCollaborazioni(rs.getString("IdCollaborazioni"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setOfferta(rs.getString("offerta"));
				bean.setNumeroAscoltatori(rs.getInt("NumeroAscoltatori"));
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
	public void doSave(Artista item) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO Artista (NOMEARTISTA, DESCRIZIONE, OFFERTA, IDCOLLABORAZIONI, NUMEROASCOLTATORI) VALUES (?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, item.getNomeArtista());
			preparedStatement.setString(2, item.getDescrizione());
			preparedStatement.setString(3, item.getOfferta());
			preparedStatement.setString(4, item.getIdCollaborazioni());
			preparedStatement.setInt(5, item.getNumeroAscoltatori());

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
	public void doUpdate(Artista item) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public synchronized boolean doDelete(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM Artista WHERE CODE = ?";

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

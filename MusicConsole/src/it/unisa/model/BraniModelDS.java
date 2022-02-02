package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import Class.Brano;
import javax.sql.DataSource;

import it.unisa.utils.Utility;

public class BraniModelDS implements ProductModelBrani<Brano> {
	
	private DataSource ds = null;
	
	public BraniModelDS(DataSource ds) {
		this.ds = ds;
	}
	
	@Override
	public Collection<Brano> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL = "SELECT * FROM  Brano";
		
		Collection<Brano> br = new LinkedList<Brano>();
		
		try {
			connection = ds.getConnection(); //recupero connessione dal data source
			preparedStatement = connection.prepareStatement(selectSQL);
			
			Utility.print("doRetrieveAll: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();			
		
			while(rs.next()) {
				Brano bean = new Brano();
				bean.setCodice(rs.getInt("codice"));
				bean.setTitolo(rs.getString("titolo"));
				bean.setDurata(rs.getFloat("durata"));
				bean.setCantante(rs.getString("cantante"));
				bean.setImgBrano(rs.getString("imgBrano"));
				bean.setGenere(rs.getString("genere"));
				bean.setTipo(rs.getString("tipo"));
				bean.setSuono(rs.getString("suono"));
				bean.setDat(rs.getDate("dataa"));
				bean.setPrezzo(rs.getFloat("prezzo"));
				bean.setDescrizione(rs.getString("descrizione"));
				
				br.add(bean);
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
		return br;
	}

	@Override
	public Brano doRetrieveByKey(String nome, String artista) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Brano bean = new Brano();
		
		String selectSQL = " SELECT * FROM  Brano  WHERE TITOLO = ? AND CANTANTE = ? ";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, nome);
			preparedStatement.setString(2, artista);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setTitolo(rs.getString("titolo"));
				bean.setCodice(rs.getInt("codice"));
				bean.setDurata(rs.getFloat("durata"));
				bean.setCantante(rs.getString("cantante"));
				bean.setImgBrano(rs.getString("imgBrano"));
				bean.setGenere(rs.getString("genere"));
				bean.setTipo(rs.getString("tipo"));
				bean.setSuono(rs.getString("suono"));
				bean.setDat(rs.getDate("dataa"));
				bean.setPrezzo(rs.getFloat("prezzo"));
				bean.setDescrizione(rs.getString("descrizione"));
				
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
	public void doSave(Brano item) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO Brano (CODICE, TITOLO, DURATA, CANTANTE, imgBrano, GENERE, TIPO, SUONO, DATAA, PREZZO, DESCRIZIONE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, item.getCodice());
			preparedStatement.setString(2, item.getTitolo());
			preparedStatement.setFloat(3, item.getDurata());
			preparedStatement.setString(4, item.getCantante());
			preparedStatement.setString(5, item.getImgBrano());
			preparedStatement.setString(6, item.getGenere());
			preparedStatement.setString(7, item.getTipo());
			preparedStatement.setString(8, item.getSuono());
			preparedStatement.setDate(9, item.getDat());
			preparedStatement.setFloat(10, item.getPrezzo());
			preparedStatement.setString(11, item.getDescrizione());
			
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
	public void doUpdate(String p1, String p2, Integer p3) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		if(p1.equals("nome")) {
		String updateSQL = "UPDATE Brano SET TITOLO=? where CODICE = ?";
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(updateSQL);
		}else if(p1.equals("artista")) {
			String updateSQL = "UPDATE Brano SET CANTANTE=? where CODICE = ?";
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
		}else if(p1.equals("img")) {
			String updateSQL = "UPDATE Brano SET IMGBRANO=? where CODICE = ?";
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
		}else if(p1.equals("tipo")) {
			String updateSQL = "UPDATE Brano SET TIPO=? where CODICE = ?";
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
		}else if(p1.equals("genere")) {
			String updateSQL = "UPDATE Brano SET GENERE=? where CODICE = ?";
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
		}else if(p1.equals("suono")) {
			String updateSQL = "UPDATE Brano SET SUONO=? where CODICE = ?";
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
		}else if(p1.equals("prezzo")) {
			String updateSQL = "UPDATE Brano SET PREZZO=? where CODICE = ?";
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
		}else if(p1.equals("descrizione")) {
			String updateSQL = "UPDATE Brano SET DESCRIZIONE=? where CODICE = ?";
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
		}
		try {
			
			preparedStatement.setString(1, p2);
			preparedStatement.setInt(2, p3);
			
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
	public void doUpdatePrezzo(String p1, Float p2, Integer p3) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		if(p1.equals("prezzo")) {
			String updateSQL = "UPDATE Brano SET PREZZO=? where CODICE = ?";
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
		}
		try {
			
			preparedStatement.setFloat(1, p2);
			preparedStatement.setInt(2, p3);
			
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

		String deleteSQL = "DELETE FROM Brano WHERE CODICE = ?";

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

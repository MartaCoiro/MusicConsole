package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import Class.Playlist;
import javax.sql.DataSource;
import it.unisa.utils.Utility;

public class PlaylistModelDS implements ProductModelPlaylist<Playlist> {

	private DataSource ds = null;
	
	public PlaylistModelDS(DataSource ds) {
		this.ds = ds;
	}
	
	@Override
	public Collection<Playlist> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL = "SELECT * FROM  playlist";
		
		Collection<Playlist> al = new LinkedList<Playlist>();
		
		try {
			connection = ds.getConnection(); //recupero connessione dal data source
			preparedStatement = connection.prepareStatement(selectSQL);
			
			Utility.print("doRetrieveAll: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();			
		
			while(rs.next()) {
				Playlist bean = new Playlist();
				bean.setId(rs.getInt("id"));
				bean.setNomeUtente(rs.getString("NomeUtente"));
				bean.setNome(rs.getString("nome"));
				bean.setNomeBrano(rs.getString("nomeBrano"));
				bean.setNumBrani(rs.getInt("numBrani"));
				bean.setNomeArtista(rs.getString("nomeArtista"));
				
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
	public Playlist doRetrieveByKey(String nome) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Playlist bean = new Playlist();
		
		String selectSQL = " SELECT * FROM  playlist  WHERE NOME	= ? ";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, nome);
		

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setNomeUtente(rs.getString("NomeUtente"));
				bean.setNome(rs.getString("nome"));
				bean.setNomeBrano(rs.getString("nomeBrano"));
				bean.setNumBrani(rs.getInt("numBrani"));
				bean.setId(rs.getInt("id"));
				bean.setNomeArtista(rs.getString("nomeArtista"));
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
	public void doSave(Playlist item) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO playlist (ID, NOMEUTENTE, NOME, NOMEBRANO, NUMBRANI, NOMEARTISTA) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, item.getId());
			preparedStatement.setString(2, item.getNomeUtente());
			preparedStatement.setString(3, item.getNome());
			preparedStatement.setString(4, item.getNomeBrano());
			preparedStatement.setInt(5, item.getNumBrani());
			preparedStatement.setString(6, item.getNomeArtista());
			
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
	public void doUpdate(Playlist item) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public synchronized boolean doDelete(String brano, String artista, String nplaylist, String nutente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM playlist WHERE NOMEBRANO = ? AND NOMEARTISTA = ? AND NOME = ? AND NOMEUTENTE = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, brano);
			preparedStatement.setString(2, artista);
			preparedStatement.setString(3, nplaylist);
			preparedStatement.setString(4, nutente);

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
	
	@Override
	public synchronized boolean doDeletep(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM playlist WHERE ID = ? ";

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
	

	@Override
	public synchronized boolean doDeleteProd(String brano, String artista) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM playlist WHERE NOMEBRANO = ? AND NOMEARTISTA = ? ";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, brano);
			preparedStatement.setString(2, artista);

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

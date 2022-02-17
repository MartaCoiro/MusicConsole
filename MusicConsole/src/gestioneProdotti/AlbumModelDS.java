package gestioneProdotti;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import it.unisa.utils.Utility;

public class AlbumModelDS implements ProductModelAlbum<Album> {

	private DataSource ds = null;
	
	public AlbumModelDS(DataSource ds) {
		this.ds = ds;
	}
	
	@Override
	public Collection<Album> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL = "SELECT * FROM  Album";
		
		Collection<Album> al = new LinkedList<Album>();
		
		try {
			connection = ds.getConnection(); //recupero connessione dal data source
			preparedStatement = connection.prepareStatement(selectSQL);
			
			Utility.print("doRetrieveAll: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();			
			
			while(rs.next()) {
				Album bean = new Album();
				bean.setNomeAlbum(rs.getString("nomeAlbum"));
				bean.setCodiceAlbum(rs.getInt("codiceAlbum"));
				bean.setImgAlbum(rs.getString("imgAlbum"));
				bean.setNartista(rs.getString("nartista"));
				bean.setTipo(rs.getString("tipo"));
				bean.setDat(rs.getDate("dataa"));
				bean.setPrezzoS(rs.getFloat("prezzoS"));
				bean.setPrezzoV(rs.getFloat("prezzoV"));
				bean.setPrezzoC(rs.getFloat("prezzoC"));
				bean.setDescrizione(rs.getString("descrizione"));
				
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
	public Album doRetrieveByKey(String nome, String artista) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL = " SELECT * FROM  Album  WHERE NOMEALBUM = ? AND NARTISTA = ? ";
		//Album bean;
		Album bean = new Album();
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, nome);
			preparedStatement.setString(2, artista);
		
			ResultSet rs = preparedStatement.executeQuery();

			//bean = new Album(rs.getInt("codiceAlbum"),rs.getString("nomeAlbum"),rs.getString("imgAlbum"),rs.getString("nartista"),rs.getString("tipo"),rs.getFloat("prezzoS"),rs.getFloat("prezzoV"),rs.getFloat("prezzoC"),rs.getString("descrizione"));
			//Album bean = new Album(0,"","","","",0.0f,0.0f,0.0f,"");
			
				while (rs.next()) {
					
				bean.setNomeAlbum(rs.getString("nomeAlbum"));
				bean.setCodiceAlbum(rs.getInt("codiceAlbum"));
				bean.setImgAlbum(rs.getString("imgAlbum"));
				bean.setNartista(rs.getString("nartista"));
				bean.setTipo(rs.getString("tipo"));
				bean.setDat(rs.getDate("dataa"));
				bean.setPrezzoS(rs.getFloat("prezzoS"));
				bean.setPrezzoV(rs.getFloat("prezzoV"));
				bean.setPrezzoC(rs.getFloat("prezzoC"));
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
	public void doSave(Album item) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO Album (CODICEALBUM, NOMEALBUM, IMGALBUM, nArtista, TIPO, DATAA, DESCRIZIONE, PREZZOS, PREZZOV, PREZZOC) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, item.getCodiceAlbum());
			preparedStatement.setString(2, item.getNomeAlbum());
			preparedStatement.setString(3, item.getImgAlbum());
			preparedStatement.setString(4, item.getNartista());
			preparedStatement.setString(5, item.getTipo());
			preparedStatement.setDate(6, item.getDat());
			preparedStatement.setString(7, item.getDescrizione());
			preparedStatement.setFloat(8, item.getPrezzoS());
			preparedStatement.setFloat(9, item.getPrezzoV());
			preparedStatement.setFloat(10, item.getPrezzoC());

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
			String updateSQL = "UPDATE Album SET NOMEALBUM=? where CODICEALBUM = ?";
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			}else if(p1.equals("artista")) {
				String updateSQL = "UPDATE Album SET NARTISTA=? where CODICEALBUM = ?";
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(updateSQL);
			}else if(p1.equals("img")) {
				String updateSQL = "UPDATE Album SET IMGALBUM=? where CODICEALBUM = ?";
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(updateSQL);
			}else if(p1.equals("tipo")) {
				String updateSQL = "UPDATE Album SET TIPO=? where CODICEALBUM = ?";
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(updateSQL);
			}else if(p1.equals("prezzoS")) {
				String updateSQL = "UPDATE Album SET PREZZOS=? where CODICEALBUM = ?";
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(updateSQL);
			}else if(p1.equals("prezzoV")) {
				String updateSQL = "UPDATE Album SET PREZZOV=? where CODICEALBUM = ?";
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(updateSQL);
			}else if(p1.equals("prezzoC")) {
				String updateSQL = "UPDATE Album SET PREZZOC=? where CODICEALBUM = ?";
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(updateSQL);
			}else if(p1.equals("descrizione")) {
				String updateSQL = "UPDATE Album SET DESCRIZIONE=? where CODICEALBUM = ?";
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
		
		if(p1.equals("prezzoS")) {
			String updateSQL = "UPDATE Album SET PREZZOS=? where CODICEALBUM = ?";
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
		}else if(p1.equals("prezzoV")) {
			String updateSQL = "UPDATE Album SET PREZZOV=? where CODICEALBUM = ?";
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
		}else if(p1.equals("prezzoC")) {
			String updateSQL = "UPDATE Album SET PREZZOC=? where CODICEALBUM = ?";
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

		String deleteSQL = "DELETE FROM Album WHERE CODICEALBUM = ?";

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



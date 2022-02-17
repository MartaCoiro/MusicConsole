package gestioneProdotti;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.utils.Utility;

public class PodcastModelDS implements ProductModelPodcast<Podcast>{

	//private DataSource ds = null;
	private Connection connection;
	
	/*public PodcastModelDS(DataSource ds) {
		this.ds = ds;
	}*/
	
	public PodcastModelDS(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public Collection<Podcast> doRetrieveAll() throws SQLException {
		//Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL = "SELECT * FROM  Podcast";
		
		Collection<Podcast> pd = new LinkedList<Podcast>();
		
		try {
			//connection = ds.getConnection(); //recupero connessione dal data source
			preparedStatement = connection.prepareStatement(selectSQL);
			
			Utility.print("doRetrieveAll: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();			
		
			while(rs.next()) {
				Podcast bean = new Podcast();
				bean.setNomePodcast(rs.getString("nomePodcast"));
				bean.setIdeatore(rs.getString("ideatore"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setDurata(rs.getFloat("durata"));
				bean.setNumEpisodi(rs.getInt("numeroEpisodi"));
				bean.setImgPod(rs.getString("imgPod"));
				bean.setTipo(rs.getString("tipo"));
				bean.setDat(rs.getDate("dataa"));
				bean.setPrezzo(rs.getFloat("prezzo"));
				
				pd.add(bean);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return pd;
	}

	@Override
	public Podcast doRetrieveByKey(String parola) throws SQLException {
		
		//Connection connection = null;
		PreparedStatement preparedStatement = null;

		//Podcast bean;
		Podcast bean = new Podcast();
		
		
		String selectSQL = " SELECT * FROM  Podcast  WHERE NOMEPODCAST = ? OR IDEATORE = ? ";
		
		try {
			//connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, parola);
			preparedStatement.setString(2, parola);

			ResultSet rs = preparedStatement.executeQuery();

			//bean = new Podcast(rs.getString("nomePodcast"),rs.getString("ideatore"),rs.getString("descrizione"),rs.getFloat("durata"),rs.getInt("numeroEpisodi"),rs.getString("imgPod"),rs.getString("tipo"),rs.getFloat("prezzo"));
			
			while (rs.next()) {
				bean.setNomePodcast(rs.getString("nomePodcast"));
				bean.setIdeatore(rs.getString("ideatore"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setDurata(rs.getFloat("durata"));
				bean.setNumEpisodi(rs.getInt("numeroEpisodi"));
				bean.setImgPod(rs.getString("imgPod"));
				bean.setTipo(rs.getString("tipo"));
				bean.setDat(rs.getDate("dataa"));
				bean.setPrezzo(rs.getFloat("prezzo"));
			}

		} catch(SQLException e) {
			e.printStackTrace();
		}
		return bean;
		}
	
	
	@Override
	public void doSave(Podcast item) throws SQLException {
		//Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO Podcast (NOMEPODCAST, IDEATORE, DESCRIZIONE, DURATA, NUMEROEPISODI, IMGPOD, TIPO, DATAA, PREZZO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			//connection = ds.getConnection();
			
			//connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, item.getNomePodcast());
			preparedStatement.setString(2, item.getIdeatore());
			preparedStatement.setString(3, item.getDescrizione());
			preparedStatement.setFloat(4, item.getDurata());
			preparedStatement.setInt(5, item.getNumEpisodi());
			preparedStatement.setString(6, item.getImgPod());
			preparedStatement.setString(7, item.getTipo());
			preparedStatement.setDate(8, item.getDat());
			preparedStatement.setFloat(9, item.getPrezzo());

			preparedStatement.executeUpdate();

			//connection.commit();
		} catch(SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void doUpdate(String p1, String p2, String p3) throws SQLException {
		//Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		if(p1.equals("artista")) {
			String updateSQL = "UPDATE Podcast SET IDEATORE=? where NOMEPODCAST = ?";
			//connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
		}else if(p1.equals("img")) {
			String updateSQL = "UPDATE Podcast SET IMGPOD=? where NOMEPODCAST = ?";
			//connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
		}else if(p1.equals("tipo")) {
			String updateSQL = "UPDATE Podcast SET TIPO=? where NOMEPODCAST = ?";
			//connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
		}else if(p1.equals("prezzo")) {
			String updateSQL = "UPDATE Podcast SET PREZZO=? where NOMEPODCAST = ?";
			//connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
		}else if(p1.equals("descrizione")) {
			String updateSQL = "UPDATE Podcast SET DESCRIZIONE=? where NOMEPODCAST = ?";
			//connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
		}
		try {
			
			preparedStatement.setString(1, p2);
			preparedStatement.setString(2, p3);
			
			 preparedStatement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doUpdatePrezzo(String p1, Float p2, String p3) throws SQLException {
		//Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		if(p1.equals("prezzo")) {
			String updateSQL = "UPDATE Podcast SET PREZZO=? where NomePodcast = ?";
			//connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
		}
		try {
			
			preparedStatement.setFloat(1, p2);
			preparedStatement.setString(2, p3);
			
			 preparedStatement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public synchronized boolean doDelete(String nomepod) throws SQLException {
		//Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM Podcast WHERE NOMEPODCAST = ?";

		try {
			//connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, nomepod);

			result = preparedStatement.executeUpdate();

		} catch(SQLException e) {
			e.printStackTrace();
		}
		return (result != 0);
	}
	
	@Override
	public Podcast Restituisci(String nome) throws SQLException {
		
		//Connection connection = null;
		PreparedStatement preparedStatement = null;

		//Podcast bean;
		Podcast bean = new Podcast();
		
		
		String selectSQL = " SELECT * FROM  Podcast  WHERE NOMEPODCAST = ? ";
		
		try {
			//connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, nome);

			ResultSet rs = preparedStatement.executeQuery();

			//bean = new Podcast(rs.getString("nomePodcast"),rs.getString("ideatore"),rs.getString("descrizione"),rs.getFloat("durata"),rs.getInt("numeroEpisodi"),rs.getString("imgPod"),rs.getString("tipo"),rs.getFloat("prezzo"));
			
			while (rs.next()) {
				bean.setNomePodcast(rs.getString("nomePodcast"));
				bean.setIdeatore(rs.getString("ideatore"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setDurata(rs.getFloat("durata"));
				bean.setNumEpisodi(rs.getInt("numeroEpisodi"));
				bean.setImgPod(rs.getString("imgPod"));
				bean.setTipo(rs.getString("tipo"));
				bean.setDat(rs.getDate("dataa"));
				bean.setPrezzo(rs.getFloat("prezzo"));
			}

		} catch(SQLException e) {
			e.printStackTrace();
		}
		return bean;
		}
	
	
}

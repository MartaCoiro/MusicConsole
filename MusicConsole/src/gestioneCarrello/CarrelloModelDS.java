package gestioneCarrello;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.utils.Utility;

public class CarrelloModelDS implements ProductModelCarrello<Carrello> {
	
	//private DataSource ds = null;
	private Connection connection;
	
	public CarrelloModelDS(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public Collection<Carrello> doRetrieveAll() throws SQLException {
		//Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL = "SELECT * FROM  Carrello";
		
		Collection<Carrello> br = new LinkedList<Carrello>();
		
		try {
			//connection = ds.getConnection(); //recupero connessione dal data source
			preparedStatement = connection.prepareStatement(selectSQL);
			
			Utility.print("doRetrieveAll: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();			
			
			while(rs.next()) {
				Carrello bean = new Carrello();
				//Carrello bean = new Carrello(0.0f,0,"","","","",0.0f);
				bean.setCod(rs.getInt("cod"));
				bean.setUtente(rs.getString("utente"));
				bean.setNome(rs.getString("nome"));
				bean.setAutore(rs.getString("autore"));
				bean.setTipo(rs.getString("tipo"));
				bean.setCosto(rs.getFloat("costo"));
				bean.setQuantit�(rs.getInt("quantit�"));
				bean.setTotq(rs.getFloat("totq"));
				
				br.add(bean);
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return br;
	}

	@Override
	public Carrello doRetrieveByKey(String parola) throws SQLException {
		
		//Connection connection = null;
		PreparedStatement preparedStatement = null;

		//Carrello bean;
		Carrello bean = new Carrello();
		
		
		String selectSQL = " SELECT * FROM  Carrello  WHERE nome = ? ";
		
		try {
			//connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, parola);

			ResultSet rs = preparedStatement.executeQuery();

			//bean = new Carrello(rs.getFloat("totq"),rs.getInt("quantit�"),rs.getString("utente"),rs.getString("nome"),rs.getString("autore"),rs.getString("tipo"),rs.getFloat("costo"));
			
			while (rs.next()) {
				bean.setCod(rs.getInt("cod"));
				bean.setNome(rs.getString("nome"));
				bean.setAutore(rs.getString("autore"));
				bean.setTipo(rs.getString("tipo"));
				bean.setCosto(rs.getFloat("costo"));
				bean.setUtente(rs.getString("utente"));
				bean.setQuantit�(rs.getInt("quantit�"));
				bean.setTotq(rs.getFloat("totq"));
				}

		} catch(SQLException e){
				e.printStackTrace();
		}
		return bean;
		}
	
	
	@Override
	public boolean doSave(Carrello item) {
		//Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO Carrello (COD, UTENTE, NOME, AUTORE, TIPO, COSTO, QUANTIT�, TOTQ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			//connection = ds.getConnection();
			
			//connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, item.getCod());
			preparedStatement.setString(2, item.getUtente());
			preparedStatement.setString(3, item.getNome());
			preparedStatement.setString(4, item.getAutore());
			preparedStatement.setString(5, item.getTipo());
			preparedStatement.setFloat(6, item.getCosto());
			preparedStatement.setInt(7, item.getQuantit�());
			preparedStatement.setFloat(8, item.getTotq());
		
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
	public boolean doUpdate(int quantit, float prez, int cod) throws SQLException {
		//Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE carrello SET QUANTIT� = ? , TOTQ = ? where COD = ?";
		//connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(updateSQL);
		
		try {
			
			preparedStatement.setInt(1, quantit);
			preparedStatement.setFloat(2, prez);
			preparedStatement.setInt(3, cod);
			
			 if(preparedStatement.executeUpdate()==0) {
				 return false;
			 }
			 return true;
			}
		catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public synchronized boolean doDelete(Integer code) throws SQLException {
		//Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM Carrello WHERE COD = ?";

		try {
			//connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);

			result = preparedStatement.executeUpdate();
			if(result==0) {
				return false;
			}

			return true;
			
		} catch(SQLException e){
			e.printStackTrace();
		}
		//return (result != 0);
		return false;
	}
	
	@Override
	public Float doSum(String utente) throws SQLException {
		
		//Connection connection = null;
		PreparedStatement preparedStatement = null;

		Float somma = 0.0f;
		
		String sumSQL = "SELECT SUM(totq) AS costototale FROM Carrello WHERE UTENTE = ?";
		
		try {
			//connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(sumSQL);
			preparedStatement.setString(1, utente);

			ResultSet result = preparedStatement.executeQuery();
			 while (result.next()) {
					somma = result.getFloat("costototale");
					String str = String.format("%.02f", somma);
					str = str.replace(",", ".");
					somma = Float.valueOf(str);
					}
			
		}catch(SQLException e){
			e.printStackTrace();
			}
		return somma;
	}
	
	@Override
	public  Collection<Carrello> RestXUtente(String utente) throws SQLException {
		
		//Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL = " SELECT * FROM  Carrello  WHERE utente = ? ";
		
		Collection<Carrello> br = new LinkedList<Carrello>();
		
		try {
			//connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, utente);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Carrello bean = new Carrello();
				//Carrello bean = new Carrello(rs.getFloat("totq"),rs.getInt("quantit�"),rs.getString("utente"),rs.getString("nome"),rs.getString("autore"),rs.getString("tipo"),rs.getFloat("costo"));
				bean.setCod(rs.getInt("cod"));
				bean.setNome(rs.getString("nome"));
				bean.setAutore(rs.getString("autore"));
				bean.setTipo(rs.getString("tipo"));
				bean.setCosto(rs.getFloat("costo"));
				bean.setUtente(rs.getString("utente"));
				bean.setQuantit�(rs.getInt("quantit�"));
				bean.setTotq(rs.getFloat("totq"));
				
				br.add(bean);
				}

		} catch(SQLException e){
			e.printStackTrace();
		}
		return br;
		}
	
}


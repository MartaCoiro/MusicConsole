package gestioneAcquisti;

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

public class OrdiniModelDS implements ProductModelOrdini<Ordini> {

private DataSource ds = null;
	
	public OrdiniModelDS(DataSource ds) {
		this.ds = ds;
	}
	
	@Override
	public Collection<Ordini> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL = "SELECT * FROM  Ordini";
		
		Collection<Ordini> br = new LinkedList<Ordini>();
		
		try {
			connection = ds.getConnection(); //recupero connessione dal data source
			preparedStatement = connection.prepareStatement(selectSQL);
			
			Utility.print("doRetrieveAll: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();			
		
			while(rs.next()) {
				Ordini bean = new Ordini();
				bean.setCod(rs.getInt("cod"));
				bean.setUtente(rs.getString("utente"));
				bean.setNome(rs.getString("nome"));
				bean.setAutore(rs.getString("autore"));
				bean.setTipo(rs.getString("tipo"));
				bean.setCosto(rs.getFloat("costo"));
				bean.setIndice(rs.getInt("indice"));
				bean.setData(rs.getDate("data"));
				bean.setTot(rs.getFloat("tot"));
				bean.setStato(rs.getString("stato"));
				bean.setQuantità(rs.getInt("quantità"));
				
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
	public Collection<Ordini> doRetrieveAllOrdinato() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL = "SELECT * FROM  Ordini ORDER BY data DESC";
		
		Collection<Ordini> br = new LinkedList<Ordini>();
		
		try {
			connection = ds.getConnection(); //recupero connessione dal data source
			preparedStatement = connection.prepareStatement(selectSQL);
			
			Utility.print("doRetrieveAllOrdinato: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();			
		
			while(rs.next()) {
				Ordini bean = new Ordini();
				bean.setCod(rs.getInt("cod"));
				bean.setUtente(rs.getString("utente"));
				bean.setNome(rs.getString("nome"));
				bean.setAutore(rs.getString("autore"));
				bean.setTipo(rs.getString("tipo"));
				bean.setCosto(rs.getFloat("costo"));
				bean.setIndice(rs.getInt("indice"));
				bean.setData(rs.getDate("data"));
				bean.setTot(rs.getFloat("tot"));
				bean.setStato(rs.getString("stato"));
				bean.setQuantità(rs.getInt("quantità"));
				
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
	public Collection<Ordini> doRetrieveByKey(String parola) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<Ordini> br = new LinkedList<Ordini>();
		
		
		String selectSQL = " SELECT * FROM  Ordini  WHERE utente = ? ORDER BY data DESC";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, parola);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Ordini bean = new Ordini();
				//Ordini bean = new Ordini(rs.getInt("quantità"),rs.getString("stato"),rs.getInt("indice"),rs.getString("utente"),rs.getString("nome"),rs.getString("autore"),rs.getString("tipo"),rs.getFloat("costo"),rs.getFloat("tot"));
				bean.setCod(rs.getInt("cod"));
				bean.setNome(rs.getString("nome"));
				bean.setAutore(rs.getString("autore"));
				bean.setTipo(rs.getString("tipo"));
				bean.setCosto(rs.getFloat("costo"));
				bean.setUtente(rs.getString("utente"));
				bean.setData(rs.getDate("data"));
				bean.setIndice(rs.getInt("indice"));
				bean.setTot(rs.getFloat("tot"));
				bean.setStato(rs.getString("stato"));
				bean.setQuantità(rs.getInt("quantità"));
				
				br.add(bean);
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
		return br;
		}
	
	
	@Override
	public void doSave(Ordini item) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO Ordini (COD, UTENTE, DATA, INDICE, NOME, AUTORE, TIPO, COSTO, TOT, STATO, QUANTITà) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, item.getCod());
			preparedStatement.setString(2, item.getUtente());
			preparedStatement.setDate(3, item.getData());
			preparedStatement.setInt(4, item.getIndice());
			preparedStatement.setString(5, item.getNome());
			preparedStatement.setString(6, item.getAutore());
			preparedStatement.setString(7, item.getTipo());
			preparedStatement.setFloat(8, item.getCosto());
			preparedStatement.setFloat(9, item.getTot());
			preparedStatement.setString(10, item.getStato());
			preparedStatement.setInt(11, item.getQuantità());
			
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
		return;
	}

	@Override
	public void doUpdate(String val, int ind) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE Ordini SET STATO = ? where INDICE = ?";
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			try {
				preparedStatement.setString(1, val);
				preparedStatement.setInt(2, ind);
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

		String deleteSQL = "DELETE FROM Ordini WHERE COD = ?";

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
	public Collection<Ordini> getIndici() throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = " SELECT indice FROM  Ordini ";
		Collection<Ordini> br = new LinkedList<Ordini>();
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Ordini bean = new Ordini();
				//Ordini bean = new Ordini(rs.getInt("quantità"),rs.getString("stato"),rs.getInt("indice"),rs.getString("utente"),rs.getString("nome"),rs.getString("autore"),rs.getString("tipo"),rs.getFloat("costo"),rs.getFloat("tot"));
				bean.setIndice(rs.getInt("indice"));
				br.add(bean);	
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
		return br;
		}
	
}

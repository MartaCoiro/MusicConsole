package gestioneProdotti;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.Collection;
	import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.utils.Utility;

public class imageModelDS implements ProductModelImage<image> {

		/*private DataSource ds;*/
		//private DataSource ds = null;
		private Connection connection;
		
		/*public imageModelDS(DataSource ds) {
			this.ds = ds;
			/*System.out.println(ds);
		}*/
		
		public imageModelDS(Connection connection) {
			this.connection = connection;
		}
		
		@Override
		public Collection<image> doRetrieveAll() throws SQLException {
			//Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			String selectSQL = "SELECT * FROM  image";
			
			/*if(order != null && !order.equals("")) {
				selectSQL += " ORDER BY " + order;
			}*/
			
			Collection<image> im = new LinkedList<image>();
			
			try {
				//connection = ds.getConnection(); //recupero connessione dal data source
				preparedStatement = connection.prepareStatement(selectSQL);
				
				Utility.print("doRetrieveAll: " + preparedStatement.toString());
				
				ResultSet rs = preparedStatement.executeQuery();			
			
				while(rs.next()) {
					image bean = new image();
					bean.setImageId(rs.getInt("imageId"));
					bean.setImageFileName(rs.getString("imageFileName"));
					
					im.add(bean);
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return im;
		}

		@Override
		public image doRetrieveByKey(String parola) throws SQLException {
			
			//Connection connection = null;
			PreparedStatement preparedStatement = null;

			image bean = new image();
			
			String selectSQL = " SELECT * FROM  image  WHERE IMAGEFILENAME = ? ";
			
			try {
				//connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, parola);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					bean.setImageId(rs.getInt("imageId"));
					bean.setImageFileName(rs.getString("imageFileName"));
				}

			} catch(SQLException e) {
				e.printStackTrace();
			
			}
			return bean;
			}
		
		
		@Override
		public void doSave(image item) throws SQLException {
			//Connection connection = null;
			PreparedStatement preparedStatement = null;

			String insertSQL = "INSERT INTO image (IMAGEID, IMAGEFILENAME) VALUES (?, ?)";

			try {
				//connection = ds.getConnection();
				
				//connection.setAutoCommit(false);
				
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setInt(1, item.getImageId());
				preparedStatement.setString(2, item.getImageFileName());

				preparedStatement.executeUpdate();

				//connection.commit();
			} catch(SQLException e) {
				e.printStackTrace();
			}

		}

		@Override
		public void doUpdate(image item) throws SQLException {
			// TODO Auto-generated method stub

		}

		@Override
		public synchronized boolean doDelete(int code) throws SQLException {
			//Connection connection = null;
			PreparedStatement preparedStatement = null;

			int result = 0;

			String deleteSQL = "DELETE FROM image WHERE IMAGEID = ?";

			try {
				//connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(deleteSQL);
				preparedStatement.setInt(1, code);

				result = preparedStatement.executeUpdate();

			} catch(SQLException e) {
				e.printStackTrace();
		
			}
			return (result != 0);
		}
		
}



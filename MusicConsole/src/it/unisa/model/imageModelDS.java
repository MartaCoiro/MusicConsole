package it.unisa.model;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.Collection;
	import java.util.LinkedList;
	import Class.image;
	import javax.sql.DataSource;

	import it.unisa.utils.Utility;

public class imageModelDS implements ProductModelImage<image> {

		/*private DataSource ds;*/
		private DataSource ds = null;
		
		public imageModelDS(DataSource ds) {
			this.ds = ds;
			/*System.out.println(ds);*/
		}
		
		@Override
		public Collection<image> doRetrieveAll() throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			String selectSQL = "SELECT * FROM  image";
			
			/*if(order != null && !order.equals("")) {
				selectSQL += " ORDER BY " + order;
			}*/
			
			Collection<image> im = new LinkedList<image>();
			
			try {
				connection = ds.getConnection(); //recupero connessione dal data source
				preparedStatement = connection.prepareStatement(selectSQL);
				
				Utility.print("doRetrieveAll: " + preparedStatement.toString());
				
				ResultSet rs = preparedStatement.executeQuery();			
			
				while(rs.next()) {
					image bean = new image();
					bean.setImageId(rs.getInt("imageId"));
					bean.setImageFileName(rs.getString("imageFileName"));
					
					im.add(bean);
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
			return im;
		}

		@Override
		public image doRetrieveByKey(String parola) throws SQLException {
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			image bean = new image();
			
			String selectSQL = " SELECT * FROM  image  WHERE IMAGEFILENAME = ? ";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, parola);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					bean.setImageId(rs.getInt("imageId"));
					bean.setImageFileName(rs.getString("imageFileName"));
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
		public void doSave(image item) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			String insertSQL = "INSERT INTO image (IMAGEID, IMAGEFILENAME) VALUES (?, ?)";

			try {
				connection = ds.getConnection();
				
				connection.setAutoCommit(false);
				
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setInt(1, item.getImageId());
				preparedStatement.setString(2, item.getImageFileName());

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
		public void doUpdate(image item) throws SQLException {
			// TODO Auto-generated method stub

		}

		@Override
		public synchronized boolean doDelete(int code) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			int result = 0;

			String deleteSQL = "DELETE FROM image WHERE IMAGEID = ?";

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



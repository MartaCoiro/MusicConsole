package gestioneProdotti;

import java.sql.SQLException;
import java.util.Collection;

public interface ProductModelAlbum<T> {

	public Album doRetrieveByKey(String nome, String artista) throws SQLException;
	
	public Collection<T> doRetrieveAll() throws SQLException;
	
	public boolean doSave(T item) throws SQLException;
	
	public boolean doUpdate(String p1, String p2, Integer p3) throws SQLException;
	
	public boolean doUpdatePrezzo(String p1, Float p2, Integer p3) throws SQLException;

	public boolean doDelete(int code) throws SQLException;
}

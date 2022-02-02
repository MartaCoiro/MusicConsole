package it.unisa.model;

import java.sql.SQLException;
import java.util.Collection;
import Class.Playlist;

public interface ProductModelPlaylist<T> {
	 
	public Playlist doRetrieveByKey(String parola) throws SQLException;
	
	public Collection<T> doRetrieveAll() throws SQLException;
	
	public void doSave(T item) throws SQLException;
	
	public void doUpdate(T item) throws SQLException;

	public boolean doDelete(String brano, String artista, String nplaylist, String nutente) throws SQLException;

	public boolean doDeletep(int code) throws SQLException;
	
	public boolean doDeleteProd(String brano,String artista) throws SQLException;
}

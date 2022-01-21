package it.unisa.model;

import java.sql.SQLException;
import java.util.Collection;

public interface ProductModelBrani<T> {

	public Brano doRetrieveByKey(String nome, String artista) throws SQLException;
	
	public Collection<T> doRetrieveAll() throws SQLException;
	
	public void doSave(T item) throws SQLException;
	
	public void doUpdate(String p1, String p2, Integer p3) throws SQLException;

	public void doUpdatePrezzo(String p1, Float p2, Integer p3) throws SQLException;
	
	public boolean doDelete(int code) throws SQLException;
}

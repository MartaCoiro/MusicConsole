package it.unisa.model;

import java.sql.SQLException;
import java.util.Collection;

public interface ProductModelGenere<T> {

	public GenereMusicale doRetrieveByKey(String parola) throws SQLException;
	
	public Collection<T> doRetrieveAll() throws SQLException;
	
	public void doSave(T item) throws SQLException;
	
	public void doUpdate(T item) throws SQLException;

	public boolean doDelete(int code) throws SQLException;
}

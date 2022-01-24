package it.unisa.model;


import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;


public interface ProductModelCarta<T> {

	public Carta doRetrieveByKey(int cvv) throws SQLException;
	
	public Collection<T> doRetrieveAll() throws SQLException;
	
	public void doSave(T item) throws SQLException;
	
	public void doUpdate(String data, int cvv) throws SQLException;
	
	public boolean doDelete(int cvv) throws SQLException;
}

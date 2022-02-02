package it.unisa.model;

import java.sql.SQLException;
import java.util.Collection;
import Class.Magazzino;

public interface ProductModelMagazzino<T> {

	public Magazzino doRetrieveByKey(int cod) throws SQLException;
	
	public Collection<T> doRetrieveAll() throws SQLException;
	
	public void doSave(T item) throws SQLException;
	
	public void doUpdate(int quantità, int cod) throws SQLException;

	public boolean doDelete(int cod) throws SQLException;

}

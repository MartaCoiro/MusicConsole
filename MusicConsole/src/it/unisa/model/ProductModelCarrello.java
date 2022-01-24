package it.unisa.model;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public interface ProductModelCarrello<T> {

	public Carrello doRetrieveByKey(String parola) throws SQLException;
	
	public Collection<T> doRetrieveAll() throws SQLException;
	
	public boolean doSave(T item) throws SQLException;
	
	public void doUpdate(int quantit,float prez, int cod) throws SQLException;

	public boolean doDelete(Integer code) throws SQLException;
	
	public Float doSum(String utente) throws SQLException;
	
	public Collection<T> RestXUtente(String utente) throws SQLException;
	
	
}




package gestioneProdotti;

import java.sql.SQLException;
import java.util.Collection;

public interface ProductModelPodcast<T>{

	public Podcast doRetrieveByKey(String parola) throws SQLException;
	
	public Collection<T> doRetrieveAll() throws SQLException;
	
	public boolean doSave(T item) throws SQLException;
	
	public boolean doUpdate(String p1, String p2, String p3) throws SQLException;

	public boolean doUpdatePrezzo(String p1, Float p2, String p3) throws SQLException;
	
	public boolean doDelete(String nomepod) throws SQLException;

	public Podcast Restituisci(String nome) throws SQLException;
}

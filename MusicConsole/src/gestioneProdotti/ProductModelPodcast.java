package gestioneProdotti;

import java.sql.SQLException;
import java.util.Collection;

public interface ProductModelPodcast<T>{

	public Podcast doRetrieveByKey(String parola) throws SQLException;
	
	public Collection<T> doRetrieveAll() throws SQLException;
	
	public void doSave(T item) throws SQLException;
	
	public void doUpdate(String p1, String p2, String p3) throws SQLException;

	public void doUpdatePrezzo(String p1, Float p2, String p3) throws SQLException;
	
	public boolean doDelete(String nomepod) throws SQLException;

	public Podcast Restituisci(String nome) throws SQLException;
}

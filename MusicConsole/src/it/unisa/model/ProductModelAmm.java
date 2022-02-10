package it.unisa.model;

import java.sql.SQLException;
import java.util.Collection;
import Class.Amm;

public interface ProductModelAmm<T> {//voglio renderla generica e la definisco su un tamplate

		public Amm doRetrieveByKey(String utente, String pass) throws SQLException;
		
		public Collection<T> doRetrieveAll(String order) throws SQLException;
		
		public void doSave(T item) throws SQLException;
		
		public void doUpdate(T item) throws SQLException;

		public boolean doDelete(String nickname) throws SQLException;
	}

package it.unisa.model;

import java.sql.SQLException;
import java.util.Collection;

public interface ProductModelImage<T> { //voglio renderla generica e la definisco su un tamplate

			public image doRetrieveByKey(String parola) throws SQLException;
			
			public Collection<T> doRetrieveAll() throws SQLException;
			
			public void doSave(T item) throws SQLException;
			
			public void doUpdate(T item) throws SQLException;

			public boolean doDelete(int code) throws SQLException;
		}

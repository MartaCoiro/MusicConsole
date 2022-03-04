package gestioneAccount;

import java.sql.SQLException;
import java.util.Collection;

public interface ProductModel<T> { //voglio renderla generica e la definisco su un tamplate

			public AccountUtente doRetrieveByKey(String utente, String pass) throws SQLException;
			
			public Collection<T> doRetrieveAll() throws SQLException;
			
			public boolean doSave(T item) throws SQLException;
			
			public boolean doUpdate(String p1, String p2) throws SQLException;

			public boolean doDelete(String utente) throws SQLException;
		}



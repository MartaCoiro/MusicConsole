package gestioneAccount;

import java.sql.SQLException;
import java.util.Collection;

public interface ProductModelProfilo<T> {

		public Profilo doRetrieveByKey(String nome) throws SQLException;
		
		public Collection<T> doRetrieveAll() throws SQLException;
		
		public void doSave(T item) throws SQLException;
		
		public void doUpdate(String p1, String p2, String p3) throws SQLException;

		public boolean doDelete(int code) throws SQLException;
}


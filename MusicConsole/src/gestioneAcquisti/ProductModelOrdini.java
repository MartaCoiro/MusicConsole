package gestioneAcquisti;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public interface ProductModelOrdini<T> {

		public Collection<T> doRetrieveByKey(String nome) throws SQLException;
		
		public Collection<T> doRetrieveAll() throws SQLException;
		
		public boolean doSave(T item) throws SQLException;
		
		public boolean doUpdate(String val, int ind) throws SQLException;
		
		public boolean doDelete(int code) throws SQLException;
	
		public Collection<T> getIndici() throws SQLException;
		
		public Collection<T> doRetrieveAllOrdinato() throws SQLException;
}

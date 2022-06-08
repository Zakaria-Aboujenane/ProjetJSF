package service;

import java.util.List;

public interface IDAO<T> {
	
	public boolean create(T o);
	public List<T> getAll();
	public boolean delete(int id);
	public T getById(int id);

}

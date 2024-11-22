package com.grishel.languageschool.server.dao;

import java.util.List;

public interface DAO<E> {
	public void save(E entity);
	public void delete(E entity);
	public E get(Integer id);
	public List<E> getAll();
}

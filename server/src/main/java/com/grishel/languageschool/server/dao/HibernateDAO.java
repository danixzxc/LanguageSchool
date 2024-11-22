package com.grishel.languageschool.server.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.grishel.languageschool.server.hibernate.HibernateUtil;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class HibernateDAO<E> implements JpaDAO<E> {

	private Class<E> clazz;
	
	public HibernateDAO(Class<E> clazz) {
		this.clazz = clazz;
	}

	@Override
	public void save(E entity) {
		System.out.println(HibernateUtil.getCurrentSession());
		System.out.println(HibernateUtil.getCurrentSession().getTransaction());
		HibernateUtil.getCurrentSession().persist(entity);
	}

	@Override
	public void delete(E entity) {
		HibernateUtil.getCurrentSession().remove(entity);
		
	}

	@Override
	public E get(Integer id) {
		return HibernateUtil.getCurrentSession().get(clazz, id);
	}

	@Override
	public List<E> getAll() {
		Session session = HibernateUtil.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
	    CriteriaQuery<E> cq = cb.createQuery(clazz);
	    Root<E> rootEntry = cq.from(clazz);
	    CriteriaQuery<E> all = cq.select(rootEntry);
	    Query<E> allQuery = session.createQuery(all);
	    return allQuery.getResultList();
	}

	@Override
	public CriteriaQuery<E> getCriteriaQuery() {
		CriteriaBuilder cb = HibernateUtil.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<E> cr = cb.createQuery(clazz);
		Root<E> root = cr.from(clazz);
		return cr.select(root);
	}

	@Override
	public List<E> getCriteriaQueryResultList(CriteriaQuery<E> cq) {
		TypedQuery<E> query = HibernateUtil.getCurrentSession().createQuery(cq);
		List<E> result;
		try {
			result = query.getResultList();
		}
		catch(NoResultException e){
			result = null;
		}
		return result;
	}

	@Override
	public E getCriteriaQuerySigleResult(CriteriaQuery<E> cq) {
		TypedQuery<E> query = HibernateUtil.getCurrentSession().createQuery(cq);
		E result;
		try {
			result = query.getSingleResult();
		}
		catch(NoResultException e){
			result = null;
		}
		return result;
	}

	@Override
	public CriteriaBuilder getCriteriaBuilder() {
		return HibernateUtil.getCurrentSession().getCriteriaBuilder();
	}

}

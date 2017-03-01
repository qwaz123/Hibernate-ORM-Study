package com.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.util.HibernateUtil;

public abstract class GenericHibernateDao<T, ID extends Serializable> implements GenericDao<T, ID> {

	protected Session session;
	protected final Class<T> entityClass;

	public GenericHibernateDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	public void setSession(Session s) {
		this.session = s;
	}

	public Session getSession() {
		if (session == null) {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		return session;
	}

	public CriteriaQuery<T> createQuery() {
		return getSession().getCriteriaBuilder().createQuery(getEntityClass());
	}

	public T findById(ID id) {
		return getSession().load(getEntityClass(), id);
	}

	public List<T> findAll() {
		// SELECT t FROM T t
		CriteriaQuery<T> cq = getSession().getCriteriaBuilder().createQuery(getEntityClass());

		Root<T> objects = cq.from(getEntityClass());
		cq.select(objects);
		TypedQuery<T> tq = getSession().createQuery(cq);

		return tq.getResultList();
	}

	public T makePersistent(T entity) {
		getSession().saveOrUpdate(entity);
		return entity;
	}

	public void makeTransient(T entity) {
		getSession().delete(entity);
	}

	public void flush() {
		getSession().flush();
	}

	public void clear() {
		getSession().clear();
	}
	
	public long count() {
		// select count( t.id )from T t 
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		
		Root<T> t = cq.from(getEntityClass());
		cq.select(cb.count(t));
		TypedQuery<Long> tq = getSession().createQuery(cq);
		
		return tq.getSingleResult();
	}	
	public T findBy(String columnName, String arg){
		// select t from T t where t.columnName = arg
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(getEntityClass());
		
		Root<T> t = cq.from(getEntityClass());
		cq.select(t);
		cq.where(cb.equal(t.get(columnName), arg));
		TypedQuery<T> tq = getSession().createQuery(cq);
		
		return getSingleResultOrNull(tq);
	}
	public List<T> getLimitResult(int first, int length) {
		// select t from T t limit first, length
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(getEntityClass());
		
		Root<T> t = cq.from(getEntityClass());
		cq.select(t);
		TypedQuery<T> tq = getSession().createQuery(cq);
		
		return tq.setMaxResults(length).setFirstResult(first).getResultList();		
	}	
	/*
	 * A helper method.
	 * Because if there is no result, TypedQuery#getSingleResult() will throw NoResultException
	 * */
	public static <T> T getSingleResultOrNull(TypedQuery<T> query) {
		List<T> results = query.getResultList();
		if (results.isEmpty()) {
			return null;
		} else if (results.size() == 1) {
			return results.get(0);
		}
		throw new NonUniqueResultException();
	}
}

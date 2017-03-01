package com.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, ID extends Serializable> {

    T findById(ID id);

    List<T> findAll();
    
    List<T> getLimitResult(int first, int length);

    T makePersistent(T entity);

    void makeTransient(T entity);
    
    long count();
}
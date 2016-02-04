/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.vflores3.fp.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Vanessa
 */
public abstract class AbstractService<T> {

    @PersistenceContext(name = "vflores3PU")
    protected EntityManager em;

    private Class<T> entityClass;

    /**
     * Constructor for AbstractService
     * @param entityClass 
     */
    public AbstractService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Method to create an entity of its class type
     * @param entity 
     */
    public void create(T entity) {
        em.persist(entity);
    }

    /**
     * Method to find all data of its class type
     * @return 
     */
    public abstract List<T> findAll();

    /**
     * Method to find a specific entity of its class type
     * @param id
     * @return 
     */
    public T find(Object id) {
        return em.find(entityClass, id);
    }

    /**
     * Method to update an entity of its class type
     * @param entity 
     */
    public void update(T entity) {
        em.merge(entity);
    }

    /**
     * Method to delete an entity of its class type
     * @param entity 
     */
    public void delete(T entity) {
        em.remove(entity);
    }

}

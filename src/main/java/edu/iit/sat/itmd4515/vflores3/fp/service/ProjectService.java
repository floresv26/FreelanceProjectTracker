/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.vflores3.fp.service;

import edu.iit.sat.itmd4515.vflores3.fp.domain.Customer;
import edu.iit.sat.itmd4515.vflores3.fp.domain.Project;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Vanessa
 */
@Stateless
public class ProjectService extends AbstractService<Project> {

    /**
     * Constructor
     */
    public ProjectService() {
        super(Project.class);
    }

    /**
     * Method to find all addresses from database
     *
     * @return
     */
    @Override
    public List<Project> findAll() {
        return em.createNamedQuery("Project.findAllProjects", Project.class).getResultList();
    }

    /**
     * Method to find a project by its project number
     *
     * @param projectNumber
     * @return
     */
    public Project findByProjectNumber(String projectNumber) {
        TypedQuery<Project> query = em.createNamedQuery("Project.pProjectNumber", Project.class);
        query.setParameter("pProjectNumber", projectNumber);
        return query.getSingleResult();
    }
    
    /**
     * Method to find a project by username
     * @param username
     * @return 
     */
    public Project findByUsername(String username){
        TypedQuery<Project> query = em.createNamedQuery("Project.findByUsername", Project.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }
    
    public void create(Project project, Customer customer) {
        customer = em.getReference(Customer.class, customer.getCustomerId());
        
        // set both sides of the relationship
        customer.addProject(project);
        
        // persist the new farm
        em.persist(project);
    }
    
    /**
     * Create a new project 
     * 
     * @param project
     */
    public void create(Project project) {
        
        em.persist(project);
    }
    
    /**
     * Delete an existing project 
     * 
     * @param project 
     */
    public void delete(Project project){
        project = em.getReference(Project.class, project.getProjectId());
        
        em.remove(project);
    }

}

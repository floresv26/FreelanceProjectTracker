/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.vflores3.fp.service;

import edu.iit.sat.itmd4515.vflores3.fp.domain.Employees;
import edu.iit.sat.itmd4515.vflores3.fp.security.Group;
import edu.iit.sat.itmd4515.vflores3.fp.security.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Vanessa
 */
@Stateless
public class UserService extends AbstractService<User> {

    /**
     * Constructor
     */
    public UserService() {
        super(User.class);
    }

    /**
     * Method to find all users
     *
     * @return
     */
    @Override
    public List<User> findAll() {
        return em.createNamedQuery("User.findAllUsers").getResultList();
    }

    /**
     * Method to find a specific user by username
     *
     * @param username
     * @return
     */
    public User findByUsername(String username) {
        TypedQuery<User> query = em.createNamedQuery("User.findByUsername", User.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

    /**
     * Create a new user and associate with a group and employee
     *
     * @param user
     * @param empId
     */
    public void create(User user, Long empId) {
        Employees employee = em.getReference(Employees.class, empId);

        // set both sides of the relationship
        employee.setUser(user);

        // persist the new user
        em.persist(user);
    }

    /**
     * Delete an existing user and remove from a group and employee
     *
     * @param user
     */
    public void delete(User user) {
        //employee = em.getReference(Employees.class, employee.getEmployeeId());
        user = em.getReference(User.class, user.getUserName());

        user.setGroup(null);

        em.remove(user);
    }

}

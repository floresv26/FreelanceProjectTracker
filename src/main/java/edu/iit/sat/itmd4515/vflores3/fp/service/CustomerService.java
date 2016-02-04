/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.vflores3.fp.service;

import edu.iit.sat.itmd4515.vflores3.fp.domain.Customer;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.TypedQuery;

/**
 *
 * @author Vanessa
 */
@Named
@Stateless
public class CustomerService extends AbstractService<Customer> {

    /**
     * Constructor
     */
    public CustomerService() {
        super(Customer.class);
    }

    /**
     * Method to find all customers from database
     *
     * @return
     */
    @Override
    public List<Customer> findAll() {
        return em.createNamedQuery("Customer.findAllCustomers", Customer.class).getResultList();
    }

    /**
     * Method to find a company or list of companies by the company name
     *
     * @param companyName
     * @return
     */
    public List<Customer> findByCompanyName(String companyName) {
        TypedQuery<Customer> query = em.createNamedQuery("Customer.findByCompanyName", Customer.class);
        query.setParameter("cCompanyName", companyName);
        return query.getResultList();
    }

    /**
     * Method to find an exact company by the company name and the company
     * representatives first and last name
     *
     * @param companyName
     * @param custRepresentative
     * @return
     */
    public Customer findExactCompany(String companyName, String custRepresentative) {
        TypedQuery<Customer> query = em.createNamedQuery("Customer.findExactCompany", Customer.class);
        query.setParameter("cCompanyName", companyName);
        query.setParameter("cCustRepresentative", custRepresentative);
        return query.getSingleResult();
    }

    /**
     * Method to find an exact user by their username
     *
     * @param username
     * @return
     */
    public Customer findByUsername(String username) {
        TypedQuery<Customer> query = em.createNamedQuery("Customer.findByUsername", Customer.class);
        query.setParameter("cUserName", username);
        return query.getSingleResult();
    }

    /**
     * Create a new customer
     *
     * @param customer
     */
    public void create(Customer customer) {
        em.persist(customer);
    }

    public void update(Customer newCustomer) {

        // first, get a reference to the old customer 
        Customer currentCustomer = em.getReference(Customer.class, newCustomer.getCustomerId());

        // make the updates from the user
        currentCustomer.setCompanyName(newCustomer.getCompanyName());
        currentCustomer.setCustPhone(newCustomer.getCustPhone());
        currentCustomer.setCustRepresentative(newCustomer.getCustRepresentative());
        currentCustomer.setCustSinceDate(newCustomer.getCustSinceDate());
        currentCustomer.setUser(newCustomer.getUser());
    }

    /**
     * Delete an existing customer and remove from a customer
     *
     * @param customer
     */
    public void delete(Customer customer) {
        customer = em.getReference(Customer.class, customer.getCustomerId());

        em.remove(customer);
    }

}

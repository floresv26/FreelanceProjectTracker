/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.vflores3.fp.service;

import edu.iit.sat.itmd4515.vflores3.fp.domain.Address;
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
public class AddressService extends AbstractService<Address> {

    /**
     * Constructor
     */
    public AddressService() {
        super(Address.class);
    }

    /**
     * Method to find all addresses from database
     *
     * @return
     */
    @Override
    public List<Address> findAll() {
        return em.createNamedQuery("Address.findAllAddresses", Address.class).getResultList();
    }

    /**
     * Method to find a list of addresses by city
     *
     * @param city
     * @return
     */
    public List<Address> findByCity(String city) {
        TypedQuery<Address> query = em.createNamedQuery("Address.findByCity", Address.class);
        query.setParameter("acity", city);
        return query.getResultList();
    }

    /**
     * Method to find a list of addresses by state
     *
     * @param state
     * @return
     */
    public List<Address> findByState(String state) {
        TypedQuery<Address> query = em.createNamedQuery("Address.findByState", Address.class);
        query.setParameter("aAddState", state);
        return query.getResultList();
    }

    /**
     * Method to find a list of addresses by zip code
     *
     * @param zip
     * @return
     */
    public List<Address> findByZipCode(String zip) {
        TypedQuery<Address> query = em.createNamedQuery("Address.findByZip", Address.class);
        query.setParameter("azip", zip);
        return query.getResultList();
    }

    /**
     * Method to find an exact address
     *
     * @param street1
     * @param street2
     * @param city
     * @param state
     * @param zip
     * @return
     */
    public Address findExactAddress(String street1, String street2, String city, String state, String zip) {
        TypedQuery<Address> query = em.createNamedQuery("Address.findExactAddress", Address.class);
        query.setParameter("astreet1", street1);
        query.setParameter("astreet2", street2);
        query.setParameter("acity", city);
        query.setParameter("aAddState", state);
        query.setParameter("azip", zip);
        return query.getSingleResult();
    }

}

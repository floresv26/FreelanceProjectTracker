/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.vflores3.fp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Vanessa
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Address.findAllAddresses", query = "select a from Address a"),
    @NamedQuery(name = "Address.findByCity", query = "select a from Address a where a.city = :acity"),
    @NamedQuery(name = "Address.findByState", query = "select a from Address a where a.addState = :aAddState"),
    @NamedQuery(name = "Address.findByZip", query = "select a from Address a where a.zip = :azip"),
    @NamedQuery(name = "Address.findExactAddress", 
            query = "select a from Address a where a.street1 = :astreet1 "
                    + "and a.street2 = :astreet2 and a.city = :acity and "
                    + "a.addState = :astate and a.zip = :azip")
})
/**
 * Address entity
 */
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "address")
    private Long addressId;
    private String street1;
    private String street2;
    private String city;
    private String addState;
    private String zip;

    /**
     * Default no arg constructor
     */
    public Address() {
    }

    /**
     * Constructor for Address entity with full parameters
     *
     * @param street1
     * @param street2
     * @param city
     * @param addState
     * @param zip
     */
    public Address(String street1, String street2, String city, String addState, String zip) {
        this.street1 = street1;
        this.street2 = street2;
        this.city = city;
        this.addState = addState;
        this.zip = zip;
    }

    /**
     * Get the value of zip
     *
     * @return the value of zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * Set the value of zip
     *
     * @param zip new value of zip
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * Get the value of addState
     *
     * @return the value of addState
     */
    public String getAddState() {
        return addState;
    }

    /**
     * Set the value of addState
     *
     * @param addState new value of addState
     */
    public void setAddState(String addState) {
        this.addState = addState;
    }

    /**
     * Get the value of city
     *
     * @return the value of city
     */
    public String getCity() {
        return city;
    }

    /**
     * Set the value of city
     *
     * @param city new value of city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Get the value of street2
     *
     * @return the value of street2
     */
    public String getStreet2() {
        return street2;
    }

    /**
     * Set the value of street2
     *
     * @param street2 new value of street2
     */
    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    /**
     * Get the value of street1
     *
     * @return the value of street1
     */
    public String getStreet1() {
        return street1;
    }

    /**
     * Set the value of street1
     *
     * @param street1 new value of street1
     */
    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    /**
     * Get the value of addressId
     *
     * @return the value of addressId
     */
    public Long getAddressId() {
        return addressId;
    }

    /**
     * Set the value of addressId
     *
     * @param addressId new value of addressId
     */
    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    /**
     * toString method
     * @return 
     */
    @Override
    public String toString() {
        return "Address{" + "addressId=" + addressId + ", street1=" + street1 + ", street2=" + street2 + ", city=" + city + ", addState=" + addState + ", zip=" + zip + '}';
    }

}

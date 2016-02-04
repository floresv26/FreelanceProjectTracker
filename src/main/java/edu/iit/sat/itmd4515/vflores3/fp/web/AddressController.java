/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.vflores3.fp.web;

import edu.iit.sat.itmd4515.vflores3.fp.domain.Address;
import edu.iit.sat.itmd4515.vflores3.fp.service.AddressService;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Vanessa
 */
@Named
@RequestScoped
public class AddressController extends AbstractJSFController {

    private static final Logger LOG = Logger.getLogger(AddressController.class.getName());

    @EJB
    AddressService addressService;
    @Inject
    LoginController loginController;
    
    private Address address;
    private List<Address> addresses;

    /**
     * Default constructor for AddressController
     */
    public AddressController() {
    }

    @Override
    protected void postConstruct() {
        super.postConstruct(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Get the value of addresses
     *
     * @return the value of addresses
     */
    public List<Address> getAddresses() {
        return addresses;
    }

    /**
     * Set the value of addresses
     *
     * @param addresses new value of addresses
     */
    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    /**
     * Get the value of address
     *
     * @return the value of address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Set the value of address
     *
     * @param address new value of address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

}

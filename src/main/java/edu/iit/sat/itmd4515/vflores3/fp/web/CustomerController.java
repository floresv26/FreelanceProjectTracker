/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.vflores3.fp.web;

import edu.iit.sat.itmd4515.vflores3.fp.domain.Customer;
import edu.iit.sat.itmd4515.vflores3.fp.domain.Project;
import edu.iit.sat.itmd4515.vflores3.fp.service.CustomerService;
import edu.iit.sat.itmd4515.vflores3.fp.service.ProjectService;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Vanessa
 */
@Named
@RequestScoped
public class CustomerController extends AbstractJSFController {

    private static final Logger LOG = Logger.getLogger(CustomerController.class.getName());

    @EJB
    CustomerService customerService;
    @Inject
    LoginController loginController;

    private Customer customer;
    private List<Customer> customers;
    private String compName;

    /**
     * Default constructor for CustomerController
     */
    public CustomerController() {
    }

    @Override
    @PostConstruct
    protected void postConstruct() {
        customer = new Customer();
        if (loginController.isAdmin() || loginController.isManager()) {
            customers = customerService.findAll();
        }
        super.postConstruct();
    }

    /**
     * Prepare to view all customers
     *
     *
     * @return the page on which all customers will be displayed to the user
     */
    public String doViewAllCustomers() {
        LOG.info("Preparing to display all customers");
        return "viewClients";
    }

    /**
     * Prepare a new customer to be created by the user
     *
     * @return the page on which the new customer will be created
     */
    public String doCreateCustomer() {
        LOG.info("Preparing to create a new customer");
        customer = new Customer();
        return "newClient";
    }

    /**
     * Refresh the collection of customers
     */
    public void refreshCustomers() {
        customers = customerService.findAll();
    }

    /**
     * Handle the action from newClient view
     *
     * @return
     */
    public String executeCreateCustomer() {
        LOG.info("Preparing to create " + customer.toString());
        // create the customer
        customerService.create(customer);
        facesContext.addMessage(null, new FacesMessage("Employee " + customer.getCompanyName() + " has been created."));
        // refresh customers;
        refreshCustomers();
        return "viewClients";
    }

    /**
     * Prepare the selected customer to be displayed in more detail
     *
     * @param customer the customer to display
     * @return the page on which the customer will be displayed to the user with
     * more detail
     */
    public String doViewCustomer(Customer customer) {
        LOG.info("Preparing to display " + customer.toString());
        this.customer = customer;
        return "viewClient";
    }

    /**
     * Prepare the selected customer to be updated
     *
     * @param customer the employee to be updated
     * @return the page on which the customer will be displayed for update
     */
    public String doUpdateCustomer(Customer customer) {
        LOG.info("Preparing to update " + customer.toString());
        this.customer = customer;
        return "editClient";
    }

    /**
     * Handle the action from editClient view
     *
     * @return
     */
    public String executeUpdateCustomer() {
        LOG.info("Preparing to update " + customer.toString());
        // Update the project
        customerService.update(customer);
        facesContext.addMessage(null, new FacesMessage("Customer " + customer.getCompanyName() + " has been updated."));
        // Refresh the collection
        refreshCustomers();
        return "viewClients";
    }

    /**
     * Prepare the selected customer for deletion, and call the delete
     *
     * @param customer the customer to be deleted
     * @return sends the user back to the welcome page with a message
     */
    public String doDeleteCustomer(Customer customer) {
        LOG.info("Preparing to delete " + customer.toString());
        // delete the project
        customerService.delete(customer);
        facesContext.addMessage(null, new FacesMessage("Customer " + customer.getCompanyName() + " has been deleted."));
        refreshCustomers();
        return "viewClients";
    }

    /**
     * Method to get the compName as a parameter
     *
     * @return
     */
    public String getCompNameParameter() {
        FacesContext fc = FacesContext.getCurrentInstance();
        compName = getCompName(fc);
        return compName;
    }

    /**
     * Method to get value from parameter
     *
     * @param fc
     * @return
     */
    public String getCompName(FacesContext fc) {

        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("compName");

    }

    /**
     * Prepare the selected customer to be displayed in more detail
     *
     * @return the page on which the customer will be displayed to the user with
     * more detail
     */
    public String showByCompName() {
        LOG.info("Preparing to display " + customer.toString());
        getCompNameParameter();

        return "viewClient";
    }

    /**
     * Get the value of customers
     *
     * @return the value of customers
     */
    public List<Customer> getCustomers() {
        return customers;
    }

    /**
     * Set the value of customers
     *
     * @param customers new value of customers
     */
    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    /**
     * Get the value of customer
     *
     * @return the value of customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Set the value of customer
     *
     * @param customer new value of customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Get the value of compName
     *
     * @return the value of compName
     */
    public String getCompName() {
        return compName;
    }

    /**
     * Set the value of compName
     *
     * @param compName new value of compName
     */
    public void setCompName(String compName) {
        this.compName = compName;
    }

}

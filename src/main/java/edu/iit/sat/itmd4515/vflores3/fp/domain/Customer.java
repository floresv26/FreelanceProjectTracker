/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.vflores3.fp.domain;

import edu.iit.sat.itmd4515.vflores3.fp.security.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Vanessa
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Customer.findAllCustomers", query = "select c from Customer c"),
    @NamedQuery(name = "Customer.findByCompanyName", query = "select c from Customer c where c.companyName = :cCompanyName"),
    @NamedQuery(name = "Customer.findExactCompany", query = "select c from Customer c where c.companyName = :cCompanyName"
            + " and c.custRepresentative = :cCustRepresentative"),
    @NamedQuery(name = "Customer.findByUsername", query = "select c from Customer c where c.user.userName = :cUserName")
})
/**
 * Customer entity
 */
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "customer")
    private Long customerId;
    private String companyName;
    private String custRepresentative;
    private String custPhone;
    @Temporal(value = TemporalType.DATE)
    private Date custSinceDate;
    /*@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
     @JoinTable(name = "customer_address",
     joinColumns = @JoinColumn(name = "customerId"),
     inverseJoinColumns = @JoinColumn(name = "addressId"))
     private List<Address> addresses = new ArrayList<>();*/
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "customer")
    private List<Project> projects = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "username")
    private User user;

    /**
     * Default no arg constructor
     */
    public Customer() {
    }

    /**
     * Constructor for Customer entity with full arguments
     *
     * @param companyName
     * @param custRepresentative
     * @param custPhone
     * @param custSinceDate
     */
    public Customer(String companyName, String custRepresentative, String custPhone, Date custSinceDate) {
        this.companyName = companyName;
        this.custRepresentative = custRepresentative;
        this.custPhone = custPhone;
        this.custSinceDate = custSinceDate;
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

    /**
     * Get the value of custSinceDate
     *
     * @return the value of custSinceDate
     */
    public Date getCustSinceDate() {
        return custSinceDate;
    }

    /**
     * Set the value of custSinceDate
     *
     * @param custSinceDate new value of custSinceDate
     */
    public void setCustSinceDate(Date custSinceDate) {
        this.custSinceDate = custSinceDate;
    }

    /**
     * Get the value of custPhone
     *
     * @return the value of custPhone
     */
    public String getCustPhone() {
        return custPhone;
    }

    /**
     * Set the value of custPhone
     *
     * @param custPhone new value of custPhone
     */
    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    /**
     * Get the value of custRepresentative
     *
     * @return the value of custRepresentative
     */
    public String getCustRepresentative() {
        return custRepresentative;
    }

    /**
     * Set the value of custRepresentative
     *
     * @param custRepresentative new value of custRepresentative
     */
    public void setCustRepresentative(String custRepresentative) {
        this.custRepresentative = custRepresentative;
    }

    /**
     * Get the value of companyName
     *
     * @return the value of companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Set the value of companyName
     *
     * @param companyName new value of companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * Get the value of customerId
     *
     * @return the value of customerId
     */
    public Long getCustomerId() {
        return customerId;
    }
    
    /**
     * Set the value of customerId
     *
     * @param customerId new value of customerId
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * Get the value of user
     *
     * @return the value of user
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the value of user
     *
     * @param user new value of user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Get the value of projects
     *
     * @return the value of projects
     */
    public List<Project> getProjects() {
        return projects;
    }

    /**
     * Set the value of project
     *
     * @param projects new value of project
     */
    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    /**
     * Method that adds a project to the customer projects list
     *
     * @param project
     */
    public void addProject(Project project) {
        if (!projects.contains(project)) {
            projects.add(project);
        }
    }

    /**
     * toString method
     *
     * @return
     */
    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + ", companyName=" + companyName + ", custRepresentative=" + custRepresentative + ", custPhone=" + custPhone + ", custSinceDate=" + custSinceDate + ", address=" + address + '}';
    }

}

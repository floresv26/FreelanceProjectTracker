/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.vflores3.fp.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Vanessa
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Project.findAllProjects", query = "select p from Project p"),
    @NamedQuery(name = "Project.findByProjectNumber", query = "select p from Project p where p.projectNumber = :pProjectNumber")
})
/**
 * Project entity
 */
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "project")
    private Long projectId;
    private String projectNumber;
    private String projectName;
    @Temporal(TemporalType.TIMESTAMP)
    private Date projStartDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date projEndDate;
    private String projectDescription;
    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
    @JoinTable(name = "Customer_Project",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id"))
    private Customer customer;
    @ManyToMany(mappedBy = "projects", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Employees> employeesOnProject = new ArrayList<>();

    /**
     * Default no arg constructor
     */
    public Project() {
    }

    /**
     * Constructor for Project entity full parameters
     *
     * @param projectNumber
     * @param projectName
     * @param projStartDate
     * @param projEndDate
     * @param projectDescription
     */
    public Project(String projectNumber, String projectName, Date projStartDate, Date projEndDate, String projectDescription) {
        this.projectNumber = projectNumber;
        this.projectName = projectName;
        this.projStartDate = projStartDate;
        this.projEndDate = projEndDate;
        this.projectDescription = projectDescription;
    }

    /**
     * Get the value of employeesOnProject
     *
     * @return the value of employeesOnProject
     */
    public List<Employees> getEmployeesOnProject() {
        return employeesOnProject;
    }

    /**
     * Set the value of employeesOnProject
     *
     * @param employeesOnProject new value of employeesOnProject
     */
    public void setEmployeesOnProject(List<Employees> employeesOnProject) {
        this.employeesOnProject = employeesOnProject;
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
     * Get the value of projectDescription
     *
     * @return the value of projectDescription
     */
    public String getProjectDescription() {
        return projectDescription;
    }

    /**
     * Set the value of projectDescription
     *
     * @param projectDescription new value of projectDescription
     */
    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    /**
     * Get the value of projEndDate
     *
     * @return the value of projEndDate
     */
    public Date getProjEndDate() {
        return projEndDate;
    }

    /**
     * Set the value of projEndDate
     *
     * @param projEndDate new value of projEndDate
     */
    public void setProjEndDate(Date projEndDate) {
        this.projEndDate = projEndDate;
    }

    /**
     * Get the value of projStartDate
     *
     * @return the value of projStartDate
     */
    public Date getProjStartDate() {
        return projStartDate;
    }

    /**
     * Set the value of projStartDate
     *
     * @param projStartDate new value of projStartDate
     */
    public void setProjStartDate(Date projStartDate) {
        this.projStartDate = projStartDate;
    }

    /**
     * Get the value of projectName
     *
     * @return the value of projectName
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Set the value of projectName
     *
     * @param projectName new value of projectName
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Get the value of projectNumber
     *
     * @return the value of projectNumber
     */
    public String getProjectNumber() {
        return projectNumber;
    }

    /**
     * Set the value of projectNumber
     *
     * @param projectNumber new value of projectNumber
     */
    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    /**
     * Get the value of projectId
     *
     * @return the value of projectId
     */
    public Long getProjectId() {
        return projectId;
    }

    /**
     * Set the value of projectId
     *
     * @param projectId new value of projectId
     */
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    /**
     * Helper method to add employee to employeesOnProject list
     *
     * @param employee
     */
    public void addEmployeesToProject(Employees employee) {
        if (!employeesOnProject.contains(employee)) {
            employeesOnProject.add(employee);
        }
        
        /*if(!employee.getProjects().contains(this)) {
            employee.getProjects().add(this);
        }*/
    }

    /**
     * Helper method that sets a customer to the project and the project to the
     * customer
     *
     * @param customer
     */
    public void addCustomerToProject(Customer customer) {
        setCustomer(customer);

        if (!customer.getProjects().contains(this)) {
            customer.getProjects().add(this);
        }
    }

    /**
     * toString() method
     *
     * @return
     */
    @Override
    public String toString() {
        return "Project{" + "projectId=" + projectId + ", projectNumber=" + projectNumber + ", projectName=" + projectName + ", projStartDate=" + projStartDate + ", projEndDate=" + projEndDate + ", projectDescription=" + projectDescription + ", customer=" + customer + '}';
    }

}

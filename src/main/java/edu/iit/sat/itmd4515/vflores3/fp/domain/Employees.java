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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
    @NamedQuery(name = "Employees.findAllEmployees", query = "select e from Employees e"),
    @NamedQuery(name = "Employees.findByEmployeeId", query = "select e from Employees e where e.employeeId = :eEmployeeId"),
    @NamedQuery(name = "Employees.findExactEmployee", query = "select e from Employees e where e.empSSNumber = :eEmpSSNumber"),
    @NamedQuery(name = "Employees.findByEmpName", query = "select e from Employees e where e.empFirstName = :eEmpFirstName"
            + " and e.empLastName = :eEmpLastName"),
    @NamedQuery(name = "Employees.findByUsername", query = "select e from Employees e where e.user.userName = :eUserName")
})
/**
 * Employees entity
 */
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "employees")
    private Long employeeId;
    private String empFirstName;
    private String empLastName;
    private String empPhone;
    private String empSSNumber;
    private double empSalary;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "jobType_fk")
    private JobType jobType;
    @Temporal(TemporalType.DATE)
    private Date empBirthDate;
    @Temporal(TemporalType.DATE)
    private Date employementStartDate;
    @Temporal(TemporalType.DATE)
    private Date employmentEndDate;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "address_id")
    private Address address;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "Employee_Skill",
            joinColumns = @JoinColumn(name = "employeeId"),
            inverseJoinColumns = @JoinColumn(name = "skillId"))
    private List<Skill> skills = new ArrayList<>();
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "Employee_On_Project",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private List<Project> projects = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "username")
    private User user;

    /**
     * Default no arg constructor
     */
    public Employees() {
    }

    /**
     * Constructor for Employees entity
     *
     * @param empFirstName
     * @param empLastName
     * @param empPhone
     * @param empSSNumber
     * @param empSalary
     * @param empBirthDate
     * @param employementStartDate
     * @param employmentEndDate
     */
    public Employees(String empFirstName, String empLastName, String empPhone, String empSSNumber, double empSalary, Date empBirthDate, Date employementStartDate, Date employmentEndDate) {
        this.empFirstName = empFirstName;
        this.empLastName = empLastName;
        this.empPhone = empPhone;
        this.empSSNumber = empSSNumber;
        this.empSalary = empSalary;
        this.empBirthDate = empBirthDate;
        this.employementStartDate = employementStartDate;
        this.employmentEndDate = employmentEndDate;
    }

    /**
     * Constructor for Employees entity with full parameters
     *
     * @param empFirstName
     * @param empLastName
     * @param empPhone
     * @param empSSNumber
     * @param empSalary
     * @param empBirthDate
     * @param employementStartDate
     * @param employmentEndDate
     * @param jobType
     */
    public Employees(String empFirstName, String empLastName, String empPhone, String empSSNumber, double empSalary, Date empBirthDate, Date employementStartDate, Date employmentEndDate, JobType jobType) {
        this.empFirstName = empFirstName;
        this.empLastName = empLastName;
        this.empPhone = empPhone;
        this.empSSNumber = empSSNumber;
        this.empSalary = empSalary;
        this.empBirthDate = empBirthDate;
        this.employementStartDate = employementStartDate;
        this.employmentEndDate = employmentEndDate;
        this.jobType = jobType;
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
     * Set the value of projects
     *
     * @param projects new value of projects
     */
    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    /**
     * Get the value of skills
     *
     * @return the value of skills
     */
    public List<Skill> getSkills() {
        return skills;
    }

    /**
     * Set the value of skills
     *
     * @param skills new value of skills
     */
    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    /**
     * Get the value of jobType
     *
     * @return the value of jobType
     */
    public JobType getJobType() {
        return jobType;
    }

    /**
     * Set the value of jobType
     *
     * @param jobType new value of jobType
     */
    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    /**
     * Get the value of empSSNumber
     *
     * @return the value of empSSNumber
     */
    public String getEmpSSNumber() {
        return empSSNumber;
    }

    /**
     * Set the value of empSSNumber
     *
     * @param empSSNumber new value of empSSNumber
     */
    public void setEmpSSNumber(String empSSNumber) {
        this.empSSNumber = empSSNumber;
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
     * Get the value of employmentEndDate
     *
     * @return the value of employmentEndDate
     */
    public Date getEmploymentEndDate() {
        return employmentEndDate;
    }

    /**
     * Set the value of employmentEndDate
     *
     * @param employmentEndDate new value of employmentEndDate
     */
    public void setEmploymentEndDate(Date employmentEndDate) {
        this.employmentEndDate = employmentEndDate;
    }

    /**
     * Get the value of employementStartDate
     *
     * @return the value of employementStartDate
     */
    public Date getEmployementStartDate() {
        return employementStartDate;
    }

    /**
     * Set the value of employementStartDate
     *
     * @param employementStartDate new value of employementStartDate
     */
    public void setEmployementStartDate(Date employementStartDate) {
        this.employementStartDate = employementStartDate;
    }

    /**
     * Get the value of empBirthDate
     *
     * @return the value of empBirthDate
     */
    public Date getEmpBirthDate() {
        return empBirthDate;
    }

    /**
     * Set the value of empBirthDate
     *
     * @param empBirthDate new value of empBirthDate
     */
    public void setEmpBirthDate(Date empBirthDate) {
        this.empBirthDate = empBirthDate;
    }

    /**
     * Get the value of empSalary
     *
     * @return the value of empSalary
     */
    public double getEmpSalary() {
        return empSalary;
    }

    /**
     * Set the value of empSalary
     *
     * @param empSalary new value of empSalary
     */
    public void setEmpSalary(double empSalary) {
        this.empSalary = empSalary;
    }

    /**
     * Get the value of empPhone
     *
     * @return the value of empPhone
     */
    public String getEmpPhone() {
        return empPhone;
    }

    /**
     * Set the value of empPhone
     *
     * @param empPhone new value of empPhone
     */
    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }

    /**
     * Get the value of empLastName
     *
     * @return the value of empLastName
     */
    public String getEmpLastName() {
        return empLastName;
    }

    /**
     * Set the value of empLastName
     *
     * @param empLastName new value of empLastName
     */
    public void setEmpLastName(String empLastName) {
        this.empLastName = empLastName;
    }

    /**
     * Get the value of empFirstName
     *
     * @return the value of empFirstName
     */
    public String getEmpFirstName() {
        return empFirstName;
    }

    /**
     * Set the value of empFirstName
     *
     * @param empFirstName new value of empFirstName
     */
    public void setEmpFirstName(String empFirstName) {
        this.empFirstName = empFirstName;
    }

    /**
     * Get the value of employeeId
     *
     * @return the value of employeeId
     */
    public Long getEmployeeId() {
        return employeeId;
    }

    /**
     * Set the value of employeeId
     *
     * @param employeeId new value of employeeId
     */
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
    
    

    /**
     * Helper method to add address to list of addresses
     *
     * @param address
     */
    /*public void addToAddressesList(Address address) {
        if (!addresses.contains(address)) {
            addresses.add(address);
        }
    }*/

    /**
     * Method that adds a skill to the employee
     *
     * @param skill
     */
    public void addSkill(Skill skill) {
        if (!skills.contains(skill)) {
            skills.add(skill);
        }
    }

    /**
     * Method that adds a project to the employee projects list
     *
     * @param project
     */
    public void addProject(Project project) {
        if (!projects.contains(project)) {
            projects.add(project);
        }
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
     * toString() method with most variables
     *
     * @return
     */
    @Override
    public String toString() {
        return "Employees{" + "employeeId=" + employeeId + ", empFirstName=" + empFirstName + ", empLastName=" + empLastName + ", empPhone=" + empPhone + ", empSSNumber=" + empSSNumber + ", empSalary=" + empSalary + ", jobType=" + jobType + ", empBirthDate=" + empBirthDate + ", employementStartDate=" + employementStartDate + ", employmentEndDate=" + employmentEndDate + ", address=" + address + ", skills=" + skills + '}';
    }

}

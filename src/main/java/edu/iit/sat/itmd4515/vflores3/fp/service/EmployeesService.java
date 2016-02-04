/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.vflores3.fp.service;

import edu.iit.sat.itmd4515.vflores3.fp.domain.Address;
import edu.iit.sat.itmd4515.vflores3.fp.domain.Employees;
import edu.iit.sat.itmd4515.vflores3.fp.domain.JobType;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Vanessa
 */
@Stateless
public class EmployeesService extends AbstractService<Employees> {

    /**
     * Constructor
     */
    public EmployeesService() {
        super(Employees.class);
    }

    /**
     * Method to find all employees
     *
     * @return
     */
    @Override
    public List<Employees> findAll() {
        return em.createNamedQuery("Employees.findAllEmployees").getResultList();
    }

    /**
     * Method to find an employee by their social security number
     *
     * @param ssNumber
     * @return
     */
    public Employees findBySSNumber(String ssNumber) {
        TypedQuery<Employees> query = em.createNamedQuery("Employees.findExactEmployee", Employees.class);
        query.setParameter("eEmpSSNumber", ssNumber);
        return query.getSingleResult();
    }

    /**
     * Method to find an employee by first and last name
     *
     * @param firstName
     * @param lastName
     * @return
     */
    public List<Employees> findByName(String firstName, String lastName) {
        TypedQuery<Employees> query = em.createNamedQuery("Employees.findByEmpName", Employees.class);
        query.setParameter("eEmpFirstName", firstName);
        query.setParameter("eEmpLastName", lastName);
        return query.getResultList();
    }
    
    /**
     * Method to find an exact user by their username
     *
     * @param username
     * @return
     */
    public Employees findByUsername(String username) {
        TypedQuery<Employees> query = em.createNamedQuery("Employees.findByUsername", Employees.class);
        query.setParameter("eUserName", username);
        return query.getSingleResult();
    }
    
    /**
     * Method to find an employee by their id
     * @param employeeId
     * @return
     */
    public Employees findByEmployeeId(Long employeeId) {
        TypedQuery<Employees> query = em.createNamedQuery("Employees.findByEmployeeId", Employees.class);
        query.setParameter("eEmployeeId", employeeId);
        return query.getSingleResult();
    }
    
    /**
     * Create a new employee and associate with an address
     * 
     * @param employee
     */
    public void create(Employees employee) {
        
        // set both sides of the relationship
        //employee.setAddress(address);
        
        // persist the new address
        //em.persist(address);
        // persist the new employee
        em.persist(employee);
    }
    
    public void update(Employees newEmployee){
        // first, get a reference to the old employee 
        Employees currentEmployee = em.getReference(Employees.class, newEmployee.getEmployeeId());
        
        // Make the updates from the user
        currentEmployee.setEmpBirthDate(newEmployee.getEmpBirthDate());
        currentEmployee.setEmpFirstName(newEmployee.getEmpFirstName());
        currentEmployee.setEmpLastName(newEmployee.getEmpLastName());
        currentEmployee.setEmpPhone(newEmployee.getEmpPhone());
        currentEmployee.setEmpSSNumber(newEmployee.getEmpSSNumber());
        currentEmployee.setEmployementStartDate(newEmployee.getEmployementStartDate());
        currentEmployee.setEmploymentEndDate(newEmployee.getEmploymentEndDate());
    }
    
    /**
     * Delete an existing employee and remove from a employee
     * 
     * @param employee 
     */
    public void delete(Employees employee){
        employee = em.getReference(Employees.class, employee.getEmployeeId());
        
        em.remove(employee);
    }

}

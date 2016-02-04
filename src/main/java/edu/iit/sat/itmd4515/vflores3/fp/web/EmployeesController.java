/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.vflores3.fp.web;

import edu.iit.sat.itmd4515.vflores3.fp.domain.Address;
import edu.iit.sat.itmd4515.vflores3.fp.domain.Employees;
import edu.iit.sat.itmd4515.vflores3.fp.domain.JobType;
import edu.iit.sat.itmd4515.vflores3.fp.security.User;
import edu.iit.sat.itmd4515.vflores3.fp.service.AddressService;
import edu.iit.sat.itmd4515.vflores3.fp.service.EmployeesService;
import edu.iit.sat.itmd4515.vflores3.fp.service.UserService;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Vanessa
 */
@Named
@RequestScoped
public class EmployeesController extends AbstractJSFController {

    private static final Logger LOG = Logger.getLogger(EmployeesController.class.getName());

    @EJB
    EmployeesService employeesService;
    @EJB
    AddressService addressService;
    @EJB
    UserService userService;
    @Inject
    LoginController loginController;
    @Inject
    AddressController addressController;
    @Inject
    UserController userController;

    private Employees employee;
    private List<Employees> employees;
    private Address address;
    private User user;

    /**
     * Default constructor for EmployeesController
     */
    public EmployeesController() {
    }

    @Override
    @PostConstruct
    protected void postConstruct() {
        employee = new Employees();
        address = new Address();
        if (loginController.isAdmin() || loginController.isManager()) {
            employees = employeesService.findAll();
        }
        super.postConstruct();
    }

    /**
     * Prepare to view all employees
     *
     *
     * @return the page on which all employees will be displayed to the user
     */
    public String doViewAllEmployees() {
        LOG.info("Preparing to display all employees");
        return "viewEmployees";
    }

    /**
     * Prepare a new employee to be created by the user
     *
     * @return the page on which the new employee will be created
     */
    public String doCreateEmployee() {
        LOG.info("Preparing to create a new employee");
        employee = new Employees();
        address = new Address();
        return "newEmployee";
    }

    /**
     * Refresh the collection of employees
     */
    public void refreshEmployees() {
        employees = employeesService.findAll();
    }

    /**
     * Handle the action from newEmployee view
     *
     * @return
     */
    public String executeCreateEmployee() {
        LOG.info("Preparing to create " + employee.toString());
        employeesService.create(employee);
        facesContext.addMessage(null, new FacesMessage("Employee " + employee.getEmpFirstName() + " " + employee.getEmpLastName() + " has been created."));
        // refresh the collection
        refreshEmployees();

        return "viewEmployees";
    }

    /**
     * Prepare the selected employee to be displayed in more detail
     *
     * @param employee the employee to display
     * @return the page on which the employee will be displayed to the user with
     * more detail
     */
    public String doViewEmployee(Employees employee) {
        LOG.info("Preparing to display " + employee.toString());
        this.employee = employee;
        return "viewEmployee";
    }

    /**
     * Prepare the selected employee to be updated
     *
     * @param employee the employee to be updated
     * @return the page on which the employee will be displayed for update
     */
    public String doUpdateEmployee(Employees employee) {
        LOG.info("Preparing to update " + employee.toString());
        this.employee = employee;
        return "editEmployee";
    }

    /**
     * Handle the action from editEmployee view
     *
     * @return
     */
    public String executeUpdateEmployee() {
        LOG.info("Preparing to update " + employee.toString());
        // Update the project
        employeesService.update(employee);
        facesContext.addMessage(null, new FacesMessage("Employee " + employee.getEmpFirstName() + " " + employee.getEmpLastName() + " has been updated."));
        // refresh the collection
        refreshEmployees();
        return "viewEmployees";
    }

    /**
     * Prepare the selected employee for deletion, and call the delete
     *
     * @param employee the employee to be deleted
     * @return sends the user back to the welcome page with a message
     */
    public String doDeleteEmployee(Employees employee) {
        LOG.info("Preparing to delete " + employee.toString());
        // delete the project
        employeesService.delete(employee);
        facesContext.addMessage(null, new FacesMessage("Employee " + employee.getEmpFirstName() + " has been deleted."));
        refreshEmployees();
        return "viewEmployees";
    }

    /**
     * Get the value of employee
     *
     * @return the value of employee
     */
    public Employees getEmployee() {
        return employee;
    }

    /**
     * Set the value of employee
     *
     * @param employee new value of employee
     */
    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    /**
     * Get the value of employees
     *
     * @return the value of employees
     */
    public List<Employees> getEmployees() {
        return employees;
    }

    /**
     * Set the value of employees
     *
     * @param employees new value of employees
     */
    public void setEmployees(List<Employees> employees) {
        this.employees = employees;
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

}

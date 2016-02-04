/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.vflores3.fp.web;

import edu.iit.sat.itmd4515.vflores3.fp.domain.Customer;
import edu.iit.sat.itmd4515.vflores3.fp.domain.Employees;
import edu.iit.sat.itmd4515.vflores3.fp.security.Group;
import edu.iit.sat.itmd4515.vflores3.fp.security.User;
import edu.iit.sat.itmd4515.vflores3.fp.service.CustomerService;
import edu.iit.sat.itmd4515.vflores3.fp.service.EmployeesService;
import edu.iit.sat.itmd4515.vflores3.fp.service.GroupService;
import edu.iit.sat.itmd4515.vflores3.fp.service.UserService;
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
public class UserController extends AbstractJSFController {

    private static final Logger LOG = Logger.getLogger(UserController.class.getName());

    @EJB
    UserService userService;
    @EJB
    GroupService groupService;
    @EJB
    EmployeesService employeeService;
    @EJB
    CustomerService customerService;
    @Inject
    LoginController loginController;
    @Inject
    EmployeesController employeesController;
    @Inject
    CustomerController customerController;

    private User user;
    private List<User> users;
    private Employees employee;
    private Customer customer;
    private Long empId;
    private Long custId;

    /**
     * Default constructor for UserController
     */
    public UserController() {
    }

    @Override
    @PostConstruct
    protected void postConstruct() {
        user = new User();
        employee = new Employees();
        if (loginController.isAdmin()) {
            users = userService.findAll();
        } else {
            facesContext.addMessage(null, new FacesMessage("Error: ", "You are not authorized to view user accounts."));
        }
        super.postConstruct();
    }

    /**
     * Go to view all users
     *
     * @return the page on which the users will be viewed
     */
    public String doViewUsers() {
        LOG.info("Preparing to view all users");
        user = new User();
        return "viewUsers";
    }

    /**
     * Refresh the collection of users
     */
    public void refreshUsers() {
        users = userService.findAll();
    }

    /**
     * Prepare a new user to be created by the user
     *
     * @return the page on which the new user will be created
     */
    public String doCreateUser() {
        LOG.info("Preparing to create a new user");
        user = new User();
        getEmpIdParameter();

        return "newUser";
    }

    /**
     * Method to get the empId as a parameter
     *
     * @return
     */
    public Long getEmpIdParameter() {
        FacesContext fc = FacesContext.getCurrentInstance();
        empId = Long.parseLong(getEmpId(fc));
        return empId;
    }

    /**
     * Method to get value from parameter
     *
     * @param fc
     * @return
     */
    public String getEmpId(FacesContext fc) {

        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("empId");

    }
    
    /**
     * Method to get the custId as a parameter
     *
     * @return
     */
    public Long getCustIdParameter() {
        FacesContext fc = FacesContext.getCurrentInstance();
        custId = Long.parseLong(getCustId(fc));
        return custId;
    }

    /**
     * Method to get value from parameter
     *
     * @param fc
     * @return
     */
    public String getCustId(FacesContext fc) {

        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("custId");

    }

    /**
     * Confirm the password the user would like to use
     *
     * @param p1
     * @param p2
     * @return
     */
    public Boolean doValidatePassword(String p1, String p2) {
        LOG.info("Validating password " + p1 + " vs " + p2);
        // Validate password
        if (!p1.equals(p2)) {
            facesContext.addMessage(null, new FacesMessage("Invalid Password", "Passwords do not match."));
            return false;
        }
        return true;
    }

    /**
     * Handle the action from newUser view
     *
     * @return
     */
    public String executeCreateUser() {
        //employee = employeeService.findByEmployeeId();
        LOG.info("Preparing to create " + user.getUserName());
        if (!doValidatePassword(user.getPassword(), user.getPassword2())) {
            return "newUser";
        } else {
            // Create the user
            userService.create(user, getEmpIdParameter());
            facesContext.addMessage(null, new FacesMessage("User " + user.getUserName() + " has been created."));
            return "viewUsers";
        }
    }

    /**
     * Prepare the selected user to be displayed in more detail
     *
     * @param user the user to display
     * @return the page on which the user will be displayed to the user with
     * more detail
     */
    public String doViewUser(User user) {
        LOG.info("Preparing to display " + user.toString());
        this.user = user;
        return "viewUser";
    }

    /**
     * Prepare the selected user to be updated
     *
     * @param user the user to be updated
     * @return the page on which the user will be displayed for update
     */
    public String doUpdateUser(User user) {
        LOG.info("Preparing to update " + user.getUserName());
        this.user = user;
        return "editUser";
    }

    /**
     * Handle the action from editUser view
     *
     * @return
     */
    public String executeUpdateUser() {
        LOG.info("Preparing to update " + user.getUserName() + user.getPassword());
        if (!doValidatePassword(user.getPassword(), user.getPassword2())) {
            return "editUser";
        } else {
            // Update the user
            userService.update(user);
            facesContext.addMessage(null, new FacesMessage("User " + user.getUserName() + " has been updated."));
            return "viewUsers";
        }
    }

    /**
     * Prepare the selected user for deletion, and call the delete
     *
     * @param user the user to be deleted
     * @return sends the user back to the welcome page with a message
     */
    public String doDeleteUser(User user) {
        LOG.info("Preparing to delete " + user.toString());
        // delete the user
        userService.delete(user);
        facesContext.addMessage(null, new FacesMessage("Username " + user.getUserName() + " has been deleted."));
        refreshUsers();
        return "viewUsers";
    }

    /**
     * Get the value of users
     *
     * @return the value of users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Set the value of users
     *
     * @param users new value of users
     */
    public void setUsers(List<User> users) {
        this.users = users;
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
     * Get the value of empId
     *
     * @return the value of empId
     */
    public Long getEmpId() {
        return empId;
    }

    /**
     * Set the value of empId
     *
     * @param empId new value of empId
     */
    public void setEmpId(Long empId) {
        this.empId = empId;
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
     * Get the value of custId
     *
     * @return the value of custId
     */
    public Long getCustId() {
        return custId;
    }

    /**
     * Set the value of custId
     *
     * @param custId new value of custId
     */
    public void setCustId(Long custId) {
        this.custId = custId;
    }

}

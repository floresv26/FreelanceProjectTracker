/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.vflores3.fp.web;

import edu.iit.sat.itmd4515.vflores3.fp.domain.Customer;
import edu.iit.sat.itmd4515.vflores3.fp.domain.Project;
import edu.iit.sat.itmd4515.vflores3.fp.service.CustomerService;
import edu.iit.sat.itmd4515.vflores3.fp.service.EmployeesService;
import edu.iit.sat.itmd4515.vflores3.fp.service.ProjectService;
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
public class ProjectController extends AbstractJSFController {

    private static final Logger LOG = Logger.getLogger(ProjectController.class.getName());

    @EJB
    ProjectService projectService;
    @EJB
    EmployeesService employeesService;
    @EJB
    CustomerService customerService;
    @Inject
    LoginController loginController;
    @Inject
    CustomerController customerController;

    private List<Project> projects;
    private Project project;

    private Customer customer;

    //private Employees employee;
    /**
     * Default constructor for ProjectController
     */
    public ProjectController() {
    }

    @Override
    @PostConstruct
    protected void postConstruct() {
        project = new Project();
        if (loginController.isClient()) {
            projects = customerService.findByUsername(loginController.getRemoteUser()).getProjects();
        } else if (loginController.isAdmin() || loginController.isManager()) {
            projects = projectService.findAll();
        } else if (loginController.isTeamLeader() || loginController.isTeamMember()) {
            projects = employeesService.findByUsername(loginController.getRemoteUser()).getProjects();
        }
        //projects = employee.getProjects();
        super.postConstruct();
    }

    /*
     private void refreshProjects() {
     projects = projectService.findByUsername(loginController.getRemoteUser()).getFarms();
     }*/
    /**
     * Prepare a new project to be created by the user
     *
     * @return the page on which the new project will be created
     */
    public String doCreateProject() {
        LOG.info("Preparing to create a new project");
        project = new Project();
        return "newProject";
    }

    /**
     * Handle the action from newProject view
     *
     * @return
     */
    public String executeCreateProject() {
        LOG.info("Preparing to create " + project.toString());
        // create the project
        projectService.create(project);
        facesContext.addMessage(null, new FacesMessage("Project " + project.getProjectName() + " has been created."));
        // refresh the collection
        refreshProjects();
        return "welcome";
    }

    /**
     * Prepare the selected project to be displayed in more detail
     *
     * @param project the project to display
     * @return the page on which the project will be displayed to the user with
     * more detail
     */
    public String doViewProject(Project project) {
        LOG.info("Preparing to display " + project.toString());
        this.project = project;
        return "viewProject";
    }

    /**
     * Prepare the selected project to be updated
     *
     * @param project the project to be updated
     * @return the page on which the project will be displayed for update
     */
    public String doUpdateProject(Project project) {
        LOG.info("Preparing to update " + project.toString());
        this.project = project;
        return "editProject";
    }

    /**
     * Method to refresh the list of projects after any changes
     */
    public void refreshProjects() {
        projects = projectService.findAll();
    }

    /**
     * Handle the action from newEmployee view
     *
     * @return
     */
    public String executeUpdateProject() {
        LOG.info("Preparing to create " + project.toString());
        facesContext.addMessage(null, new FacesMessage("Employee " + project.getProjectNumber() + " has been created."));
        projectService.create(project);

        // refresh the collection
        refreshProjects();

        return "viewEmployees";
    }

    /**
     * Prepare the selected project for deletion, and call the delete
     *
     * @param project the project to be deleted
     * @return sends the user back to the welcome page with a message
     */
    public String doDeleteProject(Project project) {
        LOG.info("Preparing to delete " + project.toString());
        facesContext.addMessage(null, new FacesMessage("Project Number " + project.getProjectNumber() + " has been deleted."));
        // delete the project
        projectService.delete(project);
        refreshProjects();
        return "welcome";
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
     * Get the value of project
     *
     * @return the value of project
     */
    public Project getProject() {
        return project;
    }

    /**
     * Set the value of project
     *
     * @param project new value of project
     */
    public void setProject(Project project) {
        this.project = project;
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

}

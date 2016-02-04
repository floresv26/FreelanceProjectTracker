/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.vflores3.fp.web;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Vanessa
 */
@Named
@RequestScoped
public class LoginController extends AbstractJSFController {

    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());

    @NotNull(message = "A username is required.")
    private String username;
    @NotNull(message = "A password is required.")
    private String password;

    /**
     * Default constructor for LoginController
     */
    public LoginController() {
    }

    @Override
    @PostConstruct
    protected void postConstruct() {
        super.postConstruct();
    }

    /**
     *
     * @return
     */
    public String getRemoteUser() {
        return facesContext.getExternalContext().getRemoteUser();
    }

    /**
     * Method that verifies if the user has Admin privileges
     *
     * @return
     */
    public boolean isAdmin() {
        return facesContext.getExternalContext().isUserInRole("ADMIN");
    }

    /**
     * Method that verifies if the user has Manager privileges
     *
     * @return
     */
    public boolean isManager() {
        return facesContext.getExternalContext().isUserInRole("MANAGER");
    }

    /**
     * Method that verifies if the user has TeamLeader privileges
     *
     * @return
     */
    public boolean isTeamLeader() {
        return facesContext.getExternalContext().isUserInRole("TEAMLEADER");
    }

    /**
     * Method that verifies if the user has TeamMember privileges
     *
     * @return
     */
    public boolean isTeamMember() {
        return facesContext.getExternalContext().isUserInRole("TEAMMEMBER");
    }

    /**
     * Method that verifies if the user has Client privileges
     *
     * @return
     */
    public boolean isClient() {
        return facesContext.getExternalContext().isUserInRole("CLIENT");
    }

    /**
     * Method to get the context path for the users role
     * @param path
     * @return
     */
    public String getRoleContextPath(String path) {
        // check user roles
        if (isAdmin()) {
            return "/admin/" + path + FACES_REDIRECT;
        } else if (isManager()) {
            return "/managerPortal/" + path + FACES_REDIRECT;
        } else if (isTeamLeader()) {
            return "/teamLeaderPortal/" + path + FACES_REDIRECT;
        } else if (isTeamMember()) {
            return "/teamMemberPortal/" + path + FACES_REDIRECT;
        } else if (isClient()) {
            return "/clientPortal/" + path + FACES_REDIRECT;
        }

        // return whatever was passed - user isn't in any role
        return path;
    }

    /**
     *
     * @return
     */
    public String doLogin() {
        HttpServletRequest req = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        try {
            req.login(username, password);
        } catch (ServletException ex) {
            LOG.log(Level.SEVERE, "Failed login from " + username, ex);
            facesContext.addMessage(null, new FacesMessage("Bad Login: ", "You have entered a bad username or password."));
            return "login.xhtml";
        }

        return getRoleContextPath("welcome.xhtml");
    }

    /**
     *
     * @return
     */
    public String doLogout() {
        HttpServletRequest req = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        try {
            req.logout();
        } catch (ServletException ex) {
            LOG.log(Level.SEVERE, "Failed login from " + username, ex);
            facesContext.addMessage(null, new FacesMessage("Bad Logout"));
            return "login.xhtml";
        }

        return "/login.xhtml";
    }

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the value of username
     *
     * @return the value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the value of username
     *
     * @param username new value of username
     */
    public void setUsername(String username) {
        this.username = username;
    }
}

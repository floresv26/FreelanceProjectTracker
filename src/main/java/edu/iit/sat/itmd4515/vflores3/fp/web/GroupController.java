/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.vflores3.fp.web;

import edu.iit.sat.itmd4515.vflores3.fp.security.Group;
import edu.iit.sat.itmd4515.vflores3.fp.service.GroupService;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
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
public class GroupController extends AbstractJSFController {

    private static final Logger LOG = Logger.getLogger(GroupController.class.getName());

    @EJB
    GroupService groupService;
    @Inject
    LoginController loginController;

    private Group group;
    private List<Group> groups;

    /**
     * Default constructor for GroupController
     */
    public GroupController() {
    }

    @Override
    @PostConstruct
    protected void postConstruct() {
        group = new Group();
        if (loginController.isAdmin()) {
            groups = groupService.findAll();
        }
        super.postConstruct();
    }

    /**
     * Get the value of groups
     *
     * @return the value of groups
     */
    public List<Group> getGroups() {
        return groups;
    }

    /**
     * Set the value of groups
     *
     * @param groups new value of groups
     */
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    /**
     * Get the value of group
     *
     * @return the value of group
     */
    public Group getGroup() {
        return group;
    }

    /**
     * Set the value of group
     *
     * @param group new value of group
     */
    public void setGroup(Group group) {
        this.group = group;
    }

}

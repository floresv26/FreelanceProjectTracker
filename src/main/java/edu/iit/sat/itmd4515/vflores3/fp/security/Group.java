/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.vflores3.fp.security;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Vanessa
 */
@Entity
@Table(name = "group_table")
@NamedQueries({
    @NamedQuery(name = "Group.findAllGroups", query = "select g from Group g"),
    @NamedQuery(name = "Group.findByGroupName", query = "select g from Group g where g.groupName = :gGroupName")
})
/**
 * Group entity
 */
public class Group {

    @Id
    private String groupName;
    private String groupDescription;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "group")
    private List<User> usersInGroup = new ArrayList<>();

    /**
     * Get the value of usersInGroup
     *
     * @return the value of usersInGroup
     */
    public List<User> getUsersInGroup() {
        return usersInGroup;
    }

    /**
     * Set the value of usersInGroup
     *
     * @param usersInGroup new value of usersInGroup
     */
    public void setUsersInGroup(List<User> usersInGroup) {
        this.usersInGroup = usersInGroup;
    }

    /**
     * Default no arg constructor
     */
    public Group() {
    }

    /**
     * Constructor for Group entity with full parameters
     *
     * @param groupName
     * @param groupDescription
     */
    public Group(String groupName, String groupDescription) {
        this.groupName = groupName;
        this.groupDescription = groupDescription;
    }

    /**
     * Get the value of groupDescription
     *
     * @return the value of groupDescription
     */
    public String getGroupDescription() {
        return groupDescription;
    }

    /**
     * Set the value of groupDescription
     *
     * @param groupDescription new value of groupDescription
     */
    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    /**
     * Get the value of groupName
     *
     * @return the value of groupName
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Set the value of groupName
     *
     * @param groupName new value of groupName
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * Method to add the user into this group
     * @param user
     */
    public void putUserInGroup(User user) {
        if(!usersInGroup.contains(user)) {
            usersInGroup.add(user);
        }

        user.setGroup(this);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.vflores3.fp.security;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Vanessa
 */
@Entity
@Table(name = "user_table")
@NamedQueries({
    @NamedQuery(name = "User.findAllUsers", query = "select u from User u"),
    @NamedQuery(name = "User.findByUsername", query = "select u from User u where u.userName = :uUsername")
})
/**
 * User entity
 */
public class User {

    @Id
    @NotNull
    private String userName;
    @NotNull
    private String password;
    private String password2;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name = "user_group_table",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "groupname"))
    private Group group;

    /**
     * Default no arg constructor
     */
    public User() {
    }

    /**
     * Constructor for User entity with two parameters
     *
     * @param userName
     * @param password
     */
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * Constructor for User entity with full parameters
     * @param userName
     * @param password
     * @param password2 
     */
    public User(String userName, String password, String password2) {
        this.userName = userName;
        this.password = password;
        this.password2 = password2;
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
     * Get the value of userName
     *
     * @return the value of userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set the value of userName
     *
     * @param userName new value of userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
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

    /**
     * Method to add this user to a group and to set a group to this user
     * @param group
     */
    public void addUserToGroup(Group group) {
        setGroup(group);

        if (!group.getUsersInGroup().contains(this)) {
            group.getUsersInGroup().add(this);
        }
    }

    /**
     * Provide a level of security for password Instructor example
     */
    @PrePersist
    @PreUpdate
    private void hashPassword() {
        String digestPassword = DigestUtils.sha256Hex(this.password);
        this.password = digestPassword;
    }

    /**
     * Get the value of password2
     *
     * @return the value of password2
     */
    public String getPassword2() {
        return password2;
    }

    /**
     * Set the value of password2
     *
     * @param password2 new value of password2
     */
    public void setPassword2(String password2) {
        this.password2 = password2;
    }

}

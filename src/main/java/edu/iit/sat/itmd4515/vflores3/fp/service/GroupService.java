/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.vflores3.fp.service;

import edu.iit.sat.itmd4515.vflores3.fp.security.Group;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Vanessa
 */
@Stateless
public class GroupService extends AbstractService<Group> {

    /**
     * Constructor
     */
    public GroupService() {
        super(Group.class);
    }

    /**
     * Method to find all groups
     *
     * @return
     */
    @Override
    public List<Group> findAll() {
        return em.createNamedQuery("Group.findAllGroups").getResultList();
    }

    /**
     * Method to find a specific group by groupName
     *
     * @param groupName
     * @return
     */
    public Group findByGroupName(String groupName) {
        TypedQuery<Group> query = em.createNamedQuery("Group.findByGroupName", Group.class);
        query.setParameter("gGroupName", groupName);
        return query.getSingleResult();
    }

}

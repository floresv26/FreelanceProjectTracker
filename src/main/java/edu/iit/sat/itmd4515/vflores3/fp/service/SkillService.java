/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.vflores3.fp.service;

import edu.iit.sat.itmd4515.vflores3.fp.domain.Skill;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Vanessa
 */
@Stateless
public class SkillService extends AbstractService<Skill> {

    /**
     * Constructor
     */
    public SkillService() {
        super(Skill.class);
    }

    /**
     * Method to find all skills
     *
     * @return
     */
    @Override
    public List<Skill> findAll() {
        return em.createNamedQuery("Skill.findAllSkills").getResultList();
    }

    /**
     * Method to find a specific skill
     *
     * @param skill
     * @return
     */
    public Skill findSkill(String skill) {
        TypedQuery<Skill> query = em.createNamedQuery("Skill.findSkill", Skill.class);
        query.setParameter("sSkill", skill);
        return query.getSingleResult();
    }

}

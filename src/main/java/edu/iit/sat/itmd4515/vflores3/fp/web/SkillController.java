/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.vflores3.fp.web;

import edu.iit.sat.itmd4515.vflores3.fp.domain.Skill;
import edu.iit.sat.itmd4515.vflores3.fp.service.SkillService;
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
public class SkillController extends AbstractJSFController {

    private static final Logger LOG = Logger.getLogger(SkillController.class.getName());

    @EJB
    SkillService skillService;
    @Inject
    LoginController loginController;

    private Skill skill;
    private List<Skill> skills;

    /**
     * Default constructor for SkillController
     */
    public SkillController() {
    }

    @Override
    @PostConstruct
    protected void postConstruct() {
        skill = new Skill();
        skills = skillService.findAll();
        super.postConstruct();
    }

    /**
     * Get the value of skills
     *
     * @return the value of skills
     */
    public List<Skill> getSkills() {
        return skills;
    }

    /**
     * Set the value of skills
     *
     * @param skills new value of skills
     */
    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    /**
     * Get the value of skill
     *
     * @return the value of skill
     */
    public Skill getSkill() {
        return skill;
    }

    /**
     * Set the value of skill
     *
     * @param skill new value of skill
     */
    public void setSkill(Skill skill) {
        this.skill = skill;
    }

}

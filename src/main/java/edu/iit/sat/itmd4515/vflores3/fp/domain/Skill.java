/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.vflores3.fp.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Vanessa
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Skill.findAllSkills", query = "select s from Skill s"),
    @NamedQuery(name = "Skill.findSkill", query = "select s from Skill s where s.skill = :sSkill")
})
/**
 * Skill entity
 */
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "skill")
    private Long skillId;
    private String skill;
    @ManyToMany(mappedBy = "skills", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Employees> employees = new ArrayList<>();

    /**
     * Default no arg constructor
     */
    public Skill() {
    }

    /**
     * Constructor for Skill entity with full parameters
     *
     * @param skill
     */
    public Skill(String skill) {
        this.skill = skill;
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
     * Get the value of skill
     *
     * @return the value of skill
     */
    public String getSkill() {
        return skill;
    }

    /**
     * Set the value of skill
     *
     * @param skill new value of skill
     */
    public void setSkill(String skill) {
        this.skill = skill;
    }

    /**
     * Get the value of skillId
     *
     * @return the value of skillId
     */
    public Long getSkillId() {
        return skillId;
    }

    /**
     * Set the value of skillId
     *
     * @param skillId new value of skillId
     */
    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }
    
    /**
     * Helper method to add employee to list
     * @param e 
     */
    public void addEmployee(Employees e) {
        if (!employees.contains(e)) {
            employees.add(e);
        }
    }

    /**
     * toString() method
     *
     * @return
     */
    @Override
    public String toString() {
        return "Skill{" + "skillId=" + skillId + ", skill=" + skill + ", employees=" + employees + '}';
    }

}

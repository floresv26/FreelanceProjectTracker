/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.vflores3.fp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Vanessa
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "JobType.findAllJobTypes", query = "select j from JobType j"),
    @NamedQuery(name = "JobType.findJobType", query = "select j from JobType j where j.jobType = :jJobType")
})
/**
 * JobType entity
 */
public class JobType {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "jobType")
    private Long jobTypeId;
    private String jobType;

    /**
     * Default no arg constructor
     */
    public JobType() {
    }

    /**
     * Constructor for JobType entity with full parameters
     *
     * @param jobType
     */
    public JobType(String jobType) {
        this.jobType = jobType;
    }

    /**
     * Get the value of jobType
     *
     * @return the value of jobType
     */
    public String getJobType() {
        return jobType;
    }

    /**
     * Set the value of jobType
     *
     * @param jobType new value of jobType
     */
    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    /**
     * Get the value of jobTypeId
     *
     * @return the value of jobTypeId
     */
    public Long getJobTypeId() {
        return jobTypeId;
    }

    /**
     * Set the value of jobTypeId
     *
     * @param jobTypeId new value of jobTypeId
     */
    public void setJobTypeId(Long jobTypeId) {
        this.jobTypeId = jobTypeId;
    }

    /**
     * toString method
     *
     * @return
     */
    @Override
    public String toString() {
        return "JobType{" + "jobTypeId=" + jobTypeId + ", jobType=" + jobType + '}';
    }

}

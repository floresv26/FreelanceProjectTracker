/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.vflores3.fp.service;

import edu.iit.sat.itmd4515.vflores3.fp.domain.JobType;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Vanessa
 */
@Stateless
public class JobTypeService extends AbstractService<JobType> {

    /**
     * Constructor
     */
    public JobTypeService() {
        super(JobType.class);
    }

    /**
     * Method to find all job types
     *
     * @return
     */
    @Override
    public List<JobType> findAll() {
        return em.createNamedQuery("JobType.findAllJobTypes").getResultList();
    }
    
    /**
     * Method to find exact job type by jobType
     * @param jobType
     * @return 
     */
    public JobType findJobType(String jobType){
        TypedQuery<JobType> query = em.createNamedQuery("JobType.findJobType", JobType.class);
        query.setParameter("jJobType", jobType);
        return query.getSingleResult();
    }

}

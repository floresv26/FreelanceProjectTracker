/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.vflores3.fp.web;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

/**
 *
 * @author Vanessa
 */
public abstract class AbstractJSFController {

    protected FacesContext facesContext;
    protected static final String FACES_REDIRECT = "?faces-redirect=true";

    /**
     * Method that does something after a construct
     */
    @PostConstruct
    protected void postConstruct() {
        facesContext = FacesContext.getCurrentInstance();
    }
}

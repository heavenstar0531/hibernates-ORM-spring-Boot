/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.lionsoft.javaee.jpa.services;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import ru.lionsoft.javaee.jpa.entities.Company;
import ru.lionsoft.javaee.jpa.facades.CompanyFacade;

/**
 * Класс RESTfull Web Services для работы с сущностью Company
 *
 * @author Igor Morenko <morenko at lionsoft.ru>
 */

@Stateless
@Path("company")
public class CompanyService {

    @EJB
    CompanyFacade companyFacade;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CompanyService
     */
    public CompanyService() {
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Company> findAll() {
        return companyFacade.findAll();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Company find(@PathParam("id") Long id) {
        return companyFacade.find(id);
    }
    
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Company> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return companyFacade.findRange(new int[]{from, to});
    }
    
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(companyFacade.count());
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Company entity) {
        companyFacade.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Company entity) {
        companyFacade.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        companyFacade.remove(companyFacade.find(id));
    }
}

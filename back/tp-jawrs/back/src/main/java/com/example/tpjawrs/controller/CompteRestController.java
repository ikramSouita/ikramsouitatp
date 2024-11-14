package com.example.tpjawrs.controller;

import com.example.tpjawrs.model.Compte;
import com.example.tpjawrs.repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;

@Path("/banque")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) // Accept both JSON and XML
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) // Return both JSON and XML
public class CompteRestController {

    @Autowired
    private CompteRepository compteRepository;

    private static final Logger logger = LoggerFactory.getLogger(CompteRestController.class);

    @GET
    @Path("/comptes")
    public List<Compte> getComptes(@Context HttpHeaders headers) {
        logRequestFormat(headers);
        return compteRepository.findAll();
    }

    @GET
    @Path("/comptes/{id}")
    public Compte getCompte(@PathParam("id") Long id, @Context HttpHeaders headers) {
        logRequestFormat(headers);
        return compteRepository.findById(id).orElse(null);
    }

    @POST
    @Path("/comptes")
    public Compte addCompte(Compte compte, @Context HttpHeaders headers) {
        logRequestFormat(headers);
        return compteRepository.save(compte);
    }



    @DELETE
    @Path("/comptes/{id}")
    public void deleteCompte(@PathParam("id") Long id, @Context HttpHeaders headers) {
        logRequestFormat(headers); // Log pour vérifier si l'API est appelée
        if (compteRepository.existsById(id)) {
            compteRepository.deleteById(id);
        } else {
            throw new WebApplicationException("Compte introuvable", 404);
        }
    }
    @PUT
    @Path("/comptes/{id}")
    public Compte updateCompte(@PathParam("id") Long id, Compte compte, @Context HttpHeaders headers) {
        logRequestFormat(headers);
        Compte existingCompte = compteRepository.findById(id).orElse(null);
        if (existingCompte != null) {
            existingCompte.setSolde(compte.getSolde());
            existingCompte.setType(compte.getType());
            existingCompte.setDateCreation(compte.getDateCreation());
            return compteRepository.save(existingCompte);
        } else {
            throw new WebApplicationException("Compte introuvable", 404);
        }
    }

    private void logRequestFormat(HttpHeaders headers) {
        String contentType = headers.getHeaderString(HttpHeaders.CONTENT_TYPE);
        String accept = headers.getHeaderString(HttpHeaders.ACCEPT);
        logger.info("Incoming request: Content-Type = {}, Accept = {}", contentType, accept);
    }
}

package com.oneandone.training.app.server.controller;


import com.oneandone.training.communication.model.inheritance.SuperHero;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by atrifan on 9/17/2015.
 */
@Path("/shield")
public class Shield {

    //TODO 5: get memory store as singleton

    //tip: you don't have the memory store inside the module you need to import it

    //TODO 6: use spring to get memory store --> edit configuration

    @POST
    @Consumes("application/json")
    public Response registerHero(SuperHero humanBeeing) {

        //TODO 5: verify if in memory store there is this entry;

        //TODO 5: if the humanBeeing does not exist in our DB store it

        //TODO 5: if the humanBeeing has been stored - return to the client the ID - with accepted status

        //TODO 5: if the humanBeeing was already present in DB - return statusCode - FOUND
        return Response.status(Response.Status.ACCEPTED).entity(null).build(); //<--- example
    }

    @GET
    @Produces("application/json")
    public Response getHeroes() {
        //TODO 5: return all the superHeroes
        return null;
    }

    //TODO 7: make a delete endpoint so you can delete an entry

    //TODO 7: make an endpoint so you can get a specific hero
}

package ro.atrifan.training.app.server.controller;


import ro.atrifan.training.communication.model.inheritance.SuperHero;
import ro.atrifan.util.MemoryStore;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Created by atrifan on 9/17/2015.
 */
@Path("/shield")
public class Shield {

    //TODO 5: get memory store as singleton
    MemoryStore memoryStore = MemoryStore.getInstance();

    //tip: you don't have the memory store inside the module you need to import it

    //TODO 6: use spring to get memory store --> edit configuration

    @POST
    @Consumes("application/json")
    public Response registerHero(SuperHero superHero) {

        boolean entryExists = memoryStore.entryExists(superHero);

        Long clientId = null;

        if(!entryExists) {
            clientId = memoryStore.saveEntry(superHero);
            return Response.status(Response.Status.ACCEPTED).entity(clientId.toString()).build();
        }

        clientId = memoryStore.findEntry(superHero);
        //TODO 5: if the humanBeeing was already present in DB - return statusCode - FOUND
        return Response.status(Response.Status.FOUND).entity(clientId.toString()).build();
    }

    @GET
    @Produces("application/json")
    public Response getHeroes() {
        //TODO 5: return all the superHeroes with status OK
        ArrayList<SuperHero> allSuperHeroes = memoryStore.getAll();
        return Response.status(Response.Status.OK).entity(allSuperHeroes).build();
    }

    //TODO 7: make a delete endpoint so you can delete an entry
    @DELETE
    @Path("/{heroId}")
    public Response deleteHeroes(@PathParam("heroId") Long heroId) {
        boolean done = memoryStore.removeEntry(heroId);
        if(done) {
            return Response.status(Response.Status.OK).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }


    //TODO 7: make an endpoint so you can get a specific hero
    @GET
    @Path("/{heroId}")
    @Produces("application/json")
    public Response getHero(@PathParam("heroId") Long heroId) {
        SuperHero theHero = memoryStore.getEntry(heroId);
        if(theHero == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(theHero).build();
    }

}

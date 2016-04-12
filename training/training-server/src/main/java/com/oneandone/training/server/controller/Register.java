package com.oneandone.training.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by alexandru.trifan on 17.09.2015.
 */
@Path("/register")
public class Register {

    private static Logger logger = LoggerFactory.getLogger(Register.class);

    @POST
    @Consumes("application/json")
    public Response register() {
        logger.debug("Registering");
        return Response.ok().build();
    }
}

package com.oneandone.training.app.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by alexandru.trifan on 17.09.2015.
 */
@Path("/login")
public class Login {
    private static Logger logger = LoggerFactory.getLogger(Login.class);

    @POST
    @Consumes("application/json")
    public Response login() {
        logger.debug("Loging in");
        return Response.ok().build();
    }
}

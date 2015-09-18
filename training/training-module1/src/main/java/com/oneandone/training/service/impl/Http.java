package com.oneandone.training.service.impl;

import com.oneandone.util.training.communication.model.inheritance.SuperHero;
import com.oneandone.training.service.Connection;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import java.io.IOException;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

/**
 * Created by alexandru.trifan on 17.09.2015.
 */
public class Http implements Connection {
    String SERVER_INFO_ENDPOINT = "http://localhost:8083/training-server";
    public Response sendInfoAboutSelf(SuperHero myself) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(SERVER_INFO_ENDPOINT).path("/shield");
        Builder request = target.request();
        ObjectMapper jsonMapper = new ObjectMapper();
        String jsonRepresentation = null;
        try {
            jsonRepresentation = jsonMapper.writeValueAsString(myself);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        try {
            Response response = request.post(Entity.entity(jsonRepresentation, APPLICATION_JSON));
            client.close();
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }
}

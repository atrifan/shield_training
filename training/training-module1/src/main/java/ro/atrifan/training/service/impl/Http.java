package ro.atrifan.training.service.impl;

import ro.atrifan.training.service.Connection;
import ro.atrifan.training.communication.model.inheritance.SuperHero;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import java.io.IOException;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by alexandru.trifan on 17.09.2015.
 */
public class Http implements Connection {
    String SERVER_INFO_ENDPOINT = "http://localhost:8080/training-server";
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

    public Response getHeroes() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(SERVER_INFO_ENDPOINT).path("/shield");
        Builder request = target.request();
        ObjectMapper jsonMapper = new ObjectMapper();
        String jsonRepresentation = null;

        try {
            Response response = request.get();
            client.close();
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Response getHero(Long id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(SERVER_INFO_ENDPOINT).path("/shield/" + id);
        Builder request = target.request();

        try {
            Response response = request.get();
            client.close();
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Response deleteHero(Long id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(SERVER_INFO_ENDPOINT).path("/shield/" + id);
        Builder request = target.request();

        try {
            Response response = request.delete();
            client.close();
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

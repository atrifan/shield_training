package com.oneandone.training.app;

import com.oneandone.training.communication.model.inheritance.SuperHero;
import com.oneandone.training.exceptions.TestFailed;
import com.oneandone.training.service.impl.Http;
import com.oneandone.training.service.impl.Persistent;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App {

    enum ConnectionType {
        REST("http"),
        PERSISTENT("persistent");

        private String type;

        ConnectionType(String type) {
            this.type = type;
        }

        public static ConnectionType fromString(String text) {
            if (text != null) {
                for(ConnectionType connectionType : ConnectionType.values()) {
                    if(text.equalsIgnoreCase(connectionType.type)) {
                        return connectionType;
                    }
                }
            }
            return null;
        }
    }

    public static void main( String[] args ) throws TestFailed {
        System.out.println("Welcome to module 1");

        if(args.length < 1) {
            //throw UnsupportedExecutionException
        }

        System.out.println("Initializing connection type - " + args[0]);

        ConnectionType connectionType = ConnectionType.fromString(args[0]);
        SuperHero mySelf = new SuperHero("Gica Cocalarul Number 1", 1, 2, 3, "samanta spit");
        ObjectMapper objectMapper = new ObjectMapper();
        App myApp = new App();

        switch(connectionType) {
            case REST:
                Http httpConnection = new Http();
                Response response = (Response) httpConnection.sendInfoAboutSelf(mySelf);
                response = (Response) httpConnection.sendInfoAboutSelf(mySelf);
                if(myApp.isResponseFound(response)) {
                    System.out.println("TEST OK");
                } else {
                    System.out.println("TEST NOK");
                }

                mySelf = new SuperHero("Gica Cocalarul number 2", 1, 2, 3, "samanta spit power");
                httpConnection.sendInfoAboutSelf(mySelf);
                response = httpConnection.getHeroes();
                long expectedNoHeroes = 2;
                if(myApp.noHeroesCorrect(response, expectedNoHeroes)) {
                    System.out.println("TEST OK NO HEROES");
                } else {
                    System.out.println("TEST NOK NO HEROES");
                }

                if(myApp.testPersist()) {
                    System.out.println("PERSISTENCE TEST PASSED");
                } else {
                    throw new TestFailed("Failed the Persistence Test");
                }

                if(myApp.testDelete()) {
                    System.out.println("DELETE TEST PASSED");
                } else {
                    throw new TestFailed("Failed the Delete Test");
                }
                //reading the entity
                /*String theResponse = response.readEntity(String.class);
                try {
                    Person stuff = objectMapper.readValue(theResponse, Person.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                //TODO 10: call tests - if true output true, else throw customException: StatusNotOK, TestFailed

                break;
            case PERSISTENT:
                Persistent persistentConnection = new Persistent();
                persistentConnection.sendInfoAboutSelf(mySelf);
                break;
            default:
                //throw unsupported method exception
                break;
        }
    }

    public boolean isResponseOk(Response response) {
        if(response.getStatus() == Response.Status.OK.getStatusCode()) {
            return true;
        }

        return false;
    }

    //TODO 9: make a test method to verify that status code is found
    public boolean isResponseFound(Response response) {
        if(response.getStatus() == Response.Status.FOUND.getStatusCode()) {
            return true;
        }
        return false;
    }

    public boolean isResponseAccepted(Response response) {
        if(response.getStatus() == Response.Status.ACCEPTED.getStatusCode()) {
            return true;
        }
        return false;
    }

    //TODO 9: make a test method to verify the number of entries returned by server
    public boolean noHeroesCorrect(Response response, long expectedHeroes) {
        String jsonResponse = response.readEntity(String.class);
        ObjectMapper jsonMapper = new ObjectMapper();
        try {
            ArrayList<SuperHero> myHeroes = jsonMapper.readValue(jsonResponse, new TypeReference<ArrayList<SuperHero>>(){});
            return (myHeroes.size() == expectedHeroes);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    //TODO 9: make a test method to verify persist
    public boolean testPersist() {
        SuperHero testHero = new SuperHero();
        testHero.setSuperPower("MANANC");
        Http httpConnection = new Http();
        Response theResponse = (Response) httpConnection.sendInfoAboutSelf(testHero);
        String entryId = theResponse.readEntity(String.class);
        Long longEntryId = Long.parseLong(entryId);
        if(isResponseAccepted(theResponse)) {
            System.out.println("STATUS OK");
        } else {
            return false;
        }

        theResponse = httpConnection.getHero(longEntryId);
        String heroAsString = theResponse.readEntity(String.class);
        ObjectMapper mapper = new ObjectMapper();
        SuperHero expectedHero = null;
        try {
            expectedHero = mapper.readValue(heroAsString, SuperHero.class);
        } catch (IOException e) {
            return false;
        }
        if(expectedHero.getName().equals(testHero.getName())) {
            System.out.println("TEST OK OBJECT");
        } else {
            return false;
        }

        httpConnection.deleteHero(longEntryId);

        return true;
    }

    //TODO 9: make a test method to verify delete
    public boolean testDelete() {

        SuperHero testHero = new SuperHero();
        testHero.setSuperPower("MANANC");
        Http httpConnection = new Http();
        Response theResponse = (Response) httpConnection.sendInfoAboutSelf(testHero);
        String entryId = theResponse.readEntity(String.class);
        Long longEntryId = Long.parseLong(entryId);
        if(isResponseAccepted(theResponse)) {
            System.out.println("STATUS OK");
        } else {
            return false;
        }

        theResponse = httpConnection.getHero(longEntryId);
        String heroAsString = theResponse.readEntity(String.class);
        ObjectMapper mapper = new ObjectMapper();
        SuperHero expectedHero = null;
        try {
            expectedHero = mapper.readValue(heroAsString, SuperHero.class);
        } catch (IOException e) {
            return false;
        }
        if(expectedHero.getName().equals(testHero.getName())) {
            System.out.println("TEST OK OBJECT");
        } else {
            return false;
        }

        httpConnection.deleteHero(longEntryId);

        theResponse = httpConnection.getHero(longEntryId);

        return testNotFound(theResponse);
    }

    public boolean testNotFound(Response response) {
        if(response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
            return true;
        }
        return false;
    }



}

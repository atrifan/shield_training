package com.oneandone.training.app;

import com.oneandone.util.training.communication.model.Person;
import com.oneandone.util.training.communication.model.inheritance.SuperHero;
import com.oneandone.training.service.impl.Http;
import com.oneandone.training.service.impl.Persistent;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.core.Response;
import java.io.IOException;

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

    public static void main( String[] args ) {
        System.out.println("Welcome to module 1");

        if(args.length < 1) {
            //throw UnsupportedExecutionException
        }

        System.out.println("Initializing connection type - " + args[0]);

        ConnectionType connectionType = ConnectionType.fromString(args[0]);
        SuperHero mySelf = new SuperHero();
        ObjectMapper objectMapper = new ObjectMapper();
        App myApp = new App();

        switch(connectionType) {
            case REST:
                Http httpConnection = new Http();
                Response response = httpConnection.sendInfoAboutSelf(mySelf);

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
        return true;
    }

    //TODO 9: make a test method to verify the number of entries returned by server
    public boolean noHeroesCorrect(Response response, long expectedHeroes) {
        return true;
    }
    //TODO 9: make a test method to verify persist
    public boolean testPersist() {
        return true;
    }

    //TODO 9: make a test method to verify delete
    public boolean testDelete() {
        return true;
    }



}

package com.oneandone;

/**
 * Created by atrifan on 10/7/2015.
 * !|ActionFixture|
 |start|ActionExample|
 |enter|setName|Hello|
 |enter|setSurName|World|
 |press|getFullName|
 |check|checkFullName|Hello World|
 */

import com.oneandone.training.communication.model.inheritance.SuperHero;
import com.oneandone.training.service.impl.Http;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.core.Response;
import java.io.IOException;

/**TODO MAKE IT FOR SUPERHERO PERSIST**/
public class ActionExample extends fit.Fixture {
    private String firsName;
    private String lastName;
    private String fullName;
    private SuperHero mySuperHero;
    private long entryInDb;

    public ActionExample() {
        mySuperHero = new SuperHero();
    }

    public void setName(String name) {
        mySuperHero.setName(name);
    }

    public void setSuperPower(String power) {
        mySuperHero.setSuperPower(power);
    }

    public void persistInDb() {
        Http myHttpHelper = new Http();
        Response response = myHttpHelper.sendInfoAboutSelf(mySuperHero);
        String entryId = response.readEntity(String.class);
        Long theEntryFromDb = Long.parseLong(entryId);
        entryInDb = theEntryFromDb;
    }

    public String checkNameFromDb() throws IOException {
        Http myHttpHelper = new Http();
        Response response = myHttpHelper.getHero(entryInDb);
        String heroAsString = response.readEntity(String.class);
        ObjectMapper mapper = new ObjectMapper();
        SuperHero expectedHero = null;
        expectedHero = mapper.readValue(heroAsString, SuperHero.class);
        return expectedHero.getName();
    }

    public void setSurName(String surName) {
        this.lastName = surName;
    }

    public void getFullName() {
        this.fullName = this.firsName + " " + this.lastName;
    }

    public String checkFullName() {
        return this.fullName;
    }

    //iniatiateSuperHero
    //setName
    //setSuperPower
    //persistInDB
    //checkNameFromDB -- verifica superoul de persistat ca este acelasi cu cel luat din baza de date
}

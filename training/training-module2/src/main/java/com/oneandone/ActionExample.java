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
/**TODO MAKE IT FOR SUPERHERO PERSIST**/
public class ActionExample extends fit.Fixture {
    private String firsName;
    private String lastName;
    private String fullName;

    public void setName(String name) {
        this.firsName = name;
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
}

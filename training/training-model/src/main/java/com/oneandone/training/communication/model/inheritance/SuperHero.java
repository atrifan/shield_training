package com.oneandone.training.communication.model.inheritance;

/**
 * Created by alexandru.trifan on 17.09.2015.
 */

import com.oneandone.training.communication.model.Person;

/**
 * This class needs to inherit the person class from `training-model`
 * Tip: you are using maven, first you must import your dependency module
 * You must use overloading and overriding - on whatever method you want.
 *
 * You must be able to set a new name for the person - changing the Person form immutable to mutable
 */
public class SuperHero extends Person{

    private String superPower;

    public SuperHero() {};

    public SuperHero(String name, long age, long height, float speed, String superPower) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.speed = speed;
        this.superPower = superPower;
    }

    public String getSuperPower() {
        return superPower;
    }

    public void setSuperPower(String superPower) {
        this.superPower = superPower;
    }
    //TODO: implement
}

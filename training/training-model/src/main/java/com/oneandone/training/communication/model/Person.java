package com.oneandone.training.communication.model;

import java.io.Serializable;

/**
 * Created by alexandru.trifan on 17.09.2015.
 */

/**
 * Imutable example
 */
public class Person implements Serializable{

    protected String name = "John Doe";
    protected long age = 25;
    protected float speed = 4.5F;
    protected long height = 152;

    public String getName() {
        return this.name;
    }

    public float getSpeed() {
        return this.speed;
    }

    public long getHeight() {
        return this.height;
    }

    public long getAge() {
        return this.age;
    }

    public void setName(String name) {
        this.name = name;
    }

}

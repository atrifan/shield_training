package com.oneandone.training.service;


import com.oneandone.training.communication.model.inheritance.SuperHero;

/**
 * Created by alexandru.trifan on 17.09.2015.
 */
public interface Connection {

    public Object sendInfoAboutSelf(SuperHero myself);

    //TODO 8: deleteEntry
    //TODO 8: getAllEntries
    //TODO 8: getOneEntry
}

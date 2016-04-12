package com.oneandone.util;


import com.oneandone.training.communication.model.inheritance.SuperHero;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by atrifan on 9/17/2015.
 */
public class MemoryStore {

    protected HashMap<Long, SuperHero> storage = new HashMap<Long, SuperHero>();
    protected Long id = 0L;

    //TODO 4: make this class a singleton

    public synchronized long saveEntry(SuperHero newHero) {
        storage.put(id, newHero);
        return id++;
    }

    public synchronized void removeEntry(long id) {
        storage.remove(id);
    }

    public synchronized boolean entryExists(SuperHero heroToFind) {
        return storage.containsValue(heroToFind);
    }

    public SuperHero getEntry(long id) {
        return storage.get(id);
    }

    public ArrayList<SuperHero> getAll() {
        Collection<SuperHero> superHeroes = storage.values();
        ArrayList<SuperHero> superHeroesResponse = new ArrayList<SuperHero>(superHeroes);
        return superHeroesResponse;
    }

}

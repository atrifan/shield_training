package ro.atrifan.util;


import ro.atrifan.training.communication.model.inheritance.SuperHero;

import java.util.*;

/**
 * Created by atrifan on 9/17/2015.
 */
public class MemoryStore {

    protected HashMap<Long, SuperHero> storage = new HashMap<Long, SuperHero>();
    protected Long id = 0L;

    //TODO 4: make this class a singleton
    private static MemoryStore instance = null;

    public static MemoryStore getInstance() {

        if(instance == null) {
            instance = new MemoryStore();
        }

        return instance;
    }

    public synchronized long saveEntry(SuperHero newHero) {
        storage.put(++id, newHero);
        return id;
    }

    public synchronized boolean removeEntry(long id) {
        if(!storage.containsKey(id)) {
            return false;
        }

        storage.remove(id);
        return true;
    }

    public synchronized boolean entryExists(SuperHero heroToFind) {
        Collection<SuperHero> values = storage.values();
        boolean found = false;
        for(SuperHero entry : values) {
            if(entry.getName().equals(heroToFind.getName())) {
                found = true;
                break;
            };
        }

        return found;
    }

    public synchronized long findEntry(SuperHero heroToFind) {
        Set<Long> keys = storage.keySet();

        for(Long key : keys) {
            SuperHero entry = storage.get(key);
            if(entry.getName().equals(heroToFind.getName())) {
                return key;
            }
        }

        return -1;
    }

    public SuperHero getEntry(long id) {
        if(!storage.containsKey(id)) {
            return null;
        }

        return storage.get(id);
    }

    public ArrayList<SuperHero> getAll() {
        Collection<SuperHero> superHeroes = storage.values();
        ArrayList<SuperHero> superHeroesResponse = new ArrayList<SuperHero>(superHeroes);
        return superHeroesResponse;
    }

}

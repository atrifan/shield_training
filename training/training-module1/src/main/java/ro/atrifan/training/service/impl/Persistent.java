package ro.atrifan.training.service.impl;

import ro.atrifan.training.communication.model.inheritance.SuperHero;
import ro.atrifan.training.service.Connection;

import java.io.*;
import java.net.Socket;

/**
 * Created by alexandru.trifan on 17.09.2015.
 */
public class Persistent implements Connection {

    String DESTINATION_HOST = "localhost";

    public Object sendInfoAboutSelf(SuperHero myself) {
        try {
            Socket clientSocket = new Socket(DESTINATION_HOST, 6789);
            ObjectOutputStream writer = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream reader = new ObjectInputStream(clientSocket.getInputStream());
            writer.writeObject(myself);

            //TODO 2: close connection
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

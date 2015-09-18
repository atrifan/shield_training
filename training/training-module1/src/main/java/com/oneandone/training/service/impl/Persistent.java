package com.oneandone.training.service.impl;

import com.oneandone.util.training.communication.model.inheritance.SuperHero;
import com.oneandone.training.service.Connection;

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

            //because the server does not know me I must send him my super power information when he asks
            //TODO 2: listen for message from server `SUPER-POWER?`


            //TODO 2: answer to message with super power


            //TODO 2: close connection


            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

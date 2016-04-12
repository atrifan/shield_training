package com.oneandone.training.standalone;

import com.oneandone.training.communication.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by alexandru.trifan on 17.09.2015.
 */
public class App {

    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket server = new ServerSocket(6789);
        while(true) {
            Socket connectionSocket = server.accept();
            ObjectInputStream reader = new ObjectInputStream(connectionSocket.getInputStream());
            ObjectOutputStream writer = new ObjectOutputStream(connectionSocket.getOutputStream());
            Person connectionIdentity = (Person) reader.readObject();
            System.out.println(String.format("%s connected he is of age %s years, %s cm tall and runs %s km/h",
                    connectionIdentity.getName(),
                    connectionIdentity.getAge(),
                    connectionIdentity.getHeight(),
                    connectionIdentity.getSpeed()));
            writer.writeChars("SUPER-POWER?");
            String superPower = (String) reader.readObject();
            System.out.println(String.format("%s has an ability of %s", connectionIdentity.getName(), superPower));
        }
    }
}

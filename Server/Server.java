/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Misc.Shutdown;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author gydo194
 */
public class Server implements Runnable {

    private ServerSocket ssock;

    public Server(int port) {
        try {
            ssock = new ServerSocket(port);
        } catch (IOException e) {
            System.err.printf("[ERROR] failed to bind to port '%d'.\n", port);
            Shutdown.shutdown();
        }
    }

    private void listen() {
        Socket csock = null;
        try {
            csock = ssock.accept(); //blocking call to wait for a client to connect //ITE: does not block if the serversocket could not bind to port
            Client cl = new Client(csock);
            Thread newClientThread = new Thread(cl);
            newClientThread.setName("Handling thread for " + cl.getAddressAsString());
            System.out.printf("[INFO] Client '%s' connected.\n", cl.getAddressAsString());
            newClientThread.start(); //start the thread

        } catch (IOException e) {
            System.err.println("[ERROR] Server::listen() caught IOException while waiting for client to connect. Dumping stacktrace...");
            e.printStackTrace();
        } catch (NullPointerException ex) {
            System.err.println("[ERROR] Server::listen() cannot connect to client (Socket.accept() threw nullPointer)");
        }

    }

    public void run() {
        while (true) {
            listen();
        }
    }

}

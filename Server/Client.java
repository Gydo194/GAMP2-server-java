/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Functions.FunctionParser;
import Group.ClientGroupHandler;
import Misc.ClientFunctionHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author gydo194
 */
public class Client implements Runnable {

    private Socket connection;
    private FunctionParser clientFP;
    private ClientFunctionHandler clientFH;

    /*
    public Client() {
        //testing mode
        System.err.println("[WARN] Instantiating client in test mode");
        init();

    }
     */
    public Client(Socket connection) {
        this.connection = connection;
        if (connection == null) {
            System.err.println("[ERROR] cannot get socket, got null.");
           // throw new 
        }
        init();
    }

    public void dispatchMessage(String message) {
        try {
            OutputStream ou = this.connection.getOutputStream();
            PrintWriter pw = new PrintWriter(ou);
            System.out.printf("Sending message '%s' to client '%s'.\n", message, this.connection.getInetAddress().getHostAddress());
            pw.print(message);
            pw.flush(); //send the damn message instead of waiting for the buffer to be filled

        } catch (IOException | NullPointerException e) {
            System.err.println("dispatchMessage caught exception. is client in test mode?");
            e.printStackTrace();
        }
    }

    private void init() {
        //setup the environment
        clientFH = new ClientFunctionHandler(this); //create a new client function parser for this client
        clientFP = new FunctionParser(clientFH); //create a new function parser that uses the newly created clientFunctionHandler as function call handler
    }

    @Override
    public String toString() {
        return "Client instance for " + connection.getInetAddress().getHostAddress() + " (" + connection.getInetAddress().getHostName() + ") tid:" + Thread.currentThread();
    }

    public String getAddressAsString() {
        return connection.getInetAddress().getHostAddress().toString() + " (" + connection.getInetAddress().getHostName() + ")";
    }

    private void handle(String input) {
        System.out.printf("[DEBUG] Client::handle() got: '%s'.\n", input);
        clientFP.process(input);
    }

    /**
     *
     */
    @Override
    public void run() {
        InputStream ins;
        Scanner in;
        String recv;
        try {
            ins = connection.getInputStream();
            in = new Scanner(ins);

            while (true) {
                recv = in.nextLine(); //maybe read per-char? tough use blocking call
                handle(recv);
            }

        } catch (IOException e) {
            System.err.println("[ERROR] Client::run() caught IOException. dumping details below.");
            System.err.println("[ERROR INFO] Client info: " + toString());
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            System.out.printf("[INFO] Client  '%s' disconnected\n", getAddressAsString());
            //throw the client offline, remove it from groups.
            //client group handler .remove(this)
            ClientGroupHandler.removeFromAll(this);
        }
    }

}

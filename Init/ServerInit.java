/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Init;

import ClientFunctions.AddToGroupFunction;
import ClientFunctions.Base64DecodeSendFunction;
import ClientFunctions.CreateGroupFunction;
import ClientFunctions.DispatchToGroupFunction;
import ClientFunctions.GroupExistsFunction;
import ClientFunctions.RemoveFromAllFunction;
import ClientFunctions.ShutdownFunction;
import Misc.ClientFunctionHandler;
import ClientFunctions.TestFunction;
import Server.Server;

/**
 *
 * @author gydo194
 */
public class ServerInit {

    public static void main(String[] args) {
        System.out.println("[INFO] [BOOTSTRAP] Starting server (ServerInit)...");
             
        System.out.println("[DEBUG] [BOOTSTRAP] creating functions...");
        TestFunction tf = new TestFunction(); //create new function
        AddToGroupFunction atgf = new AddToGroupFunction();
        DispatchToGroupFunction dtgf = new DispatchToGroupFunction();
        RemoveFromAllFunction rfaf = new RemoveFromAllFunction();
        CreateGroupFunction cgf = new CreateGroupFunction();
        ShutdownFunction sf = new ShutdownFunction();
        GroupExistsFunction gef = new GroupExistsFunction();
        Base64DecodeSendFunction bdsf = new Base64DecodeSendFunction();
        
        
        
        System.out.println("[DEBUG] [BOOTSTRAP] done creating functions");

        System.out.println("[DEBUG] [BOOTSTRAP] adding functions...");
        ClientFunctionHandler.addFunction("ping", tf); //add it to the client functions
        ClientFunctionHandler.addFunction("join", atgf);
        ClientFunctionHandler.addFunction("send", dtgf);
        ClientFunctionHandler.addFunction("leave", rfaf);
        ClientFunctionHandler.addFunction("create", cgf);
        ClientFunctionHandler.addFunction("shutdown", sf);
        ClientFunctionHandler.addFunction("exists", gef);
        ClientFunctionHandler.addFunction("bsend", bdsf);
        
        System.out.println("[DEBUG] [BOOTSTRAP] done adding functions");
        
        System.out.println("[DEBUG] [BOOTSTRAP] starting server...");
        Server s = new Server(3000);
       

        System.out.println("[DEBUG] [BOOTSTRAP] creating server thread...");
        Thread serverThread = new Thread(s);
        System.out.println("[DEBUG] [BOOTSTRAP] done creating server thread");

        System.out.println("[DEBUG] [BOOTSTRAP] starting server thread...");
        serverThread.start();
       
        System.out.println("[INFO] Server Started (ServerInit)");

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientFunctions;

import ArgumentHandling.ArgumentParser;
import Group.ClientGroupHandler;
import Server.Client;
import Misc.Function;
import util.Base64;

/**
 *
 * @author gydo194
 */
public class Base64DecodeSendFunction implements Function {
      public void call(String argument, Client caller) {
        System.out.printf("[DEBUG] Base64DecodeSendFunction got argument '%s' from client '%s'.\n",argument,caller.getAddressAsString());
        //ClientGroupHandler.dispatchAll(argument.split(",")[0], argument.split(",")[1]); //do a argument split here
        ArgumentParser ap = new ArgumentParser(argument);
        String group = ap.next(); //first argument
        if(!ap.hasNext()) {
            System.err.println("[ERROR] Base64DecodeSendFunction::call(): cannot get second argument, aborting.");
            return;
        }
        String message = ap.next();
        String b64d_message = new String(Base64.decode(message));
        System.out.println("Base64 decoded message = " + b64d_message);
        ClientGroupHandler.dispatchAll(group, b64d_message);
        
    }
}

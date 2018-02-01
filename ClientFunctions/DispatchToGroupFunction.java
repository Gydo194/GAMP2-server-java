/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientFunctions;

import ArgumentHandling.ArgumentParser;
import Group.ClientGroupHandler;
import Misc.Function;
import Server.Client;

/**
 *
 * @author gydo194
 */
public class DispatchToGroupFunction implements Function {
    public void call(String argument, Client caller) {
        System.out.printf("[DEBUG] DispatchToAllFunction got argument '%s' from client '%s'.\n",argument,caller.getAddressAsString());
        //ClientGroupHandler.dispatchAll(argument.split(",")[0], argument.split(",")[1]); //do a argument split here
        ArgumentParser ap = new ArgumentParser(argument);
        String group = ap.next(); //first argument
        if(!ap.hasNext()) {
            System.err.println("[ERROR] DispatchToGroupFunction::call(): cannot get second argument, aborting.");
            return;
        }
        String message = ap.next();
        ClientGroupHandler.dispatchAll(group, message);
        
    }
}

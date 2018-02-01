/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientFunctions;

import Group.ClientGroupHandler;
import Misc.Function;
import Server.Client;

/**
 *
 * @author gydo194
 */
public class CreateGroupFunction implements Function {
    public void call(String argument, Client caller) {
        System.out.printf("[DEBUG] CreateGroupFunction::call(): got '%s' from client '%s'.\n",argument,caller.getAddressAsString());
        ClientGroupHandler.addGroup(argument);
    }
}

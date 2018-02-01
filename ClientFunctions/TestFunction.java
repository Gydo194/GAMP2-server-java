/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientFunctions;

import Misc.Function;
import Server.Client;

/**
 *
 * @author gydo194
 */
public class TestFunction implements Function {
    public void call(String argument, Client caller) {
       // System.out.printf("[DEBUG] TestFunction::call(): got '%s' from client '%s'.\n",argument,caller.getAddressAsString());
        System.err.printf("[DEBUG] PING from '%s'.\n",caller.getAddressAsString());
        caller.dispatchMessage("PONG, you sent: '" + argument + "'.");
    }
}

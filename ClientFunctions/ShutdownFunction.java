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
public class ShutdownFunction implements Function {
    
    public void call(String argument, Client caller) {
        System.err.printf("[SHUTDOWN] client '%s' requested server shutdown, going down NOW.\n",caller.getAddressAsString());
        System.exit(0);
    }
    
}

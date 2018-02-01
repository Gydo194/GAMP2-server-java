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
public class GroupExistsFunction implements Function {
    @Override
    public void call(String argument, Client caller) {
        if(ClientGroupHandler.hasGroup(argument)) {
            caller.dispatchMessage("true");
        }
        else {
            caller.dispatchMessage("false");
        }
    }
    
    
}

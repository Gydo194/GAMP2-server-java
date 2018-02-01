/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Misc;

import Server.Client;

/**
 *
 * @author gydo194
 */
public interface Function { //the Function interface is application-specific, it's not part of the Function Handling subsystem
    public void call(String argument, Client callingClient);
}

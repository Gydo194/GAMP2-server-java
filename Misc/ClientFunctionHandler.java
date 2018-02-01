/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Misc;

import Functions.FunctionHandler;
import Server.Client;
import java.util.HashMap;

/**
 *
 * @author gydo194
 */
public class ClientFunctionHandler implements FunctionHandler {
    
    private Client client;
    private  static HashMap<String,Function> functions = new HashMap<>();
    
    public static void addFunction(String name, Function f) { functions.put(name,f); }
    
    public ClientFunctionHandler(Client c) {
        client = c; //avoid this being null
    }
    
    @Override
    public boolean hasFunction(String name) {
        return functions.containsKey(name);
    }
    
    @Override
    public void call(String name, String argument) {
        (functions.get(name)).call(argument, client);
    }
    
}

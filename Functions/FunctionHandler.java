/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Functions;

/**
 *
 * @author gydo194
 */
public interface FunctionHandler {
    public boolean hasFunction(String name);
    public void call(String name, String argument);
   // public Function getFunction(String name); //just in case, maybe get the function and call it in proper context
     
    //take storing and adding functions for account in implementations.
    
}

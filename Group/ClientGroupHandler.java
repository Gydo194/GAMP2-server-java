/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group;

import Server.Client;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * everything declared static so we can access the same from anywhere in the
 * code
 *
 * @author gydo194
 */
public class ClientGroupHandler {

    private static HashMap<String, ArrayList<Client>> groups = new HashMap();

    public static void addGroup(String groupName) {
        if (hasGroup(groupName)) {
            System.err.printf("[WARN] ClientGroupHandler::addGroup(): group '%s' already exists",groupName);
            return; //if group already exists no need to add; exit function
        }
        System.out.printf("[INFO] ClientGroupHandler::addGroup(): creating group '%s'.\n",groupName);
        ArrayList ngrouplist = new ArrayList<>();
        groups.put(groupName, ngrouplist);
    }

    public static void removeGroup(String groupName) {
        if (!hasGroup(groupName)) {
            System.err.println("[WARN] ClientGroupHandler::removeGroup(): group does not exist");
            return;
        }
        System.out.printf("[INFO] ClientGroupHandler::removeGroup(): removing group '%s'.\n",groupName);
        groups.remove(groupName);
    }

    public static boolean hasGroup(String groupName) {
        return groups.containsKey(groupName);
    }

    public static ArrayList<Client> getGroup(String name) {
        if (!hasGroup(name)) {
            return null;
        }
        return groups.get(name);
    }

    public static void dispatchAll(String groupName, String message) {
        if (!hasGroup(groupName)) {
            return;
        }
        for (Client cl : getGroup(groupName)) {
            cl.dispatchMessage(message);
        }
    }

    //client functions
    public static void addToGroup(String groupName, Client cl) {
        if (!hasGroup(groupName)) {
            System.err.println("[WARN] ClientGroupHandler::addToGroup(): group does not exist");
            return;
        }
        System.out.printf("[INFO] ClientGroupHandler::addToGroup(): adding client '%s' to group '%s'.\n",cl.getAddressAsString(),groupName);
        getGroup(groupName).add(cl);
    }

    public static void removeFromGroup(String groupName, Client cl) {
        if (!hasGroup(groupName)) {
            return;
        }
        getGroup(groupName).remove(cl);
    }

    public static void removeFromAll(Client c) {
        //iterate the groups hashmap using a lambda expression on the entrySet
        groups.entrySet().forEach(entr -> {
            ArrayList al = entr.getValue();
            System.out.printf("[DEBUG] ClientGroupHandler::removeFromAll(): iterating key '%s'.\n", entr.getKey());
            if (al.contains(c)) {
                al.remove(c);
            }
        });
    }

}

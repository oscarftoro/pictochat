/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.util.ArrayList;


/**
 *This class handle the connections
 * saving the connections in an ArrayList
 * @author oscar felipe toro DATW12 <oscar@groovenino.com>
 */
//Service which have to use a Thread Pool executor
public class ConnectionsManager {
    
    //Array List with conections
    private ArrayList<ConnectionModel> connections = new ArrayList<>();
    //An instance of this class
    private static ConnectionsManager singleton = new ConnectionsManager();
    
   //return one instance of this class by Singleton
    public static ConnectionsManager getInstance(){
        return singleton;
    }

    
    /*
     * send message to all the conected clients
     * @param message as a String
     */
    public void sendMessageToAll(String message){
        for(ConnectionModel cm : connections){
            //send message to connected clients
            System.out.println("I in connectionsManager: "+ message);
            cm.sendMessage(message);
        }
    }
    /*
     * add new socket to the array list when a new client arrives
     */    
    public void connectNew(ConnectionModel cm){
        connections.add(cm);
    }

    
   
    
}

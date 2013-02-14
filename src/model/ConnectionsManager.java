/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author oscar felipe toro DATW12 <oscar@groovenino.com>
 */
//Service which have to use a Thread Pool executor
public class ConnectionsManager {
    private ArrayList<ConnectionModel> connections = new ArrayList<>();
    
    private static ConnectionsManager singleton = new ConnectionsManager();
    
   
    public static ConnectionsManager getInstance(){
        return singleton;
    }

    
    
    public void sendMessageToAll(String message){
        for(ConnectionModel cn : connections){
            //send message to connected clients
            cn.sendMessage(message);
        }
    }
    /*
     * add new socket to the array list when a new client arrives
     */    
    public void connectNew(ConnectionModel cm){
        connections.add(cm);
    }
   
    
}

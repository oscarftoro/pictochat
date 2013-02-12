/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oscar felipe toro DATW12 <oscar@groovenino.com>
 */
public class ServerModel {
    
    int port = 6780;
    ServerSocket serverSocket;
    Socket socket;
    /**
     * Open a ServerSocket
     * @param port 
     */
    public void openPort(int port){
        try{
            //create a Server Port
            serverSocket = new ServerSocket(port);
            //waiting for anyone to connect
            socket = serverSocket.accept();
            
        }catch (Exception ex){
            
            Logger.getLogger(ServerModel.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        
    }
    public void closePort(){
        try{
            socket.close();
        }catch (Exception ex){
            Logger.getLogger(ServerModel.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    /*
     * define a port
     */
    public void setPort(int port) {
        this.port = port;
    }
 
    
}

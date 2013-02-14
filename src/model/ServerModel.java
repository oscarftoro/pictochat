/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;

/**
 *
 * @author oscar felipe toro DATW12 <oscar@groovenino.com>
 */
public class ServerModel extends TCPModel {
    
    ServerSocket serverSocket;
    
    public ServerModel(){
        
    }
   
    public Task<Boolean> doOpenPort(final int port){
        return new Task<Boolean>(){
            @Override
            protected Boolean call() {
                 try{
                        //create a Server Port
                        serverSocket = new ServerSocket(port);
                        //waiting for anyone to connect
                        //socket from TCPModel abstract class
                        socket = serverSocket.accept();
                       
                        //System.out.println("In Server: Is this a JFX Thread?" + Platform.isFxApplicationThread());
                         return true;
                 }catch (Exception ex){
            
                    Logger.getLogger(ServerModel.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        };
    }
  
    
    public void setSocketData(int port) {
        this.port = port;
    }
    //TODO I
    //add clients to the server
    //good candidate to be a service
    public void run(){
        serverSocket = null;
        try {
            //socket object? or just a new socket??...21:37
            while(true){
            socket = serverSocket.accept();
            ConnectionsManager.getInstance().connectNew(new ConnectionModel(socket));
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    
    
 
    
}

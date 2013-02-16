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
                int send = 0;
                 try{
                        //create a Server Port
                        serverSocket = new ServerSocket(port);
                        //send a text actualisation of the actual process
                        updateMessage("Waiting for connecting..");
                        
                        while(true){
                        //waiting for anyone to connect
                        //socket from TCPModel abstract class
                        socket = serverSocket.accept(); 
                        if(send==0){
                        updateMessage("Connected..");
                        send =1;}
                        //connect new clients
                        
                        ConnectionsManager.getInstance().connectNew(new ConnectionModel(socket));
                        
                        updateMessage("new client connected");
                                
                        //update message in Gui the first time
              
                        }
                        
                 }catch (Exception ex){
                    try {
                        Logger.getLogger(ServerModel.class.getName()).log(Level.SEVERE, null, ex);
                        socket.close();
                        return false;
                    } catch (IOException ex1) {
                        updateMessage("I/O Error");
                        Logger.getLogger(ServerModel.class.getName()).log(Level.SEVERE, null, ex1);
                    return false;
                    }
                }
            }
        };
    }
  

    public void setSocketData(int port) {
        this.port = port;
    }

    

    
    
    
    
 
    
}

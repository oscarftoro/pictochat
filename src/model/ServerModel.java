/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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

    

    
    
    
    
 
    
}

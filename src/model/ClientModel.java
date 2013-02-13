/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;

/**
 *
 * @author oscar felipe toro DATW12 <oscar@groovenino.com>
 */
public class ClientModel extends TCPModel {
    int port = 6780;
    private String url ="127.0.0.1";

    public Task<Boolean> doOpenPort(final String url,final int port){
        return new Task<Boolean>() {

            @Override
            protected Boolean call() {
                
                try{
            //socket from the abstract class TCPModel 
                    socket = new Socket(url, port);
                    System.out.println("socket created in client");
                    return true;
                } catch (Exception ex) {
                    
                    Logger.getLogger(ServerModel.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
            //Override the message methods to get a comprehensive text on the GUI
            @Override protected void succeeded() {
             super.succeeded();
             updateMessage("Connected");
            }
            
            @Override protected void failed() {
             super.failed();
             updateMessage("Connection Failed");
         }
          
        };
    }   
}

    
    

    


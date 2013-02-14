/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Service;
import javafx.concurrent.Task;


/**
 *
 * @author oscar felipe toro DATW12 <oscar@groovenino.com>
 */
public class ClientModel extends  Service<String>{
    Socket socket;
    int port = 6780;
    private String url ="127.0.0.1";
    
    
    //TASKS
    
    //This task is to send it in a background thread to open ports outside JFX Application Thread
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
            @Override protected void succeeded() {
                    super.succeeded();
                    updateMessage("Connected");
                }
            @Override protected void failed() {
                 super.failed();
                 updateMessage("Connection Failed");
            }
            @Override protected void cancelled() {
                super.cancelled();
                updateMessage("Connection Cancelled");
            }
        };
    } 
    
    // Task to send a message to the server
    //
    public Task<Void> sendMessage(final String message){
        return new Task<Void>(){  
            @Override public Void call() {
                try{
                    //Strings or whatever to Byte Vector
                    DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
                    dataOut.writeUTF(message);
            
                    }catch (Exception ex) {
                        
                        Logger.getLogger(ServerModel.class.getName()).log(Level.SEVERE, null, ex);
                }       
                return null;
            }
        };
    }

    
    
//    public void sendMessage(String message){
//        try{
//            //Strings or whatever to Byte Vector
//            DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
//            dataOut.writeUTF(message);
//            
//        }catch (Exception ex) {
//                    
//            Logger.getLogger(ServerModel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    
    //TO DO II
    //read messages - run for ever 
    //candidate to be a deamon thread
    public void run(){
        try {
            socket = new Socket(url,port);
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            while(true){
               String messageIn = dataInputStream.readUTF();
               //send message ClientController.receivedMessage(messageIn);
               //TO IMPLEMENT WITH TASK???
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Task<String> createTask() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
        
}

    
    

    


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
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
/**
 *
 * @author oscar felipe toro DATW12 <oscar@groovenino.com>
 */
public class ClientModel extends Service<String>{
    Socket socket;
    int port = 6780;
    Boolean booly = true;
     
    private String url ="127.0.0.1";
    
    /*
     * Send the message to this.socket
     */
    public void sendMessage(String message){
        try {
            
            //writes primitive data types in our socket
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            //send the message as a String of type UTF-8 to the socket
            dataOutputStream.writeUTF(message);

            
        } catch (IOException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setUrlAndPort(String url, int port) {
        this.url = url;
        this.port = port;
    }
       
    @Override
    protected Task createTask() {
        return new Task<String>(){
            @Override
            protected String call(){ 
                String taskChatText =null;
                try {
                    //socket from the abstract class TCPModel 
                    socket = new Socket(url, port);
                   
                    updateMessage("Connected");
                    
                        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                         
//                            while (booly) {
                                taskChatText = dataInputStream.readUTF();
                                
                              if(taskChatText != null){
                                System.out.println(taskChatText);
                              }  
                                //send message ClientController.receivedMessage(messageIn);
//                              if(messageIn.equals(".quit")){booly=false;}
                              return taskChatText;
//                            }
                    } catch (UnknownHostException ex) {
                        updateMessage("error desconocido");
                        Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        updateMessage("I/O Error");
                        System.out.println("io error");
                        Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                //FIX THIS AND CLOSE THE SOCKET PROPERLY...
//                    try {
//                        socket.close();
//                    } catch (IOException ex) {
//                        System.out.println("closing socket error");
//                        Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
//                    }
            return taskChatText;            

           }   
        };
    }
        

        
}

    
    

    


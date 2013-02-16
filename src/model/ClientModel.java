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
public class ClientModel extends Service<Void>{
    Socket socket;
    int port = 6780;
    Boolean booly = true;
    String messageIn="!";

    public String getMessageIn() {
        return messageIn;
    }
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
        return new Task<Void>(){
            @Override
            protected Void call(){                
                try {
                    //socket from the abstract class TCPModel 
                    socket = new Socket(url, port);
                    System.out.println("we have a socket");
                    updateMessage("Connected");
                    
                        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                         
                            while (booly) {
                                messageIn = dataInputStream.readUTF();
 //HOW TO SEND THE MESSAGES TO THE VIEW???!!!
                                
                              if(messageIn!= null){
                                System.out.println(messageIn);
                              }
                              
                                //send message ClientController.receivedMessage(messageIn);

                                
//                              if(messageIn.equals(".quit")){booly=false;}
                            }
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
            return null;            

           }   
        };
    }
        

        
}

    
    

    


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oscar felipe toro DATW12 <oscar@groovenino.com>
 */
public class ConnectionModel extends TCPModel{
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    
    //Constructor
    public ConnectionModel(Socket socket) {
        this.socket = socket;
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream =  new DataOutputStream(socket.getOutputStream());
            //  start(); to run the thread
        } catch (IOException ex) {
            Logger.getLogger(ConnectionModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    //TODO I
    //receive infinetly messages from the client
    public void run(){
        while (true){
            try {
                String stringDataInputStram= dataInputStream.readUTF();
                //send message to client(s)
                ConnectionsManager.getInstance().sendMessageToAll(stringDataInputStram);
            } catch (IOException ex) {
                Logger.getLogger(ConnectionModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void sendMessage(String message){
        try {
            dataOutputStream.writeUTF(message);
        } catch (IOException ex) {
            Logger.getLogger(ConnectionModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

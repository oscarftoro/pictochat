
package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 *
 * @author oscar felipe toro DATW12 <oscar@groovenino.com>
 */
public class ConnectionModel extends Thread{
    
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    
    public ConnectionModel(Socket socket){
        this.socket = socket;
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream((socket.getOutputStream()));
            
            //UTROLIG VIGTIGT er at starter trådet ellers sker der ikke noget skid
            start();
        } catch (IOException ex) {
            Logger.getLogger(ConnectionModel.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    /*
     * send messages to all the connected clients 
     */
    public void sendMessage(String message){
        try {
            dataOutputStream.writeUTF(message);
        } catch (IOException ex) {
            Logger.getLogger(ConnectionModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
            @Override
            public void run() {
                while (true){
                    try {
                        String stringDataInputStream= dataInputStream.readUTF();
                        
                        //send message to cliente
                        ConnectionsManager.getInstance().sendMessageToAll(stringDataInputStream);
                        
                        
                    } catch (IOException ex) {
                        Logger.getLogger(ConnectionModel.class.getName()).log(Level.SEVERE, null, ex);
                    }
   
                }
    }

    
    //receive message from client for ever
    private void receiveMessageFromClient() {
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
}

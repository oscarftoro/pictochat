
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
public class ConnectionModel extends Thread{
    
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    
    public ConnectionModel(Socket socket){
        this.socket = socket;
        dataStream();
       
    }
    /*
     * THIS IS NOT WRITTING ANYTHING
     * send messages to all the connected clients 
     */
    /**
     *
     */
    public synchronized void dataStream (){
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream((socket.getOutputStream()));
            this.setName("Connection_Model_Thread");
            //UTROLIG VIGTIGT!  at starte trådet ellers sker der ikke noget skid
            start();
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
                //here can we implement  a queue
                ConnectionsManager.getInstance().sendMessageToAll(stringDataInputStream);

                } catch (IOException ex) {
                    Logger.getLogger(ConnectionModel.class.getName()).log(Level.SEVERE, null, ex);
                }

        }
    }
    public synchronized void sendMessage(String message){
        try {
            
            dataOutputStream.writeUTF(message);
        } catch (IOException ex) {
            Logger.getLogger(ConnectionModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oscar felipe toro DATW12 <oscar@groovenino.com>
 */
public abstract class TCPModel{
    
    Socket socket;
    int port;
    //abstract classes 
    /**
     *
     */
    
    
    public void closePort(){
        try{
            socket.close();
        }catch (Exception ex){
            Logger.getLogger(ServerModel.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    /*
     * define a port
     */
    public void setPort(int port) {
        this.port = port;
    }
    /*
     * return port
     */
    public int getPort(){
        return this.port;
    }
    
    
    
}

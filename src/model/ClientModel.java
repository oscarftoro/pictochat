/*
 * Oscar Felipe Toro feb 2013
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
public class ClientModel extends Service<String>{
    Socket socket;
    int port = 6780;
    Boolean booly = true;
    private String url ="127.0.0.1";
    private String nickName ="undefined";
    
  /*
   * task that send a frame that could be a message,
   * nickname or coordinates of a draw depending of the code
   */
    public Task<Void> sendFrameTask(final int code, final String message){
        return new Task<Void>(){

            @Override
            protected Void call() throws Exception {
                 try {
            
            //writes primitive data types in our socket
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            //send first the code to know what are we talking about(message, nick or coordinates)
            dataOutputStream.writeInt(code);
            //send the frame as a String of type UTF-8 to the socket
            dataOutputStream.writeUTF(message);

                } catch (IOException ex) {
                    Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
            
        };
    }
    /**
     * Task that send message to Socket 
     * @param message
     * @return
     */
    public Task<Void> sendMessageTask(final String message){
        return new Task<Void>(){

            @Override
            protected Void call() throws Exception {
                 try {
            sendFrameTask(2, message);
            //writes primitive data types in our socket
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            //send the message as a String of type UTF-8 to the socket
            dataOutputStream.writeUTF(message);

                } catch (IOException ex) {
                    Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
            
        };
    }
    
    
    public void setUrlAndPort(String url, int port) {
        this.url = url;
        this.port = port;
    }
       
    @Override
    protected  Task createTask() {
        return new Task<String>(){
            @Override
            protected String call() throws IOException{ 
                String taskChatText =null;
                try {
                    //socket from the abstract class TCPModel 
                    socket = new Socket(url, port);
                   
                    updateMessage("Connected");
                    
                        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                         //probably we could try to bind this booly with an 
                        //external switch like a disconnect button
                            while (booly) {
                                taskChatText = dataInputStream.readUTF();
                                
                              if(taskChatText != null){
                                System.out.println(taskChatText);
                              }  
                    
                              return taskChatText;
                            }
                    } catch (UnknownHostException ex) {
                        updateMessage("unknown error");
                        socket.close();//close socket when catch error
                        Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
                        
                    } catch (IOException ex) {
                        updateMessage("I/O Error");
                        System.out.println("io error");
                        socket.close();//close socket when catch error
                        Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
                    }
            return taskChatText;            

           }   
        };
    }
        

        
}

    
    

    


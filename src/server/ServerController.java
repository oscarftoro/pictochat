/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import model.ServerModel;

    
    
    /**
 * FXML Controller class
 *
 * @author oscar felipe toro <oscar@groovenino.com>
 */
public class ServerController implements Initializable {
    
    @FXML TextField port_txt_field;
    @FXML Button open_port_bttn;
    @FXML Label message_lbl;
    ServerModel serverModel= null;
    
    int port;
    
    @FXML
    private void handleOpenPortButton(ActionEvent event){
        //get text from text field and send it to the server 
        port = Integer.parseInt(port_txt_field.getText());
        
         //Initialize the model just one time
        if(serverModel== null){
            message_lbl.setText("Waiting for connection...");
            serverModel = new ServerModel();
            final Task<Boolean> connect = serverModel.doOpenPort(port);
            //actualize the message according to the thread state 
            message_lbl.textProperty().bind(
                    connect.messageProperty());
            ////
            connect.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

                @Override
                public void handle(WorkerStateEvent t) {
                    if(connect.getValue()){
                        
                      //  message_lbl.setText("Suceesfully connected");
                        System.out.println("succesfully connected");
                        // System.out.println(Platform.isFxApplicationThread()+"is JavaFX thread");
                    }else{
                       
                        
                      //  message_lbl.setText("Connection Error");
                        System.out.println("err on connect");
                    }
                }
                });
            connect.setOnFailed(new EventHandler<WorkerStateEvent>() {

                @Override
                public void handle(WorkerStateEvent t) {
                    System.out.println("Connection err");
                    message_lbl.setText("Connection Error");
                    //if wh get an error at start set server model to null
                    serverModel = null;
                }
            });
            new Thread(connect).start();
        }
        else{
        message_lbl.setText("server running...");
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
     
}
     


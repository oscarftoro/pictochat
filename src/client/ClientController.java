/*
 * 
 * Oscar Toro DATW12
 * Implementing with runLater
 */
package client;

import model.ClientModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author oscar felipe toro <oscar@groovenino.com>
 */
public class ClientController implements Initializable {
    
    @FXML
    TextField port_txt_field;
    @FXML
    TextField url_txt_field;
    @FXML
    Button connect_client_bttn;
    @FXML //to show messages to the user
    Label message_client_lbl;

    @FXML
    TextArea text_area_client;
    @FXML
    TextField text_field_client;
    
    ClientModel clientModel;
    
    private EventHandler<WorkerStateEvent> EventHandler;
    
    /**
     * Initializes the controller class.
     */
    @FXML
    private void handleConnectButton(ActionEvent event){
        
        //Same with port...
        String url = url_txt_field.getText();
        
        //get the text from text field (Gui)
        int port = Integer.parseInt(port_txt_field.getText());
        //we instantiate new clients every time the button is pressed
        clientModel = new ClientModel();
        //run a background thread other than JFX Application Thread
   
        
        
        
        //generate a task to invoke it on a background thread
        //otherwise we happend to use the JavaFX Application Thread
        //and that screw up all the app
        
        final Task<Boolean> connect = clientModel.doOpenPort(url,port);
        connect.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

            @Override
            public void handle(WorkerStateEvent t) {
                if(connect.getValue()){
                    System.out.println("Success");
                    message_client_lbl.setText("Connected");
                    
                   
                }else{
                    
                    System.out.println("err on connect");
                    message_client_lbl.setText("Connection Failed");
                }
            }
        });
        connect.setOnFailed(new EventHandler<WorkerStateEvent>() {

            @Override
            public void handle(WorkerStateEvent t) {
                
                System.out.println("Connecting Err");
            }
            
            
        });
            
        new Thread(connect).start();
        
        
    }
    
    @FXML
    private void handleTextFieldClient(ActionEvent event){
        sendMessage();
    }
    //
    //print message direct from label to main Text Area
    private void sendMessage(){
       //put the message on the main Text Field
       String text = text_field_client.getText();
       text_area_client.appendText("me: "+ text + "\n");
       //clear text field after send message
       text_field_client.clear();
       //TO DO HERE
       //clientModel.sendMessage(text);
       
       //this is a task...it should be send to a service in order to send messages in one thread
       //rather than create several threads per message =/
       clientModel.sendMessage(text);
    }
    
    
    private void receivedMessage(String receivedMessage){
        text_area_client.appendText(receivedMessage + "\n");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
       
      
}

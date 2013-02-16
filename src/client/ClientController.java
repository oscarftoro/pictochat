/*
 * 
 * Oscar Toro DATW12
 * 
 */
package client;

import model.ClientModel;
import java.net.URL;
import java.util.ResourceBundle;
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
        
        clientModel.setUrlAndPort(url, port);
        message_client_lbl.textProperty().bind(
            clientModel.messageProperty());
        clientModel.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

            @Override
            public void handle(WorkerStateEvent t) {
                System.out.println("Succees again");
            }
        });
        clientModel.start();
    }
    
    @FXML
    private void handleTextFieldClient(ActionEvent event){
        sendMessage();
    }
    
    /*
     * Send Messages to the server.
     * we have to have a method because
     * we want to call it by our selves
     * any time we press enter in the GUI Text Field
     */
    private void sendMessage(){
       //put the message on the main Text Field
       String text = text_field_client.getText();
//       text_area_client.appendText("me: "+ text + "\n");
       //clear text field after send message
       text_field_client.clear();
       
       //this is a task...it should be send to a service in order to send messages in one thread
       //rather than create several threads per message =/
       clientModel.sendMessage(text);
       AppendmessageReceived();
    }
    
    /*
     * Append a Message to the Main Text Field
     */
            
    private void AppendmessageReceived(){
        
        text_area_client.appendText(clientModel.getMessageIn() + "\n");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
            clientModel = new ClientModel();
        
    }
       
      
}

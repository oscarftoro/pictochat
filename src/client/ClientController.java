/*
 * Oscar Toro DATW12 
 */
package client;

import model.ClientModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author oscar felipe toro <oscar@groovenino.com>
 */
public class ClientController implements Initializable {
    
    @FXML TextField port_txt_field;
    @FXML TextField url_txt_field;
    @FXML Button connect_client_bttn;
    @FXML Label message_client_lbl;  //to show messages to the user
    @FXML TextArea text_area_client;
    @FXML TextField text_field_client;
    @FXML TextField nickName;
    
    ClientModel clientModel;
    StringProperty messageFromClient;
    
    //Canvas Elements
    @FXML private Canvas canvas;
    @FXML private GraphicsContext paint;
    double startX, startY;
    double endX, endY;
    @FXML private Label paintLabel;
    
  
    //event handler for the mouse in order to allow the user to draw...
    EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent mouseEvent) {
          
            if(mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED){
                
                startX = mouseEvent.getX();
                startY = mouseEvent.getY();

            }else if(mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED){
                endX = mouseEvent.getX();
                endY = mouseEvent.getY();
                paint.strokeLine(startX, startY, endX, endY);              
            }  
        }
     
    };
    
    /**
     * Initializes the controller class.
     */
    @FXML
    private void handleConnectButton(ActionEvent event){
        System.out.println("Open Client Button pressed");
        //get the text from text field (Gui)
        String url = url_txt_field.getText();
        //Same with port...
        int port = Integer.parseInt(port_txt_field.getText());
        //define port and url
        clientModel.setUrlAndPort(url, port);
        //BINDING the message label with the client model thread
        message_client_lbl.textProperty().bind(
            clientModel.messageProperty());
        //override event handler when the client model thread succeed 
        clientModel.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

            @Override
            public void handle(WorkerStateEvent t) {
                
                String s = (String) t.getSource().getValue();
                //print message in the main text field
                appendmessageReceived(s);
                //start this service again to rece
                clientModel.restart();//this generate the loop
                //probably just try to isloate the task of connect and loop
            }
        });
        
        clientModel.start();
    }
    @FXML
    private void handleDisconnectButton(ActionEvent event){
        
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
    private synchronized void sendMessage(){
       //get the message from the main Text Field
       String text = text_field_client.getText();
//       text_area_client.appendText("me: "+ text + "\n");
       //clear text field after send message
       text_field_client.clear();
       
       //this is a task...it should be send to a service in order to send messages in one thread
       //rather than create several threads per message? =/
      // clientModel.sendMessage(text);
       final Task<Void> sendMessage = clientModel.sendMessageTask(text);
       new Thread(sendMessage,"messageFromClientController").start();
       
    }
    
    /*
     * Append a Message from the client thread to the Main Text Field
     * 
     */       
    private void appendmessageReceived(String messageFromThread){
        text_area_client.appendText(messageFromThread+ "\n");
    }
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
        //initialize the ClientModel
        clientModel = new ClientModel();
        //Operations to draw
        paint = canvas.getGraphicsContext2D();
        paint.setLineWidth(1);
        paint.setStroke(Color.BLACK);
        canvas.setVisible(true);
        canvas.setOnMouseClicked(mouseHandler);
        canvas.setOnMouseDragged(mouseHandler);

        canvas.setOnMouseExited(mouseHandler);
        canvas.setOnMouseMoved(mouseHandler);
        canvas.setOnMousePressed(mouseHandler);
        canvas.setOnMouseReleased(mouseHandler);
        
    }    
}

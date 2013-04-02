/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datw12chat;

import java.io.IOException;
import java.net.Inet6Address;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javafx.scene.Scene;


/**
 *
 * @author oscar felipe toro DATW12 <oscar@groovenino.com>
 */
public class SampleController implements Initializable {
    
    
    @FXML
    Button open_server_bttn;
    @FXML
    Button open_client_bttn;
    @FXML
    Label ip_address_txt_field;
    Stage server;
    Stage client;
    String myIp ="";
    String myIpInt;
    @FXML
    private void handleServerButtonAction(ActionEvent event) throws IOException {
        lunchStage("/server/Server.fxml");
  }
    

    @FXML
    private void handleClientButtonAction(ActionEvent event) throws IOException {
        lunchStage("/client/client.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try{
            ip_address_txt_field.setText(getIP());
        }catch(Exception ex) {
            Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    //Create Window
    private void lunchStage(String fxml) throws IOException {
        server = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);
        server.setScene(scene);
        server.show();
    }
    private String getIP() throws IOException{
            myIp = Inet6Address.getLocalHost().getHostAddress();        
        return myIp;
    }

    
}

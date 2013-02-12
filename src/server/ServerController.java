/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
    ServerModel serverModel;
    String port;
    
    @FXML
    private void handleOpenPortButton(ActionEvent event){
        port = port_txt_field.getText();
        serverModel = new ServerModel();
        //get text from text field and sed it to the server Integer.parseInt(port_txt_field.getText())
        serverModel.openPort(Integer.parseInt(port));
        message_lbl.setText("connection established");
     
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}

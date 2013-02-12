/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import model.ClientModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    Button connect_bttn;
    ClientModel clientModel = null;
    /**
     * Initializes the controller class.
     */
    @FXML
    private void handleConnectButton(ActionEvent event){
        clientModel = new ClientModel();
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}

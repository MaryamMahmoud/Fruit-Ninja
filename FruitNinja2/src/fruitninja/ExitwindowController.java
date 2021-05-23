/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruitninja;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ExitwindowController implements Initializable {

    @FXML
    private Button MainMenuExit;
    @FXML
    private Button ExitGame;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void Mainmenu(ActionEvent event) throws IOException {
        Parent fruitninja = FXMLLoader.load(getClass().getResource("fruitninjaamainmenu.fxml"));
        Scene fruitninja_scene = new Scene(fruitninja);
        Stage App_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        App_stage.setScene(fruitninja_scene);
        App_stage.show();
    }

    @FXML
    private void Exit(MouseEvent event) {
        Platform.exit();
    }
    
}

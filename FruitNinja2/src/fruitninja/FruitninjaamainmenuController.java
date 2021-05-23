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
import javafx.stage.Stage;

public class FruitninjaamainmenuController implements Initializable {

    @FXML
    private Button playClassicBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private Button easyBtn;
    @FXML
    private Button mediumBtn;
    @FXML
    private Button hardBtn;
    @FXML
    private Button playArcadeBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    int flag = 0;

    public void playClassic() {
        if (flag == 0) {
            easyBtn.setDisable(false);
            mediumBtn.setDisable(false);
            hardBtn.setDisable(false);
            flag++;
        } else {
            easyBtn.setDisable(true);
            mediumBtn.setDisable(true);
            hardBtn.setDisable(true);
            flag = 0;
        }
    }

    public void exit() {
        Platform.exit();
    }

    public void playEasy(ActionEvent event) throws IOException {
        Parent fruitninja = FXMLLoader.load(getClass().getResource("EasyGame.fxml"));
        Scene fruitninja_scene = new Scene(fruitninja);
        Stage App_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        App_stage.setScene(fruitninja_scene);
        App_stage.show();
    }

    public void playMedium(ActionEvent event) throws IOException {
        Parent fruitninja = FXMLLoader.load(getClass().getResource("MediumGame.fxml"));
        Scene fruitninja_scene = new Scene(fruitninja);
        Stage App_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        App_stage.setScene(fruitninja_scene);
        App_stage.show();
    }

    public void playHard(ActionEvent event) throws IOException {
        Parent fruitninja = FXMLLoader.load(getClass().getResource("HardGame.fxml"));
        Scene fruitninja_scene = new Scene(fruitninja);
        Stage App_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        App_stage.setScene(fruitninja_scene);
        App_stage.show();
    }

    public void playArcade(ActionEvent event) throws IOException {
        Parent fruitninja = FXMLLoader.load(getClass().getResource("ArcadeMood.fxml"));
        Scene fruitninja_scene = new Scene(fruitninja);
        Stage App_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        App_stage.setScene(fruitninja_scene);
        App_stage.show();
    }
}

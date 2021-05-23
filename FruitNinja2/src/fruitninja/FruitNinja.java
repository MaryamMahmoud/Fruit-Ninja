package fruitninja;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class FruitNinja extends Application {
    
    String path = "C:\\Users\\HEI\\Desktop\\project\\new\\final\\FruitNinjaa (1)\\FruitNinjaa\\FruitNinja2\\themeSong.mp3";
    Media media = new Media(new File(path).toURI().toString());  
    MediaPlayer mediaPlayer = new MediaPlayer(media);  

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fruitninjaamainmenu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Fruit Ninja!");
        mediaPlayer.setAutoPlay(true);  
        stage.show();
        stage.setResizable(false);
    }
    public static void main(String[] args) {
        launch(args);
    }
}

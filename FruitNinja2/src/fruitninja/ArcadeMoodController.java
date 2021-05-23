package fruitninja;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ArcadeMoodController implements Initializable {

    IGameStrategy strategy = new HardLevel();
    GameObjectFactory factory = new GameObjectFactory();
    IGameObject fruit = factory.creatObject("fruits");
    IGameObject specialfruit = factory.creatObject("special fruits");
    IGameObject dangerousbomb = factory.creatObject("dangerous bomb");
    String path = "C:\\Users\\HEI\\Desktop\\project\\new\\final\\FruitNinjaa (1)\\FruitNinjaa\\FruitNinja2\\CutSound.wav";
    Media media = new Media(new File(path).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    private PlayerSingleton player = PlayerSingleton.getPlayerInstance();
    private int score = 0;
    
    @FXML
    private Button mainmenuBtn;
    @FXML
    private Button startBtn;
    @FXML
    private Label HighScoreLbl;
    @FXML
    private Button saveBtn;
    @FXML
    private Label secondsLabel;
    @FXML
    private Label scoreLabel;
    @FXML
    private ImageView watermelon1;
    @FXML
    private ImageView pineapple1;
    @FXML
    private ImageView lemon1;
    @FXML
    private ImageView cherry1;
    @FXML
    private ImageView Avocato1;
    @FXML
    private ImageView dbomb1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    int seconds = 60;
    Timer myTimer = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            seconds--;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    if (seconds % strategy.durationTimeOfSpecialFruits() == 0) {
                        lemon1.setVisible(true);
                        Avocato1.setVisible(true);
                    }
                    if (seconds % strategy.durationTimeOfFruits() == 0) {
                        watermelon1.setVisible(true);
                        pineapple1.setVisible(true);
                        cherry1.setVisible(true);
                    }
                    if (seconds % strategy.durationTimeOfDangerousBombs() == 0) {
                        dbomb1.setVisible(true);
                    }
                    secondsLabel.setText("Timer: " + Integer.toString(seconds));
                    if (seconds == 0) {
                        EndGame();
                        player.updateScore(score);
                    }
                }
            });
        }
    };

    @FXML
    void Start(ActionEvent event) throws IOException {
        myTimer.scheduleAtFixedRate(task, 1000, 1000);
        HighScoreLbl.setText("High score: " + String.valueOf(Save.reader()));
        scoreLabel.setText("Score: "+score);
        mpvepineapple();
        movewatermelon();
        movelemon();
        moveavocato();
        movecherry();
        movedbomb();
        resetdbombs();

    }

    @FXML
    public void Mainmenu(ActionEvent event) throws IOException {
        Parent fruitninja = FXMLLoader.load(getClass().getResource("fruitninjaamainmenu.fxml"));
        Scene fruitninja_scene = new Scene(fruitninja);
        Stage App_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        App_stage.setScene(fruitninja_scene);
        App_stage.show();
    }

    public void movewatermelon() {
        TranslateTransition watermelontrans = new TranslateTransition();
        watermelontrans.setNode(watermelon1);
        watermelontrans.setByX(0);
        watermelontrans.setByY(700);
        watermelontrans.setDuration(Duration.seconds(strategy.durationTimeOfFruits()));
        ParallelTransition p = new ParallelTransition(watermelontrans);
        p.setCycleCount(20);
        p.play();
    }

    public void mpvepineapple() {
        TranslateTransition appletrans = new TranslateTransition();
        appletrans.setNode(pineapple1);
        appletrans.setByX(0);
        appletrans.setByY(700);
        appletrans.setDuration(Duration.seconds(strategy.durationTimeOfFruits()));
        ParallelTransition p = new ParallelTransition(appletrans);
        p.setCycleCount(50);
        p.play();
    }

    public void movelemon() {
        TranslateTransition lemon = new TranslateTransition();
        lemon.setNode(lemon1);
        lemon.setByX(0);
        lemon.setByY(700);
        lemon.setDuration(Duration.seconds(strategy.durationTimeOfSpecialFruits()));
        ParallelTransition p = new ParallelTransition(lemon);
        p.setCycleCount(50);
        p.play();
    }

    public void moveavocato() {
        TranslateTransition avocato = new TranslateTransition();
        avocato.setNode(Avocato1);
        avocato.setByX(0);
        avocato.setByY(700);
        avocato.setDuration(Duration.seconds(strategy.durationTimeOfSpecialFruits()));
        ParallelTransition p = new ParallelTransition(avocato);
        p.setCycleCount(50);
        p.play();
    }

    public void movecherry() {
        TranslateTransition cherrytrans = new TranslateTransition();
        cherrytrans.setNode(cherry1);
        cherrytrans.setByX(0);
        cherrytrans.setByY(700);
        cherrytrans.setDuration(Duration.seconds(strategy.durationTimeOfFruits()));
        ParallelTransition p = new ParallelTransition(cherrytrans);
        p.setCycleCount(50);
        p.play();
    }

    public void movedbomb() {
        TranslateTransition dbomb2 = new TranslateTransition();
        dbomb2.setNode(dbomb1);
        dbomb2.setByX(0);
        dbomb2.setByY(700);
        dbomb2.setDuration(Duration.seconds(strategy.durationTimeOfDangerousBombs()));
        ParallelTransition p = new ParallelTransition(dbomb2);
        p.setCycleCount(6);
        p.play();
    }

    @FXML
    public void slicepineapple() {
        score+=fruit.getScore();
        scoreLabel.setText("Score: "+score);
        player.updateScore(score);
        HighScoreLbl.setText("High score: " + Integer.toString(player.getScore()));
        pineapple1.setVisible(false);
    }

    @FXML
    public void slicewatermelon() {
        score+=fruit.getScore();
        scoreLabel.setText("Score: "+score);
        player.updateScore(score);
        HighScoreLbl.setText("High score: " + Integer.toString(player.getScore()));
        watermelon1.setVisible(false);
    }

    @FXML
    public void slicecherry() {
        score+=fruit.getScore();
        scoreLabel.setText("Score: "+score);
        player.updateScore(score);
        HighScoreLbl.setText("High score: " + Integer.toString(player.getScore()));
        cherry1.setVisible(false);
    }

    @FXML
    public void sliceavocato() {
        score+=specialfruit.getScore();
        scoreLabel.setText("Score: "+score);
        player.updateScore(score);
        HighScoreLbl.setText("High score: " + Integer.toString(player.getScore()));
        Avocato1.setVisible(false);
    }

    @FXML
    public void slicelemon() {
        score+=specialfruit.getScore();
        scoreLabel.setText("Score: "+score);
        player.updateScore(score);
        HighScoreLbl.setText("High score: " + Integer.toString(player.getScore()));
        lemon1.setVisible(false);
    }

    @FXML
    public void activatedangerbomb() { // set on mouseclicked of dangerous bombs
        score+=dangerousbomb.getScore(); 
        scoreLabel.setText("Score: "+score);
        player.updateScore(score);
        HighScoreLbl.setText("High score: " + Integer.toString(player.getScore()));
        dbomb1.setVisible(false); 
    }

    public void EndGame() {
        Platform.exit();

    }

    public void resetdbombs() {
        Timeline time = new Timeline(new KeyFrame(Duration.seconds(15)));
        dbomb1.setVisible(true);
        time.setCycleCount(6);
        time.play();
    }

    @FXML
    private void save(ActionEvent event) throws IOException {
                Save.Writer(player.getScore());
        Parent fruitninja = FXMLLoader.load(getClass().getResource("fruitninjaamainmenu.fxml"));
        Scene fruitninja_scene = new Scene(fruitninja);
        Stage App_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        App_stage.setScene(fruitninja_scene);
        App_stage.show();

    }

}

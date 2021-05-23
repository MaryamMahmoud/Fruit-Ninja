package fruitninja;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.Animation;
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

public class HardGameController implements Initializable {

    IGameStrategy strategy = new HardLevel();
    GameObjectFactory factory = new GameObjectFactory();
    IGameObject fruit = factory.creatObject("fruits");
    IGameObject specialfruit = factory.creatObject("special fruits");
    IGameObject fatalbomb = factory.creatObject("fatal bomb");
    IGameObject dangerousbomb = factory.creatObject("dangerous bomb");
    String path = "C:\\Users\\HEI\\Desktop\\project\\new\\final\\FruitNinjaa (1)\\FruitNinjaa\\FruitNinja2\\CutSound.wav";
    Media media = new Media(new File(path).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    private PlayerSingleton player = PlayerSingleton.getPlayerInstance();
    private int score = 0;
    private int flagapple = 0, flagcherry = 0, flagwatermelon = 0;
    
    @FXML
    private Button mainmenuBtn;
    @FXML
    private Button startBtn;
    @FXML
    private Label secondsLabel;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label highScoreLabel;
    @FXML
    private ImageView Watermelon;
    @FXML
    private ImageView Lemon;
    @FXML
    private ImageView Cherry;
    @FXML
    private ImageView Avocato;
    @FXML
    private ImageView apple1;
    @FXML
    private ImageView fbomb2;
    @FXML
    private ImageView dbomb2;
    @FXML
    private Label livesLabel;
    @FXML
    private Button saveBtn;
    @FXML
    private ImageView Cherry1;
    @FXML
    private ImageView fbomb21;

    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }


    int seconds;
    int numberoflives = 3;
    Timer myTimer = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            seconds++;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    if (seconds % strategy.durationTimeOfSpecialFruits() == 0) {
                        Lemon.setVisible(true);
                        Avocato.setVisible(true); 
                    }
                    if (seconds % strategy.durationTimeOfFruits() == 0) {
                        Watermelon.setVisible(true);
                        apple1.setVisible(true);
                        Cherry.setVisible(true);
                        if(flagapple == 0){
                            numberoflives--;
                            livesLabel.setText("Lives: " + numberoflives);
                            if (numberoflives == 0) {
                             EndGame();
                            }
                        }
                        if(flagwatermelon == 0){
                            numberoflives--;
                            livesLabel.setText("Lives: " + numberoflives);;
                            if (numberoflives == 0) {
                                EndGame();
                            }
                        }
                        if(flagcherry == 0){
                            numberoflives--;
                            livesLabel.setText("Lives: " + numberoflives);
                            if (numberoflives == 0) {
                                EndGame();
                            }
                        }
                        flagapple = 0;
                        flagcherry = 0;
                        flagwatermelon = 0;
                    }
                    if (seconds % strategy.durationTimeOfDangerousBombs() == 0) {
                        dbomb2.setVisible(true);
                    }
                    secondsLabel.setText(" " + Integer.toString(seconds));
                }
            });
        }
    };

    @FXML
    void Start(ActionEvent event) throws IOException {
        myTimer.scheduleAtFixedRate(task, 1000, 1000);
        scoreLabel.setText("Score: "+score);
        highScoreLabel.setText("High score: " + String.valueOf(Save.reader()));
        moveapple();
        movewatermelon();
        movelemon();
        moveavocato();
        movecherry();
        movedbomb();
        movebomb();
        resetfbombs();
        resetdbombs();
    }

    @FXML
    public void Mainmenu(ActionEvent event) throws IOException {
                myTimer.cancel();

        Parent fruitninja = FXMLLoader.load(getClass().getResource("fruitninjaamainmenu.fxml"));
        Scene fruitninja_scene = new Scene(fruitninja);
        Stage App_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        App_stage.setScene(fruitninja_scene);
        App_stage.show();
    }

    public void movewatermelon() {
        TranslateTransition watermelontrans = new TranslateTransition();
        watermelontrans.setNode(Watermelon);
        watermelontrans.setByX(0);
        watermelontrans.setByY(750);
        watermelontrans.setDuration(Duration.seconds(strategy.durationTimeOfFruits()));
        ParallelTransition p = new ParallelTransition(watermelontrans);
        p.setCycleCount(20);
        p.play();
    }

    public void moveapple() {
        TranslateTransition appletrans = new TranslateTransition();
        appletrans.setNode(apple1);
        appletrans.setByX(0);
        appletrans.setByY(750);
        appletrans.setDuration(Duration.seconds(strategy.durationTimeOfFruits()));
        ParallelTransition p = new ParallelTransition(appletrans);
        p.setCycleCount(50);
        p.play();
    }

    public void movelemon() {
        TranslateTransition lemon = new TranslateTransition();
        lemon.setNode(Lemon);
        lemon.setByX(0);
        lemon.setByY(850);
        lemon.setDuration(Duration.seconds(strategy.durationTimeOfSpecialFruits()));
        ParallelTransition p = new ParallelTransition(lemon);
        p.setCycleCount(50);
        p.play();
    }

    public void moveavocato() {
        TranslateTransition avocato = new TranslateTransition();
        avocato.setNode(Avocato);
        avocato.setByX(0);
        avocato.setByY(850);
        avocato.setDuration(Duration.seconds(strategy.durationTimeOfSpecialFruits()));
        ParallelTransition p = new ParallelTransition(avocato);
        p.setCycleCount(50);
        p.play();
    }

    public void movecherry() {
        TranslateTransition cherrytrans = new TranslateTransition();
        cherrytrans.setNode(Cherry);
        cherrytrans.setByX(0);
        cherrytrans.setByY(800);
        cherrytrans.setDuration(Duration.seconds(strategy.durationTimeOfFruits()));
        ParallelTransition p = new ParallelTransition(cherrytrans);
        p.setCycleCount(50);
        p.play();
    }

    public void movebomb() {
        TranslateTransition fbomb1 = new TranslateTransition();
        fbomb1.setNode(fbomb2);
        fbomb1.setByX(0);
        fbomb1.setByY(800);
        fbomb1.setDuration(Duration.seconds(strategy.durationTimeOfFatalBombs()));
        ParallelTransition p = new ParallelTransition(fbomb1);
        p.setCycleCount(6);
        p.play();
    }

    public void movedbomb() {
        TranslateTransition dbomb1 = new TranslateTransition();
        dbomb1.setNode(dbomb2);
        dbomb1.setByX(0);
        dbomb1.setByY(800);
        dbomb1.setDuration(Duration.seconds(strategy.durationTimeOfDangerousBombs()));
        ParallelTransition p = new ParallelTransition(dbomb1);
        p.setCycleCount(6);
        p.play();
    }

    @FXML
    public void sliceapple() {
        flagapple = 1;
        score+=fruit.getScore();
        scoreLabel.setText("Score: "+score);
        player.updateScore(score);
        highScoreLabel.setText("High score: " + Integer.toString(player.getScore()));
        apple1.setVisible(false);
        mediaPlayer.setAutoPlay(true);
    }

    @FXML
    public void slicewatermelon() {
        flagwatermelon = 1;
        score+=fruit.getScore();
        scoreLabel.setText("Score: "+score);
        player.updateScore(score);
        highScoreLabel.setText("High score: " + Integer.toString(player.getScore()));
        Watermelon.setVisible(false);
        mediaPlayer.setAutoPlay(true);
    }

    @FXML
    public void slicecherry() {
        flagcherry = 1;
        score+=fruit.getScore();
        scoreLabel.setText("Score: "+score);
        player.updateScore(score);
        highScoreLabel.setText("High score: " + Integer.toString(player.getScore()));
        Cherry.setVisible(false);
        mediaPlayer.setAutoPlay(true);
    }

    @FXML
    public void sliceavocato() {
        score+=specialfruit.getScore();
        scoreLabel.setText("Score: "+score);
        player.updateScore(score);
        highScoreLabel.setText("High score: " + Integer.toString(player.getScore()));
        Avocato.setVisible(false);
        mediaPlayer.setAutoPlay(true);
    }

    @FXML
    public void slicelemon() {        
        score+=specialfruit.getScore();
        scoreLabel.setText("Score: "+score);
        player.updateScore(score);
        highScoreLabel.setText("High score: " + Integer.toString(player.getScore()));
        Lemon.setVisible(false);
        mediaPlayer.setAutoPlay(true);
    }

    boolean activatedangerbomb() { // set on mouseclicked of dangerous bombs
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("The bomb has exploded!! you lost a life");
//		alert.show();
        if (numberoflives == 0) {
            EndGame();
        }
        return true;
    }

    @FXML
    public void EndGame() {
        Platform.exit();

    }

    @FXML
    public void loselife() {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(" You Lost a Life!!");
            alert.show();
            numberoflives--;
            dbomb2.setVisible(false);
            livesLabel.setText("Lives: " + numberoflives);
            if (numberoflives == 0) {
                EndGame();
            }

        
    }
    public void resetfbombs() {
        Timeline time = new Timeline(new KeyFrame(Duration.seconds(20)));
        fbomb2.setVisible(true);
        time.setCycleCount(6);
        time.play();
    }

    public void resetdbombs() {
        Timeline time = new Timeline(new KeyFrame(Duration.seconds(15)));
        dbomb2.setVisible(true);
        time.setCycleCount(6);
        time.play();
    }

    @FXML
    private void save(ActionEvent event) throws IOException {
                myTimer.cancel();

                Save.Writer(player.getScore());
        Parent fruitninja = FXMLLoader.load(getClass().getResource("fruitninjaamainmenu.fxml"));
        Scene fruitninja_scene = new Scene(fruitninja);
        Stage App_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        App_stage.setScene(fruitninja_scene);
        App_stage.show();

    }

}

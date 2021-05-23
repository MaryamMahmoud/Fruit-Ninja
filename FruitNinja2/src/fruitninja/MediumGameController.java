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

public class MediumGameController implements Initializable {

    IGameStrategy strategy = new MediumLevel();
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
    private int flagapple=0,flagpomegranate=0,flagwatermelon=0;
    
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
    private Label livesLabel;
    @FXML
    private ImageView Watermelon;
    @FXML
    private ImageView Lemon;
    @FXML
    private ImageView Pomergranate;
    @FXML
    private ImageView apple1;
    @FXML
    private ImageView fbomb;
    @FXML
    private ImageView dbomb;
    @FXML
    private Button saveBtn;
    @FXML
    private ImageView dbomb1;

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
                    }
                    if (seconds % strategy.durationTimeOfFruits() == 0) {
                        Watermelon.setVisible(true);
                        apple1.setVisible(true);
                        Pomergranate.setVisible(true);
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
                        if(flagpomegranate == 0){
                            numberoflives--;
                            livesLabel.setText("Lives: " + numberoflives);
                            if (numberoflives == 0) {
                                EndGame();
                            }
                        }
                        flagapple=0;
                        flagpomegranate=0;
                        flagwatermelon=0;
                    }
                    if (seconds % strategy.durationTimeOfDangerousBombs() == 0) {
                        dbomb.setVisible(true);
                    }
                    scoreLabel.setText("Score: "+score);
                    highScoreLabel.setText("High score: " + Integer.toString(player.getScore()));
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
        movewatermelon();
        moveapple();
        movelemon();
        movepomergranate();
        movebomb();
        movedbomb();
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
        watermelontrans.setByY(700);
        watermelontrans.setDuration(Duration.seconds(strategy.durationTimeOfFruits()));
        ParallelTransition p = new ParallelTransition(watermelontrans);
        p.setCycleCount(50);
        p.play();
    }

    public void moveapple() {
        TranslateTransition appletrans = new TranslateTransition();
        appletrans.setNode(apple1);
        appletrans.setByX(0);
        appletrans.setByY(700);
        appletrans.setDuration(Duration.seconds(strategy.durationTimeOfFruits()));
        ParallelTransition p = new ParallelTransition(appletrans);
        p.setCycleCount(50);
        p.play();
    }

    public void movelemon() {
        TranslateTransition lemon = new TranslateTransition();
        lemon.setNode(Lemon);
        lemon.setByX(0);
        lemon.setByY(700);
        lemon.setDuration(Duration.seconds(strategy.durationTimeOfSpecialFruits()));
        ParallelTransition p = new ParallelTransition(lemon);
        p.setCycleCount(50);
        p.play();
    }

    public void movepomergranate() {
        TranslateTransition pomergranate = new TranslateTransition();
        pomergranate.setNode(Pomergranate);
        pomergranate.setByX(0);
        pomergranate.setByY(800);
        pomergranate.setDuration(Duration.seconds(strategy.durationTimeOfFruits()));
        ParallelTransition p = new ParallelTransition(pomergranate);
        p.setCycleCount(50);
        p.play();
    }

    public void movebomb() {
        TranslateTransition fbomb1 = new TranslateTransition();
        fbomb1.setNode(fbomb);
        fbomb1.setByX(0);
        fbomb1.setByY(800);
        fbomb1.setDuration(Duration.seconds(strategy.durationTimeOfFatalBombs()));
        ParallelTransition p = new ParallelTransition(fbomb1);
        p.setCycleCount(5);
        p.play();
    }

    public void movedbomb() {
        TranslateTransition dbomb1 = new TranslateTransition();
        dbomb1.setNode(dbomb);
        dbomb1.setByX(0);
        dbomb1.setByY(700);
        dbomb1.setDuration(Duration.seconds(strategy.durationTimeOfDangerousBombs()));
        ParallelTransition p = new ParallelTransition(dbomb1);
        p.setCycleCount(5);
        p.play();
    }

    @FXML
    public void slicewatermelon() {
        flagwatermelon = 1;
        score+=fruit.getScore();
        scoreLabel.setText("Score: "+score);
        player.updateScore(score);
        highScoreLabel.setText("High score: " + Integer.toString(player.getScore()));
        Watermelon.setVisible(false);
    }

    @FXML
    public void sliceapple() {
        flagapple = 1;
        score+=fruit.getScore();
        scoreLabel.setText("Score: "+score);
        player.updateScore(score);
        highScoreLabel.setText("High score: " + Integer.toString(player.getScore()));
        apple1.setVisible(false);
    }

    @FXML
    public void slicepomer() {
        flagpomegranate = 1;
        score+=fruit.getScore();
        scoreLabel.setText("Score: "+score);
        player.updateScore(score);
        highScoreLabel.setText("High score: " + Integer.toString(player.getScore()));
        Pomergranate.setVisible(false);
    }

    @FXML
    public void slicelemon() {
        score+=specialfruit.getScore();
        scoreLabel.setText("Score: "+score);
        player.updateScore(score);
        highScoreLabel.setText("High score: " + Integer.toString(player.getScore()));
        Lemon.setVisible(false);
    }

    boolean activatedangerbomb() { // set on mouseclicked of dangerous bombs
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("The bomb has exploded!! you lost a life");
        //	alert.show();
        dbomb.setVisible(false);
        //	numberoflives--;
        livesLabel.setText("Lives: " + numberoflives);
        if (numberoflives == 0) {
            EndGame();
        }
        return true;
    }

    /*
	 * boolean activatefatalbomb() {//set on mouseclicked of fatalbomb Alert alert
	 * =new Alert(Alert.AlertType.ERROR); alert.setHeaderText(null);
	 * alert.setContentText(" fatalbomb has been exploded!! Gameover");
	 * alert.show(); return true; }
     */
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
        dbomb.setVisible(false);
        numberoflives--;
        livesLabel.setText("Lives: " + numberoflives);
        if (numberoflives == 0) {
            EndGame();
        }
    }

    public void resetfbombs() {
        Timeline time = new Timeline(new KeyFrame(Duration.seconds(20)));
        fbomb.setVisible(true);
        time.setCycleCount(3);
        time.play();
    }

    public void resetdbombs() {
        Timeline time = new Timeline(new KeyFrame(Duration.seconds(15)));
        dbomb.setVisible(true);
        time.setCycleCount(5);
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

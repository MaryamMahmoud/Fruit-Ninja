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
import javafx.event.EventHandler;
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
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class EasyGameController implements Initializable {

    IGameStrategy strategy = new EasyLevel();
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
    private int flagapple = 0, flagcherry = 0, flagpineapple = 0;
    
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
    private ImageView Cherry2;
    @FXML
    private ImageView pineapple2;
    @FXML
    private ImageView apple2;
    @FXML
    private ImageView fatalbomb1;
    @FXML
    private ImageView dbomb1;
    @FXML
    private Label livesLabel;
    @FXML
    private Button saveBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
                    if (seconds % strategy.durationTimeOfFruits() == 0) {
                        Cherry2.setVisible(true);
                        apple2.setVisible(true);
                        pineapple2.setVisible(true);
                        if(flagapple == 0){
                            numberoflives--;
                            livesLabel.setText("Lives: " + numberoflives);
                            if (numberoflives == 0) {
                             EndGame();
                            }
                        }
                        if(flagpineapple == 0){
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
                        flagapple=0;
                        flagcherry=0;
                        flagpineapple=0;
                    }
                    if (seconds % strategy.durationTimeOfDangerousBombs() == 0) {
                        dbomb1.setVisible(true);
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
        movepineapple();
        moveapple();
        movecherry();
        movebomb();
        movedbomb();
        resetfbombs();
        resetdbombs();
    }

    public void movepineapple() {
        TranslateTransition pine = new TranslateTransition();
        pine.setNode(pineapple2);
        pine.setByX(0);
        pine.setByY(700);
        pine.setDuration(Duration.seconds(strategy.durationTimeOfFruits()));
        ParallelTransition p = new ParallelTransition(pine);
        p.setCycleCount(40);
        pineapple2.setVisible(true);
        p.play();
    }

    public void moveapple() {
        TranslateTransition appletrans = new TranslateTransition();
        appletrans.setNode(apple2);
        appletrans.setByX(0);
        appletrans.setByY(700);
        appletrans.setDuration(Duration.seconds(strategy.durationTimeOfFruits()));
        ParallelTransition p = new ParallelTransition(appletrans);
        p.setCycleCount(40);
        apple2.setVisible(true);
        p.play();
    }

    public void movecherry() {
        TranslateTransition cherrytrans = new TranslateTransition();
        cherrytrans.setNode(Cherry2);
        cherrytrans.setByX(0);
        cherrytrans.setByY(700);
        cherrytrans.setDuration(Duration.seconds(strategy.durationTimeOfFruits()));
        ParallelTransition p = new ParallelTransition(cherrytrans);
        p.setCycleCount(40);
        Cherry2.setVisible(true);
        p.play();
    }

    public void movebomb() {
        TranslateTransition fbomb = new TranslateTransition();
        fbomb.setNode(fatalbomb1);
        fbomb.setByX(0);
        fbomb.setByY(700);
        fbomb.setDuration(Duration.seconds(strategy.durationTimeOfFatalBombs()));
        ParallelTransition p = new ParallelTransition(fbomb);
        p.setCycleCount(3);
        p.play();
    }

    public void movedbomb() {
        TranslateTransition dbomb = new TranslateTransition();
        dbomb.setNode(dbomb1);
        dbomb.setByX(0);
        dbomb.setByY(700);
        dbomb.setDuration(Duration.seconds(strategy.durationTimeOfDangerousBombs()));
        ParallelTransition p = new ParallelTransition(dbomb);
        p.setCycleCount(4);
        p.play();
    }

    @FXML
    public void slicepineapple() {
        flagpineapple = 1; 
        score+=fruit.getScore();
        scoreLabel.setText("Score: "+score);
        player.updateScore(score);
        highScoreLabel.setText("High score: " + Integer.toString(player.getScore()));
        pineapple2.setVisible(false);
        mediaPlayer.setAutoPlay(true);
    }

    @FXML
    public void slicecherry() {
        flagcherry = 1;
        score+=fruit.getScore();
        scoreLabel.setText("Score: "+score);
        player.updateScore(score);
        highScoreLabel.setText("High score: " + Integer.toString(player.getScore()));
        Cherry2.setVisible(false);
        mediaPlayer.setAutoPlay(true);
    }

    @FXML
    public void sliceapple() {
        flagapple = 1;
        score+=fruit.getScore();
        scoreLabel.setText("Score: "+score);
        player.updateScore(score);
        highScoreLabel.setText("High score: " + Integer.toString(player.getScore()));
        apple2.setVisible(false);
        mediaPlayer.setAutoPlay(true);
    }

    public boolean activatedangerbomb() throws IOException { //set on mouseclicked of dangerous bombs
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("The bomb has exploded!! you lost a life");
        //    alert.show();
        dbomb1.setVisible(false);
        //    numberoflives--;
        livesLabel.setText("Lives: " + numberoflives);
        if (numberoflives == 0) {
            EndGame(); // TODO Auto-generated catch block
        }
        return true;
    }

    /*   public void EndGame() throws IOException {
       // Platform.exit();
    	Stage stage = new Stage();
       exit(stage);
    }      
    public void exit(Stage stage) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("exitwindow.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(null);
  //      mediaPlayer.setAutoPlay(true);  
        stage.show();
        stage.setResizable(false);
    }                 */
    @FXML
    public void EndGame() {
        Platform.exit();
    }

    @FXML
    public void loselife() throws IOException {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(" You Lost a Life!!");
        alert.show();
        dbomb1.setVisible(false);
        numberoflives--;
        livesLabel.setText("Lives: " + numberoflives);
        if (numberoflives == 0) {
            EndGame();
        }

    }

    public void resetfbombs() {
        Timeline time = new Timeline(new KeyFrame(Duration.seconds(15)));
        fatalbomb1.setVisible(true);
        time.setCycleCount(3);
        time.play();
    }

    public void resetdbombs() {
        Timeline time = new Timeline(new KeyFrame(Duration.seconds(10)));
        dbomb1.setVisible(true);
        time.setCycleCount(4);
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

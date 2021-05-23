package fruitninja;

import static java.lang.Double.max;

public class PlayerSingleton{
    
    private int score;
    private static PlayerSingleton instance = new PlayerSingleton();
    
    public static PlayerSingleton getPlayerInstance(){
        return instance; 
    }

    public int getScore(){
        return score;
    }
    
    public void updateScore(int currentScore) {  
        score = (int) max(score,currentScore); 
    }
}

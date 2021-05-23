package fruitninja;

public class EasyLevel implements IGameStrategy {
    
    @Override
    public int durationTimeOfFatalBombs() {
        return 13; 
    }

    @Override
    public int durationTimeOfDangerousBombs() {
        return 12;
    }

    @Override
    public int durationTimeOfFruits() {
        return 10; 
    }

    @Override
    public int durationTimeOfSpecialFruits() {
        return 9;
    }
  
}

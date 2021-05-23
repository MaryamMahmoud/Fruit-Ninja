package fruitninja;

public class HardLevel implements IGameStrategy {

    @Override
    public int durationTimeOfFatalBombs() {
        return 9;
    }

    @Override
    public int durationTimeOfDangerousBombs() {
        return 8;
    }

    @Override
    public int durationTimeOfFruits() {
        return 6;
    }

    @Override
    public int durationTimeOfSpecialFruits() {
        return 5;
    }

}

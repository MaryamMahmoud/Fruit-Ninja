package fruitninja;

public class MediumLevel implements IGameStrategy {

    @Override
    public int durationTimeOfFatalBombs() {
        return 11;
    }

    @Override
    public int durationTimeOfDangerousBombs() {
        return 10;
    }

    @Override
    public int durationTimeOfFruits() {
        return 8;
    }

    @Override
    public int durationTimeOfSpecialFruits() {
        return 7;
    }

}

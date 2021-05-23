package fruitninja;

public class GameObjectFactory {
    public IGameObject creatObject(String objectName){   
        IGameObject object = null;
        if(objectName.equalsIgnoreCase("fruits")){
            return new Fruits();
        }
        else if(objectName.equalsIgnoreCase("special fruits")){
            return new SpecialFruits();
        }
        else if(objectName.equalsIgnoreCase("fatal bomb")){
            return new FatalBomb();
        }
        else if(objectName.equalsIgnoreCase("dangerous bomb")){
            return new DangerousBomb();
        }
        else 
            return null;
    }
}

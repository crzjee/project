import java.util.Random;

public enum playerMove {
    o,
    x;
    public static playerMove getFirstMove(){
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}

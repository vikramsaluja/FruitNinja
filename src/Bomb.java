import java.awt.*;

public class Bomb extends Item{
    private boolean isSliced;

    public Bomb(int x, int y, double velocity, boolean isBomb, String fileName, GameView window) {
        super(x, y, velocity, isBomb, fileName, window);
        this.isSliced = false;
    }

}

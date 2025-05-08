public class Bomb extends Item{
    private boolean isSliced;
    private boolean bomb;

    public Bomb(int x,int y, int difficulty,  boolean isBomb, String fileName, GameView window) {
        // Use Item's constructor
        super(x,y, difficulty, isBomb, fileName, window);
        // Set isSliced to false
        this.isSliced = false;
        this.bomb = true;
    }

    public boolean isBomb(){
        // Return true that the item is a bomb
        return true;
    }

}

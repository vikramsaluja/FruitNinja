public class Bomb extends Item{
    private boolean isSliced;
    private boolean bomb;

    public Bomb(int x,int y, int difficulty,  boolean isBomb, String fileName, GameView window) {
        super(x,y, difficulty, isBomb, fileName, window);
        this.isSliced = false;
        this.bomb = true;
    }

    public boolean isBomb(){
        return true;
    }

}

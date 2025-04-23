import javax.swing.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

// Vikram Saluja Fruit Ninja Game

public class Game implements MouseListener, MouseMotionListener, ActionListener {
    private Player player;
    private int state;
    private boolean gameOver;
    private ArrayList<Item> items;
    private GameView window;
    private int difficulty;
    private int velocity;
    private int round;

    public Game() {
        this.state = 0;
        this.items = new ArrayList<Item>();
        this.window = new GameView(this);
        gameOver = false;
        this.difficulty = 0;
        this.round = 0;
        this.velocity = this.difficulty + 10;

        this.player = new Player();


        window.repaint();

        this.window.addMouseListener(this);
        this.window.addMouseMotionListener(this);

        Timer clock = new Timer(110, this);
        clock.start();

    }

    public void actionPerformed(ActionEvent e) {
        // If game is still running
        if(this.state == 2 && !gameOver){
            for(int i = 0; i < items.size(); i++){
                Item temp = items.get(i);
                // Move every item
                temp.move();
                // If item is off the screen remove it from list
                if(offScreen(temp)){
                    // If the item is not a bomb then a live is a lost
                    if(!temp.isBomb()) {
                        player.liveLost();
                    }
                    // Remove off-screen items from list
                    items.remove(i);
                    i--;
                }
            }
        }
        // Run new waves when there are no more items
        if(items.isEmpty() || (!containsBomb() && this.round > 3)){
            this.difficulty++;
            newWave();
        }
        if(player.getNumLives() == 0){
            gameOver = true;
            this.state = 3;
        }
        window.repaint();

    }

    public Player getPlayer(){
        return this.player;
    }

    public int getState(){
        return this.state;
    }

    public ArrayList<Item> getItems(){
        return this.items;
    }

    public int getDifficulty(){
        return this.difficulty;
    }

    public boolean offScreen(Item item){
        if (item.getY() <= 0) {
            return true;
        }
        return false;
    }

    public boolean GameOver(){
        if(player.getNumLives() == 0){
            this.state = 3;
            return true;
        }
        return false;
    }

    public boolean containsBomb(){
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).isBomb()){
                return true;
            }
        }
        return false;
    }

    public void checkCollisions(int x, int y){
        for(int i = 0; i < items.size(); i++) {
            Item check = items.get(i);

            if (check.isClicked(x,y) && !check.isSliced()){
                check.setSliced(true);
                items.remove(i);
                if(check.isBomb()){
                    player.liveLost();
                }
                else {
                    player.addScore(1);
                }
                i--;
            }
        }
    }

    public void newWave(){
        this.round++;

        if(this.round < 3){
            for(int i = 0; i < this.difficulty; i++){
                items.add(new Item((int) ((Math.random() * 1100) + 100),(int) ((Math.random() * 300) + 1000), this.velocity, false,"Resources/watermelon.png", this.window));
            }
        }
        else {
            int numBombs = ((int) Math.random() * 4 + 1);

            for (int i = 0; i < this.difficulty - numBombs; i++) {
                items.add(new Item((int) ((Math.random() * 1100) + 100), (int) ((Math.random() * 300) + 1000), this.velocity, false, "Resources/watermelon.png", this.window));
            }

            for (int i = 0; i < numBombs; i++) {
                items.add((new Bomb((int) ((Math.random() * 1100) + 100), (int) ((Math.random() * 300) + 1000), this.velocity, true, "Resources/bomb.png", this.window)));
            }
        }

    }

    public void update() {
        while (!gameOver) {
            if (!items.isEmpty()) {
                this.difficulty += 3;
                this.velocity += 10;
                newWave();
                window.repaint();
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e){
        int x = e.getX();
        int y = e.getY();
        checkCollisions(x,y);
    }

    public void mouseClicked(MouseEvent e){
        if(this.state == 0 || this.state == 1){
            this.state++;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void playGame(){
        update();
    }


    public static void main(String[] args) {
        Game game = new Game();

        game.playGame();
    }
}

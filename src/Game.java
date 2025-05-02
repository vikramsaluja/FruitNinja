import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

// Vikram Saluja Fruit Ninja Game

public class Game implements MouseListener, MouseMotionListener, ActionListener {
    private Player player;
    private int state;
    private ArrayList<Item> items;
    private GameView window;
    private int difficulty;
    private int velocity;
    private int round;
    private int x;
    private int y;
    private int timer;

    public Game() {
        this.state = 0;
        this.items = new ArrayList<Item>();
        this.window = new GameView(this);
        this.difficulty = 0;
        this.round = 0;
        this.velocity = this.difficulty + 10;
        this.timer = 0;

        this.player = new Player();


        window.repaint();

        this.window.addMouseListener(this);
        this.window.addMouseMotionListener(this);

        Timer clock = new Timer(60, this);
        clock.start();

    }

    public void actionPerformed(ActionEvent e) {
        // If game is still running
        if(this.state == 1 && !gameOver()) {
            for (int i = 0; i < items.size(); i++) {
                Item temp = items.get(i);
                // Move every item
                temp.move();
                // If item is off the screen remove it from list
                if (offScreen(temp)) {
                    // If the item is not a bomb then a live is a lost
                    if (!temp.isBomb()) {
                        player.liveLost();
                    }
                    // Remove off-screen items from list
                    items.remove(i);
                    i--;
                }
            }
            // Run new waves when there are no more items
            if (items.isEmpty() || (onlyBomb() && this.round >= 3)) {
                // Increment timer
                timer++;
                // Wait before a creating new wave
                if(timer == 20) {
                    this.difficulty++;
                    newWave();
                    // Reset timer
                    timer = 0;
                }

            }
        }
        if(gameOver()){
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

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public ArrayList<Item> getItems(){
        return this.items;
    }

    public boolean offScreen(Item item){
        if (item.getY() <= 0) {
            return true;
        }
        return false;
    }

    public boolean gameOver(){
        if(player.getNumLives() == 0){
            this.state = 3;
            return true;
        }
        return false;
    }

    public boolean onlyBomb(){
        int counter = 0;
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).isBomb()){
                counter++;
            }
        }
        if(counter == items.size()){
            return true;
        }
        else{
            return false;
        }
    }

    public void checkCollisions(){
        for(int i = 0; i < items.size(); i++) {
            Item check = items.get(i);

            if (check.isClicked(this.x,this.y) && !check.isSliced()){
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

    public void clearItems(){
        while(!items.isEmpty()){
            items.remove(0);
        }
    }

    public void newWave(){
        // Everytime there is a new wave, increase the round
        this.round++;

        //  There are no bombs for the first 3 rounds
        if(this.round < 3){
            for(int i = 0; i < this.difficulty; i++){
                // Randomize x coordinate and velocity
                items.add(new Item((int) ((Math.random() * 1100) + 100),(int) ((Math.random() * 300) + 1000), this.velocity, false,"Resources/watermelon.png", this.window));
            }
        }
        else {
            // Clear all items from the screen 100% of the time before round 10
            if(this.round < 10){
                clearItems();
            }
            // After round 10, 75% chance that past bombs get cleared which makes the game harder
            else if(((int) (Math.random() * 4) + 1) != 1){
                // Clear all items, reset screen;
                clearItems();
            }
            // Randomize number of bombs between 1 and 4
            int numBombs = ((int) (Math.random() * 4) + 1);

            // Create new fruits with each round
            for (int i = 0; i < ((int)(Math.random() *6)+(this.difficulty-6)); i++) {
                // Randomize x coordinate and velocity
                items.add(new Item((int) ((Math.random() * 1100) + 100), (int) ((Math.random() * 300) + 1000),((int) (Math.random() * 10) + this.difficulty), false, "Resources/watermelon.png", this.window));
            }
            // Create new bombs for wave
            for (int i = 0; i < numBombs; i++) {
                // Randomize x coordinate and velocity
                items.add((new Bomb((int) ((Math.random() * 1100) + 100), (int) ((Math.random() * 300) + 1000), ((int) (Math.random() * 7) + this.difficulty), true, "Resources/bomb.png", this.window)));
            }
        }

    }

    public void update() {
        // Keep game running while game is not over
        while (!gameOver()) {
            if (!items.isEmpty()) {
                this.difficulty += 1;
                newWave();
                window.repaint();
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e){
        this.x = e.getX();
        this.y = e.getY();
        checkCollisions();
    }

    public void mouseClicked(MouseEvent e){
        if(this.state == 0){
            this.state++;
        }
        if(this.state == 3){
            this.state = 0;
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

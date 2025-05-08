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
        // Set the state of the game to start screen
        this.state = 0;
        this.items = new ArrayList<Item>();
        // Set all instance variables to its base
        this.difficulty = 0;
        this.round = 0;
        this.velocity = this.difficulty + 10;
        this.timer = 0;
        this.player = new Player();

        // Create window
        this.window = new GameView(this);


        window.repaint();

        this.window.addMouseListener(this);
        this.window.addMouseMotionListener(this);

        Timer clock = new Timer(60, this);
        clock.start();

    }

    // Reset game method which allows player to play again
    public void resetGame(){
        // Similarly to the constructor, reset all the instace variables
        this.state = 0;
        this.items = new ArrayList<Item>();
        this.difficulty = 0;
        this.round = 0;
        this.velocity = this.difficulty + 10;
        this.timer = 0;

        this.player = new Player();

        window.repaint();
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
        // Change the state when the game is over
        if(gameOver()){
            this.state = 3;
        }
        window.repaint();

    }

    // Getter methods
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

    // Checks to see if an item has gone off of the screen
    public boolean offScreen(Item item){
        // If the y value is off of the screen then return true
        if (item.getY() <= 0) {
            return true;
        }
        return false;
    }

    public boolean gameOver(){
        // When a player has run out of lives, that means that the game is over
        if(player.getNumLives() == 0){
            this.state = 3;
            return true;
        }
        return false;
    }

    // Checks to see if the only items that are left are bombs
    public boolean onlyBomb(){
        int counter = 0;
        // For every item, if the item is bomb then increment counter
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).isBomb()){
                counter++;
            }
        }
        // returns true if the amount of bombs is the same as the amount of items left
        if(counter == items.size()){
            return true;
        }
        else{
            return false;
        }
    }

    // Methods that checks if an item has been sliced
    public void checkCollisions(){
        for(int i = 0; i < items.size(); i++) {
            // Get each item on the arrayList
            Item check = items.get(i);

            // If the item has not already been sliced and isClicked then remove the item
            if (check.isClicked(this.x,this.y) && !check.isSliced()){
                check.setSliced(true);
                items.remove(i);
                // If the item is a bomb then the player loses a life
                if(check.isBomb()){
                    player.liveLost();
                }
                // If the item was not a bomb then the player gains a point
                else {
                    player.addScore(1);
                }
                // if an item has been removed, change i
                i--;
            }
        }
    }


    public void clearItems(){
        // Remove all the current items
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
            else if(((int) (Math.random() * 3) + 1) != 1){
                // Clear all items, reset screen;
                clearItems();
            }
            // Randomize number of bombs between 1 and 4
            int numBombs = ((int) (Math.random() * 4) + 1);

            // Create new fruits with each round
            for (int i = 0; i < ((int)(Math.random() *5)+(this.difficulty-4)); i++) {
                // Randomize x coordinate and velocity
                items.add(new Item((int) ((Math.random() * 1100) + 100), (int) ((Math.random() * 300) + 1000),((int) (Math.random() * 10) + this.difficulty), false, "Resources/watermelon.png", this.window));
            }
            // Create new bombs for wave
            for (int i = 0; i < numBombs; i++) {
                String bomb;
                // Create random number between 1 and 3
                int rand = (int) (Math.random() * 3) + 1;
                // Randomize what the bomb image is going to be
                if(rand == 1){
                    bomb = "Resources/bomb.png";
                } else if (rand == 2) {
                    bomb = "Resources/bomb2.png";
                }
                else{
                    bomb = "Resources/bomb3.png";
                }
                // Randomize x coordinate and velocity
                items.add((new Bomb((int) ((Math.random() * 1100) + 100), (int) ((Math.random() * 300) + 1000), ((int) (Math.random() * 7) + this.difficulty), true, bomb, this.window)));
            }
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e){
        this.x = e.getX();
        this.y = e.getY();
        // Check to see if there is a collision every time that the mouse is moved
        checkCollisions();
    }

    public void mouseClicked(MouseEvent e){
        // If a player clicks at the start screen, then change the state
        if(this.state == 0){
            this.state++;
        }
        // If the player clicks at the end scree, replay the game
        if(this.state == 3){
            resetGame();
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



    public static void main(String[] args) {
        // Create new game
        Game game = new Game();

    }
}

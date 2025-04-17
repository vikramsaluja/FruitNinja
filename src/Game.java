import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.awt.event.MouseEvent;

// Vikram Saluja Fruit Ninja Game

public class Game implements MouseListener, MouseMotionListener {
    private Player player;
    private int state;
    private boolean gameOver;
    private ArrayList<Item> items;
    private GameView window;
    private int difficulty;

    public Game() {
        this.state = 3;
        this.items = new ArrayList<Item>();
        this.window = new GameView(this);
        gameOver = false;
        this.difficulty = 2;

        this.player = new Player();


        this.items.add(new Item(500,500,0,false,"Resources/apple.png", this.window));


        window.repaint();

        this.window.addMouseListener(this);
        this.window.addMouseMotionListener(this);

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

    public boolean GameOver(){
        if(player.getNumLives() == 0){
            this.state = 3;
            return true;
        }
        return false;
    }

    public void checkCollisions(int x, int y){
        for(int i = 0; i < items.size(); i++) {
            Item check = items.get(i);

            if (check.isClicked(x,y) && !check.isSliced()){
                System.out.println("Collison");
                check.setSliced(true);
                items.remove(i);
                if(check.bomb()){
                    player.liveLost();
                }
                else {
                    player.addScore(1);
                }
                i--;
                window.repaint();
            }
        }
    }

    public void newWave(){
        for(int i = 0; i < difficulty; i++){
//            items.add(new Item(((Math.random() * 700) + 400), 20);
        }
    }

    public void update(){

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
        while(!gameOver){
            update();
        }
    }


    public static void main(String[] args) {
        Game game = new Game();

        game.playGame();
    }
}

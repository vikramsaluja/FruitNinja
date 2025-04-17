import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    final int WINDOW_WIDTH = 1300;
    final int WINDOW_HEIGHT = 1000;
    private Game game;

    public GameView(Game game){
        this.game = game;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Fruit Ninja");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public void startScreen(Graphics g){
        g.setColor(Color.red);
        g.drawString("CLICK TO PLAY!", 500,500);
    }

    public void printInstructions(Graphics g){
        g.setColor(Color.red);
        g.drawString("Instructions!", 400, 400);
    }

    public void paintLives(Graphics g){

    }

    public void paintScore(Graphics g){

    }

    public void paintItems(Graphics g){
        for(Item item : game.getItems()){
            item.draw(g);
        }
    }

    public void paintPlay(Graphics g){
        paintItems(g);
        paintLives(g);
    }

    public void paintGameOver(Graphics g){
        g.setColor(Color.red);
        g.drawString("GAME OVER", 500,500);
    }

    public void paint(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0,0,WINDOW_WIDTH,WINDOW_HEIGHT);
        if(game.getState() == 0){
            startScreen(g);
        }
        else if(game.getState() == 1){
            printInstructions(g);
        }
        else if(game.getState() == 2){
            paintPlay(g);
        }
        else{
            paintGameOver(g);
        }

        paintItems(g);

    }

}

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

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
        this.createBufferStrategy(2);
    }

    public void startScreen(Graphics g){
        g.setColor(Color.red);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("FRUIT NINJA GAME", 500, 450);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("CLICK TO VIEW INSTRUCTIONS", 507,550);
    }

    public void printInstructions(Graphics g){
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.setColor(Color.red);
        g.drawString("Instructions!", 400, 400);
    }

    public void paintLives(Graphics g){
        int lives = game.getPlayer().getNumLives();
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.red);
        g.drawString("Lives: ", 1030, 65);
        int margin = 1070;

        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.setColor(Color.red);
        for(int i = 0; i < lives; i++) {
            margin += 30;
            g.drawString("X", margin, 70);
        }
    }

    public void paintScore(Graphics g){
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.red);
        g.drawString("Score: " + game.getPlayer().getScore(), 25, 70);
    }

    public void paintItems(Graphics g){
        for(int i = 0; i < game.getItems().size();i++){
            Item item = game.getItems().get(i);
            if(item.isSliced()){
                game.getItems().remove(i);
                i--;
            }
            item.draw(g);
        }
    }

    public void paintPlay(Graphics g){
        paintItems(g);
        paintLives(g);
        paintScore(g);

    }

    public void paintGameOver(Graphics g){
        g.setColor(Color.red);
        g.setFont(new Font("Arial", Font.BOLD, 75));
        g.drawString("GAME OVER", 400,500);
        paintScore(g);
        paintLives(g);
    }

    public void paint(Graphics g){
        BufferStrategy bf = this.getBufferStrategy();
        if (bf == null)
            return;
        Graphics g2 = null;
        try {
            g2 = bf.getDrawGraphics();
            myPaint(g2);
        }
        finally {
            g2.dispose();
        }
        bf.show();
        Toolkit.getDefaultToolkit().sync();
    }


    public void myPaint(Graphics g){
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

    }

}

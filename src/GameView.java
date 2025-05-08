import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameView extends JFrame {
    private Image mImage;
    final int WINDOW_WIDTH = 2000;
    final int WINDOW_HEIGHT = 1000;
    private Game game;

    public GameView(Game game){
        // Create sword image
        this.mImage = new ImageIcon("Resources/sword.png").getImage();
        this.game = game;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Watermelon Slice");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        this.createBufferStrategy(2);
    }

    public void paintMouse(Graphics g){
        // Draw the sword image
        g.drawImage(this.mImage,game.getX()-50, game.getY()-50,100,100,this);
    }


    public void startScreen(Graphics g){
        // Set the color to red and print out the start screen
        g.setColor(Color.red);
        g.setFont(new Font("SansSerif", Font.BOLD, 30));
        g.drawString("WATERMELON SLICE GAME", 540, 450);
        g.setFont(new Font("SansSerif", Font.BOLD, 20));
        g.drawString("CLICK TO PLAY!", 657,550);
    }

    public void paintLives(Graphics g){
        // Save the number of lives that a player has
        int lives = game.getPlayer().getNumLives();
        // Set font and size
        g.setFont(new Font("Dialog", Font.BOLD, 20));
        g.setColor(Color.red);
        g.drawString("Lives: ", 1030, 65);
        // Create margin between the 'X'
        int margin = 1080;

        g.setFont(new Font("Serif", Font.BOLD, 30));
        g.setColor(Color.red);
        // For every live that a player has, draw 'X'
        for(int i = 0; i < lives; i++) {
            margin += 30;
            g.drawString("X", margin, 70);
        }
    }

    public void paintScore(Graphics g){
        g.setFont(new Font("Dialog", Font.BOLD, 20));
        g.setColor(Color.red);
        // Paint the players score in the top right of the screen
        g.drawString("Score: " + game.getPlayer().getScore(), 25, 70);
    }

    public void paintItems(Graphics g){
        // Paint each item on teh screen
        for(int i = 0; i < game.getItems().size();i++){
            Item item = game.getItems().get(i);
            // If an item is removed then remove don't draw it
            if(item.isSliced()){
                game.getItems().remove(i);
                // When an item is removed, change i
                i--;
            }
            // Have each item draw itself
            item.draw(g);
        }
    }

    public void paintPlay(Graphics g){
        // Call all the methods that make up the playing screen
        paintMouse(g);
        paintItems(g);
        paintLives(g);
        paintScore(g);

    }

    public void paintGameOver(Graphics g){
        // Set color and font
        g.setColor(Color.red);
        g.setFont(new Font("SanSerif", Font.BOLD, 75));
        // Draw Game Over
        g.drawString("Game Over", 500,500);
        g.setFont(new Font("SanSerif", Font.BOLD, 25));
        g.drawString("Click to play again!", 575, 600);

        // Still display the player's score
        paintScore(g);
        // Display that the player has run out of lives
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
        // Set the background color of the game
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,0,WINDOW_WIDTH,WINDOW_HEIGHT);
        // If the state is 0, then paint the start screen
        if(game.getState() == 0){
            startScreen(g);
        }
        // If the state is 1, paint the game screen
        else if(game.getState() == 1){
            paintPlay(g);
        }
        else{
            // Paint the game over screen
            paintGameOver(g);
        }

    }

}

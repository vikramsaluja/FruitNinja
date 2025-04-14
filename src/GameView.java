import javax.swing.*;
import java.awt.*;

public class GameViewer extends JFrame {
    final int WINDOW_WIDTH = 900;
    final int WINDOW_HEIGHT = 700;
    private Game game;

    public GameViewer(Game game){
        this.game = game;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Fruit Ninja");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public void printInstructions(Graphics g){

    }

    public void paintLives(Graphics g){

    }

    public void paintScore(Graphics g){

    }

}

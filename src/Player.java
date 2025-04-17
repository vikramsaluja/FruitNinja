import java.awt.*;

public class Player {

    private int score;
    private int numLives;

    public Player() {
        this.numLives = 3;
        this.score = 0;
    }

    // Getters
    public int getScore(){
        return this.score;
    }

    public int getNumLives(){
        return this.numLives;
    }


    // Setters
    public void setScore(int score) {
        this.score = score;
    }

    public void setNumLives(int numLives) {
        this.numLives = numLives;
    }

    public void addScore(int points){
        score += points;
    }

    public void liveLost(){
        numLives -= 1;
    }
}

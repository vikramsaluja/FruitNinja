public class Player {

    private int score;
    private int numLives;

    public Player() {
        // Set the number of lives that the player has
        this.numLives = 3;
        // Set the score to 0
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
        // Increase the players score
        score += points;
    }

    public void liveLost(){
        // Remove a life from the player
        numLives -= 1;
    }
}

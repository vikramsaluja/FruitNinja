import javax.swing.*;
import java.awt.*;

public class Item {
    private boolean sliced;
    private int x;
    private int y;
    private boolean isBomb;
    private Image image;
    private GameView window;
    private int dY;
    private int imageWidth;
    private int imageHeight;

    public Item(int x,int y, int difficulty, boolean isBomb, String fileName, GameView window){
        // Intialize an items x and y coordinate values
        this.x = x;
        this.y = y;
        this.isBomb = isBomb;
        // Set item not sliced
        this.sliced = false;
        // Create image
        String file = fileName;
        this.image = new ImageIcon(file).getImage();
        this.window = window;

        this.y = 900;

        // Set the items velocity to the difficulty
        this.dY = difficulty;

        // Set the images size
        this.imageWidth = 60;
        this.imageHeight = 60;

    }

    // Returns if an item is a bomb or not
    public boolean isBomb(){
        return this.isBomb;
    }

    // Check to see if a bomb is clicked
    public boolean isClicked(int mouseX, int mouseY) {
        // Check if mouse is within the boundaries of item, meaning that it is clicked
        if(mouseX >=  this.x && mouseX - 25 <= this.x + this.imageWidth && mouseY >= this.y && mouseY - 25 <= this.y + this.imageHeight){
            return true;
        }
        return false;

    }

    // Getters
    public boolean isSliced() {
        return sliced;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public Image getImage() {
        return image;
    }

    // Setters
    public void setSliced(boolean sliced) {
        this.sliced = sliced;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void move(){
        // Move the item depending on its velocity
        this.y -= dY;
    }

    // Have each item draw itself
    public void draw(Graphics g){
        g.drawImage(this.image, this.x,this.y,this.imageWidth,this.imageHeight,window);
    }


}

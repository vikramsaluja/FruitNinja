import javax.swing.*;
import java.awt.*;

public class Item {
    private boolean sliced;
    private int x;
    private int y;
    private double velocity;
    private boolean isBomb;
    private Image image;
    private GameView window;

    public Item(int x, int y, double velocity, boolean isBomb, String fileName, GameView window){
        this.x = x;
        this.y = y;
        this.velocity = velocity;
        this.isBomb = isBomb;
        this.sliced = false;
        String file = fileName;
        this.image = new ImageIcon(file).getImage();
        this.window = window;
    }

    public boolean bomb(){
        return this.isBomb;
    }

    public boolean isClicked(int mouseX, int mouseY) {
         int val = 30;
        if(mouseX > this.x + val && mouseX < this.x + image.getWidth(window) - val  && mouseY > this.y + val && mouseY < this.y + image.getHeight(window) - val){
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

    public double getVelocity() {
        return velocity;
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

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void draw(Graphics g){
        g.drawImage(this.image, this.x,this.y,100,100,window);
    }


}

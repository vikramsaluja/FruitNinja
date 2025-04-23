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

    public Item(int x,int y, int difficulty, boolean isBomb, String fileName, GameView window){
        this.x = x;
        this.y = y;
        this.isBomb = isBomb;
        this.sliced = false;
        String file = fileName;
        this.image = new ImageIcon(file).getImage();
        this.window = window;

        this.y = 900;

        this.dY = difficulty;

    }

    public boolean isBomb(){
        return this.isBomb;
    }

    public boolean isClicked(int mouseX, int mouseY) {
        if(mouseX > this.x && mouseX < this.x + image.getWidth(window) && mouseY > this.y && mouseY < this.y + image.getHeight(window)){
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
        this.y -= dY;
    }

    public void draw(Graphics g){
        g.drawImage(this.image, this.x,this.y,60,60,window);
    }


}

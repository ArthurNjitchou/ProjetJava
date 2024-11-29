
import java.awt.*;
import javax.swing.ImageIcon;

public class Sprite implements Displayable {
    protected final Image image;
    protected double x;
    protected double y;
    protected double width;
    protected double height;


    public Sprite(double x, double y, Image image, double width, double height) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.width = width;
        this.height = height;
    }
    public Image getImage() {
        return image;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, (int) x, (int) y, (int) width, (int) height, null);
    }
}
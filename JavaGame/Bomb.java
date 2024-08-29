import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Bomb implements InteractableDrawing {
    // instances
    private int xPoint, yPoint;
    private BufferedImage bombLogo;
    private int whSize = 35;

    // constructor
    public Bomb(int getX, int getY) {
        this.xPoint = getX;
        this.yPoint = getY;

        // set the image of the bomb
        try {
            bombLogo = ImageIO.read(new File("C:\\Users\\Администратор\\Desktop\\cs102LAB4\\pngegg.png"));
            bombLogo = setSizeBomb(bombLogo, whSize, whSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean intersects(Ship s) {
        Rectangle bombBox = new Rectangle(xPoint, yPoint, whSize, whSize);
        Rectangle shipBox = new Rectangle(s.getX(), s.getY(), 40, 20);
        return bombBox.intersects(shipBox);
    }

    // decrease the life score of the ship for intersections with the bombs
    public void interact(Ship ship) {
        ship.decreaseL();
    }

    public boolean moveLeft(int getSpeed) {
        xPoint -= getSpeed;
        boolean offLimit = true;
        if (xPoint + whSize < 0)
            return offLimit;
        return !offLimit;
    }

    public void draw(Graphics g) {
        if (bombLogo != null) {
            g.drawImage(bombLogo, xPoint, yPoint, null);
        } else {
            g.setColor(Color.BLACK);
            g.fillOval(xPoint, yPoint, whSize, whSize);
        }
    }

    // setting the size of an uploaded image of the bomb with the preffered size
    private BufferedImage setSizeBomb(BufferedImage image, int w, int h) {
        BufferedImage modifiedBomb = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = modifiedBomb.createGraphics();
        g.drawImage(image, 0, 0, w, h, null);
        g.dispose();
        return modifiedBomb;
    }
}

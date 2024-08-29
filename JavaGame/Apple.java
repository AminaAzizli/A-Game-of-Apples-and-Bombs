import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Apple implements InteractableDrawing {
    // instances
    private int xPoint, yPoint;
    private BufferedImage appleLogo;
    private int whSize = 35;


    // constructor
    public Apple(int x, int y) {
        this.xPoint = x;
        this.yPoint = y;


        // set the image for apple
        try {
            appleLogo = ImageIO.read(new File(
                    "C:\\Users\\Администратор\\Desktop\\cs102LAB4\\—Pngtree—pixel game fruit red apple_6316721.png"));
            appleLogo = setSizeApple(appleLogo, whSize, whSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Rectangle object to represent their size and positions

    public boolean intersects(Ship ship) {
        Rectangle appleBox = new Rectangle(this.xPoint, this.yPoint, whSize, whSize);
        Rectangle shipBox = new Rectangle(ship.getX(), ship.getY(), 40, 20);
        return appleBox.intersects(shipBox);
    }

    // for apple, if ship intersects with the apple object, the life will be increases by 1
    public void interact(Ship ship) {
        ship.setScore(ship.getScore()+1);
    }

    public boolean moveLeft(int getSpeed) {
        xPoint -= getSpeed;
        boolean offLimit = true;
        if (xPoint + whSize <= 0)
            return offLimit;
        return !offLimit;
    }

    public void draw(Graphics g) {
        if (appleLogo != null) {
            g.drawImage(appleLogo, xPoint, yPoint, null);
        } else {
            // to escape the error
            g.setColor(Color.RED);
            g.fillOval(xPoint, yPoint, whSize, whSize);
        }
    }

    // set the size of an image
    private BufferedImage setSizeApple(BufferedImage image, int w, int h) {
        BufferedImage modifiedApple = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = modifiedApple.createGraphics();
        g.drawImage(image, 0, 0, w, h, null);
        g.dispose();
        return modifiedApple;
    }
}

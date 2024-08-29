import java.awt.Graphics;

//interface to be implmented in apple and bomb classes
public interface InteractableDrawing {
 boolean intersects(Ship s);
 void interact(Ship s);
 boolean moveLeft(int speed);
 void draw(Graphics g);
}
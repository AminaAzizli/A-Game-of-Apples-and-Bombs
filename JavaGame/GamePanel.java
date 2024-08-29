import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePanel extends JPanel implements MouseMotionListener {
    private Ship ship;
    private BufferedImage newton;
    private ArrayList<InteractableDrawing> obejcts;
    private int scoreCount;
    private int lifeCount;
    private int getSpeed;

    public GamePanel(String name, int speed) {
        scoreCount = 0;
        this.getSpeed = speed; //to clarify the score of apple and bomb
        this.ship = new Ship(name);
        obejcts = new ArrayList<>();
        lifeCount = ship.getLife();

        addMouseMotionListener(this);

        // image for the Player
        try {
            newton = ImageIO.read(new File("C:\\Users\\Администратор\\Desktop\\cs102LAB4\\pngwing.com.png"));
            newton = modifyTheImageSize(newton, 40, 40); 
        } catch (IOException e) {
            e.printStackTrace();
        }

        gameStarts();
    }
    private BufferedImage modifyTheImageSize(BufferedImage image, int w, int h) {
        BufferedImage modifiedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = modifiedImage.createGraphics();
        g.drawImage(image, 0, 0, w, h, null);
        g.dispose();
        return modifiedImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE); //for background
        g.fillRect(0, 0, getWidth(), getHeight()); //new page after main menu

        if (newton != null) {
            g.drawImage(newton, ship.getX() - 10, ship.getY() - 10, this); //resize
        }

        for (InteractableDrawing obj : obejcts) {
            obj.draw(g);
        }

      
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        ship.setX(e.getX());
        ship.setY(e.getY());
        shipsIntersectins();
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //
    }

    public void gameStarts() {
        Timer timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createsObjects();
                repaint();
            }
        });
        timer.start();
    }

    //apples and bombs appearing in the game
    private void createsObjects() {
        startMovingObjects();
        // startMovingBombs();
    }

    private void startMovingObjects() {
        for (int i = 0; i < obejcts.size(); i++) {
            if (obejcts.get(i).moveLeft(getSpeed)) { //creates the apple objects from the right to the left
                obejcts.remove(i); 
                i--;
            }
        }

        Random rand = new Random(); //randomly adjust the apple objects
        if (rand.nextDouble() < 0.05) {
            obejcts.add(new Apple(getWidth(), rand.nextInt(getHeight() - 35)));
            obejcts.add(new Bomb(getWidth(), rand.nextInt(getHeight() - 25)));
        }
    }



    private void shipsIntersectins() {
        //when ship intersect with the apple, make modifications based on that and then remove this apple object form the list
        for (int obg = 0; obg < obejcts.size(); obg++) {
            if (obejcts.get(obg).intersects(ship)) {
                obejcts.get(obg).interact(ship);

                updateOfTitleInfo();
                obejcts.remove(obg);
                obg--;
            }
        }
    

            if (ship.getLife() == 0) {
            gameEnds();
        }
    }
    


    private void updateOfTitleInfo() {
        String modifiedTitle = "Life: " + ship.getLife() + "- Score: " + ship.getScore();
        JFrame fr = (JFrame) SwingUtilities.getWindowAncestor(this);
        fr.setTitle(modifiedTitle);
    }
    private void playAgain() {
        ship.setLife(3); //initialize the life score
        ship.setScore(0); //initialize the score

        //removes the objects from the frame
        obejcts.clear(); 
       
    }

    private void gameEnds() {
        int c = JOptionPane.showConfirmDialog(this, "Score: " + ship.getScore() + ", do you want to play again?", "Game Over", JOptionPane.YES_NO_CANCEL_OPTION);
        if (c == JOptionPane.YES_OPTION) {
            playAgain();
        } else if (c == JOptionPane.NO_OPTION) {
            System.exit(0);
        } else if (c == JOptionPane.CANCEL_OPTION) {
        }
    }

}

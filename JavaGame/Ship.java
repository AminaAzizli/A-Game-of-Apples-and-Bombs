// Ship.java
public class Ship {
    private int xPoint, yPoint;
    private int shipsLife;
    private int score;
    private String name;

    public Ship(String getName) {
        this.name = getName;
        this.shipsLife = 3; // inital score for the life when game starts
        score = 0;
    }

    // helper (getter/setter) methods
    public int getX() {
        return xPoint;
    }

    public int getY() {
        return yPoint;
    }

    public int getLife() {
        return shipsLife;
    }
    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void setX(int getX) {
        this.xPoint = getX;
    }

    public void setY(int getY) {
        this.yPoint = getY;
    }

    // increase the score of the life everytime it intersects with the apple
    public void increseL() {
        shipsLife++;
    }

    // decrease the score of the life everytime it intersects with the bomb
    public void decreaseL() {
        shipsLife--;
    }

    public void setLife(int getLifeScore) {
        this.shipsLife = getLifeScore;
    }
    public void setScore(int getScore){
        this.score = getScore;
    }
}

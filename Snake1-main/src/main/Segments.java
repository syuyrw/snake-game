package main;

// Define Position rectangle on screen
public class Segments {
    private int positionX;
    private int positionY;


    //Place on screen
    public Segments(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public boolean intersects(Segments other) {
        //Detect if Snake Collides With Self
        return this.positionX == other.getPositionX() && this.positionY == other.getPositionY();
    }

    //Current Position
    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    //Movement
    public void setPositionX(int increment) {
        positionX += increment;
    }

    public void setPositionY(int increment) {
        positionY += increment;
    }
}
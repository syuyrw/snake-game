package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class Snake extends JPanel {

    private static final Color c = new Color(115,162,78);
    private static final int start = 250;
    private static final int speed = 25;

    private ArrayList<Segments> body;

    private String direction;

    private Fruit apple;

    private Main window;

    public static final int WIDTH = 25;
    public static final int HEIGHT = 25;

    //Create snake in window
    public Snake(main.Main window) {
        this.window = window;
        this.body = new ArrayList<>();
        body.add(new Segments(start, start));
        Segments last = this.body.get(0);
        body.add(new Segments(last.getPositionX() - HEIGHT, last.getPositionY()));
        Segments last_2 = this.body.get(1);
        body.add(new Segments(last_2.getPositionX() - WIDTH, last_2.getPositionY()));
        this.direction = "right";
    }

    //Change direction based on user input
    public void setDirection(String direction) {
        this.direction = direction;
    }
    public String getDirection() {
        return this.direction;
    }

    //Add length to the end of snake
    public void addPart() {
        Segments last = this.body.get(this.body.size() -1);
        switch (this.direction) {
            case "right" -> this.body.add(new Segments(last.getPositionX() - WIDTH, last.getPositionY()));
            case "left" -> this.body.add(new Segments(last.getPositionX() + WIDTH, last.getPositionY()));
            case "up" -> this.body.add(new Segments(last.getPositionX() , last.getPositionY() + WIDTH));
            case "down" -> this.body.add(new Segments(last.getPositionX(), last.getPositionY()  - WIDTH));
        }
    }

    //Check if head is colliding with any of the lengths
    public void checkCollision() {
        Segments r3 = this.body.get(0);
        for (int i = 1; i < this.body.size(); i++) {
            Segments r2 = this.body.get(i);

            if (r3.intersects(r2)) {
                System.out.println("You lose!");
                this.window.setVisible(false);

                JFrame parent = new JFrame("Game over!");
                JOptionPane.showMessageDialog(parent, "Your score: " + this.body.size());

                //Close game
                this.window.dispatchEvent(new WindowEvent(this.window, WindowEvent.WINDOW_CLOSING));
                System.exit(0);
            }
        }

        //Add length if apple is eaten
        if (this.apple != null) {
            if (r3.intersects(new Segments(this.apple.getPositionX(),this.apple.getPositionY()))) {
                this.apple = null;
                this.addPart();
            }
        }

    }

    //Move the snake
    public void moveSnake() {

        ArrayList<Segments> newLst = new ArrayList<>();

        Segments first = this.body.get(0);
        Segments head = new Segments(first.getPositionX(), first.getPositionY());

        switch (this.direction) {
            case "right" -> head.setPositionX(speed);
            case "left" -> head.setPositionX(-speed);
            case "up" -> head.setPositionY(-speed);
            case "down" -> head.setPositionY(speed);
        }
        newLst.add(head);

        for (int i = 1; i < this.body.size(); i++) {
            Segments previous = this.body.get(i-1);
            //Moves lengths that are not the first one to fill in where the one before it was
            Segments newRec = new Segments(previous.getPositionX(), previous.getPositionY());
            newLst.add(newRec);
        }


        this.body = newLst;
        checkCollision();
    }

    private void drawSnake(Graphics g) {
        moveSnake();

        //Draw moved snake
        Graphics2D g2d = (Graphics2D) g;


        if (this.apple != null) {
            g2d.setPaint(Color.red);
            g2d.drawRect(this.apple.getPositionX(), this.apple.getPositionY(), WIDTH, HEIGHT);
            g2d.fillRect(this.apple.getPositionX(),this.apple.getPositionY(),WIDTH,HEIGHT);
        }

        g2d.setPaint(Color.blue);
        for (Segments rec: this.body) {
            g2d.drawRect(rec.getPositionX(),rec.getPositionY(), WIDTH, HEIGHT);
            g2d.fillRect(rec.getPositionX(),rec.getPositionY(), WIDTH, HEIGHT);
        }
    }

    public void setApple(Fruit apple) {
        this.apple = apple;
    }

    public Fruit getApple() {
        return this.apple;
    }

    //Redraw screen with new snake position and apple
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(c);
        drawSnake(g);
    }
}
